/**
 * Created the com.xsy.web.DefaultServlet.java
 * @created 2016年9月24日 下午12:28:55
 * @version 1.0.0
 */
package com.xcc.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.xcc.db.DBFactory;
import com.xcc.utils.Base64Util;
import com.xcc.utils.XUtil;
import com.xcc.utils.logger.Logger;
import com.xcc.web.annotaion.View;
import com.xcc.web.core.ActionInvocation;
import com.xcc.web.core.ActionParameter;
import com.xcc.web.core.ActionPorxy;
import com.xcc.web.core.DefaultInvocation;
import com.xcc.web.core.IApplicationContext;
import com.xcc.web.core.XInitContext;
import com.xcc.web.editor.ParamBindException;
import com.xcc.web.entity.USession;
import com.xcc.web.model.DefaultModel;
import com.xcc.web.model.IModel;

/**
 * com.xsy.web.DefaultServlet.java
 * @author XChao
 */
@MultipartConfig
public class DefaultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IApplicationContext context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = XInitContext.getContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest httpRequest, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			// 重新实现 HttpServletRequest, 方便获取文件数据
			DefaultRequest request = new DefaultRequest(httpRequest);
			// 设置 HttpServletRequest 对象编码
			request.setCharacterEncoding(context.getEncoding());
			// 设置 HttpServletResponse 返回数据类型的编码
			response.setContentType("text/html; charset=" + context.getEncoding());
			// 创建Mode对象
			IModel model = new DefaultModel();
			// 将 Model 对象存入 HttpServletRequest中
			request.setAttribute(IModel.MODEL_KEY, model);
			// 获取ActionPorxy 对象
			ActionPorxy porxy = context.getAction(request.getRequestURI());
			// 如果Action代理对象为空， 表示页面不存在，报出404错误
			if(porxy == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			// 调用登录验证方法 ,交获取指定视图渲染文件地址
			String viewPath = this.loginInterceptor(porxy, model, request, response);
			// 将视图渲染文件地址存入 ActionPorxy 对象
			porxy.setCurrentViewPath(StringUtils.defaultIfEmpty(viewPath, porxy.getDefaultViewPath()));
			// 渲染页面 和 数据
			porxy.getRender().render(model, porxy, request, response);
		} catch (Throwable throwable) {
			throwable = XUtil.getLastCause(throwable);
			Logger.severe("Service response error. ", throwable);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, throwable.getMessage());
		}
	}

	/**
	 * 登录验证, 异常处理, 并调用参数绑定方法
	 * @param porxy ActionPorxy 对象
	 * @param model 数据模型对象
	 * @param request HttpServletRequest 对象
	 * @param response HttpServletResponse 对象
	 * @return
	 * @throws Exception
	 */
	protected String loginInterceptor(ActionPorxy porxy, IModel model, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		try {
			USession usessin = (USession) request.getSession().getAttribute(USession.XUSESSION_KEY);
			if(porxy.getAction().permiss() > 0 && usessin == null) {
				if(porxy.getAction().value() == View.json) {
					model.setError(600).setMessage("Not login in error. ");
					return null;
				}
				if(StringUtils.isNotBlank(context.getLoginUrl())) {
					StringBuilder source = new StringBuilder(porxy.getName());
					if(StringUtils.isNotBlank(request.getQueryString())) {
						source.append("?").append(request.getQueryString());
					}
					return "r:" + context.getLoginUrl() + "?source=" + Base64Util.encodeurl(source.toString());
				}
				throw new RuntimeException("Not login in error.");
			}
			return paramInterceptor(porxy, model, request, response);
		} catch (Throwable throwable) {
			throwable = XUtil.getLastCause(throwable);
			if(porxy.getAction().value() == View.json) {
				// 如果model.error == 0 时,说明没有设置错误信息,强制设置错误码为500, 错误信息为异常信息
				if(model.getError() == 0) {
					model.setError(500).setMessage(throwable.getMessage());
					Logger.severe("Service response error. ", throwable);
				}
				return null;
			}
			throw new ServletException(throwable);
		}
	}

	/**
	 * 绑定参数, 创建数据库连接, 并调用Action调用方法
	 * @param porxy ActionPorxy 对象
	 * @param model 数据模型对象
	 * @param request HttpServletRequest 对象
	 * @param response HttpServletResponse 对象
	 * @return
	 * @throws Exception
	 */
	protected String paramInterceptor(ActionPorxy porxy, IModel model, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ActionParameter parameter = porxy.getParameter();
			for (int i = 0, j = parameter.length(); i < j; i++) {
				try {
					parameter.setValue(i, parameter.getEditor(i).getParamValue(parameter.getName(i),
						parameter.getType(i), request, response));
				} catch (Exception e) {
					throw new ParamBindException("Param binding error. ", e, parameter.getName(i));
				}
			}
			return actionInvocation(porxy, model, request, response);
		} finally {
			for (String name : DBFactory.getThreadDB().keySet()) {
				DBFactory.close(DBFactory.getThreadDB().get(name));
			}
		}
	}

	/**
	 * Action 调用
	 * @param porxy ActionPorxy 对象
	 * @param model 数据模型对象
	 * @param request HttpServletRequest 对象
	 * @param response HttpServletResponse 对象
	 * @return
	 * @throws Exception
	 */
	protected String actionInvocation(ActionPorxy porxy, IModel model, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		// 创建Action调用对象 ActionInvocation,并设置ActionPorxy对象
		ActionInvocation invocation = new DefaultInvocation(porxy);
		// 设置HttpServletRequest 和 HttpServletResponse
		invocation.setRequest(request).setResponse(response);
		// 调用过虑器 和 Action 方法
		return invocation.invoke();
	}

}

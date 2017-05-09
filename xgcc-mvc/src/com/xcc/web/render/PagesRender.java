/**
 * Created the com.xsy.web.render.XPagesRender.java
 * @created 2016年9月28日 下午5:36:00
 * @version 1.0.0
 */
package com.xcc.web.render;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionPorxy;
import com.xcc.web.model.IModel;

/**
 * com.xsy.web.render.XPagesRender.java
 * @author XChao
 */
public class PagesRender implements IRender {
//	获取域名与IP地址商品方法
//	String path = request.getContextPath();
//	String basePath = request.getScheme() + "://" + request.getServerName() + 
// ":" + request.getServerPort() + path + "/";

	@Override
	public void render(IModel model, ActionPorxy porxy, DefaultRequest request, HttpServletResponse response)
		throws Exception {
		String viewPath = porxy.getCurrentViewPath();
		if(StringUtils.isBlank(viewPath)) {
			throw new RuntimeException("********  Not found Page. ***********");
		}
		if(viewPath.trim().startsWith("f:")) {
			StringBuilder dispatcher = new StringBuilder();
			viewPath = viewPath.trim().substring(2).trim();
			if(!viewPath.startsWith("/")) {
				dispatcher.append("/");
			}
			dispatcher.append(viewPath);
			request.getRequestDispatcher(dispatcher.toString()).forward(request, response);
			return;
		}
		if(viewPath.trim().startsWith("r:")) {
			viewPath = viewPath.trim().substring(2).trim();
			if(Pattern.compile("([a-zA-Z]{2,}://\\S+)+").matcher(viewPath).matches()) {
				response.sendRedirect(viewPath);
				return;
			}
			StringBuilder redirect = new StringBuilder();
			if(!viewPath.startsWith("/")) {
				redirect.append(request.getServletContext().getContextPath() + "/");
			}
			response.sendRedirect(redirect.append(viewPath).toString());
			return;
		}
		porxy.getViewPorxy().execute(model, viewPath, request, response);
	}

}

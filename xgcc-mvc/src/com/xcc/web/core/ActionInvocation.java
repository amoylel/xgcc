/**
 * Created the com.xsy.web.core.XActionInvocation.java
 * @created 2016年9月28日 上午11:38:53
 * @version 1.0.0
 */
package com.xcc.web.core;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xcc.db.IDB;
import com.xcc.web.DefaultRequest;
import com.xcc.web.annotaion.Action;
import com.xcc.web.entity.USession;
import com.xcc.web.model.IModel;

/**
 * Action 调用类。所有的 Action 调用都有该类执行。
 * @author XChao
 */
public interface ActionInvocation {

	/**
	 * 获取Action名称
	 * @return
	 */
	public String getName();

	/**
	 * 获取Action 注解信息
	 * @return
	 */
	public Action getAction();

	/**
	 * 获取 HttpServletRequest
	 * @return
	 */
	public DefaultRequest getRequest();

	/**
	 * 设置HttpServletRequest
	 * @param request
	 * @return
	 */
	public ActionInvocation setRequest(DefaultRequest request);

	/**
	 * 获取 HttpServletResponse
	 * @return
	 */
	public HttpServletResponse getResponse();

	/**
	 * 设置 HttpServletResponse
	 * @param response
	 * @return
	 */
	public ActionInvocation setResponse(HttpServletResponse response);

	/**
	 * 获取 HttpSession
	 * @return
	 */
	public HttpSession getSession();

	/**
	 * 获取 IMolde 数据
	 * @return
	 */
	public IModel getIModel();

	/**
	 * 获取数据库访问对象
	 * @return
	 */
	public IDB getDB(String name);

	/**
	 * 获取用户登录Session信息
	 * @return
	 */
	public USession getUSession();

	/**
	 * 获取Action的参数信息
	 * @return
	 */
	public ActionParameter getParameters();

	/**
	 * 根据参数名称 获取Action该参数的值
	 * @return
	 */
	public Object getParameter(String name);

	/**
	 * 执行Action方法
	 * @return
	 * @throws Exception
	 */
	public String invoke() throws Exception;

}

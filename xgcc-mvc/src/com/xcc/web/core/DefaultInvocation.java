/**
 * Created the com.xsy.web.core.XDefaultActionInvocation.java
 * @created 2016年9月28日 下午12:50:08
 * @version 1.0.0
 */
package com.xcc.web.core;

import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xcc.db.IDB;
import com.xcc.web.DefaultRequest;
import com.xcc.web.annotaion.Action;
import com.xcc.web.entity.IUser;
import com.xcc.web.interceptor.InterceptorPorxy;
import com.xcc.web.model.IModel;

/**
 * com.xsy.web.core.DefaultInvocation.java
 * @author XChao
 */
public class DefaultInvocation implements ActionInvocation {
	private ActionPorxy actionPorxy;
	private DefaultRequest request;
	private HttpServletResponse response;
	private Iterator<InterceptorPorxy> iterator;

	public DefaultInvocation(ActionPorxy actionPorxy) {
		iterator = actionPorxy.getInterceptorsPorxy().iterator();
		this.actionPorxy = actionPorxy;
	}

	public String getName() {
		return actionPorxy.getName();
	}

	public Action getAction() {
		return actionPorxy.getAction();
	}

	public DefaultRequest getRequest() {
		return this.request;
	}

	public ActionInvocation setRequest(DefaultRequest request) {
		this.request = request;
		return this;
	}

	public HttpServletResponse getResponse() {
		return this.response;
	}

	public ActionInvocation setResponse(HttpServletResponse response) {
		this.response = response;
		return this;
	}

	public HttpSession getSession() {
		return request.getSession();
	}

	public IModel getIModel() {
		return (IModel) this.request.getAttribute(IModel.IMODEL_KEY);
	}

	public IDB getDB(String name) {
		return (IDB) this.getParameter(name);
	}

	public IUser getUSession() {
		return (IUser) this.getSession().getAttribute(IUser.IUSER_KEY);
	}

	public ActionParameter getParameters() {
		return actionPorxy.getParameter();
	}

	public Object getParameter(String name) {
		return this.request.getAttribute(ActionParameter.PARAMETER_KEY + name);
	}

	public String invoke() throws Exception {
		if(this.iterator.hasNext()) {
			InterceptorPorxy interceptorPorxy = iterator.next();
			return interceptorPorxy.invoke(DefaultInvocation.this);
		}
		return this.actionPorxy.execute(this.getParameters().getValues());
	}

}

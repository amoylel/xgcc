/**
 * Created the com.xsy.web.core.XActionContext.java
 * @created 2016年9月26日 下午2:52:23
 * @version 1.0.0
 */
package com.xcc.web.core;

import java.util.ArrayList;
import java.util.List;

import com.xcc.web.annotaion.Action;
import com.xcc.web.annotaion.Control;
import com.xcc.web.interceptor.InterceptorPorxy;
import com.xcc.web.render.IRender;
import com.xcc.web.view.ViewPorxy;

/**
 * com.xsy.web.core.XActionContext.java
 * @author XChao
 */
public class ActionPorxy extends AbstractPorxy {

	private Action action;

	private Control control;

	private IRender render;

	private String defaultViewPath;

	private String currentViewPath;

	private ViewPorxy viewPorxy;

	private ActionParameter parameter;

	private final List<InterceptorPorxy> interceptorsPorxy = new ArrayList<>();

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public ActionPorxy setAction(Action action) {
		this.action = action;
		return this;
	}

	/**
	 * @return the control
	 */
	public Control getControl() {
		return control;
	}

	/**
	 * @param control the control to set
	 */
	public ActionPorxy setControl(Control control) {
		this.control = control;
		return this;
	}

	/**
	 * @return the render
	 */
	public IRender getRender() {
		return render;
	}

	/**
	 * @param render the render to set
	 */
	public ActionPorxy setRender(IRender render) {
		this.render = render;
		return this;
	}

	/**
	 * @return the defaultViewPath
	 */
	public String getDefaultViewPath() {
		return defaultViewPath;
	}

	/**
	 * @param defaultViewPath the defaultViewPath to set
	 */
	public ActionPorxy setDefaultViewPath(String defaultViewPath) {
		this.defaultViewPath = defaultViewPath;
		return this;
	}

	/**
	 * @return the currentViewPath
	 */
	public String getCurrentViewPath() {
		return currentViewPath;
	}

	/**
	 * @param currentViewPath the currentViewPath to set
	 */
	public ActionPorxy setCurrentViewPath(String currentViewPath) {
		this.currentViewPath = currentViewPath;
		return this;
	}

	/**
	 * @return the viewPorxy
	 */
	public ViewPorxy getViewPorxy() {
		return viewPorxy;
	}

	/**
	 * @param viewPorxy the viewPorxy to set
	 */
	public ActionPorxy setViewPorxy(ViewPorxy viewPorxy) {
		this.viewPorxy = viewPorxy;
		return this;
	}

	/**
	 * @return the parameter
	 */
	public ActionParameter getParameter() {
		return parameter;
	}

	/**
	 * @param parameter the parameter to set
	 */
	public ActionPorxy setParameter(ActionParameter parameter) {
		this.parameter = parameter;
		return this;
	}

	/**
	 * @return the interceptorsPorxy
	 */
	public List<InterceptorPorxy> getInterceptorsPorxy() {
		return interceptorsPorxy;
	}

	/**
	 * 添加一个拦截器
	 * @param interceptorPorxy
	 */
	public ActionPorxy addInterceptorPorxy(InterceptorPorxy interceptorPorxy) {
		this.interceptorsPorxy.add(interceptorPorxy);
		return this;
	}
}

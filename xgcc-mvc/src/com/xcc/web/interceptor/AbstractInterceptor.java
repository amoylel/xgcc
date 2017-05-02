/**
 * Created the com.xsy.web.filter.XAbstractFilter.java
 * @created 2016年10月13日 上午10:57:39
 * @version 1.0.0
 */
package com.xcc.web.interceptor;

import com.xcc.web.core.ActionInvocation;

/**
 * com.xsy.web.filter.XAbstractFilter.java
 * @author XChao
 */
public abstract class AbstractInterceptor implements Interceptor {

	/**
	 * 前置拦截器
	 * @param invocation
	 * @return The invoking next nterceptor or invoking action return true, else return false.
	 */
	public abstract boolean before(ActionInvocation invocation);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		if(this.before(invocation)) {
			result = invocation.invoke();
			this.after(invocation);
		}
		return result;
	}

	/**
	 * 后置拦截器, 执行Action 返回时, 执行的代码
	 * @param invocation
	 */
	public abstract void after(ActionInvocation invocation);
}

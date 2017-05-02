/**
 * Created the com.xsy.web.core.XInterceptor.java
 * @created 2016年9月24日 下午7:58:12
 * @version 1.0.0
 */
package com.xcc.web.interceptor;

import com.xcc.web.core.ActionInvocation;

/**
 * 过虑器(拦截器)接口
 * @author XChao
 */
public interface Interceptor {

	/**
	 * 过虑器执行方法
	 * @param invocation
	 * @throws Exception
	 */
	String intercept(ActionInvocation invocation) throws Exception;

}

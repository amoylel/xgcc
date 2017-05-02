/**
 * Created the com.xsy.web.core.XInterceptorContext.java
 * @created 2016年9月26日 下午2:58:35
 * @version 1.0.0
 */
package com.xcc.web.interceptor;

import com.xcc.web.core.AbstractPorxy;
import com.xcc.web.core.DefaultInvocation;

/**
 * com.xsy.web.core.XInterceptorContext.java
 * @author XChao
 */
public class InterceptorPorxy extends AbstractPorxy {
	public String invoke(DefaultInvocation xDefaultActionInvocation) throws Exception {
		return this.execute(xDefaultActionInvocation);
	}
}

/**
 * Created the com.xcc.web.interceptor.PermissInterceptor.java
 * @created 2017年2月3日 上午10:00:53
 * @version 1.0.0
 */
package com.xcc.web.interceptor;

import java.util.List;

import com.xcc.web.annotaion.Action;
import com.xcc.web.core.ActionInvocation;
import com.xcc.web.core.IApplicationContext;
import com.xcc.web.core.XInitContext;
import com.xcc.web.entity.IRole;
import com.xcc.web.entity.IUser;
import com.xcc.web.model.IModel;
import com.xcc.web.preprocessor.MenuPreprocessor;

/**
 * com.xcc.web.interceptor.PermissInterceptor.java
 * @author XChao
 */
public abstract class PermissInterceptor implements Interceptor {
	protected IApplicationContext context = XInitContext.getContext();

	protected MenuPreprocessor getInstance() {
		return (MenuPreprocessor) (context.getPreprocessorPorxy().getPreprocessor());
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Action action = invocation.getAction();
		if(action != null && action.permiss() > 0) {
			IUser usession = (IUser) invocation.getUSession();
			if(usession == null) {
				throw new RuntimeException("对不起! 您没有该功能的操作权限.");
			}
			if(action.menuid() > 0) {
				List<IRole> userRoles = usession.getRoles();
				if(userRoles == null || userRoles.size() == 0) {
					throw new RuntimeException("对不起! 您没有该功能的操作权限.");
				}
				if(this.validate(invocation, usession, action, invocation.getIModel()) == false) {
					throw new RuntimeException("对不起! 您没有该功能的操作权限.");
				}
			}
		}
		return invocation.invoke();
	}

	protected abstract boolean validate(ActionInvocation invocation, IUser usession, Action action, IModel model);
}

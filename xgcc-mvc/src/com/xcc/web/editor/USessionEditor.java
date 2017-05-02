/**
 * Created the com.xsy.web.editor.LoginSessionEditor.java
 * @created 2016年10月24日 下午7:14:15
 * @version 1.0.0
 */
package com.xcc.web.editor;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionParameter;
import com.xcc.web.entity.USession;

/**
 * com.xsy.web.editor.LoginSessionEditor.java
 * @author XChao
 */
public class USessionEditor implements IEditor {

	@Override
	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		Object value = request.getSession().getAttribute(USession.XUSESSION_KEY);
		request.setAttribute(ActionParameter.PARAMETER_KEY + paramName, value);
		return value;
	}

}

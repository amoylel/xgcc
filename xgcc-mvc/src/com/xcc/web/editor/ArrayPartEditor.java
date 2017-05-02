package com.xcc.web.editor;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionParameter;

public class ArrayPartEditor implements IEditor {

	@Override
	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		Object value = ((DefaultRequest) request).getParts(paramName);
		request.setAttribute(ActionParameter.PARAMETER_KEY + paramName, value);
		return value;
	}

}

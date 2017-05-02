package com.xcc.web.editor;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionParameter;

public abstract class ArrayAtomicEditor implements IEditor {

	@Override
	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		Object value = parseText(request.getParameterValues(paramName));
		request.setAttribute(ActionParameter.PARAMETER_KEY + paramName, value);
		return value;
	}

	public abstract Object parseText(String[] text);

}

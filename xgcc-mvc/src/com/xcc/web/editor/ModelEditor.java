package com.xcc.web.editor;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionParameter;
import com.xcc.web.model.IModel;

public class ModelEditor implements IEditor {

	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		Object value = request.getAttribute(IModel.IMODEL_KEY);
		request.setAttribute(ActionParameter.PARAMETER_KEY + paramName, value);
		return value;
	}

}

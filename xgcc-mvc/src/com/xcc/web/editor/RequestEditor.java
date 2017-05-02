package com.xcc.web.editor;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;

public class RequestEditor implements IEditor {
	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		return request;
	}
}

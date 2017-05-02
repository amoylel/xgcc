package com.xcc.web.editor;

import java.lang.reflect.Array;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionParameter;

public class ArrayParamEditor<T> implements IEditor {
	private Class<T> clazz;
	private IEditor editor;

	public ArrayParamEditor(Class<T> clazz, IEditor xiEditor) {
		this.clazz = clazz;
		this.editor = xiEditor;
	}

	@Override
	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) throws Exception {
		Object value = parseText(request.getParameterValues(paramName));
		request.setAttribute(ActionParameter.PARAMETER_KEY + paramName, value);
		return value;
	}

	public Object parseText(String[] text) {
		if(text == null || text.length == 0) {
			return null;
		}
		if(text.length == 1 && StringUtils.isBlank(text[0])) {
			return null;
		}
		Object array = Array.newInstance(this.clazz, text.length);
		for (int i = 0; i < Array.getLength(array); i++) {
			Array.set(array, i, ((AbstractParameterEditor) editor).parseText(text[i]));
		}
		return array;
	}
}

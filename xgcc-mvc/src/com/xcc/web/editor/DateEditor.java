package com.xcc.web.editor;

import com.alibaba.fastjson.util.TypeUtils;

public class DateEditor extends AbstractParameterEditor {

	public Object parseText(String text) {
		try {
			return TypeUtils.castToDate(text);
		} catch (Exception e) {
			throw new ParamBindException(e);
		}
	}
}

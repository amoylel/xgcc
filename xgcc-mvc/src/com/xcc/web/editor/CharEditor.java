package com.xcc.web.editor;

import com.alibaba.fastjson.util.TypeUtils;

public class CharEditor extends AbstractParameterEditor {

	public Object parseText(String text) throws IllegalArgumentException {
		return TypeUtils.castToChar(text);
	}
}

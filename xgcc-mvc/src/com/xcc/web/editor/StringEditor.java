package com.xcc.web.editor;

import com.xcc.utils.type.TypeUtil;

public class StringEditor extends AbstractParameterEditor {

	public Object parseText(String text) {
		return TypeUtil.castString(text);
	}
}

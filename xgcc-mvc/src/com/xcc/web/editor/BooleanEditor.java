package com.xcc.web.editor;

import org.apache.commons.lang.StringUtils;

import com.xcc.utils.type.TypeUtil;

public class BooleanEditor extends AbstractParameterEditor {

	@Override
	public Object parseText(String text) {
		if(StringUtils.isBlank(text)) {
			return null;
		}
		return TypeUtil.castBoolean(text);
	}

}

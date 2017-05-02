package com.xcc.web.editor;

import org.apache.commons.lang.StringUtils;

import com.xcc.utils.type.TypeUtil;

public class FloatEditor extends AbstractNumberEditor {

	@Override
	protected Object getValue(String text) {
		if(StringUtils.isBlank(text)) {
			return null;
		}
		return TypeUtil.castFloat(text);
	}

}

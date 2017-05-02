package com.xcc.web.editor;

import com.xcc.utils.type.TypeUtil;

public class AtomicFloatEditor extends AbstractParameterEditor {
	@Override
	public Object parseText(String text) {
		return TypeUtil.castFloat(text);
	}

}

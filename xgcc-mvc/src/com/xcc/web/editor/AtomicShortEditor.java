package com.xcc.web.editor;

import com.xcc.utils.type.TypeUtil;

/**
 * short(原子类型)编辑器，将字符串装换为short数据
 */
public class AtomicShortEditor extends AbstractParameterEditor {
	@Override
	public Object parseText(String text) {
		return TypeUtil.castShort(text);
	}

}

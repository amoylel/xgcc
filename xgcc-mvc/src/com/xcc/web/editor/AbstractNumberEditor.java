package com.xcc.web.editor;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串到Number类型的转换
 */
public abstract class AbstractNumberEditor extends AbstractParameterEditor {

	@Override
	public Object parseText(String text) {
		if (StringUtils.isNotBlank(text)) {
			return getValue(text);
		}
		return text;
	}

	protected abstract Object getValue(String text);

}

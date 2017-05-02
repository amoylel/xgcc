/**
 * Created the com.xcc.web.core.ActionParameter.java
 * @created 2017年2月24日 下午1:06:51
 * @version 1.0.0
 */
package com.xcc.web.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xcc.web.editor.IEditor;

/**
 * com.xcc.web.core.ActionParameter.java
 * @author XChao
 */
public class ActionParameter {
	public static final String PARAMETER_KEY = "REQUEST_PARAMETER_KEY_";
	private final List<ParameterItem> param = new ArrayList<>();

	private static class ParameterItem {
		private String name;
		private Class<?> type;
		private IEditor edtor;
		private Object value;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the type
		 */
		public Class<?> getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(Class<?> type) {
			this.type = type;
		}

		/**
		 * @return the edtor
		 */
		public IEditor getEdtor() {
			return edtor;
		}

		/**
		 * @param edtor the edtor to set
		 */
		public void setEdtor(IEditor edtor) {
			this.edtor = edtor;
		}

		/**
		 * @return the value
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(Object value) {
			this.value = value;
		}

	}

	public int length() {
		return this.param.size();
	}

	public ActionParameter addParameter(String name, Class<?> type, IEditor edtor, Object value) {
		ParameterItem item = new ParameterItem();
		item.setName(name);
		item.setType(type);
		item.setEdtor(edtor);
		item.setValue(value);
		this.param.add(item);
		return this;
	}

	public ActionParameter setName(int index, String name) {
		this.param.get(index).setName(name);
		return this;
	}

	public ActionParameter setType(int index, Class<?> type) {
		this.param.get(index).setType(type);
		return this;
	}

	public ActionParameter setEditor(int index, IEditor editor) {
		this.param.get(index).setEdtor(editor);
		return this;
	}

	public ActionParameter setValue(int index, Object value) {
		this.param.get(index).setValue(value);
		return this;
	}

	public String getName(int index) {
		return this.param.get(index).getName();
	}

	public Class<?> getType(int index) {
		return this.param.get(index).getType();
	}

	public IEditor getEditor(int index) {
		return this.param.get(index).getEdtor();
	}

	public Object getValue(int index) {
		return this.param.get(index).getValue();
	}

	public Object getValue(String paramName) {
		for (ParameterItem item : this.param) {
			if(StringUtils.equals(item.getName(), paramName)) {
				return item.getValue();
			}
		}
		return null;
	}

	public Object[] getValues() {
		List<Object> values = new ArrayList<>();
		for (ParameterItem item : this.param) {
			values.add(item.getValue());
		}
		return values.toArray();
	}
}

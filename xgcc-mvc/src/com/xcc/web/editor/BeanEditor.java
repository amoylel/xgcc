package com.xcc.web.editor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionParameter;

public class BeanEditor implements IEditor {
	private static final Map<Class<?>, BeanEditorFiled> CLAZZ_FIELD = new HashMap<>();

	private static final class BeanEditorFiled extends ActionParameter {
		final Map<String, Field> fields = new HashMap<>();

		public BeanEditorFiled setField(Field field) {
			this.fields.put(field.getName(), field);
			return this;
		}

		public Field getField(String name) {
			return this.fields.get(name);
		}

		public Field getField(int index) {
			return this.fields.get(this.getName(index));
		}
	}

	private void queryField(Class<?> _clazz, BeanEditorFiled fields) {
		if(_clazz == null || _clazz == Object.class) {
			return;
		}
		for (Field field : _clazz.getDeclaredFields()) {
			if(fields.getField(field.getName()) == null) {
				fields.addParameter(field.getName(), field.getType(),
					EditorBinding.getInstence().getEditor(field.getType()), null);
				fields.setField(field);
			}
		}
		this.queryField(_clazz.getSuperclass(), fields);
	}

	@Override
	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) {
		BeanEditorFiled fields = CLAZZ_FIELD.get(paramType);
		if(fields == null) {
			fields = new BeanEditorFiled();
			this.queryField(paramType, fields);
			CLAZZ_FIELD.put(paramType, fields);
		}
		try {
			Object instence = paramType.newInstance();
			for (int i = 0, length = fields.length(); i < length; i++) {
				Field field = fields.getField(i);
				int mod = field.getModifiers();
				if(!Modifier.isFinal(mod) && !Modifier.isStatic(mod)) {
					IEditor editor = fields.getEditor(i);
					Object value = editor.getParamValue(field.getName(), field.getType(), request, response);
					field.setAccessible(true);
					field.set(instence, value);
				}
			}
			return instence;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

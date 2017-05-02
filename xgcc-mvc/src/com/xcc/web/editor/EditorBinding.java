package com.xcc.web.editor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.xcc.db.IDB;
import com.xcc.web.DefaultRequest;
import com.xcc.web.FilePart;
import com.xcc.web.entity.USession;
import com.xcc.web.model.IModel;

public class EditorBinding {
	private static EditorBinding instence;
	private Map<Class<?>, IEditor> editors = new HashMap<Class<?>, IEditor>();
	private BeanEditor beanEditor = new BeanEditor();

	public synchronized static EditorBinding getInstence() {
		if(instence == null) {
			instence = new EditorBinding();
		}
		return instence;
	}

	private EditorBinding() {
		editors.put(String.class, new StringEditor());
		editors.put(USession.class, new USessionEditor());
		editors.put(java.util.Date.class, new DateEditor());

		editors.put(HttpSession.class, new SessionEditor());
		editors.put(DefaultRequest.class, new RequestEditor());
		editors.put(HttpServletRequest.class, new RequestEditor());
		editors.put(HttpServletResponse.class, new ResponseEditor());

		editors.put(Part.class, new PartEditor());
		editors.put(FilePart.class, new PartEditor());
		editors.put((new Part[0]).getClass(), new ArrayPartEditor());
		editors.put((new FilePart[0]).getClass(), new ArrayPartEditor());

		editors.put(byte.class, new AtomicByteEditor());
		editors.put(long.class, new AtomicLongEditor());
		editors.put(double.class, new AtomicDoubleEditor());
		editors.put(float.class, new AtomicFloatEditor());
		editors.put(short.class, new AtomicShortEditor());
		editors.put(int.class, new AtomicIntegerEditor());
		editors.put(boolean.class, new AtomicBooleanEditor());
		editors.put(char.class, new CharEditor());

		editors.put(Byte.class, new ByteEditor());
		editors.put(Long.class, new LongEditor());
		editors.put(Double.class, new DoubleEditor());
		editors.put(Float.class, new FloatEditor());
		editors.put(Short.class, new ShortEditor());
		editors.put(Integer.class, new IntegerEditor());
		editors.put(Boolean.class, new BooleanEditor());

		editors.put((new String[0]).getClass(), new ArrayParamEditor<String>(String.class, getEditor(String.class)));
		editors.put((new Integer[0]).getClass(),
			new ArrayParamEditor<Integer>(Integer.class, getEditor(Integer.class)));
		editors.put((new Long[0]).getClass(), new ArrayParamEditor<Long>(Long.class, getEditor(Long.class)));
		editors.put((new Double[0]).getClass(), new ArrayParamEditor<Double>(Double.class, getEditor(Double.class)));
		editors.put((new Float[0]).getClass(), new ArrayParamEditor<Float>(Float.class, getEditor(Float.class)));
		editors.put((new Short[0]).getClass(), new ArrayParamEditor<Short>(Short.class, getEditor(Short.class)));
		editors.put((new Byte[0]).getClass(), new ArrayParamEditor<Byte>(Byte.class, getEditor(Byte.class)));
		editors.put((new Boolean[0]).getClass(),
			new ArrayParamEditor<Boolean>(Boolean.class, getEditor(Boolean.class)));

		editors.put((new int[0]).getClass(), new ArrayParamEditor<Integer>(int.class, getEditor(int.class)));
		editors.put((new long[0]).getClass(), new ArrayParamEditor<Long>(long.class, getEditor(long.class)));
		editors.put((new double[0]).getClass(), new ArrayParamEditor<Double>(double.class, getEditor(double.class)));
		editors.put((new float[0]).getClass(), new ArrayParamEditor<Float>(float.class, getEditor(float.class)));
		editors.put((new short[0]).getClass(), new ArrayParamEditor<Short>(short.class, getEditor(short.class)));
		editors.put((new byte[0]).getClass(), new ArrayParamEditor<Byte>(byte.class, getEditor(byte.class)));
		editors.put((new boolean[0]).getClass(),
			new ArrayParamEditor<Boolean>(boolean.class, getEditor(boolean.class)));
		editors.put((new char[0]).getClass(), new ArrayParamEditor<Character>(char.class, getEditor(char.class)));

		editors.put(IDB.class, new DBEditor());
		editors.put(IModel.class, new ModelEditor());
	}

	public IEditor getEditor(Class<?> type) {
		IEditor result = editors.get(type);
		if(result == null) {
			result = beanEditor;
		}
		return result;
	}

	public void bind(Class<?> clazz, IEditor editor) {
		editors.put(clazz, editor);
	}

}

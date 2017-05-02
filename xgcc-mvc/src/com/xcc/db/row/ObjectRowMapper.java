/**
 * Created the com.xsy.db.rowmapper.XObjectRowMapper.java
 * @created 2016年10月10日 下午6:17:37
 * @version 1.0.0
 */
package com.xcc.db.row;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.xcc.db.cell.CellMapping;
import com.xcc.utils.XUtil;
import com.xcc.utils.logger.Logger;

/**
 * com.xsy.db.rowmapper.XObjectRowMapper.java
 * @author XChao
 */
public class ObjectRowMapper<T> extends AbstractRowMapper<T> {
	private static final Map<Class<?>, Map<String, Field>> clazzField = new HashMap<>();

	private Class<T> clazz;
	private Map<String, Field> fields;

	public ObjectRowMapper(Class<T> clazz) {
		this.clazz = clazz;
		this.fields = clazzField.get(this.clazz);
		if(this.fields == null) {
			this.fields = new HashMap<String, Field>();
			clazzField.put(this.clazz, this.fields);
			queryField(this.clazz);
		}
	}

	@Override
	public T getRow(ResultSet resultSet) throws SQLException {
		try {
			T t = clazz.newInstance();
			for (String columnLabel : this.cells.keySet()) {
				Field field = clazzField.get(this.clazz).get(columnLabel);
				if(field == null) {
					String columnLabelByJava = XUtil.toJavaName(columnLabel, false);
					field = clazzField.get(this.clazz).get(columnLabelByJava);
					if(field != null) {
						clazzField.get(this.clazz).put(columnLabel, field);
					}
				}
				if(field != null) {
					field.setAccessible(true);
					try {
						field.set(t, field.getType().cast(this.cells.get(columnLabel).getcell(resultSet, columnLabel)));
					} catch (ClassCastException e) {
						try {
							field.set(t,
								CellMapping.getCellMapper(field.getType().getName()).getcell(resultSet, columnLabel));
						} catch (Exception e1) {
							Logger.warning(e1.getMessage(), e1);
						}
					} catch (Exception e) {
						Logger.warning(e.getMessage(), e);
					}
				}
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void queryField(Class<?> _clazz) {
		if(_clazz == null || _clazz == Object.class) {
			return;
		}
		for (Field field : _clazz.getDeclaredFields()) {
			if(this.fields.get(field.getName()) == null) {
				this.fields.put(field.getName(), field);
			}
		}
		this.queryField(_clazz.getSuperclass());
	}
}

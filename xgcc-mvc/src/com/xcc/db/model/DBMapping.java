/**
 * Created the com.xcc.db.model.DBMapping.java
 * @created 2017年4月26日 下午4:32:21
 * @version 1.0.0
 */
package com.xcc.db.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcc.db.IDB;
import com.xcc.utils.XUtil;

/**
 * com.xcc.db.model.DBMapping.java
 * @author XChao
 */
public class DBMapping {
	private static final Map<Class<?>, DBTables> DB_MAPPING = new HashMap<>();

	/**
	 * 根据java类， 获取该类对应数据库的表信息与字段信息
	 * @param clazz
	 * @return
	 */
	public static DBTables getTables(IDB db, Class<?> clazz) {
		DBTables tables = DB_MAPPING.get(clazz);
		if(tables == null) {
			// 创建数据库与实体的映射信息
			tables = new DBTables().setClazzInfo(clazz);
			// 设置java类信息并扫描java类信息
			scanningClazz(tables, clazz);
			// 扫描数据库字段信息
			scanningField(db, tables, clazz);
			// 读取所有数据库的主键字段
			getPrimaryKeys(db, tables);
			// 将数据库映射信息添加到全局缓存
			DB_MAPPING.put(clazz, tables);
		}
		return tables;
	}

	/**
	 * 扫描类信息与类注解信息， 关键数据库表
	 * @param tables
	 * @param clazz
	 */
	private static void scanningClazz(DBTables tables, Class<?> clazz) {
		DBField dbField = clazz.getAnnotation(DBField.class);
		String tableName = dbField == null ? null : dbField.value();
		if(StringUtils.isBlank(tableName)) {
			tableName = XUtil.toDBName(clazz.getSimpleName());
		}
		tables.setTableName(tableName);
	}

	/**
	 * 扫描字段信息， 关与表关联
	 * @param tables
	 * @param clazz
	 */
	private static void scanningField(IDB db, DBTables tables, Class<?> clazz) {
		// 首先从数据库获取所有字段， 放在Map里面
		JSONArray arrays = DBTables.getColumns(db, tables.getTableName());
		Map<String, Boolean> allColumns = new HashMap<>();
		for (int i = 0, len = arrays.size(); i < len; i++) {
			JSONObject objects = arrays.getJSONObject(i);
			allColumns.put(objects.getString("COLUMN_NAME"), true);
		}
		do {
			for (Field field : clazz.getDeclaredFields()) {
				DBField dbField = field.getAnnotation(DBField.class);
				String columnName = dbField == null ? null : dbField.value();
				if(StringUtils.isBlank(columnName)) {
					columnName = XUtil.toDBName(field.getName());
				}
				if(allColumns.get(columnName) != null && allColumns.get(columnName)) {
					tables.addColumnField(columnName, field);
				}
			}
			clazz = clazz.getSuperclass();
		} while (!(clazz == null || clazz == Object.class));
	}

	/**
	 * 读取所有数据库的主键字段
	 * @param db
	 * @param tables
	 */
	private static void getPrimaryKeys(IDB db, DBTables tables) {
		JSONArray arrays = DBTables.getPrimaryKey(db, tables.getTableName());
		for (int i = 0, len = arrays.size(); i < len; i++) {
			JSONObject objects = arrays.getJSONObject(i);
			tables.setPrimarys(objects.getString("COLUMN_NAME"));
		}
	}
}

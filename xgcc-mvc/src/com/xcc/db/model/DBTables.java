/**
 * Created the com.xcc.db.model.DBTables.java
 * @created 2017年4月27日 上午8:50:47
 * @version 1.0.0
 */
package com.xcc.db.model;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.xcc.db.DBException;
import com.xcc.db.DBFactory;
import com.xcc.db.IDB;

/**
 * com.xcc.db.model.DBTables.java
 * @author XChao
 */
public class DBTables {

	private String tableName;

	private Class<?> clazzInfo;

	private final Map<String, Field> columnField = new HashMap<>();

	private final Map<String, Boolean> primarys = new HashMap<>();

	/**
	 * 获取当前实体对应的数据库表名
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 设置当前实体对应的数据库表名
	 * @param tableName the tableName to set
	 */
	public DBTables setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	/**
	 * 获取当前实体的Class信息
	 * @return the clazzInfo
	 */
	public Class<?> getClazzInfo() {
		return clazzInfo;
	}

	/**
	 * 设置当前实体Class信息
	 * @param clazzInfo the clazzInfo to set
	 */
	public DBTables setClazzInfo(Class<?> clazzInfo) {
		this.clazzInfo = clazzInfo;
		return this;
	}

	/**
	 * 获取当前实体对应表的所有字段与属性
	 * @return the columnField
	 */
	public Map<String, Field> getColumnField() {
		return columnField;
	}

	/**
	 * 获取当前实体对应表的所有主键
	 * @return the primarys
	 */
	public Map<String, Boolean> getPrimarys() {
		return primarys;
	}

	/**
	 * 给当前实体添加一个字段与属性的映射
	 * @param columnName 字段名称
	 * @param field 属性信息
	 * @return
	 */
	public Field addColumnField(String columnName, Field field) {
		return this.columnField.put(columnName, field);
	}

	/**
	 * 获取当前实体设置一个主键字段
	 * @param columnName
	 * @return
	 */
	public Boolean setPrimarys(String columnName) {
		return this.primarys.put(columnName, true);
	}

	/**
	 * 根据字段名称， 获取当前字段对应实体的字段信息
	 * @param columnName 字段名称
	 * @return
	 */
	public Field getField(String columnName) {
		return this.columnField.get(columnName);
	}

	/**
	 * 获取数据库字段查询时的别名
	 * @param columnName
	 * @return
	 */
	public String getLable(String columnName) {
		return this.getField(columnName).getName();
	}

	/**
	 * 判断当前字段是否为该实体对应数据库表的的主键字段
	 * @param columnName 字段名称
	 * @return
	 */
	public boolean columnIsPrimarys(String columnName) {
		Boolean isPirmary = this.primarys.get(columnName);
		return isPirmary != null && isPirmary == true;
	}

	/**
	 * 获取指定表的创建表的SQL语句
	 * @param db
	 * @param tableName
	 * @return
	 */
	public static String getCreateTable(IDB db, String tableName) {
		ResultSet rs = null;
		try {
			rs = db.executeQuery("show create table " + tableName);
			if(rs.next()) {
				return rs.getString(2);
			}
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(rs);
		}
		return "";
	}

	/**
	 * 获取指定表所有的字段
	 * @param db
	 * @param tableName
	 * @return
	 */
	public static JSONArray getColumns(IDB db, String tableName) {
		try {
			return db.analysis(db.getMetaData().getColumns(null, null, tableName, null));
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * 获取指定表所有的主键字段
	 * @param db
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getPrimaryKey(IDB db, String tableName) {
		try {
			return db.analysis(db.getMetaData().getPrimaryKeys(null, null, tableName));
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * 获取指定表所有的外键， 其它表指向该表的所有外键
	 * @param db
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getExportedKeys(IDB db, String tableName) {
		try {
			return db.analysis(db.getMetaData().getExportedKeys(null, null, tableName));
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * 获取所有表指定的外键，该表指向其它表的所有外键
	 * @param db
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getImportedKeys(IDB db, String tableName) {
		try {
			return db.analysis(db.getMetaData().getImportedKeys(null, null, tableName));
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * 获取指定表所有的索引
	 * @param db
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getIndexInfo(IDB db, String tableName) {
		try {
			return db.analysis(db.getMetaData().getIndexInfo(null, null, tableName, false, false));
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
}

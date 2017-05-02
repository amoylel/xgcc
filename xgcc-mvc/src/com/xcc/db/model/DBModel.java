/**
 * Created the com.xcc.db.model.DBModel.java
 * @created 2017年4月26日 下午4:31:38
 * @version 1.0.0
 */
package com.xcc.db.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xcc.db.IDB;
import com.xcc.db.Sql;
import com.xcc.db.paging.Paging;

/**
 * com.xcc.db.model.DBModel.java
 * @author XChao
 */
public abstract class DBModel<T extends DBModel<T>> implements Serializable {
	private static final long serialVersionUID = 7651814940656656356L;

	/**
	 * 保存当前实体信息到数据库，并返回影响条数
	 * @param db
	 * @return
	 */
	public int insert(IDB db) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					columns.append(", ");
				}
				columns.append(columnName);
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
				index++;
			}
			return db.insert(tables.getTableName(), columns.toString(), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 保存当前实体指定字段信息到数据库，并返回影响条数
	 * @param db
	 * @param keys
	 * @return
	 */
	public int insert(IDB db, String... keys) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : keys) {
				if(index > 0) {
					columns.append(", ");
				}
				columns.append(columnName);
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
				index++;
			}
			return db.insert(tables.getTableName(), columns.toString(), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 保存当前实体信息到数据库， 并返回数据库自增长ID
	 * @param db
	 * @return
	 */
	public long insertGen(IDB db) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					columns.append(", ");
				}
				columns.append(columnName);
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
				index++;
			}
			return db.insertGen(tables.getTableName(), columns.toString(), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 保存当前实体信息指定字段到数据库，并返回数据库自增长ID值
	 * @param db
	 * @param keys
	 * @return
	 */
	public long insertGen(IDB db, String... keys) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : keys) {
				if(index > 0) {
					columns.append(", ");
				}
				columns.append(columnName);
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
				index++;
			}
			return db.insertGen(tables.getTableName(), columns.toString(), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 保存当前实体信息到数据库，并返回影响条数<br/>
	 * 如果记录重复时，则覆盖之前的记录
	 * @param db
	 * @return
	 */
	public int replace(IDB db) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					columns.append(", ");
				}
				columns.append(columnName);
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
				index++;
			}
			return db.replace(tables.getTableName(), columns.toString(), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 保存当前实体指定字段信息到数据库，并返回影响条数<br/>
	 * 如果记录重复时，则覆盖之前的记录
	 * @param db
	 * @param keys
	 * @return
	 */
	public int replace(IDB db, String... keys) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : keys) {
				if(index > 0) {
					columns.append(", ");
				}
				columns.append(columnName);
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
				index++;
			}
			return db.replace(tables.getTableName(), columns.toString(), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 保存当前实体信息到数据库， 并返回数据库自增长ID<br/>
	 * 如果记录重复时，则覆盖之前的记录
	 * @param db
	 * @return
	 */
	public long replaceGen(IDB db) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					columns.append(", ");
				}
				columns.append(columnName);
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
				index++;
			}
			return db.replaceGen(tables.getTableName(), columns.toString(), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 保存当前实体信息指定字段到数据库，并返回数据库自增长ID值<br/>
	 * 如果记录重复时，则覆盖之前的记录
	 * @param db
	 * @param keys
	 * @return
	 */
	public long replaceGen(IDB db, String... keys) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : keys) {
				if(index > 0) {
					columns.append(", ");
				}
				columns.append(columnName);
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
				index++;
			}
			return db.replaceGen(tables.getTableName(), columns.toString(), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据ID，将当前记录同步到数据库<br/>
	 * 同步时，条件字段不会被修改
	 * @param db
	 * @return
	 */
	public int update(IDB db) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			List<String> requires = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : tables.getColumnField().keySet()) {
				if(tables.columnIsPrimarys(columnName)) {
					requires.add(columnName);
				} else {
					if(index > 0) {
						columns.append(", ");
					}
					columns.append(columnName);
					Field field = tables.getField(columnName);
					field.setAccessible(true);
					params.add(field.get(this));
					index++;
				}
			}
			for (String columnName : requires) {
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				params.add(field.get(this));
			}
			return db.update(tables.getTableName(), columns.toString(), StringUtils.join(requires, ", "),
				params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据ID，将当前记录的指定字段同步到数据库<br/>
	 * 同步时，条件字段不会被修改
	 * @param db
	 * @return
	 */
	public int update(IDB db, String... keys) {
		try {
			int index = 0;
			StringBuilder columns = new StringBuilder();
			List<Object> params = new ArrayList<>();
			List<String> requires = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : keys) {
				if(!tables.columnIsPrimarys(columnName)) {
					if(index > 0) {
						columns.append(", ");
					}
					columns.append(columnName);
					Field field = tables.getField(columnName);
					field.setAccessible(true);
					params.add(field.get(this));
					index++;
				}
			}
			for (String columnName : tables.getColumnField().keySet()) {
				if(tables.columnIsPrimarys(columnName)) {
					requires.add(columnName);
					Field field = tables.getField(columnName);
					field.setAccessible(true);
					params.add(field.get(this));
				}
			}
			return db.update(tables.getTableName(), columns.toString(), StringUtils.join(requires, ", "),
				params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据ID删除数据库当前实体记录
	 * @param db
	 * @return
	 */
	public int delete(IDB db) {
		try {
			List<Object> params = new ArrayList<>();
			List<String> requires = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : tables.getColumnField().keySet()) {
				if(tables.columnIsPrimarys(columnName)) {
					requires.add(columnName);
					Field field = tables.getField(columnName);
					field.setAccessible(true);
					params.add(field.get(this));
				}
			}
			return db.delete(tables.getTableName(), StringUtils.join(requires, ", "), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据指定条件字段，删除数据库当前实体记录
	 * @param db
	 * @param requires
	 * @return
	 */
	public int delete(IDB db, String... requires) {
		try {
			List<Object> params = new ArrayList<>();
			DBTables tables = DBMapping.getTables(db, this.getClass());
			for (String columnName : requires) {
				if(tables.columnIsPrimarys(columnName)) {
					Field field = tables.getField(columnName);
					field.setAccessible(true);
					params.add(field.get(this));
				}
			}
			return db.delete(tables.getTableName(), StringUtils.join(requires, ", "), params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据ID查询当前实体信息
	 * @param db
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T queryById(IDB db) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(tables.columnIsPrimarys(columnName)) {
					Field field = tables.getField(columnName);
					field.setAccessible(true);
					sql.append(" and ").append(columnName).append(" = ? ", field.get(this));
				}
			}
			return (T) db.queryOne(this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据指定字段条件查询当前实体信息
	 * @param db
	 * @param requires
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T queryOne(IDB db) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			return (T) db.queryOne(this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据指定字段条件查询当前实体信息
	 * @param db
	 * @param requires
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T queryOne(IDB db, String... requires) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			for (String columnName : requires) {
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				sql.append(" and ").append(columnName).append(" = ? ", field.get(this));
			}
			return (T) db.queryOne(this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据Sql中添加的条件查询实体信息
	 * @param db
	 * @param requires 在sql中添加查询条件 如： sql.append(" and s_id = ?", sid);
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T queryOne(IDB db, DBRequires requires) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			requires.requires(sql);
			return (T) db.queryOne(this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据指定条件字段查询当前实体列表
	 * @param db
	 * @param requires
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(IDB db) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			return (List<T>) db.query(this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据指定条件字段查询当前实体列表
	 * @param db
	 * @param requires
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(IDB db, String... requires) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			for (String columnName : requires) {
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				sql.append(" and ").append(columnName).append(" = ? ", field.get(this));
			}
			return (List<T>) db.query(this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据Sql添加的条件查询实体列表
	 * @param db
	 * @param requires 在sql中添加查询条件 如： sql.append(" and s_id = ?", sid);
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(IDB db, DBRequires requires) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			requires.requires(sql);
			return (List<T>) db.query(this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据指定条件字段分页查询当前实体列表
	 * @param paging
	 * @param db
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(Paging paging, IDB db) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			return (List<T>) db.query(paging, this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据指定条件字段分页查询当前实体列表
	 * @param paging
	 * @param db
	 * @param requires
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(Paging paging, IDB db, String... requires) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			for (String columnName : requires) {
				Field field = tables.getField(columnName);
				field.setAccessible(true);
				sql.append(" and ").append(columnName).append(" = ? ", field.get(this));
			}
			return (List<T>) db.query(paging, this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据Sql添加的条件分页查询实体列表
	 * @param paging
	 * @param db
	 * @param requires 在sql中添加查询条件 如： sql.append(" and s_id = ?", sid);
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(Paging paging, IDB db, DBRequires requires) {
		try {
			DBTables tables = DBMapping.getTables(db, this.getClass());
			int index = 0;
			Sql sql = new Sql("select ");
			for (String columnName : tables.getColumnField().keySet()) {
				if(index > 0) {
					sql.append(", ");
				}
				sql.append(columnName);
				sql.append(" as ");
				sql.append(tables.getLable(columnName));
				index++;
			}
			sql.append(" from ").append(tables.getTableName()).append(" where 1 = 1 ");
			requires.requires(sql);
			return (List<T>) db.query(paging, this.getClass(), sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

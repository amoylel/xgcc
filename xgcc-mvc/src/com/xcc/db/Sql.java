package com.xcc.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xcc.db.cell.CellMapper;

/**
 * sql 拼装类
 * @author PangChao
 */
public class Sql {
	private StringBuilder sqls = new StringBuilder();
	private List<Object> params = new ArrayList<>();
	private Map<String, CellMapper<?>> cellMapper = new HashMap<String, CellMapper<?>>();

	public Sql() {
	}

	public static Sql createQuery() {
		return new Sql("select ");
	}

	public static Sql createQuery(String... keys) {
		return new Sql("select ").appendKeys(keys);
	}

	/**
	 * 创建sql语句
	 * @param sql sql语句字符串
	 * @param params sql 语句中带?号参数的值列表
	 */
	public Sql(String sql, Object... params) {
		this.append(sql, params);
	}

	/**
	 * 获取该sql的完整sql字符串形式
	 */
	public String getSQL() {
		return this.sqls.toString();
	}

	/**
	 * 获取该sql中所有带?号参数的值列表数组
	 */
	public Object[] getParams() {
		return this.params.toArray();
	}

	public Map<String, CellMapper<?>> getCellMapper() {
		return this.cellMapper;
	}

	public Sql append(String sql) {
		this.sqls.append(sql);
		return this;
	}

	/**
	 * 追加sql 语句
	 * @param sql sql语句字符串
	 * @param params 语句中带?号参数的值列表
	 */
	public Sql append(String sql, Object... params) {
		this.sqls.append(sql);
		if(params != null) {
			for (Object param : params) {
				this.params.add(param);
			}
		}
		return this;
	}

	/**
	 * 向Sql中追加参数
	 * @param params
	 * @return
	 */
	public Sql appendParams(Object... params) {
		if(params != null) {
			for (Object param : params) {
				this.params.add(param);
			}
		}
		return this;
	}

	/**
	 * 向Sql中追加字段<br/>
	 * 该方法只能追加 select， insert， replace, update的字段， 不能追加where条件 <br/>
	 * 该方法不能同时追加参数
	 * @param keys
	 * @return
	 */
	public Sql appendKeys(String... keys) {
		if(keys != null) {
			this.sqls.append(StringUtils.join(keys, ", "));
		}
		return this;
	}

	/**
	 * 向Sql追加 from tableName 语句
	 * @param tableName
	 * @return
	 */
	public Sql appendTable(String tableName) {
		return this.append(" from ").append(tableName).append(" ");
	}

	/**
	 * 追加join联合表语句，<br/>
	 * 该方法不能同时追加参数
	 * @param tableName 表名
	 * @param column1 连接表前表字段
	 * @param column2 连接表当前表字段
	 * @return
	 */
	public Sql appendJoin(String tableName, String column1, String column2) {
		this.sqls.append(" join ").append(tableName).append(" on ");
		this.sqls.append(column1).append(" = ").append(column2).append(" ");
		return this;
	}

	/**
	 * 追加left join联合表语句，<br/>
	 * 该方法不能同时追加参数
	 * @param tableName 表名
	 * @param column1 连接表前表字段
	 * @param column2 连接表当前表字段
	 * @return
	 */
	public Sql appendLeftJoin(String tableName, String column1, String column2) {
		this.sqls.append(" left join ").append(tableName).append(" on ");
		this.sqls.append(column1).append(" = ").append(column2).append(" ");
		return this;
	}

	/**
	 * 追加right join联合表语句，<br/>
	 * 该方法不能同时追加参数
	 * @param tableName 表名
	 * @param column1 连接表前表字段
	 * @param column2 连接表当前表字段
	 * @return
	 */
	public Sql appendRigthtJoin(String tableName, String column1, String column2) {
		this.sqls.append(" right join ").append(tableName).append(" on ");
		this.sqls.append(column1).append(" = ").append(column2).append(" ");
		return this;
	}

	/**
	 * 追加cross join联合表语句，<br/>
	 * 该方法不能同时追加参数
	 * @param tableName 表名
	 * @param column1 连接表前表字段
	 * @param column2 连接表当前表字段
	 * @return
	 */
	public Sql appendCrossJoin(String tableName, String column1, String column2) {
		this.sqls.append(" cross join ").append(tableName).append(" ");
		return this;
	}

	/**
	 * 向Sql中追加 where 1 = 1 语句块
	 * @return
	 */
	public Sql appendWhereTrue() {
		return this.append(" where 1 = 1 ");
	}

	/**
	 * 向Sql中追加 where 1 = 2 语句块
	 * @return
	 */
	public Sql appendWhereFalse() {
		return this.append(" where 1 = 2 ");
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInAnd(String key, String param) {
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接 <br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInAnd(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInAnd(String key, int param) {
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接 <br/>
	 * 如果传入参数 小于0 时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInAnd(String key, int param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInAnd(String key, long param) {
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接 <br/>
	 * 如果传入参数 小于0 时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInAnd(String key, long param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInAnd(String key, float param) {
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接 <br/>
	 * 如果传入参数 小于0 时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInAnd(String key, float param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInAnd(String key, double param) {
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接 <br/>
	 * 如果传入参数 小于0 时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInAnd(String key, double param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInAnd(String key, Date param) {
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 and 连接 <br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInAnd(String key, Date param) {
		if(param == null) {
			return this;
		}
		return this.append(" and ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInOr(String key, String param) {
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接 <br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInOr(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInOr(String key, int param) {
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接 <br/>
	 * 如果传入参数小于0 时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInOr(String key, int param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInOr(String key, long param) {
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接 <br/>
	 * 如果传入参数小于0 时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInOr(String key, long param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInOr(String key, float param) {
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接 <br/>
	 * 如果传入参数小于0 时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInOr(String key, float param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInOr(String key, double param) {
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接 <br/>
	 * 如果传入参数小于0 时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInOr(String key, double param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereEqInOr(String key, Date param) {
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 等于 条件， 用 or 连接 <br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereEqInOr(String key, Date param) {
		if(param == null) {
			return this;
		}
		return this.append(" or ").append(key).append(" = ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInAnd(String key, String param) {
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接<br />
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInAnd(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInAnd(String key, int param) {
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接<br />
	 * 如果传入参数小于0时，不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInAnd(String key, int param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInAnd(String key, long param) {
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接<br />
	 * 如果传入参数小于0时，不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInAnd(String key, long param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInAnd(String key, float param) {
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接<br />
	 * 如果传入参数小于0时，不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInAnd(String key, float param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInAnd(String key, double param) {
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接<br />
	 * 如果传入参数小于0时，不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInAnd(String key, double param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInAnd(String key, Date param) {
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 and 连接<br />
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInAnd(String key, Date param) {
		if(param == null) {
			return this;
		}
		return this.append(" and ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInOr(String key, String param) {
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInOr(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return null;
		}
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInOr(String key, int param) {
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInOr(String key, int param) {
		if(param < 0) {
			return null;
		}
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInOr(String key, long param) {
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInOr(String key, long param) {
		if(param < 0) {
			return null;
		}
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInOr(String key, float param) {
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInOr(String key, float param) {
		if(param < 0) {
			return null;
		}
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInOr(String key, double param) {
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInOr(String key, double param) {
		if(param < 0) {
			return null;
		}
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtInOr(String key, Date param) {
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtInOr(String key, Date param) {
		if(param == null) {
			return null;
		}
		return this.append(" or ").append(key).append(" > ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInAnd(String key, String param) {
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInAnd(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return null;
		}
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInAnd(String key, int param) {
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInAnd(String key, int param) {
		if(param < 0) {
			return null;
		}
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInAnd(String key, long param) {
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInAnd(String key, long param) {
		if(param < 0) {
			return null;
		}
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInAnd(String key, float param) {
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInAnd(String key, float param) {
		if(param < 0) {
			return null;
		}
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInAnd(String key, double param) {
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInAnd(String key, double param) {
		if(param < 0) {
			return null;
		}
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInAnd(String key, Date param) {
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInAnd(String key, Date param) {
		if(param == null) {
			return null;
		}
		return this.append(" and ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInOr(String key, String param) {
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInOr(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInOr(String key, int param) {
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInOr(String key, int param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInOr(String key, long param) {
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInOr(String key, long param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInOr(String key, float param) {
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInOr(String key, float param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInOr(String key, double param) {
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInOr(String key, double param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereGtEqInOr(String key, Date param) {
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 大于等于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereGtEqInOr(String key, Date param) {
		if(param == null) {
			return this;
		}
		return this.append(" or ").append(key).append(" >= ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInAnd(String key, String param) {
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInAnd(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInAnd(String key, int param) {
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接<br/>
	 * 如梦传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInAnd(String key, int param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInAnd(String key, long param) {
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接<br/>
	 * 如梦传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInAnd(String key, long param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInAnd(String key, float param) {
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接<br/>
	 * 如梦传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInAnd(String key, float param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInAnd(String key, double param) {
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接<br/>
	 * 如梦传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInAnd(String key, double param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInAnd(String key, Date param) {
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 and 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInAnd(String key, Date param) {
		if(param == null) {
			return this;
		}
		return this.append(" and ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInOr(String key, String param) {
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInOr(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInOr(String key, int param) {
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInOr(String key, int param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInOr(String key, long param) {
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInOr(String key, long param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInOr(String key, float param) {
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInOr(String key, float param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInOr(String key, double param) {
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInOr(String key, double param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtInOr(String key, Date param) {
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtInOr(String key, Date param) {
		if(param == null) {
			return this;
		}
		return this.append(" or ").append(key).append(" < ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInAnd(String key, String param) {
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInAnd(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInAnd(String key, int param) {
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInAnd(String key, int param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInAnd(String key, long param) {
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInAnd(String key, long param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInAnd(String key, float param) {
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInAnd(String key, float param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInAnd(String key, double param) {
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInAnd(String key, double param) {
		if(param < 0) {
			return this;
		}
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInAnd(String key, Date param) {
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInAnd(String key, Date param) {
		if(param == null) {
			return this;
		}
		return this.append(" and ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInOr(String key, String param) {
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInOr(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInOr(String key, int param) {
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInOr(String key, int param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInOr(String key, long param) {
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInOr(String key, long param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInOr(String key, float param) {
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInOr(String key, float param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInOr(String key, double param) {
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接<br/>
	 * 如果传入参数小于0时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInOr(String key, double param) {
		if(param < 0) {
			return this;
		}
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLtEqInOr(String key, Date param) {
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 and 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLtEqInOr(String key, Date param) {
		if(param == null) {
			return this;
		}
		return this.append(" or ").append(key).append(" <= ? ", param);
	}

	/**
	 * 追加Sql where like 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLikeInAnd(String key, String param) {
		return this.append(" and ").append(key).append(" like ? ", param);
	}

	/**
	 * 追加Sql where like 条件， 用 and 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLikeInAnd(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" and ").append(key).append(" like ? ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereLikeInOr(String key, String param) {
		return this.append(" or ").append(key).append(" like ? ", param);
	}

	/**
	 * 追加Sql where like 条件， 用 or 连接<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereLikeInOr(String key, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" or ").append(key).append(" like ? ", param);
	}

	/**
	 * 追加Sql where Match 条件， 用 and 连接<br/>
	 * 全文索引搜索条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereMatchInAnd(String keys, String param) {
		return this.append(" and MATCH(").append(keys).append(") AGAINST(? IN BOOLEAN MODE) ", param);
	}

	/**
	 * 追加Sql where Match 条件， 用 and 连接<br/>
	 * 全文索引搜索条件<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereMatchInAnd(String keys, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" and MATCH(").append(keys).append(") AGAINST(? IN BOOLEAN MODE) ", param);
	}

	/**
	 * 追加Sql where 小于等于 条件， 用 or 连接<br/>
	 * 全文索引搜索条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql appendWhereMatchInOr(String keys, String param) {
		return this.append(" or MATCH(").append(keys).append(") AGAINST(? IN BOOLEAN MODE) ", param);
	}

	/**
	 * 追加Sql where Match 条件， 用 or 连接<br/>
	 * 全文索引搜索条件<br/>
	 * 如果传入参数为空时， 不添加sql条件
	 * @param key
	 * @param params
	 * @return
	 */
	public Sql valiAppendWhereMatchInOr(String keys, String param) {
		if(StringUtils.isBlank(param)) {
			return this;
		}
		return this.append(" or MATCH(").append(keys).append(") AGAINST(? IN BOOLEAN MODE) ", param);
	}

	/**
	 * 向SQL中追加group by语句
	 * @param keys
	 * @return
	 */
	public Sql appendGroupBy(String... keys) {
		if(keys != null) {
			this.sqls.append(" group by ").append(StringUtils.join(keys, ", "));
		}
		return this;
	}

	/**
	 * 向SQL中追加group by语句
	 * @param keys
	 * @return
	 */
	public Sql appendOrderBy(String... keys) {
		if(keys != null) {
			this.sqls.append(" order by ").append(StringUtils.join(keys, ", "));
		}
		return this;
	}

	/**
	 * 添加一个cellMapper实现
	 * @param key
	 * @param cellMapper
	 * @return
	 */
	public Sql addCellMapper(String key, CellMapper<?> cellMapper) {
		this.cellMapper.put(key, cellMapper);
		return this;
	}

	/**
	 * 将传入字段用逗号连接起来
	 * @param kyes
	 * @return
	 */
	public static String join(String... kyes) {
		return StringUtils.join(kyes, ", ");
	}
}

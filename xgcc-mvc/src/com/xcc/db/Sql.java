package com.xcc.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (params != null) {
			for (Object param : params) {
				this.params.add(param);
			}
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
}

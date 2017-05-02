/**
 * Created the com.xsy.db.impls.XDefaultDB.java
 * @created 2016年9月22日 下午3:28:42
 * @version 1.0.0
 */
package com.xcc.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcc.db.cell.CellMapper;
import com.xcc.db.paging.Paging;
import com.xcc.db.row.JSONObjectRowMapper;
import com.xcc.db.row.ObjectRowMapper;
import com.xcc.db.row.RowMapper;
import com.xcc.utils.logger.Logger;

/**
 * 数据库连接实现
 * @author XChao
 */
public final class DefaultDB implements IDB {
	// insert SQL 与replace SQL 传入字段时, 将其转换成value位置中参数的对应关系
	private static final Map<String, String> insert_values = new HashMap<>();
	// update SQL 传入字段时, 将其转换成 set 位置中参数的对应关系
	private static final Map<String, String> update_values = new HashMap<>();
	// update SQL 与 delete SQL 传入字段时,将其生成 where 位置中的参数对应关系, 默认用 and连接
	private static final Map<String, String> wheres_values = new HashMap<>();

	private Connection connection;
	// 事务级别
	private int transLevel = 0;
	// 事务应用计数，支持嵌套事务
	private int transRefCount = 0;

	public DefaultDB(Connection conn) {
		this.connection = conn;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public DatabaseMetaData getMetaData() {
		try {
			return this.getConnection().getMetaData();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public void fullPreparedStatement(PreparedStatement statement, Object... params) throws SQLException {
		if(params == null || params.length == 0) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			statement.setObject((i + 1), params[i]);
		}
	}

	public void batch(String sql, DBProcessing processing) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.getConnection().prepareStatement(sql);
			processing.processing(this, preparedStatement);
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(preparedStatement);
		}
	}

	public int execute(String sql, Object... params) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.getConnection().prepareStatement(sql);
			this.fullPreparedStatement(preparedStatement, params);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(preparedStatement);
		}
	}

	public long executeGen(String sql, Object... params) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			this.fullPreparedStatement(preparedStatement, params);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				return resultSet.getLong(1);
			}
			return 0;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public int execute(Sql sql) {
		return this.execute(sql.getSQL(), sql.getParams());
	}

	public long executeGen(Sql sql) {
		return this.executeGen(sql.getSQL(), sql.getParams());
	}

	public int insert(String tname, String keys, Object... params) {
		Sql sql = new Sql("insert into ").append(tname).append("(").append(keys).append(") values(");
		return this.execute(sql.append(getInsertParam(keys)).append(")", params));
	}

	public long insertGen(String tname, String keys, Object... params) {
		Sql sql = new Sql("insert into ").append(tname).append("(").append(keys).append(") values(");
		return this.executeGen(sql.append(getInsertParam(keys)).append(")", params));
	}

	public int replace(String tname, String keys, Object... params) {
		Sql sql = new Sql("replace into ").append(tname).append("(").append(keys).append(") values(");
		return this.execute(sql.append(getInsertParam(keys)).append(")", params));
	}

	public long replaceGen(String tname, String keys, Object... params) {
		Sql sql = new Sql("replace into ").append(tname).append("(").append(keys).append(") values(");
		return this.executeGen(sql.append(getInsertParam(keys)).append(")", params));
	}

	public int update(String tname, String keys, String wheres, Object... params) {
		Sql sql = new Sql("update ").append(tname).append(" set ");
		sql.append(getUpdateParam(keys));
		if(StringUtils.isNotBlank(wheres)) {
			sql.append(" where ").append(getWheresParam(wheres));
		}
		return this.execute(sql.append(" ", params));
	}

	public int delete(String tname, String wheres, Object... params) {
		Sql sql = new Sql("delete from ").append(tname);
		if(StringUtils.isNotBlank(wheres)) {
			sql.append(" where ").append(getWheresParam(wheres));
		}
		if(params != null && params.length > 0) {
			sql.append(" ", params);
		}
		return this.execute(sql);
	}

	public ResultSet executeQuery(String sql, Object... params) {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = this.getConnection().prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
			this.fullPreparedStatement(prepareStatement, params);
			return prepareStatement.executeQuery();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public ResultSet executeQuery(Sql sql) {
		return this.executeQuery(sql.getSQL(), sql.getParams());
	}

	public ResultSet executeQuery(Paging paging, String sql, Object... params) {
		PreparedStatement preparedStatement = null;
		try {
			paging.setTotal(this.queryInt(paging.getMaxSql(sql), params));
			preparedStatement = this.getConnection().prepareStatement(paging.paging(sql));
			this.fullPreparedStatement(preparedStatement, params);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public ResultSet executeQuery(Paging paging, Sql sql) {
		return this.executeQuery(paging, sql.getSQL(), sql.getParams());
	}

	public JSONArray analysis(ResultSet resultSet) {
		JSONArray result = new JSONArray();
		try {
			RowMapper<JSONObject> mapper = new JSONObjectRowMapper();
			this.getMapperByResultSet(resultSet, mapper);
			while (resultSet.next()) {
				result.add(mapper.getRow(resultSet));
			}
			return result;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public JSONArray analysis(ResultSet resultSet, Map<String, CellMapper<?>> cells) {
		JSONArray result = new JSONArray();
		try {
			RowMapper<JSONObject> mapper = new JSONObjectRowMapper();
			this.getMapperByResultSet(resultSet, mapper, cells);
			while (resultSet.next()) {
				result.add(mapper.getRow(resultSet));
			}
			return result;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public <T> List<T> analysis(ResultSet resultSet, Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		try {
			RowMapper<T> mapper = new ObjectRowMapper<T>(clazz);
			this.getMapperByResultSet(resultSet, mapper);
			while (resultSet.next()) {
				result.add(mapper.getRow(resultSet));
			}
			return result;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public <T> List<T> analysis(ResultSet resultSet, Class<T> clazz, Map<String, CellMapper<?>> cells) {
		List<T> result = new ArrayList<T>();
		try {
			RowMapper<T> mapper = new ObjectRowMapper<T>(clazz);
			this.getMapperByResultSet(resultSet, mapper, cells);
			while (resultSet.next()) {
				result.add(mapper.getRow(resultSet));
			}
			return result;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public <T> List<T> analysis(ResultSet resultSet, RowMapper<T> mapper) {
		List<T> result = new ArrayList<T>();
		try {
			this.getMapperByResultSet(resultSet, mapper);
			while (resultSet.next()) {
				result.add(mapper.getRow(resultSet));
			}
			return result;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public JSONObject analysisOne(ResultSet resultSet) {
		try {
			RowMapper<JSONObject> mapper = new JSONObjectRowMapper();
			this.getMapperByResultSet(resultSet, mapper);
			if(resultSet.next()) {
				return mapper.getRow(resultSet);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public JSONObject analysisOne(ResultSet resultSet, Map<String, CellMapper<?>> cells) {
		try {
			RowMapper<JSONObject> mapper = new JSONObjectRowMapper();
			this.getMapperByResultSet(resultSet, mapper, cells);
			if(resultSet.next()) {
				return mapper.getRow(resultSet);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public <T> T analysisOne(ResultSet resultSet, Class<T> clazz) {
		try {
			RowMapper<T> mapper = new ObjectRowMapper<T>(clazz);
			this.getMapperByResultSet(resultSet, mapper);
			if(resultSet.next()) {
				return mapper.getRow(resultSet);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public <T> T analysisOne(ResultSet resultSet, Class<T> clazz, Map<String, CellMapper<?>> cells) {
		try {
			RowMapper<T> mapper = new ObjectRowMapper<T>(clazz);
			this.getMapperByResultSet(resultSet, mapper, cells);
			if(resultSet.next()) {
				return mapper.getRow(resultSet);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public <T> T analysisOne(ResultSet resultSet, RowMapper<T> mapper) {
		try {
			this.getMapperByResultSet(resultSet, mapper);
			if(resultSet.next()) {
				return mapper.getRow(resultSet);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public JSONArray query(Sql sql) {
		return this.analysis(this.executeQuery(sql), sql.getCellMapper());
	}

	public JSONArray query(String sql, Object... params) {
		return this.query(new Sql(sql, params));
	}

	public <T> List<T> query(Class<T> clazz, Sql sql) {
		return this.analysis(this.executeQuery(sql), clazz, sql.getCellMapper());
	}

	public <T> List<T> query(Class<T> clazz, String sql, Object... params) {
		return this.query(clazz, new Sql(sql, params));
	}

	public <T> List<T> query(RowMapper<T> mapper, Sql sql) {
		return this.analysis(this.executeQuery(sql), mapper);
	}

	public <T> List<T> query(RowMapper<T> mapper, String sql, Object... params) {
		return this.query(mapper, new Sql(sql, params));
	}

	public JSONArray query(Paging paging, Sql sql) {
		return this.analysis(this.executeQuery(paging, sql), sql.getCellMapper());
	}

	public JSONArray query(Paging paging, String sql, Object... params) {
		return this.query(paging, new Sql(sql, params));
	}

	public <T> List<T> query(Paging paging, Class<T> clazz, Sql sql) {
		return this.analysis(this.executeQuery(paging, sql), clazz, sql.getCellMapper());
	}

	public <T> List<T> query(Paging paging, Class<T> clazz, String sql, Object... params) {
		return this.query(paging, clazz, new Sql(sql, params));
	}

	public <T> List<T> query(Paging paging, RowMapper<T> mapper, Sql sql) {
		return this.analysis(this.executeQuery(paging, sql), mapper);
	}

	public <T> List<T> query(Paging paging, RowMapper<T> mapper, String sql, Object... params) {
		return this.query(paging, mapper, new Sql(sql, params));
	}

	public JSONObject queryOne(Sql sql) {
		return this.analysisOne(this.executeQuery(sql), sql.getCellMapper());
	}

	public JSONObject queryOne(String sql, Object... params) {
		return this.queryOne(new Sql(sql, params));
	}

	public <T> T queryOne(Class<T> clazz, Sql sql) {
		return this.analysisOne(this.executeQuery(sql), clazz, sql.getCellMapper());
	}

	public <T> T queryOne(Class<T> clazz, String sql, Object... params) {
		return this.queryOne(clazz, new Sql(sql, params));
	}

	public <T> T queryOne(RowMapper<T> mapper, Sql sql) {
		return this.analysisOne(this.executeQuery(sql), mapper);
	}

	public <T> T queryOne(RowMapper<T> mapper, String sql, Object... params) {
		return this.queryOne(mapper, new Sql(sql, params));
	}

	public String queryString(Sql sql) {
		return this.queryString(sql.getSQL(), sql.getParams());
	}

	public String queryString(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getString(1);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public long queryLong(Sql sql) {
		return this.queryLong(sql.getSQL(), sql.getParams());
	}

	public long queryLong(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getLong(1);
			}
			return 0L;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public int queryInt(Sql sql) {
		return this.queryInt(sql.getSQL(), sql.getParams());
	}

	public int queryInt(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public short queryShort(Sql sql) {
		return this.queryShort(sql.getSQL(), sql.getParams());
	}

	public short queryShort(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getShort(1);
			}
			return (short) 0;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public byte queryByte(Sql sql) {
		return this.queryByte(sql.getSQL(), sql.getParams());
	}

	public byte queryByte(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getByte(1);
			}
			return (byte) 0;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public double queryDouble(Sql sql) {
		return this.queryDouble(sql.getSQL(), sql.getParams());
	}

	public double queryDouble(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getDouble(1);
			}
			return 0.0D;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public float queryFloat(Sql sql) {
		return this.queryFloat(sql.getSQL(), sql.getParams());
	}

	public float queryFloat(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getFloat(1);
			}
			return 0.0F;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public Date queryDate(Sql sql) {
		return this.queryDate(sql.getSQL(), sql.getParams());
	}

	public Date queryDate(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getTimestamp(1);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public boolean queryBoolean(Sql sql) {
		return this.queryBoolean(sql.getSQL(), sql.getParams());
	}

	public boolean queryBoolean(String sql, Object... params) {
		ResultSet resultSet = null;
		try {
			resultSet = this.executeQuery(sql, params);
			if(resultSet.next()) {
				return resultSet.getBoolean(1);
			}
			return false;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBFactory.close(resultSet);
		}
	}

	public boolean transaction(DBTransaction transaction) {
		return this.transaction(Connection.TRANSACTION_REPEATABLE_READ, transaction);
	}

	public boolean transaction(int transLevel, DBTransaction transaction) {
		if(this.transRefCount == 0) { // 第一次开启事务
			try { // 开启事务阶段
				connection.setAutoCommit(false);
				this.transLevel = transLevel;
				connection.setTransactionIsolation(this.transLevel);
			} catch (SQLException e) {
				throw new DBException(e);
			}
			// 嵌套事务中的事务级别不能高于初始级别
		} else if(transLevel > this.transLevel) {
			throw new DBException("Nest transnationt level is high." + this.transLevel + "<" + transLevel);
		}
		transRefCount++;
		// 事务执行阶段
		Savepoint savepoint = null;
		boolean commit = false;
		try {
			savepoint = connection.setSavepoint();
			commit = transaction.transaction(this);
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			// 事务提交 或者回滚阶段
			this.transRefCount--;
			if(commit == false && savepoint != null) { // 回滚事务到设置的回滚点
				try {
					connection.rollback(savepoint);
				} catch (SQLException e) {
					Logger.severe("Transaction rollback error.");
				}
			}
			if(this.transRefCount == 0) { // 如果事务计数为0 时, 则表示所有事务提交或者回滚完成
				boolean roolback = true;
				try {
					this.transLevel = 0;
					if(commit == true) {
						this.getConnection().commit();
						roolback = false;
						connection.setAutoCommit(true);
					}
				} catch (SQLException e) {
					throw new DBException(e);
				} finally {
					if(roolback) {
						try {
							commit = false;
							connection.rollback();
							connection.setAutoCommit(true);
						} catch (SQLException e) {
							throw new DBException(e);
						}
					}
				}
			}
		}
		return commit;
	}

	/**
	 * 根据 ResultSet, Sql 初始化RowMapper数据
	 * @param resultSet
	 * @param mapper
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	private <T> RowMapper<T> getMapperByResultSet(ResultSet resultSet, RowMapper<T> mapper) throws SQLException {
		ResultSetMetaData rsmd = resultSet.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			mapper.putCell(rsmd.getColumnLabel(i), rsmd.getColumnClassName(i));
		}
		return mapper;
	}

	/**
	 * 根据 ResultSet, Sql 初始化RowMapper数据
	 * @param resultSet
	 * @param mapper
	 * @param cells
	 * @return
	 * @throws SQLException
	 */
	private <T> RowMapper<T> getMapperByResultSet(ResultSet resultSet, RowMapper<T> mapper,
		Map<String, CellMapper<?>> cells) throws SQLException {
		ResultSetMetaData rsmd = resultSet.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			mapper.putCell(rsmd.getColumnLabel(i), rsmd.getColumnClassName(i));
		}
		for (String hkey : cells.keySet()) {
			mapper.putCell(hkey, cells.get(hkey));
		}
		return mapper;
	}

	private static String getInsertParam(String keys) {
		String result = insert_values.get(keys);
		if(StringUtils.isBlank(result)) {
			result = keys.replaceAll("[^,^ ]+", "?");
			insert_values.put(keys, result);
		}
		return result;
	}

	private static String getUpdateParam(String keys) {
		String result = update_values.get(keys);
		if(StringUtils.isBlank(result)) {
			result = keys.replaceAll("[, ]+", " = ?, ") + " = ? ";
			update_values.put(keys, result);
		}
		return result;
	}

	private static String getWheresParam(String keys) {
		String result = wheres_values.get(keys);
		if(StringUtils.isBlank(result)) {
			result = keys.replaceAll("[, ]+", " = ? and ") + " = ? ";
			wheres_values.put(keys, result);
		}
		return result;
	}
}

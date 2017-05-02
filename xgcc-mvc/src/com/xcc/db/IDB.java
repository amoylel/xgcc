package com.xcc.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcc.db.cell.CellMapper;
import com.xcc.db.paging.Paging;
import com.xcc.db.row.RowMapper;

/**
 * 数据库链接查询接口
 * @author PangChao
 */
public interface IDB {
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection();
	
	/**
	 * 获取数据库 DatabaseMetaData
	 * @return
	 */
	public DatabaseMetaData getMetaData();

	/**
	 * 填充预处理sql语句
	 * @param statement 预处理sql 对象
	 * @param params statement 对象中 sql对应的参数值
	 */
	public void fullPreparedStatement(PreparedStatement statement, Object... params) throws SQLException;

	/**
	 * 批量SQL操作实现方法, 该方法只接管了 PreparedStatement 的创建 与关闭,具体循环过程都需要自己在processing中实现.<br/>
	 * 实现时, 填充 PreparedStatement参数可以调用 IDB.fullPreparedStatement
	 * @param sql 预编译SQL语句
	 * @param processing 回调接口
	 * @see com.xcc.db.IDB.fullPreparedStatement(PreparedStatement statement, Object... params)
	 */
	public void batch(String sql, DBProcessing processing);

	/**
	 * 执行insert,replace,update,delete语句,返回影响数据库记录的行数
	 * @param sql
	 * @param params
	 * @return
	 */
	public int execute(String sql, Object... params);

	/**
	 * 执行insert,replace语句, 返回数据库自增ID
	 * @param sql
	 * @param params
	 * @return 返回自增长ID值
	 */
	public long executeGen(String sql, Object... params);

	/**
	 * 执行insert,replace,update,delete语句,返回影响数据库记录的行数
	 * @param sql
	 * @return
	 */
	public int execute(Sql sql);

	/**
	 * 执行insert,replace语句, 返回数据库自增ID
	 * @param sql
	 * @return 返回自增长ID值
	 */
	public long executeGen(Sql sql);

	/**
	 * 生成SQL结果: insert into user_info(coumn1, column2, column3) values(?, ?, ?)
	 * @param tname 表名称 比如: user_info
	 * @param keys 比如: coumn1, column2, column3
	 * @param params 比如: va1, va2, va3 分别对应三个字段的值
	 * @return
	 */
	public int insert(String tname, String keys, Object... params);

	/**
	 * 生成SQL结果: insert into user_info(coumn1, column2, column3) values(?, ?, ?)
	 * @param tname 表名称 比如: user_info
	 * @param keys 比如: coumn1, column2, column3
	 * @param params 比如: va1, va2, va3 分别对应三个字段的值
	 * @return 返回自增长ID值
	 */
	public long insertGen(String tname, String keys, Object... params);

	/**
	 * 生成SQL结果: replace into user_info(coumn1, column2, column3) values(?, ?, ?)
	 * @param tname 表名称 比如: user_info
	 * @param keys 比如: coumn1, column2, column3
	 * @param params 比如: va1, va2, va3 分别对应三个字段的值
	 * @return
	 */
	public int replace(String tname, String keys, Object... params);

	/**
	 * 生成SQL结果: replace into user_info(coumn1, column2, column3) values(?, ?, ?)
	 * @param tname 表名称 比如: user_info
	 * @param keys 比如: coumn1, column2, column3
	 * @param params 比如: va1, va2, va3 分别对应三个字段的值
	 * @return 返回自增长ID值
	 */
	public long replaceGen(String tname, String keys, Object... params);

	/**
	 * 生成SQL结果: update coumn1 = ?, column2 = ?, column3 = ? where coumn4 = ? and column5 = ?
	 * @param tname 表名称 比如: user_info
	 * @param keys 比如: coumn1, column2, column3
	 * @param wheres 比如: coumn4, column5 默认用and方式连接
	 * @param params 比如: va1, va2, va3, va4, va5, 其中前三个对应keys的值, 后两个对应wheres的值
	 * @return
	 */
	public int update(String tname, String keys, String wheres, Object... params);

	/**
	 * 生成SQL结果: delete user_info where coumn1 = ? and column2 = ? and column3 = ?
	 * @param tname 表名称 比如: user_info
	 * @param wheres 比如: coumn1, column2, column3 默认用and方式连接
	 * @param params 比如: va1, va2, va3 分别对应三个字段值
	 * @return 影响数据库的条数
	 */
	public int delete(String tname, String wheres, Object... params);

	/**
	 * 查询记录, 根据sql中所有带条查询数据库所有匹配的记录<br/>
	 * 调用该方法时, 一定要手动关闭ResultSet结果集,否则导致无法关闭连接
	 * @param sql sql语句
	 * @param params sql语句是的参数
	 * @return
	 */
	public ResultSet executeQuery(String sql, Object... params);

	/**
	 * 查询记录, 根据sql所所带条件查询数据库所有匹配的记录<br/>
	 * 调用该方法时, 一定要手动关闭ResultSet结果集,否则导致无法关闭连接
	 * @param sql sql封装器
	 * @return
	 */
	public ResultSet executeQuery(Sql sql);

	/**
	 * 查询记录, 根据sql所所带条件查询数据库记录按分页工具分页<br/>
	 * 调用该方法时, 一定要手动关闭ResultSet结果集,否则导致无法关闭连接
	 * @param paging 分页工具
	 * @param sql sql语句
	 * @param params sql语句是的参数
	 * @return
	 */
	public ResultSet executeQuery(Paging paging, String sql, Object... params);

	/**
	 * 查询记录, 根据sql所所带条件查询数据库记录按分页工具分页<br/>
	 * 调用该方法时, 一定要手动关闭ResultSet结果集,否则导致无法关闭连接
	 * @param paging 分页工具
	 * @param sql sql封装器
	 * @return
	 */
	public ResultSet executeQuery(Paging paging, Sql sql);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 JSONArray
	 * @param resultSet
	 * @return
	 */
	public JSONArray analysis(ResultSet resultSet);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 JSONArray
	 * @param resultSet
	 * @param cells 需要单独处理的字段
	 * @return
	 */
	public JSONArray analysis(ResultSet resultSet, Map<String, CellMapper<?>> cells);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 List<T>
	 * @param resultSet
	 * @param clazz
	 * @return
	 */
	public <T> List<T> analysis(ResultSet resultSet, Class<T> clazz);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 List<T>
	 * @param resultSet
	 * @param clazz
	 * @param cells 需要单独处理的字段
	 * @return
	 */
	public <T> List<T> analysis(ResultSet resultSet, Class<T> clazz, Map<String, CellMapper<?>> cells);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 List<T>
	 * @param resultSet
	 * @param mapper 结果集解析过程
	 * @return
	 */
	public <T> List<T> analysis(ResultSet resultSet, RowMapper<T> mapper);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 JSONObject
	 * @param resultSet
	 * @return
	 */
	public JSONObject analysisOne(ResultSet resultSet);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 JSONObject
	 * @param resultSet
	 * @param cells 需要单独处理的字段
	 * @return
	 */
	public JSONObject analysisOne(ResultSet resultSet, Map<String, CellMapper<?>> cells);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 T
	 * @param resultSet
	 * @param clazz
	 * @return
	 */
	public <T> T analysisOne(ResultSet resultSet, Class<T> clazz);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 T
	 * @param resultSet
	 * @param clazz
	 * @param cells 需要单独处理的字段
	 * @return
	 */
	public <T> T analysisOne(ResultSet resultSet, Class<T> clazz, Map<String, CellMapper<?>> cells);

	/**
	 * 解析并关闭结果集， 将结果集数据组装成 T
	 * @param resultSet
	 * @param mapper 结果集解析过程
	 * @return
	 */
	public <T> T analysisOne(ResultSet resultSet, RowMapper<T> mapper);

	/**
	 * 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回
	 * @param sql sql封装工具
	 * @return
	 */
	public JSONArray query(Sql sql);

	/**
	 * 根据 查询所有记录
	 * @param sql
	 * @param params
	 * @return
	 */
	public JSONArray query(String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回
	 * @param 实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回
	 * @return 查询结果
	 */
	public <T> List<T> query(Class<T> clazz, Sql sql);

	/**
	 * 查询所有记录
	 * @param clazz 实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql 查询sql语句
	 * @param params
	 * @return
	 */
	public <T> List<T> query(Class<T> clazz, String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回, 该方法中sql中的cellMapper无效
	 * @param xRowMapper
	 * @param sql
	 * @return
	 */
	public <T> List<T> query(RowMapper<T> mapper, Sql sql);

	/**
	 * 查询所有数据
	 * @param mapper
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> query(RowMapper<T> mapper, String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件与Paging工具分页查询数据库
	 * @param paging 分页工具
	 * @param sql sql封装工具
	 * @return 查询结果 @see com.ms.mvc.util.JSONArrays
	 */
	public JSONArray query(Paging paging, Sql sql);

	/**
	 * 分页查询数据
	 * @param paging
	 * @param sql
	 * @param objects
	 * @return
	 */
	public JSONArray query(Paging paging, String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件与Paging工具分页查询数据库
	 * @param paging sql 分页工具
	 * @param clazz 实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql
	 * @return
	 */
	public <T> List<T> query(Paging paging, Class<T> clazz, Sql sql);

	/**
	 * 查询所有记录
	 * @param paging
	 * @param clazz 实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql
	 * @param objects
	 * @return
	 */
	public <T> List<T> query(Paging paging, Class<T> clazz, String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回, 该方法中sql中的cellMapper无效
	 * @param paging
	 * @param mapper
	 * @param sql
	 * @return
	 */
	public <T> List<T> query(Paging paging, RowMapper<T> mapper, Sql sql);

	/**
	 * 分页查询数据
	 * @param paging
	 * @param mapper
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> query(Paging paging, RowMapper<T> mapper, String sql, Object... params);

	/**
	 * 查询单条记录
	 * @param sql sql封装工具
	 * @return 查询结果 @see com.ms.mvc.util.JSONArrays
	 */
	public JSONObject queryOne(Sql sql);

	/**
	 * 查询单条记录
	 * @param sql
	 * @param params
	 * @return
	 */
	public JSONObject queryOne(String sql, Object... params);

	/**
	 * 查询单条记录
	 * @param 实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回
	 * @return 查询结果
	 */
	public <T> T queryOne(Class<T> clazz, Sql sql);

	/**
	 * 查询单条记录
	 * @param clazz 实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> T queryOne(Class<T> clazz, String sql, Object... params);

	/**
	 * 查询单条记录 该方法中sql中的cellMapper无效
	 * @param mapper
	 * @param sql
	 * @return
	 */
	public <T> T queryOne(RowMapper<T> mapper, Sql sql);

	/**
	 * 查询单条记录
	 * @param mapper
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> T queryOne(RowMapper<T> mapper, String sql, Object... params);

	/**
	 * 查询单一 String 数据值
	 * @param sql
	 */
	public String queryString(Sql sql);

	/**
	 * 查询单一 String 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public String queryString(String sql, Object... params);

	/**
	 * 查询单一 Long 数据值
	 * @param sql
	 */
	public long queryLong(Sql sql);

	/**
	 * 查询单一 Long 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public long queryLong(String sql, Object... params);

	/**
	 * 查询单一 int 数据值
	 * @param sql
	 */
	public int queryInt(Sql sql);

	/**
	 * 查询单一 int 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public int queryInt(String sql, Object... params);

	/**
	 * 查询单一 short 数据值
	 * @param sql
	 */
	public short queryShort(Sql sql);

	/**
	 * 查询单一 short 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public short queryShort(String sql, Object... params);

	/**
	 * 查询单一 byte 数据值
	 * @param sql
	 */
	public byte queryByte(Sql sql);

	/**
	 * 查询单一 short 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public byte queryByte(String sql, Object... params);

	/**
	 * 查询单一 double 数据值
	 * @param sql
	 */
	public double queryDouble(Sql sql);

	/**
	 * 查询单一 double 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public double queryDouble(String sql, Object... params);

	/**
	 * 查询单一 float 数据值
	 * @param sql
	 */
	public float queryFloat(Sql sql);

	/**
	 * 查询单一 float 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public float queryFloat(String sql, Object... params);

	/**
	 * 查询单一 date 数据值
	 * @param sql
	 */
	public Date queryDate(Sql sql);

	/**
	 * 查询单一 date 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public Date queryDate(String sql, Object... params);

	/**
	 * 查询单一 boolean 数据值
	 * @param sql
	 */
	public boolean queryBoolean(Sql sql);

	/**
	 * 查询单一 boolean 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean queryBoolean(String sql, Object... params);

	/**
	 * 事件执行方法
	 * @param transaction
	 * @return true: 事务执行成功(最后提交事务), false: 事务执行失败, 最后回滚
	 */
	public boolean transaction(DBTransaction transaction);

	/**
	 * 事件执行方法
	 * @param transLevel 事务级别
	 * @param transaction
	 * @return true: 事务执行成功(最后提交事务), false: 事务执行失败, 最后回滚
	 */
	public boolean transaction(int transLevel, DBTransaction transaction);

}

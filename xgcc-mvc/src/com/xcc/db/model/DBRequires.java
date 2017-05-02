/**
 * Created the com.xcc.db.model.DBRequires.java
 * @created 2017年4月26日 下午5:22:44
 * @version 1.0.0
 */
package com.xcc.db.model;

import com.xcc.db.Sql;

/**
 * com.xcc.db.model.DBRequires.java
 * @author XChao
 */
@FunctionalInterface
public interface DBRequires {
	/**
	 * 在sql中添加查询条件 如： sql.append(" and s_id = ?", sid);
	 * @param sql
	 */
	public void requires(Sql sql);
}

/**
 * Created the com.xcc.db.DBProcessing.java
 * @created 2017年3月2日 上午11:52:48
 * @version 1.0.0
 */
package com.xcc.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * com.xcc.db.DBProcessing.java
 * @author XChao
 */
@FunctionalInterface
public interface DBProcessing {
	public void processing(IDB db, PreparedStatement statement) throws SQLException;
}

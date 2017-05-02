/**   
 * Created the com.xsy.db.XDBTransaction.java
 * @created 2016年10月22日 下午3:05:39 
 * @version 1.0.0 
 */
package com.xcc.db;

import java.sql.SQLException;

/**   
 * com.xsy.db.XDBTransaction.java 
 * @author XChao  
 */
@FunctionalInterface
public interface DBTransaction {
	public boolean transaction(IDB db) throws SQLException;
}

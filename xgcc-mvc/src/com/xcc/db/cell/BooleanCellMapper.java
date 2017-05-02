/**   
 * Created the com.xsy.db.cellmapper.XBooleanCellMapper.java
 * @created 2016年10月9日 下午5:42:21 
 * @version 1.0.0 
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**   
 * com.xsy.db.cellmapper.XBooleanCellMapper.java 
 * @author XChao  
 */
public class BooleanCellMapper implements CellMapper<Boolean>{

	@Override
	public Boolean getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getBoolean(columnLabel);
	}

}

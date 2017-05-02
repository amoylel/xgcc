/**   
 * Created the com.xsy.db.cellmapper.XIntegetCellMapper.java
 * @created 2016年10月9日 下午5:37:56 
 * @version 1.0.0 
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**   
 * com.xsy.db.cellmapper.XIntegetCellMapper.java 
 * @author XChao  
 */
public class IntegerCellMapper implements CellMapper<Integer>{

	@Override
	public Integer getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getInt(columnLabel);
	}

}

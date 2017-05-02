/**
 * Created the com.xsy.db.cellmapper.XDateCellMapper.java
 * @created 2016年10月9日 下午5:44:46
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XDateCellMapper.java
 * @author XChao
 */
public class DateCellMapper implements CellMapper<java.util.Date> {

	@Override
	public java.util.Date getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getTimestamp(columnLabel);
	}

}

/**
 * Created the com.xsy.db.cellmapper.XDateToLongCellMapper.java
 * @created 2016年10月9日 下午5:46:02
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XDateToLongCellMapper.java
 * @author XChao
 */
public class DateToLongCellMapper implements CellMapper<Long> {

	@Override
	public Long getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getTimestamp(columnLabel).getTime();
	}

}

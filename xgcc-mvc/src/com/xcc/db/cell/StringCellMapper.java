/**
 * Created the com.xsy.db.cellmapper.XEntrysCellMapper.java
 * @created 2016年10月9日 下午5:34:09
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XEntrysCellMapper.java
 * @author XChao
 */
public class StringCellMapper implements CellMapper<String> {

	@Override
	public String getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getString(columnLabel);
	}

}

/**
 * Created the com.xsy.db.cellmapper.XShortCellMapper.java
 * @created 2016年10月9日 下午5:38:41
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XShortCellMapper.java
 * @author XChao
 */
public class ShortCellMapper implements CellMapper<Short> {

	@Override
	public Short getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getShort(columnLabel);
	}

}

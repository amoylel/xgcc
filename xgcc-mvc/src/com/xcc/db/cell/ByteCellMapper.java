/**
 * Created the com.xsy.db.cellmapper.XByteCellMapper.java
 * @created 2016年10月9日 下午5:39:31
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XByteCellMapper.java
 * @author XChao
 */
public class ByteCellMapper implements CellMapper<Byte> {

	@Override
	public Byte getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getByte(columnLabel);
	}

}

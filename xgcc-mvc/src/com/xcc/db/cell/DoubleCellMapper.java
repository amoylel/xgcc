/**
 * Created the com.xsy.db.cellmapper.XDoubleCellMapper.java
 * @created 2016年10月9日 下午5:40:46
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XDoubleCellMapper.java
 * @author XChao
 */
public class DoubleCellMapper implements CellMapper<Double> {

	@Override
	public Double getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getDouble(columnLabel);
	}

}

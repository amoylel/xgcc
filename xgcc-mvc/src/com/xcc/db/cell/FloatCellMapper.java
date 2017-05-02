/**
 * Created the com.xsy.db.cellmapper.XFloatCellMapper.java
 * @created 2016年10月9日 下午5:41:30
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XFloatCellMapper.java
 * @author XChao
 */
public class FloatCellMapper implements CellMapper<Float> {

	@Override
	public Float getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getFloat(columnLabel);
	}

}

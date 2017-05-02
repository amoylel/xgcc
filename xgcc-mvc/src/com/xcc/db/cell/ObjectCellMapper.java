/**
 * Created the com.xsy.db.cellmapper.XObjectCellMapper.java
 * @created 2016年10月9日 下午5:15:23
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XObjectCellMapper.java
 * @author XChao
 */
public class ObjectCellMapper implements CellMapper<Object> {
	@Override
	public Object getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getObject(columnLabel);
	}
}

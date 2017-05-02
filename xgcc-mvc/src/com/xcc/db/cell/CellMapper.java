/**
 * Created the com.xsy.db.cellmapper.XCellMapper.java
 * @created 2016年9月24日 上午11:49:55
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * com.xsy.db.cellmapper.XCellMapper.java
 * @author XChao
 */
public interface CellMapper<T> {
	public T getcell(ResultSet resultSet, String columnLabel) throws SQLException;
}

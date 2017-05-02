/**
 * Created the com.xsy.db.rowmapper.XRowMapper.java
 * @created 2016年9月24日 上午11:54:02
 * @version 1.0.0
 */
package com.xcc.db.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xcc.db.cell.CellMapper;

/**
 * com.xsy.db.rowmapper.XRowMapper.java
 * @author XChao
 */
public interface RowMapper<T> {

	public T getRow(ResultSet resultSet) throws SQLException ;
	
	public void putCell(String columnLabel, CellMapper<?> xCellMapper);
	
	public void putCell(String columnLabel, String columnClassName);

}

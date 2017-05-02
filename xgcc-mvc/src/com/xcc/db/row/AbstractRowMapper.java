/**
 * Created the com.xsy.db.rowmapper.XAbstractRowMapper.java
 * @created 2016年9月24日 上午11:58:29
 * @version 1.0.0
 */
package com.xcc.db.row;

import java.util.HashMap;
import java.util.Map;

import com.xcc.db.cell.CellMapper;
import com.xcc.db.cell.CellMapping;

/**
 * com.xsy.db.rowmapper.XAbstractRowMapper.java
 * @author XChao
 */
public abstract class AbstractRowMapper<T> implements RowMapper<T> {
	protected final Map<String, CellMapper<?>> cells = new HashMap<>();
	@Override
	public final void putCell(String columnLabel, CellMapper<?> xCellMapper) {
		this.cells.put(columnLabel, xCellMapper);
	}
	@Override
	public final void putCell(String columnLabel, String columnClassName) {
		this.cells.put(columnLabel, CellMapping.getCellMapper(columnClassName));
	}
}

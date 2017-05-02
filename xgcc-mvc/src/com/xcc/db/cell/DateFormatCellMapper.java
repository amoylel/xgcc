/**
 * Created the com.xsy.db.cellmapper.XDateToStringCellMapper.java
 * @created 2016年10月9日 下午5:47:44
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xcc.utils.DateUtil;

/**
 * com.xsy.db.cellmapper.XDateToStringCellMapper.java
 * @author XChao
 */
public class DateFormatCellMapper implements CellMapper<String> {

	private String formatter;

	public DateFormatCellMapper(String formatter) {
		this.formatter = formatter;
	}

	@Override
	public String getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return DateUtil.format(resultSet.getTimestamp(columnLabel), this.formatter);
	}

}

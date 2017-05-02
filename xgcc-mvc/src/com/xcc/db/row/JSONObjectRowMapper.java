/**
 * Created the com.xsy.db.rowmapper.XEntrysRowMapper.java
 * @created 2016年10月9日 下午4:21:17
 * @version 1.0.0
 */
package com.xcc.db.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONObject;

/**
 * com.xsy.db.rowmapper.XEntrysRowMapper.java
 * @author XChao
 */
public class JSONObjectRowMapper extends AbstractRowMapper<JSONObject> {

	@Override
	public JSONObject getRow(ResultSet resultSet) throws SQLException {
		JSONObject entrys = new JSONObject();
		for (String columnLable : this.cells.keySet()) {
			entrys.put(columnLable, this.cells.get(columnLable).getcell(resultSet, columnLable));
		}
		return entrys;
	}

}

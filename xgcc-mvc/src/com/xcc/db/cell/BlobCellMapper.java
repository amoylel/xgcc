/**   
 * Created the com.xsy.db.cellmapper.XBlobCellMapper.java
 * @created 2016年10月9日 下午5:52:02 
 * @version 1.0.0 
 */
package com.xcc.db.cell;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**   
 * com.xsy.db.cellmapper.XBlobCellMapper.java 
 * @author XChao  
 */
public class BlobCellMapper implements CellMapper<Blob>{

	@Override
	public Blob getcell(ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getBlob(columnLabel);
	}

}

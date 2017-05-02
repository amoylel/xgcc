/**
 * Created the com.xsy.db.utils.XCellMapperUtil.java
 * @created 2016年9月24日 下午12:03:31
 * @version 1.0.0
 */
package com.xcc.db.cell;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * com.xsy.db.utils.XCellMapperUtil.java
 * @author XChao
 */
public class CellMapping {
	private static final Map<String, CellMapper<?>> cells = new HashMap<>();

	static {
		cells.put(Object.class.getName(), new ObjectCellMapper());
		cells.put(String.class.getName(), new StringCellMapper());
		cells.put(Long.class.getName(), new LongCellMapper());
		cells.put(Integer.class.getName(), new IntegerCellMapper());
		cells.put(Short.class.getName(), new ShortCellMapper());
		cells.put(Byte.class.getName(), new ByteCellMapper());
		cells.put(Double.class.getName(), new DoubleCellMapper());
		cells.put(Float.class.getName(), new FloatCellMapper());
		cells.put(Boolean.class.getName(), new BooleanCellMapper());
		cells.put(BigDecimal.class.getName(), new DoubleCellMapper());
		cells.put(BigInteger.class.getName(), new LongCellMapper());
		cells.put(Date.class.getName(), new DateCellMapper());
		cells.put(java.sql.Date.class.getName(), new DateCellMapper());
		cells.put(java.sql.Time.class.getName(), new DateCellMapper());
		cells.put(java.sql.Timestamp.class.getName(), new DateCellMapper());
		cells.put(Blob.class.getName(), new BlobCellMapper());
		cells.put(long.class.getName(), new LongCellMapper());
		cells.put(int.class.getName(), new IntegerCellMapper());
		cells.put(short.class.getName(), new ShortCellMapper());
		cells.put(byte.class.getName(), new ByteCellMapper());
		cells.put(double.class.getName(), new DoubleCellMapper());
		cells.put(float.class.getName(),  new FloatCellMapper());
		cells.put(boolean.class.getName(),  new BooleanCellMapper());
	}

	public static CellMapper<?> getCellMapper(String columnClassName) {
		CellMapper<?> xCellMapper = cells.get(columnClassName);
		if (xCellMapper == null) {
			xCellMapper = cells.get(Object.class.getName());
		}
		return xCellMapper;
	}
}

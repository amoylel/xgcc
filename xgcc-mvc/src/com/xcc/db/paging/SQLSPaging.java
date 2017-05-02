/**
 * Created the com.xcc.db.paging.SQLSPaging.java
 * @created 2017年2月23日 下午1:26:34
 * @version 1.0.0
 */
package com.xcc.db.paging;

/**
 * com.xcc.db.paging.SQLSPaging.java
 * @author XChao
 */
public final class SQLSPaging extends Paging {

	public SQLSPaging(int page, int rows) {
		super(page, rows);
	}

	@Override
	public String paging(String sql) {
		// select top (startRow + rowNmu) from tableName ;
//		return new StringBuilder(sql).append(" limit ")
		// .append(this.getStart()).append(", ").append(this.getPage()).toString();
		return null;
	}
}

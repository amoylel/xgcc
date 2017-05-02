/**
 * Created the com.xcc.db.paging.DB2Paging.java
 * @created 2017年2月23日 下午1:09:26
 * @version 1.0.0
 */
package com.xcc.db.paging;

/**
 * db2 分页实现
 * @author XChao
 */
public final class DB2Paging extends Paging {
	public DB2Paging(int page, int rows) {
		super(page, rows);
	}

	@Override
	public String paging(String sql) {
		return new StringBuilder(sql).append(" limit ").append(this.getStart()).append(", ").append(this.getRows()).toString();
	}
}

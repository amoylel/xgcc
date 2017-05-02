/**
 * Created the com.xcc.db.paging.MSPaging.java
 * @created 2017年2月23日 下午1:28:09
 * @version 1.0.0
 */
package com.xcc.db.paging;

/**
 * com.xcc.db.paging.MSPaging.java
 * @author XChao
 */
public final class MSPaging extends Paging {

	public MSPaging(int page, int rows) {
		super(page, rows);
	}

	@Override
	public String paging(String sql) {
//		//
//		return new StringBuilder(sql).append(" limit ").append(this.getStart())
//				.append(", ").append(this.getPage()).toString();
		return null;
	}
}

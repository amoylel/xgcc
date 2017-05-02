/**
 * Created the com.xcc.db.paging.MysqlPaging.java
 * @created 2017年2月23日 下午12:49:46
 * @version 1.0.0
 */
package com.xcc.db.paging;

/**
 * Mysql 分页实现
 * @author XChao
 */
public final class MPaging extends Paging {

	public MPaging(int page, int rows) {
		super(page, rows);
	}

	@Override
	public String paging(String sql) {
		return new StringBuilder(sql).append(" limit ").append(this.getStart()).append(", ").append(this.getRows()).toString();
	}

}

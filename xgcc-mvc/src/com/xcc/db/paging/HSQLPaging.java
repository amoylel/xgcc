/**
 * Created the com.xcc.db.paging.HSQLPaging.java
 * @created 2017年2月23日 下午1:21:37
 * @version 1.0.0
 */
package com.xcc.db.paging;

/**
 * hsql 分页实现
 * @author XChao
 */
public final class HSQLPaging extends Paging {

	public HSQLPaging(int page, int rows) {
		super(page, rows);
	}

	@Override
	public String paging(String sql) {
		// select limit startRow rowNum * from (select * from tableName) ;
		StringBuilder builder = new StringBuilder("select limit ").append(this.getStart()).append(" ");
		return builder.append(this.getRows()).append(" from (").append(sql).append(")").toString();
	}

}

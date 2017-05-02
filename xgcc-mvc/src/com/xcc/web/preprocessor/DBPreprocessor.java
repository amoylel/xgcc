/**
 * Created the com.xsy.web.hander.XDefaultDBHandler.java
 * @created 2016年10月13日 上午11:25:00
 * @version 1.0.0
 */
package com.xcc.web.preprocessor;

import com.xcc.db.DBFactory;
import com.xcc.db.IDB;

/**
 * 带有默认数据库连接的预处理执行器
 * @author XChao
 */
public abstract class DBPreprocessor implements IPreprocessor {

	protected IDB getDB(String name) {
		return DBFactory.create(name);
	}

	@Override
	public void process() {
		try {
			this.dbProcess();
		} finally {
			for (String name : DBFactory.getThreadDB().keySet()) {
				DBFactory.close(DBFactory.getThreadDB().get(name));
			}
		}
	}

	public abstract void dbProcess();

}

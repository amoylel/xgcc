/**
 * Created the com.xsy.web.jobtask.MasterTask.java
 * @created 2016年10月13日 上午11:05:47
 * @version 1.0.0
 */
package com.xcc.web.scheduler.thread;

import com.xcc.db.DBFactory;
import com.xcc.db.IDB;
import com.xcc.utils.logger.Logger;

/**
 * 带有默认数据库连接的定时任务执行器
 * @author XChao
 */
public abstract class DBTask implements ITask {

	protected IDB getDB(String name) {
		return DBFactory.create(name);
	}

	protected void logger(Throwable e) {
		Logger.severe("定时任务[" + this.getClass().getName() + "]执行失败", e);
	}

	@Override
	public void execute() {
		try {
			this.dbExecute();
		} finally {
			for (String name : DBFactory.getThreadDB().keySet()) {
				DBFactory.close(DBFactory.getThreadDB().get(name));
			}
		}
	}

	public abstract void dbExecute();

}

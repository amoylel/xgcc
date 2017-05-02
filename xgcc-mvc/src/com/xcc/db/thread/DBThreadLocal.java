/**
 * Created the com.xcc.db.thread.DBThreadLocal.java
 * @created 2017年2月23日 上午11:13:26
 * @version 1.0.0
 */
package com.xcc.db.thread;

import java.util.HashMap;
import java.util.Map;

import com.xcc.db.IDB;

/**
 * com.xcc.db.thread.DBThreadLocal.java
 * @author XChao
 */
public class DBThreadLocal extends ThreadLocal<Map<String, IDB>> {
	public Map<String, IDB> initialValue() {
		return new HashMap<String, IDB>();
	}
}

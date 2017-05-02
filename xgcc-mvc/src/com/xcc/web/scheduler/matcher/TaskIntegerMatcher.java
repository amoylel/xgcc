/**
 * Created the com.xsy.web.jobtask.matcher.XTaskIntegerMatcher.java
 * @created 2016年10月17日 下午4:53:24
 * @version 1.0.0
 */
package com.xcc.web.scheduler.matcher;

import java.util.List;

/**
 * com.xsy.web.jobtask.matcher.XTaskIntegerMatcher.java
 * @author XChao
 */
public class TaskIntegerMatcher implements ITaskMatcher {
	private List<Integer> values;

	public TaskIntegerMatcher(List<Integer> values) {
		this.values = values;
	}

	public boolean match(int value) {
		for (Integer v : values) {
			if (v != null && v.intValue() == value) {
				return true;
			}
		}
		return false;
	}
}

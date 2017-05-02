/**
 * Created the com.xsy.web.context.XTaskPorxy.java
 * @created 2016年9月26日 下午3:37:16
 * @version 1.0.0
 */
package com.xcc.web.scheduler;

import com.xcc.web.core.AbstractPorxy;

/**
 * com.xsy.web.context.XTaskPorxy.java
 * @author XChao
 */
public class TaskPorxy extends AbstractPorxy {
	private String rule = "* * * * *";


	/**
	 * @return the rule
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * @param rule the rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}

}

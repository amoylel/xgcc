/**
 * Created the com.xcc.web.view.ViewPorxy.java
 * @created 2017年2月23日 下午3:24:02
 * @version 1.0.0
 */
package com.xcc.web.view;

import com.xcc.web.core.AbstractPorxy;

/**
 * com.xcc.web.view.ViewPorxy.java
 * @author XChao
 */
public class ViewPorxy extends AbstractPorxy {

	private String pageSuffix;

	/**
	 * @return the pageSuffix
	 */
	public String getPageSuffix() {
		return pageSuffix;
	}

	/**
	 * @param pageSuffix the pageSuffix to set
	 */
	public void setPageSuffix(String pageSuffix) {
		this.pageSuffix = pageSuffix;
	}
}

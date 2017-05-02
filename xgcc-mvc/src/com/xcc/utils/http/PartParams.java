/**
 * Created the com.xcc.utils.http.PartParams.java
 * @created 2016年11月14日 下午9:31:32
 * @version 1.0.0
 */
package com.xcc.utils.http;

import java.util.HashMap;

import javax.servlet.http.Part;

/**
 * com.xcc.utils.http.PartParams.java
 * @author XChao
 */
public class PartParams extends HashMap<String, Part> {
	private static final long serialVersionUID = 1L;

	public PartParams() {
	}

	public PartParams(String name, Part value) {
		this.put(name, value);
	}

	public PartParams add(String name, Part value) {
		this.put(name, value);
		return this;
	}
}

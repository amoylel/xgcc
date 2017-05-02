/**
 * Created the com.xcc.utils.http.TextParams.java
 * @created 2016年11月14日 下午9:21:12
 * @version 1.0.0
 */
package com.xcc.utils.http;

import java.util.HashMap;

/**
 * com.xcc.utils.http.TextParams.java
 * @author XChao
 */
public class TextParams extends HashMap<String, String> {
	private static final long serialVersionUID = 1L;

	public TextParams() {
	}

	public TextParams(String name, String value) {
		this.put(name, value);
	}

	public TextParams add(String name, String value) {
		this.put(name, value);
		return this;
	}
}

/**
 * Created the com.xcc.utils.http.FileParams.java
 * @created 2016年11月14日 下午9:26:38
 * @version 1.0.0
 */
package com.xcc.utils.http;

import java.io.File;
import java.util.HashMap;

/**
 * com.xcc.utils.http.FileParams.java
 * @author XChao
 */
public class FileParams extends HashMap<String, File> {
	private static final long serialVersionUID = 1L;

	public FileParams() {
	}

	public FileParams(String name, File value) {
		this.put(name, value);
	}

	public FileParams add(String name, File value) {
		this.put(name, value);
		return this;
	}
}

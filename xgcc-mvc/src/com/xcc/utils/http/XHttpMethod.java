/**
 * Created the com.xsy.http.XHTTPRequestMethod.java
 * @created 2016年9月22日 下午1:46:46
 * @version 1.0.0
 */
package com.xcc.utils.http;

/**
 * com.xsy.http.XHTTPRequestMethod.java
 * @author XChao
 */
public enum XHttpMethod {
	POST("POST"), GET("GET");

	private String value;

	XHttpMethod(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

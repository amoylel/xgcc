/**
 * Created the com.xsy.utils.http.XHTTPUtils.java
 * @created 2016年10月19日 下午4:43:12
 * @version 1.0.0
 */
package com.xcc.utils.http;

/**
 * com.xsy.utils.http.XHTTPUtils.java
 * @author XChao
 */
public class HTTPUtil {

	public static String request(String url, TextParams texts) {
		return HTTPUtil.request(url, texts, null, null);
	}

	public static String request(String url, PartParams parts) {
		return HTTPUtil.request(url, null, parts, null);
	}

	public static String request(String url, FileParams files) {
		return HTTPUtil.request(url, null, null, files);
	}

	public static String request(String url, TextParams texts, PartParams parts) {
		return HTTPUtil.request(url, texts, parts, null);
	}

	public static String request(String url, TextParams texts, FileParams files) {
		return HTTPUtil.request(url, texts, null, files);
	}

	public static String request(String url, PartParams parts, FileParams files) {
		return HTTPUtil.request(url, null, parts, files);
	}

	public static String request(String url, TextParams texts, PartParams parts, FileParams files) {
		try {
			XHttpURLConnection connection = new XHttpURLConnection(url);
			connection.setRequestMethod(XHttpMethod.POST);
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Connection", "Keep-Alive");
			if (texts != null) {
				for (String key : texts.keySet()) {
					connection.addParam(key, texts.get(key));
				}
			}
			if (files != null) {
				for (String key : files.keySet()) {
					connection.addParam(key, files.get(key));
				}
			}
			if (parts != null) {
				for (String key : parts.keySet()) {
					connection.addParam(key, parts.get(key));
				}
			}
			return connection.sendRequestToString();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

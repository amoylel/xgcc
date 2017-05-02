/**
 * Created the com.xsy.utils.http.XHTTPUtils.java
 * @created 2016年10月19日 下午4:43:12
 * @version 1.0.0
 */
package com.xcc.utils.http;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * com.xsy.utils.http.XHTTPUtils.java
 * @author XChao
 */
public class HTTPSUtil {
	/**
	 * TrustManager
	 * @author XChao
	 */
	private static X509TrustManager x509TrustManager = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	/**
	 * 获取 SSLSocketFactory
	 * @return
	 * @throws Exception
	 */
	private static SSLSocketFactory getSSLSocketFactory() throws Exception {
		// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
		SSLContext sslContext = SSLContext.getInstance("TLS");
		// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
		sslContext.init(null, new TrustManager[] { x509TrustManager }, new SecureRandom());
		// 创建SSLSocketFactory
		return sslContext.getSocketFactory();
	}

	public static String request(String url, TextParams texts) {
		return HTTPSUtil.request(url, texts, null, null);
	}

	public static String request(String url, PartParams parts) {
		return HTTPSUtil.request(url, null, parts, null);
	}

	public static String request(String url, FileParams files) {
		return HTTPSUtil.request(url, null, null, files);
	}

	public static String request(String url, TextParams texts, PartParams parts) {
		return HTTPSUtil.request(url, texts, parts, null);
	}

	public static String request(String url, TextParams texts, FileParams files) {
		return HTTPSUtil.request(url, texts, null, files);
	}

	public static String request(String url, PartParams parts, FileParams files) {
		return HTTPSUtil.request(url, null, parts, files);
	}

	public static String request(String url, TextParams texts, PartParams parts, FileParams files) {
		try {
			XHttpsURLConnection connection = new XHttpsURLConnection(url);
			connection.setSSLSocketFactory(getSSLSocketFactory());
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

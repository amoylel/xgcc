/**
 * Created the com.xsy.utils.http.XHttpsURLConnection.java
 * @created 2016年11月1日 下午5:59:40
 * @version 1.0.0
 */
package com.xcc.utils.http;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/**
 * com.xsy.utils.http.XHttpsURLConnection.java
 * @author XChao
 */
public class XHttpsURLConnection extends XHttpURLConnection {
	protected HttpsURLConnection connection;

	public XHttpsURLConnection(String connectionUrl) throws IOException {
		super(connectionUrl);
		this.connection = (HttpsURLConnection) super.connection;
	}

	public String getCipherSuite() {
		return connection.getCipherSuite();
	}

	public HostnameVerifier getHostnameVerifier() {
		return connection.getHostnameVerifier();
	}

	public Certificate[] getLocalCertificates() {
		return connection.getLocalCertificates();
	}

	public Principal getLocalPrincipal() {
		return connection.getLocalPrincipal();
	}

	public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
		return connection.getPeerPrincipal();
	}

	public SSLSocketFactory getSSLSocketFactory() {
		return connection.getSSLSocketFactory();
	}

	public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
		return connection.getServerCertificates();
	}

	public void setHostnameVerifier(HostnameVerifier v) {
		connection.setHostnameVerifier(v);
	}

	public void setSSLSocketFactory(SSLSocketFactory sf) {
		connection.setSSLSocketFactory(sf);
	}

	public void disconnect() {
		connection.disconnect();
	}

	public boolean usingProxy() {
		return connection.usingProxy();
	}

	public void connect() throws IOException {
		connection.connect();
	}
}

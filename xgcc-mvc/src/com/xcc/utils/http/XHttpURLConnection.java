/**
 * Created the com.xsy.http.XHttpConnection.java
 * @created 2016年9月22日 下午2:21:20
 * @version 1.0.0
 */
package com.xcc.utils.http;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Part;

import com.xcc.utils.logger.Logger;

/**
 * com.xsy.http.XHttpConnection.java
 * @author XChao
 */
public class XHttpURLConnection extends XURLConnection {
	public static interface XURLConnectionCall {
		public void callBack(InputStream is, String name, long size, String contentType);
	}

	private static final String END = "\r\n";
	private static final String HYPHENS = "--";
	public static final String BOUNDARY = "*****";

	private Map<String, String> textParams = new HashMap<>();
	private Map<String, Part> partParams = new HashMap<>();
	private Map<String, File> fileParams = new HashMap<>();

	private DataOutputStream dataOutputStream;
	private InputStream inputStream;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	private StringBuffer stringBuffer;
	private String tempLine;

	protected HttpURLConnection connection;

	public XHttpURLConnection(String connectionUrl) throws IOException {
		super(connectionUrl);
		this.connection = (HttpURLConnection) super.connection;
	}

	public void disconnect() {
		connection.disconnect();
	}

	public boolean usingProxy() {
		return connection.usingProxy();
	}

	public void setFixedLengthStreamingMode(int contentLength) {
		connection.setFixedLengthStreamingMode(contentLength);
	}

	public void setFixedLengthStreamingMode(long contentLength) {
		connection.setFixedLengthStreamingMode(contentLength);
	}

	public void setChunkedStreamingMode(int chunklen) {
		connection.setChunkedStreamingMode(chunklen);
	}

	public void setInstanceFollowRedirects(boolean followRedirects) {
		connection.setInstanceFollowRedirects(followRedirects);
	}

	public boolean getInstanceFollowRedirects() {
		return connection.getInstanceFollowRedirects();
	}

	public void setRequestMethod(XHttpMethod method) throws ProtocolException {
		connection.setRequestMethod(method.getValue());
	}

	public XHttpMethod getRequestMethod() {
		return XHttpMethod.valueOf(connection.getRequestMethod());
	}

	public int getResponseCode() throws IOException {
		return connection.getResponseCode();
	}

	public String getResponseMessage() throws IOException {
		return connection.getResponseMessage();
	}

	public InputStream getErrorStream() {
		return connection.getErrorStream();
	}

	public XHttpURLConnection addParam(String name, String value) {
		this.textParams.put(name, value);
		return this;
	}

	public XHttpURLConnection addParam(String name, File value) {
		this.fileParams.put(name, value);
		return this;
	}

	public XHttpURLConnection addParam(String name, Part value) {
		this.partParams.put(name, value);
		return this;
	}

	public String sendRequestToString() throws IOException {
		try {
			setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
			dataOutputStream = new DataOutputStream(connection.getOutputStream());
			writeTextParams();
			writeFileParams();
			writePartParams();
			writeBytesEndAndFlush();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("HTTP Request is fail, Response code : " + connection.getResponseCode());
			}
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			stringBuffer = new StringBuffer();
			while ((tempLine = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempLine);
				stringBuffer.append("\n");
			}
			return stringBuffer.toString();
		} finally {
			close(dataOutputStream).close(bufferedReader).close(inputStreamReader).close(inputStream);
		}
	}

	public InputStream sendRequestToInputStream() throws IOException {
		try {
			dataOutputStream = new DataOutputStream(connection.getOutputStream());
			writeTextParams();
			writeFileParams();
			writePartParams();
			writeBytesEndAndFlush();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("HTTP Request is fail, Response code : " + connection.getResponseCode());
			}
			return connection.getInputStream();
		} finally {
			close(dataOutputStream).close(bufferedReader).close(inputStreamReader).close(inputStream);
		}
	}

	public void sendRequestCallBack(XURLConnectionCall xURLConnectionCall) throws IOException {
		try {
			dataOutputStream = new DataOutputStream(connection.getOutputStream());
			writeTextParams();
			writeFileParams();
			writePartParams();
			writeBytesEndAndFlush();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("HTTP Request is fail, Response code : " + connection.getResponseCode());
			}
			xURLConnectionCall.callBack(connection.getInputStream(), connection.getURL().getFile(), connection.getContentLength(),
					connection.getContentType());
		} finally {
			close(dataOutputStream).close(bufferedReader).close(inputStreamReader).close(inputStream);
		}
	}

	protected void writeTextParams() throws IOException {
		for (String key : textParams.keySet()) {
			dataOutputStream.writeBytes(HYPHENS + BOUNDARY + END);
			dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + END);
			dataOutputStream.writeBytes(END);
			dataOutputStream.writeBytes(textParams.get(key));
			dataOutputStream.writeBytes(END);
		}
	}

	protected void writeFileParams() throws IOException {
		for (String key : fileParams.keySet()) {
			dataOutputStream.writeBytes(HYPHENS + BOUNDARY + END);
			StringBuilder contentDisposition = new StringBuilder("Content-Disposition: form-data; name=\"");
			contentDisposition.append(key).append("\";filename=\"").append(fileParams.get(key).getName());
			dataOutputStream.writeBytes(contentDisposition.append("\"").append(END).toString());
			dataOutputStream.writeBytes(END);
			InputStream _inputStream = null;
			try {
				int bufferSize = 1024, length = -1;
				byte[] buffer = new byte[bufferSize];
				_inputStream = new FileInputStream(fileParams.get(key));
				while ((length = _inputStream.read(buffer)) != -1) {
					dataOutputStream.write(buffer, 0, length);
				}
			} finally {
				close(_inputStream);
			}
			dataOutputStream.writeBytes(END);
		}
	}

	protected void writePartParams() throws IOException {
		for (String key : partParams.keySet()) {
			dataOutputStream.writeBytes(HYPHENS + BOUNDARY + END);
			StringBuilder contentDisposition = new StringBuilder("Content-Disposition: form-data; name=\"");
			contentDisposition.append(key).append("\";filename=\"").append(partParams.get(key).getName());
			dataOutputStream.writeBytes(contentDisposition.append("\"").append(END).toString());
			dataOutputStream.writeBytes(END);
			InputStream _inputStream = null;
			try {
				int bufferSize = 1024, length = -1;
				byte[] buffer = new byte[bufferSize];
				_inputStream = partParams.get(key).getInputStream();
				while ((length = _inputStream.read(buffer)) != -1) {
					dataOutputStream.write(buffer, 0, length);
				}
			} finally {
				close(_inputStream);
			}
			dataOutputStream.writeBytes(END);
		}
	}

	protected void writeBytesEndAndFlush() throws IOException {
		dataOutputStream.writeBytes(HYPHENS + BOUNDARY + HYPHENS + END);
		dataOutputStream.flush();
	}

	protected XHttpURLConnection close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				Logger.warning(e.getMessage(), e);
			}
		}
		return this;
	}
}

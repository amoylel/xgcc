package com.xcc.utils;

import java.util.Base64;

public class Base64Util {

	/**
	 * Base64编码
	 * @param data
	 * @return
	 */
	public static String encode(String data) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(data.getBytes());
	}

	/**
	 * Base64解码
	 * @param data
	 * @return
	 */
	public static String decode(String data) {
		Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(data));
	}

	/**
	 * UrlBase64编码
	 * @param data
	 * @return
	 */
	public static String encodeurl(String data) {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		return encoder.encodeToString(data.getBytes());
	}

	/**
	 * UrlBase64解码
	 * @param data
	 * @return
	 */
	public static String decodeurl(String data) {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		return new String(decoder.decode(data));
	}

}

/**
 * Created the com.xsy.utils.HexUtil.java
 * @created 2016年10月30日 下午1:16:52
 * @version 1.0.0
 */
package com.xcc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * com.xsy.utils.HexUtil.java
 * @author XChao
 */
public class HexUtil {

	private HexUtil() {
	}

	private static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException var2) {
			throw new IllegalArgumentException(var2);
		}
	}

	private static byte[] digest(MessageDigest messageDigest, InputStream data) {
		try {
			byte[] buffer = new byte[1024];
			for (int read = data.read(buffer, 0, 1024); read > -1; read = data.read(buffer, 0, 1024)) {
				messageDigest.update(buffer, 0, read);
			}
			return messageDigest.digest();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static MessageDigest getMd2Digest() {
		return getDigest("MD2");
	}

	private static MessageDigest getMd5Digest() {
		return getDigest("MD5");
	}

	private static MessageDigest getSha1Digest() {
		return getDigest("SHA-1");
	}

	private static MessageDigest getSha256Digest() {
		return getDigest("SHA-256");
	}

	private static MessageDigest getSha384Digest() {
		return getDigest("SHA-384");
	}

	private static MessageDigest getSha512Digest() {
		return getDigest("SHA-512");
	}

	public static String toString(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			// 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
			builder.append(String.format("%02X", b));
		}
		return builder.toString();
	}

	public static byte[] md2(byte[] data) {
		return getMd2Digest().digest(data);
	}

	public static byte[] md2(InputStream data) {
		return digest(getMd2Digest(), data);
	}

	public static byte[] md2(String data) {
		return md2(data.getBytes(Charset.forName("UTF-8")));
	}

	public static String md2s(byte[] data) {
		return toString(md2(data));
	}

	public static String md2s(InputStream data) {
		return toString(md2(data));
	}

	public static String md2s(String data) {
		return toString(md2(data));
	}

	public static byte[] md5(byte[] data) {
		return getMd5Digest().digest(data);
	}

	public static byte[] md5(InputStream data) {
		return digest(getMd5Digest(), data);
	}

	public static byte[] md5(String data) {
		return md5(data.getBytes(Charset.forName("UTF-8")));
	}

	public static String md5s(byte[] data) {
		return toString(md5(data));
	}

	public static String md5s(InputStream data) {
		return toString(md5(data));
	}

	public static String md5s(String data) {
		return toString(md5(data));
	}

	public static byte[] sha1(byte[] data) {
		return getSha1Digest().digest(data);
	}

	public static byte[] sha1(InputStream data) {
		return digest(getSha1Digest(), data);
	}

	public static byte[] sha1(String data) {
		return sha1(data.getBytes(Charset.forName("UTF-8")));
	}

	public static String sha1s(byte[] data) {
		return toString(sha1(data));
	}

	public static String sha1s(InputStream data) {
		return toString(sha1(data));
	}

	public static String sha1s(String data) {
		return toString(sha1(data));
	}

	public static byte[] sha256(byte[] data) {
		return getSha256Digest().digest(data);
	}

	public static byte[] sha256(InputStream data) {
		return digest(getSha256Digest(), data);
	}

	public static byte[] sha256(String data) {
		return sha256(data.getBytes(Charset.forName("UTF-8")));
	}

	public static String sha256s(byte[] data) {
		return toString(sha256(data));
	}

	public static String sha256s(InputStream data) {
		return toString(sha256(data));
	}

	public static String sha256s(String data) {
		return toString(sha256(data));
	}

	public static byte[] sha384(byte[] data) {
		return getSha384Digest().digest(data);
	}

	public static byte[] sha384(InputStream data) {
		return digest(getSha384Digest(), data);
	}

	public static byte[] sha384(String data) {
		return sha384(data.getBytes(Charset.forName("UTF-8")));
	}

	public static String sha384s(byte[] data) {
		return toString(sha384(data));
	}

	public static String sha384s(InputStream data) {
		return toString(sha384(data));
	}

	public static String sha384s(String data) {
		return toString(sha384(data));
	}

	public static byte[] sha512(byte[] data) {
		return getSha512Digest().digest(data);
	}

	public static byte[] sha512(InputStream data) {
		return digest(getSha512Digest(), data);
	}

	public static byte[] sha512(String data) {
		return sha512(data.getBytes(Charset.forName("UTF-8")));
	}

	public static String sha512s(byte[] data) {
		return toString(sha512(data));
	}

	public static String sha512s(InputStream data) {
		return toString(sha512(data));
	}

	public static String sha512s(String data) {
		return toString(sha512(data));
	}
}

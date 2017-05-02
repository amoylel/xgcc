package com.xcc.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 文件管理上下文，处理附件图片等
 * @author XChao
 * @since 0.3.0
 */
public class FileGenerator {
	private static final DateFormat FORMAT = new SimpleDateFormat("yyyyMM/dd/HH/");
	private static String privateFilePath = "";
	private static String publicFilePath = "";
	private static String publicFileUrl = "";

	/**
	 * @param privateFilePath the privateFilePath to set
	 */
	public static void setPrivateFilePath(String privateFilePath) {
		FileGenerator.privateFilePath = privateFilePath;
	}

	/**
	 * @param publicFilePath the publicFilePath to set
	 */
	public static void setPublicFilePath(String publicFilePath) {
		FileGenerator.publicFilePath = publicFilePath;
	}

	/**
	 * @param publicFileUrl the publicFileUrl to set
	 */
	public static void setPublicFileUrl(String publicFileUrl) {
		FileGenerator.publicFileUrl = publicFileUrl;
	}

	/**
	 * 获取根据时间缀生成的文件路径在数据库存储的路径部分
	 * @param fileExt 文件后缀带 '.' 比如: (.png, .txt, .zip )
	 * @return
	 */
	public static String getDateDBPath(String fileExt) {
		return FORMAT.format(new Date(System.currentTimeMillis())) + PKGenerator.key() + fileExt;
	}

	/**
	 * 根据主键生成器生成的ID生成文件路径在数据库存储部分
	 * @param key PKGenerator.key() 生成的值
	 * @param fileExt 文件后缀带 '.' 比如: (.png, .txt, .zip )
	 * @return
	 */
	public static String getKeyDBPath(long key, String fileExt) {
		return FORMAT.format(new Date(PKGenerator.millis(key))) + key + fileExt;
	}

	/**
	 * 根据文件在数据库存储的路径,获取私有文件在硬盘或者其它存储中的绝对路径
	 * @param dbPath 数据库路径
	 */
	public static String getPrivateFullPath(String dbPath) {
		File file = new File(privateFilePath + dbPath);
		if(!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
			new RuntimeException("创建文件夹失败: " + file.getParentFile().getAbsolutePath());
		}
		return file.getAbsolutePath();
	}

	/**
	 * 根据文件在数据库存储的路径,获取公共文件在硬盘或者其它存储中的绝对路径
	 * @param dbPath 数据库部分路径
	 */
	public static String getPublicFullPath(String dbPath) {
		File file = new File(publicFilePath + dbPath);
		if(!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
			new RuntimeException("创建文件夹失败: " + file.getParentFile().getAbsolutePath());
		}
		return file.getAbsolutePath();
	}

	/**
	 * 根据文件在数据库存储的路径,获取该文件在网页中访问的绝对路径
	 * @param dbPath
	 * @return
	 */
	public static String getPublicFullUrl(String dbPath) {
		Pattern pattern = Pattern.compile("(\\w+)(://|://)(\\S*)");
		return pattern.matcher(dbPath).matches() ? dbPath : publicFileUrl + dbPath;
	}

	/**
	 * 根据文件名返回文件后缀名
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		return fileName.substring(Math.max(0, fileName.lastIndexOf(".")));
	}

}

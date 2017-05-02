/**
 * Created the com.xsy.web.engine.XVMUtils.java
 * @created 2016年10月22日 下午8:22:30
 * @version 1.0.0
 */
package com.xcc.web.view;

import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcc.utils.DateUtil;
import com.xcc.utils.HashGenerator;
import com.xcc.web.core.XInitContext;

/**
 * com.xsy.web.view.XVMUtils.java
 * @author XChao
 */
public class VMUtils {
	/**
	 * 获取指定长度的字符串（包括汉字长度的处理）
	 * @param value 源字符串
	 * @param length 自定长度
	 * @return 返回一个指定长度的字符串
	 */
	public static String subLength(String value, int length) {
		return subLength(value, length, "...");
	}

	/**
	 * 获取指定长度的字符串（包括汉字长度的处理）
	 * @param value
	 * @param length
	 * @param suff
	 * @return
	 */
	public static String subLength(String value, int length, String suff) {
		if(StringUtils.isEmpty(value)) {
			return value;
		}
		int valueLen = value.length();
		if(StringUtils.isNotEmpty(value) && valueLen > length) {
			int size = length * 2;
			for (int i = 0, count = 0; i < valueLen; i++) {
				char ch = value.charAt(i);
				if(ch >= 0 && ch < 256) {
					count++;
				} else {
					count += 2;
				}
				if(count > size) {
					value = value.substring(0, i);
					if(StringUtils.isNotEmpty(suff)) {
						value = value + suff;
					}
					break;
				}
			}
		}
		return value;
	}

	public static Object get(Object[] objects, int index) {
		return objects[index];
	}

	/**
	 * 格式化距离显示
	 * @param dist 距离 单位米
	 */
	public static String formatdist(double dist) {
		if(dist >= 1000) {
			return String.format("%1$.2f", dist / 1000) + "km";
		}
		return String.format("%1$.1f", dist) + "m";
	}

	/**
	 * 获取两经纬度距离
	 * @param lng1 经度1
	 * @param lat1 纬度1
	 * @param lng2 经度2
	 * @param lat2 纬度2
	 * @return
	 */
	public static String distance(double lng1, double lat1, double lng2, double lat2) {
		return formatdist(HashGenerator.getInstence().distance(lng1, lat1, lng2, lat2));
	}

	/**
	 * 计算位与值
	 * @param value
	 * @param val
	 */
	public static int bitAndValue(int value, int val) {
		return value & val;
	}

	/**
	 * 计算位或值
	 * @param value
	 * @param val
	 * @return
	 */
	public static int bitOrValue(int value, int val) {
		return value | val;
	}

	public static boolean gt(double a, double b) {
		return a > b;
	}

	public static boolean gte(double a, double b) {
		return a >= b;
	}

	public static boolean lt(double a, double b) {
		return a < b;
	}

	public static boolean lte(double a, double b) {
		return a <= b;
	}

	/**
	 * 字符串替换
	 * @param text 原字符串
	 * @param target 想要被替换的字符串
	 * @param replacement 替换的字符串
	 */
	public static String replace(String text, String target, String replacement) {
		return StringUtils.replace(text, target, replacement);
	}

	/**
	 * 将JSON字符串转换成Map
	 * @param jsonString
	 */
	public static JSONObject json2object(String jsonString) {
		return JSON.parseObject(jsonString);
	}

	/**
	 * 将JOSN字符串转换成List
	 * @param jsonString
	 */
	public static JSONArray json2array(String jsonString) {
		return JSON.parseArray(jsonString);
	}

	/**
	 * 将日期格式化成yyyy-MM-dd HH:mm:ss 格式
	 * @param date
	 */
	public static String formatDateTime(Date date) {
		return DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将日期格式化成 yyyy-MM-dd 格式
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return DateUtil.format(date, "yyyy-MM-dd");
	}

	/**
	 * 将日期格式化成指定日期格式
	 * @param date
	 * @param format
	 */
	public static String formatDate(Date date, String format) {
		return DateUtil.format(date, format);
	}

	/**
	 * 将字符串转成URL编码
	 * @param stringContent
	 */
	public static String encode(String stringContent) throws Exception {
		return encode(stringContent, XInitContext.getContext().getEncoding());
	}

	/**
	 * 将字符串转成URL编码
	 * @param stringContent
	 * @param encode 字符串编码格式
	 */
	public static String encode(String stringContent, String encode) throws Exception {
		if(StringUtils.isBlank(stringContent)) {
			return stringContent;
		}
		return URLEncoder.encode(stringContent, encode);
	}
}

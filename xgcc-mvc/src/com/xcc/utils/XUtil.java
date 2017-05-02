/**
 * Created the com.xcc.utils.XUtils.java
 * @created 2016年10月18日 上午11:19:03
 * @version 1.0.0
 */
package com.xcc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * com.xcc.utils.XUtils.java
 * @author XChao
 */
public class XUtil {

	/**
	 * 把传入字符串首字母大写
	 * @param string
	 * @return
	 */
	public static String fristUpperCase(String string) {
		return Character.toUpperCase(string.charAt(0)) + string.substring(1, string.length());
	}

	/**
	 * 把传入字符串首字母小写
	 * @param string
	 * @return
	 */
	public static String fristLowerCase(String string) {
		return Character.toLowerCase(string.charAt(0)) + string.substring(1, string.length());
	}

	/**
	 * 把字符串改写成java的驼峰命名法
	 * @param name 字符串内容， 一般是将单词用"_"连接的字符串
	 * @param fristUpperCase 变成java命名后，首字母是否大写
	 * @return
	 */
	public static String toJavaName(String name, boolean fristUpperCase) {
		String[] strArr = name.split("_");
		StringBuilder newName = new StringBuilder();
		for (int i = 0, len = strArr.length; i < len; i++) {
			if(strArr[i].length() > 0) {
				newName.append(fristUpperCase(strArr[i]));
			}
		}
		if(fristUpperCase == true) {
			return newName.toString();
		}
		return fristLowerCase(newName.toString());
	}

	/**
	 * 把用java驼峰命名法的字符串字符串转换成 用"_"连接的数据库命名
	 * @param fieldName
	 * @return
	 */
	public static String toDBName(String fieldName) {
		StringBuilder result = new StringBuilder();
		char[] fileNameChars = fieldName.toCharArray();
		for (int i = 0, len = fileNameChars.length; i < len; i++) {
			if(Character.isUpperCase(fileNameChars[i]) && i > 0) {
				result.append("_");
			}
			result.append(Character.toLowerCase(fileNameChars[i]));
		}
		return result.toString();
	}

	/**
	 * 将数组类型转换成为List类型
	 * @param array
	 * @param params
	 * @return
	 */
	public static List<Object> array2list(Object[] array, Object... params) {
		List<Object> result = new ArrayList<Object>();
		result.addAll(Arrays.asList(array));
		result.addAll(Arrays.asList(params));
		return result;
	}

	/**
	 * 获取异常的最后一个cause
	 * @param throwable
	 * @return Throwable
	 */
	public static Throwable getLastCause(Throwable throwable) {
		Throwable throwable1;
		do {
			throwable1 = throwable;
			throwable = throwable.getCause();
		} while (throwable != null);
		return throwable1;
	}

	public static <T> T letval(JSONObject object, Class<T> clazz, String key) {
		if(object == null) {
			return null;
		}
		return object.getObject(key, clazz);
	}

	public static <T> T letval(JSONArray array, Class<T> clazz, int index) {
		if(array == null) {
			return null;
		}
		return array.getObject(index, clazz);
	}

	public static JSONObject letobj(JSONObject object, String key) {
		if(object == null) {
			return null;
		}
		return object.getJSONObject(key);
	}

	public static JSONObject letobj(JSONArray object, int index) {
		if(object == null) {
			return null;
		}
		return object.getJSONObject(index);
	}

	public static JSONArray letarr(JSONObject object, String key) {
		if(object == null) {
			return null;
		}
		return object.getJSONArray(key);
	}

	public static JSONArray letarr(JSONArray object, int index) {
		if(object == null) {
			return null;
		}
		return object.getJSONArray(index);
	}
}

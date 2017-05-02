/**
 * Created the com.xcc.utils.StringUtil.java
 * @created 2017年2月23日 下午5:01:55
 * @version 1.0.0
 */
package com.xcc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.xcc.utils.StringUtil.java
 * @author XChao
 */
public class StringUtil {
	/**
	 * 字符串替换的回调接口
	 * @author XChao
	 */
	public static interface XReplace {
		/**
		 * 将text转化为特定的字串返回
		 * @param text 指定的字符串
		 * @param index 替换的次序
		 * @param matcher Matcher对象
		 * @return
		 */
		public String replace(String text, int index, Matcher matcher);
	}

	/**
	 * 将String中的所有regex匹配的字串全部替换掉
	 * @param string 代替换的字符串
	 * @param regex 替换查找的正则表达式
	 * @param replacement 替换函数
	 * @return
	 */
	public static String replaceAll(String string, String regex, XReplace replacement) {
		return replaceAll(string, Pattern.compile(regex), replacement);
	}

	/**
	 * 将String中的所有pattern匹配的字串替换掉
	 * @param string 代替换的字符串
	 * @param pattern 替换查找的正则表达式对象
	 * @param replacement 替换函数
	 * @return
	 */
	public static String replaceAll(String string, Pattern pattern, XReplace replacement) {
		if (string == null) {
			return null;
		}
		Matcher matcher = pattern.matcher(string);
		StringBuffer buffer = new StringBuffer();
		int index = 0;
		while (matcher.find()) {
			matcher.appendReplacement(buffer, replacement.replace(matcher.group(0), index++, matcher));
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	/**
	 * 将String中的regex第一次匹配的字串替换掉
	 * @param string 代替换的字符串
	 * @param regex 替换查找的正则表达式
	 * @param replacement 替换函数
	 * @return
	 */
	public static String replaceFirst(String string, String regex, XReplace replacement) {
		return replaceFirst(string, Pattern.compile(regex), replacement);
	}

	/**
	 * 将String中的pattern第一次匹配的字串替换掉
	 * @param string 代替换的字符串
	 * @param pattern 替换查找的正则表达式对象
	 * @param replacement 替换函数
	 * @return
	 */
	public static String replaceFirst(String string, Pattern pattern, XReplace replacement) {
		if (string == null) {
			return null;
		}
		Matcher matcher = pattern.matcher(string);
		StringBuffer buffer = new StringBuffer();
		if (matcher.find()) {
			matcher.appendReplacement(buffer, replacement.replace(matcher.group(0), 0, matcher));
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}
}

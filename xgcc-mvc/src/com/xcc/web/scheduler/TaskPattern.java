/**
 * Created the com.xsy.web.jobtask.XTaskPar.java
 * @created 2016年10月9日 上午11:10:52
 * @version 1.0.0
 */
package com.xcc.web.scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

import com.xcc.web.scheduler.matcher.ITaskMatcher;
import com.xcc.web.scheduler.matcher.TaskAlwaysMatcher;
import com.xcc.web.scheduler.matcher.TaskDayOfMonthMatcher;
import com.xcc.web.scheduler.matcher.TaskIntegerMatcher;
import com.xcc.web.scheduler.parser.ITaskParser;
import com.xcc.web.scheduler.parser.TaskDayOfMonthParser;
import com.xcc.web.scheduler.parser.TaskDayOfWeekParser;
import com.xcc.web.scheduler.parser.TaskHourParser;
import com.xcc.web.scheduler.parser.TaskMinuteParser;
import com.xcc.web.scheduler.parser.TaskMonthParser;

/**
 * com.xsy.web.jobtask.XTaskPar.java
 * @author XChao
 */
public class TaskPattern {
	private static final ITaskParser MINUTE_VALUE_PARSER = new TaskMinuteParser();
	private static final ITaskParser HOUR_VALUE_PARSER = new TaskHourParser();
	private static final ITaskParser DAY_OF_MONTH_VALUE_PARSER = new TaskDayOfMonthParser();
	private static final ITaskParser MONTH_VALUE_PARSER = new TaskMonthParser();
	private static final ITaskParser DAY_OF_WEEK_VALUE_PARSER = new TaskDayOfWeekParser();

	protected ITaskMatcher minuteMatcher;
	protected ITaskMatcher hourMatcher;
	protected ITaskMatcher dayOfMonthMatcher;
	protected ITaskMatcher monthMatcher;
	protected ITaskMatcher dayOfWeekMatcher;
	private String schedulingPattern;

	protected TaskPattern(String pattern) {
		this.schedulingPattern = pattern;
		StringTokenizer patternTokenizer = new StringTokenizer(pattern, " \t");
		if (patternTokenizer.countTokens() != 5) {
			throw new RuntimeException("invalid pattern: \"" + pattern + "\"");
		}
		try {
			this.minuteMatcher = buildValueMatcher(patternTokenizer.nextToken(), MINUTE_VALUE_PARSER);
		} catch (Exception e) {
			throw new RuntimeException("invalid pattern \"" + pattern + "\". Error parsing minutes field: " + e.getMessage() + ".");
		}
		try {
			this.hourMatcher = buildValueMatcher(patternTokenizer.nextToken(), HOUR_VALUE_PARSER);
		} catch (Exception e) {
			throw new RuntimeException("invalid pattern \"" + pattern + "\". Error parsing hours field: " + e.getMessage() + ".");
		}
		try {
			this.dayOfMonthMatcher = buildValueMatcher(patternTokenizer.nextToken(), DAY_OF_MONTH_VALUE_PARSER);
		} catch (Exception e) {
			throw new RuntimeException("invalid pattern \"" + pattern + "\". Error parsing days of month field: " + e.getMessage() + ".");
		}
		try {
			this.monthMatcher = buildValueMatcher(patternTokenizer.nextToken(), MONTH_VALUE_PARSER);
		} catch (Exception e) {
			throw new RuntimeException("invalid pattern \"" + pattern + "\". Error parsing months field: " + e.getMessage() + ".");
		}
		try {
			this.dayOfWeekMatcher = buildValueMatcher(patternTokenizer.nextToken(), DAY_OF_WEEK_VALUE_PARSER);
		} catch (Exception e) {
			throw new RuntimeException("invalid pattern \"" + pattern + "\". Error parsing days of week field: " + e.getMessage() + ".");
		}
	}

	private ITaskMatcher buildValueMatcher(String pattern, ITaskParser parser) throws Exception {
		if (pattern.length() == 1 && pattern.equals("*")) {
			return new TaskAlwaysMatcher();
		}
		StringTokenizer patternTokanizer = new StringTokenizer(pattern, "/");
		int countTokens = patternTokanizer.countTokens();
		if (countTokens < 1 || countTokens > 2) {
			throw new Exception("invalid field \"" + pattern + "\". ");
		}
		List<Integer> values = null;
		String patternV1String = patternTokanizer.nextToken();
		values = this.buildValueMatcherRange(patternV1String, parser);
		if (countTokens == 2) {
			String patternV2String = patternTokanizer.nextToken();
			int patternV2Integer = -1;
			try {
				patternV2Integer = parser.parse(patternV2String);
			} catch (Exception e) {
				throw new Exception("invalid value \"" + patternV2String + "\", " + e.getMessage());
			}
			if (patternV2Integer < 1) {
				throw new Exception("non positive divisor \"" + patternV2Integer + "\"");
			}
			List<Integer> values2 = new ArrayList<Integer>();
			for (int i = 0; i < values.size(); i += patternV2Integer) {
				values2.add(values.get(i));
			}
			values = values2;
		}
		try {
			if (values == null || values.size() == 0) {
				throw new Exception("invalid field \"" + pattern + "\"");
			}
			if (parser == DAY_OF_MONTH_VALUE_PARSER) {
				return new TaskDayOfMonthMatcher(values);
			} else {
				return new TaskIntegerMatcher(values);
			}
		} catch (Exception e) {
			throw new Exception("invalid field \"" + pattern + "\"," + e.getMessage());
		}
	}

	private List<Integer> buildValueMatcherRange(String pattern, ITaskParser parser) throws Exception {
		if (pattern.equals("*")) {
			int min = parser.getMinValue();
			int max = parser.getMaxValue();
			List<Integer> values = new ArrayList<Integer>();
			for (int i = min; i <= max; i++) {
				values.add(Integer.valueOf(i));
			}
			return values;
		}
		StringTokenizer patternTokanizer = new StringTokenizer(pattern, "-");
		int countTokens = patternTokanizer.countTokens();
		if (countTokens < 1 || countTokens > 2) {
			throw new Exception("invalid field \"" + pattern + "\". ");
		}
		String patternV1String = patternTokanizer.nextToken();
		int patternV1Integer = -1;
		try {
			patternV1Integer = parser.parse(patternV1String);
		} catch (Exception e) {
			throw new Exception("invalid value \"" + patternV1String + "\", " + e.getMessage());
		}
		if (countTokens == 1) {
			List<Integer> values = new ArrayList<Integer>();
			values.add(patternV1Integer);
			return values;
		}
		String patternV2String = patternTokanizer.nextToken();
		int patternV2Integer = -1;
		try {
			patternV2Integer = parser.parse(patternV2String);
		} catch (Exception e) {
			throw new Exception("invalid value \"" + patternV2String + "\", " + e.getMessage());
		}
		List<Integer> values = new ArrayList<Integer>();
		if (patternV1Integer < patternV2Integer) {
			for (int i = patternV1Integer; i <= patternV2Integer; i++) {
				values.add(Integer.valueOf(i));
			}
		} else if (patternV1Integer > patternV2Integer) {
			for (int i = patternV1Integer; i <= parser.getMaxValue(); i++) {
				values.add(Integer.valueOf(i));
			}
			for (int i = parser.getMinValue(); i <= patternV2Integer; i++) {
				values.add(Integer.valueOf(i));
			}
		} else {
			values.add(Integer.valueOf(patternV1Integer));
		}
		return values;

	}

	public boolean match(TimeZone timezone, long millis) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(millis);
		gc.setTimeZone(timezone);
		int minute = gc.get(Calendar.MINUTE);
		int hour = gc.get(Calendar.HOUR_OF_DAY);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		int month = gc.get(Calendar.MONTH) + 1;
		int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK) - 1;
		int year = gc.get(Calendar.YEAR);
		boolean result = minuteMatcher.match(minute) && hourMatcher.match(hour);
		if (dayOfMonthMatcher instanceof TaskDayOfMonthMatcher) {
			result = result && ((TaskDayOfMonthMatcher) dayOfMonthMatcher).match(dayOfMonth, month, gc.isLeapYear(year));
		} else {
			result = result && dayOfMonthMatcher.match(dayOfMonth);
		}
		return result && monthMatcher.match(month) && dayOfWeekMatcher.match(dayOfWeek);
	}

	public boolean match(long millis) {
		return match(TimeZone.getDefault(), millis);
	}

	public String toString() {
		return this.schedulingPattern;
	}
}

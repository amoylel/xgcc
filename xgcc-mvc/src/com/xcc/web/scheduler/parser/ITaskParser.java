/**
 * Created the com.xsy.web.jobtask.parser.XITaskParser.java
 * @created 2016年10月17日 下午5:01:31
 * @version 1.0.0
 */
package com.xcc.web.scheduler.parser;

/**
 * com.xsy.web.jobtask.parser.XITaskParser.java
 * @author XChao
 */
public interface ITaskParser {
	public int parse(String value) throws Exception;

	public int getMinValue();

	public int getMaxValue();
}

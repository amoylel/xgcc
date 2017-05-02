/**   
 * Created the com.xsy.web.core.XIPorxy.java
 * @created 2016年9月29日 上午10:55:29 
 * @version 1.0.0 
 */
package com.xcc.web.core;


import java.lang.reflect.Method;

/**
 * com.xsy.web.core.XIPorxy.java
 * @author XChao
 */
public interface IPorxy {
	
	public String getName();

	public IPorxy setName(String name);

	public Object getInstence();

	public IPorxy setInstence(Object instence);

	public Method getMethod();

	public IPorxy setMethod(Method method);

	public String execute(Object... args) throws Exception;
}

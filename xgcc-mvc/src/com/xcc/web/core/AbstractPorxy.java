/**
 * Created the com.xsy.web.core.XAbstractPorxy.java
 * @created 2016年9月27日 下午6:29:10
 * @version 1.0.0
 */
package com.xcc.web.core;

import java.lang.reflect.Method;

/**
 * com.xsy.web.core.XAbstractPorxy.java
 * @author XChao
 */
public abstract class AbstractPorxy implements IPorxy {

	private String name;
	private Object instence;
	private Method method;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public IPorxy setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the instence
	 */
	public Object getInstence() {
		return instence;
	}

	/**
	 * @param instence the instence to set
	 */
	public IPorxy setInstence(Object instence) {
		this.instence = instence;
		return this;
	}

	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public IPorxy setMethod(Method method) {
		this.method = method;
		return this;
	}

	@Override
	public String execute(Object... args) throws Exception {
		Object result = method.invoke(instence, args);
		if (result != null && String.class.isInstance(result)) {
			return String.valueOf(result);
		}
		return null;
	}
}

/**
 * Created the com.xcc.web.entity.IMenu.java
 * @created 2017年2月3日 下午4:03:02
 * @version 1.0.0
 */
package com.xcc.web.entity;

import java.util.Map;

/**
 * com.xcc.web.entity.IMenu.java
 * @author XChao
 */
public interface IMenu {

	/**
	 * @return the id
	 */
	public int getId();

	/**
	 * @param id the id to set
	 */
	public IMenu setId(int id);

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @param name the name to set
	 */
	public IMenu setName(String name);

	/**
	 * @return the url
	 */
	public String getUrl();

	/**
	 * @param url the url to set
	 */
	public IMenu setUrl(String url);

	/**
	 * @return the type
	 */
	public int getType();

	/**
	 * @param type the type to set
	 */
	public IMenu setType(int type);

	/**
	 * @return the value
	 */
	public int getValue();

	/**
	 * @param value the value to set
	 */
	public IMenu setValue(int value);

	/**
	 * @return the parentId
	 */
	public int getParentId();

	/**
	 * @param parentId the parentId to set
	 */
	public IMenu setParentId(int parentId);

	/**
	 * @return the parentName
	 */
	public String getParentName();

	/**
	 * @param parentName the parentName to set
	 */
	public IMenu setParentName(String parentName);

	/**
	 * @return the childs
	 */
	public Map<Integer, String> getChilds();

	/**
	 * 添加一个功能点
	 * @param value
	 * @param remaker
	 */
	public IMenu addChild(Integer value, String remaker);
}

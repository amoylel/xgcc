/**
 * Created the com.xcc.web.entity.IRole.java
 * @created 2017年2月3日 上午10:51:10
 * @version 1.0.0
 */
package com.xcc.web.entity;

/**
 * --系统角色信息 <br/>
 * @author XChao
 */
public interface IRole {

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public IRole setId(long id);

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @param name the name to set
	 */
	public IRole setName(String name);

	/**
	 * @return the disc
	 */
	public String getDisc();

	/**
	 * @param disc the disc to set
	 */
	public IRole setDisc(String disc);

	/**
	 * @return the type
	 */
	public int getType();

	/**
	 * @param type the type to set
	 */
	public IRole setType(int type);

}

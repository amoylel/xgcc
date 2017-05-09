/**
 * Created com.xcc.web.entity.IRoleMenu.java
 * @created 2017年2月3日 上午10:53:47
 * @version 1.0.0
 */
package com.xcc.web.entity;

/**
 * -- 角色权限表 <br/>
 * @author XChao
 */
public interface IRoleMenu {

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public IRoleMenu setId(long id);

	/**
	 * @return the roleId
	 */
	public long getRoleId();

	/**
	 * @param roleId the roleId to set
	 */
	public IRoleMenu setRoleId(long roleId);

	/**
	 * @return the menuId
	 */
	public long getMenuId();

	/**
	 * @param menuId the menuId to set
	 */
	public IRoleMenu setMenuId(long menuId);

	/**
	 * @return the value
	 */
	public int getValue();

	/**
	 * @param value the value to set
	 */
	public IRoleMenu setValue(int value);
}

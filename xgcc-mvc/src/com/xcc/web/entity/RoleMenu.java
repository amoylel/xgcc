/**
 * Created the com.xcc.web.permiss.XRoleMenu.java
 * @created 2017年2月3日 上午10:53:47
 * @version 1.0.0
 */
package com.xcc.web.entity;

import com.xcc.db.model.DBField;
import com.xcc.db.model.DBModel;

/**
 * -- 角色权限表 <br/>
 * @author XChao
 */
public class RoleMenu extends DBModel<RoleMenu> {
	private static final long serialVersionUID = 7162386029244309516L;
	// 菜单角色关系ID
	@DBField("rm_id")
	private long id;
	// 菜单角色关系 角色ID
	@DBField("rm_role_id")
	private long roleId;
	// 菜单角色关系 菜单ID
	@DBField("rm_menu_id")
	private long menuId;
	// 菜单角色关系， 角色拥有菜单权限
	@DBField("rm_value")
	private int value;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the roleId
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the menuId
	 */
	public long getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
}

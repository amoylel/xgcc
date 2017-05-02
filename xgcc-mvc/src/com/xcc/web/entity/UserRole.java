/**
 * Created the com.xcc.web.permiss.XUserRole.java
 * @created 2017年2月3日 上午10:53:58
 * @version 1.0.0
 */
package com.xcc.web.entity;

import com.xcc.db.model.DBField;
import com.xcc.db.model.DBModel;

/**
 * -- 用户角色关系表 <br/>
 * @author XChao
 */
public class UserRole extends DBModel<UserRole> {
	private static final long serialVersionUID = -6845411052033257124L;
	// 用户角色关系ID
	@DBField("ur_id")
	private long id;
	// 用户角色关系角色ID
	@DBField("ur_role_id")
	private long roleId;
	// 用户角色关系 用户ID
	@DBField("ur_user_id")
	private long userId;

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
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

}

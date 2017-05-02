/**
 * Created the com.xcc.web.permiss.XRole.java
 * @created 2017年2月3日 上午10:51:10
 * @version 1.0.0
 */
package com.xcc.web.entity;

import com.xcc.db.model.DBField;
import com.xcc.db.model.DBModel;

/**
 * --系统角色信息 <br/>
 * @author XChao
 */
public class Role extends DBModel<Role> {
	private static final long serialVersionUID = 3116914847674626028L;
	// 角色ID
	@DBField("role_id")
	private long id;
	// 角色名称
	@DBField("role_name")
	private String name;
	// 角色说明
	@DBField("role_disc")
	private String disc;
	// 角色分类
	@DBField("role_type")
	private int type;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the disc
	 */
	public String getDisc() {
		return disc;
	}

	/**
	 * @param disc the disc to set
	 */
	public void setDisc(String disc) {
		this.disc = disc;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}

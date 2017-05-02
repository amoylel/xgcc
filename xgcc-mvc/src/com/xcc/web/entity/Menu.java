/**
 * Created the com.xcc.web.entity.XMenu.java
 * @created 2017年2月3日 下午4:03:02
 * @version 1.0.0
 */
package com.xcc.web.entity;

import java.util.HashMap;
import java.util.Map;

import com.xcc.db.model.DBField;
import com.xcc.db.model.DBModel;

/**
 * com.xcc.web.entity.XMenu.java
 * @author XChao
 */
public class Menu extends DBModel<Menu> {
	private static final long serialVersionUID = -3237623902943378222L;
	// 菜单ID
	@DBField("menu_id")
	private int id;
	// 菜单名称
	@DBField("menu_name")
	private String name;
	// 菜单Url
	@DBField("menu_url")
	private String url;
	// 菜单分类
	@DBField("menu_type")
	private int type;
	// 菜单权限
	@DBField("menu_value")
	private int value;
	// 上级菜单ID
	@DBField("menu_parent_id")
	private int parentId;
	// 上级菜单名称
	@DBField("menu_parent_name")
	private String parentName;
	// 资源列表
	private final Map<Integer, String> childs = new HashMap<Integer, String>();

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * @return the childs
	 */
	public Map<Integer, String> getChilds() {
		return childs;
	}

	public void addChild(Integer value, String remaker) {
		this.childs.put(value, remaker);
	}
}

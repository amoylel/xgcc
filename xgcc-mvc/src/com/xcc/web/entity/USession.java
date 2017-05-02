/**
 * Created the com.xcc.web.entity.USession.java
 * @created 2016年9月29日 上午10:56:39
 * @version 1.0.0
 */
package com.xcc.web.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xcc.db.model.DBField;
import com.xcc.db.model.DBModel;
import com.xcc.utils.Base64Util;
import com.xcc.utils.PKGenerator;

/**
 * com.xcc.web.entity.USession.java
 * @author XChao
 */
public class USession extends DBModel<USession> {
	private static final long serialVersionUID = 4433696413143632427L;

	public static final String XUSESSION_KEY = "SESSION_XUSESSION_KEY";

	// 用户ID
	@DBField("user_id")
	private long id;
	// 用户名
	@DBField("user_name")
	private String name;
	// 用户昵称
	@DBField("user_nick")
	private String nick;
	// 用户真实姓名
	@DBField("user_real_name")
	private String realName;
	// 用户密码
	@DBField("user_password")
	private String password;
	// 用户密码种子
	@DBField("user_seed")
	private String seed;
	// 用户头像地址
	@DBField("user_head")
	private String head;
	// 用户邮箱地址
	@DBField("user_email")
	private String email;
	// 用户手机号码/电话号码
	private String phone;
	// 用户证件号码
	@DBField("user_idcard")
	private String idcard;
	// 用户性别 0:未设置, 1:男, 2:女
	@DBField("user_gender")
	private int gender;
	// 用户生日
	@DBField("user_birthday")
	private Date birthday = new Date();
	// 用户所在地址
	@DBField("user_address")
	private String address;
	// 用户个性签名
	@DBField("user_signature")
	private String signature;
	// 用户所有经度
	@DBField("user_lng")
	private double lng;
	// 用户所在纬度
	@DBField("user_lat")
	private double lat;
	// 用户状态(认证)1,2,4,8码, 1: 邮箱已认证, 2:手机已认证, 4: 已实名认证.., 16:已禁用, 32:已删除
	@DBField("user_status")
	private int status = 0;
	// 用户token
	@DBField("user_token")
	private String token;
	// 用户创建时间
	@DBField("user_create")
	private Date create = new Date();
	// 用户信息最后修改时间(token更新时间)
	@DBField("user_update")
	private Date update = new Date();
	// 用户所拥有角色
	private final List<Role> roles = new ArrayList<>();

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public USession setId(long id) {
		this.id = id;
		return this;
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
	public USession setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick the nick to set
	 */
	public USession setNick(String nick) {
		this.nick = nick;
		return this;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public USession setRealName(String realName) {
		this.realName = realName;
		return this;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public USession setPassword(String password) {
		this.password = password;
		return this;
	}

	/**
	 * @return the seed
	 */
	public String getSeed() {
		return seed;
	}

	/**
	 * @param seed the seed to set
	 */
	public USession setSeed(String seed) {
		this.seed = seed;
		return this;
	}

	/**
	 * @return the head
	 */
	public String getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public USession setHead(String head) {
		this.head = head;
		return this;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public USession setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public USession setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * @return the idcard
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * @param idcard the idcard to set
	 */
	public USession setIdcard(String idcard) {
		this.idcard = idcard;
		return this;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public USession setGender(int gender) {
		this.gender = gender;
		return this;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public USession setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public USession setAddress(String address) {
		this.address = address;
		return this;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public USession setSignature(String signature) {
		this.signature = signature;
		return this;
	}

	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public USession setLng(double lng) {
		this.lng = lng;
		return this;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public USession setLat(double lat) {
		this.lat = lat;
		return this;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public USession setStatus(int status) {
		this.status = status;
		return this;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public USession setToken(String token) {
		this.token = token;
		return this;
	}

	/**
	 * @return the create
	 */
	public Date getCreate() {
		return create;
	}

	/**
	 * @param create the create to set
	 */
	public USession setCreate(Date create) {
		this.create = create;
		return this;
	}

	/**
	 * @return the update
	 */
	public Date getUpdate() {
		return update;
	}

	/**
	 * @param update the update to set
	 */
	public USession setUpdate(Date update) {
		this.update = update;
		return this;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * 添加用户角色
	 * @param roles
	 * @return
	 */
	public USession addRoles(List<Role> roles) {
		this.roles.addAll(roles);
		return this;
	}

	/**
	 * 添加一个用户角色
	 * @param role
	 * @return
	 */
	public USession addRole(Role role) {
		this.roles.add(role);
		return this;
	}

	/**
	 * 邮箱是否认证
	 * @return
	 */
	public boolean isAuthEmail() {
		return (this.status & 1) == 1;
	}

	/**
	 * 电话是否认证
	 * @return
	 */
	public boolean isAuthPhone() {
		return (this.status & 2) == 2;
	}

	/**
	 * 证件号是否认证(是否实名认证)
	 * @return
	 */
	public boolean isAuthIdcard() {
		return (this.status & 4) == 4;
	}

	/**
	 * 用户是否为禁用状态
	 * @return
	 */
	public boolean isDisable() {
		return (this.status & 16) == 16;
	}

	/**
	 * 用户是否为删除状态
	 * @return
	 */
	public boolean isDelete() {
		return (this.status & 32) == 32;
	}

	/**
	 * 重新创建用户token
	 * @param uid
	 * @return
	 */
	public static String newToken(long uid) {
		return Base64Util.encode(PKGenerator.genseed(6) + uid);
	}

	/**
	 * 解码token 获取用户uid
	 * @param token
	 * @return
	 */
	public static long decodeToken(String token) {
		return Long.valueOf(Base64Util.decode(token).substring(6));
	}
}

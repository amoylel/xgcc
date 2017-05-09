/**
 * Created the com.xcc.web.entity.IUser.java
 * @created 2016年9月29日 上午10:56:39
 * @version 1.0.0
 */
package com.xcc.web.entity;

import java.util.Date;
import java.util.List;

import com.xcc.utils.Base64Util;
import com.xcc.utils.PKGenerator;

/**
 * com.xcc.web.entity.IUser.java
 * @author XChao
 */
public interface IUser {

	public static final String IUSER_KEY = "IUSER_SESSION_KEY";

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public IUser setId(long id);

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @param name the name to set
	 */
	public IUser setName(String name);

	/**
	 * @return the nick
	 */
	public String getNick();

	/**
	 * @param nick the nick to set
	 */
	public IUser setNick(String nick);

	/**
	 * @return the realName
	 */
	public String getRealName();

	/**
	 * @param realName the realName to set
	 */
	public IUser setRealName(String realName);

	/**
	 * @return the password
	 */
	public String getPassword();

	/**
	 * @param password the password to set
	 */
	public IUser setPassword(String password);

	/**
	 * @return the seed
	 */
	public String getSeed();

	/**
	 * @param seed the seed to set
	 */
	public IUser setSeed(String seed);

	/**
	 * @return the head
	 */
	public String getHead();

	/**
	 * @param head the head to set
	 */
	public IUser setHead(String head);

	/**
	 * @return the email
	 */
	public String getEmail();

	/**
	 * @param email the email to set
	 */
	public IUser setEmail(String email);

	/**
	 * @return the phone
	 */
	public String getPhone();

	/**
	 * @param phone the phone to set
	 */
	public IUser setPhone(String phone);

	/**
	 * @return the idcard
	 */
	public String getIdcard();

	/**
	 * @param idcard the idcard to set
	 */
	public IUser setIdcard(String idcard);

	/**
	 * @return the gender
	 */
	public int getGender();

	/**
	 * @param gender the gender to set
	 */
	public IUser setGender(int gender);

	/**
	 * @return the birthday
	 */
	public Date getBirthday();

	/**
	 * @param birthday the birthday to set
	 */
	public IUser setBirthday(Date birthday);

	/**
	 * @return the address
	 */
	public String getAddress();

	/**
	 * @param address the address to set
	 */
	public IUser setAddress(String address);

	/**
	 * @return the signature
	 */
	public String getSignature();

	/**
	 * @param signature the signature to set
	 */
	public IUser setSignature(String signature);

	/**
	 * @return the lng
	 */
	public double getLng();

	/**
	 * @param lng the lng to set
	 */
	public IUser setLng(double lng);

	/**
	 * @return the lat
	 */
	public double getLat();

	/**
	 * @param lat the lat to set
	 */
	public IUser setLat(double lat);

	/**
	 * @return the status
	 */
	public int getStatus();

	/**
	 * @param status the status to set
	 */
	public IUser setStatus(int status);

	/**
	 * @return the token
	 */
	public String getToken();

	/**
	 * @param token the token to set
	 */
	public IUser setToken(String token);

	/**
	 * @return the create
	 */
	public Date getCreate();

	/**
	 * @param create the create to set
	 */
	public IUser setCreate(Date create);

	/**
	 * @return the update
	 */
	public Date getUpdate();

	/**
	 * @param update the update to set
	 */
	public IUser setUpdate(Date update);

	/**
	 * @return the roles
	 */
	public List<IRole> getRoles();

	/**
	 * 添加一个用户角色
	 * @param role
	 * @return
	 */
	public IUser addRole(IRole role);

	/**
	 * 邮箱是否认证
	 * @return
	 */
	default boolean isAuthEmail() {
		return (this.getStatus() & 1) == 1;
	}

	/**
	 * 电话是否认证
	 * @return
	 */
	default boolean isAuthPhone() {
		return (this.getStatus() & 2) == 2;
	}

	/**
	 * 证件号是否认证(是否实名认证)
	 * @return
	 */
	default boolean isAuthIdcard() {
		return (this.getStatus() & 4) == 4;
	}

	/**
	 * 用户是否为禁用状态
	 * @return
	 */
	default boolean isDisable() {
		return (this.getStatus() & 16) == 16;
	}

	/**
	 * 用户是否为删除状态
	 * @return
	 */
	default boolean isDelete() {
		return (this.getStatus() & 32) == 32;
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

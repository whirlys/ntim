package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private Integer userId;

	private String account;

	private String password;

	private String username;

	private Boolean isStaff;

	private Boolean isActive;

	private String role;

	private String avatar;

	private String signature;

	private Date createtime;

	private Date lastLogin;

	private static final long serialVersionUID = 1L;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public Boolean getIsStaff() {
		return isStaff;
	}

	public void setIsStaff(Boolean isStaff) {
		this.isStaff = isStaff;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role == null ? null : role.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature == null ? null : signature.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account + ", password=" + password + ", username=" + username
				+ ", isStaff=" + isStaff + ", isActive=" + isActive + ", role=" + role + ", avatar=" + avatar
				+ ", signature=" + signature + ", createtime=" + createtime + ", lastLogin=" + lastLogin + "]";
	}

	public User(String account, String password, String username, Boolean isStaff, Boolean isActive, String role,
			String avatar, String signature) {
		super();
		this.account = account;
		this.password = password;
		this.username = username;
		this.isStaff = isStaff;
		this.isActive = isActive;
		this.role = role;
		this.avatar = avatar;
		this.signature = signature;
	}

	public User() {
		super();
	}

	public static User createDefault() {
		User user = new User();
		user.setPassword("123456");
		user.setIsActive(true);
		user.setIsStaff(false);
		user.setAvatar("/ntim/images/avatar/user/1.jpg");
		user.setSignature("生活就是一直在路上，不停地在前行，不停地摸索");
		user.setRole("STU");
		return user;
	}

}
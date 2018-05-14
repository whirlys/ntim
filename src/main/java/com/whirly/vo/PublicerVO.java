package com.whirly.vo;

import java.util.Date;

public class PublicerVO {
	private Integer publicerId;

	private Integer userId;

	private Integer deparmentId;

	private String title;

	private String office;

	private String duty;

	private String introduction;

	private String email;

	private String account;

	private String username;

	private Boolean isStaff;

	private Boolean isActive;

	private String role;

	private String avatar;

	private String signature;

	private Date createtime;

	private Date lastLogin;

	private String departmentName;

	public PublicerVO(Integer publicerId, Integer userId, Integer deparmentId, String title, String office, String duty,
			String introduction, String email, String account, String username, Boolean isStaff, Boolean isActive,
			String role, String avatar, String signature, Date createtime, Date lastLogin, String departmentName) {
		super();
		this.publicerId = publicerId;
		this.userId = userId;
		this.deparmentId = deparmentId;
		this.title = title;
		this.office = office;
		this.duty = duty;
		this.introduction = introduction;
		this.email = email;
		this.account = account;
		this.username = username;
		this.isStaff = isStaff;
		this.isActive = isActive;
		this.role = role;
		this.avatar = avatar;
		this.signature = signature;
		this.createtime = createtime;
		this.lastLogin = lastLogin;
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "PublicerVO [publicerId=" + publicerId + ", userId=" + userId + ", deparmentId=" + deparmentId
				+ ", title=" + title + ", office=" + office + ", duty=" + duty + ", introduction=" + introduction
				+ ", email=" + email + ", account=" + account + ", username=" + username + ", isStaff=" + isStaff
				+ ", isActive=" + isActive + ", role=" + role + ", avatar=" + avatar + ", signature=" + signature
				+ ", createtime=" + createtime + ", lastLogin=" + lastLogin + ", departmentName=" + departmentName
				+ "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PublicerVO() {
		super();
	}

	public Integer getPublicerId() {
		return publicerId;
	}

	public void setPublicerId(Integer publicerId) {
		this.publicerId = publicerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeparmentId() {
		return deparmentId;
	}

	public void setDeparmentId(Integer deparmentId) {
		this.deparmentId = deparmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		this.role = role;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}

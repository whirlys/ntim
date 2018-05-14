package com.whirly.form;

public class PublicerForm {
	private Integer publicerId;

	private String account;

	private String password;

	private String username;

	private String email;

	private Integer departmentId;

	private String title;

	private String office;

	private String duty;

	private String introduction;

	private Boolean isActive;

	public Integer getPublicerId() {
		return publicerId;
	}

	public void setPublicerId(Integer publicerId) {
		this.publicerId = publicerId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "PublicerForm [publicerId=" + publicerId + ", account=" + account + ", password=" + password
				+ ", username=" + username + ", email=" + email + ", departmentId=" + departmentId + ", title=" + title
				+ ", office=" + office + ", duty=" + duty + ", introduction=" + introduction + ", isActive=" + isActive
				+ "]";
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}

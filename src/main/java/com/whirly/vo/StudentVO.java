package com.whirly.vo;

import java.util.Date;

public class StudentVO {
	private Integer studentId;

	private Integer userId;

	private Integer classId;

	private Boolean sex;

	private Date birthday;

	private String studentType;

	private String longPhone;

	private String shortPhone;

	private String duty;

	private String familyPhone;

	private String email;

	private String qq;

	private String idNumber;

	private String homeAddress;

	private String account;

	private String username;

	private Boolean isActive;

	private String role;

	private String avatar;

	private String signature;

	private Date createtime;

	private Date lastLogin;

	private Integer departmentId;

	private String profession;

	private Integer grade;

	private Integer classNumber;

	private String departmentName;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public String getLongPhone() {
		return longPhone;
	}

	public void setLongPhone(String longPhone) {
		this.longPhone = longPhone;
	}

	public String getShortPhone() {
		return shortPhone;
	}

	public void setShortPhone(String shortPhone) {
		this.shortPhone = shortPhone;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(Integer classNumber) {
		this.classNumber = classNumber;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public StudentVO(Integer studentId, Integer userId, Integer classId, Boolean sex, Date birthday, String studentType,
			String longPhone, String shortPhone, String duty, String familyPhone, String email, String qq,
			String idNumber, String homeAddress, String account, String username, Boolean isActive, String role,
			String avatar, String signature, Date createtime, Date lastLogin, Integer departmentId, String profession,
			Integer grade, Integer classNumber, String departmentName) {
		super();
		this.studentId = studentId;
		this.userId = userId;
		this.classId = classId;
		this.sex = sex;
		this.birthday = birthday;
		this.studentType = studentType;
		this.longPhone = longPhone;
		this.shortPhone = shortPhone;
		this.duty = duty;
		this.familyPhone = familyPhone;
		this.email = email;
		this.qq = qq;
		this.idNumber = idNumber;
		this.homeAddress = homeAddress;
		this.account = account;
		this.username = username;
		this.isActive = isActive;
		this.role = role;
		this.avatar = avatar;
		this.signature = signature;
		this.createtime = createtime;
		this.lastLogin = lastLogin;
		this.departmentId = departmentId;
		this.profession = profession;
		this.grade = grade;
		this.classNumber = classNumber;
		this.departmentName = departmentName;
	}

	public StudentVO() {
		super();
	}

	@Override
	public String toString() {
		return "StudentVO [studentId=" + studentId + ", userId=" + userId + ", classId=" + classId + ", sex=" + sex
				+ ", birthday=" + birthday + ", studentType=" + studentType + ", longPhone=" + longPhone
				+ ", shortPhone=" + shortPhone + ", duty=" + duty + ", familyPhone=" + familyPhone + ", email=" + email
				+ ", qq=" + qq + ", idNumber=" + idNumber + ", homeAddress=" + homeAddress + ", account=" + account
				+ ", username=" + username + ", isActive=" + isActive + ", role=" + role + ", avatar=" + avatar
				+ ", signature=" + signature + ", createtime=" + createtime + ", lastLogin=" + lastLogin
				+ ", departmentId=" + departmentId + ", profession=" + profession + ", grade=" + grade
				+ ", classNumber=" + classNumber + ", departmentName=" + departmentName + "]";
	}

}

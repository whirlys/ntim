package com.whirly.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class StudentForm {

	private Integer studentId;

	@NotNull(message = "学号不能为空")
	private String account;

	@NotNull(message = "姓名不能为空")
	private String username;

	@NotNull(message = "班级不能为空")
	private Integer classId;

	private Boolean sex;

	private Date birthday;

	@NotNull(message = "学生类型不能为空")
	private String studentType;

	private String longPhone;

	private String shortPhone;

	private String duty;

	private String familyPhone;
	@NotNull(message = "邮箱不能为空")
	private String email;

	private String qq;

	private String idNumber; // 身份证

	private String homeAddress;

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

	public StudentForm() {
		super();
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "StudentForm [studentId=" + studentId + ", account=" + account + ", username=" + username + ", classId="
				+ classId + ", sex=" + sex + ", birthday=" + birthday + ", studentType=" + studentType + ", longPhone="
				+ longPhone + ", shortPhone=" + shortPhone + ", duty=" + duty + ", familyPhone=" + familyPhone
				+ ", email=" + email + ", qq=" + qq + ", idNumber=" + idNumber + ", homeAddress=" + homeAddress + "]";
	}

	public StudentForm(Integer studentId, @NotNull(message = "学号不能为空") String account,
			@NotNull(message = "姓名不能为空") String username, @NotNull(message = "班级不能为空") Integer classId, Boolean sex,
			Date birthday, @NotNull(message = "学生类型不能为空") String studentType, String longPhone, String shortPhone,
			String duty, String familyPhone, @NotNull(message = "邮箱不能为空") String email, String qq, String idNumber,
			String homeAddress) {
		super();
		this.studentId = studentId;
		this.account = account;
		this.username = username;
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
	}

}

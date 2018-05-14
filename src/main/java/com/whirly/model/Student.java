package com.whirly.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
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

	private User user;

	private Classs classs;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.userId = user.getUserId();
		this.user = user;
	}

	public Classs getClasss() {
		return classs;
	}

	public void setClasss(Classs classs) {
		this.classId = classs.getClassId();
		this.classs = classs;
	}

	private static final long serialVersionUID = 1L;

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
		this.studentType = studentType == null ? null : studentType.trim();
	}

	public String getLongPhone() {
		return longPhone;
	}

	public void setLongPhone(String longPhone) {
		this.longPhone = longPhone == null ? null : longPhone.trim();
	}

	public String getShortPhone() {
		return shortPhone;
	}

	public void setShortPhone(String shortPhone) {
		this.shortPhone = shortPhone == null ? null : shortPhone.trim();
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty == null ? null : duty.trim();
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone == null ? null : familyPhone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber == null ? null : idNumber.trim();
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress == null ? null : homeAddress.trim();
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", userId=" + userId + ", classId=" + classId + ", sex=" + sex
				+ ", birthday=" + birthday + ", studentType=" + studentType + ", longPhone=" + longPhone
				+ ", shortPhone=" + shortPhone + ", duty=" + duty + ", familyPhone=" + familyPhone + ", email=" + email
				+ ", qq=" + qq + ", idNumber=" + idNumber + ", homeAddress=" + homeAddress + "]";
	}

	public Student(Integer userId, Integer classId, Boolean sex, Date birthday, String studentType, String longPhone,
			String shortPhone, String duty, String familyPhone, String email, String qq, String idNumber,
			String homeAddress) {
		super();
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
	}

	public Student() {
		super();
	}

	public void setAllNoUser(Integer classId, Boolean sex, Date birthday, String studentType, String longPhone,
			String shortPhone, String duty, String familyPhone, String email, String qq, String idNumber,
			String homeAddress) {
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
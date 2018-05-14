package com.whirly.form;

import java.util.Date;

public class StudentUserForm {
	private Boolean sex;

	private Date birthday;

	private String longPhone;

	private String shortPhone;

	private String duty;

	private String familyPhone;

	private String email;

	private String qq;

	private String idNumber;

	private String homeAddress;

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

	@Override
	public String toString() {
		return "StudentUserForm [sex=" + sex + ", birthday=" + birthday + ", longPhone=" + longPhone + ", shortPhone="
				+ shortPhone + ", duty=" + duty + ", familyPhone=" + familyPhone + ", email=" + email + ", qq=" + qq
				+ ", idNumber=" + idNumber + ", homeAddress=" + homeAddress + "]";
	}

}

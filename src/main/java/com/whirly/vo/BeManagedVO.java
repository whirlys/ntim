package com.whirly.vo;

import java.util.Date;

public class BeManagedVO {
	private Integer managedId;

	private Integer userId;

	private Integer classId;

	private Date createtime;

	private Integer departmentId;

	private String profession;

	private Integer grade;

	private Integer classNumber;

	private String departmentName;

	public Integer getManagedId() {
		return managedId;
	}

	public void setManagedId(Integer managedId) {
		this.managedId = managedId;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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

	@Override
	public String toString() {
		return "BeManagedVO [managedId=" + managedId + ", userId=" + userId + ", classId=" + classId + ", createtime="
				+ createtime + ", departmentId=" + departmentId + ", profession=" + profession + ", grade=" + grade
				+ ", classNumber=" + classNumber + ", departmentName=" + departmentName + "]";
	}

}

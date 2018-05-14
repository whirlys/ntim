package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Classs implements Serializable {

	private Department department;

	private Integer classId;

	private Integer departmentId;

	private String profession;

	private Integer grade;

	private Integer classNumber;

	private Date createtime;

	private static final long serialVersionUID = 1L;

	public String getClassName() {
		return grade + profession + classNumber + "班";
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
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
		this.profession = profession == null ? null : profession.trim();
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Classs(Integer departmentId, String profession, Integer grade, Integer classNumber) {
		super();
		this.departmentId = departmentId;
		this.profession = profession;
		this.grade = grade;
		this.classNumber = classNumber;
	}

	public Classs() {
		super();
	}

	@Override
	public String toString() {
		return "Classs [classId=" + classId + ", departmentId=" + departmentId + ", profession=" + profession
				+ ", grade=" + grade + ", classNumber=" + classNumber + ", createtime=" + createtime + "]";
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.departmentId = department.getDepartmentId();
		this.department = department;
	}

}
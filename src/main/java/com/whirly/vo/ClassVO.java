package com.whirly.vo;

import java.util.Date;

public class ClassVO {
	private Integer classId;

	private Integer departmentId;

	private String departmentName;

	private Integer parentId;

	private String remark;

	private Boolean isActive;

	private String profession;

	private Integer grade;

	private Integer classNumber;

	private Date createtime;

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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public ClassVO(Integer classId, Integer departmentId, String departmentName, Integer parentId, String remark,
			Boolean isActive, String profession, Integer grade, Integer classNumber, Date createtime) {
		super();
		this.classId = classId;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.parentId = parentId;
		this.remark = remark;
		this.isActive = isActive;
		this.profession = profession;
		this.grade = grade;
		this.classNumber = classNumber;
		this.createtime = createtime;
	}

	public ClassVO() {
		super();
	}

	@Override
	public String toString() {
		return "ClassVO [classId=" + classId + ", departmentId=" + departmentId + ", departmentName=" + departmentName
				+ ", parentId=" + parentId + ", remark=" + remark + ", isActive=" + isActive + ", profession="
				+ profession + ", grade=" + grade + ", classNumber=" + classNumber + ", createtime=" + createtime + "]";
	}

}

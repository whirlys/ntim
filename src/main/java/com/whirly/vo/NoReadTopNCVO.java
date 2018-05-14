package com.whirly.vo;

public class NoReadTopNCVO implements Comparable<NoReadTopNCVO> {

	private Integer classId;

	private Integer departmentId;

	private String profession;

	private Integer grade;

	private Integer classNumber;

	private String departmentName;

	private Integer count;

	private Integer studentNum;

	private Double unreadRate;

	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getUnreadRate() {
		return unreadRate;
	}

	public void setUnreadRate(Double unreadRate) {
		this.unreadRate = unreadRate;
	}

	@Override
	public int compareTo(NoReadTopNCVO o) {
		return -this.getUnreadRate().compareTo(o.getUnreadRate());
	}

	@Override
	public String toString() {
		return "NoReadTopNCVO [classId=" + classId + ", departmentId=" + departmentId + ", profession=" + profession
				+ ", grade=" + grade + ", classNumber=" + classNumber + ", departmentName=" + departmentName
				+ ", count=" + count + ", studentNum=" + studentNum + ", unreadRate=" + unreadRate + "]";
	}

}

package com.whirly.dto;

import java.io.Serializable;

public class ClasssParamDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String departmentName;
	private Integer departmentId;
	private String profession;
	private Integer grade;
	private Integer classNumber;

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

	@Override
	public String toString() {
		return "ClasssParamDto [departmentName=" + departmentName + ", profession=" + profession + ", grade=" + grade
				+ ", classNumber=" + classNumber + "]";
	}

	/**
	 * 重写hashCode
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((classNumber == null) ? 0 : classNumber.hashCode());
		return result;
	}

	/**
	 * 重写equals
	 */
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		ClasssParamDto other = (ClasssParamDto) obj;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;

		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;

		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;

		if (classNumber == null) {
			if (other.classNumber != null)
				return false;
		} else if (!classNumber.equals(other.classNumber))
			return false;

		return true;
	}

	public ClasssParamDto setAll(String departmentName, String profession, Integer grade, Integer classNumber) {

		this.departmentName = departmentName;
		this.profession = profession;
		this.grade = grade;
		this.classNumber = classNumber;

		return this;
	}

	public String getClassName() {
		return departmentName + grade + profession + classNumber + "班";
	}

}

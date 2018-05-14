package com.whirly.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ClasssForm {
	@NotNull(message = "院系不能为空")
	private Integer departmentId;

	@NotNull(message = "备注不能为空")
	@Length(max = 20, message = "专业名称长度须小于20")
	private String profession;

	@NotNull(message = "年级不能为空")
	private Integer grade;

	@NotNull(message = "班级不能为空")
	private Integer classNumber;

	public ClasssForm(@NotNull(message = "院系不能为空") Integer departmentId,
			@NotNull(message = "备注不能为空") @Length(max = 20, message = "专业名称长度须小于20") String profession,
			@NotNull(message = "年级不能为空") Integer grade, @NotNull(message = "班级不能为空") Integer classNumber) {
		super();
		this.departmentId = departmentId;
		this.profession = profession;
		this.grade = grade;
		this.classNumber = classNumber;
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

	public ClasssForm() {
		super();
	}

}

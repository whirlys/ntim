package com.whirly.form;

public class ClasssSearchForm extends BaseSearchForm {
	private Integer departmentId;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "ClasssSearchForm [departmentId=" + departmentId + ", toString()=" + super.toString() + "]";
	}

}

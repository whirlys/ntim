package com.whirly.form;

public class PublicerSearchForm extends BaseSearchForm {

	private Integer departmentId;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "PublicerSearchForm [departmentId=" + departmentId + ", getPage()=" + getPage() + ", getLimit()="
				+ getLimit() + ", getQ()=" + getQ() + "]";
	}

}

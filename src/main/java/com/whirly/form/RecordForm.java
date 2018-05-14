package com.whirly.form;

public class RecordForm {
	private Integer formId;

	private Integer userId;

	private String value;

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "RecordForm [formId=" + formId + ", userId=" + userId + ", value=" + value + "]";
	}

}

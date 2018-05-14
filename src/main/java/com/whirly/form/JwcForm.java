package com.whirly.form;

public class JwcForm extends BaseSearchForm {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type == null || type.trim().length() == 0) {
			this.type = null;
		} else {
			this.type = type;
		}

	}

	@Override
	public String toString() {
		return "JwcForm [type=" + type + ", toString()=" + super.toString() + "]";
	}

}
package com.whirly.model;

import java.io.Serializable;

public class Field implements Serializable {
	private Integer fieldId;

	private String name;

	private Boolean required;

	private String type;

	private String constraint;

	private String defaultValue;

	private Integer formId;

	private Integer rank;

	private static final long serialVersionUID = 1L;

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint == null ? null : constraint.trim();
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue == null ? null : defaultValue.trim();
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Field [fieldId=" + fieldId + ", name=" + name + ", required=" + required + ", type=" + type
				+ ", constraint=" + constraint + ", defaultValue=" + defaultValue + ", formId=" + formId + ", rank="
				+ rank + "]";
	}

}
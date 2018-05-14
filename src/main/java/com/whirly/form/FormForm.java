package com.whirly.form;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whirly.model.Field;

public class FormForm {
	private String formName;

	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date deadline;

	private Integer userId;

	private List<Integer> receivers;

	private List<Field> fieldItems;

	private String constraint;

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<Integer> receivers) {
		this.receivers = receivers;
	}

	public List<Field> getFieldItems() {
		return fieldItems;
	}

	public void setFieldItems(List<Field> fieldItems) {
		this.fieldItems = fieldItems;
	}

	@Override
	public String toString() {
		if (receivers != null) {
			for (Integer id : receivers) {
				System.out.print(id + ", ");
			}
			System.out.println();
		}
		if (fieldItems != null) {
			for (Field f : fieldItems) {
				System.out.println(f.toString());
			}
		}

		return "FormForm [formName=" + formName + ", description=" + description + ", deadline=" + deadline
				+ ", userId=" + userId + ", constraint=" + constraint + ", receivers=" + receivers + ", fieldItems="
				+ fieldItems + "]";
	}

}

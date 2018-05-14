package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
	private Integer recordId;

	private Integer formId;

	private Integer userId;

	private String value;

	private Date updatetime;

	private Boolean filled;

	private static final long serialVersionUID = 1L;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

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
		this.value = value == null ? null : value.trim();
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Boolean getFilled() {
		return filled;
	}

	public void setFilled(Boolean filled) {
		this.filled = filled;
	}

	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", formId=" + formId + ", userId=" + userId + ", value=" + value
				+ ", updatetime=" + updatetime + ", filled=" + filled + "]";
	}

}
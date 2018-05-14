package com.whirly.vo;

import java.util.Date;
import java.util.Map;

import com.whirly.model.Classs;

public class RecordVO {
	private String username;

	private Integer userId;

	private String account;

	private Integer recordId;

	private Integer formId;

	private String value;

	private Date updatetime;

	private Boolean filled;

	private Integer studentId;

	private Integer classId;

	private Classs classs;

	private Map<String, Object> valueMap;

	@Override
	public String toString() {
		return "RecordVO [username=" + username + ", userId=" + userId + ", account=" + account + ", recordId="
				+ recordId + ", formId=" + formId + ", value=" + value + ", updatetime=" + updatetime + ", filled="
				+ filled + ", studentId=" + studentId + ", classId=" + classId + ", classs=" + classs + ", valueMap="
				+ valueMap + "]";
	}

	public Map<String, Object> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public Classs getClasss() {
		return classs;
	}

	public void setClasss(Classs classs) {
		this.classs = classs;
	}

}

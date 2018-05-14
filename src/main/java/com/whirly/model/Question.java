package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {
	private Integer questionId;

	private Integer departmentId;

	private Integer userId;

	private String title;

	private String description;

	private Date createtime;

	private Boolean anonymous;

	private static final long serialVersionUID = 1L;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", departmentId=" + departmentId + ", userId=" + userId
				+ ", title=" + title + ", description=" + description + ", createtime=" + createtime + ", anonymous="
				+ anonymous + "]";
	}

}
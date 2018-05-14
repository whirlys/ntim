package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
	private Integer answerId;

	private Integer departmentId;

	private Integer userId;

	private String answer;

	private Date createtime;

	private Boolean anonymous;

	private Boolean top;

	private Integer questionId;

	private static final long serialVersionUID = 1L;

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", departmentId=" + departmentId + ", userId=" + userId + ", answer="
				+ answer + ", createtime=" + createtime + ", anonymous=" + anonymous + ", top=" + top + ", questionId="
				+ questionId + "]";
	}

}
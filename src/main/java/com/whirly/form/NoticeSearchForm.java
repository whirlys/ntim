package com.whirly.form;

import static org.junit.Assert.assertNotNull;

public class NoticeSearchForm extends BaseSearchForm {

	private Integer userId;

	private Integer senderId;

	private Integer lever;

	private String type;

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLever() {
		return lever;
	}

	public void setLever(Integer lever) {
		this.lever = lever;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (null == type || "".equals(type)) {
			this.type = null;
		} else {
			this.type = type;
		}
	}

	@Override
	public String toString() {
		return "NoticeSearchForm [userId=" + userId + ", lever=" + lever + ", type=" + type + ", toString()="
				+ super.toString() + "]";
	}

}

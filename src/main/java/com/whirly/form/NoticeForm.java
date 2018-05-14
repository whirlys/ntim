package com.whirly.form;

import java.util.Arrays;

public class NoticeForm {
	private String title;

	private Integer userId;

	private String publicer;

	private Short lever;

	private String type;

	private String content;

	private Integer[] receivers;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPublicer() {
		return publicer;
	}

	public void setPublicer(String publicer) {
		this.publicer = publicer;
	}

	public Short getLever() {
		return lever;
	}

	public void setLever(Short lever) {
		this.lever = lever;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer[] getReceivers() {
		return receivers;
	}

	public void setReceivers(Integer[] receivers) {
		this.receivers = receivers;
	}

	@Override
	public String toString() {
		return "NoticeForm [title=" + title + ", userId=" + userId + ", publicer=" + publicer + ", lever=" + lever
				+ ", type=" + type + ", content=" + content + ", receivers=" + Arrays.toString(receivers) + "]";
	}

}

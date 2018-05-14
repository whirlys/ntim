package com.whirly.imserver.model;

//
public class PushMessageBody {
	private String title; // 通知的标题
	private String sampleContent; // 推送的内容，即通知的前N个词

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSampleContent() {
		return sampleContent;
	}

	public void setSampleContent(String sampleContent) {
		this.sampleContent = sampleContent;
	}

}

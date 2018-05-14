package com.whirly.form;

public class NoticeMessageForm {

	private Integer noticeId;
	private String content;

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "NoticeMessageForm [noticeId=" + noticeId + ", content=" + content + "]";
	}

}

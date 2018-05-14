package com.whirly.vo;

public class NoticeIsReadVo {

	private Integer noticeId;
	private boolean isRead;
	private Integer num;

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "NoticeIsReadVo [noticeId=" + noticeId + ", isRead=" + isRead + ", num=" + num + "]";
	}

}

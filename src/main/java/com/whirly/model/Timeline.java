package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Timeline implements Serializable {
	private Integer timelineId;

	private Integer noticeId;

	private Integer userId;

	private Boolean isRead;

	private Date createtime;

	private static final long serialVersionUID = 1L;

	public Integer getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Timeline [timelineId=" + timelineId + ", noticeId=" + noticeId + ", userId=" + userId + ", isRead="
				+ isRead + ", createtime=" + createtime + "]";
	}

}
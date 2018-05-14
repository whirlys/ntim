package com.whirly.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.whirly.enums.NoticeLever;

public class NoticeUserVO {
	private Integer senderUserId;

	private String senderUsername;

	private Integer noticeId;

	private String title;

	private Short lever;

	private Date createtime;

	private String publicer;

	private String type;

	private String content;

	private Integer timelineId;

	private Boolean isRead;

	public String getFormatCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(createtime);
	}

	public String getLeverName() {
		return NoticeLever.getName(lever);
	}

	public Integer getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(Integer senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Short getLever() {
		return lever;
	}

	public void setLever(Short lever) {
		this.lever = lever;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPublicer() {
		return publicer;
	}

	public void setPublicer(String publicer) {
		this.publicer = publicer;
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

	public Integer getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

}

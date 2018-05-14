package com.whirly.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.whirly.enums.NoticeLever;

public class Notice implements Serializable {
	private Integer noticeId;

	private String title;

	private Integer userId;

	private Short lever;

	private Date createtime;

	private String publicer;

	private String type;

	private String content;

	public String getFormatCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(createtime);
	}

	public String getLeverName() {
		return NoticeLever.getName(this.lever);
	}

	private static final long serialVersionUID = 1L;

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
		this.title = title == null ? null : title.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
		this.publicer = publicer == null ? null : publicer.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", title=" + title + ", userId=" + userId + ", lever=" + lever
				+ ", createtime=" + createtime + ", publicer=" + publicer + ", type=" + type + ", content=" + content
				+ "]";
	}

	public Notice(String title, Integer userId, Short lever, String publicer, String type, String content) {
		super();
		this.title = title;
		this.userId = userId;
		this.lever = lever;
		this.publicer = publicer;
		this.type = type;
		this.content = content;
	}

	public Notice() {
		super();
	}

}
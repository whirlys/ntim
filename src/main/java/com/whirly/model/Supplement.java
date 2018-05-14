package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Supplement implements Serializable {
	private Integer supplementId;

	private Integer noticeId;

	private String content;

	private Date createtime;

	private static final long serialVersionUID = 1L;

	public Integer getSupplementId() {
		return supplementId;
	}

	public void setSupplementId(Integer supplementId) {
		this.supplementId = supplementId;
	}

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
		this.content = content == null ? null : content.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Supplement [supplementId=" + supplementId + ", noticeId=" + noticeId + ", content=" + content
				+ ", createtime=" + createtime + "]";
	}

	public Supplement(Integer noticeId, String content) {
		super();
		this.noticeId = noticeId;
		this.content = content;
	}

	public Supplement() {
		super();
	}

}
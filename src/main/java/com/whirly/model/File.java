package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {
	private Integer fileId;

	private String filename;

	private String remarks;

	private String url;

	private Date createtime;

	private Integer userId;

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", filename=" + filename + ", remarks=" + remarks + ", url=" + url
				+ ", createtime=" + createtime + ", userId=" + userId + "]";
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename == null ? null : filename.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
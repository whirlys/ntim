package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
	private Integer groupId;

	private String groupName;

	private Integer userId;

	private String introduction;

	private String avatar;

	private String announcement;

	private Date createtime;

	private static final long serialVersionUID = 1L;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement == null ? null : announcement.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", userId=" + userId + ", introduction="
				+ introduction + ", avatar=" + avatar + ", announcement=" + announcement + ", createtime=" + createtime
				+ "]";
	}

	public Group(String groupName, Integer userId, String introduction, String avatar, String announcement) {
		super();
		this.groupName = groupName;
		this.userId = userId;
		this.introduction = introduction;
		this.avatar = avatar;
		this.announcement = announcement;
	}

	public Group() {
		super();
	}

}
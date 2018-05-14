package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
	private Integer memberId;

	private Integer groupId;

	private Integer userId;

	private Date createtime;

	private static final long serialVersionUID = 1L;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", groupId=" + groupId + ", userId=" + userId + ", createtime="
				+ createtime + "]";
	}

	public Member(Integer groupId, Integer userId) {
		super();
		this.groupId = groupId;
		this.userId = userId;
	}

	public Member() {
		super();
	}

}
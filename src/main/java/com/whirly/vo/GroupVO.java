package com.whirly.vo;

public class GroupVO {
	private Integer id;

	private String groupname;

	private String avatar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "GroupVO [id=" + id + ", groupname=" + groupname + ", avatar=" + avatar + "]";
	}

}

package com.whirly.vo;

import java.util.List;

public class ReceiverFormVO {
	private Integer formId;
	private Boolean filled;
	private List<FriendsListVO> groups;

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public Boolean getFilled() {
		return filled;
	}

	public void setFilled(Boolean filled) {
		this.filled = filled;
	}

	public List<FriendsListVO> getGroups() {
		return groups;
	}

	public void setGroups(List<FriendsListVO> groups) {
		this.groups = groups;
	}

	public Integer getCount() {
		Integer count = 0;
		for (FriendsListVO group : groups) {
			count = count + group.getList().size();
		}
		return count;
	}
}

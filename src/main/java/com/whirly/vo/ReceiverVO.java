package com.whirly.vo;

import java.util.List;

public class ReceiverVO {
	private Integer noticeId;
	private Boolean isRead;

	private List<FriendsListVO> groups;

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
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

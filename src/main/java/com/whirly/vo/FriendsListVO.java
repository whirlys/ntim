package com.whirly.vo;

import java.util.List;

/**
 * 好友列表
 * 
 * @author TDW
 *
 */
public class FriendsListVO {
	private Integer id;
	private String groupname;
	private List<UserVO> list;

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

	public List<UserVO> getList() {
		return list;
	}

	public void setList(List<UserVO> list) {
		this.list = list;
	}

	public Integer getCount() {
		return list.size();
	}

	@Override
	public String toString() {
		return "FriendsListVO [id=" + id + ", groupname=" + groupname + ", list=" + list + "]";
	}

}

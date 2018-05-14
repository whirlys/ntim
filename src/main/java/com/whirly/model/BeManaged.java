package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class BeManaged implements Serializable {
	private Integer managedId;

	private Integer userId;

	private Integer classId;

	private Date createtime;

	private static final long serialVersionUID = 1L;

	public Integer getManagedId() {
		return managedId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setManagedId(Integer managedId) {
		this.managedId = managedId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void beManagedList(Integer userId) {
		this.userId = userId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "BeManaged [managedId=" + managedId + ", userId=" + userId + ", classId=" + classId + ", createtime="
				+ createtime + "]";
	}

	public BeManaged(Integer userId, Integer classId) {
		super();
		this.userId = userId;
		this.classId = classId;
	}

	public BeManaged() {
		super();
	}

}
package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable {
	private Integer departmentId;

	private String name;

	private Integer parentId;

	private String remark;

	private Boolean isActive;

	private Date createtime;

	private static final long serialVersionUID = 1L;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + ", parentId=" + parentId + ", remark="
				+ remark + ", isActive=" + isActive + ", createtime=" + createtime + "]";
	}

	public Department(String name, Integer parentId, String remark, Boolean isActive) {
		super();
		this.name = name;
		this.parentId = parentId;
		this.remark = remark;
		this.isActive = isActive;
	}

	public Department() {
		super();
	}

}
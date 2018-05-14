package com.whirly.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/*
 * 院系实体类 表单验证
 */
public class DepartmentForm {

	public DepartmentForm() {
		super();
	}

	public DepartmentForm(@NotNull(message = "名称不能为空") @Length(max = 20, message = "标题长度须小于20") String name,
			String switchbtn, @NotNull(message = "上级院系不能为空") Integer parentId,
			@NotNull(message = "备注不能为空") String remark) {
		super();
		this.name = name;
		this.switchbtn = switchbtn;
		this.parentId = parentId;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "DepartmentForm [name=" + name + ", switchbtn=" + switchbtn + ", parentId=" + parentId + ", remark="
				+ remark + "]";
	}

	@NotNull(message = "名称不能为空")
	@Length(max = 20, message = "标题长度须小于20")
	private String name;

	private String switchbtn;

	private Integer parentId;

	@NotNull(message = "备注不能为空")
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSwitchbtn() {
		return switchbtn;
	}

	public void setSwitchbtn(String switchbtn) {
		this.switchbtn = switchbtn;
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
		this.remark = remark;
	}

}

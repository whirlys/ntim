package com.whirly.enums;

public enum NoticeType {

	EDUCATIONAL("教务通知", 1), EXAMINATION("考务通知", 2), STUDENTRECORDF("学籍通知", 3), RESEARCH("教研通知", 4), PRACTICE("实践通知",
			5), FORM("表单通知", 20);

	private String name;
	private int type;

	private NoticeType(String name, int type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	// 普通方法
	public static String getName(int type) {
		for (NoticeType c : NoticeType.values()) {
			if (c.getType() == type) {
				return c.name;
			}
		}
		return null;
	}
}

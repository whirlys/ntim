package com.whirly.enums;

public enum NoticeLever {

	GENERAL("一般", 1), IMPORTANT("重要", 2), URGENCY("紧急", 3), VERYIMPORTANT("紧急且重要", 4);

	private String name;
	private int lever;

	// 普通方法
	public static String getName(int lever) {
		for (NoticeLever c : NoticeLever.values()) {
			if (c.getLever() == lever) {
				return c.name;
			}
		}
		return null;
	}

	private NoticeLever(String name, int lever) {
		this.name = name;
		this.lever = lever;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLever() {
		return lever;
	}

	public void setLever(int lever) {
		this.lever = lever;
	}

}

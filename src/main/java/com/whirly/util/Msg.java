package com.whirly.util;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	public static int SUCCESS = 100;
	public static int ERROR = 200;

	@Override
	public String toString() {
		return "Msg [code=" + code + ", msg=" + msg + ", map=" + map + "]";
	}

	// 状态码 100-成功 200-失败
	private int code;

	// 提示信息
	private String msg;

	// 用户要返回给浏览器的数据
	private Map<String, Object> map = new HashMap<String, Object>();

	public static Msg success() {
		Msg msg = new Msg();
		msg.setCode(SUCCESS);
		msg.setMsg("成功");
		return msg;
	}

	public static Msg success(String message) {
		Msg msg = new Msg();
		msg.setCode(SUCCESS);
		msg.setMsg(message);
		return msg;
	}

	public static Msg error() {
		Msg msg = new Msg();
		msg.setCode(ERROR);
		msg.setMsg("失败");
		return msg;
	}

	public static Msg error(String message) {
		Msg msg = new Msg();
		msg.setCode(ERROR);
		msg.setMsg(message);
		return msg;
	}

	public Msg add(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}

package com.whirly.vo;

import java.util.Date;

public class OffLineMessageVO {
	private Integer cid; // 消息id

	private Integer id; // 消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）

	private Short msgType; // 消息类型

	private Date timestamp; // 服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000

	private String content; // 消息内容

	private String avatar; // 头像

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
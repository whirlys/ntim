package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private Integer messageId; // 消息id

	private Integer msgFrom; // 发送者user_id

	private Integer msgTo; // 接收的id（可以是用户，群或讨论组）

	private Integer cmdId; // tcp包头命令号

	private Integer msgSeq; // tcp包头seq

	private Short msgType; // 消息类型

	private Boolean isRead; // 是否已读

	private Date createtime; // 服务端收到消息的时间

	private String content; // 消息内容

	private static final long serialVersionUID = 1L;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(Integer msgFrom) {
		this.msgFrom = msgFrom;
	}

	public Integer getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(Integer msgTo) {
		this.msgTo = msgTo;
	}

	public Integer getCmdId() {
		return cmdId;
	}

	public void setCmdId(Integer cmdId) {
		this.cmdId = cmdId;
	}

	public Integer getMsgSeq() {
		return msgSeq;
	}

	public void setMsgSeq(Integer msgSeq) {
		this.msgSeq = msgSeq;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", msgFrom=" + msgFrom + ", msgTo=" + msgTo + ", cmdId=" + cmdId
				+ ", msgSeq=" + msgSeq + ", msgType=" + msgType + ", isRead=" + isRead + ", createtime=" + createtime
				+ ", content=" + content + "]";
	}

	public Message(Integer msgFrom, Integer msgTo, Integer cmdId, Integer msgSeq, Short msgType, Boolean isRead,
			String content) {
		super();
		this.msgFrom = msgFrom;
		this.msgTo = msgTo;
		this.cmdId = cmdId;
		this.msgSeq = msgSeq;
		this.msgType = msgType;
		this.isRead = isRead;
		this.content = content;
	}

	public Message() {
		super();
	}

}
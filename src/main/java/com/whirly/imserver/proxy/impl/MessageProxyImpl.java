package com.whirly.imserver.proxy.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.whirly.imserver.constant.Constants;
import com.whirly.imserver.model.MessageWrapper;
import com.whirly.imserver.model.MessageWrapper.MessageProtocol;
import com.whirly.imserver.model.proto.MessageBodyProto;
import com.whirly.imserver.model.proto.MessageProto;
import com.whirly.imserver.proxy.MessageProxy;
import com.whirly.model.Message;
import com.whirly.service.MessageService;
import com.whirly.robot.RobotProxy;

public class MessageProxyImpl implements MessageProxy {
	// 机器人
	@Autowired
	private RobotProxy rebotProxy;

	@Autowired
	private MessageService messageService;

	@Override
	public MessageWrapper convertToMessageWrapper(String sessionId, MessageProto.Model message) {
		switch (message.getCmd()) {
		case Constants.CmdType.BIND:
			try {
				return new MessageWrapper(MessageProtocol.CONNECT, message.getSender(), null, message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case Constants.CmdType.HEARTBEAT:
			try {
				return new MessageWrapper(MessageProtocol.HEART_BEAT, sessionId, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case Constants.CmdType.ONLINE:
			break;
		case Constants.CmdType.OFFLINE:
			break;
		case Constants.CmdType.MESSAGE:
			try {
				MessageProto.Model.Builder builder = MessageProto.Model.newBuilder(message);
				builder.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				builder.setSender(sessionId);// 存入发送人sessionId
				message = MessageProto.Model.parseFrom(builder.build().toByteArray());
				// 判断消息是否有接收人
				if (StringUtils.isNotEmpty(message.getReceiver())) {
					// 判断是否发消息给机器人
					if (message.getReceiver().equals(Constants.ImServerConfig.ROBOT_SESSIONID)) {
						MessageBodyProto.MessageBody msg = MessageBodyProto.MessageBody.parseFrom(message.getContent());
						return rebotProxy.botMessageReply(sessionId, msg.getContent());
					} else {
						return new MessageWrapper(MessageWrapper.MessageProtocol.REPLY, sessionId,
								message.getReceiver(), message);
					}
				} else if (StringUtils.isNotEmpty(message.getGroupId())) {
					// 群聊消息
					return new MessageWrapper(MessageWrapper.MessageProtocol.GROUP, sessionId, null, message);
				} else {
					return new MessageWrapper(MessageWrapper.MessageProtocol.SEND, sessionId, null, message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		return null;
	}

	@Override
	public void saveOnlineMessageToDB(MessageWrapper wrapper) {
		try {
			Message userMessage = convertMessageWrapperToBean(wrapper);
			if (userMessage != null) {
				userMessage.setIsRead(true);
				messageService.insert(userMessage);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
	}

	@Override
	public void saveOfflineMessageToDB(MessageWrapper wrapper) {
		try {
			
			Message userMessage = convertMessageWrapperToBean(wrapper);
			if (userMessage != null) {
				userMessage.setIsRead(false);
				messageService.insert(userMessage);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}

	}

	@Override
	public MessageProto.Model getOnLineStateMsg(String sessionId) {
		MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
		builder.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		builder.setSender(sessionId);
		builder.setCmd(Constants.CmdType.ONLINE);
		return builder.build();
	}

	@Override
	public MessageWrapper getReConnectionStateMsg(String sessionId) {
		MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
		builder.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		builder.setSender(sessionId);
		builder.setCmd(Constants.CmdType.ONLINE);
		return new MessageWrapper(MessageWrapper.MessageProtocol.SEND, sessionId, null, builder.build());
	}

	@Override
	public MessageProto.Model getOffLineStateMsg(String sessionId) {
		MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
		builder.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		builder.setSender(sessionId);
		builder.setCmd(Constants.CmdType.RECON);
		return builder.build();
	}

	private Message convertMessageWrapperToBean(MessageWrapper wrapper) {
		try {
			if (wrapper.getSessionId().equals(Constants.ImServerConfig.ROBOT_SESSIONID)) {
				// 保存机器人消息
			} else {
				// 保存非机器人消息
				MessageProto.Model msg = (MessageProto.Model) wrapper.getBody();
				MessageBodyProto.MessageBody body = MessageBodyProto.MessageBody.parseFrom(msg.getContent());

				Message message = new Message();
				message.setMsgFrom(Integer.parseInt(msg.getSender()));
				message.setMsgTo(Integer.parseInt(msg.getReceiver()));
				message.setContent(body.getContent());

				message.setMsgType((short) msg.getMsgtype()); // 这里要区分群聊，单聊和讨论组
				message.setCmdId(msg.getCmd());
				return message;
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
		return null;
	}

}

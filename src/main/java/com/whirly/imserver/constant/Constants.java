package com.whirly.imserver.constant;

import io.netty.util.AttributeKey;

public class Constants {

	public static interface ImServerConfig {
		// 连接空闲时间
		public static final int READ_IDLE_TIME = 60;// 秒
		// 发送心跳包循环时间
		public static final int WRITE_IDLE_TIME = 20;// 秒
		// 心跳响应 超时时间
		public static final int PING_TIME_OUT = 70; // 秒 需大于空闲时间

		// 最大协议包长度
		public static final int MAX_FRAME_LENGTH = 1024 * 10; // 10k
		//
		public static final int MAX_AGGREGATED_CONTENT_LENGTH = 65536;

		public static final String ROBOT_SESSIONID = "20180001";// 机器人SessionID

		public static final int WEBSOCKET = 1;// websocket标识

		public static final int SOCKET = 0;// socket标识

		public static final int DWR = 2;// dwr标识
	}

	public static interface SessionConfig {
		public static final String SESSION_KEY = "account";
		public static final AttributeKey<String> SERVER_SESSION_ID = AttributeKey.valueOf(SESSION_KEY);
		public static final AttributeKey SERVER_SESSION_HEARBEAT = AttributeKey.valueOf("heartbeat");
	}

	public static interface ProtobufType {
		byte SEND = 1; // 请求
		byte RECEIVE = 2; // 接收
		byte NOTIFY = 3; // 通知
		byte REPLY = 4; // 回复
	}

	public static interface CmdType {
		byte BIND = 1; // 绑定
		byte HEARTBEAT = 2; // 心跳
		byte ONLINE = 3; // 上线
		byte OFFLINE = 4; // 下线
		byte MESSAGE = 5; // 消息
		byte RECON = 6; // 重连
		byte NOTIFY = 7;
	}

	public static interface MessageType {
		Short USER_MESSAGE = 1; // 用户消息
		Short GROUP_MESSAGE = 2; // 群聊消息
		Short DISCUSSION_MESSAGE = 3; // 讨论组消息
		Short PUSH_MESSAGE = 4; // 推送消息
	}
}

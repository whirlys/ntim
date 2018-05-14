package com.whirly.imserver.connector;

import com.whirly.imserver.model.MessageWrapper;

import io.netty.channel.ChannelHandlerContext;

public interface ImConnector {

	/**
	 * 发送心跳检测 到客户端
	 * 
	 * @param hander
	 * @param wrapper
	 */
	void heartbeatToClient(ChannelHandlerContext ctx, MessageWrapper wrapper);

	/**
	 * 发送消息
	 * 
	 * @param wrapper
	 * @throws RuntimeException
	 */
	void pushMessage(MessageWrapper wrapper) throws RuntimeException;

	/**
	 * 发送组消息
	 * 
	 * @param wrapper
	 * @throws RuntimeException
	 */
	void pushGroupMessage(MessageWrapper wrapper) throws RuntimeException;

	/**
	 * 验证session
	 * 
	 * @param wrapper
	 * @return
	 * @throws RuntimeException
	 */
	boolean validateSession(MessageWrapper wrapper) throws RuntimeException;

	void close(ChannelHandlerContext ctx, MessageWrapper wrapper);

	void close(String sessionId);

	void close(ChannelHandlerContext ctx);

	void connect(ChannelHandlerContext ctx, MessageWrapper wrapper);

	boolean exist(String sessionId) throws Exception;

	/**
	 * 发送消息
	 * 
	 * @param sessionId
	 *            发送人
	 * @param wrapper
	 *            发送内容
	 * @throws RuntimeException
	 */
	void pushMessage(String sessionid, MessageWrapper wrapper) throws RuntimeException;

	/**
	 * 获取用户唯一标识符
	 * 
	 * @param ctx
	 * @return
	 */
	String getChannelSessionId(ChannelHandlerContext ctx);
}

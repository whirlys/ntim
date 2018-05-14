package com.whirly.imserver;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whirly.imserver.connector.ImConnector;
import com.whirly.imserver.constant.Constants;
import com.whirly.imserver.model.MessageWrapper;
import com.whirly.imserver.model.proto.MessageProto;
import com.whirly.imserver.proxy.MessageProxy;
import com.whirly.util.ImUtils;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@Sharable
public class ImWebsocketServerHandler extends SimpleChannelInboundHandler<MessageProto.Model> {
	private static final Logger logger = LoggerFactory.getLogger(ImWebsocketServerHandler.class);
	private ImConnector connertor = null;
	private MessageProxy proxy = null;

	public ImWebsocketServerHandler(MessageProxy proxy, ImConnector connertor) {
		this.connertor = connertor;
		this.proxy = proxy;
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object o) throws Exception {
		String sessionId = ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_ID).get();
		// 发送心跳包
		if (o instanceof IdleStateEvent && ((IdleStateEvent) o).state().equals(IdleState.WRITER_IDLE)) {
			if (StringUtils.isNotEmpty(sessionId)) {
				MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
				builder.setCmd(Constants.CmdType.HEARTBEAT);
				builder.setMsgtype(Constants.ProtobufType.SEND);
				ctx.channel().writeAndFlush(builder);
			}
		}

		// 如果心跳请求发出70秒内没收到响应，则关闭连接
		if (o instanceof IdleStateEvent && ((IdleStateEvent) o).state().equals(IdleState.READER_IDLE)) {
			logger.debug(IdleState.READER_IDLE + "... from " + sessionId + " nid:" + ctx.channel().id().asShortText());
			Long lastTime = (Long) ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_HEARBEAT).get();
			if (lastTime == null
					|| ((System.currentTimeMillis() - lastTime) / 1000 >= Constants.ImServerConfig.PING_TIME_OUT)) {
				connertor.close(ctx);
			}
			ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_HEARBEAT).set(null);
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageProto.Model message) throws Exception {

		try {
			String sessionId = connertor.getChannelSessionId(ctx);
			// inbound
			if (message.getMsgtype() == Constants.ProtobufType.SEND) {
				ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_HEARBEAT).set(System.currentTimeMillis());
				MessageWrapper wrapper = proxy.convertToMessageWrapper(sessionId, message);
				if (wrapper != null)
					receiveMessages(ctx, wrapper);
			}
			// outbound
			if (message.getMsgtype() == Constants.ProtobufType.REPLY) {
				MessageWrapper wrapper = proxy.convertToMessageWrapper(sessionId, message);
				if (wrapper != null)
					receiveMessages(ctx, wrapper);
			}
		} catch (Exception e) {
			logger.error("ImWebSocketServerHandler channerRead error.", e);
			throw e;
		}

	}

	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("ImWebSocketServerHandler  join from " + ImUtils.getRemoteAddress(ctx) + " nid:"
				+ ctx.channel().id().asShortText());
	}

	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		logger.debug("ImWebSocketServerHandler Disconnected from {" + ctx.channel().remoteAddress() + "--->"
				+ ctx.channel().localAddress() + "}");
	}

	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		logger.debug("ImWebSocketServerHandler channelActive from (" + ImUtils.getRemoteAddress(ctx) + ")");
	}

	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		logger.debug("ImWebSocketServerHandler channelInactive from (" + ImUtils.getRemoteAddress(ctx) + ")");
		String sessionId = connertor.getChannelSessionId(ctx);
		receiveMessages(ctx, new MessageWrapper(MessageWrapper.MessageProtocol.CLOSE, sessionId, null, null));
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warn("ImWebSocketServerHandler (" + ImUtils.getRemoteAddress(ctx)
				+ ") -> Unexpected exception from downstream." + cause);
	}

	/**
	 * to send message
	 *
	 * @param hander
	 * @param wrapper
	 */
	private void receiveMessages(ChannelHandlerContext ctx, MessageWrapper wrapper) {

		// 设置消息来源为Websocket
		wrapper.setSource(Constants.ImServerConfig.WEBSOCKET);
		if (wrapper.isConnect()) {
			connertor.connect(ctx, wrapper);
		} else if (wrapper.isClose()) {
			connertor.close(ctx, wrapper);
		} else if (wrapper.isHeartBeat()) {
			connertor.heartbeatToClient(ctx, wrapper);
		} else if (wrapper.isGroup()) {
			connertor.pushGroupMessage(wrapper);
		} else if (wrapper.isSend()) {
			connertor.pushMessage(wrapper);
		} else if (wrapper.isReply()) {
			connertor.pushMessage(wrapper.getSessionId(), wrapper);
		}
	}

}

package com.whirly.imserver.connector.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.whirly.imserver.connector.ImConnector;
import com.whirly.imserver.constant.Constants;
import com.whirly.imserver.exception.PushException;
import com.whirly.imserver.group.ImChannelGroup;
import com.whirly.imserver.model.MessageWrapper;
import com.whirly.imserver.model.Session;
import com.whirly.imserver.proxy.MessageProxy;
import com.whirly.imserver.session.SessionManager;
import com.whirly.imserver.session.impl.SessionManagerImpl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class ImConnectorImpl implements ImConnector {
	private static final Logger logger = LoggerFactory.getLogger(ImConnectorImpl.class);

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private MessageProxy proxy;

	public void setSessionManager(SessionManagerImpl sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setProxy(MessageProxy proxy) {
		this.proxy = proxy;
	}

	@Override
	public void heartbeatToClient(ChannelHandlerContext hander, MessageWrapper wrapper) {
		// 设置心跳响应时间
		hander.channel().attr(Constants.SessionConfig.SERVER_SESSION_HEARBEAT).set(System.currentTimeMillis());
	}

	/**
	 * 
	 */
	@Override
	public void pushGroupMessage(MessageWrapper wrapper) throws RuntimeException {
		// 这里判断群组ID 是否存在 并且该用户是否在群组内
		ImChannelGroup.broadcast(wrapper.getBody());

		proxy.saveOnlineMessageToDB(wrapper);
	}

	@Override
	public void pushMessage(MessageWrapper wrapper) throws RuntimeException {
		try {
			// sessionManager.send(wrapper.getSessionId(), wrapper.getBody());
			Session session = sessionManager.getSession(wrapper.getSessionId());
			/*
			 * 服务器集群时，可以在此 判断当前session是否连接于本台服务器，如果是，继续往下走，如果不是，将此消息发往当前session连接的服务器并
			 * return if(session!=null&&!session.isLocalhost()){//判断当前session是否连接于本台服务器，如不是
			 * //发往目标服务器处理 return; }
			 */
			if (session != null) {
				boolean result = session.write(wrapper.getBody());
				return;
			}
		} catch (Exception e) {

			throw new RuntimeException(e.getCause());
		}
	}

	@Override
	public void pushMessage(String sessionId, MessageWrapper wrapper) throws RuntimeException {
		// 判断是不是无效用户回复
		if (!sessionId.equals(Constants.ImServerConfig.ROBOT_SESSIONID)) {// 判断非机器人回复时验证
			Session session = sessionManager.getSession(sessionId);
			if (session == null) {
				throw new RuntimeException(String.format("session %s is not exist.", sessionId));
			}
		}
		try {
			/// 取得接收人 给接收人写入消息
			Session responseSession = sessionManager.getSession(wrapper.getReSessionId());
			if (responseSession != null && responseSession.isConnected()) {
				boolean result = responseSession.write(wrapper.getBody());

				if (wrapper.getProtocol() == MessageWrapper.MessageProtocol.NOTIFY) {
					System.out.println("==== 不保存 ======");
					return;
				}
				if (result) {
					proxy.saveOnlineMessageToDB(wrapper);
				} else {
					System.out.println("保存离线消息1");
					proxy.saveOfflineMessageToDB(wrapper);
				}
				return;
			} else {
				if (wrapper.getProtocol() == MessageWrapper.MessageProtocol.NOTIFY) {

				} else {
					System.out.println("保存离线消息2");
					proxy.saveOfflineMessageToDB(wrapper);
				}

			}
		} catch (PushException e) {
			throw new RuntimeException(e.getCause());
		} catch (Exception e) {

			throw new RuntimeException(e.getCause());
		}

	}

	@Override
	public boolean validateSession(MessageWrapper wrapper) throws RuntimeException {
		try {
			return sessionManager.exist(wrapper.getSessionId());
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
	}

	@Override
	public void close(ChannelHandlerContext hander, MessageWrapper wrapper) {
		String sessionId = getChannelSessionId(hander);
		if (StringUtils.isNotBlank(sessionId)) {
			close(hander);
		}
	}

	@Override
	public void close(ChannelHandlerContext hander) {
		String sessionId = getChannelSessionId(hander);
		try {
			String nid = hander.channel().id().asShortText();
			Session session = sessionManager.getSession(sessionId);
			if (session != null) {
				sessionManager.removeSession(sessionId, nid);
				ImChannelGroup.remove(hander.channel());
			}
		} catch (Exception e) {

			throw new RuntimeException(e.getCause());
		}
	}

	/**
	 * 关闭websocket连接
	 */
	@Override
	public void close(String sessionId) {
		try {
			Session session = sessionManager.getSession(sessionId);
			if (session != null) {
				sessionManager.removeSession(sessionId);
				List<Channel> list = session.getSessionAll();
				for (Channel ch : list) {
					ImChannelGroup.remove(ch);
				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
	}

	/**
	 * 建立websocket 连接
	 */
	@Override
	public void connect(ChannelHandlerContext ctx, MessageWrapper wrapper) {
		try {
			String sessionId = wrapper.getSessionId();
			String sessionId0 = getChannelSessionId(ctx);
			// 当sessionID存在或者相等 视为同一用户重新连接
			if (StringUtils.isNotEmpty(sessionId0) || sessionId.equals(sessionId0)) {

				pushMessage(proxy.getReConnectionStateMsg(sessionId0));
			} else {
				sessionManager.createSession(wrapper, ctx);
				setChannelSessionId(ctx, sessionId);
			}
		} catch (Exception e) {

		}
	}

	@Override
	public boolean exist(String sessionId) throws Exception {
		return sessionManager.exist(sessionId);
	}

	@Override
	public String getChannelSessionId(ChannelHandlerContext ctx) {
		return ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_ID).get();
	}

	private void setChannelSessionId(ChannelHandlerContext ctx, String sessionId) {
		ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_ID).set(sessionId);
	}

}

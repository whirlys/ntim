package com.whirly.imserver.session.impl;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.whirly.imserver.constant.Constants;
import com.whirly.imserver.group.ImChannelGroup;
import com.whirly.imserver.model.MessageWrapper;
import com.whirly.imserver.model.Session;
import com.whirly.imserver.model.proto.MessageProto;
import com.whirly.imserver.proxy.MessageProxy;
import com.whirly.imserver.session.SessionManager;

import io.netty.channel.ChannelHandlerContext;

public class SessionManagerImpl implements SessionManager {

	@Autowired
	private MessageProxy proxy;

	/**
	 * The set of currently active Sessions for this Manager, keyed by session
	 * identifier.
	 */
	@Resource(name = "sessionMap")
	protected Map<String, Session> sessions;/* = new ConcurrentHashMap<String, Session>(); */

	public synchronized void addSession(Session session) {
		if (null == session) {
			return;
		}
		sessions.put(session.getAccount(), session);
		if (session.getSource() != Constants.ImServerConfig.DWR) {
			ImChannelGroup.add(session.getSession());
		}
		// 全员发送上线消息
		// MessageProto.Model model = proxy.getOnLineStateMsg(session.getAccount());
		// ImChannelGroup.broadcast(model);
	}

	public synchronized void updateSession(Session session) {
		session.setUpdateTime(System.currentTimeMillis());
		sessions.put(session.getAccount(), session);
	}

	/**
	 * Remove this Session from the active Sessions for this Manager.
	 */
	public synchronized void removeSession(String sessionId) {
		try {
			Session session = getSession(sessionId);
			if (session != null) {
				session.closeAll();
				sessions.remove(sessionId);
				// MessageProto.Model model = proxy.getOffLineStateMsg(sessionId);
				// ImChannelGroup.broadcast(model);
			}
		} catch (Exception e) {

		}
	}

	public synchronized void removeSession(String sessionId, String nid) {
		try {
			Session session = getSession(sessionId);
			if (session != null) {
				int source = session.getSource();
				if (source == Constants.ImServerConfig.WEBSOCKET) {
					session.close(nid);
					// 判断没有其它session后 从SessionManager里面移除
					if (session.otherSessionSize() < 1) {
						sessions.remove(sessionId);
						MessageProto.Model model = proxy.getOffLineStateMsg(sessionId);
						ImChannelGroup.broadcast(model);
					}
				} else {
					session.close();
					sessions.remove(sessionId);
					MessageProto.Model model = proxy.getOffLineStateMsg(sessionId);
					ImChannelGroup.broadcast(model);
				}
			}
		} catch (Exception e) {

		} finally {

		}
	}

	public Session getSession(String sessionId) {
		return sessions.get(sessionId);
	}

	public Session[] getSessions() {
		return sessions.values().toArray(new Session[0]);
	}

	public Set<String> getSessionKeys() {
		return sessions.keySet();
	}

	public int getSessionCount() {
		return sessions.size();
	}

	@Override
	public Session createSession(MessageWrapper wrapper, ChannelHandlerContext ctx) {
		String sessionId = wrapper.getSessionId();
		Session session = sessions.get(sessionId);
		if (session != null) {

			// 当链接来源不是同一来源或者 是socket链接，踢掉已经登录的session
			if (session.getSource() == Constants.ImServerConfig.SOCKET) {
				// 如果session已经存在则销毁session
				// 从广播组清除

				ImChannelGroup.remove(session.getSession());
				session.close(session.getNid());
				sessions.remove(session.getAccount());

			} else if (session.getSource() == Constants.ImServerConfig.WEBSOCKET) {
				// 用于解决websocket多开页面session被踢下线的问题
				Session newsession = setSessionContent(ctx, wrapper, sessionId);
				session.addSessions(newsession);
				updateSession(session);
				ImChannelGroup.add(newsession.getSession());

				return newsession;
			}
		}

		session = setSessionContent(ctx, wrapper, sessionId);
		addSession(session);
		return session;
	}

	/**
	 * 设置session内容
	 * 
	 * @param ctx
	 * @param wrapper
	 * @param sessionId
	 * @return
	 */
	private Session setSessionContent(ChannelHandlerContext ctx, MessageWrapper wrapper, String sessionId) {

		MessageProto.Model model = (MessageProto.Model) wrapper.getBody();
		Session session = new Session(ctx.channel());
		session.setAccount(sessionId);
		session.setSource(wrapper.getSource());
		session.setAppKey(model.getAppKey());
		session.setDeviceId(model.getDeviceId());
		session.setPlatform(model.getPlatform());
		session.setPlatformVersion(model.getPlatformVersion());
		session.setSign(model.getSign());
		session.setBindTime(System.currentTimeMillis());
		session.setUpdateTime(session.getBindTime());

		return session;
	}

	@Override
	public boolean exist(String sessionId) {
		Session session = getSession(sessionId);
		return session != null ? true : false;
	}

}

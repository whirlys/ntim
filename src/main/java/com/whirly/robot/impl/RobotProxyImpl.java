package com.whirly.robot.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.whirly.imserver.constant.Constants;
import com.whirly.imserver.model.MessageWrapper;
import com.whirly.imserver.model.proto.MessageBodyProto;
import com.whirly.imserver.model.proto.MessageProto;
import com.whirly.robot.RobotProxy;
import com.whirly.robot.tuling.Message;
import com.whirly.robot.tuling.TulingClient;

/**
 * 图灵机器人
 * 
 * @author TDW
 *
 */
public class RobotProxyImpl implements RobotProxy {
	// api地址
	String url = "http://www.tuling123.com/openapi/api";
	private String apikey = "dc164176129c44789e04a361b6d521de";

	private String userId = "248800";

	@Override
	public MessageWrapper botMessageReply(String sessionId, String content) {

		String message = ""; // 机器人回复的消息
		try {

			// 实例化连接
			TulingClient client = new TulingClient(url, apikey);

			Message msg = client.sendMessage(content);
			System.out.println("机器人的答复：");
			System.out.println(msg.getText());
			message = msg.getText();
		} catch (Exception e) {
			message = "哎呀呀，我出bug了";
		}
		MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
		builder.setCmd(Constants.CmdType.MESSAGE);
		builder.setMsgtype(Constants.ProtobufType.REPLY);
		builder.setSender(Constants.ImServerConfig.ROBOT_SESSIONID);
		builder.setReceiver(sessionId);
		builder.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		MessageBodyProto.MessageBody.Builder msgbody = MessageBodyProto.MessageBody.newBuilder();
		msgbody.setContent(message);
		builder.setContent(msgbody.build().toByteString());
		return new MessageWrapper(MessageWrapper.MessageProtocol.REPLY, Constants.ImServerConfig.ROBOT_SESSIONID,
				sessionId, builder.build());
	}

}

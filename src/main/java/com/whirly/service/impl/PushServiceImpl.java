package com.whirly.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.whirly.imserver.connector.ImConnector;
import com.whirly.imserver.constant.Constants;
import com.whirly.imserver.model.MessageWrapper;
import com.whirly.imserver.model.PushMessageBody;
import com.whirly.imserver.model.proto.MessageBodyProto;
import com.whirly.imserver.model.proto.MessageProto;
import com.whirly.service.PushService;

@Service
public class PushServiceImpl implements PushService {

	@Autowired
	private ImConnector imConnector;

	@Override
	public int pushTimeline(Integer from, List<Integer> toList, String title, PushMessageBody pushMessageBody) {
		MessageProto.Model.Builder builder = createTimelineProtoBuilder(title, pushMessageBody);
		for (Integer to : toList) {
			builder.setSender(from.toString());
			builder.setReceiver(to.toString());
			MessageWrapper wrapper = new MessageWrapper(MessageWrapper.MessageProtocol.NOTIFY, from.toString(),
					to.toString(), builder.build());
			try {
				imConnector.pushMessage(from.toString(), wrapper);
			} catch (Exception e) {
				return 1;
			}
		}
		return 0;
	}

	private MessageProto.Model.Builder createTimelineProtoBuilder(String title, PushMessageBody pushMessageBody) {
		MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
		builder.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		builder.setCmd(Constants.CmdType.NOTIFY);
		MessageBodyProto.MessageBody.Builder body = MessageBodyProto.MessageBody.newBuilder();
		body.setContent(JSON.toJSONString(pushMessageBody)); // JSON格式字符串
		body.setTitle(title); // 标题
		body.setType(Constants.MessageType.USER_MESSAGE);
		builder.setContent(body.build().toByteString());
		return builder;
	}
}

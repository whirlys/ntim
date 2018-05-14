package com.whirly.robot;

import com.whirly.imserver.model.MessageWrapper;

public interface RobotProxy {

	MessageWrapper botMessageReply(String sessionId, String content);

}

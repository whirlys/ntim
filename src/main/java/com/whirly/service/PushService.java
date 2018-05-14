package com.whirly.service;

import java.util.List;

import com.whirly.imserver.model.PushMessageBody;

public interface PushService {

	int pushTimeline(Integer from, List<Integer> to, String title, PushMessageBody pushMessageBody);
}

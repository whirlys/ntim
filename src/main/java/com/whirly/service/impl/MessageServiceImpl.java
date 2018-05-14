package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whirly.dao.MessageMapper;
import com.whirly.form.NoticeMessageForm;
import com.whirly.form.messageSearchForm;
import com.whirly.model.Message;
import com.whirly.service.MessageService;
import com.whirly.vo.MessageVO;
import com.whirly.vo.OffLineMessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public void insert(Message userMessage) {

		messageMapper.insert(userMessage);
	}

	@Override
	public List<OffLineMessageVO> getOfflineMessageByUserId(Integer userId) {

		return messageMapper.getOfflineMessageByUserId(userId);
	}

	@Override
	public int batchUpdateReaded(List<Integer> msgIds) {

		return messageMapper.batchUpdateReaded(msgIds);
	}

	@Override
	public List<MessageVO> getNoticeMessages(messageSearchForm searchForm) {

		return messageMapper.noticeMessage(searchForm);
	}

	@Override
	public int insertNoticeMessage(Message message) {
		return messageMapper.insert(message);
	}

}

package com.whirly.service;

import java.util.List;

import com.whirly.form.NoticeMessageForm;
import com.whirly.form.messageSearchForm;
import com.whirly.model.Message;
import com.whirly.vo.MessageVO;
import com.whirly.vo.OffLineMessageVO;

public interface MessageService {

	void insert(Message userMessage);

	List<OffLineMessageVO> getOfflineMessageByUserId(Integer userId);

	int batchUpdateReaded(List<Integer> msgIds);

	List<MessageVO> getNoticeMessages(messageSearchForm searchForm);

	int insertNoticeMessage(Message message);

}

package com.whirly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whirly.dao.AnswerMapper;
import com.whirly.model.Answer;
import com.whirly.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerMapper answerMapper;

	@Override
	public int insert(Answer answer) {

		return answerMapper.insert(answer);
	}

}

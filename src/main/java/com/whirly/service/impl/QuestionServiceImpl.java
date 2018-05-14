package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.QuestionMapper;
import com.whirly.form.QuestionSearchForm;
import com.whirly.model.Question;
import com.whirly.service.QuestionService;
import com.whirly.vo.AnswerVO;
import com.whirly.vo.QuestionVO;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public int insert(Question question) {

		return questionMapper.insert(question);
	}

	@Override
	public PageInfo<Question> selectBySearchForm(QuestionSearchForm searchForm) {
		PageHelper.startPage(searchForm.getPage(), searchForm.getLimit());
		List<Question> list = questionMapper.selectBySearchForm(searchForm);
		return new PageInfo<Question>(list);
	}

	@Override
	public Question selectById(Integer questionId) {

		return questionMapper.selectByPrimaryKey(questionId);
	}

	@Override
	public QuestionVO selectVOById(Integer questionId) {

		return questionMapper.selectVOById(questionId);
	}

	@Override
	public List<AnswerVO> selectAnswersById(Integer questionId) {
		return questionMapper.selectAnswersById(questionId);
	}

}

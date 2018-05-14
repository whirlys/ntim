package com.whirly.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whirly.form.QuestionSearchForm;
import com.whirly.model.Question;
import com.whirly.vo.AnswerVO;
import com.whirly.vo.QuestionVO;

public interface QuestionService {

	int insert(Question question);

	PageInfo<Question> selectBySearchForm(QuestionSearchForm searchForm);

	Question selectById(Integer questionId);

	QuestionVO selectVOById(Integer questionId);

	List<AnswerVO> selectAnswersById(Integer questionId);

}

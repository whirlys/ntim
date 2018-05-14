package com.whirly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.whirly.form.QuestionSearchForm;
import com.whirly.model.Question;
import com.whirly.model.QuestionExample;
import com.whirly.vo.AnswerVO;
import com.whirly.vo.QuestionVO;

public interface QuestionMapper {
	long countByExample(QuestionExample example);

	int deleteByExample(QuestionExample example);

	int deleteByPrimaryKey(Integer questionId);

	int insert(Question record);

	int insertSelective(Question record);

	List<Question> selectByExampleWithRowbounds(QuestionExample example, RowBounds rowBounds);

	List<Question> selectByExample(QuestionExample example);

	Question selectByPrimaryKey(Integer questionId);

	int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

	int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

	int updateByPrimaryKeySelective(Question record);

	int updateByPrimaryKey(Question record);

	List<Question> selectBySearchForm(QuestionSearchForm searchForm);

	QuestionVO selectVOById(Integer questionId);

	List<AnswerVO> selectAnswersById(Integer questionId);
}
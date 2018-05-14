package com.whirly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.whirly.form.NoticeSearchForm;
import com.whirly.form.TimelineSearchForm;
import com.whirly.model.Timeline;
import com.whirly.model.TimelineExample;
import com.whirly.vo.NoReadTopNCVO;
import com.whirly.vo.NoReadTopNSVO;
import com.whirly.vo.NoticeIsReadVo;
import com.whirly.vo.NoticeUserVO;
import com.whirly.vo.ReceiverVO;

public interface TimelineMapper {
	long countByExample(TimelineExample example);

	int deleteByExample(TimelineExample example);

	int deleteByPrimaryKey(Integer timelineId);

	int insert(Timeline record);

	int insertSelective(Timeline record);

	List<Timeline> selectByExampleWithRowbounds(TimelineExample example, RowBounds rowBounds);

	List<Timeline> selectByExample(TimelineExample example);

	Timeline selectByPrimaryKey(Integer timelineId);

	int updateByExampleSelective(@Param("record") Timeline record, @Param("example") TimelineExample example);

	int updateByExample(@Param("record") Timeline record, @Param("example") TimelineExample example);

	int updateByPrimaryKeySelective(Timeline record);

	int updateByPrimaryKey(Timeline record);

	int batchInsert(List<Timeline> timelines);

	List<ReceiverVO> selectReceiverByNoticeId(Integer noticeId);

	List<NoticeIsReadVo> selectIsReadNumByNoticeId(Integer noticeId);

	List<NoticeUserVO> selectBySearchForm(NoticeSearchForm form);

	NoticeUserVO selectNoticeById(Integer timelineId);

	NoticeUserVO selectNoticeBySearchForm(TimelineSearchForm form);

	List<NoReadTopNSVO> noReadedTopNStudent(Integer limit);

	List<NoReadTopNCVO> noReadedTopNClass();

}
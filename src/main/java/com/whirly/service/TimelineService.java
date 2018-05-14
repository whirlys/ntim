package com.whirly.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whirly.form.NoticeSearchForm;
import com.whirly.form.TimelineSearchForm;
import com.whirly.model.Notice;
import com.whirly.model.Timeline;
import com.whirly.vo.NoReadTopNCVO;
import com.whirly.vo.NoReadTopNSVO;
import com.whirly.vo.NoticeIsReadVo;
import com.whirly.vo.NoticeUserVO;
import com.whirly.vo.ReceiverVO;

public interface TimelineService {

	int batchInsert(List<Timeline> timelines);

	List<NoticeIsReadVo> selectIsReadNumByNoticeId(Integer noticeId);

	PageInfo<NoticeUserVO> selectBySearchForm(NoticeSearchForm form);

	NoticeUserVO selectNoticeBySearchForm(TimelineSearchForm form);

	int setReadById(Integer timelineId);

	int countNotReadByUserId(Integer userId);

	List<NoReadTopNSVO> noReadedTopNStudent(Integer limit);

	List<NoReadTopNCVO> noReadedTopNClass(Integer limit);

}

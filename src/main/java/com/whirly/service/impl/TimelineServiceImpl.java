package com.whirly.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.TimelineMapper;
import com.whirly.form.NoticeSearchForm;
import com.whirly.form.TimelineSearchForm;
import com.whirly.model.Timeline;
import com.whirly.model.TimelineExample;
import com.whirly.model.TimelineExample.Criteria;
import com.whirly.service.TimelineService;
import com.whirly.vo.NoReadTopNCVO;
import com.whirly.vo.NoReadTopNSVO;
import com.whirly.vo.NoticeIsReadVo;
import com.whirly.vo.NoticeUserVO;

@Service
public class TimelineServiceImpl implements TimelineService {

	@Autowired
	private TimelineMapper timelineMapper;

	@Override
	public int batchInsert(List<Timeline> timelines) {
		return timelineMapper.batchInsert(timelines);
	}

	@Override
	public List<NoticeIsReadVo> selectIsReadNumByNoticeId(Integer noticeId) {
		return timelineMapper.selectIsReadNumByNoticeId(noticeId);
	}

	@Override
	public PageInfo<NoticeUserVO> selectBySearchForm(NoticeSearchForm form) {
		PageHelper.startPage(form.getPage(), form.getLimit());
		List<NoticeUserVO> list = timelineMapper.selectBySearchForm(form);
		return new PageInfo<NoticeUserVO>(list);
	}

	@Override
	public NoticeUserVO selectNoticeBySearchForm(TimelineSearchForm form) {
		return timelineMapper.selectNoticeBySearchForm(form);
	}

	@Override
	public int setReadById(Integer timelineId) {
		Timeline timeline = new Timeline();
		timeline.setTimelineId(timelineId);
		timeline.setIsRead(true);

		return timelineMapper.updateByPrimaryKeySelective(timeline);
	}

	@Override
	public int countNotReadByUserId(Integer userId) {
		TimelineExample example = new TimelineExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsReadEqualTo(false);
		return (int) timelineMapper.countByExample(example);
	}

	@Override
	public List<NoReadTopNSVO> noReadedTopNStudent(Integer limit) {
		return timelineMapper.noReadedTopNStudent(limit);
	}

	@Override
	public List<NoReadTopNCVO> noReadedTopNClass(Integer limit) {
		List<NoReadTopNCVO> clazz = timelineMapper.noReadedTopNClass();
		for (NoReadTopNCVO vo : clazz) {
			vo.setUnreadRate(div(vo.getCount(), vo.getStudentNum(), 2));

		}
		Collections.sort(clazz);
		for (NoReadTopNCVO vo : clazz) {
			System.out.println(vo.toString());
		}
		return clazz.subList(0, limit);
	}

	private double div(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));

		BigDecimal bd2 = new BigDecimal(Double.toString(d2));

		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}
}

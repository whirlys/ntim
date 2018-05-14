package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.internal.ws.resources.GeneratorMessages;
import com.whirly.dao.NoticeMapper;
import com.whirly.form.NoticeSearchForm;
import com.whirly.model.Notice;
import com.whirly.model.NoticeExample;
import com.whirly.model.NoticeExample.Criteria;
import com.whirly.service.NoticeService;
import com.whirly.vo.chart.BaseCountVO;
import com.whirly.vo.chart.NoticeCountByPublicerVO;
import com.whirly.vo.chart.NoticePubTimeVO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public PageInfo<Notice> selectBySearchForm(NoticeSearchForm form) {

		PageHelper.startPage(form.getPage(), form.getLimit());
		List<Notice> list = noticeMapper.selectBySearchForm(form);
		return new PageInfo<Notice>(list);
	}

	@Override
	public int insert(Notice notice) {
		return noticeMapper.insert(notice);
	}

	@Override
	public Notice selectById(Integer noticeId) {

		return noticeMapper.selectByPrimaryKey(noticeId);
	}

	@Override
	public boolean exist(Integer noticeId) {
		NoticeExample example = new NoticeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNoticeIdEqualTo(noticeId);
		return noticeMapper.countByExample(example) != 0;
	}

	@Override
	public List<NoticePubTimeVO> countPubTime() {
		return noticeMapper.countPubTime();
	}

	@Override
	public int count() {
		NoticeExample example = new NoticeExample();
		return (int) noticeMapper.countByExample(example);
	}

	@Override
	public List<BaseCountVO> countByDepartment() {
		return noticeMapper.countByDepartment();
	}

	@Override
	public List<NoticeCountByPublicerVO> countByPublicer(Integer limit) {
		return noticeMapper.countByPublicer(limit);
	}

	@Override
	public List<BaseCountVO> countByTypes(Integer limit) {
		return noticeMapper.countByTypes(limit);
	}

}

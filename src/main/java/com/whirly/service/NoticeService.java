package com.whirly.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whirly.form.NoticeSearchForm;
import com.whirly.model.Notice;
import com.whirly.vo.NoReadTopNSVO;
import com.whirly.vo.chart.BaseCountVO;
import com.whirly.vo.chart.NoticeCountByPublicerVO;
import com.whirly.vo.chart.NoticePubTimeVO;

public interface NoticeService {

	PageInfo<Notice> selectBySearchForm(NoticeSearchForm form);

	int insert(Notice notice);

	Notice selectById(Integer noticeId);

	boolean exist(Integer noticeId);

	List<NoticePubTimeVO> countPubTime();

	int count();

	List<BaseCountVO> countByDepartment();

	List<NoticeCountByPublicerVO> countByPublicer(Integer i);

	List<BaseCountVO> countByTypes(Integer limit);

}

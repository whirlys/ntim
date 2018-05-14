package com.whirly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.whirly.form.NoticeSearchForm;
import com.whirly.model.Notice;
import com.whirly.model.NoticeExample;
import com.whirly.vo.chart.BaseCountVO;
import com.whirly.vo.chart.NoticeCountByPublicerVO;
import com.whirly.vo.chart.NoticePubTimeVO;

public interface NoticeMapper {
	long countByExample(NoticeExample example);

	int deleteByExample(NoticeExample example);

	int deleteByPrimaryKey(Integer noticeId);

	int insert(Notice record);

	int insertSelective(Notice record);

	List<Notice> selectByExampleWithBLOBsWithRowbounds(NoticeExample example, RowBounds rowBounds);

	List<Notice> selectByExampleWithBLOBs(NoticeExample example);

	List<Notice> selectByExampleWithRowbounds(NoticeExample example, RowBounds rowBounds);

	List<Notice> selectByExample(NoticeExample example);

	Notice selectByPrimaryKey(Integer noticeId);

	int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

	int updateByExampleWithBLOBs(@Param("record") Notice record, @Param("example") NoticeExample example);

	int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

	int updateByPrimaryKeySelective(Notice record);

	int updateByPrimaryKeyWithBLOBs(Notice record);

	int updateByPrimaryKey(Notice record);

	List<Notice> selectBySearchForm(NoticeSearchForm form);

	List<NoticePubTimeVO> countPubTime();

	List<BaseCountVO> countByDepartment();

	List<NoticeCountByPublicerVO> countByPublicer(Integer limit);

	List<BaseCountVO> countByTypes(Integer limit);

}
package com.whirly.dao;

import com.whirly.form.JwcForm;
import com.whirly.model.JwcPost;
import com.whirly.model.JwcPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface JwcPostMapper {
	long countByExample(JwcPostExample example);

	int deleteByExample(JwcPostExample example);

	int deleteByPrimaryKey(Integer jwcpostId);

	int insert(JwcPost record);

	int insertSelective(JwcPost record);

	List<JwcPost> selectByExampleWithBLOBsWithRowbounds(JwcPostExample example, RowBounds rowBounds);

	List<JwcPost> selectByExampleWithBLOBs(JwcPostExample example);

	List<JwcPost> selectByExampleWithRowbounds(JwcPostExample example, RowBounds rowBounds);

	List<JwcPost> selectByExample(JwcPostExample example);

	JwcPost selectByPrimaryKey(Integer jwcpostId);

	int updateByExampleSelective(@Param("record") JwcPost record, @Param("example") JwcPostExample example);

	int updateByExampleWithBLOBs(@Param("record") JwcPost record, @Param("example") JwcPostExample example);

	int updateByExample(@Param("record") JwcPost record, @Param("example") JwcPostExample example);

	int updateByPrimaryKeySelective(JwcPost record);

	int updateByPrimaryKeyWithBLOBs(JwcPost record);

	int updateByPrimaryKey(JwcPost record);

	List<JwcPost> selectBySearchForm(JwcForm form);

	List<String> selectAllType();
}
package com.whirly.dao;

import com.whirly.form.PublicerSearchForm;
import com.whirly.model.Publicer;
import com.whirly.model.PublicerExample;
import com.whirly.vo.PublicerVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PublicerMapper {
	long countByExample(PublicerExample example);

	int deleteByExample(PublicerExample example);

	int deleteByPrimaryKey(Integer publicerId);

	int insert(Publicer record);

	int insertSelective(Publicer record);

	List<Publicer> selectByExampleWithBLOBsWithRowbounds(PublicerExample example, RowBounds rowBounds);

	List<Publicer> selectByExampleWithBLOBs(PublicerExample example);

	List<Publicer> selectByExampleWithRowbounds(PublicerExample example, RowBounds rowBounds);

	List<Publicer> selectByExample(PublicerExample example);

	Publicer selectByPrimaryKey(Integer publicerId);

	int updateByExampleSelective(@Param("record") Publicer record, @Param("example") PublicerExample example);

	int updateByExampleWithBLOBs(@Param("record") Publicer record, @Param("example") PublicerExample example);

	int updateByExample(@Param("record") Publicer record, @Param("example") PublicerExample example);

	int updateByPrimaryKeySelective(Publicer record);

	int updateByPrimaryKeyWithBLOBs(Publicer record);

	int updateByPrimaryKey(Publicer record);

	List<PublicerVO> selectBySearchForm(PublicerSearchForm searchForm);
}
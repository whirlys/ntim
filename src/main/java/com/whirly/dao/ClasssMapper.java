package com.whirly.dao;

import com.whirly.dto.ClasssParamDto;
import com.whirly.form.ClasssSearchForm;
import com.whirly.model.Classs;
import com.whirly.model.ClasssExample;
import com.whirly.vo.ClassVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClasssMapper {
	long countByExample(ClasssExample example);

	int deleteByExample(ClasssExample example);

	int deleteByPrimaryKey(Integer classId);

	int insert(Classs record);

	int insertSelective(Classs record);

	List<Classs> selectByExampleWithRowbounds(ClasssExample example, RowBounds rowBounds);

	List<Classs> selectByExample(ClasssExample example);

	Classs selectByPrimaryKey(Integer classId);

	int updateByExampleSelective(@Param("record") Classs record, @Param("example") ClasssExample example);

	int updateByExample(@Param("record") Classs record, @Param("example") ClasssExample example);

	int updateByPrimaryKeySelective(Classs record);

	int updateByPrimaryKey(Classs record);

	List<ClassVO> selectByParams(ClasssParamDto paramDto);

	List<Integer> selectAllIds();

	int batchInsert(List<ClasssParamDto> batchImportClassList);

	List<ClassVO> selectBySearchForm(ClasssSearchForm form);
}
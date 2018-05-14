package com.whirly.dao;

import com.whirly.model.Field;
import com.whirly.model.FieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FieldMapper {
	long countByExample(FieldExample example);

	int deleteByExample(FieldExample example);

	int deleteByPrimaryKey(Integer fieldId);

	int insert(Field record);

	int insertSelective(Field record);

	List<Field> selectByExampleWithRowbounds(FieldExample example, RowBounds rowBounds);

	List<Field> selectByExample(FieldExample example);

	Field selectByPrimaryKey(Integer fieldId);

	int updateByExampleSelective(@Param("record") Field record, @Param("example") FieldExample example);

	int updateByExample(@Param("record") Field record, @Param("example") FieldExample example);

	int updateByPrimaryKeySelective(Field record);

	int updateByPrimaryKey(Field record);

	int batchInsert(List<Field> fields);

	List<Field> selectByFormId(Integer formId);
}
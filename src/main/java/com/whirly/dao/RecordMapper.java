package com.whirly.dao;

import com.whirly.dto.RecordParamDto;
import com.whirly.model.Record;
import com.whirly.model.RecordExample;
import com.whirly.vo.RecordVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RecordMapper {
	long countByExample(RecordExample example);

	int deleteByExample(RecordExample example);

	int deleteByPrimaryKey(Integer recordId);

	int insert(Record record);

	int insertSelective(Record record);

	List<Record> selectByExampleWithRowbounds(RecordExample example, RowBounds rowBounds);

	List<Record> selectByExample(RecordExample example);

	Record selectByPrimaryKey(Integer recordId);

	int updateByExampleSelective(@Param("record") Record record, @Param("example") RecordExample example);

	int updateByExample(@Param("record") Record record, @Param("example") RecordExample example);

	int updateByPrimaryKeySelective(Record record);

	int updateByPrimaryKey(Record record);

	Record selectByDto(RecordParamDto recordParamDto);

	List<RecordVO> selectByFormId(Integer formId);

	int batchInsert(List<Record> records);
}
package com.whirly.service;

import java.util.List;

import com.whirly.dto.RecordParamDto;
import com.whirly.model.Record;
import com.whirly.vo.RecordVO;

public interface RecordService {

	Record selectByRecord(RecordParamDto recordParamDto);

	int insert(Record record);

	List<RecordVO> selectByFormId(Integer formId);

	int batchInsert(List<Record> records);

	int update(Record record);

	Integer countNum(boolean b);

}

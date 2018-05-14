package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whirly.dao.RecordMapper;
import com.whirly.dto.RecordParamDto;
import com.whirly.model.Record;
import com.whirly.model.RecordExample;
import com.whirly.model.RecordExample.Criteria;
import com.whirly.service.RecordService;
import com.whirly.vo.RecordVO;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordMapper recordMapper;

	@Override
	public Record selectByRecord(RecordParamDto recordParamDto) {

		return recordMapper.selectByDto(recordParamDto);
	}

	@Override
	public int insert(Record record) {

		return recordMapper.insert(record);
	}

	@Override
	public List<RecordVO> selectByFormId(Integer formId) {
		return recordMapper.selectByFormId(formId);
	}

	@Override
	public int batchInsert(List<Record> records) {

		return recordMapper.batchInsert(records);
	}

	@Override
	public int update(Record record) {
		return recordMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer countNum(boolean bool) {
		RecordExample example = new RecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andFilledEqualTo(bool);
		return (int) recordMapper.countByExample(example);
	}

}

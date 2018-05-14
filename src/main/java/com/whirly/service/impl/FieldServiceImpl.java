package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whirly.dao.FieldMapper;
import com.whirly.model.Field;
import com.whirly.service.FieldService;

@Service
public class FieldServiceImpl implements FieldService {

	@Autowired
	private FieldMapper fieldMapper;

	@Override
	public int batchInsert(List<Field> fields) {

		return fieldMapper.batchInsert(fields);
	}

}

package com.whirly.service;

import java.util.List;

import com.whirly.model.Field;

public interface FieldService {

	int batchInsert(List<Field> fields);

}

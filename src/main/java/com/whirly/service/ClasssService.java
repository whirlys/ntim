package com.whirly.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whirly.form.ClasssSearchForm;
import com.whirly.model.Classs;
import com.whirly.model.ClasssExample;
import com.whirly.model.Department;
import com.whirly.vo.ClassVO;

public interface ClasssService {

	Classs selectById(Integer id);

	int insert(Classs classs);

	int update(Classs classs);

	List<Classs> selectByDepartmentId(Integer depId);

	PageInfo<ClassVO> selectBySearchForm(ClasssSearchForm form);
}

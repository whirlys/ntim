package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.ClasssMapper;
import com.whirly.form.ClasssSearchForm;
import com.whirly.model.Classs;
import com.whirly.model.ClasssExample;
import com.whirly.model.ClasssExample.Criteria;
import com.whirly.model.Department;
import com.whirly.service.ClasssService;
import com.whirly.vo.ClassVO;

@Service
public class ClasssServiceImpl implements ClasssService {

	@Autowired
	private ClasssMapper classsMapper;

	@Override
	public Classs selectById(Integer id) {

		return classsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(Classs classs) {

		return classsMapper.insert(classs);
	}

	@Override
	public int update(Classs classs) {

		return classsMapper.updateByPrimaryKey(classs);
	}

	@Override
	public List<Classs> selectByDepartmentId(Integer depId) {
		ClasssExample example = new ClasssExample();
		Criteria criteria = example.createCriteria();
		criteria.andDepartmentIdEqualTo(depId);
		example.setOrderByClause(" grade asc, profession , class_number asc");
		return classsMapper.selectByExample(example);
	}

	@Override
	public PageInfo<ClassVO> selectBySearchForm(ClasssSearchForm form) {
		PageHelper.startPage(form.getPage(), form.getLimit());
		List<ClassVO> list = classsMapper.selectBySearchForm(form);
		return new PageInfo<ClassVO>(list);
	}

}

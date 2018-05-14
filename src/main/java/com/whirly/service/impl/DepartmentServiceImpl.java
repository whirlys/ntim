package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.DepartmentMapper;
import com.whirly.model.Department;
import com.whirly.model.DepartmentExample;
import com.whirly.model.DepartmentExample.Criteria;
import com.whirly.service.DepartmentService;
import com.whirly.vo.chart.DepartmentUserVO;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> selectAll() {

		return departmentMapper.selectByExample(new DepartmentExample());
	}

	@Override
	public int insert(Department department) {

		return departmentMapper.insert(department);
	}

	@Override
	public Department selectById(Integer id) {

		return departmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Department selectByClassId(Integer classId) {

		return departmentMapper.selectByClassId(classId);
	}

	@Override
	public List<DepartmentUserVO> countUsers() {
		return departmentMapper.countUsers();
	}

	@Override
	public List<DepartmentUserVO> countPublicers() {
		return departmentMapper.countPublicers();
	}
}

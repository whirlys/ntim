package com.whirly.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whirly.model.Department;
import com.whirly.vo.chart.DepartmentUserVO;

public interface DepartmentService {

	List<Department> selectAll();

	int insert(Department department);

	Department selectById(Integer id);

	Department selectByClassId(Integer classId);

	List<DepartmentUserVO> countUsers();

	List<DepartmentUserVO> countPublicers();

}

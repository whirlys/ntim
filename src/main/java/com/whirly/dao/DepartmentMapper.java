package com.whirly.dao;

import com.whirly.model.Department;
import com.whirly.model.DepartmentExample;
import com.whirly.vo.chart.DepartmentUserVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DepartmentMapper {
	long countByExample(DepartmentExample example);

	int deleteByExample(DepartmentExample example);

	int deleteByPrimaryKey(Integer departmentId);

	int insert(Department record);

	int insertSelective(Department record);

	List<Department> selectByExampleWithRowbounds(DepartmentExample example, RowBounds rowBounds);

	List<Department> selectByExample(DepartmentExample example);

	Department selectByPrimaryKey(Integer departmentId);

	int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

	int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

	int updateByPrimaryKeySelective(Department record);

	int updateByPrimaryKey(Department record);

	Department selectByClassId(Integer classId);

	List<DepartmentUserVO> countUsers();

	List<DepartmentUserVO> countPublicers();
}
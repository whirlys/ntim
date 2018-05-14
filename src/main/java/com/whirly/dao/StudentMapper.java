package com.whirly.dao;

import com.whirly.form.StudentSearchForm;
import com.whirly.model.Student;
import com.whirly.model.StudentExample;
import com.whirly.vo.StudentVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StudentMapper {
	long countByExample(StudentExample example);

	int deleteByExample(StudentExample example);

	int deleteByPrimaryKey(Integer studentId);

	int insert(Student record);

	int insertSelective(Student record);

	List<Student> selectByExampleWithRowbounds(StudentExample example, RowBounds rowBounds);

	List<Student> selectByExample(StudentExample example);

	Student selectByPrimaryKey(Integer studentId);

	int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

	int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);

	int batchInsert(List<Student> students);

	List<StudentVO> selectByids(List<Integer> ids);

	List<StudentVO> selectBySearchForm(StudentSearchForm form);

	StudentVO selectVOByUserId(Integer userId);

	List<StudentVO> selectVOByClassId(Integer classsId);
}
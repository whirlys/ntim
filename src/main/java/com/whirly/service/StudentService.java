package com.whirly.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whirly.form.StudentSearchForm;
import com.whirly.model.Student;
import com.whirly.model.StudentExample;
import com.whirly.util.Msg;
import com.whirly.vo.StudentVO;

public interface StudentService {

	PageInfo<Student> selectByExample(StudentExample example, Integer page, Integer pageSize);

	Student selectById(Integer studentId);

	int insert(Student student);

	Msg saveExcelStrings(List<String[]> res);

	PageInfo<StudentVO> selectBySearchForm(StudentSearchForm form);

	int update(Student student);

	Student selectByUserId(Integer userId);

	StudentVO selectVOByUserId(Integer userId);

	List<StudentVO> selectVOByClassId(Integer classsId);

	int count();
}

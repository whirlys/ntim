package com.whirly.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.ClasssMapper;
import com.whirly.dao.StudentMapper;
import com.whirly.dao.UserMapper;
import com.whirly.dto.ClasssParamDto;
import com.whirly.form.StudentSearchForm;
import com.whirly.model.Department;
import com.whirly.model.Student;
import com.whirly.model.StudentExample;
import com.whirly.model.StudentExample.Criteria;
import com.whirly.model.User;
import com.whirly.service.DepartmentService;
import com.whirly.service.StudentService;
import com.whirly.util.ExcelUtil;
import com.whirly.util.Msg;
import com.whirly.vo.ClassVO;
import com.whirly.vo.StudentVO;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ClasssMapper classsMapper;

	@Autowired
	private DepartmentService departmentService;

	@Override
	public PageInfo<Student> selectByExample(StudentExample example, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Student> list = studentMapper.selectByExample(example);
		PageInfo<Student> pageInfo = new PageInfo<Student>(list);
		return pageInfo;
	}

	@Override
	public Student selectById(Integer studentId) {
		return studentMapper.selectByPrimaryKey(studentId);
	}

	@Override
	public int insert(Student student) {
		User user = student.getUser();
		user.setCreatetime(new Date());
		userMapper.insert(user);
		student.setUserId(user.getUserId());
		return studentMapper.insert(student);
	}

	@Override
	public Msg saveExcelStrings(List<String[]> res) {
		// res 的第一行为 表头
		Msg msg = checkExcelStrings(res);
		if (msg.getCode() == Msg.ERROR) {
			return msg;
		}
		Map<String, ClassVO> classExistsMap = (Map<String, ClassVO>) msg.getMap().get("classVOMap");
		Map<String, Integer> map = (Map<String, Integer>) msg.getMap().get("excelRowMap");
		// 接下来构造对象列表，批量插入数据库
		List<User> users = new LinkedList<User>();
		List<Student> students = new LinkedList<Student>();
		ClasssParamDto paramsDto = new ClasssParamDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

		for (int i = 1; i < res.size(); i++) {
			String[] row = res.get(i);

			// 获取excel中的数据，用来构造User和Student对象
			String username = getRowItem(row, map, "姓名");
			String account = getRowItem(row, map, "学号");
			String departmentName = getRowItem(row, map, "学院");
			Integer classNumber = Integer.parseInt(getRowItem(row, map, "班级号"));
			Integer grade = Integer.parseInt("20" + getRowItem(row, map, "年级"));
			String profession = getRowItem(row, map, "专业");

			Boolean sex = getRowItem(row, map, "性别").equals("") ? null : parseSex(getRowItem(row, map, "性别"));

			Date birthday = null;
			try {
				birthday = getRowItem(row, map, "生日").equals("") ? null : sdf.parse(getRowItem(row, map, "生日"));
			} catch (ParseException e) {

			}
			String studentType = getRowItem(row, map, "学生类型").equals("") ? null : getRowItem(row, map, "学生类型");
			String longPhone = getRowItem(row, map, "长号").equals("") ? null : getRowItem(row, map, "长号");
			String shortPhone = getRowItem(row, map, "短号").equals("") ? null : getRowItem(row, map, "短号");
			String duty = getRowItem(row, map, "职务").equals("") ? null : getRowItem(row, map, "职务");
			String familyPhone = getRowItem(row, map, "家庭电话").equals("") ? null : getRowItem(row, map, "家庭电话");
			String email = getRowItem(row, map, "邮箱").equals("") ? null : getRowItem(row, map, "邮箱");
			String qq = getRowItem(row, map, "QQ").equals("") ? null : getRowItem(row, map, "QQ");
			String idNumber = getRowItem(row, map, "身份证号").equals("") ? null : getRowItem(row, map, "身份证号");
			String homeAddress = getRowItem(row, map, "家庭住址").equals("") ? null : getRowItem(row, map, "家庭住址");

			// 构造User
			User user = User.createDefault();
			user.setUsername(username);
			user.setAccount(account);

			// 构造Student
			// 获取班级

			paramsDto.setAll(departmentName, profession, grade, classNumber);
			Integer classId = classExistsMap.get(paramsDto.getClassName()).getClassId();
			Student student = new Student();
			student.setAllNoUser(classId, sex, birthday, studentType, longPhone, shortPhone, duty, familyPhone, email,
					qq, idNumber, homeAddress);
			// 添加到队列
			users.add(user);
			students.add(student);

		}
		// 批量插入数据库
		System.out.println("==============批量插入===========");
		try {
			batchInsertStudent(users, students);
		} catch (Exception e) {
			e.printStackTrace();
			msg = Msg.error();
			msg.setMsg("数据插入时出现错误");
			return msg;
		}
		List<Integer> ids = new LinkedList<Integer>();
		for (Student student : students) {
			ids.add(student.getStudentId());
		}
		List<StudentVO> studentVOs = studentMapper.selectByids(ids);
		msg = Msg.success();
		msg.setMsg("数据导入成功！导入的数据如下列表所示");
		msg.add("studentvos", studentVOs);
		return msg;
	}

	private int batchInsertStudent(List<User> users, List<Student> students) {
		// TODO 这里需要事务
		userMapper.batchInsert(users);
		for (int i = 0; i < users.size(); i++) {
			students.get(i).setUser(users.get(i));
		}
		studentMapper.batchInsert(students);
		return 0;
	}

	private String getRowItem(String[] row, Map<String, Integer> map, String rowName) {
		if (map.get(rowName) == -1) {
			return null;
		}
		return row[map.get(rowName)].trim();
	}

	private boolean parseSex(String sex) {
		return sex.equals("男") ? true : false;
	}

	/**
	 * excel中必须有的字段是：姓名，学号，学院，年级，专业，班级号 检查excel中 学院，班级 是否存在于数据库中，不存在则提示先创建相应的学院和班级
	 * 
	 * @param res
	 * @return
	 */
	private Msg checkExcelStrings(List<String[]> res) {
		if (res.size() < 2) {
			Msg msg = Msg.error();
			msg.setMsg("表格数据为空！");
			return msg;
		}
		Map<String, Integer> map = ExcelUtil.ExcelHeaderMap();
		String[] rowHeader = res.get(0);
		for (int i = 0; i < rowHeader.length; i++) {
			String rowName = rowHeader[i].trim();
			if (map.containsKey(rowName)) {
				map.put(rowName, i);
			} else {
				System.out.println("字段 " + rowName + "不合法");
			}
		}
		if (map.get("姓名") == -1 || map.get("学号") == -1 || map.get("学院") == -1 || map.get("年级") == -1
				|| map.get("专业") == -1 || map.get("班级号") == -1) {
			Msg msg = Msg.error();
			msg.setMsg("表格中必须存在 姓名、学号、学院、年级、专业、班级号 信息，其它为可选字段");
			return msg;
		}
		System.out.println(map.toString());
		// 学院、班级信息 去重
		Set<ClasssParamDto> set = new LinkedHashSet<ClasssParamDto>();
		Set<String> departmentList = new LinkedHashSet<String>();

		List<String> emptyItems = new LinkedList<String>();
		List<String> accountList = new LinkedList<String>();
		for (int i = 1; i < res.size(); i++) {

			ClasssParamDto paramsDto = new ClasssParamDto();
			String[] row = res.get(i);

			String username = getRowItem(row, map, "姓名");
			String account = getRowItem(row, map, "学号");
			String departmentName = getRowItem(row, map, "学院");
			String classNumber = getRowItem(row, map, "班级号");
			String grade = "20" + getRowItem(row, map, "年级");
			String profession = getRowItem(row, map, "专业");

			if (departmentName == "" || classNumber == "" || grade == "" || profession == "" || username == ""
					|| account == "") {
				emptyItems.add(i + 1 + "");
				continue;
			}
			accountList.add(account);

			departmentList.add(departmentName);

			paramsDto.setDepartmentName(departmentName);
			paramsDto.setClassNumber(Integer.parseInt(classNumber));
			paramsDto.setGrade(Integer.parseInt(grade));
			paramsDto.setProfession(profession);

			set.add(paramsDto);

		}

		if (emptyItems.size() != 0) {
			Msg msg = Msg.error();
			msg.setMsg("第 " + emptyItems.toString() + " 等行中， 姓名、学号、学院、年级、专业、班级号 存在空项，请补充完整");
			return msg;
		}

		Iterator<ClasssParamDto> itr = set.iterator();
		List<String> classNoExistsList = new LinkedList<String>();
		Map<String, ClassVO> classExistsMap = new HashMap<String, ClassVO>();
		// List<ClasssParamDto> batchImportClassList = new LinkedList<ClasssParamDto>();
		// 从数据库中查询班级信息是否存在
		// mybatis example 条件 联表查询 ??
		while (itr.hasNext()) {
			ClasssParamDto paramDto = itr.next();

			List<ClassVO> classvoList = classsMapper.selectByParams(paramDto);
			if (classvoList.size() != 0) {
				ClassVO classVO = classvoList.get(0);
				classExistsMap.put(paramDto.getClassName(), classVO);
			} else {
				// batchImportClassList.add(paramDto);
				classNoExistsList.add(paramDto.getClassName());
			}
		}
		if (classNoExistsList.size() != 0) {
			// 批量导入班级
			/*
			 * List<Department> departments = departmentService.selectAll();
			 * System.out.println(departments.toString()); Map<String, Integer> dMap = new
			 * HashMap<String, Integer>();
			 * 
			 * for (Department d : departments) {
			 * 
			 * dMap.put(d.getName(), d.getDepartmentId()); }
			 * System.out.println("========dMap=========");
			 * System.out.println(dMap.toString()); for (ClasssParamDto dto :
			 * batchImportClassList) {
			 * dto.setDepartmentId(dMap.get(dto.getDepartmentName())); }
			 * System.out.println(batchImportClassList);
			 * classsMapper.batchInsert(batchImportClassList);
			 */

			Msg msg = Msg.error();
			msg.setMsg("班级 " + classNoExistsList.toString() + "不存在");

			return msg;
		}
		// 学号去重
		Set<String> accountSet = new HashSet<String>();
		Set<String> repeatAccount = new LinkedHashSet<String>();
		for (String acc : accountList) {
			if (accountSet.contains(acc)) {
				repeatAccount.add(acc);
			} else {
				accountSet.add(acc);
			}
		}
		if (repeatAccount.size() > 0) {
			Msg msg = Msg.error();
			msg.setMsg("表格中这些学号出现重复：" + repeatAccount.toString() + " , 学号应具有唯一性");
			return msg;
		}
		// 查找数据库，判断是否有学号已经被使用了，学号具有唯一性
		List<String> existsAccounts = userMapper.findAccounts(accountList);
		if (existsAccounts.size() != 0) {
			Msg msg = Msg.error();
			msg.setMsg("数据库中已经存在这些学号：" + existsAccounts.toString() + " , 请不要重复添加");
			return msg;
		}

		Msg msg = Msg.success();
		msg.add("classVOMap", classExistsMap);
		msg.add("excelRowMap", map);
		return msg;
	}

	public PageInfo<StudentVO> selectBySearchForm(StudentSearchForm form) {
		PageHelper.startPage(form.getPage(), form.getLimit());
		List<StudentVO> list = studentMapper.selectBySearchForm(form);
		PageInfo<StudentVO> pageInfo = new PageInfo<StudentVO>(list);
		return pageInfo;
	}

	@Override
	public int update(Student student) {
		User user = student.getUser();
		userMapper.updateByPrimaryKey(user);
		studentMapper.updateByPrimaryKey(student);
		return 0;
	}

	@Override
	public Student selectByUserId(Integer userId) {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return studentMapper.selectByExample(example).get(0);
	}

	@Override
	public StudentVO selectVOByUserId(Integer userId) {

		return studentMapper.selectVOByUserId(userId);
	}

	@Override
	public List<StudentVO> selectVOByClassId(Integer classsId) {

		return studentMapper.selectVOByClassId(classsId);
	}

	@Override
	public int count() {
		StudentExample example = new StudentExample();
		return (int) studentMapper.countByExample(example);
	}

}

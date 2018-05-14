package com.whirly.controller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.whirly.form.ClasssForm;
import com.whirly.form.ClasssSearchForm;
import com.whirly.form.DepartmentForm;
import com.whirly.form.StudentForm;
import com.whirly.form.StudentSearchForm;
import com.whirly.model.Classs;
import com.whirly.model.Department;
import com.whirly.model.Student;
import com.whirly.model.User;
import com.whirly.service.ClasssService;
import com.whirly.service.DepartmentService;
import com.whirly.service.FormService;
import com.whirly.service.NoticeService;
import com.whirly.service.PublicerService;
import com.whirly.service.StudentService;
import com.whirly.util.Constants;
import com.whirly.util.ExcelUtil;
import com.whirly.util.Msg;
import com.whirly.vo.ClassVO;
import com.whirly.vo.StudentVO;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ClasssService classsService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private PublicerService publicerService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private FormService formService;

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 转换日期
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}

	/**
	 * 获取后台主页
	 */
	@RequestMapping("")
	public String index() {
		return "admin/index";
	}

	/**
	 * 后台控制面板
	 * 
	 * @return
	 */
	@RequestMapping("main")
	public String main(Model model) {
		int studentNum = studentService.count();
		int publicerNum = publicerService.count();
		int noticeNum = noticeService.count();
		int formNum = formService.count();
		model.addAttribute("studentNum", studentNum);
		model.addAttribute("publicerNum", publicerNum);
		model.addAttribute("noticeNum", noticeNum);
		model.addAttribute("formNum", formNum);
		return "admin/main";
	}

	/**
	 * 学院列表
	 */
	@RequestMapping("departmentlist")
	public ModelAndView departmentlist() {
		List<Department> list = departmentService.selectAll();

		ModelAndView mv = new ModelAndView();
		mv.addObject("dlist", list);
		mv.setViewName("admin/department/list");
		return mv;
	}

	/**
	 * 院系列表AJAX
	 */
	@RequestMapping("departmentlistajax")
	@ResponseBody
	public List<Department> departmentlistajax() {
		List<Department> list = departmentService.selectAll();
		return list;
	}

	/**
	 * 添加学院表单
	 */
	@RequestMapping(value = "departmentadd", method = RequestMethod.GET)
	public ModelAndView departmentadd() {
		Department department = new Department();

		ModelAndView mv = new ModelAndView();
		mv.addObject("department", department);
		mv.setViewName("admin/department/form");
		return mv;
	}

	/**
	 * 提交学院表单
	 */
	@RequestMapping(value = "departmentpost", method = RequestMethod.POST)
	@ResponseBody
	public Msg departmentpost(@Valid DepartmentForm form, BindingResult result) {
		System.out.println(form.toString());
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			Msg msg = Msg.error();
			msg.setCode(300);
			msg.add("error", errorList.get(0).getDefaultMessage());
			return msg;
		}
		Department department = new Department();
		department.setName(form.getName());
		department.setRemark(form.getRemark());
		try {
			departmentService.insert(department);
		} catch (Exception e) {
			System.out.println("");
			Msg msg = Msg.error();
			msg.setMsg("该名称已存在");
			return msg;
		}

		Msg msg = Msg.success();
		msg.setMsg("提交成功");
		return msg;
	}

	/**
	 * 根据院系查看班级
	 */
	@RequestMapping("showclass")
	public ModelAndView showclass(@RequestParam("departmentid") Integer departmentid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/department/showclass");
		List<Classs> list = classsService.selectByDepartmentId(departmentid);
		mv.addObject("classlist", list);
		return mv;
	}

	/**
	 * get获取班级列表
	 */
	@RequestMapping("getclasss")
	public String getclasss() {
		return "admin/classs/list";
	}

	/**
	 * ajax获取班级列表，分页带多个参数
	 */
	@RequestMapping(value = "classslist")
	@ResponseBody
	public Map<String, Object> classslist(ClasssSearchForm form) {

		System.out.println(form.toString());

		PageInfo<ClassVO> pageInfo = classsService.selectBySearchForm(form);
		List<ClassVO> list = pageInfo.getList();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "请求成功！");

		map.put("count", pageInfo.getTotal());
		map.put("data", list);

		return map;
	}

	/**
	 * 添加班级get
	 */
	@RequestMapping("classform")
	public ModelAndView classform(@RequestParam(value = "classsid", required = false) Integer classsid) {
		Classs classs = null;
		if (null != classsid) {
			classs = classsService.selectById(classsid);
		} else {
			classs = new Classs();
		}
		List<Department> list = departmentService.selectAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/classs/form");
		mv.addObject("classs", classs);
		mv.addObject("departments", list);
		return mv;
	}

	/**
	 * 提交班级表单
	 */
	@RequestMapping(value = "classspost", method = RequestMethod.POST)
	@ResponseBody
	public Msg classspost(@Valid ClasssForm form, BindingResult result,
			@RequestParam(value = "classsId") Integer classsId) {
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			Msg msg = Msg.error();
			msg.setCode(300);
			msg.add("error", errorList.get(0).getDefaultMessage());
			return msg;
		}

		Department department = departmentService.selectById(form.getDepartmentId());
		if (null == department) {
			Msg msg = Msg.error();
			msg.setMsg("院系不存在");
			return msg;
		}
		Classs classs = null;
		if (null == classsId) {
			classs = new Classs();
			classs.setDepartmentId(form.getDepartmentId());
			classs.setProfession(form.getProfession());
			classs.setGrade(form.getGrade());
			classs.setClassNumber(form.getClassNumber());
			classsService.insert(classs);
		} else {
			classs = classsService.selectById(classsId);
			if (null == classs) {
				Msg msg = Msg.error();
				msg.setMsg("班级不存在");
				return msg;
			}
			classs.setDepartmentId(form.getDepartmentId());
			classs.setProfession(form.getProfession());
			classs.setGrade(form.getGrade());
			classs.setClassNumber(form.getClassNumber());
			classsService.update(classs);
		}

		Msg msg = Msg.success();
		msg.setMsg("提交成功");
		return msg;
	}

	@RequestMapping("showStudent")
	public String showStudent(@RequestParam("classsid") Integer classsId, Model model) {
		List<StudentVO> students = studentService.selectVOByClassId(classsId);
		model.addAttribute("students", students);
		return "admin/classs/showStudent";
	}

	/**
	 * 获取学生列表
	 */
	@RequestMapping("stulist")
	public String studentlist() {
		return "admin/student/list";
	}

	/**
	 * ajax获取学生列表，分页带多个参数
	 */
	@RequestMapping(value = "studentlist")
	@ResponseBody
	public Map<String, Object> studentlist(StudentSearchForm searchForm) {

		Map<String, Object> map = new HashMap<String, Object>();

		PageInfo<StudentVO> pageInfo = studentService.selectBySearchForm(searchForm);
		List<StudentVO> list = pageInfo.getList();

		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		/*
		 * code: 0, msg: "", count: 1000, data: []
		 */
		return map;
	}

	/**
	 * 添加学生表单
	 */
	@RequestMapping(value = "stuform", method = RequestMethod.GET)
	public ModelAndView getstuadd(@RequestParam(value = "studentId", required = false) Integer studentId) {
		List<Department> dlist = departmentService.selectAll();
		Student student = null;

		if (null != studentId) {
			student = studentService.selectById(studentId);
		} else {
			student = new Student();
		}
		Integer departmentId = null;
		if (student.getStudentId() != null) {
			departmentId = student.getClasss().getDepartmentId();
		}
		System.out.println("departmentId:" + departmentId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/student/form");
		mv.addObject("student", student);
		mv.addObject("departments", dlist);
		mv.addObject("dpId", departmentId);
		return mv;
	}

	/**
	 * 提交学生表单
	 */
	@RequestMapping(value = "studentpost", method = RequestMethod.POST)
	@ResponseBody
	public Msg studentpost(@Valid StudentForm form, BindingResult result) {
		System.out.println("==============接收到的student表单========");
		System.out.println(form.toString());
		Integer studentId = form.getStudentId();
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			Msg msg = Msg.error();
			msg.setCode(300);
			msg.add("error", errorList.get(0).getDefaultMessage());
			return msg;
		}
		Classs classs = classsService.selectById(form.getClassId());
		if (null == classs) {
			Msg msg = Msg.error();
			msg.add("error", "班级不存在");
			return msg;
		}

		Student student = null;
		if (null == studentId) {
			System.out.println("======add student=====");
			student = new Student();
			User user = User.createDefault();
			user.setUsername(form.getUsername());
			user.setRole(Constants.UserType.STUDENT);
			user.setAccount(form.getAccount());

			student.setUser(user);
			student.setClassId(form.getClassId());
			student.setBirthday(form.getBirthday());
			student.setDuty(form.getDuty());
			student.setEmail(form.getEmail());
			student.setFamilyPhone(form.getFamilyPhone());
			student.setHomeAddress(form.getHomeAddress());
			student.setIdNumber(form.getIdNumber());
			student.setLongPhone(form.getLongPhone());
			student.setShortPhone(form.getShortPhone());
			student.setQq(form.getQq());
			student.setSex(form.getSex());
			student.setStudentType(form.getStudentType());

			studentService.insert(student);
		} else {
			System.out.println("======update student=====");
			student = studentService.selectById(studentId);
			User user = student.getUser();
			user.setUsername(form.getUsername());
			user.setRole(Constants.UserType.STUDENT);
			user.setAccount(form.getAccount());

			student.setUser(user);
			student.setClassId(form.getClassId());
			student.setBirthday(form.getBirthday());
			student.setDuty(form.getDuty());
			student.setEmail(form.getEmail());
			student.setFamilyPhone(form.getFamilyPhone());
			student.setHomeAddress(form.getHomeAddress());
			student.setIdNumber(form.getIdNumber());
			student.setLongPhone(form.getLongPhone());
			student.setShortPhone(form.getShortPhone());
			student.setQq(form.getQq());
			student.setSex(form.getSex());
			student.setStudentType(form.getStudentType());

			studentService.update(student);
		}

		Msg msg = Msg.success();
		msg.setMsg("提交成功");
		return msg;
	}

	/**
	 * 根据学院获取班级列表
	 */
	@RequestMapping(value = "selectClasssByDep")
	@ResponseBody
	public Map<String, Object> selectClasssByDep(@RequestParam("departmentId") Integer departmentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Classs> list = classsService.selectByDepartmentId(departmentId);
		map.put("classslist", list);
		return map;
	}

	/**
	 * 批量导入学生页面
	 */
	@RequestMapping("tobatchimport")
	public String to_batchImport() {

		return "admin/student/batchimport";
	}

	/**
	 * 从Excel批量导入学生
	 */
	@RequestMapping(value = "excelUpload", method = RequestMethod.POST)
	@ResponseBody
	public Msg batchImportStudent(HttpServletRequest request) {
		Msg msg = null;
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multiRequest.getFile("file");
			try {
				// excelUtil工具类
				List<String[]> res = ExcelUtil.readExcel(file);
				if (res != null && res.size() > 0) {
					msg = studentService.saveExcelStrings(res);
				} else {
					msg = Msg.error();
					msg.setMsg("表格数据为空！");
					return msg;
				}
			} catch (IOException e) {
				msg = Msg.error();
				msg.setMsg(e.getMessage());
				return msg;
			}
		}
		msg.setCode(Msg.SUCCESS);
		return msg;
	}

}

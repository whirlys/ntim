package com.whirly.controller.student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.whirly.dto.RecordParamDto;
import com.whirly.exception.NotAllowException;
import com.whirly.form.JwcForm;
import com.whirly.form.NoticeSearchForm;
import com.whirly.form.PasswordForm;
import com.whirly.form.StudentUserForm;
import com.whirly.form.TimelineSearchForm;
import com.whirly.model.Form;
import com.whirly.model.JwcPost;
import com.whirly.model.Record;
import com.whirly.model.Student;
import com.whirly.model.Supplement;
import com.whirly.model.User;
import com.whirly.service.FormService;
import com.whirly.service.JwcPostService;
import com.whirly.service.NoticeService;
import com.whirly.service.RecordService;
import com.whirly.service.StudentService;
import com.whirly.service.SupplementService;
import com.whirly.service.TimelineService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.NoticeIsReadVo;
import com.whirly.vo.NoticeUserVO;
import com.whirly.vo.StudentVO;
import com.whirly.vo.UserVO;

@Controller
@RequestMapping("student")
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwcPostService jwcService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	TimelineService timelineService;

	@Autowired
	private SupplementService supplementService;

	@Autowired
	private FormService formService;

	@Autowired
	private RecordService recordService;

	@RequestMapping("")
	public String index(Model model, HttpSession session) {

		model.addAttribute("jsessionid", session.getId());
		return "student/index";
	}

	@RequestMapping("notReadNum")
	@ResponseBody
	public Msg notRead(HttpSession session) {
		User user = (User) session.getAttribute("user");
		int notReadNum = timelineService.countNotReadByUserId(user.getUserId());
		Msg msg = Msg.success();
		msg.add("notReadNum", notReadNum);
		return msg;
	}

	@RequestMapping("main")
	public String main(Model model, HttpSession session) {
		List<String> types = jwcService.selectAllType();
		model.addAttribute("types", types);
		return "student/main";
	}

	@RequestMapping("info")
	public String info(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		StudentVO vo = studentService.selectVOByUserId(user.getUserId());
		model.addAttribute("studentVo", vo);
		return "student/info";
	}

	@RequestMapping("editInfo")
	@ResponseBody
	public Msg editInfo(@RequestBody StudentUserForm form, HttpSession session) {
		Student student = (Student) session.getAttribute("student");
		if (null == student) {
			logger.warn("session student equal null, session中不存在 student");
			return Msg.error("不是学生， 或学生未登录！");
		}
		student.setBirthday(form.getBirthday());
		student.setDuty(form.getDuty());
		student.setEmail(form.getEmail());
		student.setFamilyPhone(form.getFamilyPhone());
		student.setHomeAddress(form.getHomeAddress());
		student.setIdNumber(form.getIdNumber());
		student.setLongPhone(form.getLongPhone());
		student.setShortPhone(form.getShortPhone());
		student.setSex(form.getSex());

		studentService.update(student);
		session.setAttribute("student", student);
		return Msg.success();
	}

	@RequestMapping("changePassword")
	@ResponseBody
	public Msg changePassword(@RequestBody PasswordForm form, HttpSession session) {
		if (!form.getNewpassword().equals(form.getRepassword())) {
			return Msg.error("两次密码不一致！");
		}
		User user = (User) session.getAttribute("user");
		boolean status = userService.checkPassword(user, form.getPassword());
		if (status == false) {
			return Msg.error("用户名或密码不正确");
		}
		user.setPassword(form.getNewpassword());
		userService.updatePassword(user);
		return Msg.success();
	}

	@RequestMapping("jwcposts")
	@ResponseBody
	public Map<String, Object> jwcposts(JwcForm form, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) session.getAttribute("user");
		PageInfo<JwcPost> pageInfo = jwcService.selectBySearchForm(form);
		List<JwcPost> list = pageInfo.getList();

		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		return map;
	}

	@RequestMapping("toNotices")
	public String toNotices(Model model, HttpSession session) {
		Student student = (Student) session.getAttribute("student");
		List<UserVO> publicerVos = userService.selectPublicersByStudentId(student.getStudentId());
		model.addAttribute("publicerVos", publicerVos);
		return "student/notices";
	}

	@RequestMapping("notices")
	@ResponseBody
	public Map<String, Object> notices(NoticeSearchForm form, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) session.getAttribute("user");
		form.setUserId(user.getUserId());
		PageInfo<NoticeUserVO> pageInfo = timelineService.selectBySearchForm(form);
		List<NoticeUserVO> list = pageInfo.getList();

		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		return map;
	}

	@RequestMapping("notice/{timelineId}")
	public String noticeDetail(@PathVariable("timelineId") Integer timelineId, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		TimelineSearchForm form = new TimelineSearchForm();
		form.setTimelineId(timelineId);
		form.setUserId(user.getUserId());
		NoticeUserVO notice = timelineService.selectNoticeBySearchForm(form);
		List<Supplement> supplements = supplementService.selectByNoticeId(notice.getNoticeId());
		List<NoticeIsReadVo> isReadVos = timelineService.selectIsReadNumByNoticeId(notice.getNoticeId());
		Integer readNum = 0, notReadNum = 0;
		for (NoticeIsReadVo vo : isReadVos) {
			if (vo.isRead() == true) {
				readNum = vo.getNum();
			} else {
				notReadNum = vo.getNum();
			}
		}
		if (notice.getIsRead() == false) {
			timelineService.setReadById(timelineId);
		}

		model.addAttribute("notice", notice);
		model.addAttribute("supplements", supplements);
		model.addAttribute("readNum", readNum);
		model.addAttribute("notReadNum", notReadNum);
		return "student/noticeDetail";
	}

	@RequestMapping(value = "form/{formId}", method = RequestMethod.GET)
	public String form(Model model, HttpSession session, @PathVariable("formId") Integer formId) {
		Student student = (Student) session.getAttribute("student");
		if (null == student) {
			logger.warn("student not in session, 不是学生用户");
			throw new NotAllowException("不是学生！");
		}

		Form form = formService.selectById(formId);
		if (null == form) {
			logger.warn("form {} not exist!", formId);
		}
		RecordParamDto dto = new RecordParamDto();
		dto.setFormId(formId);
		dto.setUserId(student.getUserId());
		Record record = recordService.selectByRecord(dto);

		if (form.getDeadline().compareTo(new Date()) < 0) {
			model.addAttribute("msg", "已过期限！");
			return "student/recorded";
		}
		if (record == null) {
			model.addAttribute("msg", "您无被要求填写表单");
			return "student/recorded";
		}
		if (record.getFilled() == true) {
			model.addAttribute("msg", "您已经填过该表单了");
			return "student/recorded";
		}
		model.addAttribute("form", form);
		return "student/formForm";
	}

	@RequestMapping(value = "form/{formId}", method = RequestMethod.POST)
	@ResponseBody
	public Msg formpost(HttpSession session, @PathVariable("formId") Integer formId, @RequestBody String value) {
		Student student = (Student) session.getAttribute("student");
		if (null == student) {
			logger.warn("student not in session, 不是学生用户");
			throw new NotAllowException("不是学生！");
		}
		Form form = formService.selectById(formId);
		if (form.getDeadline().compareTo(new Date()) < 0) {
			return Msg.error("已过期限！");
		}

		RecordParamDto dto = new RecordParamDto();
		dto.setFormId(formId);
		dto.setUserId(student.getUserId());
		Record record = recordService.selectByRecord(dto);
		if (record == null) {
			return Msg.error("您无被要求填写表单");
		}
		if (record.getFilled() == true) {
			return Msg.error("您已经填过该表单了");
		}
		record.setFormId(formId);
		record.setUserId(student.getUserId());
		record.setValue(value);
		record.setFilled(true);
		recordService.update(record);
		return Msg.success();
	}
}

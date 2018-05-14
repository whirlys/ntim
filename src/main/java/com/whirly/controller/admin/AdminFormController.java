package com.whirly.controller.admin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.whirly.enums.NoticeLever;
import com.whirly.enums.NoticeType;
import com.whirly.form.FormForm;
import com.whirly.form.FormSearchForm;
import com.whirly.model.Department;
import com.whirly.model.Field;
import com.whirly.model.Form;
import com.whirly.model.Notice;
import com.whirly.model.Record;
import com.whirly.model.Timeline;
import com.whirly.model.User;
import com.whirly.service.DepartmentService;
import com.whirly.service.FieldService;
import com.whirly.service.FormService;
import com.whirly.service.NoticeService;
import com.whirly.service.RecordService;
import com.whirly.service.TimelineService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.UserVO;

@Controller
@RequestMapping("admin/form")
public class AdminFormController {
	@Autowired
	private FormService formService;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private RecordService recordService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private TimelineService timelineService;

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public String toForm(HttpSession session, Model model) {
		return "admin/form/list";
	}

	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> forms(FormSearchForm form, HttpSession session) {
		System.out.println("=========form-==========");
		System.out.println(form.toString());
		Map<String, Object> map = new HashMap<String, Object>();

		PageInfo<Form> pageInfo = formService.selectBySearchForm(form);

		List<Form> list = pageInfo.getList();
		System.out.println(list.toString());

		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		return map;
	}

	@RequestMapping("toAdd")
	public String toAddForm(HttpSession session, Model model) {
		List<Department> departments = departmentService.selectAll();
		model.addAttribute("departments", departments);
		return "admin/form/form";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Msg addForm(HttpSession session, @RequestBody FormForm formForm, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(formForm.toString());

		Integer[] depids = new Integer[formForm.getReceivers().size()];
		formForm.getReceivers().toArray(depids);
		List<UserVO> receivers = userService.selectInDepId(depids);
		System.out.println(receivers.toString());

		User user = (User) session.getAttribute("user");

		List<Integer> userIds = new LinkedList<Integer>();
		for (UserVO vo : receivers) {
			userIds.add(vo.getId());
		}
		Form form = new Form();
		form.setDeadline(formForm.getDeadline());
		form.setDescription(formForm.getDescription());
		form.setFormName(formForm.getFormName());
		form.setReceivers(JSON.toJSONString(userIds));
		form.setUserId(user.getUserId());
		formService.insert(form);

		List<Field> fields = formForm.getFieldItems();
		for (Field f : fields) {
			f.setFormId(form.getFormId());
			f.setConstraint(JSON.toJSONString(f.getConstraint()));
		}
		fieldService.batchInsert(fields);

		List<Record> records = new LinkedList<Record>();
		for (UserVO vo : receivers) {
			Record record = new Record();
			record.setFilled(false);
			record.setFormId(form.getFormId());
			record.setUserId(vo.getId());
			record.setValue("{}");
			records.add(record);
		}
		recordService.batchInsert(records);

		Notice notice = new Notice();
		notice.setLever((short) NoticeLever.GENERAL.getLever());
		notice.setPublicer("管理员 " + user.getUsername());
		notice.setType(NoticeType.FORM.getName());
		notice.setTitle("关于填写 " + formForm.getFormName() + "的通知");
		notice.setUserId(user.getUserId());

		notice.setContent(formForm.getDescription() + "\n\n 请点击<a href='" //
				+ "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/student/form/" + form.getFormId() //
				+ "' target='_blank' style='color: red;'> 表单填写地址 </a> 或复制 " //
				+ "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/student/form/" + form.getFormId() + " 到浏览器中打开");//

		int status = noticeService.insert(notice);

		List<Timeline> timelines = new LinkedList<Timeline>();
		for (UserVO vo : receivers) {
			Timeline timeline = new Timeline();
			timeline.setIsRead(false);
			timeline.setNoticeId(notice.getNoticeId());
			timeline.setUserId(vo.getId());
			timelines.add(timeline);
		}
		status = timelineService.batchInsert(timelines);
		return Msg.success();
	}
}

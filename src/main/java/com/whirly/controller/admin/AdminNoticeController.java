package com.whirly.controller.admin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.whirly.form.NoticeForm;
import com.whirly.form.NoticeSearchForm;
import com.whirly.model.Department;
import com.whirly.model.Notice;
import com.whirly.model.Publicer;
import com.whirly.model.Supplement;
import com.whirly.model.Timeline;
import com.whirly.model.User;
import com.whirly.service.DepartmentService;
import com.whirly.service.NoticeService;
import com.whirly.service.SupplementService;
import com.whirly.service.TimelineService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.FriendsListVO;
import com.whirly.vo.NoticeIsReadVo;
import com.whirly.vo.UserVO;

@RequestMapping("admin/notice")
@Controller
public class AdminNoticeController {

	@Autowired
	private TimelineService timelineService;

	@Autowired
	private SupplementService supplementService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("")
	public String toNotices() {

		return "admin/notice/list";
	}

	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> notices(NoticeSearchForm form, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) session.getAttribute("user");
		// form.setUserId(user.getUserId());
		PageInfo<Notice> pageInfo = noticeService.selectBySearchForm(form);

		List<Notice> list = pageInfo.getList();
		System.out.println(list.size());
		for (Notice n : list) {
			System.out.println(n.toString());
		}

		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		return map;
	}

	@RequestMapping(value = "detail/{noticeId}")
	public String detail(@PathVariable(name = "noticeId") Integer noticeId, Model model) {
		Notice notice = noticeService.selectById(noticeId);
		List<Supplement> supplements = supplementService.selectByNoticeId(noticeId);
		List<NoticeIsReadVo> isReadVos = timelineService.selectIsReadNumByNoticeId(noticeId);
		Integer readNum = 0, notReadNum = 0;
		for (NoticeIsReadVo vo : isReadVos) {
			if (vo.isRead() == true) {
				readNum = vo.getNum();
			} else {
				notReadNum = vo.getNum();
			}
		}
		model.addAttribute("notice", notice);
		model.addAttribute("supplements", supplements);
		model.addAttribute("readNum", readNum);
		model.addAttribute("notReadNum", notReadNum);
		return "publicer/noticeDetail";
	}

	@RequestMapping("toAdd")
	public String toAddNotice(HttpSession session, Model model) {
		List<Department> departments = departmentService.selectAll();
		model.addAttribute("departments", departments);
		return "admin/notice/form";
	}

	@RequestMapping("add")
	@ResponseBody
	public Msg addNotice(NoticeForm form, HttpSession session) {
		System.out.println(form.toString());

		User user = (User) session.getAttribute("user");
		form.setUserId(user.getUserId());

		Notice notice = new Notice();
		notice.setLever(form.getLever());
		notice.setType(form.getType());
		notice.setTitle(form.getTitle());
		notice.setPublicer("系统管理员");
		notice.setContent(form.getContent());
		notice.setUserId(form.getUserId());

		int status = noticeService.insert(notice);

		List<UserVO> receivers = userService.selectInDepId(form.getReceivers());
		List<Timeline> timelines = new LinkedList<Timeline>();
		for (UserVO vo : receivers) {
			Timeline timeline = new Timeline();
			timeline.setIsRead(false);
			timeline.setNoticeId(notice.getNoticeId());
			timeline.setUserId(vo.getId());
			timelines.add(timeline);
		}
		status = timelineService.batchInsert(timelines);

		System.out.println("==========timelines==========");
		System.out.println();
		for (Timeline timeline : timelines) {
			System.out.println(timeline.toString());
		}
		return Msg.success();
	}
}

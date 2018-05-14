package com.whirly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whirly.service.DepartmentService;
import com.whirly.service.NoticeService;
import com.whirly.service.TimelineService;
import com.whirly.util.Msg;
import com.whirly.vo.NoReadTopNCVO;
import com.whirly.vo.NoReadTopNSVO;
import com.whirly.vo.chart.BaseCountVO;
import com.whirly.vo.chart.DepartmentUserVO;
import com.whirly.vo.chart.NoticeCountByPublicerVO;
import com.whirly.vo.chart.NoticePubTimeVO;

@Controller
@RequestMapping("charts")
public class ChartController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private TimelineService timelineService;

	// 用户统计
	@RequestMapping("users")
	public String users() {

		return "admin/chart/users";
	}

	// 获取各院系学生人数
	@RequestMapping("countUsers")
	@ResponseBody
	public Msg countUsers() {
		List<DepartmentUserVO> users = departmentService.countUsers(); // 统计各院系学生
		List<DepartmentUserVO> publicers = departmentService.countPublicers(); // 统计各院系发布者
		Msg msg = Msg.success();
		msg.add("users", users);
		msg.add("publicers", publicers);
		return msg;
	}

	// 日历图
	@RequestMapping("calendar")
	public String calendar() {
		return "admin/chart/calendar";
	}

	// 发布时间统计
	@RequestMapping("pubtime")
	public String getPubtime() {
		return "admin/chart/pubtime";
	}

	@RequestMapping("countPubTime")
	@ResponseBody
	public Msg countPubTime() {
		List<NoticePubTimeVO> list = noticeService.countPubTime();
		Msg msg = Msg.success();
		msg.add("pubTime", list);
		return msg;
	}

	// 通知统计
	@RequestMapping("notices")
	public String getNotices() {
		return "admin/chart/notices";
	}

	@RequestMapping("noticesCountData")
	@ResponseBody
	public Msg getNoticesCountData(
			@RequestParam(value = "plimit", required = false, defaultValue = "15") Integer plimit) {
		// 按学院的通知数量排名
		List<BaseCountVO> noticeNumByDepartment = noticeService.countByDepartment();
		List<NoticeCountByPublicerVO> noticeNumByPublicer = noticeService.countByPublicer(plimit);
		Msg msg = Msg.success();
		msg.add("noticeNumByDepartment", noticeNumByDepartment);
		msg.add("noticeNumByPublicer", noticeNumByPublicer);
		return msg;
	}

	// 类型统计
	@RequestMapping("types")
	public String getTypes() {
		return "admin/chart/types";
	}

	@RequestMapping("typesCountData")
	@ResponseBody
	public Msg getNoticesClassCountData(
			@RequestParam(value = "limit", required = false, defaultValue = "15") Integer limit) {
		// 按学院的通知数量排名
		List<BaseCountVO> noticeNumByTypes = noticeService.countByTypes(limit);
		Msg msg = Msg.success();
		msg.add("noticeNumByTypes", noticeNumByTypes);
		return msg;
	}

	@RequestMapping("noReaded")
	public String getNoReaded() {
		return "admin/chart/noReaded";
	}

	@RequestMapping("noReadedTopNStudent")
	@ResponseBody
	public Msg noReadedTopNStudent(
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
		List<NoReadTopNSVO> list = timelineService.noReadedTopNStudent(limit);
		Msg msg = Msg.success();
		msg.add("noReadedTopNStudent", list);
		return msg;
	}

	@RequestMapping("classNoReaded")
	public String classNoReaded() {
		return "admin/chart/classNoReaded";
	}

	@RequestMapping("noReadedTopNClass")
	@ResponseBody
	public Msg noReadedTopNClass(@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
		List<NoReadTopNCVO> list = timelineService.noReadedTopNClass(limit);
		Msg msg = Msg.success();
		msg.add("noReadedTopNStudent", list);
		return msg;
	}
}

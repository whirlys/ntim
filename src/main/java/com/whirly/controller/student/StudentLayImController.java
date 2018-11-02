package com.whirly.controller.student;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whirly.imserver.constant.Constants;
import com.whirly.model.Message;
import com.whirly.model.Student;
import com.whirly.model.User;
import com.whirly.service.GroupService;
import com.whirly.service.MessageService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.FriendsListVO;
import com.whirly.vo.GroupVO;
import com.whirly.vo.OffLineMessageVO;
import com.whirly.vo.UserVO;

@Controller
@RequestMapping("student/layim")
public class StudentLayImController {

	private static final Logger logger = LoggerFactory.getLogger(StudentLayImController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	/**
	 * 面板初始化获得：我的信息、好友列表、群组列表
	 * 
	 * @return
	 */
	@RequestMapping("init")
	@ResponseBody
	public Map<String, Object> init(HttpSession session) {

		Student student = (Student) session.getAttribute("student");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();

		// 获取我的信息
		Integer studentId = student.getStudentId();
		Integer userId = student.getUserId();

		// 我的信息
		UserVO userVO = userService.selectVOById(userId);
		userVO.setStatus("online"); // 状态从redis中获取

		// 获取我的好友列表，只有两个分组，一个为同班同学，一个为发布者
		FriendsListVO classmate = userService.selectClassmateByStudentId(studentId);
		List<UserVO> publicerVos = userService.selectPublicersByStudentId(studentId);
		FriendsListVO publicers = new FriendsListVO();
		publicers.setId(1000001);
		publicers.setGroupname("发布者组");
		publicers.setList(publicerVos);

		UserVO robot = new UserVO();
		robot.setId(Integer.parseInt(Constants.ImServerConfig.ROBOT_SESSIONID));
		robot.setUsername("小旋锋");
		robot.setAvatar("http://10.100.99.220:9999/group1/M00/00/00/CmRj3Fra9w-AAk8SAAAVD3wcBXs491.png");
		robot.setSign("实现机器人与人类的自由对话");
		List<UserVO> robots = new LinkedList<UserVO>();
		robots.add(robot);
		FriendsListVO robotFriendList = new FriendsListVO();
		robotFriendList.setGroupname("智能问答机器人");
		robotFriendList.setId(100000);
		robotFriendList.setList(robots);

		List<FriendsListVO> friendsList = new LinkedList<FriendsListVO>();
		friendsList.add(robotFriendList);
		friendsList.add(classmate);
		friendsList.add(publicers);

		// 获取加入的群
		List<GroupVO> groupList = groupService.selectGroupByUserId(userId);
		data.put("mine", userVO);
		data.put("friend", friendsList);
		data.put("group", groupList); // 群

		map.put("code", 0);
		map.put("msg", "获取聊天面板初始化数据成功！");
		map.put("data", data);
		return map;
	}

	/**
	 * 根据id获取群成员
	 */
	@RequestMapping("members")
	@ResponseBody
	public Map<String, Object> members(@RequestParam(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();

		List<UserVO> list = groupService.selectMembersById(id);

		data.put("list", list);

		map.put("code", 0);
		map.put("msg", "查询群成员成功");
		map.put("data", data);
		return map;
	}

}

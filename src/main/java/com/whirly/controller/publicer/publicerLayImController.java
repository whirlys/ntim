package com.whirly.controller.publicer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whirly.model.Group;
import com.whirly.model.Publicer;
import com.whirly.model.User;
import com.whirly.service.GroupService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.FriendsListVO;
import com.whirly.vo.GroupVO;
import com.whirly.vo.UserVO;

@Controller
@RequestMapping("publicer/layim")
public class publicerLayImController {

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
		Publicer publicer = (Publicer) session.getAttribute("publicer");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		// 获取我的信息
		Integer publicerId = publicer.getPublicerId(); // 从session中获取publicerId
		Integer userId = publicer.getUserId(); // 从session中获取userId

		// 我的信息
		UserVO userVO = userService.selectVOById(userId);
		userVO.setStatus("online"); // 状态从redis中获取

		// 获取我的好友列表
		// 以班级划分
		List<FriendsListVO> friendsList = userService.selectFriendsListVOByPublicerId(publicerId);
		// 同院系的发布者
		List<UserVO> publicers = userService.selectPublicersByDepartmentId(publicer.getDeparmentId());
		FriendsListVO publicerFriend = new FriendsListVO();
		publicerFriend.setGroupname("同院系发布者");
		publicerFriend.setId(1000);
		publicerFriend.setList(publicers);
		friendsList.add(0, publicerFriend);
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

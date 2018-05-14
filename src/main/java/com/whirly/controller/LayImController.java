package com.whirly.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whirly.model.User;
import com.whirly.service.MessageService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.OffLineMessageVO;

@Controller
@RequestMapping("layim")
public class LayImController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	/**
	 * 获取离线消息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getofflinemsg", method = RequestMethod.POST)
	@ResponseBody
	public Msg getofflinemsg(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<OffLineMessageVO> messages = messageService.getOfflineMessageByUserId(user.getUserId());
		List<Integer> msgIds = new LinkedList<Integer>();
		for (OffLineMessageVO vo : messages) {
			msgIds.add(vo.getCid());
		}
		if (msgIds.size() > 0) {
			messageService.batchUpdateReaded(msgIds); // 将消息更新为已读
		}

		Msg msg = Msg.success();
		msg.add("offlineMsg", messages);
		return msg;
	}

	/**
	 * 修改个性签名 没接收到？
	 */
	@RequestMapping(value = "editSign", method = RequestMethod.POST)
	@ResponseBody
	public Msg editSign(HttpSession session, String sign) {
		System.out.println(sign);
		User user = (User) session.getAttribute("user");
		user.setSignature(sign);
		userService.updateSign(user);
		session.setAttribute("user", user);
		return Msg.success();
	}

	/**
	 * 聊天记录
	 */
	@RequestMapping(value = "chatlog")
	public String chatlog() {

		return "chatlog";
	}

}

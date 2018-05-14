package com.whirly.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whirly.form.LoginForm;
import com.whirly.imserver.model.Session;
import com.whirly.model.Publicer;
import com.whirly.model.Student;
import com.whirly.model.User;
import com.whirly.service.PublicerService;
import com.whirly.service.StudentService;
import com.whirly.service.UserService;
import com.whirly.util.Constants;

import io.swagger.annotations.Api;

@Controller
@RequestMapping(value = "/")
@Api(value = "公共模块", description = "主要是登录和注销")
public class CommonController {

	@Resource(name = "sessionMap")
	protected Map<String, Session> sessions;

	@Autowired
	private UserService userService;

	@Autowired
	private PublicerService publicerService;

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (null != user) {
			switch (user.getRole()) {
			case Constants.UserType.STUDENT: // 学生
				return "redirect:/student";
			case Constants.UserType.PUBLICER: // 发布者

				return "redirect:/publicer";
			case Constants.UserType.ADMIN: // 后台管理员
				return "redirect:/admin";
			}
		}
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginform(@Valid LoginForm form, BindingResult result, Model model, HttpSession session) {
		System.out.println(form.toString());
		String errormsg = null;
		User user = null;
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			errormsg = errorList.get(0).getDefaultMessage();
		} else if (8 != form.getAccount().length() && 12 != form.getAccount().length()) {
			errormsg = "帐号的长度须为8或12";
		} else {
			user = userService.login(form);
			if (null == user) {
				errormsg = "帐号或密码错误！";
			}
		}
		if (null != errormsg) {
			model.addAttribute("errormsg", errormsg);
			model.addAttribute("account", form.getAccount());
			return "login";
		}
		user.setLastLogin(new Date());
		userService.updateLastLogin(user);
		session.setAttribute("user", user);
		switch (user.getRole()) {
		case Constants.UserType.STUDENT: // 学生
			Student student = studentService.selectByUserId(user.getUserId());
			session.setAttribute("student", student);
			return "redirect:/student";

		case Constants.UserType.PUBLICER: // 发布者
			Publicer publicer = publicerService.selectByUserId(user.getUserId());
			session.setAttribute("publicer", publicer);
			return "redirect:/publicer";

		case Constants.UserType.ADMIN: // 后台管理员

			return "redirect:/admin";
		}
		return "404";
	}

	// 注销方法
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 通过session.invalidata()方法来注销当前的session
		session.invalidate();
		return "redirect:/login";
	}
}

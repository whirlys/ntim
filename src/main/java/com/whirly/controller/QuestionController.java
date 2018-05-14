package com.whirly.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.whirly.form.QuestionSearchForm;
import com.whirly.model.Answer;
import com.whirly.model.Department;
import com.whirly.model.Publicer;
import com.whirly.model.Question;
import com.whirly.model.Student;
import com.whirly.model.User;
import com.whirly.service.AnswerService;
import com.whirly.service.DepartmentService;
import com.whirly.service.QuestionService;
import com.whirly.util.Constants.UserType;
import com.whirly.util.Msg;
import com.whirly.vo.AnswerVO;
import com.whirly.vo.QuestionVO;

@Controller
@RequestMapping("question")
public class QuestionController {

	@Autowired
	DepartmentService departmentService;

	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;

	@RequestMapping("")
	public String toQuestion(Model model) {
		List<Department> departments = departmentService.selectAll();
		model.addAttribute("departments", departments);
		return "questions";
	}

	@RequestMapping("toAddQuestion")
	public String toAddQuestion() {

		return "questionForm";
	}

	@RequestMapping("addQuestion")
	@ResponseBody
	public Msg addQuestion(@RequestBody Question question, HttpSession session) {
		System.out.println(question.toString());
		User user = (User) session.getAttribute("user");
		System.out.println(user.toString());
		question.setUserId(user.getUserId());
		if (user.getRole().equals(UserType.STUDENT)) {
			Student student = (Student) session.getAttribute("student");
			Department department = departmentService.selectByClassId(student.getClassId());
			System.out.println(department.toString());
			question.setDepartmentId(department.getDepartmentId());
		} else if (user.getRole().equals(UserType.PUBLICER)) {
			Publicer publicer = (Publicer) session.getAttribute("publicer");
			System.out.println("==========");
			System.out.println(publicer.toString());
			question.setDepartmentId(publicer.getDeparmentId());
		}
		questionService.insert(question);
		return Msg.success();
	}

	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(QuestionSearchForm searchForm) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<Question> pageInfo = questionService.selectBySearchForm(searchForm);
		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
	}

	@RequestMapping("detail/{questionId}")
	public String detail(@PathVariable(name = "questionId") Integer questionId, Model model) {
		QuestionVO question = questionService.selectVOById(questionId);
		List<AnswerVO> answerVOs = questionService.selectAnswersById(questionId);
		model.addAttribute("answerVOs", answerVOs);
		model.addAttribute("question", question);
		return "questionDetail";
	}

	@RequestMapping("addAnswer/{questionId}")
	@ResponseBody
	public Msg addAnswer(@RequestBody Answer answer, HttpSession session,
			@PathVariable("questionId") Integer questionId) {
		answer.setQuestionId(questionId);
		System.out.println("========addAnswer==========");
		System.out.println(answer.toString());
		User user = (User) session.getAttribute("user");
		System.out.println(user.toString());
		answer.setUserId(user.getUserId());
		if (user.getRole().equals(UserType.PUBLICER)) {
			Publicer publicer = (Publicer) session.getAttribute("publicer");
			answer.setDepartmentId(publicer.getDeparmentId());
			answer.setTop(true);
		} else if (user.getRole().equals(UserType.STUDENT)) {
			Student student = (Student) session.getAttribute("student");
			System.out.println("===========学生==========");
			System.out.println(student.toString());
			Department department = departmentService.selectByClassId(student.getClassId());
			System.out.println("==============department  addAnswer========\n\n");
			answer.setDepartmentId(department.getDepartmentId());
			answer.setTop(false);
		} else {
			System.out.println("=======else==========");
			answer.setDepartmentId(1);
			answer.setTop(false);
		}
		System.out.println(answer.toString());
		answerService.insert(answer);
		return Msg.success();
	}

	@RequestMapping(value = "answers/{questionId}", method = RequestMethod.GET)
	@ResponseBody
	public Msg answers(@PathVariable(name = "questionId") Integer questionId) {
		List<AnswerVO> answerVOs = questionService.selectAnswersById(questionId);
		Msg msg = Msg.success();
		msg.add("answerVOs", answerVOs);
		return msg;
	}
}

package com.whirly.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.whirly.form.PublicerForm;
import com.whirly.form.PublicerSearchForm;
import com.whirly.model.Department;
import com.whirly.model.Publicer;
import com.whirly.service.BeManagedService;
import com.whirly.service.ClasssService;
import com.whirly.service.DepartmentService;
import com.whirly.service.PublicerService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.BeManagedVO;
import com.whirly.vo.PublicerVO;

@Controller
@RequestMapping("admin/publicer")
public class AdminPublicerController {

	@Autowired
	private PublicerService publicerService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private BeManagedService beManagedService;

	@RequestMapping("index")
	public String Index(Model model) {
		List<Department> departments = departmentService.selectAll();
		model.addAttribute("departments", departments);
		return "admin/publicer/list";
	}

	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> index(PublicerSearchForm searchForm) {
		Map<String, Object> map = new HashMap<String, Object>();

		PageInfo<PublicerVO> pageInfo = publicerService.selectBySearchForm(searchForm);
		map.put("code", 0);
		map.put("msg", "请求成功！");
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
	}

	@RequestMapping("toAdd")
	public String toAdd(Model model) {
		List<Department> departments = departmentService.selectAll();
		model.addAttribute("departments", departments);
		model.addAttribute("form", new PublicerForm());
		return "admin/publicer/form";
	}

	@RequestMapping(value = "form")
	@ResponseBody
	public Msg add(@Valid PublicerForm form, BindingResult result) {
		System.out.println(form.toString());
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			Msg msg = Msg.error();
			msg.add("error", errorList.get(0).getDefaultMessage());
			return msg;
		}
		Department deparment = departmentService.selectById(form.getDepartmentId());
		if (null == deparment) {
			Msg msg = Msg.error();
			msg.setMsg("院系不存在！");
			return msg;
		}

		// 添加新发布者
		if (null == form.getPublicerId()) {
			publicerService.insert(form);

		} else {
			// 更新发布者信息
			publicerService.update(form);
		}

		return Msg.success();
	}

	@RequestMapping("toEdit")
	public String toEdit(Integer publicerId, Model model) throws NotFoundException {
		List<Department> departments = departmentService.selectAll();
		Publicer publicer = publicerService.selectById(publicerId);
		if (publicer == null) {
			throw new NotFoundException("该发布者不存在！");
		}
		PublicerForm form = new PublicerForm();
		form.setAccount(publicer.getUser().getAccount());
		form.setUsername(publicer.getUser().getUsername());
		form.setDepartmentId(publicer.getDeparmentId());
		form.setDuty(publicer.getDuty());
		form.setEmail(publicer.getEmail());
		form.setIntroduction(publicer.getIntroduction());
		form.setIsActive(publicer.getUser().getIsActive());
		form.setOffice(publicer.getOffice());
		form.setPassword(publicer.getUser().getPassword());
		form.setPublicerId(publicer.getPublicerId());
		form.setTitle(publicer.getTitle());

		model.addAttribute("departments", departments);
		model.addAttribute("form", form);
		return "admin/publicer/form";
	}

	@RequestMapping("managed")
	public String toManaged(Integer publicerId, Model model) throws NotFoundException {
		Publicer publicer = publicerService.selectById(publicerId);
		if (publicer == null) {
			throw new NotFoundException("该发布者不存在！");
		}
		model.addAttribute("publicer", publicer);
		return "admin/publicer/managed";
	}

	@RequestMapping("inManagedVo")
	@ResponseBody
	public Map<String, Object> inManagedVo(Integer publicerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 已被管理的班级
		List<BeManagedVO> inManagedVo = beManagedService.selectManaged(publicerId);

		map.put("code", 0);
		map.put("msg", "请求成功");
		map.put("count", inManagedVo.size());
		map.put("data", inManagedVo);
		return map;
	}

	@RequestMapping("notInManagedVo")
	@ResponseBody
	public Map<String, Object> notInManagedVo(Integer publicerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 所在学院内未被管理的班级
		System.out.println("============所在学院内未被管理的班级=========");
		List<BeManagedVO> notInManagedVo = beManagedService.selectNotManaged(publicerId);
		for (BeManagedVO vo : notInManagedVo) {
			System.out.println(vo.toString());
		}
		map.put("code", 0);
		map.put("msg", "请求成功");
		map.put("count", notInManagedVo.size());
		map.put("data", notInManagedVo);
		return map;
	}

	@RequestMapping("addManaged")
	@ResponseBody
	public Msg addManaged(@RequestParam("publicerId") Integer publicerId, @RequestParam("ids") Integer[] ids) {
		System.out.println("publicerId:" + publicerId);
		for (Integer id : ids) {
			System.out.println(id);
		}
		return beManagedService.addManaged(publicerId, ids);

	}

	@RequestMapping("delManaged")
	@ResponseBody
	public Msg delManaged(@RequestParam("publicerId") Integer publicerId, @RequestParam("ids") Integer[] ids) {
		System.out.println("publicerId:" + publicerId);
		for (Integer id : ids) {
			System.out.println(id);
		}
		return beManagedService.delManaged(publicerId, ids);

	}
}

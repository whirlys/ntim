package com.whirly.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.PublicerMapper;
import com.whirly.dao.UserMapper;
import com.whirly.form.PublicerForm;
import com.whirly.form.PublicerSearchForm;
import com.whirly.model.Publicer;
import com.whirly.model.PublicerExample;
import com.whirly.model.PublicerExample.Criteria;
import com.whirly.model.User;
import com.whirly.service.PublicerService;
import com.whirly.service.UserService;
import com.whirly.util.Msg;
import com.whirly.vo.PublicerVO;

@Service
public class PublicerServiceImpl implements PublicerService {

	@Autowired
	private PublicerMapper publicerMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Override
	public PageInfo<PublicerVO> selectBySearchForm(PublicerSearchForm searchForm) {
		PageHelper.startPage(searchForm.getPage(), searchForm.getLimit());
		List<PublicerVO> list = publicerMapper.selectBySearchForm(searchForm);
		PageInfo<PublicerVO> pageInfo = new PageInfo<PublicerVO>(list);
		return pageInfo;
	}

	@Override
	public Msg insert(PublicerForm form) {

		User existUser = userService.selectByAccount(form.getAccount());
		if (existUser != null) {
			Msg msg = Msg.error();
			msg.setMsg("已存在编号为：" + form.getAccount() + " 的用户！");
			return msg;
		}
		User user = User.createDefault();
		user.setAccount(form.getAccount());
		user.setUsername(form.getUsername());
		user.setPassword(form.getPassword());
		user.setRole("PUB");
		user.setCreatetime(new Date());
		userMapper.insert(user);

		Publicer publicer = new Publicer();
		publicer.setDeparmentId(form.getDepartmentId());
		publicer.setDuty(form.getDuty());
		publicer.setEmail(form.getEmail());
		publicer.setIntroduction(form.getIntroduction());
		publicer.setOffice(form.getOffice());
		publicer.setTitle(form.getTitle());
		publicer.setUserId(user.getUserId());

		publicerMapper.insert(publicer);
		return Msg.success();
	}

	@Override
	public Publicer selectById(Integer publicerId) {

		return publicerMapper.selectByPrimaryKey(publicerId);
	}

	@Override
	public Msg update(PublicerForm form) {
		Publicer publicer = publicerMapper.selectByPrimaryKey(form.getPublicerId());
		if (null == publicer) {
			Msg msg = Msg.error();
			msg.setMsg("发布者不存在");
			return msg;
		}
		publicer.setDeparmentId(form.getDepartmentId());
		publicer.setDuty(form.getDuty());
		publicer.setEmail(form.getEmail());
		publicer.setIntroduction(form.getIntroduction());
		publicer.setOffice(form.getOffice());
		publicer.setTitle(form.getTitle());

		// 不更新User的信息
		publicerMapper.updateByPrimaryKey(publicer);
		return Msg.success();
	}

	@Override
	public Publicer selectByUserId(Integer userId) {
		PublicerExample example = new PublicerExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return publicerMapper.selectByExample(example).get(0);
	}

	@Override
	public int count() {
		PublicerExample example = new PublicerExample();
		return (int) publicerMapper.countByExample(example);
	}

}

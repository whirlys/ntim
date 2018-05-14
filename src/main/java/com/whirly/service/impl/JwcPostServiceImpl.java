package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.JwcPostMapper;
import com.whirly.form.JwcForm;
import com.whirly.model.JwcPost;
import com.whirly.service.JwcPostService;

@Service
public class JwcPostServiceImpl implements JwcPostService {

	@Autowired
	private JwcPostMapper jwcPostMapper;

	public PageInfo<JwcPost> selectBySearchForm(JwcForm form) {
		PageHelper.startPage(form.getPage(), form.getLimit());
		List<JwcPost> list = jwcPostMapper.selectBySearchForm(form);
		return new PageInfo<JwcPost>(list);
	}

	@Override
	public List<String> selectAllType() {

		return jwcPostMapper.selectAllType();
	}

}

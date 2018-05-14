package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.FormMapper;
import com.whirly.form.DelayForm;
import com.whirly.form.FormSearchForm;
import com.whirly.model.Form;
import com.whirly.model.FormExample;
import com.whirly.model.FormExample.Criteria;
import com.whirly.service.FormService;

@Service
public class FormServiceImpl implements FormService {

	@Autowired
	private FormMapper formMapper;

	@Override
	public PageInfo<Form> selectBySearchForm(FormSearchForm form) {

		PageHelper.startPage(form.getPage(), form.getLimit());
		List<Form> list = formMapper.selectBySearchForm(form);
		return new PageInfo<Form>(list);
	}

	@Override
	public int insert(Form form) {

		return formMapper.insert(form);
	}

	@Override
	public Form selectById(Integer formId) {

		return formMapper.selectByPrimaryKey(formId);
	}

	@Override
	public int updateDeadline(DelayForm delayForm) {
		return formMapper.updateDeadline(delayForm);
	}

	@Override
	public int count() {
		FormExample example = new FormExample();
		return (int) formMapper.countByExample(example);
	}

}

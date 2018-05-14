package com.whirly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whirly.dao.FileMapper;
import com.whirly.form.FileSearchForm;
import com.whirly.model.File;
import com.whirly.model.FileExample;
import com.whirly.model.FileExample.Criteria;
import com.whirly.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;

	@Override
	public PageInfo<File> selectBySearchForm(FileSearchForm form) {
		PageHelper.startPage(form.getPage(), form.getLimit());
		FileExample example = new FileExample();
		if (form.getQ() != null) {
			Criteria criteria = example.createCriteria();
			criteria.andFilenameLike(form.getQ());
			Criteria criteria2 = example.createCriteria();
			criteria2.andRemarksLike(form.getQ());

			example.or(criteria2);
		}
		example.setOrderByClause("createtime desc");
		return new PageInfo<File>(fileMapper.selectByExample(example));
	}

	@Override
	public int insert(File file) {

		return fileMapper.insert(file);
	}

}

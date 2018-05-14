package com.whirly.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whirly.form.JwcForm;
import com.whirly.model.JwcPost;

public interface JwcPostService {

	PageInfo<JwcPost> selectBySearchForm(JwcForm form);

	List<String> selectAllType();

}

package com.whirly.service;

import com.github.pagehelper.PageInfo;
import com.whirly.form.PublicerForm;
import com.whirly.form.PublicerSearchForm;
import com.whirly.model.Publicer;
import com.whirly.util.Msg;
import com.whirly.vo.PublicerVO;

public interface PublicerService {

	PageInfo<PublicerVO> selectBySearchForm(PublicerSearchForm searchForm);

	Msg insert(PublicerForm form);

	Publicer selectById(Integer publicerId);

	Msg update(PublicerForm form);

	Publicer selectByUserId(Integer userId);

	int count();
}

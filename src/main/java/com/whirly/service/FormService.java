package com.whirly.service;

import com.github.pagehelper.PageInfo;
import com.whirly.form.DelayForm;
import com.whirly.form.FormSearchForm;
import com.whirly.model.Form;

public interface FormService {

	PageInfo<Form> selectBySearchForm(FormSearchForm form);

	int insert(Form form);

	Form selectById(Integer formId);

	int updateDeadline(DelayForm delayForm);

	int count();

}

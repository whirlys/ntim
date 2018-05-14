package com.whirly.service;

import com.github.pagehelper.PageInfo;
import com.whirly.form.FileSearchForm;
import com.whirly.model.File;

public interface FileService {

	PageInfo<File> selectBySearchForm(FileSearchForm form);

	int insert(File file);

}

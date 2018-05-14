package com.whirly.service;

import java.util.List;

import com.whirly.model.Supplement;

public interface SupplementService {

	int insert(Supplement supplement);

	List<Supplement> selectByNoticeId(Integer noticeId);

}

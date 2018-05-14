package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whirly.dao.SupplementMapper;
import com.whirly.model.Supplement;
import com.whirly.model.SupplementExample;
import com.whirly.model.SupplementExample.Criteria;
import com.whirly.service.SupplementService;

@Service
public class SupplementServiceImpl implements SupplementService {

	@Autowired
	private SupplementMapper supplementMapper;

	@Override
	public int insert(Supplement supplement) {
		return supplementMapper.insert(supplement);
	}

	@Override
	public List<Supplement> selectByNoticeId(Integer noticeId) {
		SupplementExample example = new SupplementExample();
		Criteria criteria = example.createCriteria();
		criteria.andNoticeIdEqualTo(noticeId);
		example.setOrderByClause("createtime asc");
		return supplementMapper.selectByExample(example);
	}

}

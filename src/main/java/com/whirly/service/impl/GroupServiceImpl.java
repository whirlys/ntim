package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whirly.dao.GroupMapper;
import com.whirly.model.Group;
import com.whirly.service.GroupService;
import com.whirly.vo.GroupVO;
import com.whirly.vo.UserVO;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper groupMapper;

	@Override
	public List<GroupVO> selectGroupByUserId(Integer userId) {

		return groupMapper.selectGroupByUserId(userId);
	}

	@Override
	public List<UserVO> selectMembersById(Integer id) {

		return groupMapper.selectMembersById(id);
	}

}

package com.whirly.service;

import java.util.List;

import com.whirly.model.Group;
import com.whirly.vo.GroupVO;
import com.whirly.vo.UserVO;

public interface GroupService {

	List<GroupVO> selectGroupByUserId(Integer userId);

	List<UserVO> selectMembersById(Integer id);

}

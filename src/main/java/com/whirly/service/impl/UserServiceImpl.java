package com.whirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whirly.dao.UserMapper;
import com.whirly.form.LoginForm;
import com.whirly.model.User;
import com.whirly.model.UserExample;
import com.whirly.model.UserExample.Criteria;
import com.whirly.service.UserService;
import com.whirly.vo.FriendsListVO;
import com.whirly.vo.ReceiverFormVO;
import com.whirly.vo.ReceiverVO;
import com.whirly.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User selectById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User selectByAccount(String account) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<User> list = userMapper.selectByExample(example);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public UserVO selectVOById(Integer userId) {

		return userMapper.selectVOById(userId);
	}

	@Override
	public List<FriendsListVO> selectFriendsListVOByPublicerId(Integer publicerId) {
		return userMapper.selectFriendsListVOByPublicerUserId(publicerId);
	}

	@Override
	public User login(LoginForm form) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(form.getAccount());
		criteria.andPasswordEqualTo(form.getPassword());
		List<User> list = userMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public FriendsListVO selectClassmateByStudentId(Integer studentId) {
		return userMapper.selectClassmateByStudentId(studentId);
	}

	@Override
	public List<UserVO> selectPublicersByStudentId(Integer studentId) {
		return userMapper.selectPublicersByStudentId(studentId);
	}

	@Override
	public List<FriendsListVO> selectFriendsListVOByPublicerIdSimple(Integer publicerId) {
		return userMapper.selectFriendsListVOByPublicerIdSimple(publicerId);
	}

	@Override
	public List<ReceiverVO> selectReceiverByNoticeId(Integer noticeId) {
		return userMapper.selectReceiverByNoticeId(noticeId);
	}

	@Override
	public int updateAvatar(User user) {

		return userMapper.updateAvatar(user);

	}

	@Override
	public boolean checkPassword(User user, String password) {
		User existUser = userMapper.selectByPrimaryKey(user.getUserId());
		if (existUser == null) {
			return false;
		}
		if (!existUser.getPassword().equals(password)) {
			return false;
		}
		return true;
	}

	@Override
	public int updatePassword(User user) {
		return userMapper.updatePassword(user);
	}

	@Override
	public List<UserVO> selectInDepId(Integer[] deparments) {

		return userMapper.selectInDepId(deparments);
	}

	@Override
	public List<UserVO> selectPublicersByDepartmentId(Integer deparmentId) {
		return userMapper.selectPublicersByDepartmentId(deparmentId);
	}

	@Override
	public int updateSign(User user) {

		return userMapper.updateSign(user);
	}

	@Override
	public int updateLastLogin(User user) {
		return userMapper.updateLastLogin();
	}

	@Override
	public List<ReceiverFormVO> selectReceiverByFormId(Integer formId) {

		return userMapper.selectReceiverByFormId(formId);
	}
}

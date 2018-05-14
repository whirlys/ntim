package com.whirly.service;

import java.util.List;

import javax.validation.Valid;

import com.whirly.form.LoginForm;
import com.whirly.model.User;
import com.whirly.vo.FriendsListVO;
import com.whirly.vo.ReceiverFormVO;
import com.whirly.vo.ReceiverVO;
import com.whirly.vo.UserVO;

public interface UserService {

	User selectById(Integer id);

	User selectByAccount(String account);

	UserVO selectVOById(Integer userId);

	List<FriendsListVO> selectFriendsListVOByPublicerId(Integer publicerId);

	User login(LoginForm form);

	FriendsListVO selectClassmateByStudentId(Integer studentId);

	List<UserVO> selectPublicersByStudentId(Integer studentId);

	List<FriendsListVO> selectFriendsListVOByPublicerIdSimple(Integer publicerId);

	List<ReceiverVO> selectReceiverByNoticeId(Integer noticeId);

	int updateAvatar(User user);

	boolean checkPassword(User user, String password);

	int updatePassword(User user);

	List<UserVO> selectInDepId(Integer[] deparments);

	List<UserVO> selectPublicersByDepartmentId(Integer deparmentId);

	int updateSign(User user);

	int updateLastLogin(User user);

	List<ReceiverFormVO> selectReceiverByFormId(Integer formId);
}

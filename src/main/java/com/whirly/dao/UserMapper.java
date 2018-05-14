package com.whirly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.whirly.model.User;
import com.whirly.model.UserExample;
import com.whirly.vo.FriendsListVO;
import com.whirly.vo.ReceiverFormVO;
import com.whirly.vo.ReceiverVO;
import com.whirly.vo.UserVO;

public interface UserMapper {
	long countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExampleWithRowbounds(UserExample example, RowBounds rowBounds);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Integer userId);

	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<String> findAccounts(List<String> accounts);

	int batchInsert(List<User> users);

	UserVO selectVOById(Integer userId);

	List<FriendsListVO> selectFriendsListVOByPublicerUserId(Integer publicerId); // 供发布者用，获取好友列表

	FriendsListVO selectClassmateByStudentId(Integer studentId);

	List<UserVO> selectPublicersByStudentId(Integer studentId);

	List<FriendsListVO> selectFriendsListVOByPublicerIdSimple(Integer publicerId);

	List<ReceiverVO> selectReceiverByNoticeId(Integer noticeId);

	int updateAvatar(User user);

	int updatePassword(User user);

	List<UserVO> selectInDepId(Integer[] deparments);

	List<UserVO> selectPublicersByDepartmentId(Integer deparmentId);

	int updateSign(User user);

	int updateLastLogin();

	List<ReceiverFormVO> selectReceiverByFormId(Integer formId);
}
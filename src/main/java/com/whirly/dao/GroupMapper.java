package com.whirly.dao;

import com.whirly.model.Group;
import com.whirly.model.GroupExample;
import com.whirly.vo.GroupVO;
import com.whirly.vo.UserVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GroupMapper {
    long countByExample(GroupExample example);

    int deleteByExample(GroupExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(Group record);

    int insertSelective(Group record);

    List<Group> selectByExampleWithRowbounds(GroupExample example, RowBounds rowBounds);

    List<Group> selectByExample(GroupExample example);

    Group selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

	List<GroupVO> selectGroupByUserId(Integer userId);

	List<UserVO> selectMembersById(Integer id);
}
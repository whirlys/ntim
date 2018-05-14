package com.whirly.dao;

import com.whirly.model.BeManaged;
import com.whirly.model.BeManagedExample;
import com.whirly.vo.BeManagedVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BeManagedMapper {
	long countByExample(BeManagedExample example);

	int deleteByExample(BeManagedExample example);

	int deleteByPrimaryKey(Integer managedId);

	int insert(BeManaged record);

	int insertSelective(BeManaged record);

	List<BeManaged> selectByExampleWithRowbounds(BeManagedExample example, RowBounds rowBounds);

	List<BeManaged> selectByExample(BeManagedExample example);

	BeManaged selectByPrimaryKey(Integer managedId);

	int updateByExampleSelective(@Param("record") BeManaged record, @Param("example") BeManagedExample example);

	int updateByExample(@Param("record") BeManaged record, @Param("example") BeManagedExample example);

	int updateByPrimaryKeySelective(BeManaged record);

	int updateByPrimaryKey(BeManaged record);

	List<BeManagedVO> selectManaged(Integer publicerId);

	List<BeManagedVO> selectNotManaged(Integer publicerId);

	int insertByList(List<BeManaged> beManagedList);

	List<Integer> selectClassIdByUserId(Integer publicerId);

	int deleteByList(List<BeManaged> beManagedList);
}
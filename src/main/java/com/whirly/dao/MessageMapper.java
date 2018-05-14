package com.whirly.dao;

import com.whirly.form.messageSearchForm;
import com.whirly.model.Message;
import com.whirly.model.MessageExample;
import com.whirly.vo.MessageVO;
import com.whirly.vo.OffLineMessageVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MessageMapper {
	long countByExample(MessageExample example);

	int deleteByExample(MessageExample example);

	int deleteByPrimaryKey(Integer messageId);

	int insert(Message record);

	int insertSelective(Message record);

	List<Message> selectByExampleWithBLOBsWithRowbounds(MessageExample example, RowBounds rowBounds);

	List<Message> selectByExampleWithBLOBs(MessageExample example);

	List<Message> selectByExampleWithRowbounds(MessageExample example, RowBounds rowBounds);

	List<Message> selectByExample(MessageExample example);

	Message selectByPrimaryKey(Integer messageId);

	int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

	int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

	int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKeyWithBLOBs(Message record);

	int updateByPrimaryKey(Message record);

	List<OffLineMessageVO> getOfflineMessageByUserId(Integer userId);

	int batchUpdateReaded(List<Integer> msgIds);

	List<MessageVO> noticeMessage(messageSearchForm searchForm);
}
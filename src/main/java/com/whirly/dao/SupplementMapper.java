package com.whirly.dao;

import com.whirly.model.Supplement;
import com.whirly.model.SupplementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SupplementMapper {
    long countByExample(SupplementExample example);

    int deleteByExample(SupplementExample example);

    int deleteByPrimaryKey(Integer supplementId);

    int insert(Supplement record);

    int insertSelective(Supplement record);

    List<Supplement> selectByExampleWithRowbounds(SupplementExample example, RowBounds rowBounds);

    List<Supplement> selectByExample(SupplementExample example);

    Supplement selectByPrimaryKey(Integer supplementId);

    int updateByExampleSelective(@Param("record") Supplement record, @Param("example") SupplementExample example);

    int updateByExample(@Param("record") Supplement record, @Param("example") SupplementExample example);

    int updateByPrimaryKeySelective(Supplement record);

    int updateByPrimaryKey(Supplement record);
}
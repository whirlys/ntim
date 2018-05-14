package com.whirly.dao;

import com.whirly.form.DelayForm;
import com.whirly.form.FormSearchForm;
import com.whirly.model.Form;
import com.whirly.model.FormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FormMapper {
    long countByExample(FormExample example);

    int deleteByExample(FormExample example);

    int deleteByPrimaryKey(Integer formId);

    int insert(Form record);

    int insertSelective(Form record);

    List<Form> selectByExampleWithRowbounds(FormExample example, RowBounds rowBounds);

    List<Form> selectByExample(FormExample example);

    Form selectByPrimaryKey(Integer formId);

    int updateByExampleSelective(@Param("record") Form record, @Param("example") FormExample example);

    int updateByExample(@Param("record") Form record, @Param("example") FormExample example);

    int updateByPrimaryKeySelective(Form record);

    int updateByPrimaryKey(Form record);

	List<Form> selectBySearchForm(FormSearchForm form);

	int updateDeadline(DelayForm delayForm);
}
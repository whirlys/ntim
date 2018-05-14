package com.whirly.service;

import java.util.List;

import com.whirly.util.Msg;
import com.whirly.vo.BeManagedVO;

public interface BeManagedService {
	List<BeManagedVO> selectManaged(Integer publicerId);

	List<BeManagedVO> selectNotManaged(Integer publicerId);

	Msg addManaged(Integer publicerId, Integer[] ids);

	Msg delManaged(Integer publicerId, Integer[] ids);
}

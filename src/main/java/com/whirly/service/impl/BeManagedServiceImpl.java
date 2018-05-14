package com.whirly.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whirly.dao.BeManagedMapper;
import com.whirly.dao.ClasssMapper;
import com.whirly.dao.PublicerMapper;
import com.whirly.model.BeManaged;
import com.whirly.model.Publicer;
import com.whirly.service.BeManagedService;
import com.whirly.util.Msg;
import com.whirly.vo.BeManagedVO;

@Service
public class BeManagedServiceImpl implements BeManagedService {

	@Autowired
	private BeManagedMapper beManagedMapper;

	@Autowired
	private ClasssMapper classsMapper;

	@Autowired
	private PublicerMapper publicerMapper;

	@Override
	public List<BeManagedVO> selectManaged(Integer publicerId) {

		return beManagedMapper.selectManaged(publicerId);
	}

	@Override
	public List<BeManagedVO> selectNotManaged(Integer publicerId) {
		return beManagedMapper.selectNotManaged(publicerId);
	}

	@Override
	public Msg addManaged(Integer publicerId, Integer[] ids) {
		Msg msg = null;
		Publicer publicer = publicerMapper.selectByPrimaryKey(publicerId);
		if (publicer == null) {
			msg = Msg.error();
			msg.setMsg("该发布者不存在！");
			return msg;
		}

		// 判断班级是否存在
		List<Integer> classIds = classsMapper.selectAllIds();
		Map<Integer, Integer> idsMap = new HashMap<Integer, Integer>();
		for (Integer id : classIds) {
			idsMap.put(id, 1);
		}
		List<Integer> notIn = new LinkedList<Integer>();
		for (Integer id : ids) {
			if (!idsMap.containsKey(id)) {
				notIn.add(id);
			}
		}
		if (!notIn.isEmpty()) {
			msg = Msg.error();
			msg.setMsg("添加失败，这些班级 " + notIn.toString() + "不存在！");
			return msg;
		}
		// 判断是否未被添加
		List<Integer> classids2 = beManagedMapper.selectClassIdByUserId(publicerId);
		List<Integer> in = new LinkedList<Integer>();
		Map<Integer, Integer> idsMap2 = new HashMap<Integer, Integer>();
		for (Integer id : classids2) {
			idsMap2.put(id, 1);
		}
		System.out.println(classids2.toString());
		for (Integer id : ids) {
			if (idsMap2.containsKey(id)) {
				in.add(id);
			}
		}
		if (!in.isEmpty()) {
			msg = Msg.error();
			msg.setMsg("添加失败，这些班级 " + in.toString() + "已经被该发布者管理！");
			return msg;
		}
		// 构建数组
		List<BeManaged> beManagedList = new LinkedList<BeManaged>();
		for (Integer id : ids) {
			BeManaged beManaged = new BeManaged();
			beManaged.setClassId(id);
			beManaged.setUserId(publicerId); // 这里 beManaged的userId为发布者的publicerId
			beManaged.setCreatetime(new Date());
			beManagedList.add(beManaged);
		}
		beManagedMapper.insertByList(beManagedList);
		msg = Msg.success();
		return msg;
	}

	@Override
	public Msg delManaged(Integer publicerId, Integer[] ids) {

		if (ids == null || ids.length == 0) {
			Msg msg = Msg.error();
			msg.setMsg("没有选中班级！");
			return msg;
		}
		List<BeManaged> beManagedList = new LinkedList<BeManaged>();
		for (Integer id : ids) {
			BeManaged beManaged = new BeManaged();
			beManaged.setClassId(id);
			beManaged.setUserId(publicerId);
			beManagedList.add(beManaged);
		}
		beManagedMapper.deleteByList(beManagedList);
		return Msg.success();
	}

}

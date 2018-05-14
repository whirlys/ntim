package com.whirly.robot.tuling;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过这个对象来搞定发送数据
 * 
 * @author yellowcong
 * @date 2016年3月26日
 *
 */
public class TulingClient {

	// 访问的key,是每一个用户自己的
	private String apikey;

	// 接口地址
	private String url;

	public TulingClient(String url, String apikey) {
		super();
		this.apikey = apikey;
		this.url = url;
	}

	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午12:09:56<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:发送数据到图灵
	 * 
	 * @param info
	 *            发送的消息，必须写
	 * @param userid
	 *            可写可不写
	 * @return 返回消息
	 */
	public Message sendMessage(String... args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("key", this.apikey);
		// 第一个是 发送的消息
		// info
		if (args.length > 0) {
			params.put("info", args[0]);
		}
		// 第二个是 userid
		if (args.length > 1) {
			params.put("userid", args[1]);
		}

		// 发送数据
		String json = HttpClientUtils.post(this.url, params);
		// System.out.println(json);
		// 将json数据转化为object
		return JsonUtils.json2Object(json, Message.class);
	}

	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午12:15:58<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:直接传递Map 集合,直接自己写一个集合，然后传递给图灵机器人
	 * 
	 * @param params
	 * @return 返回的是一个消息对象
	 */
	public Message sendMessage(Map<String, String> params) {
		if (!params.containsKey("key")) {
			params.put("key", this.apikey);
		}

		// 发送数据
		String json = HttpClientUtils.post(this.url, params);

		// 将json数据转化为object
		return JsonUtils.json2Object(json, Message.class);
	}
}
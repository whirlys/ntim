package com.whirly.robot.tuling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * JSON的工具类 json依赖 json-lib 工具类 DataGrid 类 beanutils
 * 
 * @author 狂飙のyellowcong 2015年7月27日
 *
 */
public class JsonUtils {
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 将一个对象转化为 JSON数据类型
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2Json(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * 将一个JSON 转化为一个类对象
	 * 
	 * @param <T>
	 * @return
	 */
	public static <T> T json2Object(String json, Class<T> type) {
		json = cleanJson(json);
		try {
			return mapper.readValue(json, type);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 将一个JSON 转化为一个ArrayList集合 其中我们需要将数据的类型传递进来
	 * [{name:'zsp',value:1},{name:'zsp',value:2}] 转化为一个对象ListArray<T>
	 * 
	 * @param <T>
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unused")
	public static <T> List<T> json2List(String json, Class<T> clazz) {
		json = cleanJson(json);
		try {
			ObjectMapper mapp = new ObjectMapper();
			List<Map<String, Object>> datas = mapper.readValue(json, ArrayList.class);
			List<T> list = new ArrayList<T>();
			for (Map<String, Object> data : datas) {
				Object obj = clazz.getConstructor().newInstance();
				for (String key : data.keySet()) {
					BeanUtils.setProperty(obj, key, data.get(key));
				}
				list.add((T) obj);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 格式化Json ,将里面的 回车都给换掉
	 * 
	 * @param json
	 * @return
	 */
	public static String cleanJson(String json) {
		if (json != null && !"".equals(json)) {
			return json.replaceAll("\n", "");
		} else {
			return "";
		}
	}

}

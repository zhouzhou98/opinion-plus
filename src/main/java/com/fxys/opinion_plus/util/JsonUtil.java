package com.fxys.opinion_plus.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author :suyuzhou
 * @date :20210118
 * @description json工具类
 * @email :jack.su@jodoinc.com
 */
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setSerializationInclusion(Include.NON_NULL);
		//设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static String object2Json(Object o) {
		if (o == null) {
			return null;
		}

		String s = null;

		try {
			s = mapper.writeValueAsString(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}




}

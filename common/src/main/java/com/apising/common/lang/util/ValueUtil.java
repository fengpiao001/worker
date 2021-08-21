package com.apising.common.lang.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * 对于value的处理util
 *
 * @author lilin
 */
public class ValueUtil {

	public static JSONObject getNotNull(JSONObject value) {
		return null == value ? new JSONObject() : value;
	}

	public static JSONArray getNotNull(JSONArray value) {
		return null == value ? new JSONArray() : value;
	}

	public static String getNotNull(String value) {
		return null == value ? "" : value.trim();
	}

	public static String getNotNull(String value, String defaultValue) {
		if (null == defaultValue) {
			defaultValue = "";
		}
		return null == value ? defaultValue : value;
	}

	public static Integer getNotNull(Integer value, Integer defaultValue) {
		if (null == defaultValue) {
			defaultValue = 0;
		}
		return null == value ? defaultValue : value;
	}

	public static Integer getNotNull(Integer value) {
		return null == value ? 0 : value;
	}

	public static Long getNotNull(Long value, Long defaultValue) {
		if (null == defaultValue) {
			defaultValue = 0l;
		}
		return null == value ? defaultValue : value;
	}

	public static Long getNotNull(Long value) {
		return null == value ? 0 : value;
	}

	public static Double getNotNull(Double value, Double defaultValue) {
		if (null == defaultValue) {
			defaultValue = 0d;
		}
		return null == value ? defaultValue : value;
	}

	public static Double getNotNull(Double value) {
		return null == value ? 0d : value;
	}

	public static Float getNotNull(Float value, Float defaultValue) {
		if (null == defaultValue) {
			defaultValue = 0f;
		}
		return null == value ? defaultValue : value;
	}

	public static Float getNotNull(Float value) {
		return null == value ? 0f : value;
	}

	public static BigDecimal getNotNull(BigDecimal value) {
		return null == value ? BigDecimal.ZERO : value;
	}

	public static BigDecimal getNotNull(BigDecimal value, BigDecimal defaultValue) {
		if (null == defaultValue) {
			defaultValue = BigDecimal.ZERO;
		}

		return null == value ? defaultValue : value;
	}

	/**
	 * Translate integer to '是' or '否'
	 *
	 * @param value
	 * @return '是' when and only when value == 1
	 */
	public static String intToShiFou(Integer value) {
		if (value == null)
			return "否";
		if (value == 1)
			return "是";
		return "否";
	}

	public static boolean isBigDecimal(Object value) {
		if (value == null) {
			return false;
		}
		return value instanceof BigDecimal;
	}

	public static boolean isFloat(Object value) {
		if (value == null) {
			return false;
		}
		return value instanceof Float;
	}

	public static boolean isDouble(Object value) {
		if (value == null) {
			return false;
		}
		return value instanceof Double;
	}

	public static boolean isDecimal(Object value) {
		return isBigDecimal(value) || isFloat(value) || isDouble(value);
	}

	public static String setDataSourceNodeText(String... values) {
		return String.join(" - ", values);
	}

}

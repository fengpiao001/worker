package com.apising.common.lang.util;

import java.lang.reflect.Method;

public class ReflectUtil {
	/**
	 * 反射get属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Object obj, String fieldName) {
		Class<? extends Object> clazz = obj.getClass();
		String first = fieldName.charAt(0) + "";
		fieldName = "get" + fieldName.replaceFirst(first, first.toUpperCase());
		try {
			Method method = clazz.getMethod(fieldName);
			Object res = method.invoke(obj);
			return (T) res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反射set属性值
	 * @param obj
	 * @param fieldName
	 * @param val
	 */
	public static void set(Object obj, String fieldName, Object val) {
		Class<? extends Object> clazz = obj.getClass();
		String first = fieldName.charAt(0) + "";
		fieldName = "set" + fieldName.replaceFirst(first, first.toUpperCase());
		try {
			Method method = null;
			if (val == null)
				method = clazz.getMethod(fieldName, String.class);
			else
				method = clazz.getMethod(fieldName, val.getClass());
			if (method != null)
				method.invoke(obj, val);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

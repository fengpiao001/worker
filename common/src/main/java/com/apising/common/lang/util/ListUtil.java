package com.apising.common.lang.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtil {
	
	private final static String id = "id";
	
	/**
	 * 根据id转换map
	 * @param list
	 * @return
	 */
	public static <T> Map<Long,T> listToMap(List<T> list){
		if(CollectionUtil.isEmpty(list)){
			return new HashMap<>();
		}
		Map<Long,T> map = new HashMap<>(list.size());
		for(T item : list){
			map.put(ReflectUtil.get(item, id), item);
		}
		return map;
	}
	
	/**
	 * 获取list中的id
	 * @param list
	 * @return
	 */
	public static <T> List<Long> getListIds(List<T> list){
		if(CollectionUtil.isEmpty(list)){
			return Collections.emptyList();
		}
		List<Long> ids = new ArrayList<>(list.size());
		for(T item : list){
			ids.add(ReflectUtil.get(item, id));
		}
		return ids;
	}
	
	/**
	 * 获取list中的属性值
	 * @param list
	 * @return
	 */
	public static <T,R> List<R> getListField(List<T> list,String field){
		if(CollectionUtil.isEmpty(list)){
			return Collections.emptyList();
		}
		List<R> ids = new ArrayList<>(list.size());
		for(T item : list){
			ids.add(ReflectUtil.get(item, field));
		}
		return ids;
	}
}

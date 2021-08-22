package com.apising.common.lang.convertor;

import com.apising.common.lang.util.CollectionUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通用转换器
 */
public class BaseConvertor {

	/**
	 * 通用转换
	 * 
	 * @param src
	 *            原对象
	 * @param targetClazz
	 *            目的对象类
	 * @return
	 * @throws Exception
	 */
	public static <S, T> T change(S src, Class<T> targetClazz){
		if (src == null)
			return null;
		try {
			T target = targetClazz.newInstance();
			BeanUtils.copyProperties(src, target);
			return target;
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通用转换list
	 * @param srcList	原列表对象
	 * @param targetClazz	目的对象类
	 * @return
	 * @throws Exception
	 */
	public static <S, T> List<T> changeList(List<S> srcList, Class<T> targetClazz) {
		if (CollectionUtil.isEmpty(srcList))
			return Collections.emptyList();
		List<T> targetList = new ArrayList<>(srcList.size());
		for (S src : srcList) {
			T target = null;
			try {
				target = change(src, targetClazz);
			}catch (Exception e){
				throw new RuntimeException(e);
			}
			targetList.add(target);
		}
		return targetList;
	}
}

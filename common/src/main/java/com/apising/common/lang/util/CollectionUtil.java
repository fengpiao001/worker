package com.apising.common.lang.util;

import java.util.*;

/**
 * 集合util
 *
 * @author lilin
 */
public final class CollectionUtil {

	/**
	 * 返回在curList中第一个在referSet出现的元素
	 * 
	 * @param referSet
	 * @param curList
	 * @return
	 */
	public static <T> T getFirstMatched(Set<T> referSet, List<T> curList) {
		if (isEmpty(referSet) || isEmpty(curList)) {
			return null;
		}
		for (T t : curList) {
			if (referSet.contains(t)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * 返回在curSet中第一个在referSet出现的元素
	 * 
	 * @param referSet
	 * @param curSet
	 * @return
	 */
	public static <T> T getFirstMatched(Set<T> referSet, Set<T> curSet) {
		if (isEmpty(referSet) || isEmpty(curSet)) {
			return null;
		}
		for (T t : curSet) {
			if (referSet.contains(t)) {
				return t;
			}
		}
		return null;
	}

	public static <K, V> List<K> mapKeyToList(Map<K, V> map) {
		if (isEmpty(map)) {
			return Collections.emptyList();
		}
		List<K> list = new ArrayList<K>();
		list.addAll(map.keySet());
		return list;
	}

	public static <K, V> List<V> mapValueToList(Map<K, V> map) {
		if (isEmpty(map)) {
			return Collections.emptyList();
		}
		List<V> list = new ArrayList<V>();
		list.addAll(map.values());
		return list;
	}

	public static <K, V> Set<V> mapValueToSet(Map<K, V> map) {
		if (isEmpty(map)) {
			return Collections.emptySet();
		}
		Set<V> set = new HashSet<V>();
		set.addAll(map.values());
		return set;
	}

	public static <K, V> Set<K> mapKeyToSet(Map<K, V> map) {
		if (isEmpty(map)) {
			return Collections.emptySet();
		}
		return map.keySet();
	}

	public static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}

	public static <T> Set<T> newHashSet() {
		return new HashSet<T>();
	}

	public static <T> List<T> newArrayList() {
		return new ArrayList<T>();
	}

	public static <T> boolean largeOrEqualSize(List<T> list, int size) {
		return (null == list || 0 > size) ? false : size <= list.size();
	}

	public static <T> boolean largeOrEqualSize(T[] array, int size) {
		return (null == array || 0 > size) ? false : size <= array.length;
	}

	public static <T> boolean largeOrEqualSize(Set<T> set, int size) {
		return (null == set || 0 > size) ? false : size <= set.size();
	}

	public static <K, V> boolean largeOrEqualSize(Map<K, V> map, int size) {
		return (null == map || 0 > size) ? false : size <= map.size();
	}

	public static <T> boolean largeSize(List<T> list, int size) {
		return (null == list || 0 > size) ? false : size < list.size();
	}

	public static <T> boolean largeSize(T[] array, int size) {
		return (null == array || 0 > size) ? false : size < array.length;
	}

	public static <T> boolean largeSize(Set<T> set, int size) {
		return (null == set || 0 > size) ? false : size < set.size();
	}

	public static <K, V> boolean largeSize(Map<K, V> map, int size) {
		return (null == map || 0 > size) ? false : size < map.size();
	}

	public static <T> boolean equalsSize(List<T> list, int size) {
		return (null == list || 0 > size) ? false : size == list.size();
	}

	public static <T> boolean equalsSize(T[] array, int size) {
		return (null == array || 0 > size) ? false : size == array.length;
	}

	public static <T> boolean equalsSize(Set<T> set, int size) {
		return (null == set || 0 > size) ? false : size == set.size();
	}

	public static <K, V> boolean equalsSize(Map<K, V> map, int size) {
		return (null == map || 0 > size) ? false : size == map.size();
	}

	public static <T> boolean isEmpty(T[] array) {
		return null == array || 0 >= array.length;
	}

	public static <T> boolean isEmpty(List<T> list) {
		return null == list || 0 >= list.size();
	}

	public static <T> boolean isEmpty(Set<T> set) {
		return null == set || 0 >= set.size();
	}

	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return null == map || 0 >= map.size();
	}

	/**
	 * 数组转化为list
	 *
	 * @param array
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> arrayToList(T[] array) throws Exception {
		List<T> list = new ArrayList<T>();
		if (null == array || 0 >= array.length) {
			return list;
		}
		for (T t : array) {
			list.add(t);
		}
		return list;
	}

	/**
	 * 数组转化为set
	 *
	 * @param array
	 * @return
	 * @throws Exception
	 */
	public static <T> Set<T> arrayToSet(T[] array) {
		Set<T> set = new HashSet<T>();
		if (null == array || 0 >= array.length) {
			return set;
		}
		for (T t : array) {
			set.add(t);
		}
		return set;
	}

	/**
	 * set转化为list
	 *
	 * @param set
	 * @return
	 */
	public static <T> List<T> setToList(Set<T> set) {
		if (null == set || 0 >= set.size()) {
			return Collections.emptyList();
		}
		List<T> list = new ArrayList<T>();
		list.addAll(set);
		return list;
	}

	/**
	 * set转化为list
	 *
	 * @param set
	 * @return
	 */
	public static <T> List<T> toList(Collection<T> set) {
		if (null == set || 0 >= set.size()) {
			return Collections.emptyList();
		}
		List<T> list = new ArrayList<T>();
		list.addAll(set);
		return list;
	}

	/**
	 * set转化为list
	 *
	 * @param list
	 * @return
	 */
	public static <T> Set<T> listToSet(List<T> list) {
		if (null == list || 0 >= list.size()) {
			return Collections.emptySet();
		}
		Set<T> set = new HashSet<T>();
		set.addAll(list);
		return set;
	}

	public static <T> List<T> collectionToList(Collection<T> collection) throws Exception {
		List<T> list = new ArrayList<T>();
		if (null == collection || 0 >= collection.size()) {
			return list;
		}
		for (T t : collection) {
			list.add(t);
		}
		return list;
	}

	/**
	 * orginList中的元素全部加到targetList中，如果其中有null元素 ， 则忽略
	 *
	 * @param orginList
	 * @param targetList
	 */
	public static <T> void addAllTrimNull(List<T> orginList, List<T> targetList) {
		if (null == orginList || 0 >= orginList.size() || null == targetList) {
			return;
		}
		for (T t : orginList) {
			if (null != t) {
				targetList.add(t);
			}
		}
	}

	/**
	 * orginList中的元素全部加到targetList中，如果其中有null元素 ， 则忽略
	 *
	 * @param orginList
	 * @param targetList
	 */
	public static <T> void copyListNoNull(List<T> orginList, List<T> targetList) {
		if (null == orginList || 0 >= orginList.size() || null == targetList) {
			return;
		}
		for (T t : orginList) {
			if (null != t) {
				targetList.add(t);
			}
		}
	}

	/**
	 * 得到orginList中的所有非空元素的List集合的浅拷贝
	 *
	 * @param orginList
	 * @param orginList
	 */
	public static <T> List<T> getNoNullCopy(List<T> orginList) {
		if (null == orginList || 0 >= orginList.size()) {
			return Collections.emptyList();
		}
		List<T> targetList = new ArrayList<T>();
		for (T t : orginList) {
			if (null != t) {
				targetList.add(t);
			}
		}
		return targetList;
	}

	public static <T> List<T> combine(List<T> firstList, List<T> secondList) {
		List<T> combinedList = new ArrayList<>();
		combinedList.addAll(firstList);
		combinedList.addAll(secondList);
		return combinedList;
	}
	
}

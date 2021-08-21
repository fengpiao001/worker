package com.apising.common.lang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 字符串处理
 *
 * @author lilin
 */
public class StringUtil {

	/**
	 * @param str
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public static String substringByChinsesWord(String str, int startIndex, int endIndex) throws RuntimeException {
		if (isEmpty(str)) {
			return str;
		}
		if (startIndex >= endIndex) {
			throw new RuntimeException("[StringUtil.substringByChinsesWord]:failure, and startIndex>=endIndex");
		}
		int shortCharNum = 0;
		int longCharNum = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (3000 <= (int) ch) {
				longCharNum++;
			} else {
				shortCharNum++;
			}
			int longNum = getLongCharNum(shortCharNum, longCharNum);
			if (startIndex <= longNum && longNum < endIndex) {
				builder.append(ch);
			}
		}
		return builder.toString();
	}

	private static int getLongCharNum(int shortCharNum, int longCharNum) {
		return (shortCharNum * 3 + longCharNum * 5) / 5;
	}

	/**
	 * 获取str的窗口字符串，如果str长度小于minSize，则返回结果会在str后补充minSize-str.length个空格；
	 * 如果str长度大于maxSize，则返回结果会在str中截取前maxSize个字符的字符串返回
	 * 
	 * @param str
	 * @param minSize
	 * @param maxSize
	 * @return
	 */
	public static String getWindowString(String str, Integer minSize, Integer maxSize) {
		if (StringUtil.isEmpty(str) || (null == minSize && null == maxSize)) {
			return str;
		}
		if (null != minSize && null != maxSize && minSize > maxSize) {
			return "";
		}
		if (null == maxSize) {
			if (str.length() < minSize) {
				StringBuilder builder = new StringBuilder();
				builder.append(str);
				for (int i = 0; i < minSize - str.length(); i++) {
					builder.append(" ");
				}
				return builder.toString();
			}
		} else {
			if (str.length() > maxSize) {
				return str.substring(0, maxSize);
			} else if (null != minSize && str.length() < minSize) {
				StringBuilder builder = new StringBuilder();
				builder.append(str);
				for (int i = 0; i < minSize - str.length(); i++) {
					builder.append(" ");
				}
				return builder.toString();
			}
		}
		return str;
	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean containsEnglishChar(String str) {
		if (isEmpty(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (isEnglishChar(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param ch
	 * @return
	 */
	public static boolean isNumberChar(char ch) {
		return '0' <= ch && ch <= '9';
	}

	/**
	 * @param ch
	 * @return
	 */
	public static boolean isEnglishChar(char ch) {
		return isUpperCaseChar(ch) || isLowerCaseChar(ch);
	}

	/**
	 * @param ch
	 * @return
	 */
	public static boolean isUpperCaseChar(char ch) {
		return 'A' <= ch && ch <= 'Z';
	}

	/**
	 * @param ch
	 * @return
	 */
	public static boolean isLowerCaseChar(char ch) {
		return 'a' <= ch && ch <= 'z';
	}

	/**
	 * 将文本切成单词和符号
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> split(List<String> strList, String separator, boolean saveSeparator) {
		if (CollectionUtil.isEmpty(strList)) {
			return Collections.EMPTY_LIST;
		}
		List<String> itemList = new ArrayList<String>();
		for (String itemStr : strList) {
			if (null == itemStr || "".equals(itemStr)) {
				continue;
			}
			itemList.addAll(split(itemStr, separator, saveSeparator));
		}
		return itemList;
	}

	/**
	 * 将文本切成单词和符号
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> split(String str, String separator, boolean saveSeparator) {
		if (StringUtil.isEmpty(str)) {
			return Collections.EMPTY_LIST;
		}
		List<String> itemList = new ArrayList<String>();
		char separatorFirtChar = separator.charAt(0);
		int preSegmentStartIndex = 0;
		int i = 0;
		for (; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == separatorFirtChar) {
				// boolean isMatched = true;
				int j = 1;
				for (; j < separator.length() && i + j < str.length(); j++) {
					if (separator.charAt(j) != str.charAt(i + j)) {
						break;
					}
				}
				if (j >= separator.length()) {
					if (preSegmentStartIndex < i) {
						itemList.add(str.substring(preSegmentStartIndex, i));
					}
					if (saveSeparator) {
						itemList.add(separator);
					}

					preSegmentStartIndex = i + j;
					i = i + j - 1;
				}
			}
		}
		if (preSegmentStartIndex < str.length()) {
			itemList.add(str.substring(preSegmentStartIndex, str.length()));
		}
		return itemList;
	}

	/**
	 * 将sourceStr中所有匹配上anchorStr得字符串anchorStr的部分都替换成replaceStr
	 *
	 * @param sourceStr
	 * @param anchorStr
	 * @param replaceStr
	 * @return
	 */
	public static String replaceAll(String sourceStr, String anchorStr, String replaceStr) {
		if (null == sourceStr || null == anchorStr || null == replaceStr) {
			return sourceStr;
		}
		if (anchorStr.length() > sourceStr.length()) {
			return sourceStr;
		}
		char anchorFirtChar = anchorStr.charAt(0);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < sourceStr.length(); i++) {
			char ch = sourceStr.charAt(i);
			if (ch != anchorFirtChar) {
				builder.append(ch);
				continue;
			}
			boolean isMatched = true;
			int j = i;
			for (int k = 0; k < anchorStr.length(); k++) {
				j = i + k;
				if (j >= sourceStr.length() || sourceStr.charAt(j) != anchorStr.charAt(k)) {
					isMatched = false;
					continue;
				}
			}
			if (isMatched) {
				builder.append(replaceStr);
				i = j;
			} else {
				builder.append(ch);
			}
		}

		return builder.toString();
	}

	public static String nullParser(String str) {
		return str == null ? "" : str;
	}

	public static String toLowerCase(String str) {
		if (isEmpty(str)) {
			return str;
		}
		return str.toLowerCase();
	}

	public static String toTrimAndLowerCase(String str) {
		if (null == str) {
			return null;
		}
		return str.trim().toLowerCase();
	}

	public static boolean equals(String str1, String str2) {
		if (null == str1 || null == str2) {
			return false;
		}
		return str1.equals(str2);
	}

	/**
	 * 判断是不是手机号
	 *
	 * @param keyword
	 * @return
	 */
	public static boolean isMobileNumber(String keyword) {
		return null != keyword && keyword.matches("^1[0-9]{10}$");
	}

	public static List<String> spiltToString(String str, String separator) throws Exception {
		return spiltToString(str, separator, true);
	}

	/**
	 * 把字符串切分成字符串列表
	 *
	 * @param str
	 * @param separator
	 * @return
	 * @throws Exception
	 */
	public static List<String> spiltToString(String str, String separator, boolean isDirect) throws Exception {
		if (isEmpty(str)) {
			throw new Exception("str is empty.");
		}
		List<String> l = new ArrayList<String>();
		if (null == separator) {
			l.add(str);
			return l;
		}
		if (isDirect) {
			List<String> splittedList = split(str, separator, false);
			for (String numStr : splittedList) {
				if (isEmpty(numStr)) {
					continue;
				}
				l.add(numStr);
			}
			return l;
		}
		String[] splitArray = str.trim().split(separator);
		for (String numStr : splitArray) {
			if (isEmpty(numStr)) {
				continue;
			}
			l.add(numStr);
		}
		return l;
	}

	public static String[] spiltToStringArray(String str, String separator) throws Exception {
		if (null == str) {
			throw new Exception("str is null.");
		}
		String[] splitArray = str.trim().split(separator);
		return splitArray;
	}

	public static List<Long> spiltToLong(String str, String separator) throws Exception {
		return spiltToLong(str, separator, true);
	}

	public static List<Long> spiltToLong(String str, String separator, boolean isDirect) throws Exception {
		if (isEmpty(str)) {
			throw new Exception("str is empty.");
		}
		List<Long> l = new ArrayList<Long>();
		if (null == separator) {
			l.add(Long.parseLong(str));
			return l;
		}
		if (isDirect) {
			List<String> splittedList = split(str, separator, false);
			for (String numStr : splittedList) {
				if (isEmpty(numStr)) {
					continue;
				}
				l.add(Long.parseLong(numStr.trim()));
			}
			return l;
		}
		String[] splitArray = str.trim().split(separator);
		for (String numStr : splitArray) {
			if (isEmpty(numStr)) {
				continue;
			}
			l.add(Long.parseLong(numStr.trim()));
		}
		return l;
	}

	public static List<Integer> spiltToInteger(String str, String separator) throws Exception {
		return spiltToInteger(str, separator, true);
	}

	public static List<Integer> spiltToInteger(String str, String separator, boolean isDirect) throws Exception {
		if (isEmpty(str)) {
			throw new Exception("str is empty.");
		}
		List<Integer> l = new ArrayList<Integer>();
		if (null == separator) {
			l.add(Integer.parseInt(str));
			return l;
		}
		if (isDirect) {
			List<String> splittedList = split(str, separator, false);
			for (String numStr : splittedList) {
				if (isEmpty(numStr)) {
					continue;
				}
				l.add(Integer.parseInt(numStr.trim()));
			}
			return l;
		}
		String[] splitArray = str.trim().split(separator);
		for (String numStr : splitArray) {
			if (isEmpty(numStr)) {
				continue;
			}
			l.add(Integer.parseInt(numStr.trim()));
		}
		return l;
	}

	public static List<Double> spiltToDouble(String str, String separator) throws Exception {
		return spiltToDouble(str, separator, true);
	}

	public static List<Double> spiltToDouble(String str, String separator, boolean isDirect) throws Exception {
		if (isEmpty(str)) {
			throw new Exception("str is empty.");
		}
		List<Double> l = new ArrayList<Double>();
		if (null == separator) {
			l.add(Double.parseDouble(str));
			return l;
		}
		if (isDirect) {
			List<String> splittedList = split(str, separator, false);
			for (String numStr : splittedList) {
				if (isEmpty(numStr)) {
					continue;
				}
				l.add(Double.parseDouble(numStr.trim()));
			}
			return l;
		}
		String[] splitArray = str.trim().split(separator);
		for (String numStr : splitArray) {
			if (isEmpty(numStr)) {
				continue;
			}
			l.add(Double.parseDouble(numStr.trim()));
		}
		return l;
	}

	public static boolean isEmpty(String string) {
		if (null == string || "".equals(string.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 切割类似"price:asc,sell:desc"这样的字符串成为map
	 *
	 * @param str
	 * @param mainSeparator
	 *            类似上面的“,”
	 * @param subSeparator
	 *            类似上面的“:”
	 * @return
	 */
	public static Map<String, String> splitToMap(String str, String mainSeparator, String subSeparator)
			throws Exception {
		if (isEmpty(str)) {
			return new HashMap<String, String>();
		}
		String[] strArray = str.trim().split(mainSeparator);
		Map<String, String> map = new HashMap<String, String>(strArray.length);

		for (String kv : strArray) {
			String[] kvArray = kv.trim().split(subSeparator);
			if (kvArray.length != 2 || isEmpty(kvArray[0]) || isEmpty(kvArray[1])) {
				throw new Exception("the format of str is error, error: \"" + kv + "\" in " + str);
			}
			map.put(kvArray[0], kvArray[1]);
		}
		return map;
	}

	public static String toListStatement(String pre, List<?> list, String splitTag, String end) {
		if (null == list || 0 >= list.size() || null == splitTag) {
			return null;
		}
		pre = (null == pre) ? "" : pre;
		end = (null == end) ? "" : end;

		StringBuilder builder = new StringBuilder();
		builder.append(pre);
		boolean isFirst = true;

		for (Object obj : list) {
			if (isFirst) {
				isFirst = false;
			} else {
				builder.append(splitTag);
			}
			builder.append(obj);
		}
		builder.append(end);
		return builder.toString();
	}

	public static String toSetStatement(String pre, Set<?> set, String splitTag, String end) {
		if (null == set || 0 >= set.size() || null == splitTag) {
			return null;
		}
		pre = (null == pre) ? "" : pre;
		end = (null == end) ? "" : end;

		StringBuilder builder = new StringBuilder();
		builder.append(pre);
		boolean isFirst = true;

		for (Object obj : set) {
			if (isFirst) {
				isFirst = false;
			} else {
				builder.append(splitTag);
			}
			builder.append(obj);
		}
		builder.append(end);
		return builder.toString();
	}

	// 将str转为list
	public static List<String> toList(String str, String splitChar) {
		if (isEmpty(str)) {
			return null;
		}
		String[] array = str.split(splitChar);
		List<String> list = new ArrayList<>(array.length);
		list.addAll(Arrays.asList(array));
		return list;
	}

	// 将a-b转为[a b]
	public static String toRangeStr(String str, String splitChar) {
		if (isEmpty(str)) {
			return null;
		}
		String[] array = str.split(splitChar);
		return "[" + array[0] + " " + array[1] + "]";
	}

	public static String toRangeStr(Object val1, String splitChar, Object val2) {
		if (null == val1 || null == val2) {
			return null;
		}
		String str1 = null == val1 ? "" : val1 + "";
		String str2 = null == val2 ? "" : val2 + "";
		return str1 + splitChar + str2;
	}

	public static String join(String splitStr, String... strArray) {
		if (null == splitStr || null == strArray) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (String obj : strArray) {
			if (null == obj)
				continue;
			if (!first) {
				builder.append(splitStr);
			} else {
				first = false;
			}
			builder.append(obj);
		}
		return builder.toString();
	}

	public static String join(String splitStr, List<String> stringList) {
		if (null == splitStr || null == stringList) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (String obj : stringList) {
			if (null == obj)
				continue;
			if (!first) {
				builder.append(splitStr);
			} else {
				first = false;
			}
			builder.append(obj);
		}
		return builder.toString();
	}

	/**
	 * 将类似“0001010011001”中为1的索引存到int型的数组 中
	 *
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> strIndexToIntList(String str, char indexStr) throws Exception {
		if (null == str) {
			return Collections.EMPTY_LIST;
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < str.length(); i++) {
			if (indexStr == str.charAt(i)) {
				list.add(i);
			}
		}
		return list;
	}

	/**
	 * 后期将一些其他的字符做为trim的空字符列表中
	 *
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (null == str)
			return null;
		char[] tags = { '\n', '\t' };
		str = str.trim();
		int startIndex = 0;
		int endIndex = str.length();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!inCharSet(tags, ch)) {
				startIndex = i;
				break;
			}
		}
		for (int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			if (!inCharSet(tags, ch)) {
				endIndex = i + 1;
				break;
			}
		}
		return str.substring(startIndex, endIndex);
	}

	private static boolean inCharSet(char[] array, char ch) {
		for (char item : array) {
			if (item == ch) {
				return true;
			}
		}
		return false;
	}

	public static String trim(String str, String tag) {
		if (null == str)
			return null;
		if (str.equals(tag)) {
			return "";
		}
		int indexStart = 0;
		int indexEnd = str.length();
		if (str.startsWith(tag)) {
			indexStart = tag.length();
		}
		if (str.endsWith(tag)) {
			indexEnd = str.length() - tag.length();
		}
		if (indexEnd >= indexStart) {
			str = str.substring(indexStart, indexEnd);
		}
		return str;
	}

	public static String limit(String str, int length) {
		return str.length() <= length ? str : str.substring(0, length - 1);
	}

	public static boolean startWith(String str, String startTag) {
		if (StringUtil.isEmpty(str) || StringUtil.isEmpty(startTag) || startTag.length() > str.length()) {
			return false;
		}
		for (int i = 0; i < startTag.length(); i++) {
			if (startTag.charAt(i) != str.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static boolean endsWith(String str, String endTag) {
		if (StringUtil.isEmpty(str) || StringUtil.isEmpty(endTag) || endTag.length() > str.length()) {
			return false;
		}
		int strIndex = str.length() - 1;
		for (int i = endTag.length() - 1; i >= 0; i--, strIndex--) {
			if (endTag.charAt(i) != str.charAt(strIndex)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String str = "abc123";
		String endTag = "aabc123";
		System.out.println(endsWith(str, endTag));
	}

}

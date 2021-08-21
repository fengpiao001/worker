package com.apising.common.lang.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 性别枚举类 
 * 
 */
public enum Sex {
	unknown(0, "unknown", "未定"), 
	man(1, "man", "男"),  
	woman(2, "woman", "女");  

	private Integer index;
	private String code;
	private String name;

	Sex(Integer index, String code, String name) {
		this.index = index;
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public Integer getIndex() {
		return index;
	}

	public boolean equals(String code) {		 
		return null == code ? false : (code.equals(this.code)); 
	}
	
	public boolean equals(Integer index) {
		return null == index ? false : (index.equals(this.index)); 
	}
	
	private JSONObject toJSONObject(){
		JSONObject item = new JSONObject();
		item.put("index", index);
		item.put("name", name);
		return item;
	}
	
	public static Sex getEnum(String code) {
		if (null == code) {
			return null;
		}
		for (Sex type : Sex.values()) {
			if (type.code.equals(code)) {
				return type;
			}
		}
		return null;
	}
	
	public static Sex getEnum(Integer index) {
		if (null == index) {
			return null;
		}
		for (Sex type : Sex.values()) {
			if (type.index.equals(index)) {
				return type;
			}
		}
		return null;
	} 
	
	public static JSONArray getJsonArray() {
		JSONArray array = new JSONArray();
		for (Sex item : Sex.values()) {
			array.add(item.toJSONObject());
		}
		return array;
	}

}

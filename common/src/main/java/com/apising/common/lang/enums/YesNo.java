package com.apising.common.lang.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 是否枚举类
 *
 * @author lilin
 */
public enum YesNo {

    yes(1, "y", "是"), // 是
    no(0, "n", "否"); // 否

    private Integer index;
    private String code;
    private String name;

    YesNo(Integer index, String code, String name) {
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

    private JSONObject toJSONObject() {
        JSONObject item = new JSONObject();
        item.put("index", index);
        item.put("name", name);
        return item;
    }

    public static YesNo getEnum(String code) {
        if (null == code) {
            return null;
        }
        for (YesNo type : YesNo.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }

    public static YesNo getEnum(Integer index) {
        if (null == index) {
            return null;
        }
        for (YesNo type : YesNo.values()) {
            if (type.index.equals(index)) {
                return type;
            }
        }
        return null;
    }

    public static JSONArray getJsonArray() {
        JSONArray array = new JSONArray();
        for (YesNo item : YesNo.values()) {
            array.add(item.toJSONObject());
        }
        return array;
    }

    public static JSONArray getJson() {
        JSONArray jsonvalues = new JSONArray();
        for (YesNo item : YesNo.values()) {
            JSONObject valueJsonObject = new JSONObject();
            valueJsonObject.put("id", item.getIndex());
            valueJsonObject.put("text", item.getName());
            jsonvalues.add(valueJsonObject);
        }
        return jsonvalues;
    }

    public static String getNameByIndex(Integer id) {
        if (id == null) return "";
        YesNo obj = getEnum(id);
        if (obj != null) return obj.getName();
        return "";
    }

}

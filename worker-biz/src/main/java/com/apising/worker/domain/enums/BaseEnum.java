package com.apising.worker.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 枚举的基础接口实现
 * @author
 */
public interface BaseEnum {

    Integer getIndex();

    String getCode();

    String getName();

    default boolean equal(Integer index){
        return index != null  && index.equals(getIndex());
    }

    default boolean equal(String code){
        return StringUtils.isNotEmpty(code) && code.equals(getCode());
    }

    /**
     * 根据index获取名称
     * @param index
     * @return
     */
    default String getNameByIndex(Integer index){
        BaseEnum baseEnum = valueOfIndex(this.getClass(),index);
        if(baseEnum == null){
            return null;
        }
        return baseEnum.getName();
    }

    /**
     * 根据index获取名称
     * @param code
     * @return
     */
    default String getNameByCode(String code){
        BaseEnum baseEnum = valueOfCode(this.getClass(),code);
        if(baseEnum == null){
            return null;
        }
        return baseEnum.getName();
    }


    /**
     * 根据index获取枚举
     * @param index
     * @param <T>
     * @return
     */
    static <T extends BaseEnum> T valueOfIndex(Class<? extends BaseEnum> enumType,Integer index){
        if(index == null){
            return null;
        }
        T[] enums = (T[])enumType.getEnumConstants();
        if(enums == null){
            return null;
        }
        for(T item : enums){
            if(index.equals(item.getIndex())){
                return item;
            }
        }
        return null;
    }

    /**
     * 根据code获取枚举
     * @param code
     * @return
     */
    static <T extends BaseEnum> T valueOfCode(Class<? extends BaseEnum> enumType,String code){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        T[] enums = (T[])enumType.getEnumConstants();
        if(enums == null){
            return null;
        }
        for(T item : enums){
            if(code.equals(item.getCode())){
                return item;
            }
        }
        return null;
    }
}

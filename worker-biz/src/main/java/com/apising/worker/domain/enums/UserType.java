package com.apising.worker.domain.enums;

/**
 * 用户类型
 * @author
 */

public enum UserType implements BaseEnum{
    admin(0,"admin","管理员"),
    worker(1,"worker","工人"),
    employee(2,"employee","员工");

    private Integer index;

    private String code;

    private String name;

    private UserType(Integer index,String code,String name){
        this.index = index;
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}

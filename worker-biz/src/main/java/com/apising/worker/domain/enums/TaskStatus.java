package com.apising.worker.domain.enums;

/**
 * 任务状态
 * @author
 */

public enum TaskStatus implements BaseEnum{
    draft(0,"draft","草稿"),
    publishing(10,"publishing","招聘中"),
    executing(20,"executing","进行中"),
    finished(30,"finished","已完成"),
    stoped(40,"stoped","已终止");

    private Integer index;

    private String code;

    private String name;

    private TaskStatus(Integer index,String code,String name){
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

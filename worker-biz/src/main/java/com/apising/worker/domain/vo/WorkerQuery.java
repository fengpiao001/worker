package com.apising.worker.domain.vo;

import com.apising.common.lang.domain.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 工人的查询请求对象
 * @author tianxu
 */
@Getter
@Setter
@ToString
public class WorkerQuery extends Page {

    private String mobile;

    private String realName;
}

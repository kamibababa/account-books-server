package com.zytd.account.books.param.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IncomeOrderPageParam implements Serializable {
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页页数")
    private Integer pageSize;

    @ApiModelProperty(value = "1-批发 2-零售")
    private Integer orderType;

    @ApiModelProperty(value = "开始时间 2023-10-1")
    private String startTime;

    @ApiModelProperty(value = "结束时间 2023-10-1")
    private String endTime;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "状态 1-已结清 2-未结清")
    private Integer status;

    @ApiModelProperty(value = "用户ID",hidden = true)
    private Long memberId;
}

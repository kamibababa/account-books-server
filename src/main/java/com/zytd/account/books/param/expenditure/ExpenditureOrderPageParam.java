package com.zytd.account.books.param.expenditure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExpenditureOrderPageParam implements Serializable {
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页页数")
    private Integer pageSize;

    @ApiModelProperty(value = "类型 1-进货 2-其他")
    private Integer type;

    @ApiModelProperty(value = "开始时间 2023-10-1")
    private String startTime;

    @ApiModelProperty(value = "结束时间 2023-10-1")
    private String endTime;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "产品类型id")
    private Long productTypeId;
}

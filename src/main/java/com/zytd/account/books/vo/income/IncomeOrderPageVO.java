package com.zytd.account.books.vo.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IncomeOrderPageVO implements Serializable {
    @ApiModelProperty(value = "收入订单id")
    private Long incomeOrderId;

    @ApiModelProperty(value = "类型 1-批发 2-零售")
    private Integer type;

    @ApiModelProperty(value = "用户名字")
    private String userName;

    @ApiModelProperty(value = "总金额，单位元")
    private Integer totalMoney;

    @ApiModelProperty(value = "未付金额，单位元")
    private Integer unpaidMoney;

    @ApiModelProperty(value = "时间 2023-11-21")
    private String day;

    @ApiModelProperty(value = "状态 1-已结清 2-未结清")
    private Integer status;
}

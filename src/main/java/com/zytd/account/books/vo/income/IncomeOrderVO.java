package com.zytd.account.books.vo.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IncomeOrderVO implements Serializable {

    @ApiModelProperty(value = "收入订单id")
    private Long incomeOrderId;

    @ApiModelProperty(value = "类型 1-批发 2-零售", required = true)
    private String type;

    @ApiModelProperty(value = "用户名字")
    private String userName;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "总金额，单位元", required = true)
    private Integer totalMoney;

    @ApiModelProperty(value = "已付金额，单位元", required = true)
    private Integer paidMoney;

    @ApiModelProperty(value = "未付金额，单位元", required = true)
    private Integer unpaidMoney;

    @ApiModelProperty(value = "时间 2023-11-21", required = true)
    private String day;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态 1-已结清 2-未结清")
    private Integer status;

    @ApiModelProperty(value = "订单详情")
    private List<IncomeOrderDetailVO> orderDetails;
}

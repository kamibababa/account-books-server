package com.zytd.account.books.param.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IncomeOrderUpdateParam implements Serializable {
    @ApiModelProperty(value = "收入订单id")
    private Long incomeOrderId;

    @ApiModelProperty(value = "类型 1-批发 2-零售", required = true)
    private Integer type;

    @ApiModelProperty(value = "用户id")
    private Long userManagerId;

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

    @ApiModelProperty(value = "订单详情")
    private List<IncomeOrderDetailAddParam> orderDetail;

    @Data
    public static class IncomeOrderDetailAddParam implements Serializable{
        @ApiModelProperty(value = "商品类型id")
        private Long productTypeId;

        @ApiModelProperty(value = "重量，单位斤")
        private Integer weight;

        @ApiModelProperty(value = "货品金额，单位元")
        private Integer money;
    }
}

package com.zytd.account.books.vo.expenditure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExpenditureOrderPageVO implements Serializable {
    @ApiModelProperty(value = "支出订单id")
    private Long expenditureOrderId;

    @ApiModelProperty(value = "用户名字")
    private String username;

    @ApiModelProperty(value = "类型 1-进货 2-其他")
    private Integer type;

    @ApiModelProperty(value = "商品类型")
    private String productType;

    @ApiModelProperty(value = "总金额，单位元")
    private Integer totalMoney;

    @ApiModelProperty(value = "重量，单位斤")
    private Integer weight;

    @ApiModelProperty(value = "时间")
    private String day;
}

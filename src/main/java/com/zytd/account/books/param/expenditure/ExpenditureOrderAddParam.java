package com.zytd.account.books.param.expenditure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ExpenditureOrderAddParam implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "类型 1-进货 2-其他")
    private Integer type;

    @ApiModelProperty(value = "时间")
    private String day;

    @ApiModelProperty(value = "产品类型id")
    private Long productTypeId;

    @ApiModelProperty(value = "重量，单位斤")
    private Integer weight;

    @ApiModelProperty(value = "单价，单位元")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "货品金额，单位元")
    private Integer money;

    @ApiModelProperty(value = "运输费用，单位元")
    private Integer transportMoney;

    @ApiModelProperty(value = "其他费用，单位元")
    private Integer otherMoney;

    @ApiModelProperty(value = "总金额，单位元")
    private Integer totalMoney;

    @ApiModelProperty(value = "备注")
    private String remark;
}

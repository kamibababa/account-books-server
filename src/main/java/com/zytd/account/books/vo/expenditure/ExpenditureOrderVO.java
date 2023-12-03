package com.zytd.account.books.vo.expenditure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ExpenditureOrderVO implements Serializable {
    @ApiModelProperty(value = "支出订单id")
    private Long expenditureOrderId;

    @ApiModelProperty(value = "用户名字")
    private String userName;

    @ApiModelProperty(value = "类型 1-进货 2-其他")
    private Integer type;

    @ApiModelProperty(value = "商品类型")
    private String productType;

    @ApiModelProperty(value = "总金额，单位元")
    private Integer totalMoney;

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

    @ApiModelProperty(value = "时间")
    private String day;

    @ApiModelProperty(value = "备注")
    private String remark;
}

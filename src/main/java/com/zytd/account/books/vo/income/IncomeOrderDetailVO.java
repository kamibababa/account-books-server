package com.zytd.account.books.vo.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IncomeOrderDetailVO implements Serializable {

    @ApiModelProperty(value = "商品类型")
    private Long productTypeId;

    @ApiModelProperty(value = "商品名称")
    private String productTypeName;

    @ApiModelProperty(value = "重量，单位斤")
    private Integer weight;

    @ApiModelProperty(value = "货品金额，单位元")
    private Integer money;
}

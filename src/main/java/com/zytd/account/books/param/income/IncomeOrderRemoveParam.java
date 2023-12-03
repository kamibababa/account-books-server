package com.zytd.account.books.param.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IncomeOrderRemoveParam implements Serializable {
    @ApiModelProperty(value = "收入订单id")
    private Long incomeOrderId;
}

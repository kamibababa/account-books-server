package com.zytd.account.books.param.expenditure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExpenditureOrderRemoveParam implements Serializable {
    @ApiModelProperty(value = "支出订单id")
    private Long expenditureOrderId;
}

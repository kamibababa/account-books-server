package com.zytd.account.books.vo.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IncomeVO implements Serializable {
    @ApiModelProperty(value = "总收入")
    private String totalIncome;

    @ApiModelProperty(value = "总支出")
    private String totalExpenditure;

    @ApiModelProperty(value = "总欠款")
    private String totalUnpaidMoney;
}

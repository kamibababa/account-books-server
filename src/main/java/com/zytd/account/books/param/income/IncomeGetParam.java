package com.zytd.account.books.param.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IncomeGetParam implements Serializable {
    @ApiModelProperty(value = "开始时间 2023-10-1")
    private String startTime;

    @ApiModelProperty(value = "结束时间 2023-10-1")
    private String endTime;
}

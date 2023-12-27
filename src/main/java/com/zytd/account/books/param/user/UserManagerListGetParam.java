package com.zytd.account.books.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserManagerListGetParam implements Serializable {
    @ApiModelProperty(value = "类型 1-客户 2-供货人", required = true)
    private Integer type;

    @ApiModelProperty("页数")
    private Integer pageNum;

    @ApiModelProperty("条数")
    private Integer pageSize;

    @ApiModelProperty(value = "会员ID",hidden = true)
    private Long memberId;

    @ApiModelProperty(value = "查询输入")
    private String keyword;
}

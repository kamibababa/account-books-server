package com.zytd.account.books.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserManagerListGetParam implements Serializable {
    @ApiModelProperty(value = "类型 1-客户 2-供货人", required = true)
    private Integer type;
}

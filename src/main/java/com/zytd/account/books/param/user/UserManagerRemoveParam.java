package com.zytd.account.books.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserManagerRemoveParam implements Serializable {
    @ApiModelProperty(value = "用户管理id")
    private Long userManagerId;
}

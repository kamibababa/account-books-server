package com.zytd.account.books.param.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginByPasswordParam implements Serializable {
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
    @ApiModelProperty(value = "密码，加密后的密码", required = true)
    private String password;
}

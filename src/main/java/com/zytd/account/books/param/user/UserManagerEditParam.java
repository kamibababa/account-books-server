package com.zytd.account.books.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserManagerEditParam implements Serializable {
    @ApiModelProperty(value = "用户管理id")
    private Long userManagerId;

    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;
}

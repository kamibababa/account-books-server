package com.zytd.account.books.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserManagerVO implements Serializable {
    @ApiModelProperty(value = "用户管理id")
    private Long userManagerId;

    @ApiModelProperty(value = "类型 1-客户 2-供货人", required = true)
    private Integer type;

    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;


}

package com.zytd.account.books.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserManagerAddParam implements Serializable {
    @ApiModelProperty(value = "类型 1-客户 2-供货人", required = true)
    private Integer type;

    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

//    @ApiModelProperty(value = "省编码")
//    private String provinceCode;
//
//    @ApiModelProperty(value = "省")
//    private String provinceName;
//
//    @ApiModelProperty(value = "市编码")
//    private String cityCode;
//
//    @ApiModelProperty(value = "市")
//    private String cityName;
//
//    @ApiModelProperty(value = "区编码")
//    private String districtCode;
//
//    @ApiModelProperty(value = "区")
//    private String districtName;
//
//    @ApiModelProperty(value = "街道编码")
//    private String streetCode;
//
//    @ApiModelProperty(value = "街道")
//    private String streetName;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;
}

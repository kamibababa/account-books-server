package com.zytd.account.books.param.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductTypeAddParam implements Serializable {
    @ApiModelProperty(value = "类型", required = true)
    private String type;
}

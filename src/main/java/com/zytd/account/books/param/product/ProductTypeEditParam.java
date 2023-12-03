package com.zytd.account.books.param.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductTypeEditParam implements Serializable {
    @ApiModelProperty(value = "产品类型id", required = true)
    private Long productTypeId;

    @ApiModelProperty(value = "类型", required = true)
    private String type;
}

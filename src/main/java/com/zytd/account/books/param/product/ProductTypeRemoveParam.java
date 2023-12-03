package com.zytd.account.books.param.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductTypeRemoveParam implements Serializable {
    @ApiModelProperty(value = "产品类型id", required = true)
    private Long productTypeId;
}

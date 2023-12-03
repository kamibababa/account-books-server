package com.zytd.account.books.vo.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductTypeVO implements Serializable {
    @ApiModelProperty(value = "产品类型id", required = true)
    private Long productTypeId;

    @ApiModelProperty(value = "类型", required = true)
    private String type;

    public ProductTypeVO(Long productTypeId, String type) {
        this.productTypeId = productTypeId;
        this.type = type;
    }
}

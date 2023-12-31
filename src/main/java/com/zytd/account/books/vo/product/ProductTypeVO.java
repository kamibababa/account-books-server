package com.zytd.account.books.vo.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class ProductTypeVO implements Serializable {
    @ApiModelProperty(value = "产品类型id", required = true)
    private Long productTypeId;

    @ApiModelProperty(value = "类型", required = true)
    private String type;

    @ApiModelProperty(value = "排序", required = true)
    private Integer serialNum;

}

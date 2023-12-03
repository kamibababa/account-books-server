package com.zytd.account.books.param.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductTypeSortParam implements Serializable {
    @ApiModelProperty(value = "排序列表")
    private List<ProductTypeSort> sortList;

    @Data
    public static class ProductTypeSort{
        @ApiModelProperty(value = "产品类型id")
        private Long productTypeId;

        @ApiModelProperty(value = "排序的序号 从小到大")
        private Integer serialNum;
    }
}

package com.zytd.account.books.service;

import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.product.ProductTypeAddParam;
import com.zytd.account.books.param.product.ProductTypeEditParam;
import com.zytd.account.books.param.product.ProductTypeRemoveParam;
import com.zytd.account.books.param.product.ProductTypeSortParam;
import com.zytd.account.books.vo.product.ProductTypeVO;

import java.util.List;

public interface ProductTypeExtendService {

    /**
     * 新增
     */
    ResultVO<Boolean> add(ProductTypeAddParam param);

    /**
     * 编辑
     */
    ResultVO<Boolean> edit(ProductTypeEditParam param);

    /**
     * 删除
     */
    ResultVO<Boolean> remove(ProductTypeRemoveParam param);

    /**
     * 排序
     */
    ResultVO<Boolean> sort(ProductTypeSortParam param);

    /**
     * 拉取列表
     */
    ResultVO<List<ProductTypeVO>> getList();
}

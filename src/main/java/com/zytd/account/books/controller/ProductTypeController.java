package com.zytd.account.books.controller;


import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.product.ProductTypeAddParam;
import com.zytd.account.books.param.product.ProductTypeEditParam;
import com.zytd.account.books.param.product.ProductTypeRemoveParam;
import com.zytd.account.books.param.product.ProductTypeSortParam;
import com.zytd.account.books.service.ProductTypeExtendService;
import com.zytd.account.books.vo.product.ProductTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品类型 前端控制器
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Api(value = "商品类型", tags = {"商品类型"})
@RestController
@RequestMapping("/productType")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class ProductTypeController {
    private final ProductTypeExtendService productTypeExtendService;

    @ApiOperation("新增")
    @PostMapping("add")
    public ResultVO<ProductTypeVO> add(@RequestBody ProductTypeAddParam param){
        return productTypeExtendService.add(param);
    }

    @ApiOperation("编辑")
    @PostMapping("edit")
    public ResultVO<Boolean> edit(@RequestBody ProductTypeEditParam param){
        return productTypeExtendService.edit(param);
    }

    @ApiOperation("删除")
    @PostMapping("remove")
    public ResultVO<Boolean> remove(@RequestBody ProductTypeRemoveParam param){
        return productTypeExtendService.remove(param);
    }

    @ApiOperation("排序")
    @PostMapping("sort")
    public ResultVO<Boolean> sort(@RequestBody ProductTypeSortParam param){
        return productTypeExtendService.sort(param);
    }

    @ApiOperation("拉取列表")
    @PostMapping("list")
    public ResultVO<List<ProductTypeVO>> getList(){
        return productTypeExtendService.getList();
    }
}


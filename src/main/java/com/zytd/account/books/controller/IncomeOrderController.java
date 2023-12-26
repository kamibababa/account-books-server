package com.zytd.account.books.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.income.*;
import com.zytd.account.books.param.product.ProductTypeAddParam;
import com.zytd.account.books.service.IncomeOrderExtendService;
import com.zytd.account.books.vo.income.IncomeOrderPageVO;
import com.zytd.account.books.vo.income.IncomeOrderVO;
import com.zytd.account.books.vo.income.IncomeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收入订单表 前端控制器
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Api(value = "收入订单", tags = {"收入订单"})
@RestController
@RequestMapping("/incomeOrder")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class IncomeOrderController {
    private final IncomeOrderExtendService incomeOrderExtendService;

    @ApiOperation("新增")
    @PostMapping("add")
    public ResultVO<Boolean> add(@RequestBody IncomeOrderAddParam param){
        return incomeOrderExtendService.add(param);
    }

    @ApiOperation("编辑")
    @PostMapping("update")
    public ResultVO<Boolean> update(@RequestBody IncomeOrderUpdateParam param){
        return incomeOrderExtendService.update(param);
    }

    @ApiOperation("详情")
    @PostMapping("detail")
    public ResultVO<IncomeOrderVO> detail(@RequestBody IncomeOrderRemoveParam param){
        return incomeOrderExtendService.detail(param);
    }

    @ApiOperation("删除")
    @PostMapping("remove")
    public ResultVO<Boolean> remove(@RequestBody IncomeOrderRemoveParam param){
        return incomeOrderExtendService.remove(param);
    }

    @ApiOperation("分页列表")
    @PostMapping("page")
    public ResultVO<IPage<IncomeOrderPageVO>> page(@RequestBody IncomeOrderPageParam param){
        return incomeOrderExtendService.page(param);
    }

    @ApiOperation("根据时间范围拉取收入信息")
    @PostMapping("getIncomeByTime")
    public ResultVO<IncomeVO> getIncomeByTime(@RequestBody IncomeGetParam param){
        return incomeOrderExtendService.getIncomeByTime(param);
    }
}


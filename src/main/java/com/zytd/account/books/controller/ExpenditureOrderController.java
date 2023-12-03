package com.zytd.account.books.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.expenditure.ExpenditureOrderAddParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderPageParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderRemoveParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderUpdateParam;
import com.zytd.account.books.param.income.IncomeOrderAddParam;
import com.zytd.account.books.param.income.IncomeOrderPageParam;
import com.zytd.account.books.param.income.IncomeOrderRemoveParam;
import com.zytd.account.books.param.income.IncomeOrderUpdateParam;
import com.zytd.account.books.service.ExpenditureOrderExtendService;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderPageVO;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderVO;
import com.zytd.account.books.vo.income.IncomeOrderPageVO;
import com.zytd.account.books.vo.income.IncomeOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 支出订单表 前端控制器
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Api(value = "支出订单", tags = {"支出订单"})
@RestController
@RequestMapping("/expenditureOrder")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class ExpenditureOrderController {
    private final ExpenditureOrderExtendService expenditureOrderExtendService;

    @ApiOperation("新增")
    @PostMapping("add")
    public ResultVO<Boolean> add(@RequestBody ExpenditureOrderAddParam param){
        return expenditureOrderExtendService.add(param);
    }

    @ApiOperation("编辑")
    @PostMapping("update")
    public ResultVO<Boolean> update(@RequestBody ExpenditureOrderUpdateParam param){
        return expenditureOrderExtendService.update(param);
    }

    @ApiOperation("详情")
    @PostMapping("detail")
    public ResultVO<ExpenditureOrderVO> detail(@RequestBody ExpenditureOrderRemoveParam param){
        return expenditureOrderExtendService.detail(param);
    }

    @ApiOperation("删除")
    @PostMapping("remove")
    public ResultVO<Boolean> remove(@RequestBody ExpenditureOrderRemoveParam param){
        return expenditureOrderExtendService.remove(param);
    }

    @ApiOperation("分页列表")
    @PostMapping("page")
    public ResultVO<IPage<ExpenditureOrderPageVO>> page(@RequestBody ExpenditureOrderPageParam param){
        return expenditureOrderExtendService.page(param);
    }
}


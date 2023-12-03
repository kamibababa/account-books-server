package com.zytd.account.books.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.income.*;
import com.zytd.account.books.vo.income.IncomeOrderPageVO;
import com.zytd.account.books.vo.income.IncomeOrderVO;
import com.zytd.account.books.vo.income.IncomeVO;

public interface IncomeOrderExtendService {
    /**
     * 新增
     */
    ResultVO<Boolean> add(IncomeOrderAddParam param);

    /**
     * 编辑
     */
    ResultVO<Boolean> update(IncomeOrderUpdateParam param);

    /**
     * 详情
     */
    ResultVO<IncomeOrderVO> detail(IncomeOrderRemoveParam param);

    /**
     * 删除
     */
    ResultVO<Boolean> remove(IncomeOrderRemoveParam param);

    /**
     * 分页列表
     */
    ResultVO<IPage<IncomeOrderPageVO>> page(IncomeOrderPageParam param);

    /**
     * 根据时间范围拉取收入信息
     */
    ResultVO<IncomeVO> getIncomeByTime(IncomeGetParam param);
}

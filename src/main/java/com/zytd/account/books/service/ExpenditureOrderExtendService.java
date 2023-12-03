package com.zytd.account.books.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.expenditure.ExpenditureOrderAddParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderPageParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderRemoveParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderUpdateParam;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderPageVO;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderVO;

public interface ExpenditureOrderExtendService {
    /**
     * 新增
     */
    ResultVO<Boolean> add(ExpenditureOrderAddParam param);

    /**
     * 编辑
     */
    ResultVO<Boolean> update(ExpenditureOrderUpdateParam param);

    /**
     * 详情
     */
    ResultVO<ExpenditureOrderVO> detail(ExpenditureOrderRemoveParam param);

    /**
     * 删除
     */
    ResultVO<Boolean> remove(ExpenditureOrderRemoveParam param);

    /**
     * 分页列表
     */
    ResultVO<IPage<ExpenditureOrderPageVO>> page(ExpenditureOrderPageParam param);
}

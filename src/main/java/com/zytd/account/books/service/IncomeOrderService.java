package com.zytd.account.books.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zytd.account.books.model.IncomeOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zytd.account.books.param.income.IncomeOrderPageParam;
import com.zytd.account.books.vo.income.IncomeOrderPageVO;
import com.zytd.account.books.vo.income.IncomeVO;

import java.math.BigDecimal;

/**
 * <p>
 * 收入订单表 服务类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
public interface IncomeOrderService extends IService<IncomeOrder> {

    /**
     * 根据会员id和用户id统计使用用户的个数
     */
    int countByUserId(Long userId, Long memberId);

    /**
     * 根据条件拉取分页列表
     */
    IPage<IncomeOrderPageVO> page(IncomeOrderPageParam param);

    /**
     * 根据开始时间和结束时间统计
     */
    IncomeVO getSumByTime(String startTime, String endTime);

    /**
     * 获取总收入
     */
    Integer getTotalMoney(IncomeOrderPageParam param);
}

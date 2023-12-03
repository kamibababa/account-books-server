package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.model.IncomeOrder;
import com.zytd.account.books.dao.IncomeOrderMapper;
import com.zytd.account.books.param.income.IncomeOrderPageParam;
import com.zytd.account.books.service.IncomeOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zytd.account.books.vo.income.IncomeOrderPageVO;
import com.zytd.account.books.vo.income.IncomeVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收入订单表 服务实现类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Service
public class IncomeOrderServiceImpl extends ServiceImpl<IncomeOrderMapper, IncomeOrder> implements IncomeOrderService {

    /**
     * 根据会员id和用户id统计使用用户的个数
     */
    @Override
    public int countByUserId(Long userId, Long memberId) {
        LambdaQueryWrapper<IncomeOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IncomeOrder::getMemberId, memberId).eq(IncomeOrder::getUserId, userId);
        return count(queryWrapper);
    }

    /**
     * 根据条件拉取分页列表
     */
    @Override
    public IPage<IncomeOrderPageVO> page(IncomeOrderPageParam param) {
        Page<IncomeOrderPageVO> page = new Page<>(param.getPageNum(), param.getPageSize());
        return baseMapper.page(page, param);
    }

    /**
     * 根据开始时间和结束时间统计
     */
    @Override
    public IncomeVO getSumByTime(String startTime, String endTime) {
        return baseMapper.getSumByTime(startTime, endTime);
    }
}

package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.enums.OrderTypeEnum;
import com.zytd.account.books.model.IncomeOrder;
import com.zytd.account.books.dao.IncomeOrderMapper;
import com.zytd.account.books.param.income.IncomeOrderPageParam;
import com.zytd.account.books.service.IncomeOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zytd.account.books.vo.income.IncomeOrderPageVO;
import com.zytd.account.books.vo.income.IncomeVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;

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
        IPage<IncomeOrderPageVO> iPage = baseMapper.page(page, param);
        if(!CollectionUtils.isEmpty(iPage.getRecords())){
            iPage.getRecords().forEach(vo -> vo.setTypeDesc(OrderTypeEnum.getMessage(vo.getType())));
        }
        return iPage;
    }

    /**
     * 根据开始时间和结束时间统计
     */
    @Override
    public IncomeVO getSumByTime(String startTime, String endTime) {
        return baseMapper.getSumByTime(startTime, endTime);
    }

    @Override
    public Integer getTotalMoney(IncomeOrderPageParam param) {
        param.setPageNum(1);
        param.setPageSize(Integer.MAX_VALUE);
        IPage<IncomeOrderPageVO> page = page(param);
        if(page.getRecords().isEmpty()){
            return 0;
        }
        Integer totalMoney = page.getRecords().stream().mapToInt(IncomeOrderPageVO::getTotalMoney).sum();
        return totalMoney;
    }
}

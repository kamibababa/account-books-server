package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.model.ExpenditureOrder;
import com.zytd.account.books.dao.ExpenditureOrderMapper;
import com.zytd.account.books.param.expenditure.ExpenditureOrderPageParam;
import com.zytd.account.books.service.ExpenditureOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderPageVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支出订单表 服务实现类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Service
public class ExpenditureOrderServiceImpl extends ServiceImpl<ExpenditureOrderMapper, ExpenditureOrder> implements ExpenditureOrderService {

    /**
     * 根据会员id和用户id统计使用用户的个数
     */
    @Override
    public int countByUserId(Long userId, Long memberId) {
        LambdaQueryWrapper<ExpenditureOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExpenditureOrder::getUserId, userId).eq(ExpenditureOrder::getMemberId, memberId);
        return count(queryWrapper);
    }

    /**
     * 根据产品类型统计
     */
    @Override
    public int countByProductTypeId(Long productTypeId) {
        LambdaQueryWrapper<ExpenditureOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExpenditureOrder::getProductTypeId, productTypeId);
        return count(queryWrapper);
    }

    /**
     * 分页列表
     */
    @Override
    public IPage<ExpenditureOrderPageVO> page(ExpenditureOrderPageParam param) {
        Page<ExpenditureOrderPageVO> page = new Page<>(param.getPageNum(), param.getPageSize());
        return baseMapper.page(page, param);
    }
}

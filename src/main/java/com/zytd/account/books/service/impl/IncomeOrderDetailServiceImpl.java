package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zytd.account.books.model.IncomeOrderDetail;
import com.zytd.account.books.dao.IncomeOrderDetailMapper;
import com.zytd.account.books.service.IncomeOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zytd.account.books.vo.income.IncomeVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收入订单详情表 服务实现类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Service
public class IncomeOrderDetailServiceImpl extends ServiceImpl<IncomeOrderDetailMapper, IncomeOrderDetail> implements IncomeOrderDetailService {

    /**
     * 根据产品类型统计
     */
    @Override
    public int countByProductTypeId(Long productTypeId) {
        LambdaQueryWrapper<IncomeOrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IncomeOrderDetail::getProductTypeId, productTypeId);
        return count(queryWrapper);
    }

    /**
     * 根据订单id删除
     */
    @Override
    public boolean removeByIncomeOrderId(Long incomeOrderId) {
        LambdaQueryWrapper<IncomeOrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IncomeOrderDetail::getIncomeOrderId, incomeOrderId);
        return remove(queryWrapper);
    }

    /**
     * 根据订单id查询
     */
    @Override
    public List<IncomeOrderDetail> queryByIncomeOrderId(Long incomeOrderId) {
        LambdaQueryWrapper<IncomeOrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IncomeOrderDetail::getIncomeOrderId, incomeOrderId);
        return list(queryWrapper);
    }
}

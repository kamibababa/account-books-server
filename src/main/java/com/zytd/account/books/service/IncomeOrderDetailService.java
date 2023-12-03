package com.zytd.account.books.service;

import com.zytd.account.books.model.IncomeOrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zytd.account.books.vo.income.IncomeVO;

import java.util.List;

/**
 * <p>
 * 收入订单详情表 服务类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
public interface IncomeOrderDetailService extends IService<IncomeOrderDetail> {
    /**
     * 根据产品类型统计
     */
    int countByProductTypeId(Long productTypeId);

    /**
     * 根据订单id删除
     */
    boolean removeByIncomeOrderId(Long incomeOrderId);

    /**
     * 根据订单id查询
     */
    List<IncomeOrderDetail> queryByIncomeOrderId(Long incomeOrderId);
}

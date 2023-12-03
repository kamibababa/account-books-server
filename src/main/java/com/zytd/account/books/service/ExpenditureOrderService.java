package com.zytd.account.books.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zytd.account.books.model.ExpenditureOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zytd.account.books.param.expenditure.ExpenditureOrderPageParam;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderPageVO;

/**
 * <p>
 * 支出订单表 服务类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
public interface ExpenditureOrderService extends IService<ExpenditureOrder> {

    /**
     * 根据会员id和用户id统计使用用户的个数
     */
    int countByUserId(Long userId, Long memberId);

    /**
     * 根据产品类型统计
     */
    int countByProductTypeId(Long productTypeId);

    /**
     * 分页列表
     */
    IPage<ExpenditureOrderPageVO> page(ExpenditureOrderPageParam param);
}

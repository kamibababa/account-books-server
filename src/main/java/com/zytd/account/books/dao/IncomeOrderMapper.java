package com.zytd.account.books.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.model.IncomeOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zytd.account.books.param.income.IncomeOrderPageParam;
import com.zytd.account.books.vo.income.IncomeOrderPageVO;
import com.zytd.account.books.vo.income.IncomeVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收入订单表 Mapper 接口
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
public interface IncomeOrderMapper extends BaseMapper<IncomeOrder> {
    /**
     * 根据条件拉取分页列表
     */
    IPage<IncomeOrderPageVO> page(@Param("page") Page<IncomeOrderPageVO> page, @Param("param") IncomeOrderPageParam param);

    /**
     * 根据开始时间和结束时间统计
     */
    IncomeVO getSumByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);
}

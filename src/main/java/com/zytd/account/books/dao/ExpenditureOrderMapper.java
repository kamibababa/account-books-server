package com.zytd.account.books.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.model.ExpenditureOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zytd.account.books.param.expenditure.ExpenditureOrderPageParam;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderPageVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 支出订单表 Mapper 接口
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
public interface ExpenditureOrderMapper extends BaseMapper<ExpenditureOrder> {

    /**
     * 分页列表
     */
    IPage<ExpenditureOrderPageVO> page(@Param("page") Page<ExpenditureOrderPageVO> page, @Param("param") ExpenditureOrderPageParam param);
}

package com.zytd.account.books.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.common.utils.ThreadLocalUtil;
import com.zytd.account.books.model.ExpenditureOrder;
import com.zytd.account.books.model.ProductType;
import com.zytd.account.books.model.UserManage;
import com.zytd.account.books.param.expenditure.ExpenditureOrderAddParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderPageParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderRemoveParam;
import com.zytd.account.books.param.expenditure.ExpenditureOrderUpdateParam;
import com.zytd.account.books.service.ExpenditureOrderExtendService;
import com.zytd.account.books.service.ExpenditureOrderService;
import com.zytd.account.books.service.ProductTypeService;
import com.zytd.account.books.service.UserManageService;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderPageVO;
import com.zytd.account.books.vo.expenditure.ExpenditureOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ExpenditureOrderExtendServiceImpl implements ExpenditureOrderExtendService {
    private final ExpenditureOrderService expenditureOrderService;
    private final UserManageService userManageService;
    private final ProductTypeService productTypeService;

    /**
     * 新增
     */
    @Override
    public ResultVO<Boolean> add(ExpenditureOrderAddParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        ExpenditureOrder order = new ExpenditureOrder();
        BeanUtils.copyProperties(param, order);
        order.setMemberId(memberId);
        order.setCreatTime(new Date());
        order.setUpdateTime(new Date());
        return expenditureOrderService.save(order) ? ResultVO.success() : ResultVO.error("新增失败");
    }

    /**
     * 编辑
     */
    @Override
    public ResultVO<Boolean> update(ExpenditureOrderUpdateParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        ExpenditureOrder expenditureOrder = expenditureOrderService.getById(param.getExpenditureOrderId());
        if(Objects.isNull(expenditureOrder))    return ResultVO.error("订单不存在");
        if(!expenditureOrder.getMemberId().equals(memberId))    return ResultVO.error("不能操作不属于自己的订单");
        ExpenditureOrder order = new ExpenditureOrder();
        BeanUtils.copyProperties(param, order);
        order.setId(param.getExpenditureOrderId());
        order.setType(expenditureOrder.getType());
        order.setUserId(expenditureOrder.getUserId());
        order.setMemberId(memberId);
        order.setUpdateTime(new Date());
        return expenditureOrderService.updateById(order) ? ResultVO.success() : ResultVO.error("编辑失败");
    }

    /**
     * 详情
     */
    @Override
    public ResultVO<ExpenditureOrderVO> detail(ExpenditureOrderRemoveParam param) {
        ExpenditureOrder expenditureOrder = expenditureOrderService.getById(param.getExpenditureOrderId());
        if(Objects.isNull(expenditureOrder))    return ResultVO.error("订单不存在");
        ExpenditureOrderVO vo = new ExpenditureOrderVO();
        BeanUtils.copyProperties(expenditureOrder, vo);
        UserManage userManage = userManageService.getById(expenditureOrder.getUserId());
        vo.setUserName(Objects.nonNull(userManage) ? userManage.getName() : "");
        ProductType productType = productTypeService.getById(expenditureOrder.getProductTypeId());
        vo.setProductType(Objects.nonNull(productType) ? productType.getType() : "");
        return ResultVO.success(vo);
    }

    /**
     * 删除
     */
    @Override
    public ResultVO<Boolean> remove(ExpenditureOrderRemoveParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        ExpenditureOrder expenditureOrder = expenditureOrderService.getById(param.getExpenditureOrderId());
        if(Objects.nonNull(expenditureOrder) && !expenditureOrder.getMemberId().equals(memberId))    return ResultVO.error("不能操作不属于自己的订单");
        return expenditureOrderService.removeById(param.getExpenditureOrderId()) ? ResultVO.success() : ResultVO.error("删除失败");
    }

    /**
     * 分页列表
     */
    @Override
    public ResultVO<IPage<ExpenditureOrderPageVO>> page(ExpenditureOrderPageParam param) {
        return null;
    }
}

package com.zytd.account.books.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.common.utils.ThreadLocalUtil;
import com.zytd.account.books.enums.OrderStatusEnum;
import com.zytd.account.books.enums.OrderTypeEnum;
import com.zytd.account.books.model.IncomeOrder;
import com.zytd.account.books.model.IncomeOrderDetail;
import com.zytd.account.books.model.ProductType;
import com.zytd.account.books.model.UserManage;
import com.zytd.account.books.param.income.*;
import com.zytd.account.books.service.*;
import com.zytd.account.books.vo.income.IncomeOrderDetailVO;
import com.zytd.account.books.vo.income.IncomeOrderPageVO;
import com.zytd.account.books.vo.income.IncomeOrderVO;
import com.zytd.account.books.vo.income.IncomeVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IncomeOrderExtendServiceImpl implements IncomeOrderExtendService {
    private final IncomeOrderService incomeOrderService;
    private final IncomeOrderDetailService incomeOrderDetailService;
    private final UserManageService userManageService;
    private final ProductTypeService productTypeService;

    /**
     * 新增
     */
    @Override
    @Transactional
    public ResultVO<Boolean> add(IncomeOrderAddParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        IncomeOrder incomeOrder = new IncomeOrder();
        BeanUtils.copyProperties(param, incomeOrder);
        incomeOrder.setMemberId(memberId);
        incomeOrder.setUnpaidMoney(param.getTotalMoney() - param.getPaidMoney());
        if(incomeOrder.getUnpaidMoney() > 0){
            incomeOrder.setStatus(OrderStatusEnum.uncleared.getCode());
        }
        if(StringUtils.isBlank(param.getDay())){
            incomeOrder.setDay(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
        incomeOrder.setCreatTime(new Date());
        incomeOrder.setUpdateTime(new Date());
        incomeOrderService.save(incomeOrder);
        List<IncomeOrderDetail> list = new ArrayList<>();
        param.getDetails().forEach(incomeOrderDetailAddParam -> {
            IncomeOrderDetail orderDetail = new IncomeOrderDetail();
            orderDetail.setIncomeOrderId(incomeOrder.getId());
            orderDetail.setProductTypeId(incomeOrderDetailAddParam.getProductTypeId());
            orderDetail.setWeight(incomeOrderDetailAddParam.getWeight());
            orderDetail.setMoney(incomeOrderDetailAddParam.getMoney());
            orderDetail.setCreatTime(new Date());
            orderDetail.setUpdateTime(new Date());
            list.add(orderDetail);
        });
        incomeOrderDetailService.saveBatch(list);
        return ResultVO.success();
    }

    /**
     * 编辑
     */
    @Override
    @Transactional
    public ResultVO<Boolean> update(IncomeOrderUpdateParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        IncomeOrder incomeOrder = incomeOrderService.getById(param.getIncomeOrderId());
        if(Objects.isNull(incomeOrder)) return ResultVO.error("未找到订单");
        if(!incomeOrder.getMemberId().equals(memberId)) return ResultVO.error("无权修改别人的订单");
        BeanUtils.copyProperties(param, incomeOrder);
        incomeOrder.setUnpaidMoney(param.getTotalMoney() - param.getPaidMoney());
        if(incomeOrder.getUnpaidMoney() > 0){
            incomeOrder.setStatus(OrderStatusEnum.uncleared.getCode());
        }
        incomeOrderService.updateById(incomeOrder);
        incomeOrderDetailService.removeByIncomeOrderId(param.getIncomeOrderId());
        List<IncomeOrderDetail> list = new ArrayList<>();
        param.getDetails().forEach(incomeOrderDetailAddParam -> {
            IncomeOrderDetail orderDetail = new IncomeOrderDetail();
            orderDetail.setIncomeOrderId(incomeOrder.getId());
            orderDetail.setProductTypeId(incomeOrderDetailAddParam.getProductTypeId());
            orderDetail.setWeight(incomeOrderDetailAddParam.getWeight());
            orderDetail.setMoney(incomeOrderDetailAddParam.getMoney());
            orderDetail.setCreatTime(new Date());
            orderDetail.setUpdateTime(new Date());
            list.add(orderDetail);
        });
        incomeOrderDetailService.saveBatch(list);
        return ResultVO.success();
    }

    /**
     * 详情
     */
    @Override
    public ResultVO<IncomeOrderVO> detail(IncomeOrderRemoveParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        IncomeOrder incomeOrder = incomeOrderService.getById(param.getIncomeOrderId());
        if(Objects.isNull(incomeOrder)) return ResultVO.error("订单不存在");
        IncomeOrderVO vo = new IncomeOrderVO();
        BeanUtils.copyProperties(incomeOrder, vo);
        vo.setIncomeOrderId(incomeOrder.getId());
        vo.setType(incomeOrder.getType());
        vo.setTypeDesc(OrderTypeEnum.getMessage(vo.getType()));
        if(Objects.nonNull(incomeOrder.getUserId())) {
            UserManage userManage = userManageService.getById(incomeOrder.getUserId());
            vo.setUsername(Objects.nonNull(userManage) ? userManage.getName() : StringPool.EMPTY);
            vo.setAddress(Objects.nonNull(userManage) ? userManage.getAddress() : StringPool.EMPTY);
        }
        List<IncomeOrderDetailVO> orderDetails = new ArrayList<>();
        List<IncomeOrderDetail> detailList = incomeOrderDetailService.queryByIncomeOrderId(param.getIncomeOrderId());
        List<ProductType> typeList = productTypeService.queryByMemberId(memberId);
        detailList.forEach(incomeOrderDetail -> {
            IncomeOrderDetailVO detailVO = new IncomeOrderDetailVO();
            List<ProductType> collect = typeList.stream().filter(it -> it.getId().equals(incomeOrderDetail.getProductTypeId())).collect(Collectors.toList());
            detailVO.setProductTypeId(CollectionUtils.isNotEmpty(collect) ? collect.get(0).getId() : null);
            detailVO.setProductTypeName(CollectionUtils.isNotEmpty(collect) ? collect.get(0).getType() : StringPool.EMPTY);
            detailVO.setWeight(incomeOrderDetail.getWeight());
            detailVO.setMoney(incomeOrderDetail.getMoney());
            orderDetails.add(detailVO);
        });
        vo.setDetails(orderDetails);
        return ResultVO.success(vo);
    }

    /**
     * 删除
     */
    @Override
    @Transactional
    public ResultVO<Boolean> remove(IncomeOrderRemoveParam param) {
        incomeOrderService.removeById(param.getIncomeOrderId());
        incomeOrderDetailService.removeByIncomeOrderId(param.getIncomeOrderId());
        return ResultVO.success();
    }

    /**
     * 分页列表
     */
    @Override
    public ResultVO<IPage<IncomeOrderPageVO>> page(IncomeOrderPageParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        param.setMemberId(memberId);
        return ResultVO.success(incomeOrderService.page(param));
    }

    /**
     * 根据时间范围拉取收入信息
     */
    @Override
    public ResultVO<IncomeVO> getIncomeByTime(IncomeGetParam param) {
        IncomeVO vo = incomeOrderService.getSumByTime(param.getStartTime(), param.getEndTime());
        return ResultVO.success(vo);
    }
}

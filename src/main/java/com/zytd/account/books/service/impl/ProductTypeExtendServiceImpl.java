package com.zytd.account.books.service.impl;

import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.common.utils.ThreadLocalUtil;
import com.zytd.account.books.model.ProductType;
import com.zytd.account.books.param.product.ProductTypeAddParam;
import com.zytd.account.books.param.product.ProductTypeEditParam;
import com.zytd.account.books.param.product.ProductTypeRemoveParam;
import com.zytd.account.books.param.product.ProductTypeSortParam;
import com.zytd.account.books.service.ExpenditureOrderService;
import com.zytd.account.books.service.IncomeOrderDetailService;
import com.zytd.account.books.service.ProductTypeExtendService;
import com.zytd.account.books.service.ProductTypeService;
import com.zytd.account.books.vo.product.ProductTypeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductTypeExtendServiceImpl implements ProductTypeExtendService {
    private final ProductTypeService productTypeService;
    private final IncomeOrderDetailService incomeOrderDetailService;
    private final ExpenditureOrderService expenditureOrderService;

    /**
     * 新增
     */
    @Override
    public ResultVO<Boolean> add(ProductTypeAddParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        //根据名称去重
        ProductType productType = productTypeService.selectByMemberIdAndType(memberId, param.getType());
        if(Objects.nonNull(productType))    return ResultVO.error("类型名称不可重复");
        productType = new ProductType();
        productType.setMemberId(memberId);
        productType.setType(param.getType());
        productType.setCreatTime(new Date());
        productType.setUpdateTime(new Date());
        return productTypeService.save(productType) ? ResultVO.success() : ResultVO.error("新增失败");
    }

    /**
     * 编辑
     */
    @Override
    public ResultVO<Boolean> edit(ProductTypeEditParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        ProductType byId = productTypeService.getById(param.getProductTypeId());
        if(Objects.isNull(byId))    return ResultVO.error("修改的数据不存在");
        //根据名称去重
        ProductType productType = productTypeService.selectByMemberIdAndType(memberId, param.getType());
        if(Objects.nonNull(productType) && !productType.getId().equals(param.getProductTypeId()))    return ResultVO.error("类型名称不可重复");
        byId.setType(param.getType());
        byId.setUpdateTime(new Date());
        return productTypeService.updateById(byId) ? ResultVO.success() : ResultVO.error("编辑失败");
    }

    /**
     * 删除
     */
    @Override
    public ResultVO<Boolean> remove(ProductTypeRemoveParam param) {
        ProductType byId = productTypeService.getById(param.getProductTypeId());
        if(Objects.isNull(byId))    return ResultVO.success();
        //若没有被关联使用可以被删除
        int count = incomeOrderDetailService.countByProductTypeId(param.getProductTypeId());
        if(count > 0)   return ResultVO.error("已被收入账单使用，无法删除");
        int count1 = expenditureOrderService.countByProductTypeId(param.getProductTypeId());
        if(count1 > 0)  return ResultVO.error("已被支出账单使用，无法删除");
        return productTypeService.removeById(param.getProductTypeId()) ? ResultVO.success() : ResultVO.error("删除失败");
    }

    /**
     * 排序
     */
    @Override
    public ResultVO<Boolean> sort(ProductTypeSortParam param) {
        List<ProductType> list = new ArrayList<>();
        param.getSortList().forEach(it -> {
            ProductType productType = new ProductType();
            productType.setId(it.getProductTypeId());
            productType.setSerialNum(it.getSerialNum());
            list.add(productType);
        });
        return productTypeService.updateBatchById(list) ? ResultVO.success() : ResultVO.error("排序失败");
    }

    /**
     * 拉取列表
     */
    @Override
    public ResultVO<List<ProductTypeVO>> getList() {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        List<ProductTypeVO> voList = new ArrayList<>();
        List<ProductType> list = productTypeService.queryByMemberId(memberId);
        list.forEach(productType -> voList.add(new ProductTypeVO(productType.getId(), productType.getType())));
        return ResultVO.success(voList);
    }
}

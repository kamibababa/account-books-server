package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zytd.account.books.model.ProductType;
import com.zytd.account.books.dao.ProductTypeMapper;
import com.zytd.account.books.service.ProductTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品类型 服务实现类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {

    /**
     * 根据会员id和类型查询
     */
    @Override
    public ProductType selectByMemberIdAndType(Long memberId, String type) {
        LambdaQueryWrapper<ProductType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductType::getMemberId, memberId).eq(ProductType::getType, type);
        return getOne(queryWrapper);
    }

    /**
     * 根据用户id查询列表
     *
     * @param memberId
     */
    @Override
    public List<ProductType> queryByMemberId(Long memberId) {
        LambdaQueryWrapper<ProductType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductType::getMemberId, memberId).orderByAsc(ProductType::getSerialNum);
        return list(queryWrapper);
    }
}

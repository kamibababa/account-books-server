package com.zytd.account.books.service;

import com.zytd.account.books.model.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品类型 服务类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
public interface ProductTypeService extends IService<ProductType> {
    /**
     * 根据会员id和类型查询
     */
    ProductType selectByMemberIdAndType(Long memberId, String type);

    /**
     * 根据用户id查询列表
     */
    List<ProductType> queryByMemberId(Long memberId);
}

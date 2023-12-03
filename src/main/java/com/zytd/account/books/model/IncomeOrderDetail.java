package com.zytd.account.books.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 收入订单详情表
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("income_order_detail")
public class IncomeOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 收入订单id
     */
    private Long incomeOrderId;

    /**
     * 商品类型id
     */
    private Long productTypeId;

    /**
     * 重量，单位斤
     */
    private Integer weight;

    /**
     * 货品金额，单位元
     */
    private Integer money;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除状态 0-正常 1-删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}

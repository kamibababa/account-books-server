package com.zytd.account.books.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 支出订单表
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("expenditure_order")
public class ExpenditureOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 类型 1-进货 2-其他
     */
    private Integer type;

    /**
     * 时间
     */
    private String day;

    /**
     * 产品类型id
     */
    private Long productTypeId;

    /**
     * 重量，单位斤
     */
    private Integer weight;

    /**
     * 单价，单位元
     */
    private BigDecimal unitPrice;

    /**
     * 货品金额，单位元
     */
    private Integer money;

    /**
     * 运输费用，单位元
     */
    private Integer transportMoney;

    /**
     * 其他费用，单位元
     */
    private Integer otherMoney;

    /**
     * 总金额，单位元
     */
    private Integer totalMoney;

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

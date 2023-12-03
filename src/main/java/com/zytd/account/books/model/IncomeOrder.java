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
 * 收入订单表
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("income_order")
public class IncomeOrder implements Serializable {

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
     * 类型 1-批发 2-零售
     */
    private Integer type;

    /**
     * 时间
     */
    private String day;

    /**
     * 总金额，单位元
     */
    private Integer totalMoney;

    /**
     * 已付金额，单位元
     */
    private Integer paidMoney;

    /**
     * 未付金额，单位元
     */
    private Integer unpaidMoney;

    /**
     * 状态 1-已结清 2-未结清
     */
    private Integer status;

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

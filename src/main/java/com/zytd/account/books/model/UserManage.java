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
 * 用户管理
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_manage")
public class UserManage implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 市
     */
    private String cityName;

    /**
     * 区编码
     */
    private String districtCode;

    /**
     * 区
     */
    private String districtName;

    /**
     * 街道编码
     */
    private String streetCode;

    /**
     * 街道
     */
    private String streetName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序的序号 从小到大
     */
    private Integer serialNum;

    /**
     * 类型 1-客户 2-供货人
     */
    private Integer type;

    /**
     * 删除状态 0-正常 1-删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 启用禁用
     */
    private Integer enabled;


}

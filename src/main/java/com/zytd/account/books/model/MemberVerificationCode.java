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
 * 验证码
 * </p>
 *
 * @author wl
 * @since 2023-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member_verification_code")
public class MemberVerificationCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 验证码类型 1-注册/登录 2-忘记密码
     */
    private Integer type;

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

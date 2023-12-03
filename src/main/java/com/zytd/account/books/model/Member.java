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
 * 会员
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称，默认手机号码，前三后四中间*
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否禁用 1-是 2-否
     */
    private Integer isForbidden;

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

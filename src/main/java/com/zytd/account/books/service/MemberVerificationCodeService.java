package com.zytd.account.books.service;

import com.zytd.account.books.model.MemberVerificationCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 验证码 服务类
 * </p>
 *
 * @author wl
 * @since 2023-11-11
 */
public interface MemberVerificationCodeService extends IService<MemberVerificationCode> {

    /**
     * 根据手机号和类型查询
     * @param phone
     * @param type
     * @return
     */
    MemberVerificationCode selectByPhoneAndType(String phone, Integer type);
}

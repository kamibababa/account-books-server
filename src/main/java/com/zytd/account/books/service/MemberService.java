package com.zytd.account.books.service;

import com.zytd.account.books.model.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员 服务类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
public interface MemberService extends IService<Member> {
    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    Member selectByPhone(String phone);
}

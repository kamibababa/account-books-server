package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zytd.account.books.model.Member;
import com.zytd.account.books.dao.MemberMapper;
import com.zytd.account.books.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    /**
     * 根据手机号查询
     *
     * @param phone
     * @return
     */
    @Override
    public Member selectByPhone(String phone) {
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getPhone, phone);
        return getOne(queryWrapper);
    }
}

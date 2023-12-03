package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zytd.account.books.model.MemberVerificationCode;
import com.zytd.account.books.dao.MemberVerificationCodeMapper;
import com.zytd.account.books.service.MemberVerificationCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 验证码 服务实现类
 * </p>
 *
 * @author wl
 * @since 2023-11-11
 */
@Service
public class MemberVerificationCodeServiceImpl extends ServiceImpl<MemberVerificationCodeMapper, MemberVerificationCode> implements MemberVerificationCodeService {


    /**
     * 根据手机号和类型查询
     *
     * @param phone
     * @param type
     * @return
     */
    @Override
    public MemberVerificationCode selectByPhoneAndType(String phone, Integer type) {
        LambdaQueryWrapper<MemberVerificationCode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MemberVerificationCode::getPhone, phone).eq(MemberVerificationCode::getType, type);
        return getOne(queryWrapper);
    }
}

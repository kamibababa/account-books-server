package com.zytd.account.books.service;

import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.member.LoginByPasswordParam;
import com.zytd.account.books.param.member.LoginParam;
import com.zytd.account.books.param.member.RegisterParam;
import com.zytd.account.books.vo.member.MemberVO;
import org.springframework.web.bind.annotation.RequestBody;

public interface MemberExtendService {
    /**
     * 注册并登录
     */
    ResultVO<MemberVO> register(@RequestBody RegisterParam param);

    /**
     * 密码登录
     */
    ResultVO<MemberVO> loginByPassword(@RequestBody LoginByPasswordParam param);

    /**
     * 验证码登录
     */
    ResultVO<MemberVO> loginByVerifyCode(@RequestBody LoginParam param);

    /**
     * 查看会员详情
     */
    ResultVO<MemberVO> detail();

    /**
     * 退出登录
     */
    ResultVO<Integer> logout();
}

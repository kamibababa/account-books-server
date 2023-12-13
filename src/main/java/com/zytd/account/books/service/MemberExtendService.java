package com.zytd.account.books.service;

import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.model.VerifyCode;
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

//    /**
//     * 验证码登录
//     */
//    ResultVO<MemberVO> loginByVerifyCode(@RequestBody LoginParam param);

    /**
     * 获取验证码
     */
    ResultVO<String> getVerifyCode(String phone);

    /**
     * 查看会员详情
     */
    ResultVO<MemberVO> detail();

    /**
     * 退出登录
     */
    ResultVO<Integer> logout();

    /**
     * 保存验证码
     */
    void save(VerifyCode verifyCode);

    /**
     * 获取验证码
     * @param username
     * @param type
     * @return
     */
    String getVerifyCode(String username, String type);
}

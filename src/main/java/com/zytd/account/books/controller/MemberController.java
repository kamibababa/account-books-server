package com.zytd.account.books.controller;


import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.member.LoginParam;
import com.zytd.account.books.service.MemberExtendService;
import com.zytd.account.books.vo.member.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员 前端控制器
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Api(value = "会员管理", tags = {"会员管理"})
@RestController
@RequestMapping("/member")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MemberController {
    private final MemberExtendService memberExtendService;

//    @ApiOperation("注册并登录")
//    @PostMapping("register")
//    public ResultVO<MemberVO> register(@RequestBody RegisterParam param){
//        return memberExtendService.register(param);
//    }
//
//    @ApiOperation("密码登录")
//    @PostMapping("loginByPassword")
//    public ResultVO<MemberVO> loginByPassword(@RequestBody LoginByPasswordParam param){
//        return memberExtendService.loginByPassword(param);
//    }

    @ApiOperation("验证码登录")
    @PostMapping("loginByVerifyCode")
    public ResultVO<MemberVO> loginByVerifyCode(@RequestBody LoginParam param){
        return memberExtendService.loginByVerifyCode(param);
    }

    @ApiOperation("查看会员详情")
    @PostMapping("detail")
    public ResultVO<MemberVO> detail(){
        return memberExtendService.detail();
    }

    @ApiOperation("退出登录")
    @PostMapping("logout")
    public ResultVO<Integer> logout(){
        return memberExtendService.logout();
    }

}


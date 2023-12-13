package com.zytd.account.books.controller;


import com.google.code.kaptcha.Producer;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.enums.VerificationCodeTypeEnum;
import com.zytd.account.books.model.VerifyCode;
import com.zytd.account.books.param.member.LoginParam;
import com.zytd.account.books.service.MemberExtendService;
import com.zytd.account.books.vo.member.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    private final Producer producer;

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

    @ApiOperation("获取短信验证码")
    @PostMapping("getVerifyCode")
    public ResultVO<String> getVerifyCode(@RequestBody LoginParam param){
        // 实际是生成验证码
        ResultVO<String> verifyCode = memberExtendService.getVerifyCode(param.getPhone());
        return verifyCode;
    }

    @ApiOperation("获取图片验证码")
    @PostMapping("getImageCode")
    public ResultVO<String> getImageCode(@RequestBody LoginParam param) throws IOException {
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", out);
        byte[] bytes = out.toByteArray();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String imageBase64 = base64Encoder.encodeBuffer(bytes).trim();
        imageBase64 = imageBase64.replaceAll("\n", "").replaceAll("\r", "");
        // 保存验证码
        VerifyCode code = new VerifyCode(param.getPhone(), text.toUpperCase(), VerificationCodeTypeEnum.IMAGE.getType(),90);
        memberExtendService.save(code);
        return ResultVO.success("data:image/jpg;base64," + imageBase64);
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


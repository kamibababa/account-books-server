package com.zytd.account.books.service.impl;

import com.zytd.account.books.common.base.*;
import com.zytd.account.books.common.constants.CommonConstants;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import com.zytd.account.books.common.utils.RedisUtil;
import com.zytd.account.books.common.utils.ThreadLocalUtil;
import com.zytd.account.books.model.Member;
import com.zytd.account.books.param.member.LoginByPasswordParam;
import com.zytd.account.books.param.member.LoginParam;
import com.zytd.account.books.param.member.RegisterParam;
import com.zytd.account.books.service.MemberExtendService;
import com.zytd.account.books.service.MemberService;
import com.zytd.account.books.vo.member.MemberVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MemberExtendServiceImpl implements MemberExtendService {
    private final MemberService memberService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final RedisUtil redisUtil;

    /**
     * 注册并登录
     */
    @Override
    public synchronized ResultVO<MemberVO> register(RegisterParam param) {
        //TODO redis、ThreadLocal/拦截器、jwtToken
        //判断是否已注册
        Member member = memberService.selectByPhone(param.getPhone());
        if(Objects.nonNull(member))
            return ResultVO.error("您已注册，请直接登录");
        //判断验证码是否正确

        //生成会员
        member = new Member();
        member.setPhone(param.getPhone());
        member.setPassword(param.getPassword());
        //TODO
        member.setNickName("");
        member.setAvatar(Constants.DEFAULT_AVATAR);
        member.setCreatTime(new Date());
        memberService.save(member);
        //生成token
        TokenVO tokenVO = jwtTokenUtil.generateToken(new MemberInfoVO(member.getId()));
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(member, memberVO);
        memberVO.setToken(tokenVO.getToken());
        return ResultVO.success(memberVO);
    }

    /**
     * 密码登录
     */
    @Override
    public ResultVO<MemberVO> loginByPassword(LoginByPasswordParam param) {
        //判断密码是否正确
        //生成token
        return null;
    }

    /**
     * 验证码登录
     */
    @Override
    public ResultVO<MemberVO> loginByVerifyCode(LoginParam param) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(param.getPhone(), param.getVerifyCode());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if(Objects.isNull(authenticate))    return ResultVO.error("登录失败");
        ////获取用户信息
        LoginUserDetails user = (LoginUserDetails) authenticate.getPrincipal();
        //生成token
        TokenVO tokenVO = jwtTokenUtil.generateToken(new MemberInfoVO(user.getMember().getId()));
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(user.getMember(), memberVO);
        memberVO.setMemberId(user.getMember().getId());
        memberVO.setToken(tokenVO.getToken());
        redisUtil.setValue(CommonConstants.token_prefix + memberVO.getMemberId(), memberVO.getToken());
        return ResultVO.success(memberVO);
    }

    /**
     * 查看会员详情
     */
    @Override
    public ResultVO<MemberVO> detail() {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        Member member = memberService.getById(memberId);
        MemberVO vo = new MemberVO();
        BeanUtils.copyProperties(member, vo);
        vo.setMemberId(memberId);
        return ResultVO.success(vo);
    }

    /**
     * 退出登录
     */
    @Override
    public ResultVO<Integer> logout() {
        //移除token
        redisUtil.deleteValue(CommonConstants.token_prefix + ThreadLocalUtil.MEMBER_ID_HOLDER.get());
        return ResultVO.success();
    }
}



package com.zytd.account.books.config.security;

import com.zytd.account.books.common.base.LoginUserDetails;
import com.zytd.account.books.common.base.MemberInfoVO;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.common.base.TokenVO;
import com.zytd.account.books.common.constants.CommonConstants;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import com.zytd.account.books.vo.member.MemberVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * An {@link AuthenticationProvider} implementation that retrieves user details from a
 * {@link UserDetailsService}.
 *
 * @author Ben Alex
 * @author Rob Winch
 */
public class SmsVerifyCodeAuthenticationProvider implements AuthenticationProvider  {
	// ~ Static fields/initializers
	// =====================================================================================

	/**
	 * The plaintext password used to perform
	 * PasswordEncoder#matches(CharSequence, String)}  on when the user is
	 * not found to avoid SEC-2056.
	 */
	private static final String USER_NOT_FOUND_VERIFY_CODE = "userNotFoundVerifyCode";

	private UserDetailsService userDetailsService;


	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}
		////获取用户信息
		SmsVerifyCodeAuthenticationToken smsVerifyCodeAuthenticationToken = (SmsVerifyCodeAuthenticationToken) authentication;
		// 验证
		doAuthenticate(smsVerifyCodeAuthenticationToken);
		return authentication;
	}

	private void doAuthenticate(SmsVerifyCodeAuthenticationToken token) {
		LoginUserDetails userDetails = (LoginUserDetails)getUserDetailsService().loadUserByUsername(token.getPrincipal().toString());
		if(userDetails == null){
			throw new UsernameNotFoundException("用户名或密码错误");
		}
		String verifyCode = userDetails.getPassword();
		if(!token.getCredentials().toString().equals(verifyCode)){
			throw new BadCredentialsException(USER_NOT_FOUND_VERIFY_CODE);
		}
		// 设置用户信息用于后续操作
		token.setDetails(userDetails);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsVerifyCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
}

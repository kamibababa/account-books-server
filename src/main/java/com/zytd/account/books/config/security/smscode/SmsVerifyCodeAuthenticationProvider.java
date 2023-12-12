

package com.zytd.account.books.config.security.smscode;

import com.zytd.account.books.common.base.LoginUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

	private static final String USE_NOT_FOUND = "手机号错误";
	private static final String USER_NOT_FOUND_VERIFY_CODE = "验证码错误";

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
			throw new UsernameNotFoundException(USE_NOT_FOUND);
		}
		String verifyCode = userDetails.getVerifyCode();
		if(StringUtils.isBlank(verifyCode) || !token.getCredentials().toString().equals(verifyCode)){
			throw new BadCredentialsException(USER_NOT_FOUND_VERIFY_CODE);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsVerifyCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
}

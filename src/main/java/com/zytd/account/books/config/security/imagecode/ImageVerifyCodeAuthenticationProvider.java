/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zytd.account.books.config.security.imagecode;

import com.zytd.account.books.common.base.LoginUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 图片验证码：用于用户名、密码、验证码登录
 */
public class ImageVerifyCodeAuthenticationProvider extends DaoAuthenticationProvider {
	// ~ Static fields/initializers
	// =====================================================================================

	/**
	 * The plaintext password used to perform
	 * PasswordEncoder#matches(CharSequence, String)}  on when the user is
	 * not found to avoid SEC-2056.
	 */
	private static final String USER_NOT_FOUND = "用户名或密码错误";
	private static final String USER_NOT_FOUND_VERIFY_CODE = "验证码错误";

	public ImageVerifyCodeAuthenticationProvider() {
	}


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}
		// 获取用户信息
		ImageVerifyCodeAuthenticationToken imageVerifyCodeAuthenticationToken = (ImageVerifyCodeAuthenticationToken) authentication;
		// 验证
		doAuthenticate(imageVerifyCodeAuthenticationToken);
		// 为null标识验证通过，由parentProvider：用户名和密码继续验证
		return super.authenticate(authentication);
	}

	private void doAuthenticate(ImageVerifyCodeAuthenticationToken token) {
		LoginUserDetails userDetails = (LoginUserDetails)getUserDetailsService().loadUserByUsername(token.getPrincipal().toString());
		if(userDetails == null){
			throw new UsernameNotFoundException(USER_NOT_FOUND);
		}
		String verifyCode = userDetails.getVerifyCode();
		if(StringUtils.isBlank(verifyCode) || !token.getVerifyCode().toUpperCase().equals(verifyCode)){
			throw new CredentialsExpiredException(USER_NOT_FOUND_VERIFY_CODE);
		}
	}

	/**
	 * 用户名和密码登录时，先验证验证码是否正确
	 *
	 * @param authentication
	 * @return
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return ImageVerifyCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
}

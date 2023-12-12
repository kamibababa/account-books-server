
package com.zytd.account.books.config.security.imagecode;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * An {@link org.springframework.security.core.Authentication} implementation that is
 * designed for simple presentation of a username and password.
 * <p>
 * The <code>principal</code> and <code>credentials</code> should be set with an
 * <code>Object</code> that provides the respective property via its
 * <code>Object.toString()</code> method. The simplest such <code>Object</code> to use is
 * <code>String</code>.
 *
 * @author Ben Alex
 */
public class ImageVerifyCodeAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String verifyCode;

	public ImageVerifyCodeAuthenticationToken(Object principal, Object credentials, String verifyCode) {
		super(principal, credentials);
		this.verifyCode = verifyCode;
	}

	public ImageVerifyCodeAuthenticationToken(Object principal, Object credentials, String verifyCode, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.verifyCode = verifyCode;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}

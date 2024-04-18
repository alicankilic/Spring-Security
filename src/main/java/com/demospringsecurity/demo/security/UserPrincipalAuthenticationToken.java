package com.demospringsecurity.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import javax.security.auth.Subject;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken
{

	private final UserPrincipal principal;


	@Autowired
	public UserPrincipalAuthenticationToken(UserPrincipal principal)
	{
		super(principal.getAuthorities());
		this.principal = principal;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials()
	{
		return null;
	}

	@Override
	public UserPrincipal getPrincipal()
	{
		return principal;
	}

	@Override
	public boolean implies(Subject subject)
	{
		return super.implies(subject);
	}
}

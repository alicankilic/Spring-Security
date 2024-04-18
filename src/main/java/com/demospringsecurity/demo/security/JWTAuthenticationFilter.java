package com.demospringsecurity.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter
{

	private JwtDecoder jwtDecoder;

	private JwtToPrincipal jwtToPrincipal;


	@Autowired
	public JWTAuthenticationFilter(JwtDecoder jwtDecoder, JwtToPrincipal jwtToPrincipal)
	{
		this.jwtDecoder = jwtDecoder;
		this.jwtToPrincipal = jwtToPrincipal;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		extractTokenFromRequest(request).map(s -> jwtDecoder.decode(s))
				.map(x -> jwtToPrincipal.convert(x))
				.map(k -> new UserPrincipalAuthenticationToken(k))
				.ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));

		filterChain.doFilter(request, response);
	}

	private Optional<String> extractTokenFromRequest(HttpServletRequest request)
	{
		String token = request.getHeader("Authorization");
		if (StringUtils.hasText(token) && token.startsWith("Bearer "))
		{
			return Optional.of(token.substring(7));
		}

		return Optional.empty();
	}
}

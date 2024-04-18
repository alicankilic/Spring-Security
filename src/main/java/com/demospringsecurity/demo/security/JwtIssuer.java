package com.demospringsecurity.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class JwtIssuer
{

	private JwtProperties jwtProperties;

	@Autowired
	public JwtIssuer(JwtProperties jwtProperties)
	{
		this.jwtProperties = jwtProperties;
	}

	public String issue(long userId, String email, List<String> roles)
	{
		return JWT.create()
				.withSubject(String.valueOf(userId))
				.withExpiresAt(Date.from(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS))))
				.withClaim("e", email)
				.withClaim("a", roles)
				.sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
	}


}

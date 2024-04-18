package com.demospringsecurity.demo.controller;


import com.demospringsecurity.demo.modal.LoginRequest;
import com.demospringsecurity.demo.modal.LoginResp;
import com.demospringsecurity.demo.security.JwtIssuer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController

public class AuthController
{

	private JwtIssuer jwtIssuer;


	@Autowired
	public AuthController(JwtIssuer jwtIssuer)
	{
		this.jwtIssuer = jwtIssuer;
	}

	@PostMapping("/auth/login")
	public LoginResp login(@RequestBody @Validated LoginRequest request)
	{
		String token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
		return LoginResp.builder().accessToken(token).build();
	}


}

package com.demospringsecurity.demo.modal;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResp
{
	private final String accessToken;
}

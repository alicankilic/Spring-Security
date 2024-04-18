package com.demospringsecurity.demo.security;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@ConfigurationProperties("security.jwt")
public class JwtProperties
{
	private String secretKey;

}

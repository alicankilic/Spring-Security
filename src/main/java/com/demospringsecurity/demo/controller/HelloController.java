package com.demospringsecurity.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController
{
	@GetMapping("/")
	public String greeting()
	{
		return "hello world";
	}

	@GetMapping("/secured")
	public String secured()
	{
		return "if you are seeing this u are logged in";
	}


}

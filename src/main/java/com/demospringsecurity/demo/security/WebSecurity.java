package com.demospringsecurity.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity
{

	private JWTAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	public WebSecurity(JWTAuthenticationFilter jwtAuthenticationFilter)
	{
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	public SecurityFilterChain appSec(HttpSecurity http) throws Exception
	{

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


		http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
				.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
				.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable())
				.securityMatcher("/**")
				.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
						.requestMatchers("/").permitAll()
						.requestMatchers("/auth/login").permitAll()
						.anyRequest().authenticated()
				);


		//TODO YUKARİDAKİ O SLASH ** SLASHTAN SONRAKI BUTUN RESOURCELAR ICIN UYGULA DEMEK /API/** OLSAYDI /API DAN SONRAKI TUM ENDPOINTLER ICIN

		return http.build();
	}

}

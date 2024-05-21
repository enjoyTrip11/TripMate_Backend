package com.ssafy.tripmate.config;

import com.ssafy.tripmate.user.role.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import com.ssafy.tripmate.user.service.CustomOAuth2UserService;


@Configuration
@EnableWebSecurity 
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	
	public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
		this.customOAuth2UserService = customOAuth2UserService;
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

		// 접근 권한 설정
		http
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/oauth-login/admin").hasRole(Role.USER.name())
						.requestMatchers("/oauth-login/info").authenticated()
						.anyRequest().permitAll()
				);

		// 폼 로그인 방식 설정
		http
				.formLogin((auth) -> auth.loginPage("/oauth-login/login")
						.loginProcessingUrl("/oauth-login/loginProc")
						.usernameParameter("loginId")
						.passwordParameter("password")
						.defaultSuccessUrl("/oauth-login")
						.failureUrl("/oauth-login")
						.permitAll());

		// OAuth 2.0 로그인 방식 설정
		http
				.oauth2Login((auth) -> auth.loginPage("/oauth-login/login")
						.defaultSuccessUrl("/oauth-login")
						.failureUrl("/oauth-login/login")
						.permitAll());

		http
				.logout((auth) -> auth
						.logoutUrl("/oauth-login/logout"));

		http
				.csrf((auth) -> auth.disable());

		return http.build();
	}
}
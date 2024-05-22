package com.ssafy.tripmate.config;

import com.ssafy.tripmate.user.controller.CustomAuthenticationSuccessHandler;
import com.ssafy.tripmate.user.jwt.JwtAuthenticationFilter;
import com.ssafy.tripmate.user.jwt.JwtTokenProvider;
import com.ssafy.tripmate.user.role.Role;
import com.ssafy.tripmate.user.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final JwtTokenProvider jwtTokenProvider;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, JwtTokenProvider jwtTokenProvider, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
		this.customOAuth2UserService = customOAuth2UserService;
		this.jwtTokenProvider = jwtTokenProvider;
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(sessionManagement ->
						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
				.formLogin(formLogin -> formLogin.disable())
				.httpBasic(httpBasic -> httpBasic.disable())
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.requestMatchers("/oauth-login/admin").hasRole(Role.USER.name())
						.requestMatchers("/oauth-login/info").authenticated()
						.anyRequest().permitAll()
				)
				.oauth2Login(oauth2Login -> oauth2Login
						.loginPage("/login/oauth2/code/google")
						.defaultSuccessUrl("/login/oauth2/code/google")
						.failureUrl("/fail")
						.userInfoEndpoint(userInfoEndpointConfig ->
								userInfoEndpointConfig.userService(customOAuth2UserService)
						)
						.successHandler(customAuthenticationSuccessHandler)
				)
				.logout(logout -> logout
						.logoutUrl("/oauth-login/logout")
				);

		http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}

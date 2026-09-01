package com.springsecurity.jwtauthentication;

import com.springsecurity.jwtauthentication.Filter.RateLimitingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JwtAuthenticationApplication {
	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<RateLimitingFilter> reteLimitingFilter() {
		FilterRegistrationBean<RateLimitingFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new RateLimitingFilter());
		registrationBean.addUrlPatterns("/auth/test/*");
		return registrationBean;

	}

}

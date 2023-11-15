package com.gcu;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .antMatchers("/", "/images/**", "/login", "/register", "/css/**").permitAll()
	                .and()
	            .logout()
	                .logoutUrl("/signout")
	                .invalidateHttpSession(true)
	                .clearAuthentication(true)
	                .permitAll()
	                .logoutSuccessUrl("/")
	                .and()
	            .csrf().disable(); // Disabling CSRF for simplicity, you might want to enable it in production
	    }
}
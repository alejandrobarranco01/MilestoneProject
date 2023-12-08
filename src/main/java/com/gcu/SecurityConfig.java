package com.gcu;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
			.httpBasic().and()	
				.authorizeRequests()
				.antMatchers("/api/**").authenticated()
				.and()
				.authorizeRequests()
				.antMatchers("/", "/images/**", "/login", "/register", "/css/**").permitAll()
				.and()
			.logout()
				.logoutUrl("/signout")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.permitAll()
				.logoutSuccessUrl("/");		
	}
	
	
}
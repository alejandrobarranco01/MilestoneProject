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

		http.csrf().disable()
			.httpBasic().and()	
				.authorizeRequests()
				.antMatchers("/api/**", "/home/").authenticated()
				.and()
				.authorizeRequests()
				.antMatchers("/", "/images/**", "/register", "/css/**").permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				.defaultSuccessUrl("/home", true)
				.and()
			.logout()
				.logoutUrl("/signout")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.permitAll()
				.logoutSuccessUrl("/");		
	}
	
	
}
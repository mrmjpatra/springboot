package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("raja").password("rani").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("rajesh").password("hyd").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("jani").password("{noop}begum").roles("MANAGER", "CLERK");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/offers").authenticated()
				.antMatchers("/balance").hasAnyRole("CUSTOMER", "MANAGER")
				.antMatchers("/loan").hasRole("MANAGER")
				.anyRequest().authenticated() 
				.and().logout()
				.and().formLogin()
				.and().exceptionHandling().accessDeniedPage("/denied")
				.and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
	}
}

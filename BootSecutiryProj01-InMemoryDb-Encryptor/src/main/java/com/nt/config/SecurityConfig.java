package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("raja").password("$2a$10$TSVVvjcjYDCN7.FyBf10AuHlj7UgujFKni6mu5/0hxnIATfzC2iY6").roles("CUSTOMER");
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("rajesh").password("$2a$10$mjmrkWZ/wdTiS9QbVMGdpOdEL8tS4d5W1egLhvKUQMaU50jfTewSe").roles("MANAGER");
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("jani").password("{noop}begum").roles("MANAGER", "CLERK");
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

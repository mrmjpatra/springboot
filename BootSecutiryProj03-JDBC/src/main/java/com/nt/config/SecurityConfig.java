package com.nt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource ds;
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.jdbcAuthentication().dataSource(ds).usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USERS WHERE UNAME=?").
		auth.jdbcAuthentication().dataSource(ds).passwordEncoder(new BCryptPasswordEncoder()).usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USERS WHERE UNAME=?")
			.authoritiesByUsernameQuery("SELECT UNAME,ROLE FROM USER_ROLES WHERE UNAME=?");
		
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/offers").authenticated()
				.antMatchers("/balance").hasAnyAuthority("CUSTOMER", "MANAGER")
				.antMatchers("/loan").hasAuthority("MANAGER")
				.anyRequest().authenticated()
				.and().logout()
				.and().formLogin()
				.and().exceptionHandling().accessDeniedPage("/denied")
				.and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
	}
}

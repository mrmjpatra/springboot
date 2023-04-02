package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService service;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(service).passwordEncoder(encoder);

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/bank/").permitAll() //Not authetication an no authorization
				.antMatchers("/user/register", "/user/showlogin").permitAll().antMatchers("/bank/offers")
				.authenticated() //only authentication
				.antMatchers("/bank/balance").hasAnyAuthority("CUSTOMER", "MANAGER") // authentication + authorization for  "CUSTOMER","MANAGER" role uses
				.antMatchers("/bank/loan").hasAuthority("MANAGER") //// authentication + authorization for 
				.anyRequest().authenticated() //remaing all requests url mus be authtenticated

				.and().formLogin().defaultSuccessUrl("/bank/", true) //HOME page url
				.loginPage("/user/showlogin") //for  GET mode request to launch form page
				.loginProcessingUrl("/login") // for POST mode request to submit and process the request
				.failureUrl("/user/showlogin?error") //  Authentication failed url
				
				
				.and().rememberMe()
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
				.logoutSuccessUrl("/user/showlogin?logout") // after logout url
				  .and().exceptionHandling().accessDeniedPage("/denied");
	}
}

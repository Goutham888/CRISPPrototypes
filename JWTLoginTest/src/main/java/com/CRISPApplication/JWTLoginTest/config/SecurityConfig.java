package com.CRISPApplication.JWTLoginTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.CRISPApplication.JWTLoginTest.filter.JwtFilter;
import com.CRISPApplication.JWTLoginTest.service.CustomUserDetailsService;

@Configuration//this is a config file
@EnableWebSecurity//enables security for the project
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService cuds;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(cuds);//uses the custom user details object to authenticate users
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests().antMatchers("/api/v1/authenticate").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;//registering the jwt filter
		//disable csrf, authorize requests, for any /authenticate url permit all, but for anything else authenticate
		//enable stateless session creation policy, and regsiter jwt filter
		
	}
	
	@Bean//you need this bean here to encrypt the password
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();//this means that there is no actual encoding happening
		//I  didn't want to complicate it past necessity
		//In future developments the password should be encoded
	}
	
	
	
	  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	  }
	
}

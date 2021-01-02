package com.CRISPApplication.JWTLoginTest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.CRISPApplication.JWTLoginTest.service.CustomUserDetailsService;
import com.CRISPApplication.JWTLoginTest.util.JwtUtils;

@Component//this is a component, it lights up in the ComponentScan
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@Autowired
	private CustomUserDetailsService cuds;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");//gets the token from the request
		String token=null;
		String username=null;
		System.out.println("AuthHeader "+authHeader);
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {//taking the "Bearer " part off of the token
			token=authHeader.substring(7);
			username=jwtUtil.extractUsername(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails uds = cuds.loadUserByUsername(username);//get the userdetails from the custom user details service
			
				if (jwtUtil.validateToken(token, uds)) {//using the jwt utils to validate the token with the user

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                        new UsernamePasswordAuthenticationToken(uds, null, uds.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	                //authenticating the user
	            }
			
		}
		filterChain.doFilter(request, response);//do filter for all requests
	}

}

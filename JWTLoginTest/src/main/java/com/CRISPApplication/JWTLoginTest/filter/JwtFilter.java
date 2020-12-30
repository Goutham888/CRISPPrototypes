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

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@Autowired
	private CustomUserDetailsService cuds;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token=null;
		String username=null;
		System.out.println("AuthHeader "+authHeader);
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			username=jwtUtil.extractUsername(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails uds = cuds.loadUserByUsername(username);
			if(jwtUtil.validateToken(token, uds)) {
				if (jwtUtil.validateToken(token, uds)) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                        new UsernamePasswordAuthenticationToken(uds, null, uds.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
			}
		}
		filterChain.doFilter(request, response);
	}

}

package com.ferryboat.app.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ferryboat.app.service.JwtService;
import com.ferryboat.app.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
		
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		try {
			
			final String token = authHeader.substring(7);
			final String userEmail = jwtService.extractUsername(token);
			
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if (userEmail != null && authentication == null) {
            	UserDetails user = userService.loadUserByUsername(userEmail);
            	
            	
            	if (jwtService.isTokenValid(token, user)) {
            		UsernamePasswordAuthenticationToken authenticationToken = 
            					new UsernamePasswordAuthenticationToken(
            							user,
            							null,
            							user.getAuthorities());
            		
            		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            	}

            }
            
            filterChain.doFilter(request, response);
			
			
		} catch(Exception exception) {
			System.out.println(exception);
        }
		
		
	}

}
package com.springsecurity.JwtAuthentication.Filter;

import com.springsecurity.JwtAuthentication.JwtAuthenticationApplication;
import com.springsecurity.JwtAuthentication.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public JwtAuthFilter(UserDetailsService userDetailsService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUserName(token);
            }
            // check whether username is null and isn't already authenticated
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // fetching username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // check whether token is valid with corresponding username  or not
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    // setDetails
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // authenticated at last
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception ex) {
            logger.error("JWT authentication failed");
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}

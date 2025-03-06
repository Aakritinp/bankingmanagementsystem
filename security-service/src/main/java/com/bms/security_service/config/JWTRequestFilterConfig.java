package com.bms.security_service.config;

import com.bms.security_service.model.User;
import com.bms.security_service.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
public class JWTRequestFilterConfig extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private RestTemplate restTemplate;

    private String securityServiceUrl = "http://user-service/users/";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);

            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                request.setAttribute("exception", e);
                chain.doFilter(request, response);
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = restTemplate.getForObject(securityServiceUrl + username, User.class);

            if (user != null && jwtUtil.validateToken(jwt, username)) {
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        user.getId(),
                        user.getPassword(),
                        getAuthorities(user.getRole())
                );
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        if (request.getServletPath().equals("/auth/login")) {
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }
}

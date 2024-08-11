package com.argus.mytodo.jwt;


import com.argus.mytodo.config.RoleEndpoints;
import com.argus.mytodo.exceptionhandler.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (isPermitAllEndpoint(requestURI)) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader("Authorization");
            String token;
            String username;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                try {
                    username = jwtService.extractUsername(token);
                } catch (Exception e) {
                    this.exception(response, "This error may be due to a session expiration or an invalid token.");
                    return;
                }
            } else {
                this.exception(response, "You do not have the required permissions to access this resource.");
                return;
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    if (hasAccess(userDetails, requestURI)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } else {
                        this.exception(response, "You do not have the required permissions to access this resource.");
                        return;
                    }
                }
            }

            filterChain.doFilter(request, response);
        }
    }

    private boolean isPermitAllEndpoint(String requestURI) {
        for (String endpoint : RoleEndpoints.WhiteList_ENDPOINTS) {
            if (requestURI.matches(endpoint)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAccess(UserDetails userDetails, String requestURI) {
        Set<String> userAuthorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        Map<String, List<String>> authorityToEndpoints = new HashMap<>();
        authorityToEndpoints.put("ROLE_SUPERADMIN", RoleEndpoints.SUPERADMIN_ENDPOINTS);
        authorityToEndpoints.put("ROLE_ADMIN", RoleEndpoints.ADMIN_ENDPOINTS);
        authorityToEndpoints.put("ROLE_CLIENT", RoleEndpoints.CLIENT_ENDPOINTS);

        for (String authority : userAuthorities) {
            List<String> endpoints = authorityToEndpoints.get(authority);
            if (endpoints != null && endpoints.stream().anyMatch(requestURI::matches)) {
                return true;
            }
        }

        return anyOtherRequest(requestURI);
    }

    private boolean anyOtherRequest(String requestURI){
        List<String> availableEndpoints = new ArrayList<>();
        availableEndpoints.addAll(RoleEndpoints.WhiteList_ENDPOINTS);
        availableEndpoints.addAll(RoleEndpoints.SUPERADMIN_ENDPOINTS);
        availableEndpoints.addAll(RoleEndpoints.ADMIN_ENDPOINTS);
        availableEndpoints.addAll(RoleEndpoints.CLIENT_ENDPOINTS);

        return !availableEndpoints.stream().anyMatch(requestURI::matches);
    }
    private void exception(HttpServletResponse response, String message) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), message);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}

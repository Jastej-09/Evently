package com.evt_bff.evtbff.security;

import com.evt_bff.evtbff.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import org.slf4j.MDC;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtSerivce;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filerchain
            ) throws ServletException, IOException{
        String jwtToken = request.getHeader("Authorization");
        if(jwtToken == null || !jwtToken.startsWith("Bearer ")){
            filerchain.doFilter(request,response);
            return;
        }
        String token = jwtToken.substring(7);
        if(jwtSerivce.isTokenValid(token)){
            String userId = jwtSerivce.extractUserId(token);
            String UserRole = jwtSerivce.extractUserRole(token);
            MDC.put("userId", userId);
            MDC.put("role",UserRole);
            var authorities = List.of(new SimpleGrantedAuthority("ROLE_"+ UserRole));
            var authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,null,authorities
                    ) ;
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
        }
        filerchain.doFilter(request,response);
    }

}

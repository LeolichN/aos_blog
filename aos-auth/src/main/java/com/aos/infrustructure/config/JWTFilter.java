package com.aos.infrustructure.config;

import com.aos.domain.service.UserService;
import com.aos.core.utils.JWTUtils;
import com.aos.core.utils.WebUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().contains("login")) {
            String token = WebUtils.resolveToken(request);
            if (StringUtils.isNotBlank(token)) {
                SecurityContextHolder.getContext().setAuthentication(JWTUtils.getAuthentication(token));
            }
        }
        filterChain.doFilter(request, response);
    }
}

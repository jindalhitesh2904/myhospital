package com.example.myhospital.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("NURSE")) {
            httpServletResponse.sendRedirect("/home/nurse");
        }
        if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/home/admin");
        }
        if (roles.contains("DOCTOR")) {
            httpServletResponse.sendRedirect("/home/doctor");
        }
        if (roles.contains("RECEPTIONIST")) {
            httpServletResponse.sendRedirect("/home/receptionist");
        }
        if (roles.contains("PATIENT")) {
            httpServletResponse.sendRedirect("/home/patient");
        }
    }
}
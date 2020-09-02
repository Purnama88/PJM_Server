/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.security.filter;

import com.purnama.PJM_Server.security.JwtUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

/**
 *
 * @author p_cor
 */
public class TokenHandleFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        
        if(header == null){
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
        else{
            
            if(JwtUtil.parseToken(header.substring(7)) >= 0){
                
                int userid = JwtUtil.parseToken(header.substring(7));
                int warehouseid = JwtUtil.parseToken2(header.substring(7));
                
                String token = JwtUtil.generateToken(userid, warehouseid);
                
                httpResponse.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                httpResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
                
                chain.doFilter(request, response);
            }
            else{
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}

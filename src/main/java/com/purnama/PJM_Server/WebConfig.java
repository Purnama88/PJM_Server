/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server;

import com.purnama.PJM_Server.security.filter.TokenHandleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 *
 * @author p_cor
 */
@Configuration
public class WebConfig {
    
    @Bean
    public FilterRegistrationBean<TokenHandleFilter> abcFilter() {
            FilterRegistrationBean<TokenHandleFilter> filterRegBean = new FilterRegistrationBean<>();
            filterRegBean.setFilter(new TokenHandleFilter());
            filterRegBean.addUrlPatterns("/api/*");
            filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE -1);
            return filterRegBean;
    }
    
}

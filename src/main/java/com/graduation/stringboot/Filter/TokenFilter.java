package com.graduation.stringboot.Filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * TokenFilter
 *
 * @author Lyn
 * @date 2022/01/30
 */
@Component
@WebFilter(urlPatterns = "/demo/*", filterName = "tokenFilter")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

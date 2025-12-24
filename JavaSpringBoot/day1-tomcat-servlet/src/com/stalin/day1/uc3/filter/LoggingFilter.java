package com.stalin.day1.uc3.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*") // applies to all requests
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // Pre-processing logic
        System.out.println("Request intercepted by Filter");
        System.out.println("Requested URI: " + req.getRequestURI());

        // Pass request to next filter / servlet
        chain.doFilter(request, response);

        // Post-processing logic
        System.out.println("Response sent back to client");
    }

    @Override
    public void destroy() {
        System.out.println("LoggingFilter destroyed");
    }
}

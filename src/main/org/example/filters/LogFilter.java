package org.example.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@WebFilter("/*")
public class LogFilter extends HttpFilter {

    private static final String PRINT_PATTERN = "%s : %s [%s] {%s} %s %s %s";

    @Override
    public void init() throws ServletException {
        System.out.println("Init LogFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        String servletPath = httpReq.getServletPath();
        String uri = httpReq.getRequestURI();
        String url = httpReq.getRequestURL().toString();
        String sessionId = httpReq.getSession().getId();
        System.out.println(String.format(PRINT_PATTERN, new Date().toString(), "INFO", Thread.currentThread().getName(), sessionId, servletPath, uri, url));
        chain.doFilter(req, res);

    }

    public void destroy() {
        System.out.println("Destroy LogFilter");
    }
}

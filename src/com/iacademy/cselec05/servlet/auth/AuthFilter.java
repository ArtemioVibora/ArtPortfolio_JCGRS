package com.iacademy.cselec05.servlet.auth;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iacademy.cselec05.util.SessionUtil;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI().substring(contextPath.length());

        boolean isLoggedIn = SessionUtil.isLoggedIn(httpRequest);

        // Normalize matching (handling root vs /landing vs /login etc)
        boolean isPublicPath = uri.equals("/") || uri.equals("/login") || uri.equals("/register");

        if (isLoggedIn) {
            // Already logged in - prevent access to login/register/landing
            if (isPublicPath) {
                httpResponse.sendRedirect(contextPath + "/home");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            // Guest access - restrict to public paths
            if (isPublicPath) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(contextPath + "/login");
            }
        }
    }

    @Override
    public void destroy() {}
}

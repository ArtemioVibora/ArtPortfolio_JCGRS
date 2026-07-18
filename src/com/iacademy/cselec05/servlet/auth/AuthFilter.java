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

// This class is the auth filter that will redirect to login if it is not a valid page ish
public class AuthFilter implements Filter {

    // init function
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // This is the context path
        String contextPath = httpRequest.getContextPath();
        // context path
        String uri = httpRequest.getRequestURI().substring(contextPath.length());

        // Session is logged in -- honestly this could be a helper function but this is okay too
        boolean isLoggedIn = SessionUtil.isLoggedIn(httpRequest);

        // Normalize matching (handling root vs /landing vs /login etc)
        // Added this because in the network -- the son of a gun is redirecting the css to somewhere that is the
        // the reason why we have a 302. The good news here is 302 means the client is reading it
        if (uri.startsWith("/css/") || uri.startsWith("/js/")) {
            chain.doFilter(request, response);
            return;
        }

        boolean isPublicPath = uri.equals("/") || uri.equals("/login") || uri.equals("/register");

        if (isLoggedIn) {
            // Already logged in - prevent access to login/register/landing
            if (isPublicPath) {
                httpResponse.sendRedirect(contextPath + "/home");
            } else {
                chain.doFilter(request, response);
            }
        }
        else {
            // Guest access - restrict to public paths
            if (isPublicPath) {
                chain.doFilter(request, response);
            } else {
                // http redirects to login
                httpResponse.sendRedirect(contextPath + "/login");
            }
        }
    }

    @Override
    public void destroy() {}
}

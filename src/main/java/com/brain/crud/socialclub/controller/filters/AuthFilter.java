package com.brain.crud.socialclub.controller.filters;

import com.brain.crud.socialclub.service.OnlineUsers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

    String loginURI;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        loginURI = httpServletRequest.getContextPath() + "/login";

        boolean loggedIn = OnlineUsers.getInstance().isOnline(httpServletRequest.getSession().getId());
        boolean loginRequest = httpServletRequest.getServletPath().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect(loginURI);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}

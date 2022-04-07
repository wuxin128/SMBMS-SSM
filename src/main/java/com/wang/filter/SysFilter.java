package com.wang.filter;/*
@author carl
@date 2022/3/27 - 10:41
*/


import com.wang.pojo.User;
import com.wang.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);

        if (user == null) {
            response.sendRedirect("/error.jsp");
        }
        filterChain.doFilter(request,response);
    }

    public void destroy() {

    }
}

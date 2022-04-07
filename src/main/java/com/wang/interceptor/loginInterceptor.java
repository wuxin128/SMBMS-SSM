package com.wang.interceptor;/*
@author carl
@date 2022/3/23 - 9:20
*/

import com.wang.pojo.User;
import com.wang.util.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class loginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
   /*     User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);
        if(user == null){
            response.sendRedirect("/error.jsp");
            return false;
        }*/
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

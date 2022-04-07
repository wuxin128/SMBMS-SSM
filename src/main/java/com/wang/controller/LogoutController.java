package com.wang.controller;/*
@author carl
@date 2022/3/23 - 8:49
*/

import com.wang.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/jsp/logout.do")
    public String logout(HttpSession session){
        session.removeAttribute(Constants.USER_SESSION);
        return "redirect:/login.jsp";
    }
}

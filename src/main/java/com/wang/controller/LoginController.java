package com.wang.controller;/*
@author carl
@date 2022/3/22 - 18:48
*/

import com.wang.pojo.User;
import com.wang.service.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wang.util.Constants.*;

@Controller
public class LoginController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @PostMapping("/login.do")
    public String login(HttpServletRequest request,Model model){
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");

        Map<String, String> map = new HashMap<String, String>();
        map.put("userCode",userCode);
        map.put("userPassword",userPassword);
        User user = userService.login(map);
        if(user != null){
            request.getSession().setAttribute(USER_SESSION,user);
            return "redirect:/jsp/frame.jsp";
        }else {
            model.addAttribute("error","用户名或者密码不正确");
            return "forward:/login.jsp";
        }
    }
    @RequestMapping("jsp/user.any")
    @ResponseBody
    public String any(){
        return "baBa";
    }

    @Test
    public void test(){
        User user = new User();
        Date date = new Date(2020-11-11);
        System.out.println(date);
        user.setBirthday(date);
        System.out.println(user.getBirthday());
    }
}

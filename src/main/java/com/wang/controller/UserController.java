package com.wang.controller;/*
@author carl
@date 2022/3/23 - 9:39
*/

import com.alibaba.fastjson.JSONArray;
import com.wang.pojo.Role;
import com.wang.pojo.User;
import com.wang.service.role.RoleService;
import com.wang.service.user.UserService;
import com.wang.util.Constants;
import com.wang.util.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @noinspection Duplicates*/
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/jsp/user.doing")
    public String savePwd(HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = request.getParameter("newpassword");
        boolean flag = false;
        if(attribute != null && newpassword != null){
            Map<String,Object> map = new HashMap();
            map.put("id",((User)attribute).getId());
            map.put("userPassword",newpassword);
            flag = userService.updatePwd(map);
            if(flag){
                request.setAttribute("message","密码修改成功！");
                request.getSession().removeAttribute(Constants.USER_SESSION);

            }else {
                request.setAttribute("message","新密码有误，请重新输入！");

            }
        }
        else {
            request.setAttribute("message","新密码有问题！");

        }
        return "forward:/jsp/pwdmodify.jsp";
    }

    @RequestMapping("/jsp/user.do")
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        Object o= req.getSession().getAttribute(Constants.USER_SESSION);
        String oldPassword = req.getParameter("oldpassword");
        Map<String, String> map = new HashMap();
        if (o == null) {
            map.put("result","sessionerror");
        }else if(oldPassword == null){
            map.put("result","error");
        }else {
            String userPassword = ((User) o).getUserPassword();
            if(userPassword.equals(oldPassword)){
                map.put("result","true");
            }else {
                map.put("result","false");
            }
        }
        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(map));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("jsp/user.query")
    public String query(Model model,HttpServletRequest request){

        String queryUserName = request.getParameter("queryname");
        System.out.println(queryUserName);
        String temp = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");

        int queryUserRole = 0;
        int pageSize = 8;
        int currentPageNo = 1;
        if(queryUserName == null){
            queryUserName = "";
        }
        if (temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);
        }
        if(pageIndex != null){
            currentPageNo = Integer.valueOf(pageIndex);
        }

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("userName","%"+queryUserName+"%");
        map1.put("userRole",queryUserRole);

        int totalCount = userService.getUserCount(map1);
        //通过工具类来获取页数支持
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        pageSupport.setTotalPageCount(pageSupport.setTotalPageCountByRs());


        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if (currentPageNo > pageSupport.getTotalPageCount()){
            currentPageNo = pageSupport.getTotalPageCount();
        }

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("userName","%"+queryUserName+"%");
        map2.put("userRole",queryUserRole);
        map2.put("currentPageNo",(currentPageNo-1)*pageSize);
        map2.put("pageSize",pageSize);

        List<User> userList = userService.getUserList(map2);
        List<Role> roleList = roleService.getRoleList();

        model.addAttribute("userList",userList);
        model.addAttribute("roleList",roleList);
        request.setAttribute("queryUserName",queryUserName);
        request.setAttribute("queryUserRole",queryUserRole);
        request.setAttribute("totalCount",totalCount);
        request.setAttribute("currentPageNo",currentPageNo);
        request.setAttribute("totalPageCount",pageSupport.getTotalPageCount());
        return "forward:/jsp/userlist.jsp";
    }

    @RequestMapping("jsp/user.view")
    public String getUserById(HttpServletRequest request){
        String id = request.getParameter("uid");
        System.out.println(id);
        if(id != null){
            Integer ID = Integer.parseInt(id);
            User user = userService.getUserById(ID);
            System.out.println(user);
            System.out.println(user.getBirthday());
            request.setAttribute("user",user);
        }
        return "forward:/jsp/userview.jsp";
    }

    @PostMapping("/jsp/user.add")
    public String addUser(HttpServletRequest request){
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userRole = request.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(Integer.valueOf(gender));
        user.setBirthday(Date.valueOf(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        user.setCreationDate(new Date(System.currentTimeMillis()));
        user.setCreatedBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());

        System.out.println(user);

        boolean flag = userService.addUser(user);
        if(flag){
            return "redirect:/jsp/user.query";
        }else{
            return "forward:/jsp/useradd.jsp";
        }
    }

    @RequestMapping("/jsp/user.check")
    public void ucExist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String userCode = request.getParameter("userCode");
        Map<String, String> map = new HashMap();
        if(userCode == null || userCode == ""){
            map.put("userCode","exist");
        }else{
            User user = userService.getUserByCode(userCode);
            if(user == null){
                map.put("userCode","notexist");
            }else {
                map.put("userCode","exist");
            }
        }
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(JSONArray.toJSONString(map));
        writer.flush();
        writer.close();
    }

    @RequestMapping("/jsp/user.modify")
    public String modifyUser(HttpServletRequest request){
        String id = request.getParameter("uid");
        if(id != null){
            int ID = Integer.parseInt(id);
            System.out.println(ID);
            User user = userService.getUserById(ID);
            System.out.println(user);
            request.setAttribute("user",user);
        }
        return "forward:/jsp/usermodify.jsp";
    }
    @RequestMapping("/jsp/user.save")
    public String saveUser(HttpServletRequest request){
        String id = request.getParameter("uid");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userRole = request.getParameter("userRole");
        System.out.println(id);
        System.out.println(userName);
        System.out.println(gender);
        System.out.println(birthday);
        System.out.println(phone);
        System.out.println(address);

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUserName(userName);
        user.setGender(Integer.valueOf(gender));
        user.setBirthday(Date.valueOf(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        user.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date(System.currentTimeMillis()));

        boolean flag = userService.modifyUser(user);
        if(flag){
            return "redirect:/jsp/user.query";
        }else {
            return "forward:/jsp/usermodify.jsp";
        }
    }

    @RequestMapping("/jsp/user.delete")
    public void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String id = request.getParameter("uid");
        Integer delID = 0;

        delID = Integer.parseInt(id);
        HashMap<String, String> map = new HashMap();
        if(delID <= 0){
            map.put("delResult","notexist");
        }else {
            if(userService.deleteUser(delID)){
                map.put("delResult","true");
            }else {
                map.put("delResult","false");
            }
        }
        //将map转换成json对象输出
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(JSONArray.toJSONString(map));
        writer.flush();
        writer.close();
    }
}

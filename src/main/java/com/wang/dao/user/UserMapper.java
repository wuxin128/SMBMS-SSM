package com.wang.dao.user;/*
@author carl
@date 2022/3/22 - 1:00
*/


import com.wang.pojo.User;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    //获取要登录的用户
    User getLoginUser(Map map);
    //修改用户密码
    boolean updatePwd(Map map);
    //查询用户总数
    int getUserCount(Map map);
    //获取用户列表
    List<User> getUserList(Map map);
    //删除用户
    boolean deleteUser(int id);
    //修改用户
    boolean modifyUser(User user);
    //查看用户具体信息
    User getUserById(int id);
    //添加用户
    boolean addUser(User user);
    //新增用户时通过用户编码判断是否重复
    User getUserByCode(String userCode);
}

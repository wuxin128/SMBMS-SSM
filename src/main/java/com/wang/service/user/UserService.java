package com.wang.service.user;/*
@author carl
@date 2022/3/22 - 0:50
*/

import com.wang.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(Map map);
    boolean updatePwd(Map map);
    int getUserCount(Map map);
    List<User> getUserList(Map map);
    boolean deleteUser(int id);
    boolean modifyUser(User user);
    User getUserById(int id);
    boolean addUser(User user);
    User getUserByCode(String userCode);
}


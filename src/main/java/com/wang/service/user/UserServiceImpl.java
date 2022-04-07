package com.wang.service.user;/*
@author carl
@date 2022/3/22 - 0:50
*/


import com.wang.dao.user.UserMapper;
import com.wang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(Map map) {
        return userMapper.getLoginUser(map);
    }

    public boolean updatePwd(Map map) {
        return userMapper.updatePwd(map);
    }

    public int getUserCount(Map map) {
        return userMapper.getUserCount(map);
    }

    public List<User> getUserList(Map map) {
        return userMapper.getUserList(map);
    }

    public boolean deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    public boolean modifyUser(User user) {
        return userMapper.modifyUser(user);
    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    public User getUserByCode(String userCode) {
        return userMapper.getUserByCode(userCode);
    }
}

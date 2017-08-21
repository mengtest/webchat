package com.zns.service.impl;

import com.zns.mapper.UserMapper;
import com.zns.model.User;
import com.zns.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByName(String username) {
        return this.userMapper.findUserByName(username);
    }

    public int addUser(User user) {
        return this.userMapper.addUser(user);
    }

    public User findUserById(int id) {
        return this.userMapper.findUserById(id);
    }

    /**
     * 检测用户是否存在
     *
     * @param user
     * @return
     */
    public boolean isUserExit(User user) {
        if (null == findUserByName(user.getUsername())) {
            return true;
        }
        return false;
    }
}

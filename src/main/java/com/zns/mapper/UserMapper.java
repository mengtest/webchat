package com.zns.mapper;

import com.zns.model.User;

public interface UserMapper {

    public User findUserByName(String username);

    public int addUser(User user);

    public User findUserById(int id);
}

package com.zns.service;

import com.zns.model.User;

public interface IUserService {

    public User findUserByName(String username);

    public int addUser(User user);

    public User findUserById(int id);

    public boolean isUserExit(User user);
}

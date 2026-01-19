package com.exhale.messageboard.service;

import com.exhale.messageboard.entity.User;

import java.util.List;

public interface UserService {
    boolean register(User user);
    List<User> list();
    public User login(String username, String password);
}

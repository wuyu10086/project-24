package com.taskmanager.service;

import com.taskmanager.entity.User;
import com.taskmanager.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean register(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return false; // 用户已存在
        }
        userMapper.insert(user);
        return true;
    }

    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
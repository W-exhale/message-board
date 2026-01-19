package com.exhale.messageboard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhale.messageboard.entity.User;
import com.exhale.messageboard.mapper.UserMapper;
import com.exhale.messageboard.service.UserService;
import com.exhale.messageboard.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        //默认普通用户
        user.setRole("USER");
        user.setPassword(PasswordUtil.encode(user.getPassword()));

        //增加一个校验，用户名不能重复
        //...
        return userMapper.insert(user) > 0;
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(null);
    }

    @Override
    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        //用户名唯一时可用该方法
        // User user = userMapper.selectOne(queryWrapper);

        queryWrapper.last("limit 1");//当返回多个时，取一个，改成用户名唯一
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if(!PasswordUtil.matches(password, user.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return user;
    }
}

package com.exhale.messageboard.controller;

import com.exhale.messageboard.common.Result;
import com.exhale.messageboard.entity.User;
import com.exhale.messageboard.service.UserService;
import com.exhale.messageboard.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody User user){
        boolean success = userService.register(user);
        if(success){
            return Result.success("注册成功");
        }
        return Result.error("注册失败");
    }

    @GetMapping("/list")
    public Result<List<User>> list(){
        return Result.success(userService.list());
    }


    @PostMapping("/login")
    public Result<String> login(
            @RequestBody User user){
        //1. 校验用户名密码
        User loginUser = userService.login(user.getUsername(), user.getPassword());

        //2. 如果登录成功，生成 JWT 令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginUser.getId());
        claims.put("username", loginUser.getUsername());

        String token = JwtUtil.generateToken(claims);

        //3. 返回 Token 给前端
        return Result.success(token);
    }

}

package com.exhale.messageboard.util;

import com.exhale.messageboard.entity.User;

import javax.servlet.http.HttpSession;

public class UserContext {

//    public static User getLoginUser(HttpSession session){
//        Object obj = session.getAttribute("loginUser");
//        if(obj == null){
//            throw new RuntimeException("未登录");
//        }
//        return (User) obj;
//    }

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setUserId(Long userId){
        threadLocal.set(userId);
    }

    public static Long getUserId(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();//防止内存泄露
    }
}

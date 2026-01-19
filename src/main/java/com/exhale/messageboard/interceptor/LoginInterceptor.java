package com.exhale.messageboard.interceptor;

import com.exhale.messageboard.util.JwtUtil;
import com.exhale.messageboard.util.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler){
        //1. 获取请求头中的令牌
        String token = request.getHeader("token");
       //2. 校验令牌是否存在
        if(token == null || token.isEmpty()){
            response.setStatus(401);
            return false; //拦截
        }

        // 3. 解析令牌
        try{
            Claims claims = JwtUtil.parseToken(token);
            Long userId = Long.valueOf(claims.get("id").toString());
            //4. 将用户ID存入 ThreadLocal 供后续Service使用
            UserContext.setUserId(userId);

            return true; //放行
        }catch (Exception e){
            response.setStatus(401); // 解析失败（过期或伪造）
            return false;
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        // 请求结束，需要清空 ThreadLocal
        UserContext.remove();
    }
}

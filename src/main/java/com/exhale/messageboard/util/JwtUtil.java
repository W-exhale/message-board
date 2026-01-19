package com.exhale.messageboard.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET = "ExhaleKey123"; //专属密钥
    private static final long EXPIRE = 1000 * 60 * 60 * 12;//过期时间：12h

    // 生成Token
    public static String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRE)
                )
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    //解析
    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

    }
}

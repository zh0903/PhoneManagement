package com.xm.common.utils;


import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**

 * @Description 用于创建token的工具类
 * @date 2021 年 02 月 24 日 10:36
 */

public class TokenManager {


    public static final String TOKEN_NAME = "token";

    public static final String NEW_TOKEN = "newToken";


    private final static long tokenExpiration = 20 * 24 * 60 * 60 * 1000;


    private final static String tokenSignKey = "787878787888989";

    //1 使用jwt根据用户名生成token
    public static String createToken(Long userId) {
        return Jwts.builder().setSubject(userId.toString())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    //2 根据token字符串得到用户信息
    public static String getUserInfoFromToken(String token) {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
    }

    //3 删除token
    public static void removeToken(String token) {

    }

}

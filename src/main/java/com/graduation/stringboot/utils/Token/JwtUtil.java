package com.graduation.stringboot.utils.Token;

import com.graduation.stringboot.entity.Userinfo;
import com.graduation.stringboot.service.UserInfoService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwtUtil
 *
 * @author Lyn
 * @date 2022/01/30
 */
@Component
public class JwtUtil {

    UserInfoService userInfoService;

    @Autowired
    public JwtUtil(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    //    token过期时间30天
    private static final long LIVE_TIME = 100 * 60 * 60 * 24 * 30;

    //    this.KEY = uid + name;
    private String KEY;

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }

    /**
     * 初始化生成token的参数
     *
     * @param uid uid
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    public Map<String, Object> generateToken(String uid, String name) {
        this.KEY = uid + name;
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("uid", uid);
        claims.put("userName", name);
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param claims 声明
     * @return {@link String}
     */
    public String generateToken(Map<String, Object> claims) {
        //setID:用户ID
        //setSubject:登录用户的名称
        //setExpiration:token:过期时间  当前时间+有效时间
        //setIssuedAt:token创建时间
        //signWith:加密方式

        return Jwts.builder()
                .setClaims(claims)
                .setId((String) claims.get("uid"))
                .setSubject((String) claims.get("userName"))
                .setExpiration(new Date(System.currentTimeMillis() + LIVE_TIME))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, this.KEY)
                .compact();
    }


    /**
     * 验证token是否有效
     *
     * @param token 令牌
     * @param key   key
     * @return int  token验证结果  2-token过期；1-token认证通过；0-token认证失败
     */
    public int verify(String token, String key) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return 2;
        }
        String id = claims.getId();
        Userinfo userinfo = userInfoService.getUserInfoById(Long.valueOf(id));
        if (userinfo != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 判断token是否可以刷新
     *
     * @param token     令牌
     * @param key       key
     * @param resetTime 重置时间
     * @return {@link Boolean}
     */
    public Boolean canTokenRefreshed(String token, String key, Date resetTime) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            Date sTime = claims.getIssuedAt();
            Date eTime = claims.getExpiration();
            return sTime.before(resetTime) && eTime.after(resetTime);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 令牌
     * @param key   key
     * @return {@link String}
     */
    public String refreshToken(String token, String key) {
        String refreshedToken;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(claims.toString());
            refreshedToken = this.generateToken(claims);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();
            refreshedToken = null;
        }
        return refreshedToken;
    }


}

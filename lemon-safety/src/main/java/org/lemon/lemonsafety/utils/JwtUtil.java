package org.lemon.lemonsafety.utils;

import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT工具类
 */
public class JwtUtil {

    /**
     * 有效期为一个小时：60 * 60 *1000
     */
    private static final Long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 设置秘钥明文
     */
    private static final String JWT_KEY = "lemonLL";

    public static final String LOGIN = "login";

    /**
     * 解析
     *
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 生成jtw
     *
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    /**
     * 创建token
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        ttlMillis = Objects.isNull(ttlMillis) ? JWT_TTL : ttlMillis;
        Date now = new Date(nowMillis);
        Date expDate = new Date(nowMillis + ttlMillis);
        return Jwts.builder()
                //唯一的ID
                .setId(uuid)
                // 主题  可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("sg")
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(expDate);
    }
}
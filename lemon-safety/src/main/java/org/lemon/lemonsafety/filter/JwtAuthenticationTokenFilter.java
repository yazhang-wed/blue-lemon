package org.lemon.lemonsafety.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.rmi.RemoteException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.lemon.lemoncrod.utils.RedisUtil;
import org.lemon.lemonsafety.bean.LoginUser;
import org.lemon.lemonsafety.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private RedisUtil redisUtil;

    public JwtAuthenticationTokenFilter(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        //获取token
        String token = request.getHeader(AUTHORIZATION);
        if (StringUtils.isNoneBlank(token)) {
            // 解析token
            Claims claims = JwtUtil.parseJWT(token);
            // 从解析的token中获取用户id
            String userid = claims.getSubject();
            //从redis中获取用户信息
            LoginUser loginUser = redisUtil.getCacheObject(JwtUtil.LOGIN + userid);
            if (Objects.isNull(loginUser)) {
                throw new RemoteException("用户未登录");
            }
            //获取权限信息封装到Authentication中
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

            // 存入SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}

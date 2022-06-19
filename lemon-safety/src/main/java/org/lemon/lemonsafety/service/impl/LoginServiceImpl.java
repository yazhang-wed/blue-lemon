package org.lemon.lemonsafety.service.impl;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.lemon.lemoncrod.utils.RedisUtil;
import org.lemon.lemonsafety.bean.LoginUser;
import org.lemon.lemonsafety.bean.User;
import org.lemon.lemonsafety.service.LoginService;
import org.lemon.lemonsafety.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author LBK
 * @create 2022-05-25 22:30
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisCache;

    @Override
    public Map<String, Object> login(User user) {
        // 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        //如果认证通过了，使用userid生成一个jwt jwt存入 ResponseResult 返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);

        //把完整的用户信息存入redis  userid作为key
        redisCache.setCacheObject(JwtUtil.LOGIN + userid, loginUser);
        Map<String, Object> map = new HashMap<>();
        map.put(AUTHORIZATION, jwt);
        return map;
    }

    @Override
    public Boolean logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        return redisCache.deleteObject(JwtUtil.LOGIN + userid);
    }
}

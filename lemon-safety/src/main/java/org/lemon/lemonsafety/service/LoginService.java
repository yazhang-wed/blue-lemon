package org.lemon.lemonsafety.service;

import java.util.Map;

import org.lemon.lemonsafety.bean.User;

/**
 * 登录逻辑处理层
 *
 * @author LBK
 * @create 2022-05-25 22:29
 */
public interface LoginService {

    /**
     * 用户登录逻辑处理
     *
     * @param user 登录用户对象
     * @return
     */
    Map<String, Object> login(User user);

    /**
     * 退出登录
     *
     * @return
     */
    Boolean logout();
}

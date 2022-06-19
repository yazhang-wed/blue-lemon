package org.lemon.lemonsafety.extensions;

import java.util.List;

import org.lemon.lemonsafety.bean.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 自定义权限控制方法
 *
 * @author LBK
 * @create 2022-06-04 14:12
 */
@Component("pe")
public class PermissionsExpressionRoot {

    public boolean hasAuthority(String authority) {
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        //判断用户权限集合中是否存在authority
        return permissions.contains(authority);
    }
}

package org.lemon.lemonsafety.service.impl;

import java.util.List;
import java.util.Objects;

import org.lemon.lemonsafety.bean.LoginUser;
import org.lemon.lemonsafety.bean.User;
import org.lemon.lemonsafety.mapper.MenuMapper;
import org.lemon.lemonsafety.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * @author LBK
 * @create 2022-05-24 22:38
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserMapper userMapper;

    private MenuMapper menuMapper;

    public UserDetailsServiceImpl(UserMapper userMapper, MenuMapper menuMapper) {
        this.userMapper = userMapper;
        this.menuMapper = menuMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        //根据用户查询权限信息 添加到LoginUser中
        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //封装成UserDetails对象返回
        return new LoginUser(user, list);
    }
}
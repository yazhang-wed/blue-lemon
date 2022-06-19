package org.lemon.lemonsafety.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.lemon.lemonsafety.bean.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author LBK
 * @create 2022-05-24 22:34
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
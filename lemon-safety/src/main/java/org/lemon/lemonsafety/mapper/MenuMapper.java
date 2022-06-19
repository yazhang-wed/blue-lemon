package org.lemon.lemonsafety.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.lemon.lemonsafety.bean.Menu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * @author LBK
 * @create 2022-06-04 1:05
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询对应的权限
     *
     * @param userId 用户id
     * @return 返回权限集合
     */
    List<String> selectPermsByUserId(Long userId);
}
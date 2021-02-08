package com.fxys.opinion_plus.mapper;

import com.fxys.opinion_plus.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /*
     *根据用户名查找用户对象
     */
    User selectByUsername(String username);

    User selectByEmail(String email);
}
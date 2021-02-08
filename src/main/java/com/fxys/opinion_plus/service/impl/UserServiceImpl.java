package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.User;
import com.fxys.opinion_plus.mapper.UserMapper;
import com.fxys.opinion_plus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}

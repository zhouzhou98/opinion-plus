package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.User;
import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.mapper.UserMapper;
import com.fxys.opinion_plus.service.IUserService;
import com.fxys.opinion_plus.vo.user.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 用户注册
     * @param userRegister 用户注册
     * @return 注册结果
     */
    @Override
    public String addUser(UserRegister userRegister) {

        if(userMapper.selectByUsername(userRegister.getUsername())!=null){
            throw new OpinionException(ResultCodeEnums.USER_HAS_EXISTED);
        }
        if(userMapper.selectByEmail(userRegister.getEmail())!=null){
            throw new OpinionException(ResultCodeEnums.EMAIL_EXISTED);
        }
        User u;
        try {
            u=new User();
            u.setCreateTime(new Date());
            u.setUpdateTime(new Date());
            u.setPassword(userRegister.getPassword());
            u.setUsername(userRegister.getUsername());
            u.setEmail(userRegister.getEmail());
            u.setDataVersion(0);
            u.setState(0);
            userMapper.insert(u);
            return ResultCodeEnums.REGISTER_SUCCESS.message();
        }catch (Exception e){
            e.printStackTrace();
            throw new OpinionException(ResultCodeEnums.REGISTER_FAIL);
        }

    }
}

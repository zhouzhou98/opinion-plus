package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.User;
import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.mapper.UserMapper;
import com.fxys.opinion_plus.service.IUserService;
import com.fxys.opinion_plus.util.JwtUtil;
import com.fxys.opinion_plus.vo.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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


    @Override
    public Map<String, Object> getLogin(UserLogin req) {
        User u=userMapper.selectByUsernameAndPad(req.getUsername(),req.getPassword());

        if(u==null){
            throw new OpinionException(ResultCodeEnums.USER_ERROR);
        }else {
            u.setPassword("");
            Map<String, Object> returnMap = new HashMap<>(2);
            //获取token
            String token = JwtUtil.createJWT(6000000, u);
            returnMap.put("token", token);
            returnMap.put("user", u);
            return returnMap;
        }

    }

    @Override
    public String updateUsername(UserUsernameReq req) {
        User u=userMapper.selectByUsername(req.getUsername());

        if(u!=null){
            throw new OpinionException(ResultCodeEnums.USER_HAS_EXISTED);
        }else{
            try {
                User user=selectById(req.getId());
                user.setUpdateTime(new Date());
                user.setUsername(req.getUsername());
                userMapper.updateUser(user);
                return ResultCodeEnums.UPDATE_SUCCESS.message();
            }catch (Exception e){
                e.printStackTrace();
                throw new OpinionException(ResultCodeEnums.UPDATE_FAIL);
            }

        }

    }

    @Override
    public String updatePad(UserPasswordReq req) {
        try {
            User user=selectById(req.getId());
            if(!user.getPassword().equals(req.getOldPad())){
                throw new OpinionException(ResultCodeEnums.PASSWORD_ERROR);
            }
            user.setUpdateTime(new Date());
            user.setPassword(req.getNewPad());
            userMapper.updateUser(user);
            return ResultCodeEnums.UPDATE_SUCCESS.message();
        }catch (Exception e){
            e.printStackTrace();
            throw new OpinionException(ResultCodeEnums.UPDATE_FAIL);
        }
    }

    @Override
    public String updateEmail(UserEmailReq req) {
        if(userMapper.selectByEmail(req.getEmail())!=null){
            throw new OpinionException(ResultCodeEnums.EMAIL_EXISTED);
        }else{
            try {
                User user=selectById(req.getId());
                user.setUpdateTime(new Date());
                user.setEmail(req.getEmail());
                userMapper.updateUser(user);
                return ResultCodeEnums.UPDATE_SUCCESS.message();
            }catch (Exception e){
                throw new OpinionException(ResultCodeEnums.UPDATE_FAIL);
            }
        }

    }
}

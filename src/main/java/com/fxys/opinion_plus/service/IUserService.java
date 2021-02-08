package com.fxys.opinion_plus.service;

import com.fxys.opinion_plus.domain.User;
import com.fxys.opinion_plus.vo.user.UserRegister;

public interface IUserService {
    User selectById(Long id);

    /**
     * 用户注册
     * @param userRegister 用户注册
     * @return 注册结果
     */
    String addUser(UserRegister userRegister);
}

package com.fxys.opinion_plus.controller;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping(value = PathConstants.USER_SING_IN)
    public Map<String, Object> login(){

        return null;
    }

    @PostMapping(value = PathConstants.USER_REGISTER)
    public String register(){
        return null;
    }
}

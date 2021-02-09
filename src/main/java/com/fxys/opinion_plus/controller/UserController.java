package com.fxys.opinion_plus.controller;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.domain.User;
import com.fxys.opinion_plus.service.IUserService;
import com.fxys.opinion_plus.vo.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping(value = PathConstants.USER_SING_IN)
    public Map<String, Object> login(@RequestBody UserLogin req){

        return userService.getLogin(req);
    }

    @PostMapping(value = PathConstants.USER_REGISTER)
    public String register(@RequestBody UserRegister userRegister){

        return userService.addUser(userRegister);
    }

    @PostMapping(value = PathConstants.USER_UPDATE_USERNAME)
    public String updateUsername(@RequestBody UserUsernameReq req){
        System.err.println(req.getUsername()+"  "+req.getId());
        return userService.updateUsername(req);
    }

    @PostMapping(value = PathConstants.USER_UPDATE_PASSWORD)
    public String updatePad(@RequestBody UserPasswordReq req){
        return userService.updatePad(req);
    }

    @PostMapping(value = PathConstants.USER_UPDATE_EMAIL)
    public String updateEmail(@RequestBody UserEmailReq req){
        return userService.updateEmail(req);
    }

    @GetMapping(value = PathConstants.USER_GET+"/{id}")
    public User get(@PathVariable Long id){
      return userService.selectById(id);
    }
}

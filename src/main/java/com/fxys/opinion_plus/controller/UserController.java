package com.fxys.opinion_plus.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("login")
    public String login(){
        return "1111";
    }
}

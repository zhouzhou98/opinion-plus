package com.fxys.opinion_plus.controller;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.domain.Ring;
import com.fxys.opinion_plus.domain.Tendency;
import com.fxys.opinion_plus.service.IBlogService;
import com.fxys.opinion_plus.vo.blog.BlogBaseReq;
import com.fxys.opinion_plus.vo.user.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @PostMapping(value = PathConstants.BLOG_EVENT)
    public Map<String, Object> login(@RequestBody BlogBaseReq req){
        return blogService.getEvent(req);
    }

    @PostMapping(value = PathConstants.BLOG_SENSITIVE)
    public List<Ring>getSensitive(@RequestBody BlogBaseReq req){
        return blogService.getSensitive(req);
    }

    @PostMapping(value = PathConstants.BLOG_POSITIVE)
    public List<Ring>getPositive(@RequestBody BlogBaseReq req){
        return blogService.getPositive(req);
    }

    @PostMapping(value = PathConstants.BLOG_ORIGIN)
    public List<Ring>getOrigin(@RequestBody BlogBaseReq req){
        return blogService.getOrigin(req);
    }

    @PostMapping(value = PathConstants.BLOG_TENDENCY)
    public List<Tendency>getTendency(@RequestBody BlogBaseReq req){
        return blogService.getTendency(req);
    }

    @PostMapping(value = PathConstants.BLOG_MAP)
    public List<Ring>getMap(@RequestBody BlogBaseReq req){
        return blogService.getMap(req);
    }
}

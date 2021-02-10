package com.fxys.opinion_plus.controller;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.domain.Blog;
import com.fxys.opinion_plus.domain.Ring;
import com.fxys.opinion_plus.domain.SingleTendency;
import com.fxys.opinion_plus.domain.Tendency;
import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.resp.Page;
import com.fxys.opinion_plus.service.IBlogService;
import com.fxys.opinion_plus.vo.blog.BlogBaseReq;
import com.fxys.opinion_plus.vo.user.UserLogin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
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

    @PostMapping(value = PathConstants.BLOG_SEARCH)
    public Page<Blog> getByPage(@RequestBody Page<Blog> page){
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"createTime", "updateTime"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn)) {
                throw new OpinionException(ResultCodeEnums.PARAM_IS_INVALID);
            }
        }
        return blogService.getByPage(page);
    }

    @PostMapping(value = PathConstants.BLOG_SINGEL)
    public List<SingleTendency>getSingleTendency(@RequestBody BlogBaseReq req){
        return blogService.getSingleTendency(req);
    }


}

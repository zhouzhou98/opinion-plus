package com.fxys.opinion_plus.service;

import com.fxys.opinion_plus.domain.*;
import com.fxys.opinion_plus.resp.Page;
import com.fxys.opinion_plus.vo.blog.BlogBaseReq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IBlogService {
    Map<String, Object> getEvent(BlogBaseReq req);


    List<Ring> getSensitive(BlogBaseReq req);

    List<Ring> getPositive(BlogBaseReq req);

    List<Ring> getOrigin(BlogBaseReq req);

    List<Tendency> getTendency(BlogBaseReq req);

    List<Ring> getMap(BlogBaseReq req);

    Page<Blog> getByPage(Page<Blog> page);

    List<SingleTendency> getSingleTendency(BlogBaseReq req);

    void wordCloud(HttpServletRequest req, HttpServletResponse resp) throws Exception;

    List<Ring> getFrequency(BlogBaseReq req);

    List<Warning> getWarning(BlogBaseReq req);

    Blog selectById(Long id);
}

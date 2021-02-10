package com.fxys.opinion_plus.service;

import com.fxys.opinion_plus.domain.Ring;
import com.fxys.opinion_plus.vo.blog.BlogBaseReq;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IBlogService {
    Map<String, Object> getEvent(BlogBaseReq req);


    List<Ring> getSensitive(BlogBaseReq req);

    List<Ring> getPositive(BlogBaseReq req);

    List<Ring> getOrigin(BlogBaseReq req);
}

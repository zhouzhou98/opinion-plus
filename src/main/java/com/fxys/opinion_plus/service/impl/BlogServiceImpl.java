package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.Blog;
import com.fxys.opinion_plus.domain.Ring;
import com.fxys.opinion_plus.mapper.BlogMapper;
import com.fxys.opinion_plus.service.IBlogService;
import com.fxys.opinion_plus.util.TimeStampUtil;
import com.fxys.opinion_plus.vo.blog.BlogBaseReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public Map<String, Object> getEvent(BlogBaseReq req) {
        String day=req.getDay();
        Long kid=req.getKid();
        Map<String, Object> returnMap = new HashMap<>(3);
        int total;
        double avl;
        int top;
        if(day.equals("today")){
            total=blogMapper.selectByTime(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
            avl=(double) total/24;
            top=0;
        }else if(day.equals("three")){
            total=blogMapper.selectByTime(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
            avl=(double) total/(24*3);
            top=0;
        }else {
            total=blogMapper.selectByTime(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
            avl=(double) total/(24*7);
            top=0;
        }
        returnMap.put("total",total);
        returnMap.put("avl",avl);
        returnMap.put("top",top);
        return returnMap;
    }

    @Override
    public List<Ring> getSensitive(BlogBaseReq req) {
        String day=req.getDay();
        Long kid=req.getKid();
        List<Ring>list=new ArrayList<>();
        int sensitive;
        int nontive;
        if(day.equals("today")){
            sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
            nontive=blogMapper.selectByNontive(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        }else if(day.equals("three")){
            sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
            nontive=blogMapper.selectByNontive(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
        }else {
            sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
            nontive=blogMapper.selectByNontive(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
        }
        list.add(new Ring("敏感事件",sensitive));
        list.add(new Ring("非敏感事件",nontive));
        return list;
    }

    @Override
    public List<Ring> getPositive(BlogBaseReq req) {
        String day=req.getDay();
        Long kid=req.getKid();
        List<Ring>list=new ArrayList<>();
        int sensitive;
        int nontive;
        if(day.equals("today")){
            sensitive=blogMapper.selectByPositive(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
            nontive=blogMapper.selectByNegative(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        }else if(day.equals("three")){
            sensitive=blogMapper.selectByPositive(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
            nontive=blogMapper.selectByNegative(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
        }else {
            sensitive=blogMapper.selectByPositive(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
            nontive=blogMapper.selectByNegative(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
        }
        list.add(new Ring("正面舆情事件",sensitive));
        list.add(new Ring("负面舆情事件",nontive));
        return list;
    }

    @Override
    public List<Ring> getOrigin(BlogBaseReq req) {
        List<Blog>list;
        List<String>authorList=new ArrayList<>();
        Map<String,Integer>map=new HashMap<>();
        String day=req.getDay();
        Long kid=req.getKid();
        if(day.equals("today")){
            list=blogMapper.selectByAll(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));

        }else if(day.equals("three")){
            list=blogMapper.selectByAll(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));

        }else {
            list=blogMapper.selectByAll(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));

        }
        for (Blog blog : list) {
            if(!authorList.contains(blog.getAuthor())){
                authorList.add(blog.getAuthor());
            }
            Integer count = map.getOrDefault(blog.getAuthor(), 0);
            map.put(blog.getAuthor(),count+1);
        }
        List<Ring>lists=new ArrayList<>();
        for (String s : authorList) {
            lists.add(new Ring(s,map.get(s)));
        }
        return lists;
    }
}

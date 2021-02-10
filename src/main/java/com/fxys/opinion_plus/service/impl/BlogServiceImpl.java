package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.Blog;
import com.fxys.opinion_plus.domain.Ring;
import com.fxys.opinion_plus.domain.Tendency;
import com.fxys.opinion_plus.mapper.BlogMapper;
import com.fxys.opinion_plus.service.IBlogService;
import com.fxys.opinion_plus.util.TimeStampUtil;
import com.fxys.opinion_plus.vo.blog.BlogBaseReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<Tendency>tendencyList=new ArrayList<>();
        if(day.equals("today")){
            total=blogMapper.selectByTime(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
            avl=(double) total/24;
            top=getToday(kid,tendencyList);
        }else if(day.equals("three")){
            total=blogMapper.selectByTime(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
            avl=(double) total/(24*3);
            top=getThree(kid,tendencyList);
        }else {
            total=blogMapper.selectByTime(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
            avl=(double) total/(24*7);
            top=getSeven(kid,tendencyList);
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

    @Override
    public List<Tendency> getTendency(BlogBaseReq req) {
        String day=req.getDay();
        Long kid=req.getKid();
        List<Tendency>tendencyList=new ArrayList<>();
        if(day.equals("today")){
//            list=blogMapper.selectByAll(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
            getToday(kid,tendencyList);
        }else if(day.equals("three")){
//            list=blogMapper.selectByAll(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
            getThree(kid,tendencyList);
        }else {
//            list=blogMapper.selectByAll(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
            getSeven(kid,tendencyList);
        }

        return tendencyList;
    }

    @Override
    public List<Ring> getMap(BlogBaseReq req) {
        String day=req.getDay();
        Long kid=req.getKid();
        List<Ring>list=new ArrayList<>();
        if(day.equals("today")){
            getTodayMap(kid,list);
        }else if(day.equals("three")){
            getThreeMap(kid,list);
        }else {
            getSevenMap(kid,list);
        }
        return list;
    }

    private void getSevenMap(Long kid, List<Ring> list) {
        dealWithMap(kid,list,TimeStampUtil.get(30),TimeStampUtil.get(0));
    }

    private void getThreeMap(Long kid, List<Ring> list) {
        dealWithMap(kid,list,TimeStampUtil.get(26),TimeStampUtil.get(0));
    }

    private void getTodayMap(Long kid, List<Ring> list) {
        dealWithMap(kid,list,TimeStampUtil.get(24),TimeStampUtil.get(0));
    }

    private void dealWithMap(Long kid, List<Ring> list, Date start, Date end) {
        list.add(new Ring("黑龙江",blogMapper.selectByArea(kid,start,end,"黑龙江")));
        list.add(new Ring("吉林",blogMapper.selectByArea(kid,start,end,"吉林")));
        list.add(new Ring("辽宁",blogMapper.selectByArea(kid,start,end,"辽宁")));
        list.add(new Ring("内蒙古",blogMapper.selectByArea(kid,start,end,"内蒙古")));
        list.add(new Ring("河北",blogMapper.selectByArea(kid,start,end,"河北")));
        list.add(new Ring("天津",blogMapper.selectByArea(kid,start,end,"天津")));
        list.add(new Ring("山东",blogMapper.selectByArea(kid,start,end,"山东")));
        list.add(new Ring("北京",blogMapper.selectByArea(kid,start,end,"北京")));
        list.add(new Ring("山西",blogMapper.selectByArea(kid,start,end,"山西")));
        list.add(new Ring("河南",blogMapper.selectByArea(kid,start,end,"河南")));
        list.add(new Ring("安徽",blogMapper.selectByArea(kid,start,end,"安徽")));
        list.add(new Ring("江苏",blogMapper.selectByArea(kid,start,end,"江苏")));
        list.add(new Ring("上海",blogMapper.selectByArea(kid,start,end,"上海")));
        list.add(new Ring("浙江",blogMapper.selectByArea(kid,start,end,"浙江")));
        list.add(new Ring("宁夏",blogMapper.selectByArea(kid,start,end,"宁夏")));
        list.add(new Ring("陕西",blogMapper.selectByArea(kid,start,end,"陕西")));
        list.add(new Ring("湖北",blogMapper.selectByArea(kid,start,end,"湖北")));
        list.add(new Ring("江西",blogMapper.selectByArea(kid,start,end,"江西")));
        list.add(new Ring("福建",blogMapper.selectByArea(kid,start,end,"福建")));
        list.add(new Ring("台湾",blogMapper.selectByArea(kid,start,end,"台湾")));
        list.add(new Ring("甘肃",blogMapper.selectByArea(kid,start,end,"甘肃")));
        list.add(new Ring("四川",blogMapper.selectByArea(kid,start,end,"四川")));
        list.add(new Ring("重庆",blogMapper.selectByArea(kid,start,end,"重庆")));
        list.add(new Ring("湖南",blogMapper.selectByArea(kid,start,end,"湖南")));
        list.add(new Ring("广东",blogMapper.selectByArea(kid,start,end,"广东")));
        list.add(new Ring("澳门",blogMapper.selectByArea(kid,start,end,"澳门")));
        list.add(new Ring("香港",blogMapper.selectByArea(kid,start,end,"香港")));
        list.add(new Ring("贵州",blogMapper.selectByArea(kid,start,end,"贵州")));
        list.add(new Ring("广西",blogMapper.selectByArea(kid,start,end,"广西")));
        list.add(new Ring("海南",blogMapper.selectByArea(kid,start,end,"海南")));
        list.add(new Ring("青海",blogMapper.selectByArea(kid,start,end,"青海")));
        list.add(new Ring("西藏",blogMapper.selectByArea(kid,start,end,"西藏")));
        list.add(new Ring("新疆",blogMapper.selectByArea(kid,start,end,"新疆")));
        list.add(new Ring("云南",blogMapper.selectByArea(kid,start,end,"云南")));
    }

    private int getSeven(Long kid, List<Tendency> tendencyList) {
        int max=0;
        Tendency t=new Tendency();
        for(int i=30;i>=25;i--){
            int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-1));
            int sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-1));
            int positive=blogMapper.selectByPositive(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-1));
            int nontive=total-sensitive;
            int negative=total-positive;
            t=new Tendency((TimeStampUtil.get(i).getMonth()+1)+"月"+TimeStampUtil.get(i).getDate()+"日",total,sensitive,nontive,positive,negative);
            tendencyList.add(t);
            max=Math.max(total,max);
        }
        int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        int sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        int positive=blogMapper.selectByPositive(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        int nontive=total-sensitive;
        int negative=total-positive;
        t=new Tendency((TimeStampUtil.get(24).getMonth()+1)+"月"+TimeStampUtil.get(24).getDate()+"日",total,sensitive,nontive,positive,negative);
        tendencyList.add(t);
        max=Math.max(total,max);
        return max;
    }
    private int getThree(Long kid, List<Tendency> tendencyList) {
        int max=0;
        Tendency t=new Tendency();
        for(int i=26;i>=25;i--){
            int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-1));
            int sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-1));
            int positive=blogMapper.selectByPositive(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-1));
            int nontive=total-sensitive;
            int negative=total-positive;
            t=new Tendency((TimeStampUtil.get(i).getMonth()+1)+"月"+TimeStampUtil.get(i).getDate()+"日",total,sensitive,nontive,positive,negative);
            tendencyList.add(t);
            max=Math.max(total,max);
        }
        int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        int sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        int positive=blogMapper.selectByPositive(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        int nontive=total-sensitive;
        int negative=total-positive;
        t=new Tendency((TimeStampUtil.get(24).getMonth()+1)+"月"+TimeStampUtil.get(24).getDate()+"日",total,sensitive,nontive,positive,negative);
        tendencyList.add(t);
        max=Math.max(total,max);
        return max;
    }



    private int getToday(Long kid, List<Tendency> tendencyList) {
        int max=0;
        Tendency t=new Tendency("0点",0,0,0,0,0);
        tendencyList.add(t);
        for(int i=24;i>2;i-=2){
            int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-2));
            int sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-2));
            int positive=blogMapper.selectByPositive(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-2));
            int nontive=total-sensitive;
            int negative=total-positive;
            t=new Tendency(TimeStampUtil.get(i-2).getHours()+"点",total,sensitive,nontive,positive,negative);
            tendencyList.add(t);
            max=Math.max(total,max);
        }
        int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(2),TimeStampUtil.get(0));
        int sensitive=blogMapper.selectBySensitive(kid, TimeStampUtil.get(2),TimeStampUtil.get(0));
        int positive=blogMapper.selectByPositive(kid, TimeStampUtil.get(2),TimeStampUtil.get(0));
        int nontive=total-sensitive;
        int negative=total-positive;
        t=new Tendency("24点",total,sensitive,nontive,positive,negative);
        tendencyList.add(t);
        max=Math.max(total,max);
        return max;
    }
}

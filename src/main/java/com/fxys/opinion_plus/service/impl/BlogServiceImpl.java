package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.*;
import com.fxys.opinion_plus.mapper.BlogMapper;
import com.fxys.opinion_plus.resp.Page;
import com.fxys.opinion_plus.service.IBlogService;
import com.fxys.opinion_plus.util.TimeStampUtil;
import com.fxys.opinion_plus.vo.blog.BlogBaseReq;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.*;
import java.util.List;

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
            getToday(kid,tendencyList);
        }else if(day.equals("three")){
            getThree(kid,tendencyList);
        }else {
            getSeven(kid,tendencyList);
        }

        return tendencyList;
    }

    @Override
    public List<SingleTendency> getSingleTendency(BlogBaseReq req) {
        String day=req.getDay();
        Long kid=req.getKid();

        List<SingleTendency>tendencyList=new ArrayList<>();
        if(day.equals("today")){
            getTodaySingleTendency(kid,tendencyList);
        }else if(day.equals("three")){
            getThreeSingleTendency(kid,tendencyList);
        }else {
            getSevenSingleTendency(kid,tendencyList);
        }

        return tendencyList;
    }

    @Override
    public void wordCloud(HttpServletRequest req, HttpServletResponse resp) throws  Exception{
        String day=req.getParameter("day");
        Long kid=Long.valueOf(req.getParameter("kid"));
        List<String>list;
        List<SingleTendency>tendencyList=new ArrayList<>();
        if(day.equals("today")){
            list=blogMapper.selectContent(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        }else if(day.equals("three")){
            list=blogMapper.selectContent(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
        }else {
            list=blogMapper.selectContent(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
        }
        //建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);

        //引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());


        List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load(list);


//        List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load("D:\\project/1.txt");
        //设置图片分辨率
        for(int i=0;i<wordFrequencyList.size();i++) {
//            System.out.println("单词"+wordFrequencyList.get(i).getWord()+"  "+"数量:"+wordFrequencyList.get(i).getFrequency());

            if(wordFrequencyList.get(i).getWord()==")"||wordFrequencyList.get(i).getWord()=="("||wordFrequencyList.get(i).getWord()=="（"||wordFrequencyList.get(i).getWord()=="）"){
                wordFrequencyList.remove(i);
            }

        }
        Dimension dimension = new Dimension(600,600);
        //此处的设置采用内置常量即可，生成词云对象
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        //设置边界及字体
        wordCloud.setPadding(2);
        Font font = new java.awt.Font("STSong-Light", 2, 20);
        //设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
        wordCloud.setKumoFont(new KumoFont(font));

        //设置背景色
        wordCloud.setBackgroundColor(new Color(255,255,255));
        //设置背景图片
        //wordCloud.setBackground(new PixelBoundryBackground("E:\\爬虫/google.jpg"));
        //设置背景图层为圆形
        wordCloud.setBackground(new CircleBackground(255));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));

        //生成词云
        wordCloud.build(wordFrequencyList);

        //把图片对象以流的方式保存出去
        ImageIO.write(wordCloud.getBufferedImage(), "png", resp.getOutputStream());
    }

    @Override
    public List<Ring> getFrequency(BlogBaseReq req) {
        String day=req.getDay();
        Long kid=req.getKid();
        List<String>list;

        if(day.equals("today")){
            list=blogMapper.selectContent(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));
        }else if(day.equals("three")){
            list=blogMapper.selectContent(kid, TimeStampUtil.get(27),TimeStampUtil.get(0));
        }else {
            list=blogMapper.selectContent(kid, TimeStampUtil.get(30),TimeStampUtil.get(0));
        }
        //建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);

        //引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());


        List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load(list);

        List<Ring>lists=new ArrayList<>();
        if(wordFrequencyList.size()>=6){
            for(int i=0;i<6;i++){
                lists.add(new Ring(wordFrequencyList.get(i).getWord(),wordFrequencyList.get(i).getFrequency()));
            }
        }else if(wordFrequencyList.size()>0){
            for(int i=0;i<wordFrequencyList.size();i++){
                lists.add(new Ring(wordFrequencyList.get(i).getWord(),wordFrequencyList.get(i).getFrequency()));
            }
        }
        return lists;
    }

    @Override
    public List<Warning> getWarning(BlogBaseReq req) {
        Warning w=new Warning();
        String day=req.getDay();
        Long kid=req.getKid();
        if(day.equals("today")){
            getTodayWarning(kid,w);
        }else if(day.equals("three")){
            getThreeWarning(kid,w);
        }else {
            getSevenWarning(kid,w);
        }
        List<Warning>list=new ArrayList<>();
        list.add(w);
        return list;
    }

    private void getSevenWarning(Long kid, Warning w) {
        dealWithWarn(kid,w,TimeStampUtil.get(30),TimeStampUtil.get(0));
    }

    private void dealWithWarn(Long kid, Warning w, Date start, Date end) {
        int low=blogMapper.selectByGrade(kid,start,end,1);
        int low_mid=blogMapper.selectByGrade(kid,start,end,2);
        int mid=blogMapper.selectByGrade(kid,start,end,3);
        int mid_hei=blogMapper.selectByGrade(kid,start,end,4);
        int hei=blogMapper.selectByGrade(kid,start,end,5);
        w.setGrade("预警等级");
        w.setLow(low);
        w.setLow_mid(low_mid);
        w.setMid(mid);
        w.setMid_hei(mid_hei);
        w.setHeight(hei);
    }


    private void getThreeWarning(Long kid, Warning w) {
        dealWithWarn(kid,w,TimeStampUtil.get(26),TimeStampUtil.get(0));
    }

    private void getTodayWarning(Long kid, Warning w) {
        dealWithWarn(kid,w,TimeStampUtil.get(24),TimeStampUtil.get(0));
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

    @Override
    public Page<Blog> getByPage(Page<Blog> page) {
        // 查询数据
        List<Blog> aboutList = blogMapper.getByPage(page);
        page.setList(aboutList);
        // 查询总数
        int totalCount = blogMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
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

    private void getSevenSingleTendency(Long kid, List<SingleTendency> tendencyList) {

        SingleTendency t=new SingleTendency();
        for(int i=30;i>=25;i--){
            int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-1));

            t=new SingleTendency((TimeStampUtil.get(i).getMonth()+1)+"月"+TimeStampUtil.get(i).getDate()+"日",total);
            tendencyList.add(t);

        }
        int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));

        t=new SingleTendency((TimeStampUtil.get(24).getMonth()+1)+"月"+TimeStampUtil.get(24).getDate()+"日",total);
        tendencyList.add(t);

    }


    private void getThreeSingleTendency(Long kid, List<SingleTendency> tendencyList) {
        SingleTendency t=new SingleTendency();
        for(int i=26;i>=25;i--){
            int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-1));

            t=new SingleTendency((TimeStampUtil.get(i).getMonth()+1)+"月"+TimeStampUtil.get(i).getDate()+"日",total);
            tendencyList.add(t);

        }
        int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(24),TimeStampUtil.get(0));

        t=new SingleTendency((TimeStampUtil.get(24).getMonth()+1)+"月"+TimeStampUtil.get(24).getDate()+"日",total);
        tendencyList.add(t);
    }


    private void getTodaySingleTendency(Long kid, List<SingleTendency> tendencyList) {
        SingleTendency t=new SingleTendency("0点",0);
        tendencyList.add(t);
        for(int i=24;i>2;i-=2){
            int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(i),TimeStampUtil.get(i-2));

            t=new SingleTendency(TimeStampUtil.get(i-2).getHours()+"点",total);
            tendencyList.add(t);

        }
        int total=blogMapper.selectByCountAll(kid, TimeStampUtil.get(2),TimeStampUtil.get(0));
        t=new SingleTendency("24点",total);
        tendencyList.add(t);
    }
}

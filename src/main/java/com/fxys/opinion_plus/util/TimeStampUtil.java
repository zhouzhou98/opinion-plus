package com.fxys.opinion_plus.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeStampUtil {



    public static Date get(int i){
        List<Date> timeStamp=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        int oneHourStamp=60*60*1000;

        //0点时间戳
        long everyZero =calendar.getTime().getTime();
        //1点
        long everyOne=everyZero+oneHourStamp;
        //2点
        long everyTwo=everyOne+oneHourStamp;
        //3点
        long everyThree=everyTwo+oneHourStamp;
        //4点
        long everyFour=everyThree+oneHourStamp;
        //5点
        long everyFive=everyFour+oneHourStamp;
        //6点
        long everySix=everyFive+oneHourStamp;
        //7点
        long everySeven=everySix+oneHourStamp;
        //8点
        long everyEight=everySeven+oneHourStamp;
        //9点
        long everyNine=everyEight+oneHourStamp;
        //10点
        long everyTen=everyNine+oneHourStamp;
        //11点
        long everyEleven=everyTen+oneHourStamp;
        //12点
        long everyTwelve=everyEleven+oneHourStamp;
        //13点
        long everyThirteen=everyTwelve+oneHourStamp;
        //14点
        long everyFourteen=everyThirteen+oneHourStamp;
        //15点
        long everyFifthteen=everyFourteen+oneHourStamp;
        //16点
        long everySixteen=everyFifthteen+oneHourStamp;
        //17点
        long everySeventeen=everySixteen+oneHourStamp;
        //18点
        long everyEighteen=everySeventeen+oneHourStamp;
        //19点
        long everyNineteen=everyEighteen+oneHourStamp;
        //20点
        long everyTwentierth=everyNineteen+oneHourStamp;
        //21点
        long everyTwentierOne=everyTwentierth+oneHourStamp;
        //22点
        long everyTwentierTwo=everyTwentierOne+oneHourStamp;
        //23点
        long everyTwentierThree=everyTwentierTwo+oneHourStamp;
        //24点
        long everyLast=everyTwentierThree+oneHourStamp;

        //1天的时间戳
        long oneDayStamp=everyLast-everyZero;

        //前一天的零点
        long preOneZero=everyZero-oneDayStamp;


        //前两天的的零点
        long preTwoZero=preOneZero-oneDayStamp;


        //前三天的时间戳
        long preThreeZero=preTwoZero-oneDayStamp;


        //前四天的时间戳
        long preFourZero=preThreeZero-oneDayStamp;

        //前五天的时间戳
        long preFiveZero=preFourZero-oneDayStamp;

        //前六天的时间戳
        long preSixZero=preFiveZero-oneDayStamp;

        //24点
        Date twentierfour=new Date(everyLast);
        timeStamp.add(twentierfour);
        //23点
        Date twentierthree=new Date(everyTwentierThree);
        timeStamp.add(twentierthree);
        //22点
        Date twentiertwoHour=new Date(everyTwentierTwo);
        timeStamp.add(twentiertwoHour);
        //21点
        Date twentieroneHour=new Date(everyTwentierOne);
        timeStamp.add(twentieroneHour);
        //20点
        Date twentierHour=new Date(everyTwentierth);
        timeStamp.add(twentierHour);
        //19点
        Date nineteenHour=new Date(everyNineteen);
        timeStamp.add(nineteenHour);
        //18点
        Date eighteenHour=new Date(everyEighteen);
        timeStamp.add(eighteenHour);
        //17点
        Date seventhteenHour=new Date(everySeventeen);
        timeStamp.add(seventhteenHour);
        //16点
        Date sixthteenHour=new Date(everySixteen);
        timeStamp.add(sixthteenHour);
        //15点
        Date fifthteenHour=new Date(everyFifthteen);
        timeStamp.add(fifthteenHour);
        //14点
        Date fourteenHour=new Date(everyFourteen);
        timeStamp.add(fourteenHour);
        //13点
        Date thirteenHour=new Date(everyThirteen);
        timeStamp.add(thirteenHour);
        //12点
        Date twelveHour=new Date(everyTwelve);
        timeStamp.add(twelveHour);
        //11点
        Date elevenHour=new Date(everyEleven);
        timeStamp.add(elevenHour);
        //10点
        Date tenHour=new Date(everyTen);
        timeStamp.add(tenHour);
        //9点
        Date nineHour=new Date(everyNine);
        timeStamp.add(nineHour);
        //8点
        Date eightHour=new Date(everyEight);
        timeStamp.add(eightHour);
        //7点
        Date sevenHour=new Date(everySeven);
        timeStamp.add(sevenHour);
        //6点
        Date sixHour=new Date(everySix);
        timeStamp.add(sixHour);
        //5点
        Date fiveHour=new Date(everyFive);
        timeStamp.add(fiveHour);
        //4点
        Date fourHour=new Date(everyFour);
        timeStamp.add(fourHour);
        //3点
        Date threeHour=new Date(everyThree);
        timeStamp.add(threeHour);
        //2点
        Date twoHour=new Date(everyTwo);
        timeStamp.add(twoHour);
        //1点
        Date oneHour=new Date(everyOne);
        timeStamp.add(oneHour);
        //0点
        Date zeroHour=new Date(everyZero);
        timeStamp.add(zeroHour);



        //前一天
        Date prevOne=new Date(preOneZero);
        timeStamp.add(prevOne);

        //前两天
        Date prevTwo=new Date(preTwoZero);
        timeStamp.add(prevTwo);

        //前三天
        Date prevThree=new Date(preThreeZero);
        timeStamp.add(prevThree);

        //前四天
        Date prevFour=new Date(preFourZero);
        timeStamp.add(prevFour);
        //前五天
        Date prevFive=new Date(preFiveZero);
        timeStamp.add(prevFive);
        //前六天
        Date prevSix=new Date(preSixZero);
        timeStamp.add(prevSix);




        timeStamp.add(new Date()) ;
        return timeStamp.get(i);
    }

    public static void main(String[] args) {
        for(int i=0;i<=30;i++){
            System.out.println(i+"  "+TimeStampUtil.get(i).toLocaleString());
        }
    }
}

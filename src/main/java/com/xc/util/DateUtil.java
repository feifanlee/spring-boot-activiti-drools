package com.xc.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/13
 * Time: 11:19
 */
public class DateUtil {
    /**
     * @param date
     * @param day 想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
     * @return
     */
    public static Date getSomeDay(Date date, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date getSomeMinutes(Date date, int minutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 日期差天数、小时、分钟、秒数组
     * @param startDate
     * @param endDate
     * @return
     */
    public static long[] getDisTime(Date startDate, Date endDate){
        long timesDis = Math.abs(startDate.getTime() - endDate.getTime());
        long day = timesDis / (1000 * 60 * 60 * 24);
        long hour = timesDis / (1000 * 60 * 60) - day * 24;
        long min = timesDis / (1000 * 60) - day * 24 * 60 - hour * 60;
        long sec = timesDis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        return new long[]{day, hour, min, sec};
    }

    /**
     * 日期差天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDisDay(Date startDate, Date endDate){
        long[] dis = getDisTime(startDate, endDate);
        long day = dis[0];
        if (dis[1] > 0 || dis[2] > 0 || dis[3] > 0) {
            day += 1;
        }
        return day;
    }

    /**
     * 日期差文字描述
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDisTimeStr(Date startDate, Date endDate){
        long[] dis = getDisTime(startDate, endDate);
        return new StringBuilder().append(dis[0]).append("天").append(dis[1]).append("小时").append(dis[2]).append("分钟")
                .append(dis[3]).append("秒").toString();
    }
}

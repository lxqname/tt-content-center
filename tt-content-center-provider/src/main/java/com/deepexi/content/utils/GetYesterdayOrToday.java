package com.deepexi.content.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/26 10:02
 * @description：获取昨日日期
 * @version: 1.0.0
 */
public class GetYesterdayOrToday {

    /**
     * 日期格式
     */
    private static final String DATE_PATTERN="yyyy-MM-dd";

    /**
     * @Description:获取昨天日期字符串形式
     * @Param: []
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/10/10 9:59
     */
    public static String getYesterday(){

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Calendar c = Calendar.getInstance();
        //-1.昨天时间 0.当前时间 1.明天时间 *以此类推
        c.add(Calendar.DATE, -1);
        String yesterday = sdf.format(c.getTime());
        return yesterday;
    }


    /**
     * @Description:获取今天日期字符串形式
     * @Param: []
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/10/10 10:00
     */
    public static String getToday(){

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date date = new Date();
        String today = sdf.format(date);
        return today;
    }


    /**
     * @Description: 日期格式转换
     * @Param: [date]
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/10/10 10:00
     */
    public static String transForm(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        String dateStr = sdf.format(date);
        return dateStr;
    }

}

package com.zwb.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.utils
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-22 11:28
 * @Description: TODO
 */
public class TimeUtls {

    public static String getNow(){
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return f.format(new Date());
    }
    /**
     * 按照参数format的格式，日期转字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }
    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date) {
        //return date2Str(date, "yyyy-MM-dd HH:mm:ss");
        return date2Str(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }
    public static String date2Stryb(Date date) {
        //return date2Str(date, "yyyy-MM-dd HH:mm:ss");
        return date2Str(date, "yyyyMMdd");
    }
    public static String date2Strybs(Date date) {
        //return date2Str(date, "yyyy-MM-dd HH:mm:ss");
        return date2Str(date, "HH");
    }
    public static String date2Strs(Date date) {
        //return date2Str(date, "yyyy-MM-dd HH:mm:ss");
        return date2Str(date, "yyyy-MM-dd");
    }
    public static String date2Strss(Date date) {
        //return date2Str(date, "yyyy-MM-dd HH:mm:ss");
        return date2Str(date, "yyyy-MM-dd HH:mm");
    }
    public static String yubao(int i) {
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MINUTE,i);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmm");
        String dateString1 = formatter1.format(date);
        return dateString1;
    }
    public static String date082Strs(int i) {
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
        String dateString1 = formatter1.format(date);
        return dateString1;
    }
}

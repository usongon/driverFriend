package com.usongon.driverFriend.common.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtil {

    /**
     * 日期时间字符串转LocalDateTime
     * @param strDateTime
     * @return
     */
    public static LocalDateTime parserLocalDateTime(String strDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(strDateTime, df);
    }

    /**
     * 日期时间转字符串
     * @param time
     * @return
     */
    public static String localDateTime2Str(LocalDateTime time) {
        return localDateTime2Str(time, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 日期时间转字符串
     * @param time
     * @return
     */
    public static String localDateTime2Str(LocalDateTime time, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(time);
    }

    /**
     * 通用列表时间格式
     * @param time
     * @return
     */
    public static String localDateTime2CommonListFormat(LocalDateTime time) {
        String str = localDateTime2Str(time, "yyyy年MM月dd日 HH:mm");
        return str.substring(2);
    }


    /**
     * 当前年月日
     * @return
     */
    public static String getNowYmd() {
        return localDateTime2Str(LocalDateTime.now(), "yyyyMMdd");
    }

}

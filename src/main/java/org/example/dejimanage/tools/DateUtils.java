package org.example.dejimanage.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//日期时间格式处理工具类
public class DateUtils {
    private static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_MONTH_FORMAT = new SimpleDateFormat("dd");
    private static final SimpleDateFormat DATE_MONTH_DAY_FORMAT = new SimpleDateFormat("MM-dd");

    /***
     * 返回整理后的时间格式
     * @param date 原始日期
     * @return 整理后：yyyy-MM-dd HH:mm:ss 时间的格式
     */
    public static String formatDefault(Date date) {
        return date != null ? DEFAULT_FORMAT.format(date) : null;
    }

    /***
     * 返回整理后的时间格式
     * @param date 原始日期
     * @return 整理后：yyyy-MM-dd 时间的格式
     */
    public static String formatDateOnly(Date date) {
        return date != null ? DATE_ONLY_FORMAT.format(date) : null;
    }

    /***
     * 返回整理后的时间格式
     * @param date 原始日期
     * @return 整理后：dd 时间的格式
     */
    public static String formatDateMonth(Date date) {
        return date != null ? DATE_MONTH_FORMAT.format(date) : null;
    }

    /***
     * 返回整理后的时间格式
     * @param date 原始日期
     * @return 整理后：MM-dd 时间的格式
     */
    public static String formatDateMonthDay(Date date) {
        return date != null ? DATE_MONTH_DAY_FORMAT.format(date) : null;
    }

    /**
     * 计算给定日期前n天的日期
     * @param date 原始日期
     * @param daysBefore 要回溯的天数
     * @return 计算后的日期 MM-dd 时间的格式
     */
    public static String calculateDateBefore(Date date, int daysBefore) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -daysBefore);
        return formatDateMonth(calendar.getTime());
    }
}

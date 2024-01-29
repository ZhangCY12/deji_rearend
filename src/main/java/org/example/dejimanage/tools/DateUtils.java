package org.example.dejimanage.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

//时间处理工具类
public class DateUtils {
    private static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDefault(Date date) {
        return date != null ? DEFAULT_FORMAT.format(date) : null;
    }

    public static String formatDateOnly(Date date) {
        return date != null ? DATE_ONLY_FORMAT.format(date) : null;
    }

    // 你可以根据需要添加更多的格式化方法
}

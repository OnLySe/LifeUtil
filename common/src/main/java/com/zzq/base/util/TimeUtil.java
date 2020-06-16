package com.zzq.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private TimeUtil() {

    }

    private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static Date sDate = new Date();

    public static String format(long time) {
        sDate.setTime(time);
        return sFormat.format(sDate);
    }
}

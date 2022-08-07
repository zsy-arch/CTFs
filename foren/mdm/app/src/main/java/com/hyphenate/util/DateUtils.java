package com.hyphenate.util;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DateUtils {
    private static final long INTERVAL_IN_MILLISECONDS = 30000;

    @SuppressLint({"SimpleDateFormat"})
    public static Date StringToDate(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TimeInfo getBeforeYesterdayStartAndEndTime() {
        Calendar instance = Calendar.getInstance();
        instance.add(5, -2);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long time = instance.getTime().getTime();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(5, -2);
        instance2.set(11, 23);
        instance2.set(12, 59);
        instance2.set(13, 59);
        instance2.set(14, 999);
        long time2 = instance2.getTime().getTime();
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setStartTime(time);
        timeInfo.setEndTime(time2);
        return timeInfo;
    }

    public static TimeInfo getCurrentMonthStartAndEndTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(5, 1);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long time = instance.getTime().getTime();
        long time2 = Calendar.getInstance().getTime().getTime();
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setStartTime(time);
        timeInfo.setEndTime(time2);
        return timeInfo;
    }

    public static TimeInfo getLastMonthStartAndEndTime() {
        Calendar instance = Calendar.getInstance();
        instance.add(2, -1);
        instance.set(5, 1);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long time = instance.getTime().getTime();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(2, -1);
        instance2.set(5, 1);
        instance2.set(11, 23);
        instance2.set(12, 59);
        instance2.set(13, 59);
        instance2.set(14, 999);
        instance2.roll(5, -1);
        long time2 = instance2.getTime().getTime();
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setStartTime(time);
        timeInfo.setEndTime(time2);
        return timeInfo;
    }

    public static String getTimestampStr() {
        return Long.toString(System.currentTimeMillis());
    }

    public static String getTimestampString(Date date) {
        String str;
        boolean startsWith = Locale.getDefault().getLanguage().startsWith("zh");
        long time = date.getTime();
        if (isSameDay(time)) {
            str = startsWith ? "aa hh:mm" : "hh:mm aa";
        } else if (!isYesterday(time)) {
            str = startsWith ? "M月d日aa hh:mm" : "MMM dd hh:mm aa";
        } else if (!startsWith) {
            return "Yesterday " + new SimpleDateFormat("hh:mm aa", Locale.ENGLISH).format(date);
        } else {
            str = "昨天aa hh:mm";
        }
        return startsWith ? new SimpleDateFormat(str, Locale.CHINESE).format(date) : new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    public static TimeInfo getTodayStartAndEndTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long time = instance.getTime().getTime();
        Calendar instance2 = Calendar.getInstance();
        instance2.set(11, 23);
        instance2.set(12, 59);
        instance2.set(13, 59);
        instance2.set(14, 999);
        long time2 = instance2.getTime().getTime();
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setStartTime(time);
        timeInfo.setEndTime(time2);
        return timeInfo;
    }

    public static TimeInfo getYesterdayStartAndEndTime() {
        Calendar instance = Calendar.getInstance();
        instance.add(5, -1);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long time = instance.getTime().getTime();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(5, -1);
        instance2.set(11, 23);
        instance2.set(12, 59);
        instance2.set(13, 59);
        instance2.set(14, 999);
        long time2 = instance2.getTime().getTime();
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setStartTime(time);
        timeInfo.setEndTime(time2);
        return timeInfo;
    }

    public static boolean isCloseEnough(long j, long j2) {
        long j3 = j - j2;
        if (j3 < 0) {
            j3 = -j3;
        }
        return j3 < INTERVAL_IN_MILLISECONDS;
    }

    private static boolean isSameDay(long j) {
        TimeInfo todayStartAndEndTime = getTodayStartAndEndTime();
        return j > todayStartAndEndTime.getStartTime() && j < todayStartAndEndTime.getEndTime();
    }

    private static boolean isYesterday(long j) {
        TimeInfo yesterdayStartAndEndTime = getYesterdayStartAndEndTime();
        return j > yesterdayStartAndEndTime.getStartTime() && j < yesterdayStartAndEndTime.getEndTime();
    }

    @SuppressLint({"DefaultLocale"})
    public static String toTime(int i) {
        int i2 = i / 1000;
        int i3 = i2 / 60;
        if (i3 >= 60) {
            int i4 = i3 / 60;
            i3 %= 60;
        }
        return String.format("%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2 % 60));
    }

    @SuppressLint({"DefaultLocale"})
    public static String toTimeBySecond(int i) {
        int i2 = i / 60;
        if (i2 >= 60) {
            int i3 = i2 / 60;
            i2 %= 60;
        }
        return String.format("%02d:%02d", Integer.valueOf(i2), Integer.valueOf(i % 60));
    }
}

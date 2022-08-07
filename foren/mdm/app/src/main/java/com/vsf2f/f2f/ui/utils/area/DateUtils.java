package com.vsf2f.f2f.ui.utils.area;

import android.support.annotation.NonNull;
import com.autonavi.ae.guide.GuideControl;
import com.hy.frame.util.MyLog;
import com.umeng.analytics.a;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DateUtils extends android.text.format.DateUtils {

    /* loaded from: classes2.dex */
    public enum DifferenceMode {
        Second,
        Minute,
        Hour,
        Day
    }

    public static long calculateDifferentSecond(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, DifferenceMode.Second);
    }

    public static long calculateDifferentMinute(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, DifferenceMode.Minute);
    }

    public static long calculateDifferentHour(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, DifferenceMode.Hour);
    }

    public static long calculateDifferentDay(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, DifferenceMode.Day);
    }

    public static long calculateDifferentSecond(long startTimeMillis, long endTimeMillis) {
        return calculateDifference(startTimeMillis, endTimeMillis, DifferenceMode.Second);
    }

    public static long calculateDifferentMinute(long startTimeMillis, long endTimeMillis) {
        return calculateDifference(startTimeMillis, endTimeMillis, DifferenceMode.Minute);
    }

    public static long calculateDifferentHour(long startTimeMillis, long endTimeMillis) {
        return calculateDifference(startTimeMillis, endTimeMillis, DifferenceMode.Hour);
    }

    public static long calculateDifferentDay(long startTimeMillis, long endTimeMillis) {
        return calculateDifference(startTimeMillis, endTimeMillis, DifferenceMode.Day);
    }

    public static long calculateDifference(long startTimeMillis, long endTimeMillis, DifferenceMode mode) {
        return calculateDifference(new Date(startTimeMillis), new Date(endTimeMillis), mode);
    }

    public static long calculateDifference(Date startDate, Date endDate, DifferenceMode mode) {
        long[] different = calculateDifference(startDate, endDate);
        if (mode.equals(DifferenceMode.Minute)) {
            return different[2];
        }
        if (mode.equals(DifferenceMode.Hour)) {
            return different[1];
        }
        if (mode.equals(DifferenceMode.Day)) {
            return different[0];
        }
        return different[3];
    }

    public static long[] calculateDifference(Date startDate, Date endDate) {
        return calculateDifference(endDate.getTime() - startDate.getTime());
    }

    public static long[] calculateDifference(long differentMilliSeconds) {
        long minutesInMilli = 1000 * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = differentMilliSeconds / daysInMilli;
        long differentMilliSeconds2 = differentMilliSeconds % daysInMilli;
        long elapsedHours = differentMilliSeconds2 / hoursInMilli;
        long differentMilliSeconds3 = differentMilliSeconds2 % hoursInMilli;
        long elapsedMinutes = differentMilliSeconds3 / minutesInMilli;
        long differentMilliSeconds4 = differentMilliSeconds3 % minutesInMilli;
        long elapsedSeconds = differentMilliSeconds4 / 1000;
        MyLog.d(String.format(Locale.CHINA, "different: %d ms, %d days, %d hours, %d minutes, %d seconds", Long.valueOf(differentMilliSeconds4), Long.valueOf(elapsedDays), Long.valueOf(elapsedHours), Long.valueOf(elapsedMinutes), Long.valueOf(elapsedSeconds)));
        return new long[]{elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds};
    }

    public static long checkDate(int year, int month, int day, int limit) throws ParseException {
        Date date = new Date(year - 1900, month, day, 23, 59, 59);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        long targetTime = dateFormat.parse(dateFormat.format(date)).getTime();
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        long disrtance = (targetTime - cal.getTimeInMillis()) / a.j;
        if (disrtance >= limit || disrtance < 0) {
            return -1L;
        }
        return targetTime;
    }

    public static int calculateDaysInMonth(int month) {
        return calculateDaysInMonth(0, month);
    }

    public static int calculateDaysInMonth(int year, int month) {
        String[] littleMonths = {"4", "6", GuideControl.CHANGE_PLAY_TYPE_LZL_COMMON, "11"};
        List<String> bigList = Arrays.asList("1", "3", "5", "7", "8", "10", "12");
        List<String> littleList = Arrays.asList(littleMonths);
        if (bigList.contains(String.valueOf(month))) {
            return 31;
        }
        if (littleList.contains(String.valueOf(month))) {
            return 30;
        }
        if (year <= 0) {
            return 29;
        }
        if ((year % 4 != 0 || year % 100 == 0) && year % 400 != 0) {
            return 28;
        }
        return 29;
    }

    @NonNull
    public static String fillZero(int number) {
        return number < 10 ? "0" + number : "" + number;
    }

    public static boolean isSameDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        Calendar nowCalendar = Calendar.getInstance();
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(date);
        return nowCalendar.get(0) == newCalendar.get(0) && nowCalendar.get(1) == newCalendar.get(1) && nowCalendar.get(6) == newCalendar.get(6);
    }

    public static Date parseDate(String dateStr, String dataFormat) {
        try {
            return new Date(new SimpleDateFormat(dataFormat).parse(dateStr).getTime());
        } catch (Exception e) {
            MyLog.w(e);
            return null;
        }
    }

    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static long parseLongDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss").getTime();
    }

    public static String getNowDateStr(String dateFormatStr) {
        if (dateFormatStr == null) {
            dateFormatStr = "yyyyMMddHHmmss";
        }
        return new SimpleDateFormat(dateFormatStr).format(new Date(System.currentTimeMillis()));
    }

    public static String getNowDateStr() {
        return getNowDateStr("yyyyMMddHHmmss");
    }
}

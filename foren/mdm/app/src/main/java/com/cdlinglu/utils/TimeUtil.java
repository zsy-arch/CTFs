package com.cdlinglu.utils;

import com.ta.utdid2.android.utils.TimeUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public class TimeUtil {
    public static String formatDisplayTime(String time, String pattern) {
        String display = "";
        int tHour = 60000 * 60;
        int tDay = tHour * 24;
        if (time != null) {
            try {
                Date tDate = new SimpleDateFormat(pattern).parse(time);
                Date today = new Date();
                if (tDate != null) {
                    SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                    if (tDate.before(new Date(thisYearDf.parse(thisYearDf.format(today)).getTime()))) {
                        display = new SimpleDateFormat("yyyy年MM月dd日").format(tDate);
                    } else {
                        SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
                        Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                        Date beforeYes = new Date(yesterday.getTime() - tDay);
                        long dTime = today.getTime() - tDate.getTime();
                        if (dTime < 60000) {
                            display = "刚刚";
                        } else if (dTime < tHour) {
                            display = ((int) Math.ceil(dTime / 60000)) + "分钟前";
                        } else if (dTime < tDay && tDate.after(yesterday)) {
                            display = ((int) Math.ceil(dTime / tHour)) + "小时前";
                        } else if (!tDate.after(beforeYes) || !tDate.before(yesterday)) {
                            display = new SimpleDateFormat("MM月dd日").format(tDate);
                        } else {
                            display = "昨天" + new SimpleDateFormat("HH:mm").format(tDate);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return display;
    }

    public static String formatChatTime(String time, String pattern) {
        String display = "";
        if (time != null) {
            try {
                Date tDate = new SimpleDateFormat(pattern).parse(time);
                Date today = new Date();
                if (tDate != null) {
                    SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
                    if (todayDf.format(tDate).equals(todayDf.format(today))) {
                        display = new SimpleDateFormat("HH:mm").format(tDate);
                    } else {
                        SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                        Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                        Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                        Date beforeYes = new Date(yesterday.getTime() - ((long) TimeUtils.TOTAL_M_S_ONE_DAY));
                        if (tDate.before(thisYear)) {
                            display = new SimpleDateFormat("yyyy年MM月dd日").format(tDate);
                        } else if (!tDate.after(beforeYes) || !tDate.before(yesterday)) {
                            display = new SimpleDateFormat("MM月dd日 HH:mm").format(tDate);
                        } else {
                            display = "昨天 " + new SimpleDateFormat("HH:mm").format(tDate);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return display;
    }

    public static String toMinbyMs(int s) {
        return toMinbyS(s / 1000);
    }

    public static String toMinbyS(int s) {
        int var1 = 0;
        int var2 = s;
        if (s > 60) {
            var1 = s / 60;
            var2 = s % 60;
        }
        return String.format("%02d:%02d", Integer.valueOf(var1), Integer.valueOf(var2));
    }

    public static String formatDisplayTime(long ltime, String pattern) {
        return formatDisplayTime(getDateTime(ltime, pattern), pattern);
    }

    public static String formatChatTime(long ltime) {
        return formatChatTime(getDateTime(ltime, "yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm");
    }

    public static String getNowHour() {
        return new SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
    }

    public static String getNowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
    }

    public static String getDateTime(long ltime) {
        return getDateTime(ltime, null);
    }

    public static String getDateTime(long ltime, String type) {
        if ((ltime + "").length() == 10) {
            ltime *= 1000;
        }
        if (type == null) {
            type = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(type, Locale.CHINA).format(new Date(ltime));
    }

    public static long getLongTime(String strTime, String type) {
        if (type == null) {
            type = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            return new SimpleDateFormat(type).parse(strTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }
}

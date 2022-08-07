package com.amap.api.col;

import android.content.Context;
import android.os.Environment;
import android.util.TypedValue;
import com.amap.api.col.gj;
import com.amap.api.navi.model.NaviLatLng;
import com.tencent.open.utils.SystemUtils;
import java.io.File;

/* compiled from: NaviUtil.java */
/* loaded from: classes.dex */
public class fn {
    public static boolean a = false;
    public static boolean b = false;
    private static String[] c = {"com.amap.api.navi", "com.autonavi.tbt", "com.autonavi.wtbt", "com.autonavi.rbt", "com.autonavi.ae.guide", "com.autonavi.ae.route", "com.autonavi.ae.pos"};

    public static gj a() {
        try {
            return new gj.a("navi", SystemUtils.QQ_VERSION_NAME_5_1_0, "AMAP_SDK_Android_NAVI_5.1.0").a(c).a();
        } catch (fz e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void a(Throwable th) {
        th.printStackTrace();
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append("exception->");
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append("@").append(stackTraceElement.getFileName());
            sb.append(stackTraceElement.getLineNumber()).append("#");
            sb.append(stackTraceElement.getClassName()).append("#");
            sb.append(stackTraceElement.getMethodName());
        }
    }

    public static File a(Context context) {
        File externalFilesDir;
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                externalFilesDir = Environment.getExternalStorageDirectory();
                if (!externalFilesDir.canWrite()) {
                    externalFilesDir = context.getExternalFilesDir("LBS");
                }
            } else {
                externalFilesDir = context.getExternalFilesDir("LBS");
            }
            fr.c("使用目录" + externalFilesDir.getAbsolutePath());
            return externalFilesDir;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int a(Context context, int i) {
        if (i == 0) {
            return 0;
        }
        if (context == null) {
            return i;
        }
        try {
            return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static String a(int i) {
        if (i == 0) {
            return "0米";
        }
        if (i < 100) {
            return i + "米";
        }
        if (100 <= i && i < 1000) {
            return i + "米";
        }
        if (1000 <= i && i < 10000) {
            return (((i / 10) * 10) / 1000.0d) + "公里";
        }
        if (10000 > i || i >= 100000) {
            return (i / 1000) + "公里";
        }
        return (((i / 100) * 100) / 1000.0d) + "公里";
    }

    public static String a(int i, String str, String str2) {
        if (i == 0) {
            return "<font color='" + str + "' ><B>0</B></font><font color ='" + str2 + "'>米</font>";
        }
        if (i < 100) {
            return "<font color='" + str + "'><B>" + i + "</B></font><font color ='" + str2 + "'>米</font>";
        }
        if (100 <= i && i < 1000) {
            return "<font color='" + str + "'><B>" + ((i / 10) * 10) + "</B></font><font color ='" + str2 + "'>米</font>";
        }
        if (1000 <= i && i < 10000) {
            return "<font color='" + str + "'><B>" + (((i / 10) * 10) / 1000.0d) + "</B></font><font color ='" + str2 + "'>公里</font>";
        }
        if (10000 > i || i >= 100000) {
            return "<font color='" + str + "'><B>" + (i / 1000) + "</B></font><font color ='" + str2 + "'>公里</font>";
        }
        return "<font color='" + str + "'><B>" + (((i / 100) * 100) / 1000.0d) + "</B></font><font color ='" + str2 + "'>公里</font>";
    }

    public static String a(String str) {
        String str2;
        String str3;
        if (str == null) {
            return "";
        }
        String[] split = str.split(":");
        StringBuffer stringBuffer = new StringBuffer();
        if (split != null && split.length > 2) {
            if (!"00".equals(split[0])) {
                if (split[0].indexOf("0") != -1) {
                    int indexOf = split[0].indexOf("0");
                    if (indexOf + 1 < split[0].length()) {
                        str3 = split[0].substring(indexOf + 1);
                    } else {
                        str3 = split[0];
                    }
                    stringBuffer.append(str3 + "小时");
                } else {
                    stringBuffer.append(split[0] + "小时");
                }
            }
            if (!"00".equals(split[1])) {
                if (split[1].indexOf("0") != -1) {
                    int indexOf2 = split[1].indexOf("0");
                    if (indexOf2 + 1 < split[1].length()) {
                        str2 = split[1].substring(indexOf2 + 1);
                    } else {
                        str2 = split[1];
                    }
                    stringBuffer.append(str2 + "分钟");
                } else {
                    stringBuffer.append(split[1] + "分钟");
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String a(String str, String str2, String str3) {
        String str4;
        String str5;
        if (str == null) {
            return "";
        }
        String[] split = str.split(":");
        StringBuffer stringBuffer = new StringBuffer();
        if (split != null && split.length > 2) {
            if (!"00".equals(split[0])) {
                if (split[0].indexOf("0") != -1) {
                    int indexOf = split[0].indexOf("0");
                    if (indexOf != 0 || indexOf + 1 >= split[0].length()) {
                        str5 = split[0];
                    } else {
                        str5 = split[0].substring(indexOf + 1);
                    }
                    stringBuffer.append("<font color='" + str2 + "' ><B>" + str5 + "</B></font><font color ='" + str3 + "'>小时</font>");
                } else {
                    stringBuffer.append("<font color='" + str2 + "' ><B>" + split[0] + "</B></font><font color ='" + str3 + "'>小时</font>");
                }
            }
            if (!"00".equals(split[1])) {
                if (split[1].indexOf("0") != -1) {
                    int indexOf2 = split[1].indexOf("0");
                    if (indexOf2 + 1 < split[1].length()) {
                        str4 = split[1].substring(indexOf2 + 1);
                    } else {
                        str4 = split[1];
                    }
                    stringBuffer.append("<font color='" + str2 + "' ><B>" + str4 + "</B></font><font color ='" + str3 + "'>分钟</font>");
                } else {
                    stringBuffer.append("<font color='" + str2 + "' ><B>" + split[1] + "</B></font><font color ='" + str3 + "'>分钟</font>");
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String b(int i) {
        int abs = Math.abs(i);
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = abs / 3600;
        if (i2 == 0) {
            stringBuffer.append("00:");
        }
        if (i2 > 0) {
            stringBuffer.append(c(i2) + ":");
        }
        int i3 = abs % 3600;
        stringBuffer.append(c((i3 + 59) / 60) + ":");
        stringBuffer.append(c(i3 % 60));
        return stringBuffer.toString();
    }

    private static String c(int i) {
        return i < 10 ? "0" + i : "" + i;
    }

    public static NaviLatLng a(double d, double d2, double d3, double d4) {
        double d5;
        double d6 = 0.0d;
        if (d <= 0.0d || d2 <= 0.0d || d3 <= 0.0d || d4 <= 0.0d) {
            d5 = 0.0d;
        } else {
            d5 = (d + d3) / 2.0d;
            d6 = (d2 + d4) / 2.0d;
        }
        return new NaviLatLng(d5, d6);
    }

    public static NaviLatLng a(NaviLatLng naviLatLng, NaviLatLng naviLatLng2, double d) {
        NaviLatLng naviLatLng3 = new NaviLatLng();
        double a2 = d / a(naviLatLng, naviLatLng2);
        naviLatLng3.setLatitude(((naviLatLng2.getLatitude() - naviLatLng.getLatitude()) * a2) + naviLatLng.getLatitude());
        naviLatLng3.setLongitude((a2 * (naviLatLng2.getLongitude() - naviLatLng.getLongitude())) + naviLatLng.getLongitude());
        return naviLatLng3;
    }

    public static int a(NaviLatLng naviLatLng, NaviLatLng naviLatLng2) {
        return b(naviLatLng.getLongitude(), naviLatLng.getLatitude(), naviLatLng2.getLongitude(), naviLatLng2.getLatitude());
    }

    private static int b(double d, double d2, double d3, double d4) {
        double d5 = 0.01745329251994329d * d;
        double d6 = 0.01745329251994329d * d2;
        double d7 = 0.01745329251994329d * d3;
        double d8 = 0.01745329251994329d * d4;
        double sin = Math.sin(d5);
        double sin2 = Math.sin(d6);
        double cos = Math.cos(d5);
        double cos2 = Math.cos(d6);
        double sin3 = Math.sin(d7);
        double sin4 = Math.sin(d8);
        double cos3 = Math.cos(d7);
        double cos4 = Math.cos(d8);
        double[] dArr = {(cos * cos2) - (cos3 * cos4), (cos2 * sin) - (cos4 * sin3), sin2 - sin4};
        return (int) (Math.asin(Math.sqrt(((dArr[0] * dArr[0]) + (dArr[1] * dArr[1])) + (dArr[2] * dArr[2])) / 2.0d) * 1.27420015798544E7d);
    }

    public static int b(Context context, int i) {
        if (context == null) {
            return i;
        }
        try {
            return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }
}

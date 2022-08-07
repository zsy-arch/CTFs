package com.hy.frame.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.hyphenate.util.EMPrivateConstant;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpHost;

/* loaded from: classes.dex */
public class HyUtil {
    private static long lastTime = 0;
    public static final String[] weeks = new String[0];

    public static boolean isMobile(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$").matcher(str).matches();
    }

    public static String hideMobile(String str) {
        if (str == null) {
            return "";
        }
        return str.length() == 11 ? str.substring(0, 4) + "***" + str.substring(7, str.length()) : str;
    }

    public static String cutMobile(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("\\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{11}").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static String cutUrl(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("\\b(([\\w-]+://?|www[.])[^\\s()<>]+(?:[\\w\\d]+[\\w\\d]+|([^[:punct:]\\s]|/)))").matcher(str);
        if (!matcher.find()) {
            return null;
        }
        String url = matcher.group();
        if (!url.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            return "http://" + url;
        }
        return url;
    }

    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }

    public static boolean isEnglish(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("[a-zA-Z]+").matcher(str).matches();
    }

    public static boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("^[一-龥]+$").matcher(str).matches();
    }

    public static boolean isD(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("^[一-龥]+$").matcher(str).matches();
    }

    public static boolean hasChinese(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("[一-龥]").matcher(str).find();
    }

    public static boolean hasSpecialChar(String str) {
        return !TextUtils.isEmpty(str) && !Pattern.compile("[a-zA-Z0-9_‘’，,。；;：:！!@#￥\\(\\)（）\\u4e00-\\u9fa5]").matcher(str).find();
    }

    public static boolean isIpAddress(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}").matcher(str).matches();
    }

    public static boolean isIdentity(String str) {
        if (isEmpty(str)) {
            return false;
        }
        if (str.length() == 15) {
            return Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$").matcher(str).matches();
        }
        if (str.contains(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME)) {
            str = str.replaceAll(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME, "X");
        }
        if (str.length() == 18) {
            return Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$").matcher(str).matches();
        }
        return false;
    }

    public static boolean isPhone(String str) {
        if (str == null) {
            return false;
        }
        boolean b = false;
        String str2 = str.replaceAll("-", "");
        if (str2.length() == 11) {
            b = Pattern.compile("^[0][1-9]{2,3}[0-9]{5,10}$").matcher(str2).matches();
        } else if (str2.length() <= 9) {
            b = Pattern.compile("^[1-9]{1}[0-9]{5,8}$").matcher(str2).matches();
        }
        if (!b) {
            return isMobile(str2);
        }
        return b;
    }

    public static boolean isEmail(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(str).matches();
    }

    public static boolean isBankCard(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return Pattern.compile("^(\\d{16}|\\d{19})$").matcher(str).matches();
    }

    public static boolean isNoEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0 || str.indexOf(f.b) == 0;
    }

    public static boolean isNoEmpty(List<?> datas) {
        return !isEmpty(datas);
    }

    public static boolean isEmpty(List<?> datas) {
        return datas == null || datas.size() == 0;
    }

    public static String removeNumberZero(String str) {
        if (isEmpty(str)) {
            return "0";
        }
        if (str.indexOf(".") > 0) {
            return str.replaceAll("0+?$", "").replaceAll("[.]$", "");
        }
        return str;
    }

    public static String formatToMoney(Double money) {
        return new DecimalFormat("0.00").format(money);
    }

    public static String formatToMoney(String str) {
        try {
            return formatToMoney(Double.valueOf(str));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            MyLog.e("toMoney", "转浮点数失败");
            return str;
        }
    }

    public static String formatMoney(Double money) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setGroupingUsed(false);
        return nf.format(money);
    }

    public static Double formatMoney2(Double money) {
        return Double.valueOf(new BigDecimal(money.doubleValue()).setScale(2, 4).doubleValue());
    }

    public static float floatToSpDimension(float value, Context context) {
        return value / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static <T> T getView(View v, int resId) {
        return (T) v.findViewById(resId);
    }

    public static String getNowTime() {
        return new SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
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

    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        return (int) ((spValue * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int getViewMeasuredHeight(View view) {
        calcViewMeasure(view);
        return view.getMeasuredHeight();
    }

    public static int getViewMeasuredWidth(View view) {
        calcViewMeasure(view);
        return view.getMeasuredWidth();
    }

    public static void calcViewMeasure(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    public static PackageInfo getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            MyLog.e("VersionInfo|Exception:" + e);
            return null;
        }
    }

    public static boolean isInstalled(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(packageName, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        return new AppShare(context.getApplicationContext()).getInt(Constant.NET_STATUS) > -1;
    }

    public static boolean isFastClick() {
        long curTime = System.currentTimeMillis();
        if (curTime - lastTime < 800) {
            return true;
        }
        lastTime = curTime;
        return false;
    }

    public static boolean isCarNumber(String str) {
        if (isNoEmpty(str)) {
            return Pattern.compile("^[一-龥|A-Z]{1}[A-Z]{1}[A-Z_0-9]{5}$").matcher(str).matches();
        }
        return false;
    }

    public static String getWeekName(int week) {
        switch (week) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return null;
        }
    }

    public static String getProcessName(Context cxt, int pid) {
        List<ActivityManager.RunningAppProcessInfo> runningApps = ((ActivityManager) cxt.getSystemService("activity")).getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    public static void printException(Exception e) {
        if (e != null) {
            MyLog.e(e.getMessage());
            for (StackTraceElement ste : e.getStackTrace()) {
                MyLog.e(ste);
            }
        }
    }
}

package com.vsf2f.f2f.jpush;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class JPushUtil {
    public static final String KEY_APP_KEY = "JPUSH_APPKEY";
    public static final String PREFS_DAYS = "JPUSH_EXAMPLE_DAYS";
    public static final String PREFS_END_TIME = "PREFS_END_TIME";
    public static final String PREFS_NAME = "JPUSH_EXAMPLE";
    public static final String PREFS_START_TIME = "PREFS_START_TIME";

    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0 || s.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isValidTagAndAlias(String s) {
        return Pattern.compile("^[一-龥0-9a-zA-Z_!@#$&*+=.|￥¥]+$").matcher(s).matches();
    }

    public static String getAppKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (ai != null) {
                metaData = ai.metaData;
            }
            if (!(metaData == null || (appKey = metaData.getString(KEY_APP_KEY)) == null)) {
                if (appKey.length() == 24) {
                    return appKey;
                }
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            return appKey;
        }
    }

    public static String GetVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return f.c;
        }
    }

    public static String getDeviceId(Context context) {
        return JPushInterface.getUdid(context);
    }

    public static void showToast(final String toast, final Context context) {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.jpush.JPushUtil.1
            @Override // java.lang.Runnable
            public void run() {
                Looper.prepare();
                Toast.makeText(context, toast, 0).show();
                Looper.loop();
            }
        });
    }

    public static boolean isConnected(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public static String getImei(Context context, String imei) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            Log.e(JPushUtil.class.getSimpleName(), e.getMessage());
            return imei;
        }
    }
}

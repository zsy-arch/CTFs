package com.tencent.open.b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.sys.a;
import com.hyphenate.util.EMPrivateConstant;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import java.util.Locale;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class c {
    private static String d;
    static String a = null;
    static String b = null;
    static String c = null;
    private static String e = null;

    public static String a() {
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        try {
            Context context = Global.getContext();
            if (!(context == null || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null || (connectionInfo = wifiManager.getConnectionInfo()) == null)) {
                return connectionInfo.getMacAddress();
            }
            return "";
        } catch (SecurityException e2) {
            f.b("openSDK_LOG.MobileInfoUtil", "getLocalMacAddress>>>", e2);
            return "";
        }
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        if (context == null) {
            return "";
        }
        d = "";
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            int width = windowManager.getDefaultDisplay().getWidth();
            d = width + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + windowManager.getDefaultDisplay().getHeight();
        }
        return d;
    }

    public static String b() {
        return Locale.getDefault().getLanguage();
    }

    public static String b(Context context) {
        if (a != null && a.length() > 0) {
            return a;
        }
        if (context == null) {
            return "";
        }
        try {
            a = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            return a;
        } catch (Exception e2) {
            return "";
        }
    }

    public static String c(Context context) {
        if (b != null && b.length() > 0) {
            return b;
        }
        if (context == null) {
            return "";
        }
        try {
            b = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            return b;
        } catch (Exception e2) {
            return "";
        }
    }

    public static String d(Context context) {
        if (c != null && c.length() > 0) {
            return c;
        }
        if (context == null) {
            return "";
        }
        try {
            c = Settings.Secure.getString(context.getContentResolver(), "android_id");
            return c;
        } catch (Exception e2) {
            return "";
        }
    }

    public static String e(Context context) {
        try {
            if (e == null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                StringBuilder sb = new StringBuilder();
                sb.append("imei=").append(b(context)).append('&');
                sb.append("model=").append(Build.MODEL).append('&');
                sb.append("os=").append(Build.VERSION.RELEASE).append('&');
                sb.append("apilevel=").append(Build.VERSION.SDK_INT).append('&');
                String b2 = a.b(context);
                if (b2 == null) {
                    b2 = "";
                }
                sb.append("network=").append(b2).append('&');
                sb.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
                sb.append("display=").append(displayMetrics.widthPixels).append('*').append(displayMetrics.heightPixels).append('&');
                sb.append("manu=").append(Build.MANUFACTURER).append(a.b);
                sb.append("wifi=").append(a.e(context));
                e = sb.toString();
            }
            return e;
        } catch (Exception e2) {
            return null;
        }
    }
}

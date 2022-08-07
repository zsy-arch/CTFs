package com.cdlinglu.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.util.UUID;

/* loaded from: classes.dex */
public class DeviceTool {
    public static String getNativePhoneNumber(Context context) {
        String nativePhoneNumber;
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
        } catch (Exception e) {
            nativePhoneNumber = "";
        }
        if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_SMS") != 0 && ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        nativePhoneNumber = telephonyManager.getLine1Number();
        return nativePhoneNumber;
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        String deviceId = telephonyManager.getDeviceId();
        if (deviceId == null || "".equals(deviceId)) {
            deviceId = "0000000000";
        }
        return deviceId;
    }

    public static String getDeviceUni(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
        if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        String deviceUni = tm.getDeviceId();
        if (TextUtils.isEmpty(deviceUni)) {
            deviceUni = getMacAddress(context);
        }
        return deviceUni;
    }

    public static String getMacAddress(Context context) {
        return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo info;
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivity != null && (info = connectivity.getActiveNetworkInfo()) != null && info.isConnected() && info.getState() == NetworkInfo.State.CONNECTED;
    }

    public static int checkNetWorkType(Context context) {
        if (isAirplaneModeOn(context)) {
            return -1;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            return 1;
        }
        if (connectivityManager.getNetworkInfo(0).getState() != NetworkInfo.State.CONNECTED) {
            return -1;
        }
        String type = connectivityManager.getActiveNetworkInfo().getExtraInfo();
        return "wap".equalsIgnoreCase(type.substring(type.length() + (-3))) ? 0 : 1;
    }

    public static int getNetWorkConnectionType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(1);
        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(0);
        if (wifiNetworkInfo == null || !wifiNetworkInfo.isAvailable()) {
            return (mobileNetworkInfo == null || !mobileNetworkInfo.isAvailable()) ? -1 : 0;
        }
        return 1;
    }

    public static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    public static boolean isSDCardUsable() {
        return "mounted".equalsIgnoreCase(Environment.getExternalStorageState());
    }

    public static File getImgCacheDir(String defaultImageFolderName) {
        if (!isSDCardUsable()) {
            return null;
        }
        File dir = new File(Environment.getExternalStorageDirectory(), defaultImageFolderName);
        if (dir.exists()) {
            return dir;
        }
        dir.mkdirs();
        return dir;
    }

    public static void hideSoftKeyboardFromView(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (((Activity) context).getWindow().getAttributes().softInputMode != 2 && ((Activity) context).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 2);
        }
    }

    public static String getSDPath() {
        File sdDir;
        if (Environment.getExternalStorageState().equalsIgnoreCase("mounted")) {
            sdDir = Environment.getExternalStorageDirectory();
        } else {
            sdDir = new File("/mnt/sdcard/");
            if (!sdDir.exists() || !sdDir.canRead() || !sdDir.canWrite()) {
                return "";
            }
        }
        return sdDir.getAbsolutePath();
    }

    public static String getModel() {
        return Build.MODEL;
    }
}

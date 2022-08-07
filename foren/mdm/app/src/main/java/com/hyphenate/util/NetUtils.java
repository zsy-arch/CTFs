package com.hyphenate.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

/* loaded from: classes.dex */
public class NetUtils {
    private static final int HIGH_SPEED_DOWNLOAD_BUF_SIZE = 30720;
    private static final int HIGH_SPEED_UPLOAD_BUF_SIZE = 10240;
    private static final int LOW_SPEED_DOWNLOAD_BUF_SIZE = 2024;
    private static final int LOW_SPEED_UPLOAD_BUF_SIZE = 1024;
    private static final int MAX_SPEED_DOWNLOAD_BUF_SIZE = 102400;
    private static final int MAX_SPEED_UPLOAD_BUF_SIZE = 102400;
    private static final String TAG = "net";

    public static int getDownloadBufSize(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
            return 102400;
        }
        if (Build.VERSION.SDK_INT < 13 || activeNetworkInfo == null || activeNetworkInfo.getType() != 9) {
            return (activeNetworkInfo != null || !isConnectionFast(activeNetworkInfo.getType(), activeNetworkInfo.getSubtype())) ? LOW_SPEED_DOWNLOAD_BUF_SIZE : HIGH_SPEED_DOWNLOAD_BUF_SIZE;
        }
        return 102400;
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return "no network";
        }
        int type = activeNetworkInfo.getType();
        if (Build.VERSION.SDK_INT >= 13 && type == 9) {
            return "ETHERNET";
        }
        if (type == 1) {
            return "WIFI";
        }
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return "unkonw network";
        }
    }

    public static int getUploadBufSize(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
            return 102400;
        }
        if (Build.VERSION.SDK_INT >= 13 && activeNetworkInfo != null && activeNetworkInfo.getType() == 9) {
            return 102400;
        }
        if (activeNetworkInfo != null || !isConnectionFast(activeNetworkInfo.getType(), activeNetworkInfo.getSubtype())) {
            return 1024;
        }
        return HIGH_SPEED_UPLOAD_BUF_SIZE;
    }

    public static String getWiFiSSID(Context context) {
        ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
    }

    @TargetApi(13)
    public static boolean hasDataConnection(Context context) {
        boolean z;
        NetworkInfo networkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
            if (networkInfo2 == null || !networkInfo2.isAvailable() || !networkInfo2.isConnected()) {
                NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(0);
                if (networkInfo3 != null && networkInfo3.isAvailable() && networkInfo3.isConnected()) {
                    EMLog.d("net", "has mobile connection");
                    z = true;
                } else if (Build.VERSION.SDK_INT < 13 || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
                    EMLog.d("net", "no data connection");
                    z = false;
                } else {
                    EMLog.d("net", "has ethernet connection");
                    z = true;
                }
            } else {
                EMLog.d("net", "has wifi connection");
                z = true;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hasNetwork(Context context) {
        NetworkInfo activeNetworkInfo;
        if (!(context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null)) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    private static boolean isConnectionFast(int i, int i2) {
        if (i == 1) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 13 && i == 9) {
            return true;
        }
        if (i == 0) {
            switch (i2) {
                case 1:
                    return false;
                case 2:
                    return false;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                    return true;
                case 4:
                    return false;
                case 7:
                    return false;
                default:
                    if (Build.VERSION.SDK_INT >= 11 && (i2 == 14 || i2 == 13)) {
                        return true;
                    }
                    if (Build.VERSION.SDK_INT >= 9 && i2 == 12) {
                        return true;
                    }
                    if (Build.VERSION.SDK_INT >= 8 && i2 == 11) {
                        return false;
                    }
                    break;
            }
        }
        return false;
    }

    public static boolean isEthernetConnected(Context context) {
        NetworkInfo networkInfo;
        if (Build.VERSION.SDK_INT < 13 || (networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(9)) == null || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
            return false;
        }
        EMLog.d("net", "ethernet is connected");
        return true;
    }

    @Deprecated
    public static boolean isEthernetConnection(Context context) {
        return isEthernetConnected(context);
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0);
        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
            return false;
        }
        EMLog.d("net", "mobile is connected");
        return true;
    }

    @Deprecated
    public static boolean isMobileConnection(Context context) {
        return isMobileConnected(context);
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
            return false;
        }
        EMLog.d("net", "wifi is connected");
        return true;
    }

    @Deprecated
    public static boolean isWifiConnection(Context context) {
        return isWifiConnected(context);
    }
}

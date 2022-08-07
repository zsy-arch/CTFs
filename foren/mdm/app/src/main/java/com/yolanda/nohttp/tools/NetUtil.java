package com.yolanda.nohttp.tools;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import com.yolanda.nohttp.Logger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class NetUtil {
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
    private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^[0-9a-fA-F]{1,4}(:[0-9a-fA-F]{1,4}){7}$");
    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)::(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)$");

    public static void openSetting(Context context) {
        if (Build.VERSION.SDK_INT > 10) {
            context.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        } else {
            context.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
        }
    }

    @TargetApi(21)
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivity == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Network[] networks = connectivity.getAllNetworks();
            if (networks == null) {
                return false;
            }
            for (Network network : networks) {
                NetworkInfo networkInfo = connectivity.getNetworkInfo(network);
                if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
            return false;
        }
        NetworkInfo[] networkInfoArray = connectivity.getAllNetworkInfo();
        if (networkInfoArray == null) {
            return false;
        }
        for (NetworkInfo networkInfo2 : networkInfoArray) {
            if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(21)
    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (Build.VERSION.SDK_INT >= 21) {
                for (Network network : mConnectivityManager.getAllNetworks()) {
                    NetworkInfo networkInfo = mConnectivityManager.getNetworkInfo(network);
                    if (networkInfo != null && networkInfo.getType() == 1) {
                        return networkInfo.isAvailable() && networkInfo.isConnected();
                    }
                }
            } else {
                NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(1);
                return mWiFiNetworkInfo != null && mWiFiNetworkInfo.isAvailable() && mWiFiNetworkInfo.isConnected();
            }
        }
        return false;
    }

    @TargetApi(21)
    public boolean isMobileConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (Build.VERSION.SDK_INT >= 21) {
            Network[] networks = mConnectivityManager.getAllNetworks();
            if (networks != null) {
                for (Network network : networks) {
                    NetworkInfo networkInfo = mConnectivityManager.getNetworkInfo(network);
                    if (networkInfo != null && networkInfo.getType() == 0) {
                        return networkInfo.isAvailable() && networkInfo.isConnected();
                    }
                }
            }
            return false;
        }
        NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(1);
        return mWiFiNetworkInfo != null && mWiFiNetworkInfo.isAvailable() && mWiFiNetworkInfo.isConnected();
    }

    public static boolean isGPRSOpen(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        try {
            return ((Boolean) connectivityManager.getClass().getMethod("getMobileDataEnabled", null).invoke(connectivityManager, null)).booleanValue();
        } catch (Exception e) {
            Logger.w(e);
            return false;
        }
    }

    public static void setGPRSEnable(Context context, boolean isEnable) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            connectivityManager.getClass().getMethod("setMobileDataEnabled", Boolean.TYPE).invoke(connectivityManager, Boolean.valueOf(isEnable));
        } catch (Exception e) {
            Logger.w(e);
        }
    }

    public static String getLocalIPAddress() {
        Enumeration<NetworkInterface> enumeration = null;
        try {
            enumeration = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            Logger.w(e);
        }
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = enumeration.nextElement().getInetAddresses();
                if (inetAddresses != null) {
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress ip = inetAddresses.nextElement();
                        if (!ip.isLoopbackAddress() && isIPv4Address(ip.getHostAddress())) {
                            return ip.getHostAddress();
                        }
                    }
                    continue;
                }
            }
        }
        return "";
    }

    public static boolean isIPv4Address(String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6StdAddress(String input) {
        return IPV6_STD_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6HexCompressedAddress(String input) {
        int colonCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ':') {
                colonCount++;
            }
        }
        return colonCount <= 7 && IPV6_HEX_COMPRESSED_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6Address(String input) {
        return isIPv6StdAddress(input) || isIPv6HexCompressedAddress(input);
    }
}

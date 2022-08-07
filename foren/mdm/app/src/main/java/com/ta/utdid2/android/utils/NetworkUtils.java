package com.ta.utdid2.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.umeng.update.UpdateConfig;

/* loaded from: classes2.dex */
public class NetworkUtils {
    public static final String DEFAULT_WIFI_ADDRESS = "00-00-00-00-00-00";
    private static final String TAG = "NetworkUtils";
    public static final String WIFI = "Wi-Fi";
    private static ConnectivityManager sConnManager = null;
    private static final int[] WEAK_NETWORK_GROUP = {4, 7, 2, 1};

    public static boolean isConnected(Context context) {
        ConnectivityManager connManager = getConnManager(context);
        if (connManager != null) {
            try {
                NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    return networkInfo.isConnected();
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        } else {
            Log.e(TAG, "connManager is null!");
        }
        return false;
    }

    public static boolean isConnectedToWeakNetwork(Context context) {
        ConnectivityManager connManager = getConnManager(context);
        if (connManager != null) {
            try {
                NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    int subType = networkInfo.getSubtype();
                    if (DebugUtils.DBG) {
                        Log.d(TAG, "subType:" + subType + ": name:" + networkInfo.getSubtypeName());
                    }
                    for (int element : WEAK_NETWORK_GROUP) {
                        if (element == subType) {
                            return true;
                        }
                    }
                    return false;
                }
                Log.e(TAG, "networkInfo is null!");
                return false;
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                return false;
            }
        } else {
            Log.e(TAG, "connManager is null!");
            return false;
        }
    }

    public static ConnectivityManager getConnManager(Context context) {
        if (context == null) {
            Log.e(TAG, "context is null!");
            return null;
        }
        if (sConnManager == null) {
            sConnManager = (ConnectivityManager) context.getSystemService("connectivity");
        }
        return sConnManager;
    }

    public static String[] getNetworkState(Context paramContext) {
        String[] arrayOfString = new String[2];
        arrayOfString[0] = f.c;
        arrayOfString[1] = f.c;
        try {
            if (paramContext.getPackageManager().checkPermission(UpdateConfig.g, paramContext.getPackageName()) != 0) {
                arrayOfString[0] = f.c;
            } else {
                ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext.getSystemService("connectivity");
                if (localConnectivityManager == null) {
                    arrayOfString[0] = f.c;
                } else {
                    NetworkInfo localNetworkInfo1 = localConnectivityManager.getNetworkInfo(1);
                    if (localNetworkInfo1 == null || localNetworkInfo1.getState() != NetworkInfo.State.CONNECTED) {
                        NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(0);
                        if (localNetworkInfo2 != null && localNetworkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                            arrayOfString[0] = "2G/3G";
                            arrayOfString[1] = localNetworkInfo2.getSubtypeName();
                        }
                    } else {
                        arrayOfString[0] = "Wi-Fi";
                    }
                }
            }
        } catch (Exception e) {
        }
        return arrayOfString;
    }

    public static String getWifiAddress(Context context) {
        if (context == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        WifiInfo wifiinfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (wifiinfo == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        String address = wifiinfo.getMacAddress();
        if (StringUtils.isEmpty(address)) {
            return DEFAULT_WIFI_ADDRESS;
        }
        return address;
    }

    private static String _convertIntToIp(int i) {
        return String.valueOf(i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String getWifiIpAddress(Context context) {
        if (context == null) {
            return null;
        }
        try {
            WifiInfo wifiinfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (wifiinfo != null) {
                return _convertIntToIp(wifiinfo.getIpAddress());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isWifi(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (getNetworkState(context)[0].equals("Wi-Fi")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

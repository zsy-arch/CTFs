package com.ta.utdid2.aid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.Base64Helper;
import com.ta.utdid2.android.utils.DebugUtils;
import com.ta.utdid2.android.utils.SharedPreferenceHelper;
import com.ta.utdid2.android.utils.StringUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class AidStorageController {
    private static final String KEY_PREF_AID_GEN_TIME = "rKrMJgyAEbVtSQGi";
    private static final String KEY_PREF_AID_VALUE = "EvQwnbilKezpOJey";
    private static final String PREF_AID = "OfJbkLdFbPOMbGyP";
    private static final String TAG = AidStorageController.class.getName();
    private static Map<String, String> sAidMapInSP = new ConcurrentHashMap();
    private static Map<String, Long> sAidGenTimeMapInSP = new ConcurrentHashMap();

    public static void setAidValueToSP(Context context, String appName, String aidValue, String token) {
        if (context == null) {
            Log.e(TAG, "no context!");
            return;
        }
        String encodedAppName = getEncodedAppName(appName, token);
        long timeStamp = System.currentTimeMillis();
        sAidMapInSP.put(encodedAppName, aidValue);
        sAidGenTimeMapInSP.put(encodedAppName, Long.valueOf(timeStamp));
        SharedPreferences sp = context.getSharedPreferences(PREF_AID, 0);
        if (Build.VERSION.SDK_INT >= 9) {
            SharedPreferenceHelper.apply(sp.edit().putString(KEY_PREF_AID_VALUE.concat(encodedAppName), aidValue));
            SharedPreferenceHelper.apply(sp.edit().putLong(KEY_PREF_AID_GEN_TIME.concat(encodedAppName), timeStamp));
            return;
        }
        sp.edit().putString(KEY_PREF_AID_VALUE.concat(encodedAppName), aidValue).commit();
        sp.edit().putLong(KEY_PREF_AID_GEN_TIME.concat(encodedAppName), timeStamp).commit();
    }

    public static String getAidValueFromSP(Context context, String appName, String token) {
        if (context == null) {
            Log.e(TAG, "no context!");
            return "";
        }
        String encodedAppName = getEncodedAppName(appName, token);
        String aidInSP = sAidMapInSP.get(encodedAppName);
        if (DebugUtils.DBG) {
            Log.d(TAG, "cache AID:" + aidInSP);
        }
        if (!StringUtils.isEmpty(aidInSP)) {
            return aidInSP;
        }
        String aidInSP2 = context.getSharedPreferences(PREF_AID, 0).getString(KEY_PREF_AID_VALUE.concat(encodedAppName), "");
        sAidMapInSP.put(encodedAppName, aidInSP2);
        return aidInSP2;
    }

    public static long getAidGenTimeFromSP(Context context, String appName, String token) {
        long j;
        if (context == null) {
            Log.e(TAG, "no context!");
            return 0L;
        }
        String encodedAppName = getEncodedAppName(appName, token);
        if (sAidGenTimeMapInSP.containsKey(encodedAppName)) {
            j = sAidGenTimeMapInSP.get(encodedAppName).longValue();
        } else {
            j = 0;
        }
        Long aidGenTimeInSP = Long.valueOf(j);
        if (DebugUtils.DBG) {
            Log.d(TAG, "cache AIDGenTime:" + aidGenTimeInSP);
        }
        if (aidGenTimeInSP.longValue() == 0) {
            aidGenTimeInSP = Long.valueOf(context.getSharedPreferences(PREF_AID, 0).getLong(KEY_PREF_AID_GEN_TIME.concat(encodedAppName), 0L));
            sAidGenTimeMapInSP.put(encodedAppName, aidGenTimeInSP);
        }
        return aidGenTimeInSP.longValue();
    }

    private static String getEncodedAppName(String appName, String token) {
        String encodedName = Build.VERSION.SDK_INT >= 8 ? Base64Helper.encodeToString(appName.concat(token).getBytes(), 2) : Base64.encodeToString(appName.concat(token).getBytes(), 2);
        if (DebugUtils.DBG) {
            Log.d(TAG, "encodedName:" + encodedName);
        }
        return encodedName;
    }
}

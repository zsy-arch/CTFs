package com.ta.utdid2.aid;

import android.content.Context;
import android.util.Log;
import com.ta.utdid2.android.utils.NetworkUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.android.utils.TimeUtils;
import com.ut.device.AidCallback;

/* loaded from: classes2.dex */
public class AidManager {
    private static final int NUM_DAY_OUT_OF_DATE = 1;
    private Context mContext;
    private static AidManager sAidManager = null;
    private static final String TAG = AidManager.class.getName();

    public static synchronized AidManager getInstance(Context context) {
        AidManager aidManager;
        synchronized (AidManager.class) {
            if (sAidManager == null) {
                sAidManager = new AidManager(context);
            }
            aidManager = sAidManager;
        }
        return aidManager;
    }

    private AidManager(Context context) {
        this.mContext = context;
    }

    public void requestAid(String appName, String token, String utdid, AidCallback callback) {
        boolean z = false;
        if (callback == null) {
            Log.e(TAG, "callback is null!");
        } else if (this.mContext == null || StringUtils.isEmpty(appName) || StringUtils.isEmpty(token)) {
            String str = TAG;
            StringBuilder append = new StringBuilder("mContext:").append(this.mContext).append("; callback:").append(callback).append("; has appName:").append(!StringUtils.isEmpty(appName)).append("; has token:");
            if (!StringUtils.isEmpty(token)) {
                z = true;
            }
            Log.e(str, append.append(z).toString());
            callback.onAidEventChanged(1002, "");
        } else {
            String aid = AidStorageController.getAidValueFromSP(this.mContext, appName, token);
            if (!StringUtils.isEmpty(aid) && TimeUtils.isUpToDate(AidStorageController.getAidGenTimeFromSP(this.mContext, appName, token), 1)) {
                callback.onAidEventChanged(1001, aid);
            } else if (NetworkUtils.isConnected(this.mContext)) {
                AidRequester.getInstance(this.mContext).postRestAsync(appName, token, utdid, aid, callback);
            } else {
                callback.onAidEventChanged(1003, aid);
            }
        }
    }

    public String getValue(String appName, String token, String utdid) {
        boolean z = false;
        if (this.mContext == null || StringUtils.isEmpty(appName) || StringUtils.isEmpty(token)) {
            String str = TAG;
            StringBuilder append = new StringBuilder("mContext:").append(this.mContext).append("; has appName:").append(!StringUtils.isEmpty(appName)).append("; has token:");
            if (!StringUtils.isEmpty(token)) {
                z = true;
            }
            Log.e(str, append.append(z).toString());
            return "";
        }
        String aid = AidStorageController.getAidValueFromSP(this.mContext, appName, token);
        if ((StringUtils.isEmpty(aid) || !TimeUtils.isUpToDate(AidStorageController.getAidGenTimeFromSP(this.mContext, appName, token), 1)) && NetworkUtils.isConnected(this.mContext)) {
            return genAidValue(appName, token, utdid);
        }
        return aid;
    }

    private synchronized String genAidValue(String appName, String token, String utdid) {
        String aidValue;
        if (this.mContext == null) {
            Log.e(TAG, "no context!");
            aidValue = "";
        } else {
            aidValue = "";
            if (NetworkUtils.isConnected(this.mContext)) {
                aidValue = AidRequester.getInstance(this.mContext).postRest(appName, token, utdid, AidStorageController.getAidValueFromSP(this.mContext, appName, token));
            }
            AidStorageController.setAidValueToSP(this.mContext, appName, aidValue, token);
        }
        return aidValue;
    }
}

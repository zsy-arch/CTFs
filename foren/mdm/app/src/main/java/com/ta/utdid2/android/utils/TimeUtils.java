package com.ta.utdid2.android.utils;

import android.util.Log;
import com.umeng.analytics.a;

/* loaded from: classes2.dex */
public class TimeUtils {
    public static final String TAG = TimeUtils.class.getName();
    public static final int TOTAL_M_S_ONE_DAY = 86400000;

    public static boolean isUpToDate(long timeStamp, int diffDay) {
        boolean isUpToDate = (System.currentTimeMillis() - timeStamp) / a.j < ((long) diffDay);
        if (DebugUtils.DBG) {
            Log.d(TAG, "isUpToDate: " + isUpToDate + "; oldTimestamp: " + timeStamp + "; currentTimestamp" + System.currentTimeMillis());
        }
        return isUpToDate;
    }
}

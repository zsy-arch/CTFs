package com.hy.frame.util;

import android.content.Context;
import com.umeng.analytics.a;

@Deprecated
/* loaded from: classes.dex */
public class CacheShare extends AppShare {
    private static final String LAST_TIME = "LAST_TIME";
    private static CacheShare instance;

    private CacheShare(Context context) {
        super(context, true);
        clearCache();
    }

    public static CacheShare get(Context context) {
        if (instance == null) {
            instance = new CacheShare(context);
        }
        return instance;
    }

    private void clearCache() {
        if ((System.currentTimeMillis() - getLong(LAST_TIME)) / a.j > 0) {
            clear();
        }
    }
}

package com.litepal;

import android.content.Context;
import com.hy.frame.common.BaseApplication;
import com.litepal.exceptions.GlobalException;

/* loaded from: classes.dex */
public class LitePalApplication extends BaseApplication {
    private static Context sContext;

    public LitePalApplication() {
        sContext = this;
    }

    public static void initialize(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        if (sContext != null) {
            return sContext;
        }
        throw new GlobalException(GlobalException.APPLICATION_CONTEXT_IS_NULL);
    }
}

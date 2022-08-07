package com.bumptech.glide.manager;

import android.content.Context;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.umeng.update.UpdateConfig;

/* loaded from: classes.dex */
public class ConnectivityMonitorFactory {
    public ConnectivityMonitor build(Context context, ConnectivityMonitor.ConnectivityListener listener) {
        if (context.checkCallingOrSelfPermission(UpdateConfig.g) == 0) {
            return new DefaultConnectivityMonitor(context, listener);
        }
        return new NullConnectivityMonitor();
    }
}

package com.amap.api.location;

import android.content.Intent;
import android.os.IBinder;

/* loaded from: classes.dex */
public interface APSServiceBase {
    IBinder onBind(Intent intent);

    void onCreate();

    void onDestroy();

    int onStartCommand(Intent intent, int i, int i2);
}

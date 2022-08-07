package cn.jpush.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import cn.jpush.android.e;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public class DaemonService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        ac.b();
        if (!e.a(getApplicationContext())) {
            stopSelf();
            return;
        }
        ServiceInterface.c(getApplicationContext(), 30000);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}

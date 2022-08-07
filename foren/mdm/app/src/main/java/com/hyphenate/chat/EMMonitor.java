package com.hyphenate.chat;

import android.content.Context;
import com.hyphenate.chat.a.d;
import com.hyphenate.util.EasyUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class EMMonitor {
    private static final String TAG = "EMMonitor";
    private boolean wakeuped;
    private static EMMonitor _instance = null;
    private static String FILENAME = "pid";
    private boolean nativeServiceStarted = false;
    private d monitorDB = new d();

    private EMMonitor() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized EMMonitor getInstance() {
        EMMonitor eMMonitor;
        synchronized (EMMonitor.class) {
            if (_instance == null) {
                _instance = new EMMonitor();
            }
            eMMonitor = _instance;
        }
        return eMMonitor;
    }

    private native void startMonitor(String str);

    private native void startWakeup(String[] strArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public d getMonitorDB() {
        return this.monitorDB;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isStarted() {
        return this.nativeServiceStarted && this.wakeuped;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start(Context context, String str) {
        if (!this.nativeServiceStarted) {
            startMonitor(str);
            this.nativeServiceStarted = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startWakeup(Context context, String str) {
        if (!(this.wakeuped || "wakeup".equals(str))) {
            this.wakeuped = true;
            ArrayList arrayList = new ArrayList();
            List<String> a = this.monitorDB.a();
            List<String> runningApps = EasyUtils.getRunningApps(context);
            for (String str2 : a) {
                if (!runningApps.contains(str2)) {
                    arrayList.add(str2 + "/" + EMChatService.class.getName());
                }
            }
            if (arrayList.size() != 0) {
                startWakeup((String[]) arrayList.toArray(new String[arrayList.size()]));
            }
        }
    }
}

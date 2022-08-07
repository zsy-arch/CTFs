package cn.jpush.android.service;

import android.os.PowerManager;

/* loaded from: classes.dex */
public final class q {
    private static q a = null;
    private PowerManager.WakeLock b = null;

    private q() {
    }

    public static q a() {
        if (a == null) {
            a = new q();
        }
        return a;
    }

    public final void a(PowerManager.WakeLock wakeLock) {
        this.b = wakeLock;
    }

    public final PowerManager.WakeLock b() {
        return this.b;
    }
}

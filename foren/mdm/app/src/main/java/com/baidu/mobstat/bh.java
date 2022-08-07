package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;

/* loaded from: classes.dex */
public class bh {
    private static Handler c;
    HashMap<String, bm> a = new HashMap<>();
    private static HandlerThread b = new HandlerThread("EventHandleThread");
    private static bh d = new bh();

    private bh() {
        b.start();
        b.setPriority(10);
        c = new Handler(b.getLooper());
    }

    public static bh a() {
        return d;
    }

    public String a(String str, String str2) {
        return "__sdk_" + str + "$|$" + str2;
    }

    public void a(Context context, String str, String str2, int i, long j) {
        c.post(new bi(this, context, str, str2, i, j));
    }

    public void a(Context context, String str, String str2, int i, long j, long j2) {
        DataCore.instance().putEvent(str, str2, i, j, j2);
        DataCore.instance().flush(context);
    }

    public void a(Context context, String str, String str2, long j) {
        c.post(new bj(this, j, str, str2));
    }

    public void b(Context context, String str, String str2, long j) {
        c.post(new bk(this, str, str2, j, context));
    }

    public void c(Context context, String str, String str2, long j) {
        c.post(new bl(this, j, context, str, str2));
    }
}

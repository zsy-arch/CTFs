package com.baidu.mobstat;

import android.content.Context;

/* loaded from: classes.dex */
public class m {
    public static void a(Context context) {
        l.a.a(context);
        ax.a(context).a(t.AP_LIST, System.currentTimeMillis());
    }

    public static void a(Context context, String str, String str2) {
        p.a.a(context, str, str2);
        ax.a(context).a(t.APP_CHANGE, System.currentTimeMillis());
    }

    public static void a(Context context, boolean z) {
        q.a.a(context, z);
        ax.a(context).a(z ? t.APP_SYS_LIST : t.APP_USER_LIST, System.currentTimeMillis());
    }

    public static void b(Context context) {
        n.a.a(context);
        ax.a(context).a(t.APP_APK, System.currentTimeMillis());
    }

    public static void b(Context context, boolean z) {
        r.a.a(context, z);
        ax.a(context).a(z ? t.APP_TRACE_CURRENT : t.APP_TRACE_HIS, System.currentTimeMillis());
    }
}

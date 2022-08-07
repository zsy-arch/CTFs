package com.alipay.b.a.a.d;

import android.content.Context;
import com.alipay.b.a.a.a.a.c;
import java.util.HashMap;

/* loaded from: classes.dex */
public class a {
    public static String a(Context context, String str, String str2) {
        String a;
        String str3 = null;
        synchronized (a.class) {
            if (context != null) {
                if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2)) {
                    try {
                        a = d.a(context, str, str2, "");
                    } catch (Throwable th) {
                    }
                    if (!com.alipay.b.a.a.a.a.a(a)) {
                        str3 = c.b(c.a(), a);
                    }
                }
            }
        }
        return str3;
    }

    public static void a(Context context, String str, String str2, String str3) {
        synchronized (a.class) {
            if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2) && context != null) {
                try {
                    String a = c.a(c.a(), str3);
                    HashMap hashMap = new HashMap();
                    hashMap.put(str2, a);
                    d.a(context, str, hashMap);
                } catch (Throwable th) {
                }
            }
        }
    }
}

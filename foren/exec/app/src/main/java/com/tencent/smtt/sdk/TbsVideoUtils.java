package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes.dex */
public class TbsVideoUtils {

    /* renamed from: a  reason: collision with root package name */
    public static q f1273a;

    public static void a(Context context) {
        synchronized (TbsVideoUtils.class) {
            if (f1273a == null) {
                d.a(true).a(context, false, false);
                s a2 = d.a(true).a();
                DexLoader dexLoader = null;
                if (a2 != null) {
                    dexLoader = a2.b();
                }
                if (dexLoader != null) {
                    f1273a = new q(dexLoader);
                }
            }
        }
    }

    public static void deleteVideoCache(Context context, String str) {
        a(context);
        q qVar = f1273a;
        if (qVar != null) {
            qVar.a(context, str);
        }
    }

    public static String getCurWDPDecodeType(Context context) {
        a(context);
        q qVar = f1273a;
        return qVar != null ? qVar.a(context) : BuildConfig.FLAVOR;
    }
}

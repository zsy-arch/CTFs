package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.lang.reflect.Constructor;

/* compiled from: InstanceFactory.java */
/* loaded from: classes2.dex */
public final class au {
    public static <T> T a(Context context, s sVar, String str, Class cls, Class[] clsArr, Object[] objArr) throws j {
        T t = (T) a(b(context, sVar), str, clsArr, objArr);
        if (t != null || (t = (T) a(cls, clsArr, objArr)) != null) {
            return t;
        }
        throw new j("获取对象错误");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000b, code lost:
        if (r3.d != false) goto L_0x000d;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static <T> T a(com.loc.av r3, java.lang.String r4, java.lang.Class[] r5, java.lang.Object[] r6) {
        /*
            r0 = 1
            if (r3 == 0) goto L_0x0022
            boolean r1 = r3.a()     // Catch: Throwable -> 0x0024
            if (r1 == 0) goto L_0x0022
            boolean r1 = r3.d     // Catch: Throwable -> 0x0024
            if (r1 == 0) goto L_0x0022
        L_0x000d:
            if (r0 == 0) goto L_0x002c
            java.lang.Class r0 = r3.loadClass(r4)     // Catch: Throwable -> 0x0024
            if (r0 == 0) goto L_0x002c
            java.lang.reflect.Constructor r0 = r0.getDeclaredConstructor(r5)     // Catch: Throwable -> 0x0024
            r1 = 1
            r0.setAccessible(r1)     // Catch: Throwable -> 0x0024
            java.lang.Object r0 = r0.newInstance(r6)     // Catch: Throwable -> 0x0024
        L_0x0021:
            return r0
        L_0x0022:
            r0 = 0
            goto L_0x000d
        L_0x0024:
            r0 = move-exception
            java.lang.String r1 = "IFactory"
            java.lang.String r2 = "getWrap"
            com.loc.w.a(r0, r1, r2)
        L_0x002c:
            r0 = 0
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.au.a(com.loc.av, java.lang.String, java.lang.Class[], java.lang.Object[]):java.lang.Object");
    }

    private static <T> T a(Class cls, Class[] clsArr, Object[] objArr) {
        if (cls == null) {
            return null;
        }
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(objArr);
        } catch (Throwable th) {
            w.a(th, "IFactory", "gIns2()");
            return null;
        }
    }

    public static void a(Context context, at atVar, s sVar) {
        if (atVar != null) {
            try {
                if (!TextUtils.isEmpty(atVar.a()) && !TextUtils.isEmpty(atVar.b()) && !TextUtils.isEmpty(atVar.c)) {
                    new as(context, atVar, sVar).a();
                }
            } catch (Throwable th) {
                w.a(th, "IFactory", "dDownload()");
            }
        }
    }

    public static void a(final Context context, final String str) {
        try {
            z.b().submit(new Runnable() { // from class: com.loc.au.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        ay.a(new af(context, bb.b()), context, str);
                    } catch (Throwable th) {
                        w.a(th, "InstanceFactory", "rollBack");
                    }
                }
            });
        } catch (Throwable th) {
            w.a(th, "InstanceFactory", "rollBack");
        }
    }

    public static boolean a(Context context, s sVar) {
        try {
            File file = new File(ay.b(context, sVar.a(), sVar.b()));
            if (file.exists()) {
                return true;
            }
            File parentFile = file.getParentFile();
            if (file.exists() || parentFile == null || !parentFile.exists()) {
                return false;
            }
            ay.c(context, sVar.a(), sVar.b());
            return false;
        } catch (Throwable th) {
            w.a(th, "IFactory", "isdowned");
            return false;
        }
    }

    private static av b(Context context, s sVar) {
        try {
            if (a(context, sVar)) {
                return aw.a().a(context, sVar);
            }
            return null;
        } catch (Throwable th) {
            w.a(th, "IFactory", "gIns1");
            return null;
        }
    }
}

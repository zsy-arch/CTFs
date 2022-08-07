package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.lang.reflect.Constructor;

/* compiled from: InstanceFactory.java */
/* loaded from: classes.dex */
public class hm {
    public static <T> T a(Context context, gj gjVar, String str, Class cls, Class[] clsArr, Object[] objArr) throws fz {
        T t = (T) a(b(context, gjVar), str, clsArr, objArr);
        if (t != null || (t = (T) a(cls, clsArr, objArr)) != null) {
            return t;
        }
        throw new fz("获取对象错误");
    }

    public static boolean a(Context context, gj gjVar) {
        try {
            File file = new File(hp.b(context, gjVar.a(), gjVar.b()));
            if (file.exists()) {
                return true;
            }
            hp.a(context, file, gjVar);
            return false;
        } catch (Throwable th) {
            hv.a(th, "IFactory", "isdowned");
            return false;
        }
    }

    public static void a(Context context, hl hlVar, gj gjVar) {
        if (hlVar != null) {
            try {
                if (!TextUtils.isEmpty(hlVar.a()) && !TextUtils.isEmpty(hlVar.b()) && !TextUtils.isEmpty(hlVar.c())) {
                    new hk(context, hlVar, gjVar).a();
                }
            } catch (Throwable th) {
                hv.a(th, "IFactory", "dDownload()");
            }
        }
    }

    private static hn b(Context context, gj gjVar) {
        if (context == null) {
            return null;
        }
        try {
            if (a(context, gjVar)) {
                return hu.a().a(context, gjVar);
            }
            return null;
        } catch (Throwable th) {
            hv.a(th, "IFactory", "gIns1");
            return null;
        }
    }

    private static boolean a(hn hnVar) {
        return hnVar != null && hnVar.a() && hnVar.d;
    }

    private static <T> T a(hn hnVar, String str, Class[] clsArr, Object[] objArr) {
        Class<?> loadClass;
        try {
            if (a(hnVar) && (loadClass = hnVar.loadClass(str)) != null) {
                Constructor<?> declaredConstructor = loadClass.getDeclaredConstructor(clsArr);
                declaredConstructor.setAccessible(true);
                return (T) declaredConstructor.newInstance(objArr);
            }
        } catch (Throwable th) {
            hv.a(th, "IFactory", "getWrap");
        }
        return null;
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
            hv.a(th, "IFactory", "gIns2()");
            return null;
        }
    }
}

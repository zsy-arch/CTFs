package com.amap.api.services.a;

import android.content.Context;
import java.io.File;
import java.lang.reflect.Constructor;

/* compiled from: InstanceFactory.java */
/* loaded from: classes.dex */
public class cf {
    public static <T> T a(Context context, be beVar, String str, Class cls, Class[] clsArr, Object[] objArr) throws av {
        T t = (T) a(b(context, beVar), str, clsArr, objArr);
        if (t != null || (t = (T) a(cls, clsArr, objArr)) != null) {
            return t;
        }
        throw new av("获取对象错误");
    }

    public static boolean a(Context context, be beVar) {
        try {
            File file = new File(cj.b(context, beVar.a(), beVar.b()));
            if (file.exists()) {
                return true;
            }
            cj.a(context, file, beVar);
            return false;
        } catch (Throwable th) {
            co.a(th, "IFactory", "isdowned");
            return false;
        }
    }

    private static cg b(Context context, be beVar) {
        try {
            if (a(context, beVar)) {
                return ch.a().a(context, beVar);
            }
            return null;
        } catch (Throwable th) {
            co.a(th, "IFactory", "gIns1");
            return null;
        }
    }

    private static boolean a(cg cgVar) {
        return cgVar != null && cgVar.a() && cgVar.d;
    }

    private static <T> T a(cg cgVar, String str, Class[] clsArr, Object[] objArr) {
        Class<?> loadClass;
        try {
            if (a(cgVar) && (loadClass = cgVar.loadClass(str)) != null) {
                Constructor<?> declaredConstructor = loadClass.getDeclaredConstructor(clsArr);
                declaredConstructor.setAccessible(true);
                return (T) declaredConstructor.newInstance(objArr);
            }
        } catch (Throwable th) {
            co.a(th, "IFactory", "getWrap");
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
            co.a(th, "IFactory", "gIns2()");
            return null;
        }
    }
}

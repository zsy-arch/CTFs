package com.tencent.smtt.utils;

import android.os.Build;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class v {
    public static Object a(Class<?> cls, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(null, objArr);
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    public static Object a(Object obj, String str) {
        return a(obj, str, (Class<?>[]) null, new Object[0]);
    }

    public static Object a(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        Object obj2 = null;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> cls = obj.getClass();
            Method method = Build.VERSION.SDK_INT > 10 ? cls.getMethod(str, clsArr) : cls.getDeclaredMethod(str, clsArr);
            method.setAccessible(true);
            if (objArr.length == 0) {
                objArr = null;
            }
            obj2 = method.invoke(obj, objArr);
            return obj2;
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            if (th.getCause() != null && th.getCause().toString().contains("AuthenticationFail")) {
                return new String("AuthenticationFail");
            }
            if (str != null && (str.equalsIgnoreCase("canLoadX5Core") || str.equalsIgnoreCase("initTesRuntimeEnvironment"))) {
                return obj2;
            }
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            Log.e("ReflectionUtils", "invokeInstance -- exceptions:" + stringWriter.toString());
            return obj2;
        }
    }

    public static Object a(String str, String str2) {
        try {
            return Class.forName(str).getMethod(str2, new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            return null;
        }
    }

    public static Method a(Object obj, String str, Class<?>... clsArr) {
        for (Class<?> cls = obj.getClass(); cls != Object.class && cls != null; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception e) {
            }
        }
        return null;
    }
}

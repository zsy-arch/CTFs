package com.tencent.smtt.utils;

import android.text.TextUtils;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class PropertyUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Class f1487a;

    /* renamed from: b  reason: collision with root package name */
    public static Method f1488b;

    static {
        try {
            f1487a = Class.forName("android.os.SystemProperties");
            f1488b = f1487a.getDeclaredMethod("get", String.class, String.class);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String a(String str, String str2) {
        Method method;
        Class cls = f1487a;
        if (cls == null || (method = f1488b) == null) {
            return str2;
        }
        try {
            return (String) method.invoke(cls, str, str2);
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    public static String getQuickly(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : a(str, str2);
    }
}

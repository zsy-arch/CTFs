package com.ta.utdid2.android.utils;

import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class DebugUtils {
    public static boolean DBG = false;
    private static final String PROPERTY_DEBUG = "alidebug";
    private static Class<?> mClassType;
    private static Method mGetIntMethod;
    private static Method mGetMethod;

    public static String get(String key) {
        init();
        try {
            return (String) mGetMethod.invoke(mClassType, key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getInt(String key, int def) {
        init();
        try {
            return ((Integer) mGetIntMethod.invoke(mClassType, key, Integer.valueOf(def))).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return def;
        }
    }

    static {
        boolean z = true;
        if (getInt(PROPERTY_DEBUG, 0) != 1) {
            z = false;
        }
        DBG = z;
        mClassType = null;
        mGetMethod = null;
        mGetIntMethod = null;
    }

    private static void init() {
        try {
            if (mClassType == null) {
                mClassType = Class.forName("android.os.SystemProperties");
                mGetMethod = mClassType.getDeclaredMethod("get", String.class);
                mGetIntMethod = mClassType.getDeclaredMethod("getInt", String.class, Integer.TYPE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

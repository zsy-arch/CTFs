package com.tencent.smtt.utils;

import java.security.MessageDigest;

/* loaded from: classes2.dex */
public class o {
    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            return c.a(instance.digest());
        } catch (Exception e) {
            return null;
        }
    }
}

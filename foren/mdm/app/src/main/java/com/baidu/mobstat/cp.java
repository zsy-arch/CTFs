package com.baidu.mobstat;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class cp {
    public static String a(File file) {
        String b;
        try {
            b = co.b(MessageDigest.getInstance("SHA-256"), file);
            return b;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(byte[] bArr) {
        String b;
        try {
            b = co.b(MessageDigest.getInstance("SHA-256"), bArr);
            return b;
        } catch (Exception e) {
            cr.b(e);
            return "";
        }
    }
}

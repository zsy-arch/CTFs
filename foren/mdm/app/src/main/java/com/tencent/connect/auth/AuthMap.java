package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class AuthMap {
    static final /* synthetic */ boolean a;
    private static int b;
    public static AuthMap sInstance;
    public HashMap<String, Auth> authMap = new HashMap<>();
    public final String KEY_CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class Auth {
        public AuthDialog dialog;
        public String key;
        public IUiListener listener;
    }

    static {
        a = !AuthMap.class.desiredAssertionStatus();
        b = 0;
    }

    public static AuthMap getInstance() {
        if (sInstance == null) {
            sInstance = new AuthMap();
        }
        return sInstance;
    }

    public Auth get(String str) {
        return this.authMap.get(str);
    }

    public static int getSerial() {
        int i = b + 1;
        b = i;
        return i;
    }

    public String set(Auth auth) {
        int serial = getSerial();
        try {
            this.authMap.put("" + serial, auth);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "" + serial;
    }

    public void remove(String str) {
        this.authMap.remove(str);
    }

    public String makeKey() {
        int ceil = (int) Math.ceil((Math.random() * 20.0d) + 3.0d);
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int length = charArray.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < ceil; i++) {
            stringBuffer.append(charArray[(int) (Math.random() * length)]);
        }
        return stringBuffer.toString();
    }

    public String decode(String str, String str2) {
        return a(str, str2);
    }

    private String a(String str, String str2) {
        if (a || str.length() % 2 == 0) {
            StringBuilder sb = new StringBuilder();
            int length = str2.length();
            int length2 = str.length() / 2;
            int i = 0;
            for (int i2 = 0; i2 < length2; i2++) {
                sb.append((char) (Integer.parseInt(str.substring(i2 * 2, (i2 * 2) + 2), 16) ^ str2.charAt(i)));
                i = (i + 1) % length;
            }
            return sb.toString();
        }
        throw new AssertionError();
    }
}

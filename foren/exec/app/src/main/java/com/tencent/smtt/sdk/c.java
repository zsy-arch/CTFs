package com.tencent.smtt.sdk;

import android.content.Context;
import java.util.UnknownFormatConversionException;

/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static int f1329a = 5;

    /* renamed from: b  reason: collision with root package name */
    public static int f1330b = 16;

    /* renamed from: c  reason: collision with root package name */
    public static char[] f1331c = new char[f1330b];

    /* renamed from: d  reason: collision with root package name */
    public static String f1332d = "dex2oat-cmdline";

    /* renamed from: e  reason: collision with root package name */
    public static long f1333e = 4096;

    public static String a(Context context, String str) {
        com.tencent.smtt.utils.c cVar = new com.tencent.smtt.utils.c(str);
        cVar.a(f1331c);
        boolean z = true;
        if (f1331c[f1329a] != 1) {
            z = false;
        }
        cVar.a(z);
        cVar.a(f1333e);
        return a(new String(a(cVar)));
    }

    public static String a(String str) {
        String[] split = str.split(new String("\u0000"));
        int i = 0;
        while (i < split.length) {
            int i2 = i + 1;
            String str2 = split[i];
            i = i2 + 1;
            String str3 = split[i2];
            if (str2.equals(f1332d)) {
                return str3;
            }
        }
        return BuildConfig.FLAVOR;
    }

    public static char[] a(com.tencent.smtt.utils.c cVar) {
        char[] cArr = new char[4];
        char[] cArr2 = new char[4];
        cVar.a(cArr);
        if (cArr[0] == 'o' && cArr[1] == 'a' && cArr[2] == 't') {
            cVar.a(cArr2);
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            if (cArr2[1] <= '4') {
                cVar.b();
                cVar.b();
                cVar.b();
            }
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            cVar.b();
            char[] cArr3 = new char[cVar.b()];
            cVar.a(cArr3);
            return cArr3;
        }
        throw new UnknownFormatConversionException(String.format("Invalid art magic %c%c%c", Character.valueOf(cArr[0]), Character.valueOf(cArr[1]), Character.valueOf(cArr[2])));
    }
}

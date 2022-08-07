package com.amap.api.col;

import android.content.Context;
import com.amap.api.col.hp;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

/* compiled from: Utils.java */
/* loaded from: classes.dex */
public class hv {
    public static PublicKey a() {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        Throwable th2;
        try {
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            byteArrayInputStream = new ByteArrayInputStream(gf.b("MIIDRzCCAi+gAwIBAgIEeuDbsDANBgkqhkiG9w0BAQsFADBTMQswCQYDVQQGEwJjbjELMAkGA1UECBMCYmoxCzAJBgNVBAcTAmJqMQ0wCwYDVQQKEwRvcGVuMQ4wDAYDVQQLEwVnYW9kZTELMAkGA1UEAxMCUWkwIBcNMTYwODAxMDE0ODMwWhgPMjA3MTA1MDUwMTQ4MzBaMFMxCzAJBgNVBAYTAmNuMQswCQYDVQQIEwJiajELMAkGA1UEBxMCYmoxDTALBgNVBAoTBG9wZW4xDjAMBgNVBAsTBWdhb2RlMQswCQYDVQQDEwJRaTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKpL13mZm4q6AFP5csQE7130Lwq8m+HICy3rBARd9vbw5Cb1wFF96KdhC5P/aASlrPb+6MSyP1nE97p3ygKJWsgxExyvVuOvh1KUqOFuK15oY7JKTk6L4eLCbkBJZV2DLffpW0HGiRpmFG8LJR0sjNOoubSd5R/6XoBwyRglsyVHprjrK2qDRvT3Edgtfvxp4HnUzMsDD3CJRtgsaDw6ECyF7fhYKEz9I6OEEVsPlpbgzRmhSeFDL77/k1mhPve1ZyKGlPcxvSSdLSAlV0hzr5NKlujHll7BbouwDnr6l/0O44AzZ0V/ieft1iBkSLirnlm56uI/8jdh8ANrD1fW4ZUCAwEAAaMhMB8wHQYDVR0OBBYEFBzudtI5UKRvHGDV+VQRzItIj3PqMA0GCSqGSIb3DQEBCwUAA4IBAQBS2EGndgvIBnf7Ce4IhDbm7F5h4L+6TYGmT9acnQbEFY8oUoFblMDgg+cETT44jU/elwbJJVmKhj/WRQl+AdSALBAgDvxq1AcjlGg+c8H3pa2BWlrxNJP9MFLIEI5bA8m5og/Epjut50uemZ9ggoNmJeW0N/a6D8euhYJKOYngUQqDu6cwLj1Ec0ptwrNRbvRXXgzjfJMPE/ii4K/b8JZ+QN2d/bl7QEvKWBSzVueZifV659qAbMh6C9TCVstWWfV53Z3Vyt+duDNU5ed7aWao42Ppw4VHslrJW0V6BXDUhhzgXx28UWY78W7LmYGCtC8PfDId2+k4tPoTNPM6HHP5"));
            try {
                PublicKey publicKey = ((X509Certificate) instance.generateCertificate(byteArrayInputStream)).getPublicKey();
                try {
                    a(byteArrayInputStream);
                    return publicKey;
                } catch (Throwable th4) {
                    th4.printStackTrace();
                    return publicKey;
                }
            } catch (Throwable th5) {
                th2 = th5;
                a(th2, "DyLoader", "init");
                try {
                    a(byteArrayInputStream);
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th7) {
            th2 = th7;
            byteArrayInputStream = null;
        }
    }

    public static int a(String str, String str2) {
        int i = 0;
        try {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            int min = Math.min(split.length, split2.length);
            for (int i2 = 0; i2 < min; i2++) {
                i = split[i2].length() - split2[i2].length();
                if (!(i == 0 && (i = split[i2].compareTo(split2[i2])) == 0)) {
                    break;
                }
            }
            if (i != 0) {
                return i;
            }
            return split.length - split2.length;
        } catch (Exception e) {
            go.a(e, "Utils", "compareVersion");
            return -1;
        }
    }

    public static void a(List<ht> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int i2 = i + 1; i2 < list.size(); i2++) {
                ht htVar = list.get(i);
                ht htVar2 = list.get(i2);
                if (a(htVar2.e(), htVar.e()) > 0) {
                    list.set(i, htVar2);
                    list.set(i2, htVar);
                }
            }
        }
    }

    public static boolean b(String str, String str2) {
        String a = gg.a(str);
        return a != null && a.equalsIgnoreCase(str2);
    }

    public static boolean a(Context context, gx gxVar, String str, gj gjVar) {
        return a(gxVar, str, hp.a(context, str), gjVar);
    }

    public static boolean a(gx gxVar, String str, String str2, gj gjVar) {
        ht a = hp.a.a(gxVar, str);
        if (a == null || !gjVar.b().equals(a.d()) || !b(str2, a.b())) {
            return false;
        }
        return true;
    }

    public static void a(Throwable th, String str, String str2) {
        go.a(th, str, str2);
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}

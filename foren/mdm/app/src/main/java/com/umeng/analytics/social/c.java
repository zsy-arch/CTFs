package com.umeng.analytics.social;

import com.umeng.analytics.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: UMNetwork.java */
/* loaded from: classes2.dex */
public abstract class c {
    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:47:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.social.c.a(java.lang.String):java.lang.String");
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8192);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine + "\n");
                    } else {
                        try {
                            inputStream.close();
                            return sb.toString();
                        } catch (IOException e) {
                            b.b(a.d, "Caught IOException in convertStreamToString()", e);
                            return null;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                        throw th;
                    } catch (IOException e2) {
                        b.b(a.d, "Caught IOException in convertStreamToString()", e2);
                        return null;
                    }
                }
            } catch (IOException e3) {
                b.b(a.d, "Caught IOException in convertStreamToString()", e3);
                try {
                    inputStream.close();
                    return null;
                } catch (IOException e4) {
                    b.b(a.d, "Caught IOException in convertStreamToString()", e4);
                    return null;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.social.c.a(java.lang.String, java.lang.String):java.lang.String");
    }
}

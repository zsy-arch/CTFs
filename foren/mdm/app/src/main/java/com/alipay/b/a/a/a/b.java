package com.alipay.b.a.a.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public final class b {
    public static String a(String str, String str2) {
        BufferedReader bufferedReader;
        Throwable th;
        File file;
        BufferedReader bufferedReader2 = null;
        StringBuilder sb = new StringBuilder();
        try {
            file = new File(str, str2);
        } catch (IOException e) {
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        if (!file.exists()) {
            return null;
        }
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    try {
                        break;
                    } catch (Throwable th3) {
                    }
                }
            } catch (IOException e2) {
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable th4) {
                    }
                }
                return sb.toString();
            } catch (Throwable th5) {
                th = th5;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th6) {
                    }
                }
                throw th;
            }
        }
        bufferedReader.close();
        return sb.toString();
    }
}

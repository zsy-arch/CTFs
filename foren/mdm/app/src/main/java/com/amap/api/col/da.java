package com.amap.api.col;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: AndroidAssets.java */
/* loaded from: classes.dex */
public class da {
    static da b;
    Context a;

    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        InputStream b2 = b.b(str);
        if (b2 == null) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(b2, "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void a(Context context) {
        b = new da(context);
    }

    private da(Context context) {
        this.a = context;
    }

    public InputStream b(String str) {
        try {
            return this.a.getAssets().open(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* compiled from: StatisticsEntity.java */
/* loaded from: classes.dex */
public class de {
    private Context a;
    private String b;
    private String c;
    private String d;
    private String e;

    public de(Context context, String str, String str2, String str3) throws av {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new av("无效的参数 - IllegalArgumentException");
        }
        this.a = context.getApplicationContext();
        this.c = str;
        this.d = str2;
        this.b = str3;
    }

    public void a(String str) throws av {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new av("无效的参数 - IllegalArgumentException");
        }
        this.e = str;
    }

    public byte[] a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[]{0, 0};
        }
        int length = str.length();
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    public byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        Throwable th;
        Throwable th2;
        try {
            bArr = new byte[0];
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bf.a(byteArrayOutputStream, this.c);
                bf.a(byteArrayOutputStream, this.d);
                bf.a(byteArrayOutputStream, this.b);
                bf.a(byteArrayOutputStream, String.valueOf(ba.m(this.a)));
                new SimpleDateFormat("SSS").format(new Date());
                byteArrayOutputStream.write(a(Calendar.getInstance().get(14)));
                byteArrayOutputStream.write(b(this.e));
                byteArrayOutputStream.write(bf.a(this.e));
                bArr = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th4) {
                        th = th4;
                        th.printStackTrace();
                        return bArr;
                    }
                }
            } catch (Throwable th5) {
                th2 = th5;
                bh.a(th2, "StatisticsEntity", "toDatas");
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th6) {
                        th = th6;
                        th.printStackTrace();
                        return bArr;
                    }
                }
                return bArr;
            }
        } catch (Throwable th7) {
            th2 = th7;
            byteArrayOutputStream = null;
        }
        return bArr;
    }
}

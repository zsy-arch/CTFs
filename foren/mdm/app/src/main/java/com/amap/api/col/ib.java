package com.amap.api.col;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BinaryRequest.java */
/* loaded from: classes.dex */
public abstract class ib extends ig {
    protected Context b;
    protected gj c;

    public abstract byte[] d();

    public abstract byte[] e();

    public boolean i() {
        return true;
    }

    public ib(Context context, gj gjVar) {
        if (context != null) {
            this.b = context.getApplicationContext();
        }
        this.c = gjVar;
    }

    @Override // com.amap.api.col.ig
    public Map<String, String> b() {
        String f = ga.f(this.b);
        String a = gd.a();
        String a2 = gd.a(this.b, a, "key=" + f);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", a);
        hashMap.put("key", f);
        hashMap.put("scode", a2);
        return hashMap;
    }

    @Override // com.amap.api.col.ig
    public final byte[] g() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(f());
            byteArrayOutputStream.write(j());
            byteArrayOutputStream.write(o());
            byteArrayOutputStream.write(p());
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                go.a(th, "BinaryRequest", "getEntityBytes");
            }
        }
    }

    private byte[] f() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(gk.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                go.a(th, "BinaryRequest", "getBinaryHead");
            }
        }
    }

    public byte[] j() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (i()) {
                byte[] a = gd.a(this.b, l());
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] a2 = gk.a(h());
            if (a2 == null || a2.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(a(a2));
                byteArrayOutputStream.write(a2);
            }
            byte[] a3 = gk.a(k());
            if (a3 == null || a3.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(a(a3));
                byteArrayOutputStream.write(a3);
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                go.a(th, "BinaryRequest", "getRequestEncryptData");
            }
        }
    }

    public String k() {
        return String.format("platform=Android&sdkversion=%s&product=%s", this.c.c(), this.c.a());
    }

    protected String h() {
        return "2.1";
    }

    protected byte[] a(byte[] bArr) {
        int length = bArr.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] o() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] e = e();
            if (e == null || e.length == 0) {
                byteArrayOutputStream.write(new byte[]{0});
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                byteArrayOutputStream.write(new byte[]{1});
                byteArrayOutputStream.write(a(e));
                byteArrayOutputStream.write(e);
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    byteArrayOutputStream = byteArrayOutputStream;
                } catch (Throwable th) {
                    go.a(th, "BinaryRequest", "getRequestRawData");
                    byteArrayOutputStream = th;
                }
            }
            return bArr;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                go.a(th2, "BinaryRequest", "getRequestRawData");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] p() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] d = d();
            if (d == null || d.length == 0) {
                byteArrayOutputStream.write(new byte[]{0});
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                byteArrayOutputStream.write(new byte[]{1});
                byte[] a = gd.a(this.b, d);
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    byteArrayOutputStream = byteArrayOutputStream;
                } catch (Throwable th) {
                    go.a(th, "BinaryRequest", "getRequestEncryptData");
                    byteArrayOutputStream = th;
                }
            }
            return bArr;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                go.a(th2, "BinaryRequest", "getRequestEncryptData");
            }
        }
    }

    protected boolean l() {
        return false;
    }
}

package com.amap.api.services.a;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BinaryRequest.java */
/* loaded from: classes.dex */
public abstract class cu extends cz {
    protected Context a;
    protected be b;

    public abstract byte[] a();

    public abstract byte[] d();

    public boolean h() {
        return true;
    }

    public cu(Context context, be beVar) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        this.b = beVar;
    }

    @Override // com.amap.api.services.a.cz
    public Map<String, String> b() {
        String f = aw.f(this.a);
        String a = az.a();
        String a2 = az.a(this.a, a, "key=" + f);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", a);
        hashMap.put("key", f);
        hashMap.put("scode", a2);
        return hashMap;
    }

    @Override // com.amap.api.services.a.cz
    public final byte[] f() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(m());
            byteArrayOutputStream.write(i());
            byteArrayOutputStream.write(n());
            byteArrayOutputStream.write(o());
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                bh.a(th, "BinaryRequest", "getEntityBytes");
            }
        }
    }

    private byte[] m() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(bf.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                bh.a(th, "BinaryRequest", "getBinaryHead");
            }
        }
    }

    public byte[] i() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (h()) {
                byte[] a = az.a(this.a, false);
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] a2 = bf.a(e());
            if (a2 == null || a2.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(a(a2));
                byteArrayOutputStream.write(a2);
            }
            byte[] a3 = bf.a(j());
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
                bh.a(th, "BinaryRequest", "getRequestEncryptData");
            }
        }
    }

    public String j() {
        return String.format("platform=Android&sdkversion=%s&product=%s", this.b.c(), this.b.a());
    }

    protected String e() {
        return "2.1";
    }

    protected byte[] a(byte[] bArr) {
        int length = bArr.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] n() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] a = a();
            if (a == null || a.length == 0) {
                byteArrayOutputStream.write(new byte[]{0});
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                byteArrayOutputStream.write(new byte[]{1});
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    byteArrayOutputStream = byteArrayOutputStream;
                } catch (Throwable th) {
                    bh.a(th, "BinaryRequest", "getRequestRawData");
                    byteArrayOutputStream = th;
                }
            }
            return bArr;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                bh.a(th2, "BinaryRequest", "getRequestRawData");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] o() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] d = d();
            if (d == null || d.length == 0) {
                byteArrayOutputStream.write(new byte[]{0});
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                byteArrayOutputStream.write(new byte[]{1});
                byte[] a = az.a(this.a, d);
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    byteArrayOutputStream = byteArrayOutputStream;
                } catch (Throwable th) {
                    bh.a(th, "BinaryRequest", "getRequestEncryptData");
                    byteArrayOutputStream = th;
                }
            }
            return bArr;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                bh.a(th2, "BinaryRequest", "getRequestEncryptData");
            }
        }
    }
}

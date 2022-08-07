package com.loc;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BinaryRequest.java */
/* loaded from: classes2.dex */
public abstract class bj extends bn {
    protected Context a;
    protected s b;

    public bj(Context context, s sVar) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        this.b = sVar;
    }

    protected static byte[] a(byte[] bArr) {
        int length = bArr.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    private static byte[] h() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(t.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                w.a(th, "BinaryRequest", "getBinaryHead");
            }
        }
    }

    private byte[] i() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (f()) {
                byte[] a = m.a(this.a);
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] a2 = t.a(e());
            if (a2 == null || a2.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(a(a2));
                byteArrayOutputStream.write(a2);
            }
            byte[] a3 = t.a(g());
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
                w.a(th, "BinaryRequest", "getRequestEncryptData");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] j() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] a_ = a_();
            if (a_ == null || a_.length == 0) {
                byteArrayOutputStream.write(new byte[]{0});
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                byteArrayOutputStream.write(new byte[]{1});
                byteArrayOutputStream.write(a(a_));
                byteArrayOutputStream.write(a_);
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    byteArrayOutputStream = byteArrayOutputStream;
                } catch (Throwable th) {
                    w.a(th, "BinaryRequest", "getRequestRawData");
                    byteArrayOutputStream = th;
                }
            }
            return bArr;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                w.a(th2, "BinaryRequest", "getRequestRawData");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] k() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] b_ = b_();
            if (b_ == null || b_.length == 0) {
                byteArrayOutputStream.write(new byte[]{0});
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                byteArrayOutputStream.write(new byte[]{1});
                Context context = this.a;
                byte[] a = m.a(b_);
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    byteArrayOutputStream = byteArrayOutputStream;
                } catch (Throwable th) {
                    w.a(th, "BinaryRequest", "getRequestEncryptData");
                    byteArrayOutputStream = th;
                }
            }
            return bArr;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                w.a(th2, "BinaryRequest", "getRequestEncryptData");
            }
        }
    }

    public abstract byte[] a_();

    public abstract byte[] b_();

    @Override // com.loc.bn
    public Map<String, String> c() {
        String f = k.f(this.a);
        String a = m.a();
        String a2 = m.a(this.a, a, "key=" + f);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", a);
        hashMap.put("key", f);
        hashMap.put("scode", a2);
        return hashMap;
    }

    @Override // com.loc.bn
    public final byte[] d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(h());
            byteArrayOutputStream.write(i());
            byteArrayOutputStream.write(j());
            byteArrayOutputStream.write(k());
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                w.a(th, "BinaryRequest", "getEntityBytes");
            }
        }
    }

    protected String e() {
        return "2.1";
    }

    public boolean f() {
        return true;
    }

    public String g() {
        return String.format("platform=Android&sdkversion=%s&product=%s", this.b.c(), this.b.a());
    }
}

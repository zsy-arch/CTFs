package com.loc;

import android.text.TextUtils;
import java.net.URLConnection;
import java.util.Map;

/* compiled from: BaseNetManager.java */
/* loaded from: classes2.dex */
public final class bi {
    private static bi a;

    /* compiled from: BaseNetManager.java */
    /* loaded from: classes2.dex */
    public interface a {
        URLConnection a();
    }

    public static bi a() {
        if (a == null) {
            a = new bi();
        }
        return a;
    }

    public static bo a(bn bnVar, boolean z) throws j {
        String str;
        try {
            if (bnVar == null) {
                throw new j("requeust is null");
            } else if (bnVar.b() == null || "".equals(bnVar.b())) {
                throw new j("request url is empty");
            } else {
                bl blVar = new bl(bnVar.c, bnVar.d, bnVar.e == null ? null : bnVar.e, z);
                byte[] d = bnVar.d();
                if (d == null || d.length == 0) {
                    str = bnVar.b();
                } else {
                    Map<String, String> c = bnVar.c();
                    if (c == null) {
                        str = bnVar.b();
                    } else {
                        String a2 = bl.a(c);
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(bnVar.b()).append("?").append(a2);
                        str = stringBuffer.toString();
                    }
                }
                Map<String, String> a3 = bnVar.a();
                byte[] d2 = bnVar.d();
                if (d2 == null || d2.length == 0) {
                    String a4 = bl.a(bnVar.c());
                    if (!TextUtils.isEmpty(a4)) {
                        d2 = t.a(a4);
                    }
                }
                return blVar.a(str, a3, d2);
            }
        } catch (j e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new j("未知的错误");
        }
    }

    public static byte[] a(bn bnVar) throws j {
        try {
            bo a2 = a(bnVar, false);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (j e) {
            throw e;
        } catch (Throwable th) {
            w.a(th, "BaseNetManager", "makeSyncPostRequest");
            throw new j("未知的错误");
        }
    }
}

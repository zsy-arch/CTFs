package com.loc;

import android.content.Context;
import java.util.Map;

/* compiled from: LocationRequest.java */
/* loaded from: classes2.dex */
public final class cn extends bj {
    Map<String, String> f = null;
    String g = "";
    byte[] h = null;
    byte[] i = null;
    boolean j = false;
    String k = null;
    Map<String, String> l = null;

    public cn(Context context, s sVar) {
        super(context, sVar);
    }

    @Override // com.loc.bn
    public final Map<String, String> a() {
        return this.f;
    }

    @Override // com.loc.bj
    public final byte[] a_() {
        return this.h;
    }

    @Override // com.loc.bn
    public final String b() {
        return this.g;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0032 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(byte[] r4) {
        /*
            r3 = this;
            r2 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x001e, all -> 0x002e
            r1.<init>()     // Catch: Throwable -> 0x001e, all -> 0x002e
            if (r4 == 0) goto L_0x0012
            byte[] r0 = a(r4)     // Catch: Throwable -> 0x003f, all -> 0x003d
            r1.write(r0)     // Catch: IOException -> 0x0041, Throwable -> 0x003f, all -> 0x003d
            r1.write(r4)     // Catch: IOException -> 0x0041, Throwable -> 0x003f, all -> 0x003d
        L_0x0012:
            byte[] r0 = r1.toByteArray()     // Catch: Throwable -> 0x003f, all -> 0x003d
            r3.i = r0     // Catch: Throwable -> 0x003f, all -> 0x003d
            if (r1 == 0) goto L_0x001d
            r1.close()     // Catch: IOException -> 0x003b
        L_0x001d:
            return
        L_0x001e:
            r0 = move-exception
            r1 = r2
        L_0x0020:
            r0.printStackTrace()     // Catch: all -> 0x003d
            if (r1 == 0) goto L_0x001d
            r1.close()     // Catch: IOException -> 0x0029
            goto L_0x001d
        L_0x0029:
            r0 = move-exception
        L_0x002a:
            r0.printStackTrace()
            goto L_0x001d
        L_0x002e:
            r0 = move-exception
            r1 = r2
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch: IOException -> 0x0036
        L_0x0035:
            throw r0
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0035
        L_0x003b:
            r0 = move-exception
            goto L_0x002a
        L_0x003d:
            r0 = move-exception
            goto L_0x0030
        L_0x003f:
            r0 = move-exception
            goto L_0x0020
        L_0x0041:
            r0 = move-exception
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cn.b(byte[]):void");
    }

    @Override // com.loc.bj
    public final byte[] b_() {
        return this.i;
    }

    @Override // com.loc.bj, com.loc.bn
    public final Map<String, String> c() {
        return this.l;
    }

    @Override // com.loc.bj
    public final boolean f() {
        return this.j;
    }

    @Override // com.loc.bj
    public final String g() {
        return this.k;
    }
}

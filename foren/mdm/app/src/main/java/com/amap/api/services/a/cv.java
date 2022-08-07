package com.amap.api.services.a;

import java.net.Proxy;

/* compiled from: DownloadManager.java */
/* loaded from: classes.dex */
public class cv {
    private cw a;
    private cz b;

    /* compiled from: DownloadManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(Throwable th);

        void a(byte[] bArr, long j);

        void d();

        void e();
    }

    public cv(cz czVar) {
        this(czVar, 0L, -1L);
    }

    public cv(cz czVar, long j, long j2) {
        Proxy proxy;
        this.b = czVar;
        if (czVar.g == null) {
            proxy = null;
        } else {
            proxy = czVar.g;
        }
        this.a = new cw(this.b.e, this.b.f, proxy);
        this.a.b(j2);
        this.a.a(j);
    }

    public void a(a aVar) {
        this.a.a(this.b.g(), this.b.c(), this.b.b(), aVar);
    }
}

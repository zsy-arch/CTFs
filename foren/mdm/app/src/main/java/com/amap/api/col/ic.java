package com.amap.api.col;

import java.net.Proxy;

/* compiled from: DownloadManager.java */
/* loaded from: classes.dex */
public class ic {
    private id a;
    private ig b;

    /* compiled from: DownloadManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(Throwable th);

        void a(byte[] bArr, long j);

        void d();

        void e();
    }

    public ic(ig igVar) {
        this(igVar, 0L, -1L);
    }

    public ic(ig igVar, long j, long j2) {
        Proxy proxy;
        this.b = igVar;
        if (igVar.h == null) {
            proxy = null;
        } else {
            proxy = igVar.h;
        }
        this.a = new id(this.b.f, this.b.g, proxy);
        this.a.b(j2);
        this.a.a(j);
    }

    public void a(a aVar) {
        this.a.a(this.b.c(), this.b.a(), this.b.b(), aVar);
    }

    public void a() {
        this.a.a();
    }
}

package com.amap.api.col;

/* compiled from: IDownloadListener.java */
/* loaded from: classes.dex */
public interface bj {
    void a(long j, long j2);

    void a(a aVar);

    void n();

    void o();

    void p();

    /* compiled from: IDownloadListener.java */
    /* loaded from: classes.dex */
    public enum a {
        amap_exception(-1),
        network_exception(-1),
        file_io_exception(0),
        success_no_exception(1),
        cancel_no_exception(2);
        
        private int f;

        a(int i) {
            this.f = i;
        }
    }
}

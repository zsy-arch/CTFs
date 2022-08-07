package com.amap.api.col;

import android.content.Context;
import com.amap.api.col.bj;
import com.amap.api.col.ic;
import com.amap.api.maps.AMapException;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: NetFileFetch.java */
/* loaded from: classes.dex */
public class be implements ic.a {
    bf a;
    long d;
    az f;
    a h;
    private Context i;
    private bj j;
    private String k;
    private ic l;
    private ba m;
    long b = 0;
    long c = 0;
    boolean e = true;
    long g = 0;

    /* compiled from: NetFileFetch.java */
    /* loaded from: classes.dex */
    public interface a {
        void d();
    }

    public be(bf bfVar, String str, Context context, bj bjVar) throws IOException {
        this.a = null;
        this.f = az.a(context.getApplicationContext());
        this.a = bfVar;
        this.i = context;
        this.k = str;
        this.j = bjVar;
        g();
    }

    private void f() throws IOException {
        bk bkVar = new bk(this.k);
        bkVar.a(1800000);
        bkVar.b(1800000);
        this.l = new ic(bkVar, this.b, this.c);
        this.m = new ba(this.a.b() + File.separator + this.a.c(), this.b);
    }

    private void g() {
        File file = new File(this.a.b() + this.a.c());
        if (file.exists()) {
            this.e = false;
            this.b = file.length();
            try {
                this.d = b();
                this.c = this.d;
            } catch (IOException e) {
                if (this.j != null) {
                    this.j.a(bj.a.file_io_exception);
                }
            }
        } else {
            this.b = 0L;
            this.c = 0L;
        }
    }

    public void a() {
        try {
            if (dt.c(this.i)) {
                i();
                if (gc.a == 1) {
                    if (!h()) {
                        this.e = true;
                    }
                    if (this.e) {
                        this.d = b();
                        if (this.d == -1) {
                            bh.a("File Length is not known!");
                        } else if (this.d == -2) {
                            bh.a("File is not access!");
                        } else {
                            this.c = this.d;
                        }
                        this.b = 0L;
                    }
                    if (this.j != null) {
                        this.j.n();
                    }
                    if (this.b >= this.c) {
                        e();
                        return;
                    }
                    f();
                    this.l.a(this);
                } else if (this.j != null) {
                    this.j.a(bj.a.amap_exception);
                }
            } else if (this.j != null) {
                this.j.a(bj.a.network_exception);
            }
        } catch (AMapException e) {
            gr.b(e, "SiteFileFetch", "download");
            if (this.j != null) {
                this.j.a(bj.a.amap_exception);
            }
        } catch (IOException e2) {
            if (this.j != null) {
                this.j.a(bj.a.file_io_exception);
            }
        }
    }

    private boolean h() {
        return new File(new StringBuilder().append(this.a.b()).append(File.separator).append(this.a.c()).toString()).length() >= 10;
    }

    private void i() throws AMapException {
        if (gc.a != 1) {
            for (int i = 0; i < 3; i++) {
                try {
                } catch (Throwable th) {
                    gr.b(th, "SiteFileFetch", "authOffLineDownLoad");
                    th.printStackTrace();
                }
                if (gc.a(this.i, dt.e())) {
                    return;
                }
            }
        }
    }

    public long b() throws IOException {
        int i;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a.a()).openConnection();
        httpURLConnection.setRequestProperty("User-Agent", g.d);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode >= 400) {
            a(responseCode);
            return -2L;
        }
        int i2 = 1;
        while (true) {
            String headerFieldKey = httpURLConnection.getHeaderFieldKey(i2);
            if (headerFieldKey == null) {
                i = -1;
                break;
            } else if (headerFieldKey.equalsIgnoreCase("Content-Length")) {
                i = Integer.parseInt(httpURLConnection.getHeaderField(headerFieldKey));
                break;
            } else {
                i2++;
            }
        }
        return i;
    }

    private void j() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.a != null && currentTimeMillis - this.g > 500) {
            k();
            this.g = currentTimeMillis;
            a(this.b);
        }
    }

    private void k() {
        this.f.a(this.a.e(), this.a.d(), this.d, this.b, this.c);
    }

    private void a(long j) {
        if (this.d > 0 && this.j != null) {
            this.j.a(this.d, j);
            this.g = System.currentTimeMillis();
        }
    }

    private void a(int i) {
        System.err.println("Error Code : " + i);
    }

    public void c() {
        if (this.l != null) {
            this.l.a();
        }
    }

    @Override // com.amap.api.col.ic.a
    public void d() {
        if (this.j != null) {
            this.j.p();
        }
        k();
    }

    @Override // com.amap.api.col.ic.a
    public void e() {
        j();
        if (this.j != null) {
            this.j.o();
        }
        if (this.m != null) {
            this.m.a();
        }
        if (this.h != null) {
            this.h.d();
        }
    }

    @Override // com.amap.api.col.ic.a
    public void a(Throwable th) {
        if (this.j != null) {
            this.j.a(bj.a.network_exception);
        }
        if (!(th instanceof IOException) && this.m != null) {
            this.m.a();
        }
    }

    @Override // com.amap.api.col.ic.a
    public void a(byte[] bArr, long j) {
        try {
            this.m.a(bArr);
            this.b = j;
            j();
        } catch (IOException e) {
            e.printStackTrace();
            gr.b(e, "fileAccessI", "fileAccessI.write(byte[] data)");
            if (this.j != null) {
                this.j.a(bj.a.file_io_exception);
            }
            if (this.l != null) {
                this.l.a();
            }
        }
    }

    public void a(a aVar) {
        this.h = aVar;
    }
}

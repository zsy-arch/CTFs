package com.amap.api.col;

import android.content.Context;
import android.os.Bundle;
import com.amap.api.col.be;
import com.amap.api.col.bj;
import com.amap.api.maps.AMap;
import java.io.IOException;

/* compiled from: OfflineMapDownloadTask.java */
/* loaded from: classes.dex */
public class ap extends iq implements be.a {
    private be a;
    private bg b;
    private bi c;
    private Context d;
    private Bundle e;
    private AMap f;
    private boolean g;

    public ap(bi biVar, Context context) {
        this.e = new Bundle();
        this.g = false;
        this.c = biVar;
        this.d = context;
    }

    public ap(bi biVar, Context context, AMap aMap) {
        this(biVar, context);
        this.f = aMap;
    }

    @Override // com.amap.api.col.iq
    public void a() {
        if (this.c.y()) {
            this.c.a(bj.a.file_io_exception);
            return;
        }
        try {
            g();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void b() {
        this.g = true;
        if (this.a != null) {
            this.a.c();
        } else {
            e();
        }
        if (this.b != null) {
            this.b.a();
        }
    }

    private String f() {
        return dt.b(this.d);
    }

    private void g() throws IOException {
        this.a = new be(new bf(this.c.getUrl(), f(), this.c.z(), 1, this.c.A()), this.c.getUrl(), this.d, this.c);
        this.a.a(this);
        this.b = new bg(this.c, this.c);
        if (!this.g) {
            this.a.a();
        }
    }

    public void c() {
        this.f = null;
        if (this.e != null) {
            this.e.clear();
            this.e = null;
        }
    }

    @Override // com.amap.api.col.be.a
    public void d() {
        if (this.b != null) {
            this.b.b();
        }
    }
}

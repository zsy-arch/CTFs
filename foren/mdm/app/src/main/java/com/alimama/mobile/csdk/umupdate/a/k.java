package com.alimama.mobile.csdk.umupdate.a;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;
import com.alimama.mobile.csdk.umupdate.b.c;
import com.alimama.mobile.csdk.umupdate.b.d;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.umeng.update.net.b;

/* compiled from: XpDownloadAgent.java */
/* loaded from: classes.dex */
public class k {
    static final String a = "xp";
    private static final String b = k.class.getName();
    private Context c = com.alimama.mobile.a.a().c();
    private com.umeng.update.net.a d;
    private d e;
    private Promoter f;

    public k(Promoter promoter, d.a aVar) {
        this.f = promoter;
        this.e = aVar.a();
        this.d = new com.umeng.update.net.a(this.c.getApplicationContext(), a, promoter.title, promoter.url, new a());
        d a2 = aVar.a(1).a();
        if (a2 != null) {
            this.d.a(new String[]{a2.b()});
            if (!e.f || Build.VERSION.SDK_INT < 16) {
                this.d.a(false);
            } else {
                this.d.a(true);
            }
        }
    }

    public void a() {
        g.b(b, "start Download.");
        this.d.a();
    }

    /* compiled from: XpDownloadAgent.java */
    /* loaded from: classes.dex */
    public class a implements com.umeng.update.net.d {
        private String b;
        private b c;

        public a() {
            this.b = k.this.f.url;
            this.c = b.a(k.this.c);
        }

        @Override // com.umeng.update.net.d
        public void onStart() {
            g.b(k.b, "XpDownloadListener.onStart");
            if (e.g) {
                Toast.makeText(k.this.c, com.alimama.mobile.a.a().d().c() + k.this.f.title, 0).show();
            }
            if (k.this.e != null) {
                new c().sendAsync(k.this.e, null);
            }
            this.c.a(k.a, this.b);
        }

        @Override // com.umeng.update.net.d
        public void onProgressUpdate(int i) {
            g.b(k.b, "XpDownloadListener.onProgressUpdate");
        }

        @Override // com.umeng.update.net.d
        public void onEnd(int i, int i2, String str) {
            g.b(k.b, "XpDownloadListener.onEndresult = " + i + " file = " + str);
            if (i == 1) {
                this.c.e(k.a, this.b);
            }
        }

        @Override // com.umeng.update.net.d
        public void onStatus(int i) {
        }
    }
}

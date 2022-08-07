package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.col.ic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

/* compiled from: SDKCoordinatorDownload.java */
/* loaded from: classes.dex */
public class gi extends Thread implements ic.a {
    private static String h = "sodownload";
    private static String i = "sofail";
    private ic a;
    private a b;
    private RandomAccessFile c;
    private String d;
    private String e;
    private String f;
    private Context g;

    public gi(Context context, String str, String str2, String str3) {
        this.g = context;
        this.f = str3;
        this.d = a(context, str + "temp.so");
        this.e = a(context, "libwgs2gcj.so");
        this.b = new a(str2);
        this.a = new ic(this.b);
    }

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    private static String b(Context context, String str) {
        return a(context, str);
    }

    public void a() {
        if (this.b != null && !TextUtils.isEmpty(this.b.c()) && this.b.c().contains("libJni_wgs2gcj.so") && this.b.c().contains(gk.a(this.g)) && !new File(this.e).exists()) {
            start();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            File file = new File(b(this.g, "tempfile"));
            if (file.exists()) {
                file.delete();
            }
            this.a.a(this);
        } catch (Throwable th) {
            go.a(th, "SDKCoordinatorDownload", "run");
            b();
        }
    }

    @Override // com.amap.api.col.ic.a
    public void a(byte[] bArr, long j) {
        try {
            if (this.c == null) {
                File file = new File(this.d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                try {
                    this.c = new RandomAccessFile(file, "rw");
                } catch (FileNotFoundException e) {
                    go.a(e, "SDKCoordinatorDownload", "onDownload");
                    b();
                }
            }
            if (this.c != null) {
                try {
                    this.c.seek(j);
                    this.c.write(bArr);
                } catch (IOException e2) {
                    b();
                    go.a(e2, "SDKCoordinatorDownload", "onDownload");
                }
            }
        } catch (Throwable th) {
            b();
            go.a(th, "SDKCoordinatorDownload", "onDownload");
        }
    }

    @Override // com.amap.api.col.ic.a
    public void d() {
        b();
    }

    @Override // com.amap.api.col.ic.a
    public void e() {
        try {
            if (this.c != null) {
                this.c.close();
            }
            String a2 = gg.a(this.d);
            if (a2 == null || !a2.equalsIgnoreCase(this.f)) {
                b();
            } else if (new File(this.e).exists()) {
                b();
            } else {
                new File(this.d).renameTo(new File(this.e));
            }
        } catch (Throwable th) {
            b();
            File file = new File(this.e);
            if (file.exists()) {
                file.delete();
            }
            go.a(th, "SDKCoordinatorDownload", "onFinish");
        }
    }

    @Override // com.amap.api.col.ic.a
    public void a(Throwable th) {
        try {
            if (this.c != null) {
                this.c.close();
            }
            b();
            File file = new File(b(this.g, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable th2) {
            go.a(th2, "SDKCoordinatorDownload", "onException");
        }
    }

    private void b() {
        File file = new File(this.d);
        if (file.exists()) {
            file.delete();
        }
    }

    /* compiled from: SDKCoordinatorDownload.java */
    /* loaded from: classes.dex */
    private static class a extends ig {
        private String a;

        a(String str) {
            this.a = str;
        }

        @Override // com.amap.api.col.ig
        public Map<String, String> a() {
            return null;
        }

        @Override // com.amap.api.col.ig
        public Map<String, String> b() {
            return null;
        }

        @Override // com.amap.api.col.ig
        public String c() {
            return this.a;
        }
    }
}

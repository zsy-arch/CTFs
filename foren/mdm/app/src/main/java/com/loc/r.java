package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.loc.bk;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

/* compiled from: SDKCoordinatorDownload.java */
/* loaded from: classes2.dex */
public final class r extends Thread implements bk.a {
    private static String h = "sodownload";
    private static String i = "sofail";
    private bk a;
    private a b;
    private RandomAccessFile c;
    private String d;
    private String e;
    private String f;
    private Context g;

    /* compiled from: SDKCoordinatorDownload.java */
    /* loaded from: classes2.dex */
    private static class a extends bn {
        private String a;

        a(String str) {
            this.a = str;
        }

        @Override // com.loc.bn
        public final Map<String, String> a() {
            return null;
        }

        @Override // com.loc.bn
        public final String b() {
            return this.a;
        }

        @Override // com.loc.bn
        public final Map<String, String> c() {
            return null;
        }
    }

    public r(Context context, String str, String str2, String str3) {
        this.g = context;
        this.f = str3;
        this.d = a(context, str + "temp.so");
        this.e = a(context, "libwgs2gcj.so");
        this.b = new a(str2);
        this.a = new bk(this.b);
    }

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    private void e() {
        File file = new File(this.d);
        if (file.exists()) {
            file.delete();
        }
    }

    public final void a() {
        if (this.b != null && !TextUtils.isEmpty(this.b.b()) && this.b.b().contains("libJni_wgs2gcj.so") && this.b.b().contains(Build.CPU_ABI) && !new File(this.e).exists()) {
            start();
        }
    }

    @Override // com.loc.bk.a
    public final void a(byte[] bArr, long j) {
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
                    w.a(e, "SDKCoordinatorDownload", "onDownload");
                    e();
                }
            }
            if (this.c != null) {
                try {
                    this.c.seek(j);
                    this.c.write(bArr);
                } catch (IOException e2) {
                    e();
                    w.a(e2, "SDKCoordinatorDownload", "onDownload");
                }
            }
        } catch (Throwable th) {
            e();
            w.a(th, "SDKCoordinatorDownload", "onDownload");
        }
    }

    @Override // com.loc.bk.a
    public final void b() {
        e();
    }

    @Override // com.loc.bk.a
    public final void c() {
        try {
            if (this.c != null) {
                this.c.close();
            }
            String a2 = p.a(this.d);
            if (a2 == null || !a2.equalsIgnoreCase(this.f)) {
                e();
            } else if (new File(this.e).exists()) {
                e();
            } else {
                new File(this.d).renameTo(new File(this.e));
            }
        } catch (Throwable th) {
            e();
            File file = new File(this.e);
            if (file.exists()) {
                file.delete();
            }
            w.a(th, "SDKCoordinatorDownload", "onFinish");
        }
    }

    @Override // com.loc.bk.a
    public final void d() {
        try {
            if (this.c != null) {
                this.c.close();
            }
            e();
            File file = new File(a(this.g, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable th) {
            w.a(th, "SDKCoordinatorDownload", "onException");
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            File file = new File(a(this.g, "tempfile"));
            if (file.exists()) {
                file.delete();
            }
            this.a.a(this);
        } catch (Throwable th) {
            w.a(th, "SDKCoordinatorDownload", "run");
            e();
        }
    }
}

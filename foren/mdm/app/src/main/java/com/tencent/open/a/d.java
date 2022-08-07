package com.tencent.open.a;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.text.SimpleDateFormat;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class d {

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static final class a {
        public static final boolean a(int i, int i2) {
            return i2 == (i & i2);
        }
    }

    /* compiled from: ProGuard */
    /* renamed from: com.tencent.open.a.d$d  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C0087d {
        @SuppressLint({"SimpleDateFormat"})
        public static SimpleDateFormat a(String str) {
            return new SimpleDateFormat(str);
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static final class b {
        public static boolean a() {
            String externalStorageState = Environment.getExternalStorageState();
            return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
        }

        public static c b() {
            if (!a()) {
                return null;
            }
            return c.b(Environment.getExternalStorageDirectory());
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class c {
        private File a;
        private long b;
        private long c;

        public File a() {
            return this.a;
        }

        public void a(File file) {
            this.a = file;
        }

        public long b() {
            return this.b;
        }

        public void a(long j) {
            this.b = j;
        }

        public long c() {
            return this.c;
        }

        public void b(long j) {
            this.c = j;
        }

        public static c b(File file) {
            c cVar = new c();
            cVar.a(file);
            StatFs statFs = new StatFs(file.getAbsolutePath());
            long blockSize = statFs.getBlockSize();
            cVar.a(statFs.getBlockCount() * blockSize);
            cVar.b(blockSize * statFs.getAvailableBlocks());
            return cVar;
        }

        public String toString() {
            return String.format("[%s : %d / %d]", a().getAbsolutePath(), Long.valueOf(c()), Long.valueOf(b()));
        }
    }
}

package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.k;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/* loaded from: classes2.dex */
public class bi {
    private static bi a;
    private static FileLock e = null;
    private bj b;
    private boolean c;
    private boolean d;

    private bi() {
    }

    public static bi b() {
        if (a == null) {
            synchronized (bi.class) {
                if (a == null) {
                    a = new bi();
                }
            }
        }
        return a;
    }

    public bj a(boolean z) {
        return z ? this.b : d();
    }

    public FileLock a() {
        return e;
    }

    public synchronized FileLock a(Context context) {
        FileLock fileLock;
        if (e != null) {
            fileLock = e;
        } else {
            FileOutputStream b = k.b(context, true, "tbs_rename_lock.txt");
            if (b != null) {
                e = k.a(context, b);
                if (e == null) {
                }
            }
            fileLock = e;
        }
        return fileLock;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0060 A[Catch: all -> 0x0142, TRY_LEAVE, TryCatch #3 {, blocks: (B:5:0x0004, B:6:0x000a, B:8:0x001b, B:9:0x0021, B:11:0x0027, B:14:0x002d, B:15:0x0030, B:16:0x003f, B:18:0x004b, B:20:0x0051, B:22:0x0055, B:23:0x005c, B:25:0x0060, B:28:0x006f, B:30:0x0079, B:31:0x0087, B:33:0x008b, B:34:0x00c1, B:36:0x00c5, B:38:0x00dd, B:40:0x00e9, B:41:0x010e, B:43:0x0113, B:48:0x011d, B:50:0x0123, B:54:0x0146, B:56:0x0155, B:57:0x017b, B:59:0x0181, B:60:0x01a4, B:61:0x01c7, B:63:0x01cb), top: B:66:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0113 A[Catch: all -> 0x0142, TRY_LEAVE, TryCatch #3 {, blocks: (B:5:0x0004, B:6:0x000a, B:8:0x001b, B:9:0x0021, B:11:0x0027, B:14:0x002d, B:15:0x0030, B:16:0x003f, B:18:0x004b, B:20:0x0051, B:22:0x0055, B:23:0x005c, B:25:0x0060, B:28:0x006f, B:30:0x0079, B:31:0x0087, B:33:0x008b, B:34:0x00c1, B:36:0x00c5, B:38:0x00dd, B:40:0x00e9, B:41:0x010e, B:43:0x0113, B:48:0x011d, B:50:0x0123, B:54:0x0146, B:56:0x0155, B:57:0x017b, B:59:0x0181, B:60:0x01a4, B:61:0x01c7, B:63:0x01cb), top: B:66:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01c7 A[Catch: all -> 0x0142, TryCatch #3 {, blocks: (B:5:0x0004, B:6:0x000a, B:8:0x001b, B:9:0x0021, B:11:0x0027, B:14:0x002d, B:15:0x0030, B:16:0x003f, B:18:0x004b, B:20:0x0051, B:22:0x0055, B:23:0x005c, B:25:0x0060, B:28:0x006f, B:30:0x0079, B:31:0x0087, B:33:0x008b, B:34:0x00c1, B:36:0x00c5, B:38:0x00dd, B:40:0x00e9, B:41:0x010e, B:43:0x0113, B:48:0x011d, B:50:0x0123, B:54:0x0146, B:56:0x0155, B:57:0x017b, B:59:0x0181, B:60:0x01a4, B:61:0x01c7, B:63:0x01cb), top: B:66:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void a(android.content.Context r8, com.tencent.smtt.sdk.ai r9) {
        /*
            Method dump skipped, instructions count: 464
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.bi.a(android.content.Context, com.tencent.smtt.sdk.ai):void");
    }

    public boolean c() {
        if (QbSdk.a) {
            return false;
        }
        return this.c;
    }

    public bj d() {
        if (QbSdk.a) {
            return null;
        }
        return this.b;
    }

    public boolean e() {
        return this.d;
    }
}

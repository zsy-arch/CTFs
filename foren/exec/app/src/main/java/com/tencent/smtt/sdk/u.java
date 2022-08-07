package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.nio.channels.FileLock;

/* loaded from: classes.dex */
public class u {

    /* renamed from: a */
    public static u f1417a;

    /* renamed from: e */
    public static FileLock f1418e;

    /* renamed from: b */
    public v f1419b;

    /* renamed from: c */
    public boolean f1420c;

    /* renamed from: d */
    public boolean f1421d;

    public static u a() {
        if (f1417a == null) {
            synchronized (u.class) {
                if (f1417a == null) {
                    f1417a = new u();
                }
            }
        }
        return f1417a;
    }

    public v a(boolean z) {
        return z ? this.f1419b : c();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x009a A[Catch: all -> 0x01c5, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0001, B:6:0x0023, B:8:0x0027, B:9:0x0032, B:11:0x003e, B:12:0x0060, B:14:0x0063, B:16:0x0067, B:17:0x0076, B:19:0x007e, B:21:0x009a, B:25:0x00ab, B:27:0x00b1, B:28:0x00bd, B:30:0x00c1, B:31:0x00f0, B:33:0x00f4, B:34:0x010d, B:36:0x0119, B:37:0x0139, B:38:0x013e, B:39:0x015f, B:41:0x0165, B:42:0x0181, B:43:0x0185, B:44:0x01a2, B:46:0x01be, B:47:0x01c1), top: B:54:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01a2 A[Catch: all -> 0x01c5, TryCatch #3 {, blocks: (B:3:0x0001, B:6:0x0023, B:8:0x0027, B:9:0x0032, B:11:0x003e, B:12:0x0060, B:14:0x0063, B:16:0x0067, B:17:0x0076, B:19:0x007e, B:21:0x009a, B:25:0x00ab, B:27:0x00b1, B:28:0x00bd, B:30:0x00c1, B:31:0x00f0, B:33:0x00f4, B:34:0x010d, B:36:0x0119, B:37:0x0139, B:38:0x013e, B:39:0x015f, B:41:0x0165, B:42:0x0181, B:43:0x0185, B:44:0x01a2, B:46:0x01be, B:47:0x01c1), top: B:54:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void a(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.u.a(android.content.Context):void");
    }

    public FileLock b(Context context) {
        String str;
        String str2;
        TbsLog.i("X5CoreEngine", "tryTbsCoreLoadFileLock ##");
        FileLock fileLock = f1418e;
        if (fileLock != null) {
            return fileLock;
        }
        synchronized (u.class) {
            if (f1418e == null) {
                f1418e = FileUtil.e(context);
                if (f1418e == null) {
                    str = "X5CoreEngine";
                    str2 = "init -- sTbsCoreLoadFileLock failed!";
                } else {
                    str = "X5CoreEngine";
                    str2 = "init -- sTbsCoreLoadFileLock succeeded: " + f1418e;
                }
                TbsLog.i(str, str2);
            }
        }
        return f1418e;
    }

    public boolean b() {
        if (QbSdk.f1127a) {
            return false;
        }
        return this.f1420c;
    }

    public v c() {
        if (QbSdk.f1127a) {
            return null;
        }
        return this.f1419b;
    }

    public boolean d() {
        return this.f1421d;
    }
}

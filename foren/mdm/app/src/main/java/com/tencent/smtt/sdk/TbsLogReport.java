package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Looper;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import com.alipay.sdk.util.h;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.n;
import com.tencent.smtt.utils.w;
import com.tencent.smtt.utils.x;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;

/* loaded from: classes2.dex */
public class TbsLogReport {
    private static TbsLogReport b;
    int a;
    private Context c;
    private long d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private String k;
    private int l;
    private int m;
    private long n;
    private long o;
    private int p;
    private String q;
    private String r;
    private long s;

    /* loaded from: classes2.dex */
    public enum EventType {
        TYPE_DOWNLOAD(0),
        TYPE_INSTALL(1),
        TYPE_LOAD(2);
        
        int a;

        EventType(int i) {
            this.a = i;
        }
    }

    /* loaded from: classes2.dex */
    public static class ZipHelper {
        private final String a;
        private final String b;

        public ZipHelper(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        private static void a(File file) {
            RandomAccessFile randomAccessFile;
            RandomAccessFile randomAccessFile2;
            Exception e;
            try {
                randomAccessFile = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                randomAccessFile2 = new RandomAccessFile(file, "rw");
                if (randomAccessFile2 != null) {
                    try {
                        int parseInt = Integer.parseInt("00001000", 2);
                        randomAccessFile2.seek(7L);
                        int read = randomAccessFile2.read();
                        if ((read & parseInt) > 0) {
                            randomAccessFile2.seek(7L);
                            randomAccessFile2.write((parseInt ^ (-1)) & 255 & read);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                                return;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                randomAccessFile2 = null;
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:100:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:104:0x00b7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:108:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:116:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:120:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:134:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void Zip() {
            /*
                Method dump skipped, instructions count: 261
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.ZipHelper.Zip():void");
        }
    }

    private TbsLogReport(Context context) {
        this.c = context.getApplicationContext();
        e();
    }

    public static TbsLogReport a(Context context) {
        if (b == null) {
            synchronized (TbsLogReport.class) {
                if (b == null) {
                    b = new TbsLogReport(context);
                }
            }
        }
        return b;
    }

    private String e(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j));
        } catch (Exception e) {
            return null;
        }
    }

    private void e() {
        this.d = 0L;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 2;
        this.k = EnvironmentCompat.MEDIA_UNKNOWN;
        this.l = 0;
        this.m = 2;
        this.n = 0L;
        this.o = 0L;
        this.p = 1;
        this.a = 0;
        this.q = null;
        this.r = null;
        this.s = 0L;
    }

    private String f(long j) {
        return j + "|";
    }

    private String f(String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        return sb.append(str).append("|").toString();
    }

    private JSONArray f() {
        String string = h().getString("tbs_download_upload", null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(string);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    public void g() {
        SharedPreferences.Editor edit = h().edit();
        edit.remove("tbs_download_upload");
        edit.commit();
    }

    private SharedPreferences h() {
        return this.c.getSharedPreferences("tbs_download_stat", 4);
    }

    private String i(int i) {
        return i + "|";
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a() {
        /*
            Method dump skipped, instructions count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.a():void");
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(int i, String str) {
        if (!(i == 200 || i == 220 || i == 221)) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured in installation, errorCode:" + i, true);
        }
        h(i);
        a(System.currentTimeMillis());
        e(str);
        QbSdk.j.onInstallFinish(i);
        a(EventType.TYPE_INSTALL);
    }

    public void a(int i, Throwable th) {
        a(th);
        a(i, this.r);
    }

    public void a(long j) {
        this.d = j;
    }

    public void a(EventType eventType) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(i(eventType.a));
        sb.append(f(b.c(this.c)));
        sb.append(f(w.a(this.c)));
        sb.append(i(aj.a().f(this.c)));
        String str2 = Build.MODEL;
        try {
            str = new String(str2.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e) {
            str = str2;
        }
        sb.append(f(str));
        String packageName = this.c.getPackageName();
        sb.append(f(packageName));
        if ("com.tencent.mm".equals(packageName)) {
            sb.append(f(b.a(this.c, "com.tencent.mm.BuildInfo.CLIENT_VERSION")));
        } else {
            sb.append(i(b.b(this.c)));
        }
        sb.append(f(e(this.d)));
        sb.append(f(this.e));
        sb.append(f(this.f));
        sb.append(i(this.g));
        sb.append(i(this.h));
        sb.append(i(this.i));
        sb.append(i(this.j));
        sb.append(f(this.k));
        sb.append(i(this.l));
        sb.append(i(this.m));
        sb.append(f(this.s));
        sb.append(f(this.n));
        sb.append(f(this.o));
        sb.append(i(this.p));
        sb.append(i(this.a));
        sb.append(f(this.q));
        sb.append(f(this.r));
        sb.append(i(TbsDownloadConfig.getInstance(this.c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)));
        sb.append(f(b.f(this.c)));
        sb.append(f("3.1.0.1034_43100"));
        sb.append(false);
        SharedPreferences h = h();
        JSONArray f = f();
        f.put(sb.toString());
        SharedPreferences.Editor edit = h.edit();
        edit.putString("tbs_download_upload", f.toString());
        edit.commit();
        e();
        new Thread(new ar(this)).start();
    }

    public void a(String str) {
        if (this.e == null) {
            this.e = str;
        } else {
            this.e += h.b + str;
        }
    }

    public void a(Throwable th) {
        if (th == null) {
            this.r = "";
            return;
        }
        String stackTraceString = Log.getStackTraceString(th);
        if (stackTraceString.length() > 1024) {
            stackTraceString = stackTraceString.substring(0, 1024);
        }
        this.r = stackTraceString;
    }

    public void b() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] Run in UIThread, Report delay");
            return;
        }
        synchronized (this) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat]");
            JSONArray f = f();
            if (f == null || f.length() == 0) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] no data");
                return;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] jsonArray:" + f);
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] response:" + n.a(x.a(this.c).c(), f.toString().getBytes("utf-8"), new at(this), true) + " testcase: -1");
        }
    }

    public void b(int i) {
        this.h = i;
    }

    public void b(int i, String str) {
        h(i);
        a(System.currentTimeMillis());
        e(str);
        a(EventType.TYPE_LOAD);
    }

    public void b(int i, Throwable th) {
        if (th != null) {
            String str = "msg: " + th.getMessage() + "; err: " + th + "; cause: " + Log.getStackTraceString(th.getCause());
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
            this.r = str;
        } else {
            this.r = "NULL";
        }
        b(i, this.r);
    }

    public void b(long j) {
        this.n = j;
    }

    public void b(String str) {
        this.f = str;
    }

    public int c() {
        return this.m;
    }

    public void c(int i) {
        this.i = i;
    }

    public void c(long j) {
        this.o += j;
    }

    public void c(String str) {
        this.k = str;
    }

    public void d() {
        try {
            e();
            SharedPreferences.Editor edit = h().edit();
            edit.clear();
            edit.commit();
        } catch (Exception e) {
        }
    }

    public void d(int i) {
        this.j = i;
    }

    public void d(long j) {
        this.s += j;
    }

    public void d(String str) {
        h(108);
        this.q = str;
    }

    public void e(int i) {
        this.l = i;
    }

    public void e(String str) {
        if (str != null) {
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
            this.r = str;
        }
    }

    public void f(int i) {
        this.m = i;
    }

    public void g(int i) {
        this.p = i;
    }

    public void h(int i) {
        if (!(i == 100 || i == 110 || i == 120 || i == 111 || i >= 400)) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured, errorCode:" + i, true);
        }
        if (i == 111) {
            TbsLog.i(TbsDownloader.LOGTAG, "you are not in wifi, downloading stoped", true);
        }
        this.a = i;
    }
}

package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.p;
import e.a.a.a.a;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class j {

    /* renamed from: d  reason: collision with root package name */
    public static int f1362d = 5;

    /* renamed from: e  reason: collision with root package name */
    public static int f1363e = 1;
    public static final String[] f = {"tbs_downloading_com.tencent.mtt", "tbs_downloading_com.tencent.mm", "tbs_downloading_com.tencent.mobileqq", "tbs_downloading_com.tencent.tbs", "tbs_downloading_com.qzone"};
    public boolean C;

    /* renamed from: a  reason: collision with root package name */
    public String f1364a;
    public Context g;
    public String h;
    public String i;
    public String j;
    public File k;
    public long l;
    public boolean o;
    public int p;
    public int q;
    public boolean r;
    public boolean s;
    public HttpURLConnection t;
    public String u;
    public TbsLogReport.TbsLogInfo v;
    public String w;
    public int x;
    public boolean y;
    public Handler z;
    public int m = 30000;
    public int n = 20000;
    public int B = f1362d;

    /* renamed from: b  reason: collision with root package name */
    public String[] f1365b = null;

    /* renamed from: c  reason: collision with root package name */
    public int f1366c = 0;
    public Set<String> A = new HashSet();

    public j(Context context) {
        this.g = context.getApplicationContext();
        this.v = TbsLogReport.getInstance(this.g).tbsLogInfo();
        StringBuilder a2 = a.a("tbs_downloading_");
        a2.append(this.g.getPackageName());
        this.u = a2.toString();
        m.a();
        this.k = m.s(this.g);
        if (this.k != null) {
            e();
            this.w = null;
            this.x = -1;
            return;
        }
        throw new NullPointerException("TbsCorePrivateDir is null!");
    }

    private long a(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        this.v.setDownConsumeTime(currentTimeMillis - j);
        this.v.setDownloadSize(j2);
        return currentTimeMillis;
    }

    @TargetApi(8)
    public static File a(Context context) {
        try {
            int i = Build.VERSION.SDK_INT;
            File file = new File(FileUtil.a(context, 4));
            if (file != null && !file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            return file;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    public static File a(Context context, int i) {
        File file = new File(FileUtil.a(context, i));
        if (file.exists() && file.isDirectory()) {
            if (new File(file, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false)).exists()) {
                return file;
            }
        }
        return null;
    }

    private String a(Throwable th) {
        String stackTraceString = Log.getStackTraceString(th);
        return stackTraceString.length() > 1024 ? stackTraceString.substring(0, 1024) : stackTraceString;
    }

    private String a(URL url) {
        try {
            return InetAddress.getByName(url.getHost()).getHostAddress();
        } catch (Error e2) {
            e2.printStackTrace();
            return BuildConfig.FLAVOR;
        } catch (Exception e3) {
            e3.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    private void a(int i, String str, boolean z) {
        if (z || this.p > this.B) {
            this.v.setErrorCode(i);
            this.v.setFailDetail(str);
        }
    }

    private void a(long j) {
        this.p++;
        if (j <= 0) {
            try {
                j = l();
            } catch (Exception unused) {
                return;
            }
        }
        Thread.sleep(j);
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(File file, Context context) {
        synchronized (com.tencent.smtt.utils.a.class) {
            if (file != null) {
                if (file.exists()) {
                    try {
                        File a2 = a(context);
                        if (a2 != null) {
                            File file2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) == 1 ? new File(a2, TbsDownloader.getBackupFileName(true)) : new File(a2, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false));
                            file2.delete();
                            FileUtil.b(file, file2);
                            boolean contains = file2.getName().contains("tbs.org");
                            boolean contains2 = file2.getName().contains("x5.tbs.decouple");
                            if (contains2 || contains) {
                                File[] listFiles = a2.listFiles();
                                Pattern compile = Pattern.compile(com.tencent.smtt.utils.a.a(contains2) + "(.*)");
                                for (File file3 : listFiles) {
                                    if (compile.matcher(file3.getName()).find() && file3.isFile() && file3.exists()) {
                                        file3.delete();
                                    }
                                }
                                File file4 = new File(a2, com.tencent.smtt.utils.a.a(contains2) + "." + TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                if (file4.exists()) {
                                    TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupTbsApk]delete bacup config file error ");
                                    return;
                                }
                                file4.createNewFile();
                            }
                            if (TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) != 1 && TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0) == com.tencent.smtt.utils.a.a(context, file)) {
                                int i = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
                                if (i == 5 || i == 3) {
                                    TbsLog.i("TbsApkDownloader", "response code=" + i + "return backup decouple apk");
                                }
                                File file5 = new File(a2, TbsDownloader.getBackupFileName(true));
                                if (com.tencent.smtt.utils.a.a(context, file) != com.tencent.smtt.utils.a.a(context, file5)) {
                                    file5.delete();
                                    FileUtil.b(file, file5);
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    private void a(String str) {
        URL url = new URL(str);
        HttpURLConnection httpURLConnection = this.t;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Throwable th) {
                StringBuilder a2 = a.a("[initHttpRequest] mHttpRequest.disconnect() Throwable:");
                a2.append(th.toString());
                TbsLog.e(TbsDownloader.LOGTAG, a2.toString());
            }
        }
        this.t = (HttpURLConnection) url.openConnection();
        this.t.setRequestProperty("User-Agent", TbsDownloader.b(this.g));
        this.t.setRequestProperty("Accept-Encoding", "identity");
        this.t.setRequestMethod("GET");
        this.t.setInstanceFollowRedirects(false);
        this.t.setConnectTimeout(this.n);
        this.t.setReadTimeout(this.m);
    }

    private boolean a(File file) {
        int i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        if (i == 0) {
            i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        }
        return com.tencent.smtt.utils.a.a(this.g, file, 0L, i);
    }

    @TargetApi(8)
    public static File b(Context context) {
        try {
            int i = Build.VERSION.SDK_INT;
            File a2 = a(context, 4);
            if (a2 == null) {
                a2 = a(context, 3);
            }
            if (a2 == null) {
                a2 = a(context, 2);
            }
            return a2 == null ? a(context, 1) : a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    private boolean b(int i) {
        try {
            File file = new File(this.k, "x5.tbs");
            File a2 = a(this.g);
            if (a2 == null) {
                return false;
            }
            File file2 = new File(a2, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false));
            file.delete();
            FileUtil.b(file2, file);
            if (com.tencent.smtt.utils.a.a(this.g, file, 0L, i)) {
                return true;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] verifyTbsApk error!!");
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] Exception is " + e2.getMessage());
            return false;
        }
    }

    public static void c(Context context) {
        try {
            m.a();
            File s = m.s(context);
            new File(s, "x5.tbs").delete();
            new File(s, "x5.tbs.temp").delete();
            File a2 = a(context);
            if (a2 != null) {
                new File(a2, TbsDownloader.getBackupFileName(false)).delete();
                new File(a2, "x5.oversea.tbs.org").delete();
                File[] listFiles = a2.listFiles();
                Pattern compile = Pattern.compile(com.tencent.smtt.utils.a.a(true) + "(.*)");
                for (File file : listFiles) {
                    if (compile.matcher(file.getName()).find() && file.isFile() && file.exists()) {
                        file.delete();
                    }
                }
                Pattern compile2 = Pattern.compile(com.tencent.smtt.utils.a.a(false) + "(.*)");
                for (File file2 : listFiles) {
                    if (compile2.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private void c(boolean z) {
        Bundle a2;
        p.a(this.g);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(this.g);
        instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false);
        instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -123);
        instance.commit();
        QbSdk.m.onDownloadFinish(z ? 100 : 120);
        int i = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
        boolean a3 = TbsDownloader.a(this.g);
        if (i == 5) {
            a2 = a(i, a3);
            if (a2 == null) {
                return;
            }
        } else if (i == 3 || i > 10000) {
            File a4 = a(this.g);
            if (a4 != null) {
                a2 = a(i, a4, a3);
            } else {
                c();
                instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, true);
                instance.commit();
                return;
            }
        } else {
            m.a().a(this.g, new File(this.k, "x5.tbs").getAbsolutePath(), instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
            a(new File(this.k, "x5.tbs"), this.g);
            return;
        }
        m.a().b(this.g, a2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0085, code lost:
        if (r10 != r8) goto L_0x0087;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean c(boolean r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.c(boolean, boolean):boolean");
    }

    private boolean d(boolean z) {
        a.a("[TbsApkDownloader.deleteFile] isApk=", z, TbsDownloader.LOGTAG);
        File file = z ? new File(this.k, "x5.tbs") : new File(this.k, "x5.tbs.temp");
        if (file.exists()) {
            FileUtil.a(file, false);
        }
        return true;
    }

    private void e() {
        this.p = 0;
        this.q = 0;
        this.l = -1L;
        this.j = null;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
    }

    private void f() {
        TbsLogReport.EventType eventType;
        TbsLogReport tbsLogReport;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest]");
        HttpURLConnection httpURLConnection = this.t;
        if (httpURLConnection != null) {
            if (!this.r) {
                this.v.setResolveIp(a(httpURLConnection.getURL()));
            }
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                StringBuilder a2 = a.a("[closeHttpRequest] mHttpRequest.disconnect() Throwable:");
                a2.append(th.toString());
                TbsLog.e(TbsDownloader.LOGTAG, a2.toString());
            }
            this.t = null;
        }
        TbsLogReport.TbsLogInfo tbsLogInfo = this.v;
        int i = tbsLogInfo.f1238a;
        if (this.r || !this.y) {
            TbsDownloader.f1218a = false;
            return;
        }
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        String apnInfo = Apn.getApnInfo(this.g);
        if (apnInfo == null) {
            apnInfo = BuildConfig.FLAVOR;
        }
        int apnType = Apn.getApnType(this.g);
        this.v.setApn(apnInfo);
        this.v.setNetworkType(apnType);
        if (apnType != this.x || !apnInfo.equals(this.w)) {
            this.v.setNetworkChange(0);
        }
        int i2 = this.v.f1238a;
        if ((i2 == 0 || i2 == 107) && this.v.getDownFinalFlag() == 0 && (!Apn.isNetworkAvailable(this.g) || !k())) {
            a(101, (String) null, true);
        }
        if (TbsDownloader.a(this.g)) {
            tbsLogReport = TbsLogReport.getInstance(this.g);
            eventType = TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE;
        } else {
            tbsLogReport = TbsLogReport.getInstance(this.g);
            eventType = TbsLogReport.EventType.TYPE_DOWNLOAD;
        }
        tbsLogReport.eventReport(eventType, this.v);
        this.v.resetArgs();
        if (i != 100) {
            QbSdk.m.onDownloadFinish(i);
        }
    }

    private File g() {
        return TbsDownloader.a(this.g) ? new File(FileUtil.a(this.g, 4), TbsDownloader.getBackupFileName(true)) : new File(FileUtil.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false));
    }

    private void h() {
        try {
            File g = g();
            if (g.exists()) {
                FileUtil.a(g, false);
                File[] listFiles = g.getParentFile().listFiles();
                Pattern compile = Pattern.compile(com.tencent.smtt.utils.a.a(TbsDownloader.a(this.g)) + "(.*)");
                for (File file : listFiles) {
                    if (compile.matcher(file.getName()).find() && file.isFile() && file.exists()) {
                        FileUtil.a(file, false);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean i() {
        return new File(this.k, "x5.tbs.temp").exists();
    }

    private long j() {
        File file = new File(this.k, "x5.tbs.temp");
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0083, code lost:
        if (r4 == null) goto L_0x0086;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0091 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.Closeable, java.io.Reader, java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean k() {
        /*
            r9 = this;
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            java.lang.String r1 = "www.qq.com"
            r2 = 0
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0073, all -> 0x006f
            r4.<init>()     // Catch: Throwable -> 0x0073, all -> 0x006f
            java.lang.String r5 = "ping "
            r4.append(r5)     // Catch: Throwable -> 0x0073, all -> 0x006f
            r4.append(r1)     // Catch: Throwable -> 0x0073, all -> 0x006f
            java.lang.String r1 = r4.toString()     // Catch: Throwable -> 0x0073, all -> 0x006f
            java.lang.Process r0 = r0.exec(r1)     // Catch: Throwable -> 0x0073, all -> 0x006f
            java.io.InputStream r0 = r0.getInputStream()     // Catch: Throwable -> 0x0073, all -> 0x006f
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: Throwable -> 0x0069, all -> 0x0066
            r1.<init>(r0)     // Catch: Throwable -> 0x0069, all -> 0x0066
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: Throwable -> 0x0060, all -> 0x005d
            r4.<init>(r1)     // Catch: Throwable -> 0x0060, all -> 0x005d
            r3 = 0
        L_0x002c:
            java.lang.String r5 = r4.readLine()     // Catch: Throwable -> 0x0058, all -> 0x0056
            r6 = 1
            if (r5 == 0) goto L_0x004a
            java.lang.String r7 = "TTL"
            boolean r7 = r5.contains(r7)     // Catch: Throwable -> 0x0058, all -> 0x0056
            if (r7 != 0) goto L_0x0049
            java.lang.String r7 = "ttl"
            boolean r5 = r5.contains(r7)     // Catch: Throwable -> 0x0058, all -> 0x0056
            if (r5 == 0) goto L_0x0044
            goto L_0x0049
        L_0x0044:
            int r3 = r3 + r6
            r5 = 5
            if (r3 < r5) goto L_0x002c
            goto L_0x004a
        L_0x0049:
            r2 = 1
        L_0x004a:
            if (r0 == 0) goto L_0x004f
            r0.close()     // Catch: IOException -> 0x004f
        L_0x004f:
            r1.close()     // Catch: IOException -> 0x0052
        L_0x0052:
            r4.close()     // Catch: IOException -> 0x0086
            goto L_0x0086
        L_0x0056:
            r2 = move-exception
            goto L_0x0089
        L_0x0058:
            r3 = move-exception
            r8 = r3
            r3 = r0
            r0 = r8
            goto L_0x0076
        L_0x005d:
            r2 = move-exception
            r4 = r3
            goto L_0x0089
        L_0x0060:
            r4 = move-exception
            r8 = r3
            r3 = r0
            r0 = r4
            r4 = r8
            goto L_0x0076
        L_0x0066:
            r2 = move-exception
            r4 = r3
            goto L_0x008a
        L_0x0069:
            r1 = move-exception
            r4 = r3
            r3 = r0
            r0 = r1
            r1 = r4
            goto L_0x0076
        L_0x006f:
            r2 = move-exception
            r0 = r3
            r4 = r0
            goto L_0x008a
        L_0x0073:
            r0 = move-exception
            r1 = r3
            r4 = r1
        L_0x0076:
            r0.printStackTrace()     // Catch: all -> 0x0087
            if (r3 == 0) goto L_0x007e
            r3.close()     // Catch: IOException -> 0x007e
        L_0x007e:
            if (r1 == 0) goto L_0x0083
            r1.close()     // Catch: IOException -> 0x0083
        L_0x0083:
            if (r4 == 0) goto L_0x0086
            goto L_0x0052
        L_0x0086:
            return r2
        L_0x0087:
            r2 = move-exception
            r0 = r3
        L_0x0089:
            r3 = r1
        L_0x008a:
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch: IOException -> 0x008f
        L_0x008f:
            if (r3 == 0) goto L_0x0094
            r3.close()     // Catch: IOException -> 0x0094
        L_0x0094:
            if (r4 == 0) goto L_0x0099
            r4.close()     // Catch: IOException -> 0x0099
        L_0x0099:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.k():boolean");
    }

    private long l() {
        int i = this.p;
        return (i == 1 || i == 2) ? this.p * 20000 : (i == 3 || i == 4) ? 100000L : 200000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007a, code lost:
        if (r4 == null) goto L_0x0089;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m() {
        /*
            r7 = this;
            android.content.Context r0 = r7.g
            int r0 = com.tencent.smtt.utils.Apn.getApnType(r0)
            r1 = 0
            r2 = 3
            if (r0 != r2) goto L_0x000c
            r0 = 1
            goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            java.lang.String r2 = "[TbsApkDwonloader.detectWifiNetworkAvailable] isWifi="
            java.lang.String r3 = "TbsDownload"
            e.a.a.a.a.a(r2, r0, r3)
            r2 = 0
            if (r0 == 0) goto L_0x0088
            android.content.Context r0 = r7.g
            java.lang.String r0 = com.tencent.smtt.utils.Apn.getWifiSSID(r0)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "[TbsApkDwonloader.detectWifiNetworkAvailable] localBSSID="
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r4)
            java.net.URL r4 = new java.net.URL     // Catch: Throwable -> 0x0074, all -> 0x0071
            java.lang.String r5 = "http://pms.mb.qq.com/rsp204"
            r4.<init>(r5)     // Catch: Throwable -> 0x0074, all -> 0x0071
            java.net.URLConnection r4 = r4.openConnection()     // Catch: Throwable -> 0x0074, all -> 0x0071
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: Throwable -> 0x0074, all -> 0x0071
            r4.setInstanceFollowRedirects(r1)     // Catch: Throwable -> 0x006f, all -> 0x006d
            r2 = 10000(0x2710, float:1.4013E-41)
            r4.setConnectTimeout(r2)     // Catch: Throwable -> 0x006f, all -> 0x006d
            r4.setReadTimeout(r2)     // Catch: Throwable -> 0x006f, all -> 0x006d
            r4.setUseCaches(r1)     // Catch: Throwable -> 0x006f, all -> 0x006d
            r4.getInputStream()     // Catch: Throwable -> 0x006f, all -> 0x006d
            int r2 = r4.getResponseCode()     // Catch: Throwable -> 0x006f, all -> 0x006d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: Throwable -> 0x006f, all -> 0x006d
            r5.<init>()     // Catch: Throwable -> 0x006f, all -> 0x006d
            java.lang.String r6 = "[TbsApkDwonloader.detectWifiNetworkAvailable] responseCode="
            r5.append(r6)     // Catch: Throwable -> 0x006f, all -> 0x006d
            r5.append(r2)     // Catch: Throwable -> 0x006f, all -> 0x006d
            java.lang.String r5 = r5.toString()     // Catch: Throwable -> 0x006f, all -> 0x006d
            com.tencent.smtt.utils.TbsLog.i(r3, r5)     // Catch: Throwable -> 0x006f, all -> 0x006d
            r3 = 204(0xcc, float:2.86E-43)
            if (r2 != r3) goto L_0x007c
            r1 = 1
            goto L_0x007c
        L_0x006d:
            r0 = move-exception
            goto L_0x0082
        L_0x006f:
            r2 = move-exception
            goto L_0x0077
        L_0x0071:
            r0 = move-exception
            r4 = r2
            goto L_0x0082
        L_0x0074:
            r3 = move-exception
            r4 = r2
            r2 = r3
        L_0x0077:
            r2.printStackTrace()     // Catch: all -> 0x006d
            if (r4 == 0) goto L_0x0089
        L_0x007c:
            r4.disconnect()     // Catch: Exception -> 0x0080
            goto L_0x0089
        L_0x0080:
            goto L_0x0089
        L_0x0082:
            if (r4 == 0) goto L_0x0087
            r4.disconnect()     // Catch: Exception -> 0x0087
        L_0x0087:
            throw r0
        L_0x0088:
            r0 = r2
        L_0x0089:
            if (r1 != 0) goto L_0x00b1
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x00b1
            java.util.Set<java.lang.String> r2 = r7.A
            boolean r2 = r2.contains(r0)
            if (r2 != 0) goto L_0x00b1
            java.util.Set<java.lang.String> r2 = r7.A
            r2.add(r0)
            r7.n()
            android.os.Handler r2 = r7.z
            r3 = 150(0x96, float:2.1E-43)
            android.os.Message r2 = r2.obtainMessage(r3, r0)
            android.os.Handler r3 = r7.z
            r4 = 120000(0x1d4c0, double:5.9288E-319)
            r3.sendMessageDelayed(r2, r4)
        L_0x00b1:
            if (r1 == 0) goto L_0x00c0
            java.util.Set<java.lang.String> r2 = r7.A
            boolean r2 = r2.contains(r0)
            if (r2 == 0) goto L_0x00c0
            java.util.Set<java.lang.String> r2 = r7.A
            r2.remove(r0)
        L_0x00c0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.m():boolean");
    }

    private void n() {
        if (this.z == null) {
            this.z = new Handler(l.a().getLooper()) { // from class: com.tencent.smtt.sdk.j.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what == 150) {
                        j.this.m();
                    }
                }
            };
        }
    }

    public Bundle a(int i, File file, boolean z) {
        File file2;
        if (z) {
            file2 = new File(file, TbsDownloader.getBackupFileName(true));
        } else {
            file2 = new File(file, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false));
        }
        int a2 = com.tencent.smtt.utils.a.a(this.g, file2);
        File file3 = new File(this.k, "x5.tbs");
        String absolutePath = file3.exists() ? file3.getAbsolutePath() : null;
        int i2 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i);
        bundle.putInt("old_core_ver", a2);
        bundle.putInt("new_core_ver", i2);
        bundle.putString("old_apk_location", file2.getAbsolutePath());
        bundle.putString("new_apk_location", absolutePath);
        bundle.putString("diff_file_location", absolutePath);
        return bundle;
    }

    public Bundle a(int i, boolean z) {
        int i2;
        File file;
        m mVar;
        int i3;
        Context context;
        if (z) {
            file = m.a().p(this.g);
            i2 = m.a().h(this.g);
        } else {
            file = m.a().q(this.g);
            i2 = m.a().i(this.g);
        }
        File file2 = new File(this.k, "x5.tbs");
        String absolutePath = file2.exists() ? file2.getAbsolutePath() : null;
        if (absolutePath == null) {
            return null;
        }
        int i4 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        if (z) {
            mVar = m.a();
            context = this.g;
            i3 = 6;
        } else {
            mVar = m.a();
            context = this.g;
            i3 = 5;
        }
        File f2 = mVar.f(context, i3);
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i);
        bundle.putInt("old_core_ver", i2);
        bundle.putInt("new_core_ver", i4);
        bundle.putString("old_apk_location", file.getAbsolutePath());
        bundle.putString("new_apk_location", f2.getAbsolutePath());
        bundle.putString("diff_file_location", absolutePath);
        String a2 = FileUtil.a(this.g, 7);
        File file3 = new File(a2);
        if (!file3.exists()) {
            file3.mkdirs();
        }
        bundle.putString("backup_apk", new File(a2, i4 + ".tbs").getAbsolutePath());
        return bundle;
    }

    public void a(int i) {
        if (m.a().t(this.g)) {
            m.a().b();
            try {
                File file = new File(this.k, "x5.tbs");
                int a2 = com.tencent.smtt.utils.a.a(this.g, file);
                if (-1 == a2 || (i > 0 && i == a2)) {
                    FileUtil.a(file, false);
                }
            } catch (Exception unused) {
            }
        }
    }

    public boolean a() {
        TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #1");
        try {
            File file = new File(FileUtil.a(this.g, 4), TbsDownloader.getBackupFileName(true));
            if (file.exists()) {
                TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #2");
            } else {
                File b2 = TbsDownloader.b(TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, -1));
                if (b2 != null && b2.exists()) {
                    FileUtil.b(b2, file);
                }
            }
            if (!com.tencent.smtt.utils.a.a(this.g, file, 0L, TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, -1))) {
                return false;
            }
            TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #3");
            return m.a().e(this.g);
        } catch (Exception unused) {
            return false;
        }
    }

    public int b(boolean z) {
        File a2 = a(this.g);
        if (z) {
            if (a2 == null) {
                return 0;
            }
            return com.tencent.smtt.utils.a.a(this.g, new File(a2, TbsDownloader.getBackupFileName(true)));
        } else if (a2 == null) {
            return 0;
        } else {
            Context context = this.g;
            return com.tencent.smtt.utils.a.a(context, new File(a2, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false)));
        }
    }

    public void b() {
        TbsLogReport.EventType eventType;
        TbsLogReport tbsLogReport;
        this.r = true;
        if (TbsShareManager.isThirdPartyApp(this.g)) {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(this.g).tbsLogInfo();
            tbsLogInfo.setErrorCode(-309);
            tbsLogInfo.setFailDetail(new Exception());
            if (TbsDownloader.a(this.g)) {
                tbsLogReport = TbsLogReport.getInstance(this.g);
                eventType = TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE;
            } else {
                tbsLogReport = TbsLogReport.getInstance(this.g);
                eventType = TbsLogReport.EventType.TYPE_DOWNLOAD;
            }
            tbsLogReport.eventReport(eventType, tbsLogInfo);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(25:53|(5:572|55|(1:57)(4:58|579|59|(3:646|61|655))|64|(2:627|66))|568|74|(1:76)(1:77)|78|(1:80)|81|(2:83|(1:85)(13:86|577|91|(1:93)(1:94)|95|(2:100|(1:104))(1:99)|105|106|(1:108)|109|(3:119|(1:121)|122)|123|(2:643|125)(7:128|(6:133|(5:146|566|147|148|(4:628|150|151|(5:593|153|649|(1:155)|156)(4:648|159|160|(1:162)))(7:165|(3:585|169|(3:631|171|(1:173)))(1:650)|174|(1:(3:177|239|240))(2:178|(2:189|(2:206|(1:1)(2:212|(3:214|239|240)))(2:199|(3:634|201|(1:203))(1:(3:205|239|240))))(2:182|(3:633|184|(1:186))(1:(3:188|239|240))))|484|663|662))(3:661|137|(2:139|(2:141|240)(1:142))(3:647|143|144))|241|484|663|662)|220|575|221|222|(7:250|599|251|(4:595|253|(3:607|255|(19:257|258|270|621|271|272|611|273|274|597|275|619|276|277|(3:570|278|(1:563)(4:284|609|285|(1:623)(7:311|(2:313|(1:603)(3:615|321|(2:665|323)))(1:333)|334|601|335|(8:337|338|573|339|(3:341|605|342)(1:347)|(1:389)(3:587|351|(1:383)(1:666))|390|668)(2:392|667)|393)))|324|325|326|(4:583|372|651|(5:374|482|484|663|662)(4:375|484|663|662))(2:632|378)))|(18:269|270|621|271|272|611|273|274|597|275|619|276|277|(4:570|278|(0)(0)|393)|324|325|326|(0)(0))(19:266|258|270|621|271|272|611|273|274|597|275|619|276|277|(4:570|278|(0)(0)|393)|324|325|326|(0)(0)))(2:630|436)|625|437|(3:590|439|654)(1:653))(3:652|226|(1:1)(2:234|(1:1)(0))))))(1:89)|90|577|91|(0)(0)|95|(1:97)|100|(1:102)|104|105|106|(0)|109|(6:111|113|117|119|(0)|122)|123|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:128|(6:133|(5:146|566|147|148|(4:628|150|151|(5:593|153|649|(1:155)|156)(4:648|159|160|(1:162)))(7:165|(3:585|169|(3:631|171|(1:173)))(1:650)|174|(1:(3:177|239|240))(2:178|(2:189|(2:206|(1:1)(2:212|(3:214|239|240)))(2:199|(3:634|201|(1:203))(1:(3:205|239|240))))(2:182|(3:633|184|(1:186))(1:(3:188|239|240))))|484|663|662))(3:661|137|(2:139|(2:141|240)(1:142))(3:647|143|144))|241|484|663|662)|220|575|221|222|(7:250|599|251|(4:595|253|(3:607|255|(19:257|258|270|621|271|272|611|273|274|597|275|619|276|277|(3:570|278|(1:563)(4:284|609|285|(1:623)(7:311|(2:313|(1:603)(3:615|321|(2:665|323)))(1:333)|334|601|335|(8:337|338|573|339|(3:341|605|342)(1:347)|(1:389)(3:587|351|(1:383)(1:666))|390|668)(2:392|667)|393)))|324|325|326|(4:583|372|651|(5:374|482|484|663|662)(4:375|484|663|662))(2:632|378)))|(18:269|270|621|271|272|611|273|274|597|275|619|276|277|(4:570|278|(0)(0)|393)|324|325|326|(0)(0))(19:266|258|270|621|271|272|611|273|274|597|275|619|276|277|(4:570|278|(0)(0)|393)|324|325|326|(0)(0)))(2:630|436)|625|437|(3:590|439|654)(1:653))(3:652|226|(1:1)(2:234|(1:1)(0)))) */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x03d7, code lost:
        if (r32 == false) goto L_0x03d9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0430, code lost:
        if (r32 == false) goto L_0x03d9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x0546, code lost:
        if (r32 != false) goto L_0x0652;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x05d4, code lost:
        if (r32 == false) goto L_0x05d6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x05fa, code lost:
        a(113, "tbsApkFileSize=" + r8 + "  but contentLength=" + r31.l, true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).setDownloadInterruptCode(-310);
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x0638, code lost:
        if (r32 == false) goto L_0x063a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x06e4, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...Canceled!", true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).setDownloadInterruptCode(-309);
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x06f5, code lost:
        r3 = r33;
        r24 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x070f, code lost:
        if (r31.f1365b == null) goto L_0x0731;
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x0716, code lost:
        if (c(true, r7) != false) goto L_0x0731;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x0718, code lost:
        if (r32 != false) goto L_0x0726;
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x071e, code lost:
        if (a(false) == false) goto L_0x0726;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x0720, code lost:
        r3 = true;
        r4 = r7;
        r7 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x0726, code lost:
        r31.s = true;
        r3 = false;
        r19 = r5;
        r4 = r7;
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x0731, code lost:
        r31.s = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x0736, code lost:
        if (r31.f1365b == null) goto L_0x073a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x0738, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x073a, code lost:
        r3 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x073c, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).setDownloadInterruptCode(-311);
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x0747, code lost:
        r4 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x074a, code lost:
        r3 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x074b, code lost:
        r7 = r3;
        r4 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x0754, code lost:
        r8 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x0755, code lost:
        r19 = r5;
        r4 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x077a, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...failed because you exceeded max flow!", true);
        a(112, "downloadFlow=" + r14 + " downloadMaxflow=" + r5, true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).setDownloadInterruptCode(-307);
     */
    /* JADX WARN: Code restructure failed: missing block: B:360:0x089d, code lost:
        b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x08a2, code lost:
        if (com.tencent.smtt.sdk.QbSdk.m == null) goto L_0x08c0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:362:0x08a4, code lost:
        com.tencent.smtt.sdk.QbSdk.m.onDownloadFinish(111);
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x08c0, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "Download is paused due to NOT_WIFI error!", false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:369:0x08ce, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).setDownloadInterruptCode(-304);
     */
    /* JADX WARN: Code restructure failed: missing block: B:370:0x08d1, code lost:
        r3 = false;
        r7 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:379:0x08f6, code lost:
        r8 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:380:0x08f7, code lost:
        r3 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:381:0x08fc, code lost:
        r8 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:382:0x08fd, code lost:
        r3 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x09f9, code lost:
        if (r32 != false) goto L_0x0a15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:445:0x09fb, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).mSyncMap.put(r23, java.lang.Long.valueOf(r14));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:446:0x0a15, code lost:
        r6 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:468:0x0aa3, code lost:
        if (r32 == false) goto L_0x0bc0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:476:0x0acd, code lost:
        if (r32 != false) goto L_0x0b09;
     */
    /* JADX WARN: Code restructure failed: missing block: B:480:0x0aed, code lost:
        if (r32 == false) goto L_0x0aef;
     */
    /* JADX WARN: Code restructure failed: missing block: B:481:0x0aef, code lost:
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:483:0x0b09, code lost:
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:493:0x0b2b, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:494:0x0b2c, code lost:
        r16 = r3;
        r19 = r5;
        r4 = r7;
        r5 = r13;
        r21 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:495:0x0b35, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:496:0x0b36, code lost:
        r19 = r5;
        r5 = r13;
        r21 = r14;
        r4 = r4;
        r16 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x0b3f, code lost:
        r7 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:515:0x0bbe, code lost:
        if (r32 == false) goto L_0x0bc0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:516:0x0bc0, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).mSyncMap.put(r5, java.lang.Long.valueOf(r14));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01f8, code lost:
        if (r32 == false) goto L_0x01fa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0224, code lost:
        if (r32 == false) goto L_0x01fa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0227, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).mSyncMap.put(r13, java.lang.Long.valueOf(r14));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r31.g).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0233, code lost:
        r7 = r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x036b A[Catch: Throwable -> 0x031e, all -> 0x0245, TRY_ENTER, TRY_LEAVE, TryCatch #16 {all -> 0x0245, blocks: (B:55:0x0188, B:57:0x019d, B:58:0x01cb, B:59:0x01d5, B:61:0x01e3, B:64:0x0207, B:66:0x020f, B:76:0x024f, B:80:0x027a, B:83:0x0288, B:85:0x02a8, B:86:0x02da, B:97:0x0346, B:99:0x034b, B:102:0x0354, B:108:0x036b, B:111:0x0395, B:113:0x039d, B:115:0x03a6, B:117:0x03ae, B:119:0x03b4, B:121:0x03bb, B:122:0x03c2, B:125:0x03cc, B:137:0x03ef, B:139:0x03fd, B:144:0x0422, B:147:0x0435, B:151:0x0444, B:153:0x044a, B:160:0x0482, B:169:0x04a1, B:171:0x04a9, B:178:0x04c0, B:182:0x04ca, B:184:0x04dd, B:189:0x04f0, B:199:0x0506, B:201:0x050f, B:206:0x0522, B:208:0x052c, B:212:0x0534, B:215:0x053b, B:224:0x058e, B:226:0x0594, B:228:0x05b5, B:230:0x05bb, B:232:0x05c1, B:234:0x05c9, B:236:0x05cd, B:243:0x05fa, B:244:0x0625), top: B:572:0x0188 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03bb A[Catch: Throwable -> 0x031e, all -> 0x0245, TryCatch #16 {all -> 0x0245, blocks: (B:55:0x0188, B:57:0x019d, B:58:0x01cb, B:59:0x01d5, B:61:0x01e3, B:64:0x0207, B:66:0x020f, B:76:0x024f, B:80:0x027a, B:83:0x0288, B:85:0x02a8, B:86:0x02da, B:97:0x0346, B:99:0x034b, B:102:0x0354, B:108:0x036b, B:111:0x0395, B:113:0x039d, B:115:0x03a6, B:117:0x03ae, B:119:0x03b4, B:121:0x03bb, B:122:0x03c2, B:125:0x03cc, B:137:0x03ef, B:139:0x03fd, B:144:0x0422, B:147:0x0435, B:151:0x0444, B:153:0x044a, B:160:0x0482, B:169:0x04a1, B:171:0x04a9, B:178:0x04c0, B:182:0x04ca, B:184:0x04dd, B:189:0x04f0, B:199:0x0506, B:201:0x050f, B:206:0x0522, B:208:0x052c, B:212:0x0534, B:215:0x053b, B:224:0x058e, B:226:0x0594, B:228:0x05b5, B:230:0x05bb, B:232:0x05c1, B:234:0x05c9, B:236:0x05cd, B:243:0x05fa, B:244:0x0625), top: B:572:0x0188 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0a52 A[Catch: all -> 0x0b1b, TryCatch #52 {all -> 0x0b1b, blocks: (B:458:0x0a4b, B:460:0x0a52, B:464:0x0a5a, B:466:0x0a62, B:470:0x0aa9, B:472:0x0ab2, B:473:0x0aba, B:474:0x0ac1, B:478:0x0ad3), top: B:591:0x0a4b }] */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0ab2 A[Catch: all -> 0x0b1b, TryCatch #52 {all -> 0x0b1b, blocks: (B:458:0x0a4b, B:460:0x0a52, B:464:0x0a5a, B:466:0x0a62, B:470:0x0aa9, B:472:0x0ab2, B:473:0x0aba, B:474:0x0ac1, B:478:0x0ad3), top: B:591:0x0a4b }] */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0aba A[Catch: all -> 0x0b1b, TryCatch #52 {all -> 0x0b1b, blocks: (B:458:0x0a4b, B:460:0x0a52, B:464:0x0a5a, B:466:0x0a62, B:470:0x0aa9, B:472:0x0ab2, B:473:0x0aba, B:474:0x0ac1, B:478:0x0ad3), top: B:591:0x0a4b }] */
    /* JADX WARN: Removed duplicated region for block: B:506:0x0b62 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0c37  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x0c4b  */
    /* JADX WARN: Removed duplicated region for block: B:549:0x0c71  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x0c88  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x0cae  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x06e4 A[EDGE_INSN: B:563:0x06e4->B:280:0x06e4 ?: BREAK  , EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:583:0x08d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:632:0x08f2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:643:0x03cc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:656:0x0ad0 A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:660:0x0bb3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0332  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(boolean r32, boolean r33) {
        /*
            Method dump skipped, instructions count: 3271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.b(boolean, boolean):void");
    }

    public void c() {
        b();
        d(false);
        d(true);
    }

    public boolean d() {
        StringBuilder a2 = a.a("[TbsApkDownloader.isDownloadForeground] mIsDownloadForeground=");
        a2.append(this.C);
        TbsLog.i(TbsDownloader.LOGTAG, a2.toString());
        return this.C;
    }

    public boolean a(boolean z) {
        String[] strArr;
        int i;
        if ((z && !m() && (!QbSdk.F || !Apn.isNetworkAvailable(this.g))) || (strArr = this.f1365b) == null || (i = this.f1366c) < 0 || i >= strArr.length) {
            return false;
        }
        this.f1366c = i + 1;
        this.j = strArr[i];
        this.p = 0;
        this.q = 0;
        this.l = -1L;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
        return true;
    }

    public boolean a(boolean z, boolean z2) {
        String str;
        boolean z3;
        File g;
        TbsLogReport.EventType eventType;
        TbsLogReport tbsLogReport;
        if (Build.VERSION.SDK_INT == 23) {
            return false;
        }
        int i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        int i2 = m.a().i(this.g);
        if (i == 0) {
            i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            str = "by default key";
        } else {
            str = "by new key";
        }
        this.f1364a = str;
        if (!(i == 0 || i == i2)) {
            if (z2) {
                File a2 = TbsDownloader.a(i);
                if (a2 != null && a2.exists()) {
                    File file = new File(FileUtil.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false));
                    try {
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) != 1) {
                        FileUtil.b(a2, file);
                        z3 = true;
                        g = g();
                        if (g.exists() || !a(g)) {
                            h();
                            if (a2 != null && a2.exists() && !com.tencent.smtt.utils.a.a(this.g, a2, 0L, i) && a2.exists()) {
                                FileUtil.a(a2, false);
                            }
                        } else if (b(i)) {
                            TbsDownloadConfig.getInstance(this.g).mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -214);
                            TbsDownloadConfig.getInstance(this.g).setDownloadInterruptCode(-214);
                            c(false);
                            if (z3) {
                                StringBuilder a3 = a.a("use local backup apk in startDownload");
                                a3.append(this.f1364a);
                                a(100, a3.toString(), true);
                                if (TbsDownloader.a(this.g)) {
                                    tbsLogReport = TbsLogReport.getInstance(this.g);
                                    eventType = TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE;
                                } else {
                                    tbsLogReport = TbsLogReport.getInstance(this.g);
                                    eventType = TbsLogReport.EventType.TYPE_DOWNLOAD;
                                }
                                tbsLogReport.eventReport(eventType, this.v);
                                this.v.resetArgs();
                            }
                            return true;
                        }
                    }
                }
                z3 = false;
                g = g();
                if (g.exists()) {
                }
                h();
                if (a2 != null) {
                    FileUtil.a(a2, false);
                }
            }
            if (c(false, z2)) {
                TbsDownloadConfig.getInstance(this.g).mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -214);
                TbsDownloadConfig.getInstance(this.g).setDownloadInterruptCode(-214);
                c(false);
                return true;
            }
            d(true);
        }
        return false;
    }
}

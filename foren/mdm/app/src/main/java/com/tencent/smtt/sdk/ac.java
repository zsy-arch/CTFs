package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.aa;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.k;
import com.yolanda.nohttp.Headers;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.protocol.HTTP;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ac {
    private static int d = 5;
    private static int e = 1;
    private static final String[] f = {"tbs_downloading_com.tencent.mtt", "tbs_downloading_com.tencent.mm", "tbs_downloading_com.tencent.mobileqq", "tbs_downloading_com.tencent.tbs", "tbs_downloading_com.qzone"};
    private boolean C;
    String a;
    private Context g;
    private String h;
    private String i;
    private String j;
    private File k;
    private long l;
    private boolean o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private HttpURLConnection t;

    /* renamed from: u  reason: collision with root package name */
    private String f47u;
    private TbsLogReport v;
    private String w;
    private int x;
    private boolean y;
    private Handler z;
    private int m = 30000;
    private int n = 20000;
    private int B = d;
    String[] b = null;
    int c = 0;
    private Set<String> A = new HashSet();

    public ac(Context context) {
        this.g = context.getApplicationContext();
        this.v = TbsLogReport.a(this.g);
        this.f47u = "tbs_downloading_" + this.g.getPackageName();
        aj.a();
        this.k = aj.j(this.g);
        if (this.k == null) {
            throw new NullPointerException("TbsCorePrivateDir is null!");
        }
        e();
        this.w = null;
        this.x = -1;
    }

    private long a(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        this.v.c(currentTimeMillis - j);
        this.v.d(j2);
        return currentTimeMillis;
    }

    @TargetApi(8)
    static File a(Context context) {
        try {
            File file = Build.VERSION.SDK_INT >= 8 ? new File(k.a(context, 4)) : null;
            if (file == null || file.exists() || file.isDirectory()) {
                return file;
            }
            file.mkdirs();
            return file;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    private static File a(Context context, int i) {
        File file = new File(k.a(context, i));
        if (file == null || !file.exists() || !file.isDirectory()) {
            return null;
        }
        File file2 = new File(file, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        if (file2 == null || !file2.exists()) {
            return null;
        }
        return file;
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
            return "";
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }

    private void a(int i, String str, boolean z) {
        if (z || this.p > this.B) {
            this.v.h(i);
            this.v.e(str);
        }
    }

    private void a(long j) {
        this.p++;
        if (j <= 0) {
            try {
                j = m();
            } catch (Exception e2) {
                return;
            }
        }
        Thread.sleep(j);
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
            }
        }
    }

    public static void a(File file, Context context) {
        if (file != null && file.exists()) {
            try {
                File a = a(context);
                if (a != null) {
                    File file2 = new File(a, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                    file2.delete();
                    k.b(file, file2);
                }
            } catch (Exception e2) {
            }
        }
    }

    private void a(String str) {
        URL url = new URL(str);
        if (this.t != null) {
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[initHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
        }
        this.t = (HttpURLConnection) url.openConnection();
        this.t.setRequestProperty("User-Agent", TbsDownloader.a(this.g));
        this.t.setRequestProperty(Headers.HEAD_KEY_ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
        this.t.setRequestMethod("GET");
        this.t.setInstanceFollowRedirects(false);
        this.t.setConnectTimeout(this.n);
        this.t.setReadTimeout(this.m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(8)
    public static File b(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 8) {
                return null;
            }
            File a = a(context, 4);
            if (a == null) {
                a = a(context, 3);
            }
            if (a == null) {
                a = a(context, 2);
            }
            return a == null ? a(context, 1) : a;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    private boolean b(boolean z, boolean z2) {
        boolean z3;
        int i;
        long j;
        long j2 = 0;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z);
        File file = new File(this.k, !z ? "x5.tbs" : "x5.tbs.temp");
        if (!file.exists()) {
            return false;
        }
        String string = TbsDownloadConfig.getInstance(this.g).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPK_MD5, null);
        String a = a.a(file);
        if (string == null || !string.equals(a)) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " md5 failed");
            if (!z) {
                return false;
            }
            this.v.d("fileMd5 not match");
            return false;
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] md5(" + a + ") successful!");
        if (z) {
            long j3 = TbsDownloadConfig.getInstance(this.g).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0L);
            if (file == null || !file.exists()) {
                j = 0;
            } else if (j3 > 0) {
                j = file.length();
                if (j3 == j) {
                    j2 = j;
                }
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " filelength failed");
            this.v.d("fileLength:" + j + ",contentLength:" + j3);
            return false;
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] length(" + j2 + ") successful!");
        int i2 = -1;
        if (!z2 || (i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)) == (i2 = a.a(this.g, file))) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] tbsApkVersionCode(" + i2 + ") successful!");
            if (z2) {
                String a2 = b.a(this.g, file);
                if (!"3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(a2)) {
                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " signature failed");
                    if (!z) {
                        return false;
                    }
                    this.v.d("signature:" + (a2 == null ? f.b : Integer.valueOf(a2.length())));
                    return false;
                }
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] signature successful!");
            if (z) {
                try {
                    z3 = file.renameTo(new File(this.k, "x5.tbs"));
                    e = null;
                } catch (Exception e2) {
                    e = e2;
                    z3 = false;
                }
                if (!z3) {
                    a(109, a(e), true);
                    return false;
                }
            } else {
                z3 = false;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] rename(" + z3 + ") successful!");
            return true;
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " versionCode failed");
        if (!z) {
            return false;
        }
        this.v.d("fileVersion:" + i2 + ",configVersion:" + i);
        return false;
    }

    public static void c(Context context) {
        try {
            aj.a();
            File j = aj.j(context);
            new File(j, "x5.tbs").delete();
            new File(j, "x5.tbs.temp").delete();
            File a = a(context);
            if (a != null) {
                new File(a, "x5.tbs.org").delete();
                new File(a, "x5.oversea.tbs.org").delete();
            }
        } catch (Exception e2) {
        }
    }

    private void c(boolean z) {
        aa.a(this.g);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(this.g);
        instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false);
        instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -123);
        instance.commit();
        QbSdk.j.onDownloadFinish(z ? 100 : 120);
        int i = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
        if (i == 3 || i > 10000) {
            File a = a(this.g);
            if (a != null) {
                File file = new File(a, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                int a2 = a.a(this.g, file);
                File file2 = new File(this.k, "x5.tbs");
                String absolutePath = file2.exists() ? file2.getAbsolutePath() : null;
                int i2 = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
                Bundle bundle = new Bundle();
                bundle.putInt("operation", i);
                bundle.putInt("old_core_ver", a2);
                bundle.putInt("new_core_ver", i2);
                bundle.putString("old_apk_location", file.getAbsolutePath());
                bundle.putString("new_apk_location", absolutePath);
                bundle.putString("diff_file_location", absolutePath);
                aj.a().b(this.g, bundle);
                return;
            }
            c();
            instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, true);
            instance.commit();
            return;
        }
        aj.a().a(this.g, new File(this.k, "x5.tbs").getAbsolutePath(), instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
        a(new File(this.k, "x5.tbs"), this.g);
    }

    private boolean d(boolean z) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.deleteFile] isApk=" + z);
        File file = z ? new File(this.k, "x5.tbs") : new File(this.k, "x5.tbs.temp");
        if (file == null || !file.exists()) {
            return true;
        }
        return file.delete();
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
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest]");
        if (this.t != null) {
            if (!this.r) {
                this.v.b(a(this.t.getURL()));
            }
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[closeHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
            this.t = null;
        }
        int i = this.v.a;
        if (this.r || !this.y) {
            TbsDownloader.a = false;
            return;
        }
        this.v.a(System.currentTimeMillis());
        String apnInfo = Apn.getApnInfo(this.g);
        if (apnInfo == null) {
            apnInfo = "";
        }
        int apnType = Apn.getApnType(this.g);
        this.v.c(apnInfo);
        this.v.e(apnType);
        if (apnType != this.x || !apnInfo.equals(this.w)) {
            this.v.g(0);
        }
        if ((this.v.a == 0 || this.v.a == 107) && this.v.c() == 0) {
            if (!Apn.isNetworkAvailable(this.g)) {
                a(101, null, true);
            } else if (!l()) {
                a(101, null, true);
            }
        }
        this.v.a(TbsLogReport.EventType.TYPE_DOWNLOAD);
        if (i != 100) {
            QbSdk.j.onDownloadFinish(i);
        }
    }

    private boolean g() {
        try {
            File file = new File(this.k, "x5.tbs");
            File a = a(this.g);
            if (a != null) {
                File file2 = new File(a, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                file.delete();
                k.b(file2, file);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] Exception is " + e2.getMessage());
            return false;
        }
    }

    private boolean h() {
        File file = new File(k.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        int i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        if (i == 0) {
            i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        }
        return a.a(this.g, file, 0L, i);
    }

    private void i() {
        try {
            File file = new File(k.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (file != null && file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean j() {
        File file = new File(this.k, "x5.tbs.temp");
        return file != null && file.exists();
    }

    private long k() {
        File file = new File(this.k, "x5.tbs.temp");
        if (file == null || !file.exists()) {
            return 0L;
        }
        return file.length();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean l() {
        Closeable closeable;
        Closeable closeable2;
        Throwable th;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        boolean z = false;
        Closeable closeable3 = null;
        try {
            inputStream = Runtime.getRuntime().exec("ping www.qq.com").getInputStream();
            try {
                inputStreamReader = new InputStreamReader(inputStream);
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    int i = 0;
                    do {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else if (readLine.contains("TTL") || readLine.contains("ttl")) {
                                z = true;
                                break;
                            } else {
                                i++;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            closeable3 = inputStreamReader;
                            closeable = inputStream;
                            closeable2 = bufferedReader;
                            try {
                                th.printStackTrace();
                                a(closeable);
                                a(closeable3);
                                a(closeable2);
                                return z;
                            } catch (Throwable th3) {
                                th = th3;
                                inputStream = closeable;
                                inputStreamReader = closeable3;
                                closeable3 = closeable2;
                                a(inputStream);
                                a(inputStreamReader);
                                a(closeable3);
                                throw th;
                            }
                        }
                    } while (i < 5);
                    a(inputStream);
                    a(inputStreamReader);
                    a(bufferedReader);
                } catch (Throwable th4) {
                    th = th4;
                    closeable2 = null;
                    closeable3 = inputStreamReader;
                    closeable = inputStream;
                }
            } catch (Throwable th5) {
                th = th5;
                closeable2 = null;
                closeable = inputStream;
            }
        } catch (Throwable th6) {
            th = th6;
            closeable2 = null;
            closeable = null;
        }
        return z;
    }

    private long m() {
        switch (this.p) {
            case 1:
            case 2:
                return 20000 * this.p;
            case 3:
            case 4:
                return 20000 * 5;
            default:
                return 20000 * 10;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean n() {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ac.n():boolean");
    }

    private void o() {
        if (this.z == null) {
            this.z = new ad(this, ah.a().getLooper());
        }
    }

    public int a() {
        File a = a(this.g);
        if (a == null) {
            return 0;
        }
        return a.a(this.g, new File(a, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org"));
    }

    public void a(int i) {
        try {
            File file = new File(this.k, "x5.tbs");
            int a = a.a(this.g, file);
            if (-1 == a || (i > 0 && i == a)) {
                file.delete();
            }
        } catch (Exception e2) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:180:0x06f3, code lost:
        a(113, "tbsApkFileSize=" + r8 + "  but contentLength=" + r32.l, true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r32.g).setDownloadInterruptCode(-310);
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x074b, code lost:
        a(101, "WifiNetworkUnAvailable", true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r32.g).setDownloadInterruptCode(-304);
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x088f, code lost:
        if (r32.b == null) goto L_0x08b8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0898, code lost:
        if (b(true, r4) != false) goto L_0x08b8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x089a, code lost:
        if (r33 != false) goto L_0x08ad;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x08a3, code lost:
        if (b(false) == false) goto L_0x08ad;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x08a5, code lost:
        r8 = true;
        r6 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x08ad, code lost:
        r32.s = true;
        r5 = false;
        r6 = r8;
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x08b8, code lost:
        r32.s = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x08c1, code lost:
        if (r32.b == null) goto L_0x0f88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x08c3, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x08c4, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r32.g).setDownloadInterruptCode(-311);
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x08d1, code lost:
        r5 = r6;
        r6 = r8;
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x0a2a, code lost:
        b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x0a2f, code lost:
        if (com.tencent.smtt.sdk.QbSdk.j == null) goto L_0x0a38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x0a31, code lost:
        com.tencent.smtt.sdk.QbSdk.j.onDownloadFinish(111);
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x0a38, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "Download is paused due to NOT_WIFI error!", false);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r32.g).setDownloadInterruptCode(-304);
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x0a4d, code lost:
        r8 = false;
        r6 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x0e47, code lost:
        if (r33 != false) goto L_0x01ba;
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x0e49, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r32.g).a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r6));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r32.g).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x0f45, code lost:
        r5 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x0f6d, code lost:
        r8 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x0f6e, code lost:
        r10 = r15;
        r11 = r16;
        r5 = r6;
        r6 = r8;
        r9 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:420:0x0f88, code lost:
        r6 = r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:450:0x0e87 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x05bc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:477:0x0858 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:510:0x01b0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:516:0x01b0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r33) {
        /*
            Method dump skipped, instructions count: 3988
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ac.a(boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(boolean r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ac.a(boolean, boolean):boolean");
    }

    public void b() {
        this.r = true;
        if (TbsShareManager.isThirdPartyApp(this.g)) {
            TbsLogReport.a(this.g).h(-309);
            TbsLogReport.a(this.g).a(new Exception());
            TbsLogReport.a(this.g).a(TbsLogReport.EventType.TYPE_DOWNLOAD);
        }
    }

    public boolean b(boolean z) {
        if ((z && !n() && (!QbSdk.getDownloadWithoutWifi() || !Apn.isNetworkAvailable(this.g))) || this.b == null || this.c < 0 || this.c >= this.b.length) {
            return false;
        }
        String[] strArr = this.b;
        int i = this.c;
        this.c = i + 1;
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

    public void c() {
        b();
        d(false);
        d(true);
    }

    public boolean d() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.isDownloadForeground] mIsDownloadForeground=" + this.C);
        return this.C;
    }
}

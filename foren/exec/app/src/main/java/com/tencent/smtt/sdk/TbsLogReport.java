package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.f;
import com.tencent.smtt.utils.j;
import com.tencent.smtt.utils.m;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class TbsLogReport {

    /* renamed from: a */
    public static TbsLogReport f1229a;

    /* renamed from: b */
    public Handler f1230b;

    /* renamed from: c */
    public Context f1231c;

    /* renamed from: d */
    public boolean f1232d = false;

    /* loaded from: classes.dex */
    public enum EventType {
        TYPE_DOWNLOAD(0),
        TYPE_INSTALL(1),
        TYPE_LOAD(2),
        TYPE_DOWNLOAD_DECOUPLE(3),
        TYPE_INSTALL_DECOUPLE(4),
        TYPE_COOKIE_DB_SWITCH(5),
        TYPE_SDK_REPORT_INFO(6);
        

        /* renamed from: a */
        public int f1237a;

        EventType(int i) {
            this.f1237a = i;
        }
    }

    /* loaded from: classes.dex */
    public static class TbsLogInfo implements Cloneable {

        /* renamed from: a */
        public int f1238a;

        /* renamed from: b */
        public long f1239b;

        /* renamed from: c */
        public String f1240c;

        /* renamed from: d */
        public String f1241d;

        /* renamed from: e */
        public int f1242e;
        public int f;
        public int g;
        public int h;
        public String i;
        public int j;
        public int k;
        public long l;
        public long m;
        public int n;
        public String o;
        public String p;
        public long q;

        public TbsLogInfo() {
            resetArgs();
        }

        public /* synthetic */ TbsLogInfo(AnonymousClass1 r1) {
            resetArgs();
        }

        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException unused) {
                return this;
            }
        }

        public int getDownFinalFlag() {
            return this.k;
        }

        public void resetArgs() {
            this.f1239b = 0L;
            this.f1240c = null;
            this.f1241d = null;
            this.f1242e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 2;
            this.i = "unknown";
            this.j = 0;
            this.k = 2;
            this.l = 0L;
            this.m = 0L;
            this.n = 1;
            this.f1238a = 0;
            this.o = null;
            this.p = null;
            this.q = 0L;
        }

        public void setApn(String str) {
            this.i = str;
        }

        public void setCheckErrorDetail(String str) {
            setErrorCode(108);
            this.o = str;
        }

        public void setDownConsumeTime(long j) {
            this.m += j;
        }

        public void setDownFinalFlag(int i) {
            this.k = i;
        }

        public void setDownloadCancel(int i) {
            this.g = i;
        }

        public void setDownloadSize(long j) {
            this.q += j;
        }

        public void setDownloadUrl(String str) {
            if (this.f1240c != null) {
                str = this.f1240c + ";" + str;
            }
            this.f1240c = str;
        }

        public void setErrorCode(int i) {
            if (!(i == 100 || i == 110 || i == 120 || i == 111 || i >= 400)) {
                TbsLog.i(TbsDownloader.LOGTAG, "error occured, errorCode:" + i, true);
            }
            if (i == 111) {
                TbsLog.i(TbsDownloader.LOGTAG, "you are not in wifi, downloading stoped", true);
            }
            this.f1238a = i;
        }

        public void setEventTime(long j) {
            this.f1239b = j;
        }

        public void setFailDetail(String str) {
            if (str != null) {
                if (str.length() > 1024) {
                    str = str.substring(0, 1024);
                }
                this.p = str;
            }
        }

        public void setFailDetail(Throwable th) {
            if (th == null) {
                this.p = BuildConfig.FLAVOR;
                return;
            }
            String stackTraceString = Log.getStackTraceString(th);
            if (stackTraceString.length() > 1024) {
                stackTraceString = stackTraceString.substring(0, 1024);
            }
            this.p = stackTraceString;
        }

        public void setHttpCode(int i) {
            this.f1242e = i;
        }

        public void setNetworkChange(int i) {
            this.n = i;
        }

        public void setNetworkType(int i) {
            this.j = i;
        }

        public void setPatchUpdateFlag(int i) {
            this.f = i;
        }

        public void setPkgSize(long j) {
            this.l = j;
        }

        public void setResolveIp(String str) {
            this.f1241d = str;
        }

        public void setUnpkgFlag(int i) {
            this.h = i;
        }
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public final String f1243a;

        /* renamed from: b */
        public final String f1244b;

        public a(String str, String str2) {
            this.f1243a = str;
            this.f1244b = str2;
        }

        public static void a(File file) {
            RandomAccessFile randomAccessFile;
            Throwable th;
            Exception e2;
            try {
                randomAccessFile = null;
                try {
                    try {
                        randomAccessFile = new RandomAccessFile(file, "rw");
                    } catch (Exception e3) {
                        e2 = e3;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            try {
                int parseInt = Integer.parseInt("00001000", 2);
                randomAccessFile.seek(7L);
                int read = randomAccessFile.read();
                if ((read & parseInt) > 0) {
                    randomAccessFile.seek(7L);
                    randomAccessFile.write((~parseInt) & WebView.NORMAL_MODE_ALPHA & read);
                }
                randomAccessFile.close();
            } catch (Exception e5) {
                e2 = e5;
                randomAccessFile = randomAccessFile;
                e2.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th3) {
                th = th3;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        }

        /* JADX WARN: Can't wrap try/catch for region: R(8:115|3|(7:111|4|5|83|6|109|7)|(10:113|8|101|9|(2:10|(1:12)(1:117))|13|93|14|89|17)|39|91|40|43) */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x008d, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x008e, code lost:
            r0.printStackTrace();
         */
        /* JADX WARN: Removed duplicated region for block: B:105:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:87:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a() {
            /*
                Method dump skipped, instructions count: 238
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.a.a():void");
        }
    }

    public TbsLogReport(Context context) {
        this.f1230b = null;
        this.f1231c = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("TbsLogReportThread");
        handlerThread.start();
        this.f1230b = new Handler(handlerThread.getLooper()) { // from class: com.tencent.smtt.sdk.TbsLogReport.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 600) {
                    Object obj = message.obj;
                    if (obj instanceof TbsLogInfo) {
                        try {
                            int i2 = message.arg1;
                            TbsLogReport.this.a(i2, (TbsLogInfo) obj);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                } else if (i == 601) {
                    TbsLogReport.this.b();
                }
            }
        };
    }

    private String a(int i) {
        return i + "|";
    }

    private String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j));
        } catch (Exception unused) {
            return null;
        }
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        sb.append(str);
        sb.append("|");
        return sb.toString();
    }

    private JSONArray a() {
        String string = d().getString("tbs_download_upload", null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() > 5) {
                JSONArray jSONArray2 = new JSONArray();
                int length = jSONArray.length() - 1;
                if (length > jSONArray.length() - 5) {
                    jSONArray2.put(jSONArray.get(length));
                    return jSONArray2;
                }
            }
            return jSONArray;
        } catch (Exception unused) {
            return new JSONArray();
        }
    }

    public void a(int i, TbsLogInfo tbsLogInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(a(i));
        sb.append(a(b.d(this.f1231c)));
        sb.append(a(j.a(this.f1231c)));
        sb.append(a(m.a().i(this.f1231c)));
        String str = Build.MODEL;
        try {
            str = new String(str.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception unused) {
        }
        sb.append(a(str));
        String packageName = this.f1231c.getPackageName();
        sb.append(a(packageName));
        sb.append(TbsConfig.APP_WX.equals(packageName) ? a(b.a(this.f1231c, TbsDownloader.TBS_METADATA)) : a(b.b(this.f1231c)));
        sb.append(a(a(tbsLogInfo.f1239b)));
        sb.append(a(tbsLogInfo.f1240c));
        sb.append(a(tbsLogInfo.f1241d));
        sb.append(a(tbsLogInfo.f1242e));
        sb.append(a(tbsLogInfo.f));
        sb.append(a(tbsLogInfo.g));
        sb.append(a(tbsLogInfo.h));
        sb.append(a(tbsLogInfo.i));
        sb.append(a(tbsLogInfo.j));
        sb.append(a(tbsLogInfo.k));
        sb.append(b(tbsLogInfo.q));
        sb.append(b(tbsLogInfo.l));
        sb.append(b(tbsLogInfo.m));
        sb.append(a(tbsLogInfo.n));
        sb.append(a(tbsLogInfo.f1238a));
        sb.append(a(tbsLogInfo.o));
        sb.append(a(tbsLogInfo.p));
        sb.append(a(TbsDownloadConfig.getInstance(this.f1231c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)));
        sb.append(a(b.g(this.f1231c)));
        sb.append(a("4.3.0.39_43939"));
        sb.append(false);
        SharedPreferences d2 = d();
        JSONArray a2 = a();
        JSONArray jSONArray = new JSONArray();
        if (jSONArray.length() >= 5) {
            for (int i2 = 4; i2 >= 1; i2--) {
                try {
                    jSONArray.put(a2.get(jSONArray.length() - i2));
                } catch (Exception unused2) {
                    TbsLog.e("upload", "JSONArray transform error!");
                }
            }
            a2 = jSONArray;
        }
        a2.put(sb.toString());
        SharedPreferences.Editor edit = d2.edit();
        edit.putString("tbs_download_upload", a2.toString());
        edit.commit();
        if (this.f1232d || i != EventType.TYPE_LOAD.f1237a) {
            b();
        }
    }

    private void a(int i, TbsLogInfo tbsLogInfo, EventType eventType) {
        tbsLogInfo.setErrorCode(i);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        QbSdk.m.onInstallFinish(i);
        eventReport(eventType, tbsLogInfo);
    }

    private String b(long j) {
        return j + "|";
    }

    public void b() {
        String str;
        String str2;
        Map<String, Object> map = QbSdk.n;
        if (map == null || !map.containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) || !QbSdk.n.get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            str = TbsDownloader.LOGTAG;
            TbsLog.i(str, "[TbsApkDownloadStat.reportDownloadStat]");
            JSONArray a2 = a();
            if (a2.length() == 0) {
                str2 = "[TbsApkDownloadStat.reportDownloadStat] no data";
            } else {
                TbsLog.i(str, "[TbsApkDownloadStat.reportDownloadStat] jsonArray:" + a2);
                try {
                    String a3 = f.a(m.a(this.f1231c).c(), a2.toString().getBytes("utf-8"), new f.a() { // from class: com.tencent.smtt.sdk.TbsLogReport.3
                        @Override // com.tencent.smtt.utils.f.a
                        public void a(int i) {
                            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] onHttpResponseCode:" + i);
                            if (i < 300) {
                                TbsLogReport.this.c();
                            }
                        }
                    }, true);
                    TbsLog.i(str, "[TbsApkDownloadStat.reportDownloadStat] response:" + a3 + " testcase: -1");
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
        } else {
            str = "upload";
            str2 = "[TbsLogReport.sendLogReportRequest] -- SET_SENDREQUEST_AND_UPLOAD is false";
        }
        TbsLog.i(str, str2);
    }

    public void c() {
        SharedPreferences.Editor edit = d().edit();
        edit.remove("tbs_download_upload");
        edit.commit();
    }

    private SharedPreferences d() {
        return this.f1231c.getSharedPreferences("tbs_download_stat", 4);
    }

    public static TbsLogReport getInstance(Context context) {
        if (f1229a == null) {
            synchronized (TbsLogReport.class) {
                if (f1229a == null) {
                    f1229a = new TbsLogReport(context);
                }
            }
        }
        return f1229a;
    }

    public void clear() {
        try {
            SharedPreferences.Editor edit = d().edit();
            edit.clear();
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public void dailyReport() {
        this.f1230b.sendEmptyMessage(601);
    }

    public void eventReport(EventType eventType, TbsLogInfo tbsLogInfo) {
        try {
            Message obtainMessage = this.f1230b.obtainMessage();
            obtainMessage.what = 600;
            obtainMessage.arg1 = eventType.f1237a;
            obtainMessage.obj = (TbsLogInfo) tbsLogInfo.clone();
            this.f1230b.sendMessage(obtainMessage);
        } catch (Throwable th) {
            StringBuilder a2 = e.a.a.a.a.a("[TbsLogReport.eventReport] error, message=");
            a2.append(th.getMessage());
            TbsLog.w("upload", a2.toString());
        }
    }

    public boolean getShouldUploadEventReport() {
        return this.f1232d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x012a, code lost:
        if (r0 != 0) goto L_0x0103;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0127 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0153 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0122 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void reportTbsLog() {
        /*
            Method dump skipped, instructions count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.reportTbsLog():void");
    }

    public void setInstallErrorCode(int i, String str) {
        setInstallErrorCode(i, str, EventType.TYPE_INSTALL);
    }

    public void setInstallErrorCode(int i, String str, EventType eventType) {
        if (!(i == 200 || i == 220 || i == 221)) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured in installation, errorCode:" + i, true);
        }
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(str);
        a(i, tbsLogInfo, eventType);
    }

    public void setInstallErrorCode(int i, Throwable th) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(th);
        a(i, tbsLogInfo, EventType.TYPE_INSTALL);
    }

    public void setLoadErrorCode(int i, String str) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setErrorCode(i);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        tbsLogInfo.setFailDetail(str);
        eventReport(EventType.TYPE_LOAD, tbsLogInfo);
    }

    public void setLoadErrorCode(int i, Throwable th) {
        String str;
        if (th != null) {
            StringBuilder a2 = e.a.a.a.a.a("msg: ");
            a2.append(th.getMessage());
            a2.append("; err: ");
            a2.append(th);
            a2.append("; cause: ");
            a2.append(Log.getStackTraceString(th.getCause()));
            str = a2.toString();
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
        } else {
            str = "NULL";
        }
        setLoadErrorCode(i, str);
    }

    public void setShouldUploadEventReport(boolean z) {
        this.f1232d = z;
    }

    public TbsLogInfo tbsLogInfo() {
        return new TbsLogInfo(null);
    }
}

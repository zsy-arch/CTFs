package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.smtt.sdk.TbsListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes.dex */
public class TbsDownloadUpload {

    /* renamed from: b */
    public static TbsDownloadUpload f1213b;

    /* renamed from: a */
    public Map<String, Object> f1214a = new HashMap();

    /* renamed from: c */
    public Context f1215c;

    /* renamed from: d */
    public int f1216d;

    /* renamed from: e */
    public int f1217e;
    public int f;
    public int g;
    public int h;
    public int i;
    public SharedPreferences mPreferences;

    /* loaded from: classes.dex */
    public interface TbsUploadKey {
        public static final String KEY_LOCAL_CORE_VERSION = "tbs_local_core_version";
        public static final String KEY_NEEDDOWNLOAD_CODE = "tbs_needdownload_code";
        public static final String KEY_NEEDDOWNLOAD_RETURN = "tbs_needdownload_return";
        public static final String KEY_NEEDDOWNLOAD_SENT = "tbs_needdownload_sent";
        public static final String KEY_STARTDOWNLOAD_CODE = "tbs_startdownload_code";
        public static final String KEY_STARTDOWNLOAD_SENT = "tbs_startdownload_sent";
    }

    public TbsDownloadUpload(Context context) {
        this.mPreferences = context.getSharedPreferences("tbs_download_upload", 4);
        this.f1215c = context.getApplicationContext();
        if (this.f1215c == null) {
            this.f1215c = context;
        }
    }

    public static File a(Context context, String str) {
        m.a();
        File s = m.s(context);
        if (s == null) {
            return null;
        }
        File file = new File(s, str);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static synchronized void clear() {
        synchronized (TbsDownloadUpload.class) {
            f1213b = null;
        }
    }

    public static synchronized TbsDownloadUpload getInstance() {
        TbsDownloadUpload tbsDownloadUpload;
        synchronized (TbsDownloadUpload.class) {
            tbsDownloadUpload = f1213b;
        }
        return tbsDownloadUpload;
    }

    public static synchronized TbsDownloadUpload getInstance(Context context) {
        TbsDownloadUpload tbsDownloadUpload;
        synchronized (TbsDownloadUpload.class) {
            if (f1213b == null) {
                f1213b = new TbsDownloadUpload(context);
            }
            tbsDownloadUpload = f1213b;
        }
        return tbsDownloadUpload;
    }

    public void clearUploadCode() {
        this.f1214a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, 0);
        this.f1214a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, 0);
        this.f1214a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, 0);
        this.f1214a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_SENT, 0);
        this.f1214a.put(TbsUploadKey.KEY_STARTDOWNLOAD_SENT, 0);
        this.f1214a.put(TbsUploadKey.KEY_LOCAL_CORE_VERSION, 0);
        writeTbsDownloadInfo();
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }

    public synchronized int getLocalCoreVersion() {
        return this.i;
    }

    public synchronized int getNeedDownloadCode() {
        if (this.g == 1) {
            return TbsListener.ErrorCode.NEEDDOWNLOAD_9;
        }
        return this.f1216d;
    }

    public synchronized int getNeedDownloadReturn() {
        return this.f;
    }

    public synchronized int getStartDownloadCode() {
        if (this.h == 1) {
            return TbsListener.ErrorCode.STARTDOWNLOAD_9;
        }
        return this.f1217e;
    }

    public synchronized void readTbsDownloadInfo(Context context) {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        Throwable th2;
        BufferedInputStream bufferedInputStream2;
        File a2;
        try {
            bufferedInputStream = null;
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            a2 = a(this.f1215c, "download_upload");
        } catch (Throwable th4) {
            th = th4;
            if (0 != 0) {
                try {
                    bufferedInputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            throw th;
        }
        if (a2 != null) {
            bufferedInputStream2 = new BufferedInputStream(new FileInputStream(a2));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream2);
                String property = properties.getProperty(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, BuildConfig.FLAVOR);
                if (!BuildConfig.FLAVOR.equals(property)) {
                    this.f1216d = Math.max(Integer.parseInt(property), 0);
                }
                String property2 = properties.getProperty(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, BuildConfig.FLAVOR);
                if (!BuildConfig.FLAVOR.equals(property2)) {
                    this.f1217e = Math.max(Integer.parseInt(property2), 0);
                }
                String property3 = properties.getProperty(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, BuildConfig.FLAVOR);
                if (!BuildConfig.FLAVOR.equals(property3)) {
                    this.f = Math.max(Integer.parseInt(property3), 0);
                }
                String property4 = properties.getProperty(TbsUploadKey.KEY_NEEDDOWNLOAD_SENT, BuildConfig.FLAVOR);
                if (!BuildConfig.FLAVOR.equals(property4)) {
                    this.g = Math.max(Integer.parseInt(property4), 0);
                }
                String property5 = properties.getProperty(TbsUploadKey.KEY_STARTDOWNLOAD_SENT, BuildConfig.FLAVOR);
                if (!BuildConfig.FLAVOR.equals(property5)) {
                    this.h = Math.max(Integer.parseInt(property5), 0);
                }
                String property6 = properties.getProperty(TbsUploadKey.KEY_LOCAL_CORE_VERSION, BuildConfig.FLAVOR);
                if (!BuildConfig.FLAVOR.equals(property6)) {
                    this.i = Math.max(Integer.parseInt(property6), 0);
                }
            } catch (Throwable th5) {
                th2 = th5;
                th2.printStackTrace();
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                    }
                }
            }
            try {
                bufferedInputStream2.close();
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void writeTbsDownloadInfo() {
        /*
            Method dump skipped, instructions count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadUpload.writeTbsDownloadInfo():void");
    }
}

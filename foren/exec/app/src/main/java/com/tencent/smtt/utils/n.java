package com.tencent.smtt.utils;

import android.content.Context;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.QbSdk;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/* loaded from: classes.dex */
public class n {

    /* renamed from: e  reason: collision with root package name */
    public static n f1585e;

    /* renamed from: b  reason: collision with root package name */
    public Context f1587b;

    /* renamed from: c  reason: collision with root package name */
    public File f1588c = null;

    /* renamed from: a  reason: collision with root package name */
    public boolean f1586a = false;

    /* renamed from: d  reason: collision with root package name */
    public boolean f1589d = false;
    public File f = null;

    public n(Context context) {
        this.f1587b = null;
        this.f1587b = context.getApplicationContext();
        b();
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            nVar = f1585e;
        }
        return nVar;
    }

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (f1585e == null) {
                f1585e = new n(context);
            }
            nVar = f1585e;
        }
        return nVar;
    }

    private File d() {
        try {
            if (this.f1588c == null) {
                this.f1588c = new File(QbSdk.getTbsFolderDir(this.f1587b), "core_private");
                if (this.f1588c == null || !this.f1588c.isDirectory()) {
                    return null;
                }
            }
            File file = new File(this.f1588c, "debug.conf");
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(boolean z) {
        this.f1589d = z;
        c();
    }

    public synchronized void b() {
        Throwable th;
        Throwable th2;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                if (this.f == null) {
                    this.f = d();
                }
            } catch (Throwable th3) {
                th2 = th3;
            }
            if (this.f != null) {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(this.f));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream2);
                    String property = properties.getProperty("setting_forceUseSystemWebview", BuildConfig.FLAVOR);
                    if (!BuildConfig.FLAVOR.equals(property)) {
                        this.f1586a = Boolean.parseBoolean(property);
                    }
                    try {
                        bufferedInputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    bufferedInputStream = bufferedInputStream2;
                    th2.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e3) {
                            e = e3;
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public void c() {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        Throwable th2;
        try {
            BufferedInputStream bufferedInputStream2 = null;
            r0 = null;
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                File d2 = d();
                if (d2 != null) {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(d2));
                    try {
                        Properties properties = new Properties();
                        properties.load(bufferedInputStream);
                        properties.setProperty("setting_forceUseSystemWebview", Boolean.toString(this.f1586a));
                        properties.setProperty("result_systemWebviewForceUsed", Boolean.toString(this.f1589d));
                        BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(d2));
                        try {
                            properties.store(bufferedOutputStream3, (String) null);
                            try {
                                bufferedInputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            bufferedOutputStream3.close();
                        } catch (Throwable th3) {
                            th2 = th3;
                            bufferedOutputStream = bufferedOutputStream3;
                            bufferedInputStream2 = bufferedInputStream;
                            try {
                                th2.printStackTrace();
                                try {
                                    bufferedInputStream2.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                bufferedOutputStream.close();
                            } catch (Throwable th4) {
                                th = th4;
                                bufferedInputStream = bufferedInputStream2;
                                bufferedOutputStream2 = bufferedOutputStream;
                                bufferedInputStream.close();
                                bufferedOutputStream2.close();
                                throw th;
                            }
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        bufferedOutputStream = null;
                    }
                } else {
                    try {
                        throw null;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        try {
                            throw null;
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                }
            } catch (Throwable th6) {
                th2 = th6;
                bufferedOutputStream = null;
            }
        } catch (Exception e6) {
            while (true) {
                e6.printStackTrace();
                return;
            }
        }
    }
}

package com.tencent.smtt.utils;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/* loaded from: classes2.dex */
public class y {
    private static y e = null;
    private Context b;
    private File c = null;
    public boolean a = false;
    private boolean d = false;
    private File f = null;

    private y(Context context) {
        this.b = null;
        this.b = context.getApplicationContext();
        b();
    }

    public static synchronized y a() {
        y yVar;
        synchronized (y.class) {
            yVar = e;
        }
        return yVar;
    }

    public static synchronized y a(Context context) {
        y yVar;
        synchronized (y.class) {
            if (e == null) {
                e = new y(context);
            }
            yVar = e;
        }
        return yVar;
    }

    private File d() {
        File file;
        try {
            if (this.c == null) {
                this.c = new File(this.b.getDir("tbs", 0), "core_private");
                if (this.c == null || !this.c.isDirectory()) {
                    return null;
                }
            }
            file = new File(this.c, "debug.conf");
            if (file != null && !file.exists()) {
                file.createNewFile();
            }
        } catch (Throwable th) {
            th.printStackTrace();
            file = null;
        }
        return file;
    }

    public void a(boolean z) {
        this.d = z;
        c();
    }

    public synchronized void b() {
        Throwable th;
        Throwable th2;
        try {
            BufferedInputStream bufferedInputStream = null;
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
                    String property = properties.getProperty("setting_forceUseSystemWebview", "");
                    if (!"".equals(property)) {
                        this.a = Boolean.parseBoolean(property);
                    }
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } else if (0 != 0) {
                try {
                    bufferedInputStream.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
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
        Properties properties;
        BufferedOutputStream bufferedOutputStream2 = null;
        bufferedOutputStream2 = null;
        BufferedInputStream bufferedInputStream2 = null;
        BufferedInputStream bufferedInputStream3 = null;
        BufferedOutputStream bufferedOutputStream3 = null;
        try {
            File d = d();
            if (d == null) {
                try {
                    bufferedInputStream3.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    bufferedOutputStream3.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(d));
                try {
                    properties = new Properties();
                    properties.load(bufferedInputStream);
                    properties.setProperty("setting_forceUseSystemWebview", Boolean.toString(this.a));
                    properties.setProperty("result_systemWebviewForceUsed", Boolean.toString(this.d));
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(d));
                } catch (Throwable th3) {
                    th2 = th3;
                    bufferedOutputStream = null;
                    bufferedInputStream2 = bufferedInputStream;
                }
                try {
                    properties.store(bufferedOutputStream, (String) null);
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    bufferedInputStream2 = bufferedInputStream;
                    try {
                        th2.printStackTrace();
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedInputStream = bufferedInputStream2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        bufferedInputStream.close();
                        bufferedOutputStream2.close();
                        throw th;
                    }
                }
            }
        } catch (Throwable th6) {
            th = th6;
            bufferedInputStream = null;
        }
    }
}

package com.tencent.smtt.utils;

import android.os.Handler;
import android.os.HandlerThread;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;

/* loaded from: classes.dex */
public class k implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public static String f1575a = "TBSFileLock";
    public static Object f = new Object();
    public static Object g = new Object();
    public static HashMap<k, Object> h = null;
    public static Handler i = null;

    /* renamed from: b  reason: collision with root package name */
    public File f1576b;

    /* renamed from: c  reason: collision with root package name */
    public RandomAccessFile f1577c = null;

    /* renamed from: d  reason: collision with root package name */
    public FileLock f1578d = null;

    /* renamed from: e  reason: collision with root package name */
    public long f1579e = 0;

    public k(File file, String str) {
        this.f1576b = null;
        this.f1576b = new File(file, "." + str + ".lock");
    }

    public Handler a() {
        if (i == null) {
            synchronized (k.class) {
                if (i == null) {
                    HandlerThread handlerThread = new HandlerThread("QBFileLock.Thread");
                    handlerThread.start();
                    i = new Handler(handlerThread.getLooper());
                }
            }
        }
        return i;
    }

    public synchronized void a(boolean z) {
        String str = f1575a;
        String str2 = ">>> release lock: " + this.f1576b.getName();
        if (this.f1578d != null) {
            try {
                this.f1578d.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.f1578d = null;
        }
        if (this.f1577c != null) {
            try {
                this.f1577c.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.f1577c = null;
        }
        if (i != null && this.f1579e > 0) {
            i.removeCallbacks(this);
        }
        if (z) {
            d();
        }
    }

    public synchronized void b() {
        FileChannel channel;
        try {
            this.f1577c = new RandomAccessFile(this.f1576b, "rw");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!(this.f1577c == null || (channel = this.f1577c.getChannel()) == null)) {
            if (this.f1579e > 0) {
                a().postDelayed(this, this.f1579e);
            }
            FileLock fileLock = null;
            long currentTimeMillis = System.currentTimeMillis();
            while (true) {
                try {
                    fileLock = channel.lock();
                    if (fileLock != null) {
                        break;
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    String str = f1575a;
                }
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
                if (Math.abs(System.currentTimeMillis() - currentTimeMillis) >= 1000) {
                    String str2 = f1575a;
                    break;
                }
            }
            this.f1578d = fileLock;
            String str3 = f1575a;
            String str4 = ">>> lock [" + this.f1576b.getName() + "] cost: " + (System.currentTimeMillis() - currentTimeMillis);
        }
        if (this.f1578d != null) {
            c();
        }
    }

    public void c() {
        synchronized (g) {
            if (h == null) {
                h = new HashMap<>();
            }
            h.put(this, f);
        }
    }

    public void d() {
        synchronized (g) {
            if (h != null) {
                h.remove(this);
            }
        }
    }

    public void e() {
        a(true);
    }

    @Override // java.lang.Runnable
    public void run() {
        String str = f1575a;
        e();
    }
}

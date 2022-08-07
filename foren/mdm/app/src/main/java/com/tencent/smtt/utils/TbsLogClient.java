package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes2.dex */
public class TbsLogClient {
    static TbsLogClient a = null;
    static File c = null;
    static String d = null;
    static byte[] e = null;
    TextView b;
    private SimpleDateFormat f;
    private Context g;
    private String h = "";

    /* loaded from: classes2.dex */
    private class a implements Runnable {
        String a;

        a(String str) {
            this.a = null;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TbsLogClient.this.b != null) {
                TbsLogClient.this.b.append(this.a + "\n");
            }
        }
    }

    public TbsLogClient(Context context) {
        this.f = null;
        this.g = null;
        try {
            this.g = context.getApplicationContext();
            this.f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception e2) {
            this.f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }
    }

    private void a() {
        try {
            if (c == null) {
                if (Environment.getExternalStorageState().equals("mounted")) {
                    String a2 = k.a(this.g, 6);
                    if (a2 == null) {
                        c = null;
                    } else {
                        c = new File(a2, "tbslog.txt");
                        d = LogFileUtils.createKey();
                        e = LogFileUtils.createHeaderText(c.getName(), d);
                    }
                } else {
                    c = null;
                }
            }
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        } catch (SecurityException e3) {
            e3.printStackTrace();
        }
    }

    public void d(String str, String str2) {
    }

    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    public void i(String str, String str2) {
    }

    public void setLogView(TextView textView) {
        this.b = textView;
    }

    public void showLog(String str) {
        if (this.b != null) {
            this.b.post(new a(str));
        }
    }

    public void v(String str, String str2) {
    }

    public void w(String str, String str2) {
    }

    public void writeLog(String str) {
        this.h += this.f.format(Long.valueOf(System.currentTimeMillis())) + " pid=" + Process.myPid() + " tid=" + Process.myTid() + str + "\n";
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            writeLogToDisk();
        }
    }

    public void writeLogToDisk() {
        a();
        if (c != null) {
            LogFileUtils.writeDataToStorage(c, d, e, this.h, true);
            this.h = "";
        }
    }
}

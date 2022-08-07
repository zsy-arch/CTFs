package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes.dex */
public class TbsLogClient {

    /* renamed from: a */
    public static TbsLogClient f1492a = null;

    /* renamed from: c */
    public static File f1493c = null;

    /* renamed from: d */
    public static String f1494d = null;

    /* renamed from: e */
    public static byte[] f1495e = null;
    public static boolean i = true;

    /* renamed from: b */
    public TextView f1496b;
    public SimpleDateFormat f;
    public Context g;
    public StringBuffer h = new StringBuffer();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: a */
        public String f1497a;

        public a(String str) {
            TbsLogClient.this = r1;
            this.f1497a = null;
            this.f1497a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TextView textView = TbsLogClient.this.f1496b;
            if (textView != null) {
                textView.append(this.f1497a + "\n");
            }
        }
    }

    public TbsLogClient(Context context) {
        this.f = null;
        this.g = null;
        try {
            this.g = context.getApplicationContext();
            this.f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception unused) {
            this.f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }
    }

    private void a() {
        String a2;
        try {
            if (f1493c == null) {
                if (!Environment.getExternalStorageState().equals("mounted") || (a2 = FileUtil.a(this.g, 6)) == null) {
                    f1493c = null;
                } else {
                    f1493c = new File(a2, "tbslog.txt");
                    f1494d = LogFileUtils.createKey();
                    f1495e = LogFileUtils.createHeaderText(f1493c.getName(), f1494d);
                }
            }
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        } catch (SecurityException e3) {
            e3.printStackTrace();
        }
    }

    public static void setWriteLogJIT(boolean z) {
        i = z;
    }

    public void d(String str, String str2) {
    }

    public void e(String str, String str2) {
    }

    public void i(String str, String str2) {
    }

    public void setLogView(TextView textView) {
        this.f1496b = textView;
    }

    public void showLog(String str) {
        TextView textView = this.f1496b;
        if (textView != null) {
            textView.post(new a(str));
        }
    }

    public void v(String str, String str2) {
    }

    public void w(String str, String str2) {
    }

    public void writeLog(String str) {
        try {
            String format = this.f.format(Long.valueOf(System.currentTimeMillis()));
            StringBuffer stringBuffer = this.h;
            stringBuffer.append(format);
            stringBuffer.append(" pid=");
            stringBuffer.append(Process.myPid());
            stringBuffer.append(" tid=");
            stringBuffer.append(Process.myTid());
            stringBuffer.append(str);
            stringBuffer.append("\n");
            if (Thread.currentThread() != Looper.getMainLooper().getThread() || i) {
                writeLogToDisk();
            }
            if (this.h.length() > 524288) {
                this.h.delete(0, this.h.length());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void writeLogToDisk() {
        try {
            a();
            if (f1493c != null) {
                LogFileUtils.writeDataToStorage(f1493c, f1494d, f1495e, this.h.toString(), true);
                this.h.delete(0, this.h.length());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

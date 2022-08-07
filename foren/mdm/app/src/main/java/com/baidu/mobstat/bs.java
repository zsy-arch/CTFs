package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.Timer;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
public class bs {
    private static bs a = new bs();
    private Timer e;
    private WeakReference<Context> h;
    private Handler i;
    private boolean b = false;
    private SendStrategyEnum c = SendStrategyEnum.APP_START;
    private int d = 1;
    private int f = 0;
    private boolean g = false;

    private bs() {
        HandlerThread handlerThread = new HandlerThread("LogSenderThread");
        handlerThread.start();
        this.i = new Handler(handlerThread.getLooper());
    }

    public static bs a() {
        return a;
    }

    private String a(Context context, String str, String str2) {
        return b(context, str, str2);
    }

    private String b(Context context, String str, String str2) {
        HttpURLConnection d = cl.d(context, str);
        d.setDoOutput(true);
        d.setInstanceFollowRedirects(false);
        d.setUseCaches(false);
        d.setRequestProperty("Content-Type", "gzip");
        d.connect();
        cr.a("AdUtil.httpPost connected");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(d.getOutputStream())));
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            bufferedWriter.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(readLine);
            }
            int contentLength = d.getContentLength();
            if (d.getResponseCode() == 200 && contentLength == 0) {
                return sb.toString();
            }
            throw new IOException("http code = " + d.getResponseCode() + "; contentResponse = " + ((Object) sb));
        } finally {
            d.disconnect();
        }
    }

    public boolean b(Context context, String str) {
        boolean z = false;
        if (!this.b || cu.m(context)) {
            try {
                a(context, "https://hmma.baidu.com/app.gif", str);
                z = true;
            } catch (Exception e) {
                cr.c(e);
            }
            cr.a("send log data over. result = " + z + "; data=" + str);
        }
        return z;
    }

    private void e(Context context) {
        if (context == null) {
            cr.a("initContext context = " + ((Object) null));
        }
        if (this.h == null && context != null) {
            this.h = new WeakReference<>(context);
        }
    }

    public void f(Context context) {
        if (!this.b || cu.m(context)) {
            this.i.post(new bw(this, context));
        }
    }

    public void a(int i) {
        if (i >= 0 && i <= 30) {
            this.f = i;
        }
    }

    public void a(Context context) {
        e(context);
        SendStrategyEnum sendStrategyEnum = SendStrategyEnum.APP_START;
        try {
            String a2 = cu.a(context, "BaiduMobAd_EXCEPTION_LOG");
            if (!TextUtils.isEmpty(a2)) {
                if ("true".equals(a2)) {
                    bn.a().a(context);
                    be.a().a(context, true);
                } else if ("false".equals(a2)) {
                    be.a().a(context, false);
                }
            }
        } catch (Exception e) {
            cr.a(e);
        }
        try {
            String a3 = cu.a(context, "BaiduMobAd_SEND_STRATEGY");
            if (!TextUtils.isEmpty(a3)) {
                if (a3.equals(SendStrategyEnum.APP_START.name())) {
                    sendStrategyEnum = SendStrategyEnum.APP_START;
                    be.a().a(context, sendStrategyEnum.ordinal());
                } else if (a3.equals(SendStrategyEnum.ONCE_A_DAY.name())) {
                    sendStrategyEnum = SendStrategyEnum.ONCE_A_DAY;
                    be.a().a(context, sendStrategyEnum.ordinal());
                    be.a().b(context, 24);
                } else if (a3.equals(SendStrategyEnum.SET_TIME_INTERVAL.name())) {
                    sendStrategyEnum = SendStrategyEnum.SET_TIME_INTERVAL;
                    be.a().a(context, sendStrategyEnum.ordinal());
                }
            }
        } catch (Exception e2) {
            cr.a(e2);
            sendStrategyEnum = sendStrategyEnum;
        }
        try {
            String a4 = cu.a(context, "BaiduMobAd_TIME_INTERVAL");
            if (!TextUtils.isEmpty(a4)) {
                int parseInt = Integer.parseInt(a4);
                if (sendStrategyEnum.ordinal() == SendStrategyEnum.SET_TIME_INTERVAL.ordinal() && parseInt > 0 && parseInt <= 24) {
                    be.a().b(context, parseInt);
                }
            }
        } catch (Exception e3) {
            cr.a(e3);
        }
        try {
            String a5 = cu.a(context, "BaiduMobAd_ONLY_WIFI");
            if (TextUtils.isEmpty(a5)) {
                return;
            }
            if ("true".equals(a5)) {
                be.a().b(context, true);
            } else if ("false".equals(a5)) {
                be.a().b(context, false);
            }
        } catch (Exception e4) {
            cr.a(e4);
        }
    }

    public void a(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (!sendStrategyEnum.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            this.c = sendStrategyEnum;
            be.a().a(context, this.c.ordinal());
            if (sendStrategyEnum.equals(SendStrategyEnum.ONCE_A_DAY)) {
                be.a().b(context, 24);
            }
        } else if (i <= 0 || i > 24) {
            cr.c("timeInterval is invalid, new strategy does not work");
        } else {
            this.d = i;
            this.c = SendStrategyEnum.SET_TIME_INTERVAL;
            be.a().a(context, this.c.ordinal());
            be.a().b(context, this.d);
        }
        this.b = z;
        be.a().b(context, this.b);
        cr.a("sstype is:" + this.c.name() + " And timeInterval is:" + this.d + " And mOnlyWifi:" + this.b);
    }

    public void a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            this.i.post(new bx(this, context, str));
        }
    }

    public void a(boolean z, Context context) {
        e(context);
        this.g = z;
        cr.a("APP_ANALYSIS_EXCEPTION is:" + this.g);
        be.a().a(context, this.g);
    }

    public void b(Context context) {
        e(context);
        Context context2 = context == null ? this.h.get() : context;
        if (context2 != null) {
            this.i.post(new bt(this, context2));
        }
    }

    public void c(Context context) {
        be.a().a(context, System.currentTimeMillis());
    }

    public void d(Context context) {
        Context applicationContext = context.getApplicationContext();
        long j = this.d * 3600000;
        this.e = new Timer();
        this.e.schedule(new bv(this, applicationContext), j, j);
    }
}

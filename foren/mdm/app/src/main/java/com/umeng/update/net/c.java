package com.umeng.update.net;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.util.HanziToPinyin;
import com.umeng.update.UpdateConfig;
import com.umeng.update.net.DownloadingService;
import com.umeng.update.net.a;
import com.umeng.update.util.DeltaUpdate;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import u.upd.j;
import u.upd.k;
import u.upd.l;
import u.upd.n;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DownloadTool.java */
/* loaded from: classes2.dex */
public class c {
    static final int a = 0;
    static final int b = 1;
    static final int c = 1;
    static final int d = 2;
    private static final String e = c.class.getName();
    private SparseArray<b> f;
    private Map<a.C0091a, Messenger> g;
    private e h;

    public c(SparseArray<b> sparseArray, Map<a.C0091a, Messenger> map, e eVar) {
        this.f = sparseArray;
        this.g = map;
        this.h = eVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadTool.java */
    /* loaded from: classes2.dex */
    public static class b {
        DownloadingService.b a;
        a b;
        int c;
        int d;
        a.C0091a e;
        long[] f = new long[3];

        public b(a.C0091a aVar, int i) {
            this.c = i;
            this.e = aVar;
        }

        public void a(SparseArray<b> sparseArray) {
            sparseArray.put(this.c, this);
        }

        public void b(SparseArray<b> sparseArray) {
            if (sparseArray.indexOfKey(this.c) >= 0) {
                sparseArray.remove(this.c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadTool.java */
    /* loaded from: classes2.dex */
    public static class a extends com.umeng.update.util.b {
        public a(Context context) {
            super(context);
        }

        public a a(RemoteViews remoteViews) {
            this.c.contentView = remoteViews;
            return this;
        }

        public a a(CharSequence charSequence) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.d.setContentText(charSequence);
            }
            this.c.contentView.setTextViewText(j.a(this.b), charSequence);
            return this;
        }

        public a b(CharSequence charSequence) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.d.setContentTitle(charSequence);
            }
            this.c.contentView.setTextViewText(j.d(this.b), charSequence);
            return this;
        }

        public a a(int i, int i2, boolean z) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.d.setProgress(i, i2, z);
            }
            this.c.contentView.setProgressBar(j.c(this.b), 100, i2, false);
            return this;
        }

        public a a() {
            this.c.contentView.setViewVisibility(j.e(this.b), 8);
            this.c.contentView.setViewVisibility(j.g(this.b), 8);
            return this;
        }

        public a a(PendingIntent pendingIntent, PendingIntent pendingIntent2) {
            this.c.contentView.setOnClickPendingIntent(j.e(this.b), pendingIntent);
            this.c.contentView.setViewVisibility(j.e(this.b), 0);
            this.c.contentView.setViewVisibility(j.g(this.b), 0);
            this.c.contentView.setOnClickPendingIntent(j.g(this.b), pendingIntent2);
            return this;
        }

        public a b() {
            int e = j.e(this.b);
            this.c.contentView.setTextViewText(e, this.b.getResources().getString(l.e(this.b.getApplicationContext())));
            this.c.contentView.setInt(e, "setBackgroundResource", u.upd.c.a(this.b).c("umeng_common_gradient_green"));
            return this;
        }

        public a c() {
            int e = j.e(this.b);
            this.c.contentView.setTextViewText(e, this.b.getResources().getString(l.d(this.b.getApplicationContext())));
            this.c.contentView.setInt(e, "setBackgroundResource", u.upd.c.a(this.b).c("umeng_common_gradient_orange"));
            return this;
        }

        public Notification d() {
            if (Build.VERSION.SDK_INT >= 16) {
                return this.d.build();
            }
            if (Build.VERSION.SDK_INT >= 14) {
                return this.d.getNotification();
            }
            return this.c;
        }

        public void a(int i, String str, PendingIntent pendingIntent) {
            if (Build.VERSION.SDK_INT >= 16) {
                this.d.addAction(i, str, pendingIntent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(a.C0091a aVar) {
        return Math.abs((int) ((aVar.b.hashCode() >> 2) + (aVar.c.hashCode() >> 3) + System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a a(Context context, a.C0091a aVar, int i, int i2) {
        Context applicationContext = context.getApplicationContext();
        a aVar2 = new a(applicationContext);
        aVar2.d(applicationContext.getString(l.j(applicationContext))).a(17301633).a(PendingIntent.getActivity(applicationContext, 0, new Intent(), 134217728)).a(System.currentTimeMillis());
        RemoteViews remoteViews = new RemoteViews(applicationContext.getPackageName(), k.a(applicationContext));
        if (Build.VERSION.SDK_INT >= 14) {
            int dimensionPixelSize = applicationContext.getResources().getDimensionPixelSize(17104901);
            int dimensionPixelSize2 = applicationContext.getResources().getDimensionPixelSize(17104902);
            remoteViews.setInt(j.b(applicationContext), "setWidth", dimensionPixelSize);
            remoteViews.setInt(j.b(applicationContext), "setHeight", dimensionPixelSize2);
            try {
                Field declaredField = Class.forName("com.android.internal.R$drawable").getDeclaredField("notify_panel_notification_icon_bg_tile");
                declaredField.setAccessible(true);
                remoteViews.setInt(j.b(applicationContext), "setBackgroundResource", declaredField.getInt(null));
            } catch (Exception e2) {
                u.upd.b.a(e, "No notification icon background found:", e2);
            }
        } else {
            try {
                Field declaredField2 = Class.forName("com.android.internal.R$drawable").getDeclaredField("status_bar_notification_icon_bg");
                declaredField2.setAccessible(true);
                remoteViews.setInt(j.b(applicationContext), "setBackgroundResource", declaredField2.getInt(null));
            } catch (Exception e3) {
                try {
                    Class<?> cls = Class.forName("com.android.internal.R$dimen");
                    Field declaredField3 = cls.getDeclaredField("status_bar_edge_ignore");
                    declaredField3.setAccessible(true);
                    int i3 = declaredField3.getInt(null);
                    Field declaredField4 = cls.getDeclaredField("status_bar_height");
                    declaredField4.setAccessible(true);
                    int i4 = declaredField4.getInt(null);
                    remoteViews.setInt(j.b(applicationContext), "setWidth", applicationContext.getResources().getDimensionPixelSize(i4) + applicationContext.getResources().getDimensionPixelSize(i3) + 0 + applicationContext.getResources().getDimensionPixelSize(i4));
                } catch (Exception e4) {
                    u.upd.b.a(e, "No notification size found:", e4);
                }
            }
        }
        aVar2.a(remoteViews);
        aVar2.b(applicationContext.getResources().getString(l.g(applicationContext)) + aVar.b).a(i2 + "%").a(100, i2, false);
        if (aVar.g) {
            aVar2.b(remoteViews);
            aVar2.e();
            PendingIntent b2 = f.b(applicationContext, f.a(i, f.b));
            PendingIntent b3 = f.b(applicationContext, f.a(i, f.c));
            a(applicationContext, aVar2, i, 2);
            aVar2.a(b2, b3).c().a(true).b(false);
        } else {
            aVar2.a().a(true).b(false);
        }
        return aVar2;
    }

    void a(Context context, a aVar, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            PendingIntent b2 = f.b(context, f.a(i, f.b));
            PendingIntent b3 = f.b(context, f.a(i, f.c));
            switch (i2) {
                case 1:
                    aVar.a(17301540, context.getResources().getString(l.e(context)), b2);
                    break;
                case 2:
                    aVar.a(17301539, context.getResources().getString(l.d(context)), b2);
                    break;
            }
            aVar.a(17301560, context.getResources().getString(l.f(context)), b3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(a.C0091a aVar, boolean z, Messenger messenger) {
        if (z) {
            int nextInt = new Random().nextInt(1000);
            if (this.g != null) {
                for (a.C0091a aVar2 : this.g.keySet()) {
                    u.upd.b.c(e, "_" + nextInt + " downling  " + aVar2.b + "   " + aVar2.c);
                }
            } else {
                u.upd.b.c(e, "_" + nextInt + "downling  null");
            }
        }
        if (this.g == null) {
            return false;
        }
        for (a.C0091a aVar3 : this.g.keySet()) {
            if (aVar.e != null && aVar.e.equals(aVar3.e)) {
                this.g.put(aVar3, messenger);
                return true;
            } else if (aVar3.c.equals(aVar.c)) {
                this.g.put(aVar3, messenger);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(a.C0091a aVar) {
        for (int i = 0; i < this.f.size(); i++) {
            int keyAt = this.f.keyAt(i);
            if (aVar.e != null && aVar.e.equals(this.f.get(keyAt).e.e)) {
                return this.f.get(keyAt).c;
            }
            if (this.f.get(keyAt).e.c.equals(aVar.c)) {
                return this.f.get(keyAt).c;
            }
        }
        return -1;
    }

    void a(Context context, int i) {
        Context applicationContext = context.getApplicationContext();
        b bVar = this.f.get(i);
        bVar.b.e();
        a(applicationContext, bVar.b, i, 1);
        bVar.b.b(applicationContext.getResources().getString(l.h(applicationContext)) + bVar.e.b).b().a(false).b(true);
        ((NotificationManager) applicationContext.getSystemService("notification")).notify(i, bVar.b.d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Context context, int i) {
        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService("notification");
        b bVar = this.f.get(i);
        if (bVar != null) {
            u.upd.b.c(e, "download service clear cache " + bVar.e.b);
            if (bVar.a != null) {
                bVar.a.a(2);
            }
            notificationManager.cancel(bVar.c);
            if (this.g.containsKey(bVar.e)) {
                this.g.remove(bVar.e);
            }
            bVar.b(this.f);
            this.h.b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(a.C0091a aVar, long j, long j2, long j3) {
        if (aVar.f != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("dsize", String.valueOf(j));
            hashMap.put("dtime", n.a().split(HanziToPinyin.Token.SEPARATOR)[1]);
            float f = 0.0f;
            if (j2 > 0) {
                f = ((float) j) / ((float) j2);
            }
            hashMap.put("dpcent", String.valueOf((int) (f * 100.0f)));
            hashMap.put("ptimes", String.valueOf(j3));
            a((Map<String, String>) hashMap, false, aVar.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(final Map<String, String> map, final boolean z, final String[] strArr) {
        new Thread(new Runnable() { // from class: com.umeng.update.net.c.1
            @Override // java.lang.Runnable
            public void run() {
                HttpResponse execute;
                int nextInt = new Random().nextInt(1000);
                if (strArr == null) {
                    u.upd.b.a(c.e, nextInt + "service report: urls is null");
                    return;
                }
                String[] strArr2 = strArr;
                for (String str : strArr2) {
                    String a2 = n.a();
                    String str2 = a2.split(HanziToPinyin.Token.SEPARATOR)[0];
                    String str3 = a2.split(HanziToPinyin.Token.SEPARATOR)[1];
                    long currentTimeMillis = System.currentTimeMillis();
                    StringBuilder sb = new StringBuilder(str);
                    sb.append("&data=").append(str2);
                    sb.append("&time=").append(str3);
                    sb.append("&ts=").append(currentTimeMillis);
                    if (z) {
                        sb.append("&action_type=").append(1);
                    } else {
                        sb.append("&action_type=").append(-2);
                    }
                    if (map != null) {
                        for (String str4 : map.keySet()) {
                            sb.append(com.alipay.sdk.sys.a.b).append(str4).append("=").append((String) map.get(str4));
                        }
                    }
                    try {
                        u.upd.b.a(c.e, nextInt + ": service report:\tget: " + sb.toString());
                        HttpGet httpGet = new HttpGet(sb.toString());
                        BasicHttpParams basicHttpParams = new BasicHttpParams();
                        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
                        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
                        execute = new DefaultHttpClient(basicHttpParams).execute(httpGet);
                        u.upd.b.a(c.e, nextInt + ": service report:status code:  " + execute.getStatusLine().getStatusCode());
                    } catch (ClientProtocolException e2) {
                        u.upd.b.c(c.e, nextInt + ": service report:\tClientProtocolException,Failed to send message." + str, e2);
                    } catch (IOException e3) {
                        u.upd.b.c(c.e, nextInt + ": service report:\tIOException,Failed to send message." + str, e3);
                    }
                    if (execute.getStatusLine().getStatusCode() == 200) {
                        return;
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(DownloadingService downloadingService, Intent intent) {
        Context applicationContext;
        int parseInt;
        String trim;
        try {
            applicationContext = downloadingService.getApplicationContext();
            String[] split = intent.getExtras().getString(f.e).split(":");
            parseInt = Integer.parseInt(split[0]);
            trim = split[1].trim();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (parseInt != 0 && !TextUtils.isEmpty(trim) && this.f.indexOfKey(parseInt) >= 0) {
            b bVar = this.f.get(parseInt);
            DownloadingService.b bVar2 = bVar.a;
            if (f.b.equals(trim)) {
                if (bVar2 == null) {
                    u.upd.b.c(e, "Receive action do play click.");
                    if (!u.upd.a.a(applicationContext, UpdateConfig.g) || u.upd.a.l(applicationContext)) {
                        downloadingService.getClass();
                        DownloadingService.b bVar3 = new DownloadingService.b(applicationContext, bVar.e, parseInt, bVar.d, downloadingService.q);
                        bVar.a = bVar3;
                        bVar3.start();
                        Message obtain = Message.obtain();
                        obtain.what = 2;
                        obtain.arg1 = 7;
                        obtain.arg2 = parseInt;
                        try {
                            if (this.g.get(bVar.e) != null) {
                                this.g.get(bVar.e).send(obtain);
                            }
                        } catch (RemoteException e3) {
                            u.upd.b.b(e, "", e3);
                        }
                        return true;
                    }
                    Toast.makeText(applicationContext, applicationContext.getResources().getString(l.a(applicationContext.getApplicationContext())), 1).show();
                    return false;
                }
                u.upd.b.c(e, "Receive action do play click.");
                bVar2.a(1);
                bVar.a = null;
                a(applicationContext, parseInt);
                Message obtain2 = Message.obtain();
                obtain2.what = 2;
                obtain2.arg1 = 6;
                obtain2.arg2 = parseInt;
                try {
                    if (this.g.get(bVar.e) != null) {
                        this.g.get(bVar.e).send(obtain2);
                    }
                } catch (RemoteException e4) {
                    u.upd.b.b(e, "", e4);
                }
                return true;
            } else if (f.c.equals(trim)) {
                u.upd.b.c(e, "Receive action do stop click.");
                try {
                    if (bVar2 != null) {
                        bVar2.a(2);
                    } else {
                        a(bVar.e, bVar.f[0], bVar.f[1], bVar.f[2]);
                    }
                    Message obtain3 = Message.obtain();
                    obtain3.what = 5;
                    obtain3.arg1 = 5;
                    obtain3.arg2 = parseInt;
                    try {
                        if (this.g.get(bVar.e) != null) {
                            this.g.get(bVar.e).send(obtain3);
                        }
                        b(applicationContext, parseInt);
                    } catch (RemoteException e5) {
                        b(applicationContext, parseInt);
                    }
                } catch (Exception e6) {
                    Message obtain4 = Message.obtain();
                    obtain4.what = 5;
                    obtain4.arg1 = 5;
                    obtain4.arg2 = parseInt;
                    try {
                        if (this.g.get(bVar.e) != null) {
                            this.g.get(bVar.e).send(obtain4);
                        }
                        b(applicationContext, parseInt);
                    } catch (RemoteException e7) {
                        b(applicationContext, parseInt);
                    }
                } catch (Throwable th) {
                    Message obtain5 = Message.obtain();
                    obtain5.what = 5;
                    obtain5.arg1 = 5;
                    obtain5.arg2 = parseInt;
                    try {
                        if (this.g.get(bVar.e) != null) {
                            this.g.get(bVar.e).send(obtain5);
                        }
                        b(applicationContext, parseInt);
                    } catch (RemoteException e8) {
                        b(applicationContext, parseInt);
                    }
                    throw th;
                }
                return true;
            }
            e2.printStackTrace();
        }
        return false;
    }

    /* compiled from: DownloadTool.java */
    /* renamed from: com.umeng.update.net.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    class AsyncTaskC0093c extends AsyncTask<String, Void, Integer> {
        public int a;
        public String b;
        private a.C0091a d;
        private Context e;
        private NotificationManager f;

        public AsyncTaskC0093c(Context context, int i, a.C0091a aVar, String str) {
            this.e = context.getApplicationContext();
            this.f = (NotificationManager) this.e.getSystemService("notification");
            this.a = i;
            this.d = aVar;
            this.b = str;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public Integer doInBackground(String... strArr) {
            int a = DeltaUpdate.a(strArr[0], strArr[1], strArr[2]) + 1;
            new File(strArr[2]).delete();
            if (a != 1) {
                u.upd.b.a(c.e, "file patch error");
            } else if (!n.a(new File(strArr[1])).equalsIgnoreCase(this.d.e)) {
                u.upd.b.a(c.e, "file patch error");
                return 0;
            } else {
                u.upd.b.a(c.e, "file patch success");
            }
            return Integer.valueOf(a);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Integer num) {
            if (num.intValue() == 1) {
                j.a(this.b, 39, -1, -1);
                Notification notification = new Notification(17301634, this.e.getString(l.l(this.e)), System.currentTimeMillis());
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(268435456);
                intent.setDataAndType(Uri.fromFile(new File(this.b)), "application/vnd.android.package-archive");
                notification.setLatestEventInfo(this.e, u.upd.a.v(this.e), this.e.getString(l.l(this.e)), PendingIntent.getActivity(this.e, 0, intent, 134217728));
                notification.flags = 16;
                this.f.notify(this.a + 1, notification);
                if (c.this.a(this.e) && !this.d.h) {
                    this.f.cancel(this.a + 1);
                    this.e.startActivity(intent);
                }
                Bundle bundle = new Bundle();
                bundle.putString(MessageEncoder.ATTR_FILENAME, this.b);
                Message obtain = Message.obtain();
                obtain.what = 5;
                obtain.arg1 = 1;
                obtain.arg2 = this.a;
                obtain.setData(bundle);
                try {
                    if (c.this.g.get(this.d) != null) {
                        ((Messenger) c.this.g.get(this.d)).send(obtain);
                    }
                    c.this.b(this.e, this.a);
                } catch (RemoteException e) {
                    c.this.b(this.e, this.a);
                }
            } else {
                this.f.cancel(this.a + 1);
                Bundle bundle2 = new Bundle();
                bundle2.putString(MessageEncoder.ATTR_FILENAME, this.b);
                Message obtain2 = Message.obtain();
                obtain2.what = 5;
                obtain2.arg1 = 3;
                obtain2.arg2 = this.a;
                obtain2.setData(bundle2);
                try {
                    if (c.this.g.get(this.d) != null) {
                        ((Messenger) c.this.g.get(this.d)).send(obtain2);
                    }
                    c.this.b(this.e, this.a);
                } catch (RemoteException e2) {
                    c.this.b(this.e, this.a);
                }
            }
        }
    }
}

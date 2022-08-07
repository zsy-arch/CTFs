package com.umeng.update.net;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.SparseArray;
import android.widget.Toast;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.hyphenate.util.HanziToPinyin;
import com.umeng.update.net.a;
import com.umeng.update.net.c;
import com.umeng.update.util.DeltaUpdate;
import com.yolanda.nohttp.Headers;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import u.upd.l;
import u.upd.n;

/* loaded from: classes2.dex */
public class DownloadingService extends Service {
    private static final long C = 8000;
    private static final long D = 500;
    static final int a = 1;
    static final int b = 2;
    static final int c = 3;
    static final int d = 4;
    static final int e = 5;
    static final int f = 6;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 4;
    public static final int l = 5;
    public static final int m = 6;
    public static final int n = 7;
    static final int o = 100;
    static final String p = "filename";
    private static final long w = 104857600;
    private static final long x = 10485760;
    private static final long y = 259200000;
    private static final int z = 3;
    private Context A;
    private Handler B;
    private e G;
    a q;

    /* renamed from: u */
    private NotificationManager f53u;
    private c v;
    private static final String t = DownloadingService.class.getName();
    public static boolean r = false;
    private static Map<a.C0091a, Messenger> E = new HashMap();
    private static SparseArray<c.b> F = new SparseArray<>();
    private static Boolean I = false;
    final Messenger s = new Messenger(new c());
    private boolean H = true;

    /* loaded from: classes2.dex */
    public interface a {
        void a(int i);

        void a(int i, int i2);

        void a(int i, Exception exc);

        void a(int i, String str);
    }

    /* loaded from: classes2.dex */
    class c extends Handler {
        c() {
            DownloadingService.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            u.upd.b.c(DownloadingService.t, "IncomingHandler(msg.what:" + message.what + " msg.arg1:" + message.arg1 + " msg.arg2:" + message.arg2 + " msg.replyTo:" + message.replyTo);
            switch (message.what) {
                case 4:
                    Bundle data = message.getData();
                    u.upd.b.c(DownloadingService.t, "IncomingHandler(msg.getData():" + data);
                    a.C0091a a = a.C0091a.a(data);
                    if (DownloadingService.this.v.a(a, DownloadingService.r, message.replyTo)) {
                        u.upd.b.a(DownloadingService.t, a.b + " is already in downloading list. ");
                        int b = DownloadingService.this.v.b(a);
                        if (b == -1 || ((c.b) DownloadingService.F.get(b)).a != null) {
                            Toast.makeText(DownloadingService.this.A, l.b(DownloadingService.this.A), 0).show();
                            Message obtain = Message.obtain();
                            obtain.what = 2;
                            obtain.arg1 = 2;
                            obtain.arg2 = 0;
                            try {
                                message.replyTo.send(obtain);
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        } else {
                            String a2 = f.a(b, f.b);
                            Intent intent = new Intent(DownloadingService.this.A, DownloadingService.class);
                            intent.putExtra(f.e, a2);
                            DownloadingService.this.v.a(DownloadingService.this, intent);
                            return;
                        }
                    } else if (u.upd.a.l(DownloadingService.this.getApplicationContext())) {
                        DownloadingService.E.put(a, message.replyTo);
                        Message obtain2 = Message.obtain();
                        obtain2.what = 1;
                        obtain2.arg1 = 1;
                        obtain2.arg2 = 0;
                        try {
                            message.replyTo.send(obtain2);
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                        DownloadingService.this.a(a);
                        return;
                    } else {
                        Toast.makeText(DownloadingService.this.A, l.a(DownloadingService.this.A), 0).show();
                        Message obtain3 = Message.obtain();
                        obtain3.what = 2;
                        obtain3.arg1 = 4;
                        obtain3.arg2 = 0;
                        try {
                            message.replyTo.send(obtain3);
                            return;
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        u.upd.b.c(t, "onBind ");
        return this.s.getBinder();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (!(intent == null || intent.getExtras() == null || !intent.getExtras().containsKey(f.e))) {
            this.v.a(this, intent);
        }
        if (Build.VERSION.SDK_INT >= 19 && (this.G.b() || this.H)) {
            try {
                Intent intent2 = new Intent(getApplicationContext(), getClass());
                intent2.setPackage(getPackageName());
                ((AlarmManager) getApplicationContext().getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, SystemClock.elapsedRealtime() + 5000, PendingIntent.getService(getApplicationContext(), 1, intent2, 1073741824));
            } catch (Exception e2) {
            }
        }
        if (this.H) {
            d();
            this.H = false;
        }
        return 1;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (r) {
            u.upd.b.a = true;
            Debug.waitForDebugger();
        }
        u.upd.b.c(t, "onCreate ");
        this.f53u = (NotificationManager) getSystemService("notification");
        this.A = this;
        this.G = new e(this.A);
        this.v = new c(F, E, this.G);
        this.B = new Handler() { // from class: com.umeng.update.net.DownloadingService.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Notification notification;
                switch (message.what) {
                    case 5:
                        a.C0091a aVar = (a.C0091a) message.obj;
                        int i2 = message.arg2;
                        try {
                            String string = message.getData().getString("filename");
                            j.a(string, 39, -1, -1);
                            u.upd.b.c(DownloadingService.t, "Cancel old notification....");
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.addFlags(268435456);
                            intent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
                            PendingIntent activity = PendingIntent.getActivity(DownloadingService.this.A, 0, intent, 134217728);
                            if (aVar.h) {
                                Notification notification2 = new Notification(17301634, DownloadingService.this.A.getString(l.m(DownloadingService.this.A)), System.currentTimeMillis());
                                notification2.setLatestEventInfo(DownloadingService.this.A, aVar.b, DownloadingService.this.A.getString(l.m(DownloadingService.this.A)), activity);
                                notification = notification2;
                            } else {
                                Notification notification3 = new Notification(17301634, DownloadingService.this.A.getString(l.k(DownloadingService.this.A)), System.currentTimeMillis());
                                notification3.setLatestEventInfo(DownloadingService.this.A, aVar.b, DownloadingService.this.A.getString(l.k(DownloadingService.this.A)), activity);
                                notification = notification3;
                            }
                            notification.flags = 16;
                            DownloadingService.this.f53u = (NotificationManager) DownloadingService.this.getSystemService("notification");
                            DownloadingService.this.f53u.notify(i2 + 1, notification);
                            u.upd.b.c(DownloadingService.t, "Show new  notification....");
                            boolean a2 = DownloadingService.this.v.a(DownloadingService.this.A);
                            u.upd.b.c(DownloadingService.t, String.format("isAppOnForeground = %1$B", Boolean.valueOf(a2)));
                            if (a2 && !aVar.h) {
                                DownloadingService.this.f53u.cancel(i2 + 1);
                                DownloadingService.this.A.startActivity(intent);
                            }
                            u.upd.b.a(DownloadingService.t, String.format("%1$10s downloaded. Saved to: %2$s", aVar.b, string));
                            return;
                        } catch (Exception e2) {
                            u.upd.b.b(DownloadingService.t, "can not install. " + e2.getMessage());
                            DownloadingService.this.f53u.cancel(i2 + 1);
                            return;
                        }
                    case 6:
                        int i3 = message.arg2;
                        String string2 = message.getData().getString("filename");
                        DownloadingService.this.f53u.cancel(i3);
                        Notification notification4 = new Notification(17301633, DownloadingService.this.A.getString(l.n(DownloadingService.this.A)), System.currentTimeMillis());
                        notification4.setLatestEventInfo(DownloadingService.this.A, u.upd.a.v(DownloadingService.this.A), DownloadingService.this.A.getString(l.n(DownloadingService.this.A)), PendingIntent.getActivity(DownloadingService.this.A, 0, new Intent(), 134217728));
                        DownloadingService.this.f53u.notify(i3 + 1, notification4);
                        String replace = string2.replace(".patch", ".apk");
                        String a3 = DeltaUpdate.a(DownloadingService.this);
                        c cVar = DownloadingService.this.v;
                        cVar.getClass();
                        new c.AsyncTaskC0093c(DownloadingService.this.A, i3, (a.C0091a) message.obj, replace).execute(a3, replace, string2);
                        return;
                    default:
                        return;
                }
            }
        };
        this.q = new a() { // from class: com.umeng.update.net.DownloadingService.2
            SparseArray<Long> a = new SparseArray<>();

            @Override // com.umeng.update.net.DownloadingService.a
            public void a(int i2) {
                int i3 = 0;
                if (DownloadingService.F.indexOfKey(i2) >= 0) {
                    c.b bVar = (c.b) DownloadingService.F.get(i2);
                    long[] jArr = bVar.f;
                    if (jArr != null && jArr[1] > 0 && (i3 = (int) ((((float) jArr[0]) / ((float) jArr[1])) * 100.0f)) > 100) {
                        i3 = 99;
                    }
                    if (!bVar.e.h) {
                        this.a.put(i2, -1L);
                        c.a a2 = DownloadingService.this.v.a(DownloadingService.this, bVar.e, i2, i3);
                        bVar.b = a2;
                        DownloadingService.this.f53u.notify(i2, a2.d());
                    }
                }
            }

            @Override // com.umeng.update.net.DownloadingService.a
            public void a(int i2, int i3) {
                if (DownloadingService.F.indexOfKey(i2) >= 0) {
                    c.b bVar = (c.b) DownloadingService.F.get(i2);
                    a.C0091a aVar = bVar.e;
                    long currentTimeMillis = System.currentTimeMillis();
                    if (!aVar.h && currentTimeMillis - this.a.get(i2).longValue() > DownloadingService.D) {
                        this.a.put(i2, Long.valueOf(currentTimeMillis));
                        c.a aVar2 = bVar.b;
                        aVar2.a(100, i3, false).a(String.valueOf(i3) + "%");
                        DownloadingService.this.f53u.notify(i2, aVar2.d());
                    }
                    u.upd.b.c(DownloadingService.t, String.format("%3$10s Notification: mNotificationId = %1$15s\t|\tprogress = %2$15s", Integer.valueOf(i2), Integer.valueOf(i3), aVar.b));
                }
            }

            @Override // com.umeng.update.net.DownloadingService.a
            public void a(int i2, String str) {
                c.b bVar;
                if (DownloadingService.F.indexOfKey(i2) >= 0 && (bVar = (c.b) DownloadingService.F.get(i2)) != null) {
                    a.C0091a aVar = bVar.e;
                    b.a(DownloadingService.this.A).a(aVar.a, aVar.c, 100);
                    Bundle bundle = new Bundle();
                    bundle.putString("filename", str);
                    if (aVar.a.equalsIgnoreCase("delta_update")) {
                        Message obtain = Message.obtain();
                        obtain.what = 6;
                        obtain.arg1 = 1;
                        obtain.obj = aVar;
                        obtain.arg2 = i2;
                        obtain.setData(bundle);
                        DownloadingService.this.B.sendMessage(obtain);
                        return;
                    }
                    Message obtain2 = Message.obtain();
                    obtain2.what = 5;
                    obtain2.arg1 = 1;
                    obtain2.obj = aVar;
                    obtain2.arg2 = i2;
                    obtain2.setData(bundle);
                    DownloadingService.this.B.sendMessage(obtain2);
                    Message obtain3 = Message.obtain();
                    obtain3.what = 5;
                    obtain3.arg1 = 1;
                    obtain3.arg2 = i2;
                    obtain3.setData(bundle);
                    try {
                        if (DownloadingService.E.get(aVar) != null) {
                            ((Messenger) DownloadingService.E.get(aVar)).send(obtain3);
                        }
                        DownloadingService.this.v.b(DownloadingService.this.A, i2);
                    } catch (RemoteException e2) {
                        DownloadingService.this.v.b(DownloadingService.this.A, i2);
                    }
                }
            }

            @Override // com.umeng.update.net.DownloadingService.a
            public void a(int i2, Exception exc) {
                if (DownloadingService.F.indexOfKey(i2) >= 0) {
                    DownloadingService.this.v.b(DownloadingService.this.A, i2);
                }
            }
        };
    }

    private void d() {
        for (Integer num : this.G.a()) {
            this.f53u.cancel(num.intValue());
        }
    }

    public void a(a.C0091a aVar) {
        u.upd.b.c(t, "startDownload([mComponentName:" + aVar.a + " mTitle:" + aVar.b + " mUrl:" + aVar.c + "])");
        int a2 = this.v.a(aVar);
        b bVar = new b(getApplicationContext(), aVar, a2, 0, this.q);
        c.b bVar2 = new c.b(aVar, a2);
        this.G.a(a2);
        bVar2.a(F);
        bVar2.a = bVar;
        bVar.start();
        e();
        if (r) {
            for (int i2 = 0; i2 < F.size(); i2++) {
                u.upd.b.c(t, "Running task " + F.valueAt(i2).e.b);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class b extends Thread {
        private Context b;
        private boolean c;
        private File d;
        private int e;
        private long f;
        private long g;
        private int h = -1;
        private int i;
        private a j;
        private a.C0091a k;

        public b(Context context, a.C0091a aVar, int i, int i2, a aVar2) {
            long[] jArr;
            DownloadingService.this = r7;
            this.e = 0;
            this.f = -1L;
            this.g = -1L;
            try {
                this.b = context;
                this.k = aVar;
                this.e = i2;
                if (DownloadingService.F.indexOfKey(i) >= 0 && (jArr = ((c.b) DownloadingService.F.get(i)).f) != null && jArr.length > 1) {
                    this.f = jArr[0];
                    this.g = jArr[1];
                }
                this.j = aVar2;
                this.i = i;
                boolean[] zArr = new boolean[1];
                this.d = j.a("/apk", context, zArr);
                this.c = zArr[0];
                j.a(this.d, this.c ? DownloadingService.w : DownloadingService.x, (long) DownloadingService.y);
                this.d = new File(this.d, a(this.k));
            } catch (Exception e) {
                u.upd.b.c(DownloadingService.t, e.getMessage(), e);
                this.j.a(this.i, e);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean z = false;
            this.e = 0;
            try {
                if (this.j != null) {
                    this.j.a(this.i);
                }
                if (this.f > 0) {
                    z = true;
                }
                a(z);
                if (DownloadingService.E.size() <= 0) {
                    DownloadingService.this.stopSelf();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void a(int i) {
            this.h = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:222:0x01fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:233:0x03ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:237:0x01f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:241:0x03b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:262:0x035b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:266:0x0356 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:287:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:298:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void a(boolean r15) {
            /*
                Method dump skipped, instructions count: 1149
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.update.net.DownloadingService.b.a(boolean):void");
        }

        private void a() {
            u.upd.b.c(DownloadingService.t, "wait for repeating Test network repeat count=" + this.e);
            try {
                if (!this.k.g) {
                    Thread.sleep(DownloadingService.C);
                    if (this.g < 1) {
                        a(false);
                    } else {
                        a(true);
                    }
                } else {
                    c.b bVar = (c.b) DownloadingService.F.get(this.i);
                    bVar.f[0] = this.f;
                    bVar.f[1] = this.g;
                    bVar.f[2] = this.e;
                    String a = f.a(this.i, f.b);
                    Intent intent = new Intent(this.b, DownloadingService.class);
                    intent.putExtra(f.e, a);
                    DownloadingService.this.v.a(DownloadingService.this, intent);
                    DownloadingService.this.a(this.b.getString(l.c(this.b)));
                    u.upd.b.c(DownloadingService.t, "changed play state button on op-notification.");
                }
            } catch (InterruptedException e) {
                a(e);
                DownloadingService.this.v.b(this.b, this.i);
            }
        }

        private void b(int i) throws RemoteException {
            try {
                if (DownloadingService.E.get(this.k) != null) {
                    ((Messenger) DownloadingService.E.get(this.k)).send(Message.obtain(null, 3, i, 0));
                }
            } catch (DeadObjectException e) {
                u.upd.b.b(DownloadingService.t, String.format("Service Client for downloading %1$15s is dead. Removing messenger from the service", this.k.b));
                DownloadingService.E.put(this.k, null);
            }
        }

        private HttpURLConnection a(URL url, File file) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(Headers.HEAD_KEY_ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
            httpURLConnection.addRequestProperty("Connection", Headers.HEAD_VALUE_CONNECTION_KEEP_ALIVE);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(10000);
            if (file.exists() && file.length() > 0) {
                u.upd.b.c(DownloadingService.t, String.format(this.k.b + " getFileLength: %1$15s", Long.valueOf(file.length())));
                httpURLConnection.setRequestProperty(HttpHeaders.RANGE, "bytes=" + file.length() + "-");
            }
            return httpURLConnection;
        }

        private String a(a.C0091a aVar) {
            String str;
            if (this.k.e != null) {
                str = this.k.e + ".apk.tmp";
            } else {
                str = n.a(this.k.c) + ".apk.tmp";
            }
            if (this.k.a.equalsIgnoreCase("delta_update")) {
                return str.replace(".apk", ".patch");
            }
            return str;
        }

        private void b() {
            if (this.k.f != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("dsize", String.valueOf(this.g));
                hashMap.put("dtime", n.a().split(HanziToPinyin.Token.SEPARATOR)[1]);
                hashMap.put("ptimes", String.valueOf(this.e));
                DownloadingService.this.v.a((Map<String, String>) hashMap, true, this.k.f);
            }
        }

        private void a(File file, String str) throws RemoteException {
            u.upd.b.c(DownloadingService.t, "itemMd5 " + this.k.d);
            u.upd.b.c(DownloadingService.t, "fileMd5 " + n.a(file));
            if (this.k.d != null && !this.k.d.equalsIgnoreCase(n.a(file))) {
                if (this.k.a.equalsIgnoreCase("delta_update")) {
                    DownloadingService.this.f53u.cancel(this.i);
                    Bundle bundle = new Bundle();
                    bundle.putString("filename", str);
                    Message obtain = Message.obtain();
                    obtain.what = 5;
                    obtain.arg1 = 3;
                    obtain.arg2 = this.i;
                    obtain.setData(bundle);
                    try {
                        if (DownloadingService.E.get(this.k) != null) {
                            ((Messenger) DownloadingService.E.get(this.k)).send(obtain);
                        }
                        DownloadingService.this.v.b(this.b, this.i);
                    } catch (RemoteException e) {
                        DownloadingService.this.v.b(this.b, this.i);
                    }
                } else {
                    ((Messenger) DownloadingService.E.get(this.k)).send(Message.obtain(null, 5, 0, 0));
                    if (!this.k.h) {
                        DownloadingService.this.v.b(this.b, this.i);
                        Notification notification = new Notification(17301634, this.b.getString(l.i(this.b)), System.currentTimeMillis());
                        notification.setLatestEventInfo(this.b, u.upd.a.v(this.b), this.k.b + this.b.getString(l.i(this.b)), PendingIntent.getActivity(this.b, 0, new Intent(), 0));
                        notification.flags |= 16;
                        DownloadingService.this.f53u.notify(this.i, notification);
                    }
                }
            }
        }

        private void a(Exception exc) {
            u.upd.b.b(DownloadingService.t, "can not install. " + exc.getMessage());
            if (this.j != null) {
                this.j.a(this.i, exc);
            }
            DownloadingService.this.v.a(this.k, this.f, this.g, this.e);
        }
    }

    public void a(final String str) {
        synchronized (I) {
            if (!I.booleanValue()) {
                u.upd.b.c(t, "show single toast.[" + str + "]");
                I = true;
                this.B.post(new Runnable() { // from class: com.umeng.update.net.DownloadingService.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(DownloadingService.this.A, str, 0).show();
                    }
                });
                this.B.postDelayed(new Runnable() { // from class: com.umeng.update.net.DownloadingService.4
                    @Override // java.lang.Runnable
                    public void run() {
                        Boolean unused = DownloadingService.I = false;
                    }
                }, 1200L);
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            b.a(getApplicationContext()).a(259200);
            b.a(getApplicationContext()).finalize();
        } catch (Exception e2) {
            u.upd.b.b(t, e2.getMessage());
        }
        super.onDestroy();
    }

    private void e() {
        if (r) {
            int size = E.size();
            int size2 = F.size();
            u.upd.b.a(t, "Client size =" + size + "   cacheSize = " + size2);
            if (size != size2) {
                throw new RuntimeException("Client size =" + size + "   cacheSize = " + size2);
            }
        }
    }
}

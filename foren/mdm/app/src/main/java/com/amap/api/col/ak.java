package com.amap.api.col;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alipay.sdk.util.h;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* compiled from: OfflineDownloadManager.java */
/* loaded from: classes.dex */
public class ak {
    public static String a = "";
    public static boolean b = false;
    public static String d = "";
    private static volatile ak k;
    public ao f;
    aq g;
    private Context i;
    private a l;
    private at m;
    private az n;
    private boolean j = true;
    List<aj> c = new Vector();
    private ExecutorService o = null;
    private ExecutorService p = null;
    private ExecutorService q = null;
    b e = null;
    an h = null;
    private boolean r = true;

    /* compiled from: OfflineDownloadManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void a();

        void a(aj ajVar);

        void b(aj ajVar);

        void c(aj ajVar);
    }

    private ak(Context context) {
        this.i = context;
    }

    public static ak a(Context context) {
        if (k == null) {
            synchronized (ak.class) {
                if (k == null && !b) {
                    k = new ak(context.getApplicationContext());
                }
            }
        }
        return k;
    }

    public void a() {
        this.n = az.a(this.i.getApplicationContext());
        h();
        this.e = new b(this.i.getMainLooper());
        this.f = new ao(this.i, this.e);
        this.m = at.a(1);
        g(dt.b(this.i));
        try {
            i();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        synchronized (this.c) {
            Iterator<OfflineMapProvince> it = this.f.a().iterator();
            while (it.hasNext()) {
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    OfflineMapCity next = it2.next();
                    if (next != null) {
                        this.c.add(new aj(this.i, next));
                    }
                }
            }
        }
        this.h = new an(this.i);
        this.h.start();
    }

    private void h() {
        try {
            au a2 = this.n.a("000001");
            if (a2 != null) {
                this.n.c("000001");
                a2.c("100000");
                this.n.a(a2);
            }
        } catch (Throwable th) {
            gr.b(th, "OfflineDownloadManager", "changeBadCase");
        }
    }

    private void i() {
        String c;
        if (!dt.b(this.i).equals("")) {
            File file = new File(dt.b(this.i) + "offlinemapv4.png");
            if (!file.exists()) {
                c = bh.a(this.i, "offlinemapv4.png");
            } else {
                c = bh.c(file);
            }
            if (c != null) {
                try {
                    h(c);
                } catch (JSONException e) {
                    if (file.exists()) {
                        file.delete();
                    }
                    gr.b(e, "MapDownloadManager", "paseJson io");
                    e.printStackTrace();
                }
            }
        }
    }

    private void h(String str) throws JSONException {
        List<OfflineMapProvince> a2 = bh.a(str, this.i.getApplicationContext());
        if (a2 != null && a2.size() != 0) {
            this.f.a(a2);
        }
    }

    private void j() {
        Iterator<au> it = this.n.a().iterator();
        while (it.hasNext()) {
            au next = it.next();
            if (!(next == null || next.d() == null || next.f().length() < 1)) {
                if (!(next.l == 4 || next.l == 7 || next.l < 0)) {
                    next.l = 3;
                }
                aj i = i(next.d());
                if (i != null) {
                    String e = next.e();
                    if (e == null || !a(d, e)) {
                        i.a(next.l);
                        i.setCompleteCode(next.g());
                    } else {
                        i.a(7);
                    }
                    if (next.e().length() > 0) {
                        i.setVersion(next.e());
                    }
                    List<String> b2 = this.n.b(next.f());
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String str : b2) {
                        stringBuffer.append(str);
                        stringBuffer.append(h.b);
                    }
                    i.a(stringBuffer.toString());
                    this.f.a(i);
                }
            }
        }
    }

    public void a(ArrayList<au> arrayList) {
        j();
        if (this.l != null) {
            try {
                this.l.a();
            } catch (Throwable th) {
                gr.b(th, "OfflineDownloadManager", "verifyCallBack");
            }
        }
    }

    public void a(final String str) {
        try {
            if (str != null) {
                if (this.o == null) {
                    this.o = Executors.newSingleThreadExecutor();
                }
                this.o.execute(new Runnable() { // from class: com.amap.api.col.ak.1
                    @Override // java.lang.Runnable
                    public void run() {
                        aj i = ak.this.i(str);
                        if (i != null) {
                            try {
                                if (!i.c().equals(i.c) && !i.c().equals(i.e)) {
                                    String pinyin = i.getPinyin();
                                    if (pinyin.length() > 0) {
                                        String d2 = ak.this.n.d(pinyin);
                                        if (d2 == null) {
                                            d2 = i.getVersion();
                                        }
                                        if (ak.d.length() > 0 && d2 != null && ak.this.a(ak.d, d2)) {
                                            i.j();
                                        }
                                    }
                                } else if (ak.this.l != null) {
                                    synchronized (ak.this) {
                                        try {
                                            ak.this.l.b(i);
                                        } catch (Throwable th) {
                                            throw th;
                                        }
                                    }
                                    return;
                                } else {
                                    return;
                                }
                            } catch (Exception e) {
                                if (ak.this.l != null) {
                                    synchronized (ak.this) {
                                        try {
                                            ak.this.l.b(i);
                                            return;
                                        } catch (Throwable th2) {
                                            throw th2;
                                        }
                                    }
                                } else {
                                    return;
                                }
                            } catch (Throwable th3) {
                                if (ak.this.l != null) {
                                    synchronized (ak.this) {
                                        try {
                                            ak.this.l.b(i);
                                        } catch (Throwable th4) {
                                            throw th4;
                                        }
                                    }
                                }
                                throw th3;
                            }
                        }
                        ak.this.k();
                        al c = new am(ak.this.i, ak.d).c();
                        if (ak.this.l != null) {
                            if (c == null) {
                                if (ak.this.l != null) {
                                    synchronized (ak.this) {
                                        try {
                                            ak.this.l.b(i);
                                        } catch (Throwable th5) {
                                            throw th5;
                                        }
                                    }
                                    return;
                                }
                                return;
                            } else if (c.a()) {
                                ak.this.b();
                            }
                        }
                        if (ak.this.l != null) {
                            synchronized (ak.this) {
                                try {
                                    ak.this.l.b(i);
                                } catch (Throwable th6) {
                                    throw th6;
                                }
                            }
                        }
                    }
                });
            } else if (this.l != null) {
                this.l.b(null);
            }
        } catch (Throwable th) {
            gr.b(th, "OfflineDownloadManager", "checkUpdate");
        }
    }

    public void k() throws AMapException {
        if (!dt.c(this.i)) {
            throw new AMapException(AMapException.ERROR_CONNECTION);
        }
    }

    protected void b() throws AMapException {
        ar arVar = new ar(this.i, "");
        arVar.a(this.i);
        List<OfflineMapProvince> c = arVar.c();
        if (this.c != null) {
            this.f.a(c);
        }
        synchronized (this.c) {
            Iterator<OfflineMapProvince> it = this.f.a().iterator();
            while (it.hasNext()) {
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    OfflineMapCity next = it2.next();
                    for (aj ajVar : this.c) {
                        if (next.getPinyin().equals(ajVar.getPinyin())) {
                            String version = ajVar.getVersion();
                            if (ajVar.getState() != 4 || d.length() <= 0 || !a(d, version)) {
                                ajVar.setCity(next.getCity());
                                ajVar.setUrl(next.getUrl());
                                ajVar.setAdcode(next.getAdcode());
                                ajVar.setVersion(next.getVersion());
                                ajVar.setSize(next.getSize());
                                ajVar.setCode(next.getCode());
                                ajVar.setJianpin(next.getJianpin());
                                ajVar.setPinyin(next.getPinyin());
                            } else {
                                ajVar.j();
                                ajVar.setUrl(next.getUrl());
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean a(String str, String str2) {
        for (int i = 0; i < str2.length(); i++) {
            try {
                if (str.charAt(i) > str2.charAt(i)) {
                    return true;
                }
                if (str.charAt(i) < str2.charAt(i)) {
                    return false;
                }
            } catch (Throwable th) {
                return false;
            }
        }
        return false;
    }

    public boolean b(String str) {
        return i(str) != null;
    }

    public void c(String str) {
        aj i = i(str);
        if (i != null) {
            d(i);
            a(i, true);
        } else if (this.l != null) {
            try {
                this.l.c(i);
            } catch (Throwable th) {
                gr.b(th, "OfflineDownloadManager", "remove");
            }
        }
    }

    public void a(aj ajVar) {
        a(ajVar, false);
    }

    private void a(final aj ajVar, final boolean z) {
        if (this.g == null) {
            this.g = new aq(this.i);
        }
        if (this.p == null) {
            this.p = Executors.newSingleThreadExecutor();
        }
        try {
            this.p.execute(new Runnable() { // from class: com.amap.api.col.ak.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (ajVar.c().equals(ajVar.a)) {
                            if (ak.this.l != null) {
                                ak.this.l.c(ajVar);
                            }
                        } else if (ajVar.getState() == 7 || ajVar.getState() == -1) {
                            ak.this.g.a(ajVar);
                            if (z && ak.this.l != null) {
                                ak.this.l.c(ajVar);
                            }
                        } else {
                            ak.this.g.a(ajVar);
                            if (ak.this.l != null) {
                                ak.this.l.c(ajVar);
                            }
                        }
                    } catch (Throwable th) {
                        gr.b(th, "requestDelete", "removeExcecRunnable");
                    }
                }
            });
        } catch (Throwable th) {
            gr.b(th, "requestDelete", "removeExcecRunnable");
        }
    }

    public void b(aj ajVar) {
        try {
            if (this.m != null) {
                this.m.a(ajVar, this.i, null);
            }
        } catch (fz e) {
            e.printStackTrace();
        }
    }

    public void c(aj ajVar) {
        this.f.a(ajVar);
        if (this.e != null) {
            Message obtainMessage = this.e.obtainMessage();
            obtainMessage.obj = ajVar;
            this.e.sendMessage(obtainMessage);
        }
    }

    public void c() {
        synchronized (this.c) {
            for (aj ajVar : this.c) {
                if (ajVar.c().equals(ajVar.c) || ajVar.c().equals(ajVar.b)) {
                    d(ajVar);
                    ajVar.g();
                }
            }
        }
    }

    public void d() {
        synchronized (this.c) {
            Iterator<aj> it = this.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                aj next = it.next();
                if (next.c().equals(next.c)) {
                    next.g();
                    break;
                }
            }
        }
    }

    public void e() {
        if (this.o != null && !this.o.isShutdown()) {
            this.o.shutdownNow();
        }
        if (this.q != null && !this.q.isShutdown()) {
            this.q.shutdownNow();
        }
        if (this.h != null) {
            if (this.h.isAlive()) {
                this.h.interrupt();
            }
            this.h = null;
        }
        if (this.e != null) {
            this.e.removeCallbacksAndMessages(null);
            this.e = null;
        }
        if (this.m != null) {
            this.m.b();
        }
        if (this.f != null) {
            this.f.g();
        }
        f();
        this.j = true;
        g();
    }

    public static void f() {
        k = null;
        b = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001b A[Catch: all -> 0x0037, TryCatch #0 {, blocks: (B:10:0x000f, B:11:0x0015, B:13:0x001b, B:15:0x002b, B:17:0x0035, B:22:0x003a), top: B:24:0x000f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amap.api.col.aj i(java.lang.String r6) {
        /*
            r5 = this;
            r1 = 0
            if (r6 == 0) goto L_0x000a
            int r0 = r6.length()
            r2 = 1
            if (r0 >= r2) goto L_0x000c
        L_0x000a:
            r0 = r1
        L_0x000b:
            return r0
        L_0x000c:
            java.util.List<com.amap.api.col.aj> r2 = r5.c
            monitor-enter(r2)
            java.util.List<com.amap.api.col.aj> r0 = r5.c     // Catch: all -> 0x0037
            java.util.Iterator r3 = r0.iterator()     // Catch: all -> 0x0037
        L_0x0015:
            boolean r0 = r3.hasNext()     // Catch: all -> 0x0037
            if (r0 == 0) goto L_0x003a
            java.lang.Object r0 = r3.next()     // Catch: all -> 0x0037
            com.amap.api.col.aj r0 = (com.amap.api.col.aj) r0     // Catch: all -> 0x0037
            java.lang.String r4 = r0.getCity()     // Catch: all -> 0x0037
            boolean r4 = r6.equals(r4)     // Catch: all -> 0x0037
            if (r4 != 0) goto L_0x0035
            java.lang.String r4 = r0.getPinyin()     // Catch: all -> 0x0037
            boolean r4 = r6.equals(r4)     // Catch: all -> 0x0037
            if (r4 == 0) goto L_0x0015
        L_0x0035:
            monitor-exit(r2)     // Catch: all -> 0x0037
            goto L_0x000b
        L_0x0037:
            r0 = move-exception
            monitor-exit(r2)     // Catch: all -> 0x0037
            throw r0
        L_0x003a:
            monitor-exit(r2)     // Catch: all -> 0x0037
            r0 = r1
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.ak.i(java.lang.String):com.amap.api.col.aj");
    }

    private aj j(String str) {
        aj ajVar;
        if (str == null || str.length() < 1) {
            return null;
        }
        synchronized (this.c) {
            Iterator<aj> it = this.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    ajVar = null;
                    break;
                }
                ajVar = it.next();
                if (str.equals(ajVar.getCode())) {
                    break;
                }
            }
        }
        return ajVar;
    }

    public void d(String str) throws AMapException {
        aj i = i(str);
        if (str == null || str.length() < 1 || i == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        f(i);
    }

    public void e(String str) throws AMapException {
        aj j = j(str);
        if (j != null) {
            f(j);
            return;
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    private void f(final aj ajVar) throws AMapException {
        k();
        if (ajVar == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        if (this.q == null) {
            this.q = Executors.newSingleThreadExecutor();
        }
        try {
            this.q.execute(new Runnable() { // from class: com.amap.api.col.ak.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (ak.this.j) {
                            ak.this.k();
                            al c = new am(ak.this.i, ak.d).c();
                            if (c != null) {
                                ak.this.j = false;
                                if (c.a()) {
                                    ak.this.b();
                                }
                            }
                        }
                        ajVar.setVersion(ak.d);
                        ajVar.f();
                    } catch (AMapException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        gr.b(th, "OfflineDownloadManager", "startDownloadRunnable");
                    }
                }
            });
        } catch (Throwable th) {
            gr.b(th, "startDownload", "downloadExcecRunnable");
        }
    }

    public void d(aj ajVar) {
        if (this.m != null) {
            this.m.a(ajVar);
        }
    }

    public void e(aj ajVar) {
        if (this.m != null) {
            this.m.b(ajVar);
        }
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void g() {
        synchronized (this) {
            this.l = null;
        }
    }

    /* compiled from: OfflineDownloadManager.java */
    /* loaded from: classes.dex */
    public class b extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Looper looper) {
            super(looper);
            ak.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                message.getData();
                Object obj = message.obj;
                if (obj instanceof aj) {
                    aj ajVar = (aj) obj;
                    bh.a("OfflineMapHandler handleMessage CitObj  name: " + ajVar.getCity() + " complete: " + ajVar.getcompleteCode() + " status: " + ajVar.getState());
                    if (ak.this.l != null) {
                        ak.this.l.a(ajVar);
                    }
                } else {
                    bh.a("Do not callback by CityObject! ");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public String f(String str) {
        aj i;
        if (str == null || (i = i(str)) == null) {
            return "";
        }
        return i.getAdcode();
    }

    public static void g(String str) {
        a = str;
    }
}

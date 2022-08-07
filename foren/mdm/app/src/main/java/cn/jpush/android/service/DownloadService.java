package cn.jpush.android.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.RemoteViews;
import cn.jpush.android.api.n;
import cn.jpush.android.data.c;
import cn.jpush.android.data.i;
import cn.jpush.android.data.s;
import cn.jpush.android.e;
import cn.jpush.android.helpers.h;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
public class DownloadService extends IntentService {
    public static ConcurrentLinkedQueue<c> a;
    private static Bundle b;
    private static final String[] z;
    private NotificationManager c;
    private c d;
    private g e;
    private Notification f;
    private Notification.Builder g;
    private RemoteViews h;
    private Integer i = 0;
    private Integer j = 0;
    private Integer k = 0;
    private Integer l = 0;
    private Integer m = 0;
    private Handler n = new f(this);

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004d;
            case 2: goto L_0x0055;
            case 3: goto L_0x005d;
            case 4: goto L_0x0066;
            case 5: goto L_0x006e;
            case 6: goto L_0x0077;
            case 7: goto L_0x0081;
            case 8: goto L_0x008c;
            case 9: goto L_0x0097;
            case 10: goto L_0x00a3;
            case 11: goto L_0x00ae;
            case 12: goto L_0x00b9;
            case 13: goto L_0x00c4;
            case 14: goto L_0x00cf;
            case 15: goto L_0x00db;
            case 16: goto L_0x00e7;
            case 17: goto L_0x00f3;
            case 18: goto L_0x00fe;
            case 19: goto L_0x0109;
            case 20: goto L_0x0115;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "`R\u001bG";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "}X\u0000E6";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "g^\u0000@5`R\u0015]:f_";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "g^\u0000@5`R\u0015]:f_+E2p^\u0001]";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "yC\u001bN!lB\u0007v1hC";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "[\u0015\u001dM";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "}T\f]\fyC\u001bN!lB\u0007";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "曽斁爼朅嶡丂轌寸毼｟课炈冏宠袖》";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0081, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "k^\u0010P";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008c, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "j_ZC#|B\u001c\u00072gU\u0006F:m\u001f\u001dG'l_\u0000\u0007\u001dFe=o\u001aJp `\u001cGn=g\u0000]p8e\fJ}=j\u0018Lu";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0097, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "宀裴危巛乘轴宽殡Ｅ认炰凊寽裬け";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a3, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "g^\u0000v2|E\u001b[&g";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ae, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "G^TL=m\u0011\u001aF'`W\u001dJ2}X\u001bG})X\u0007\t5`]\u0011y2}YTL>yE\r\tl)\u001cT";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b9, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "LI\u0011J&}TTF?m\u0011\u0010F$g]\u001bH7)E\u0015Z8)\u001cTZ:sTN";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c4, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "ZE\u0015['`_\u0013\t'f\u0011\u0010F$g]\u001bH7)\u001cTD6zB\u0015N6@UN";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cf, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "丂轌贰溹奢敁〳讃稤呝炰凊醹料乘轴Ｐ";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00db, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "丂轌奅贌け课稼呺炐冨釄斁乿轔ｒ";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e7, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "彚剼缥绵乞另甙ぶ稤呝伓绖纙丢輮（";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00f3, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "d^\u0001G'lU";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00fe, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "M^\u0003G?fP\u0010z6{G\u001dJ6";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0109, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "丂轌乙\u0007}'\u0011";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0115, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.DownloadService.z = r3;
        cn.jpush.android.service.DownloadService.a = new java.util.concurrent.ConcurrentLinkedQueue<>();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0120, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r9 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0125, code lost:
        r9 = '1';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0129, code lost:
        r9 = 't';
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x012d, code lost:
        r9 = ')';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0121;
            case 1: goto L_0x0125;
            case 2: goto L_0x0129;
            case 3: goto L_0x012d;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'S';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.DownloadService.<clinit>():void");
    }

    public DownloadService() {
        super(z[20]);
    }

    public static void a(Context context) {
        new StringBuilder(z[14]).append(a.size());
        ac.b();
        ArrayList arrayList = new ArrayList();
        while (true) {
            c poll = a.poll();
            if (poll == null) {
                break;
            } else if (poll.w) {
                new StringBuilder(z[15]).append(poll.c);
                ac.b();
                ServiceInterface.a(context, poll);
            } else {
                ac.a();
                arrayList.add(poll);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a.offer((c) it.next());
        }
    }

    public void a(c cVar, int i, long j, long j2) {
        if (Build.VERSION.SDK_INT >= 11) {
            if (this.g == null) {
                this.g = new Notification.Builder(getApplicationContext()).setSmallIcon(17301633).setWhen(System.currentTimeMillis()).setDefaults(4).setOngoing(true);
                this.g.setContentIntent(PendingIntent.getActivity(getApplicationContext(), i, new Intent(), 134217728));
            }
            String str = cVar.s;
            String str2 = z[21];
            int i2 = (int) ((((float) j) / ((float) j2)) * 100.0f);
            if (j2 > 0) {
                str2 = str2 + i2 + "%";
            }
            if (this.m == null || this.m.intValue() <= 0) {
                this.g.setContentTitle(str).setContentText(str2).setContentIntent(PendingIntent.getActivity(getApplicationContext(), i, new Intent(), 134217728));
            } else {
                if (this.h == null) {
                    this.h = new RemoteViews(getPackageName(), this.m.intValue());
                    this.h.setTextViewText(this.i.intValue(), str);
                    this.h.setImageViewResource(this.k.intValue(), e.b);
                }
                this.h.setTextViewText(this.l.intValue(), i2 + "%");
                this.h.setProgressBar(this.j.intValue(), 100, i2, false);
                this.g.setContent(this.h);
            }
            this.c.notify(i, this.g.getNotification());
            return;
        }
        if (this.f == null) {
            this.f = new Notification();
            this.f.icon = 17301633;
            this.f.when = System.currentTimeMillis();
            this.f.flags = 2;
            this.f.defaults = 4;
            this.f.contentIntent = PendingIntent.getActivity(getApplicationContext(), i, new Intent(), 134217728);
        }
        String str3 = cVar.s;
        String str4 = z[21];
        int i3 = (int) ((((float) j) / ((float) j2)) * 100.0f);
        if (j2 > 0) {
            str4 = str4 + i3 + "%";
        }
        if (this.m == null || this.m.intValue() <= 0) {
            n.a(this.f, this, str3, str4, PendingIntent.getActivity(getApplicationContext(), i, new Intent(), 134217728));
        } else {
            if (this.h == null) {
                this.h = new RemoteViews(getPackageName(), this.m.intValue());
                this.h.setTextViewText(this.i.intValue(), str3);
                this.h.setImageViewResource(this.k.intValue(), e.b);
            }
            this.h.setTextViewText(this.l.intValue(), i3 + "%");
            this.h.setProgressBar(this.j.intValue(), 100, i3, false);
            this.f.contentView = this.h;
        }
        if (this.f != null) {
            this.c.notify(i, this.f);
        }
    }

    private void a(c cVar, boolean z2) {
        Intent a2;
        boolean z3;
        boolean c = cVar.c();
        if (!cVar.a() || z2) {
            a2 = b.a(getApplicationContext(), cVar, false);
            z3 = false;
        } else {
            z3 = true;
            a2 = new Intent();
            a2.putExtra(z[9], cVar);
            a2.setClass(getApplicationContext(), PushReceiver.class);
            a2.setAction(z[10]);
            if (cVar.c()) {
                cVar.t = z[8];
            } else {
                cVar.t = z[11];
            }
        }
        int a3 = n.a(cVar, 0);
        Notification a4 = n.a(getApplicationContext(), a3, a2, cVar, c, z3);
        if (a4 != null) {
            this.c.notify(a3, a4);
        } else {
            ac.e();
        }
    }

    public static /* synthetic */ void a(DownloadService downloadService, int i, c cVar, int i2) {
        String str;
        if (i2 != 0 && !cVar.e()) {
            int i3 = 4;
            if (2 == i2) {
                str = z[17];
            } else if (3 == i2) {
                str = z[16];
            } else if (1 == i2) {
                str = z[18];
                i3 = 2;
            } else {
                return;
            }
            String str2 = cVar.s;
            Intent intent = new Intent();
            if (b.a(i2)) {
                intent.setClass(downloadService.getApplicationContext(), DownloadService.class);
                cVar.z = -1;
                intent.putExtra(z[9], cVar);
            }
            PendingIntent service = PendingIntent.getService(downloadService, i, intent, 134217728);
            if (Build.VERSION.SDK_INT >= 11) {
                new Notification.Builder(downloadService.getApplicationContext()).setContentTitle(str2).setContentText(str).setContentIntent(service).setWhen(System.currentTimeMillis()).setSmallIcon(17301634).getNotification().flags = i3;
            } else {
                Notification notification = new Notification();
                notification.icon = 17301634;
                notification.when = System.currentTimeMillis();
                notification.flags = i3;
                n.a(notification, downloadService.getApplicationContext(), str2, str, service);
            }
            if (downloadService.f != null) {
                downloadService.c.notify(i, downloadService.f);
            }
        }
    }

    public static /* synthetic */ void a(DownloadService downloadService, c cVar) {
        if (cVar.e()) {
            b.b(downloadService.getApplicationContext(), cVar);
            return;
        }
        String str = cVar.a() ? ((i) cVar).P : cVar.b() ? ((s) cVar).I : "";
        if (cVar.a() && !TextUtils.isEmpty(str)) {
            i iVar = (i) cVar;
            String str2 = iVar.I ? iVar.E : z[12];
            PackageInfo packageInfo = null;
            try {
                packageInfo = downloadService.getPackageManager().getPackageArchiveInfo(str, 16384);
            } catch (Exception e) {
                ac.h();
            }
            String str3 = packageInfo != null ? packageInfo.packageName : "";
            if (TextUtils.isEmpty(str3) && !TextUtils.isEmpty(iVar.a)) {
                str3 = iVar.a;
            }
            cn.jpush.android.data.b.a(downloadService, cVar, str3, str2);
            if (h.a(iVar.G, iVar.H, downloadService.getApplicationContext())) {
                downloadService.a(cVar, true);
                return;
            }
            if (iVar.N) {
                downloadService.a(cVar, false);
            }
            if (iVar.O) {
                b.e(downloadService.getApplicationContext(), str);
            }
            if (!iVar.N && !iVar.O) {
                ac.b();
            }
        } else if (!cVar.b() || TextUtils.isEmpty(str)) {
            new StringBuilder(z[13]).append(str);
            ac.b();
        } else {
            downloadService.a(cVar, false);
        }
    }

    public static boolean a() {
        return a.size() > 0;
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        ac.b();
        super.onCreate();
        this.e = new g(this, getApplicationContext());
        this.c = (NotificationManager) getSystemService(z[3]);
        if (b == null) {
            b = new Bundle();
        }
        try {
            if (this.m.intValue() == 0) {
                HashMap<String, Integer> a2 = n.a(z[0], new String[]{z[4]});
                if (a2.size() > 0) {
                    this.m = a2.get(z[4]);
                }
                HashMap<String, Integer> a3 = n.a(z[6], new String[]{z[2], z[5], z[1], z[7]});
                if (a3.size() > 0) {
                    this.i = a3.get(z[2]);
                    this.j = a3.get(z[5]);
                    this.k = a3.get(z[1]);
                    this.l = a3.get(z[7]);
                }
            }
        } catch (Exception e) {
            ac.h();
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        ac.b();
        super.onDestroy();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        boolean z2;
        boolean z3;
        ac.b();
        this.d = (c) intent.getSerializableExtra(z[9]);
        if (this.d == null) {
            ac.d();
        } else if (!Environment.getExternalStorageState().equals(z[19])) {
            ac.d();
            this.e.sendEmptyMessage(0);
        } else if (this.d.x) {
            ac.b();
        } else {
            if (this.d.v) {
                k.a(this.d.c, 1012, this);
            }
            if (!a.contains(this.d)) {
                a.offer(this.d);
            }
            int a2 = n.a(this.d, 1);
            c cVar = this.d;
            if (cVar.e()) {
                z3 = true;
            } else {
                if (cVar.a()) {
                    i iVar = (i) cVar;
                    if (!h.a(iVar.G, iVar.H, this)) {
                        a(cVar, a2, 0L, 0L);
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                } else {
                    z2 = false;
                }
                z3 = z2 || cVar.b();
            }
            Thread.currentThread().setPriority(1);
            new b(this, this.d, b, new e(this, z3, a2), 3000);
        }
    }
}

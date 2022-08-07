package cn.jpush.android.helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import cn.jpush.a.a.a.f;
import cn.jpush.a.a.a.g;
import cn.jpush.android.a;
import cn.jpush.android.e;
import cn.jpush.android.service.PushProtocol;
import cn.jpush.android.service.k;
import cn.jpush.android.util.ac;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/* loaded from: classes.dex */
public final class b {
    private static b g;
    private static final String[] z;
    private Handler a;
    private Context b;
    private Map<Long, c> c = new ConcurrentHashMap();
    private Deque<c> d = new LinkedBlockingDeque();
    private Deque<c> e = new LinkedBlockingDeque();
    private boolean f = true;

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
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "!X\u001bwz\u0000IJr~\u0001\\\u0007q?^\u001d\to{I";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "_\u001d\u0019k{I";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "2^\u001ekp\u001d\u001dG\"l\u0016S\u000eAp\u001eP\u000bl{$T\u001ejS\u001cZ\rg{:SJ/?\u0001X\u001bwz\u0000I\u0003lxI";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "2^\u001ekp\u001d\u001dG\"l\u0007\\\u0018vL\u0016S\u001eVv\u001eX\u0005wkS\u0010Jpv\u0017\u0007";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "_\u001d\u001ekr\u0016R\u001fv%A\rZ2/";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "9m\u001fqw!X\u001bwz\u0000I\"gs\u0003X\u0018";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "2^\u001ekp\u001d\u001dG\"l\u0007\\\u0018vM\u0016L\u001fgl\u0007i\u0003oz\u001cH\u001e\"2SO\u0003f%";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "_\u001d\u001ejm\u0016\\\u000eK{I";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "2^\u001ekp\u001d\u001dG\"l\u0016S\u000eHO\u0006N\u0002Pz\u0002H\u000fqkS\u0010Jap\u001dS\u000fak\u001aR\u00048";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "_\u001d\u0018gl\u0003R\u0004qzI";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "2^\u001ekp\u001d\u001dG\"w\u0012S\u000enz\u0001w:wl\u001bo\u000fqo\u001cS\u0019g?^\u001d\tmq\u001dX\tvv\u001cSP";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "2^\u001ekp\u001d\u001dG\"z\u001dY9gq\u0007i\u0003oz\u001cH\u001e\"2SO\u0003f%";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "2^\u001ekp\u001d\u001dG\"{\u0016L\u001fgL\u0016S\u001eSj\u0016H\u000f\"2SO\u0003f%";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0010SDho\u0006N\u0002,~\u001dY\u0018mv\u0017\u0013\u0003lk\u0016S\u001e,K2z5CS:|9]K:p/MJ'";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "_\u001d\u0018gn\u0006X\u0019vv\u001dZP";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0007\\\rcs\u001a\\\u0019]l\u0016L\u0003f";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0007\\\rcs\u001a\\\u0019]z\u0001O\u0005p|\u001cY\u000f";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "2^\u001ekp\u001d\u001dG\"p\u001do\u000fsj\u0016N\u001eVv\u001eX\u0005wk_\u001d\u0018k{I\u001d";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "_\u001d?lz\u000bM\u000fak\u0016YJ/?\u001dR\u001e\"y\u001cH\u0004f?\u0001X\u001bwz\u0000IJkqS^\u000baw\u0016\u0013";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "2^\u001ekp\u001d\u001dG\"o\u0001T\u0004vL\u0016S\u001eSj\u0016H\u000f\"2SN\u0003xzI";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "2^\u001ekp\u001d\u001dG\"z\u001dL\u001fgL\u0016S\u001eSj\u0016H\u000f\"2SO\u0003f%";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "!X\u001epfSI\u0005\"l\u0016S\u000e\"m\u0016L\u001fgl\u0007\u001dG\"";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u0012^\u001ekp\u001d\u001dG\"p\u001dn\u000flk'T\u0007gp\u0006IF\"m\u001aYP";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "_\u001d?lz\u000bM\u000fak\u0016YP\"q\u001c\u001d\tc|\u001bX\u000e\"m\u0016L\u001fgl\u0007\u001d\u001djz\u001d\u001d\u0019gq\u0007\u001d\u001ekr\u0016R\u001fv1";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "_\u001d\u0018gn\u0006X\u0019v%";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "2^\u001ekp\u001d\u001dG\"z\u001dY8gn\u0006X\u0019vK\u001aP\u000fmj\u0007\u001dG\"m\u001aYP";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        cn.jpush.android.helpers.b.z = r3;
        cn.jpush.android.helpers.b.g = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0149, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x014a, code lost:
        r9 = 's';
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x014e, code lost:
        r9 = '=';
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0152, code lost:
        r9 = 'j';
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0156, code lost:
        r9 = 2;
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
            case 0: goto L_0x014a;
            case 1: goto L_0x014e;
            case 2: goto L_0x0152;
            case 3: goto L_0x0156;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.b.<clinit>():void");
    }

    private b(Context context, Handler handler) {
        this.a = null;
        this.b = null;
        this.b = context;
        this.a = handler;
    }

    public static synchronized b a(Context context, Handler handler) {
        b bVar;
        synchronized (b.class) {
            if (g == null) {
                g = new b(context, handler);
            }
            bVar = g;
        }
        return bVar;
    }

    private void a(c cVar) {
        new StringBuilder(z[3]).append(cVar.toString());
        ac.b();
        f fVar = cVar.a;
        Long a = fVar.e().a();
        int d = fVar.d();
        long y = a.y();
        int l = a.l();
        new StringBuilder(z[1]).append(d).append(z[2]).append(l).append(z[0]).append(y);
        ac.b();
        switch (d) {
            case 10:
                PushProtocol.TagAlias(k.a.get(), a.longValue(), l, y, e.f, ((cn.jpush.a.a.a.k) fVar).a());
                break;
            default:
                ac.d();
                break;
        }
        b(cVar);
        new StringBuilder(z[4]).append(a);
        ac.b();
        this.a.sendMessageDelayed(Message.obtain(this.a, 7404, a), 9800L);
    }

    private synchronized void b(c cVar) {
        boolean z2;
        long longValue = cVar.a.e().a().longValue();
        Iterator<c> it = this.e.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().a.e().a().longValue() == longValue) {
                    z2 = true;
                    break;
                }
            } else {
                z2 = false;
                break;
            }
        }
        if (!z2) {
            new StringBuilder(z[21]).append(longValue);
            ac.b();
            this.e.offerLast(cVar);
            if (this.e != null) {
                new StringBuilder(z[20]).append(this.e.size());
                ac.a();
            }
        }
    }

    private synchronized c c(Long l) {
        c cVar;
        new StringBuilder(z[13]).append(l);
        ac.b();
        cVar = null;
        Iterator<c> it = this.e.iterator();
        while (it.hasNext()) {
            cVar = it.next();
            if (l.longValue() == cVar.a.e().a().longValue()) {
                this.e.remove(cVar);
            } else {
                cVar = cVar;
            }
        }
        return cVar;
    }

    private void c(c cVar) {
        Long a = cVar.a.e().a();
        new StringBuilder(z[26]).append(a);
        ac.a();
        if (this.c.remove(a) == null) {
            ac.d();
        }
        this.d.remove(cVar);
        this.a.removeMessages(7403, a);
    }

    public final void a(long j, g gVar) {
        new StringBuilder(z[11]).append(j).append(z[10]).append(gVar.toString());
        ac.b();
        if (j != k.a.get()) {
            ac.d();
        }
        Long a = gVar.e().a();
        c c = c(a);
        if (c != null) {
            Long a2 = c.a.e().a();
            new StringBuilder(z[12]).append(a2);
            ac.b();
            this.a.removeMessages(7404, a2);
        } else {
            ac.d();
        }
        c cVar = this.c.get(a);
        if (cVar != null) {
            c(cVar);
        } else {
            ac.d();
        }
    }

    public final void a(f fVar, int i) {
        ac.b(z[6], z[9] + k.a.get() + z[5] + z[8] + Thread.currentThread().getId());
        fVar.toString();
        ac.a();
        Long a = fVar.e().a();
        c cVar = new c(fVar, 20000);
        this.c.put(a, cVar);
        Long a2 = cVar.a.e().a();
        new StringBuilder(z[7]).append(a2);
        ac.a();
        this.a.sendMessageDelayed(Message.obtain(this.a, 7403, a2), cVar.b);
        if (k.b.get() || !this.f) {
            ac.c();
            this.d.offerLast(cVar);
            return;
        }
        cVar.a();
        a(cVar);
    }

    public final void a(Long l) {
        c cVar = this.c.get(l);
        if (cVar == null) {
            new StringBuilder(z[23]).append(l).append(z[24]);
            ac.d();
            return;
        }
        new StringBuilder(z[23]).append(l).append(z[25]).append(cVar.toString());
        ac.b();
        c(l);
        if (cVar.b > 0) {
            if (this.f) {
                new StringBuilder(z[22]).append(cVar.toString());
                ac.a();
                cVar.a();
                a(cVar);
            } else {
                ac.a();
                this.d.offerFirst(cVar);
            }
            if (cVar.c >= 2) {
                this.a.sendEmptyMessageDelayed(1005, 2000L);
                return;
            }
            return;
        }
        b(l);
    }

    public final void b(Long l) {
        c cVar = this.c.get(l);
        if (cVar == null) {
            new StringBuilder(z[18]).append(l).append(z[19]);
            ac.d();
            return;
        }
        new StringBuilder(z[18]).append(l).append(z[15]).append(cVar.toString());
        ac.b();
        int d = cVar.a.d();
        c(cVar);
        switch (d) {
            case 10:
                f fVar = cVar.a;
                ac.b();
                Intent intent = new Intent();
                intent.addCategory(e.c);
                intent.setAction(z[14]);
                intent.putExtra(z[17], cn.jpush.android.api.e.b);
                intent.putExtra(z[16], fVar.e().a().longValue());
                this.b.sendBroadcast(intent);
                return;
            default:
                ac.b();
                return;
        }
    }
}

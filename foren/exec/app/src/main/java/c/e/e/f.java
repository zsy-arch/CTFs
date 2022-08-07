package c.e.e;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import c.a.a.C;
import c.c.g;
import c.c.i;
import c.e.b.a.j;
import c.e.e.k;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class f {

    /* renamed from: a */
    public static final g<String, Typeface> f799a = new g<>(16);

    /* renamed from: b */
    public static final k f800b = new k("fonts", 10, 10000);

    /* renamed from: c */
    public static final Object f801c = new Object();

    /* renamed from: d */
    public static final i<String, ArrayList<k.a<c>>> f802d = new i<>();

    /* renamed from: e */
    public static final Comparator<byte[]> f803e = new e();

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public final int f804a;

        /* renamed from: b */
        public final b[] f805b;

        public a(int i, b[] bVarArr) {
            this.f804a = i;
            this.f805b = bVarArr;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a */
        public final Uri f806a;

        /* renamed from: b */
        public final int f807b;

        /* renamed from: c */
        public final int f808c;

        /* renamed from: d */
        public final boolean f809d;

        /* renamed from: e */
        public final int f810e;

        public b(Uri uri, int i, int i2, boolean z, int i3) {
            if (uri != null) {
                this.f806a = uri;
                this.f807b = i;
                this.f808c = i2;
                this.f809d = z;
                this.f810e = i3;
                return;
            }
            throw new NullPointerException();
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a */
        public final Typeface f811a;

        /* renamed from: b */
        public final int f812b;

        public c(Typeface typeface, int i) {
            this.f811a = typeface;
            this.f812b = i;
        }
    }

    public static c a(Context context, a aVar, int i) {
        try {
            a a2 = a(context, (CancellationSignal) null, aVar);
            int i2 = a2.f804a;
            int i3 = -3;
            if (i2 == 0) {
                Typeface a3 = c.e.c.c.f776a.a(context, (CancellationSignal) null, a2.f805b, i);
                if (a3 != null) {
                    i3 = 0;
                }
                return new c(a3, i3);
            }
            if (i2 == 1) {
                i3 = -2;
            }
            return new c(null, i3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new c(null, -1);
        }
    }

    public static Typeface a(Context context, a aVar, j jVar, Handler handler, boolean z, int i, int i2) {
        String str = aVar.f + "-" + i2;
        Typeface a2 = f799a.a((g<String, Typeface>) str);
        if (a2 != null) {
            if (jVar != null) {
                jVar.a(a2);
            }
            return a2;
        } else if (!z || i != -1) {
            b bVar = new b(context, aVar, i2, str);
            if (z) {
                try {
                    return ((c) f800b.a(bVar, i)).f811a;
                } catch (InterruptedException unused) {
                    return null;
                }
            } else {
                c cVar = jVar == null ? null : new c(jVar, handler);
                synchronized (f801c) {
                    if (f802d.a(str) >= 0) {
                        if (cVar != null) {
                            f802d.get(str).add(cVar);
                        }
                        return null;
                    }
                    if (cVar != null) {
                        ArrayList<k.a<c>> arrayList = new ArrayList<>();
                        arrayList.add(cVar);
                        f802d.put(str, arrayList);
                    }
                    f800b.a(bVar, new d(str));
                    return null;
                }
            }
        } else {
            c a3 = a(context, aVar, i2);
            if (jVar != null) {
                int i3 = a3.f812b;
                if (i3 == 0) {
                    jVar.a(a3.f811a, handler);
                } else {
                    jVar.a(i3, handler);
                }
            }
            return a3.f811a;
        }
    }

    public static Map<Uri, ByteBuffer> a(Context context, b[] bVarArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (b bVar : bVarArr) {
            if (bVar.f810e == 0) {
                Uri uri = bVar.f806a;
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, C.a(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0092 A[LOOP:1: B:14:0x004d->B:27:0x0092, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0096 A[EDGE_INSN: B:75:0x0096->B:29:0x0096 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static c.e.e.f.a a(android.content.Context r20, android.os.CancellationSignal r21, c.e.e.a r22) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.e.f.a(android.content.Context, android.os.CancellationSignal, c.e.e.a):c.e.e.f$a");
    }
}

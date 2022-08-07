package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import com.amap.api.col.dz;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IMarker;
import com.hyphenate.util.EMPrivateConstant;
import java.io.Serializable;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: MapOverlayImageView.java */
/* loaded from: classes.dex */
public class p {
    public static b c;
    k a;
    private IPoint k;
    private ce l;
    private ce m;
    private FloatBuffer p;
    private List<ch> f = new ArrayList(500);
    private List<r> g = new ArrayList();
    private List<ch> h = new ArrayList();
    private a i = new a();
    private boolean j = true;
    private Handler q = new Handler(Looper.getMainLooper());
    private int[] r = new int[1];
    float[] b = new float[180000];
    int d = 0;
    int e = 0;
    private Runnable s = new Runnable() { // from class: com.amap.api.col.p.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (p.this.f) {
                p.this.k();
            }
        }
    };
    private dz n = new dz(512, 1024);
    private dm o = new dm();

    public p(Context context, k kVar) {
        this.a = kVar;
    }

    public Marker a(MarkerOptions markerOptions) throws RemoteException {
        Marker marker;
        if (markerOptions == null) {
            return null;
        }
        cm cmVar = new cm(markerOptions, this);
        synchronized (this.f) {
            d(cmVar);
            marker = new Marker(cmVar);
        }
        return marker;
    }

    public ArrayList<Marker> a(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        MarkerOptions markerOptions;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<Marker> arrayList2 = new ArrayList<>();
        try {
            if (arrayList.size() != 1 || (markerOptions = arrayList.get(0)) == null) {
                final LatLngBounds.Builder builder = LatLngBounds.builder();
                for (int i = 0; i < arrayList.size(); i++) {
                    MarkerOptions markerOptions2 = arrayList.get(i);
                    if (arrayList.get(i) != null) {
                        arrayList2.add(a(markerOptions2));
                        if (markerOptions2.getPosition() != null) {
                            builder.include(markerOptions2.getPosition());
                        }
                    }
                }
                if (z && arrayList2.size() > 0) {
                    this.a.getMainHandler().postDelayed(new Runnable() { // from class: com.amap.api.col.p.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                p.this.a.a(z.a(builder.build(), 50));
                            } catch (Throwable th) {
                            }
                        }
                    }, 50L);
                }
            } else {
                arrayList2.add(a(markerOptions));
                if (z && markerOptions.getPosition() != null) {
                    this.a.a(z.a(markerOptions.getPosition(), 18.0f));
                }
            }
            return arrayList2;
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImpGLSurfaceView", "addMarkers");
            th.printStackTrace();
            return arrayList2;
        }
    }

    public Text a(TextOptions textOptions) throws RemoteException {
        Text text;
        if (textOptions == null) {
            return null;
        }
        synchronized (this.f) {
            cs csVar = new cs(textOptions, this);
            d(csVar);
            text = new Text(csVar);
        }
        return text;
    }

    private void d(ch chVar) {
        try {
            this.f.add(chVar);
            g();
        } catch (Throwable th) {
            gr.b(th, "MapOverlayImageView", "addMarker");
        }
    }

    public boolean a(ch chVar) {
        boolean remove;
        synchronized (this.f) {
            if (this.m != null && this.m.getId().equals(chVar.getId())) {
                this.m = null;
            }
            b(chVar);
            remove = this.f.remove(chVar);
        }
        return remove;
    }

    public void a(ce ceVar) {
        try {
            if (this.m != null) {
                if (ceVar == null || !ceVar.getId().equals(this.m.getId())) {
                    this.m.b(false);
                } else {
                    return;
                }
            }
            if (this.f.contains(ceVar)) {
                ceVar.b(true);
                this.m = ceVar;
            }
        } catch (Throwable th) {
            gr.b(th, "MapOverlayImageView", "set2Top");
        }
    }

    public void b(ce ceVar) {
        if (this.k == null) {
            this.k = IPoint.obtain();
        }
        Rect h = ceVar.h();
        this.k = IPoint.obtain(h.left + (h.width() / 2), h.top);
        this.l = ceVar;
        try {
            this.a.a(this.l);
        } catch (Throwable th) {
            gr.b(th, "MapOverlayImageView", "showInfoWindow");
            th.printStackTrace();
        }
    }

    public void b(ch chVar) {
        try {
            if (chVar.isInfoWindowShown()) {
                this.a.i();
                this.l = null;
            } else if (this.l != null && this.l.getId() == chVar.getId()) {
                this.l = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void k() {
        try {
            Collections.sort(this.f, this.i);
        } catch (Throwable th) {
            gr.b(th, "MapOverlayImageView", "changeOverlayIndex");
        }
    }

    public void a() {
        int i;
        int i2;
        int i3;
        try {
            i();
            float mapPerPixelUnitLength = this.a.getMapConfig().getMapPerPixelUnitLength();
            synchronized (this.f) {
                if (this.f.size() != 0) {
                    this.h.clear();
                    int size = this.f.size();
                    for (int i4 = 0; i4 < size; i4++) {
                        ch chVar = this.f.get(i4);
                        if (chVar.isVisible() && !chVar.l()) {
                            this.j = chVar.i();
                            if (chVar.j() || chVar.isInfoWindowShown()) {
                                try {
                                    this.h.add(chVar);
                                } catch (Throwable th) {
                                    th.printStackTrace();
                                }
                            }
                        }
                    }
                    if (this.m != null && this.m.isVisible()) {
                        this.h.add(this.m);
                    }
                    if (this.h.size() > 0) {
                        int size2 = this.h.size();
                        int i5 = 0;
                        int i6 = 0;
                        int i7 = 0;
                        int i8 = 0;
                        while (i5 < size2) {
                            ch chVar2 = this.h.get(i5);
                            chVar2.a(this.a);
                            if (i5 == 0) {
                                i8 = chVar2.k();
                            } else {
                                int k = chVar2.k();
                                if (!(k == i8 || i8 == 0)) {
                                    a(i8, i7, i6);
                                    i6 = 0;
                                    i7 = 0;
                                    i8 = k;
                                }
                            }
                            chVar2.a(this.a, this.b, i6, mapPerPixelUnitLength);
                            int k2 = chVar2.k();
                            if (k2 == i8 || i8 == 0) {
                                i = i6;
                                i2 = i7;
                                i3 = i8;
                            } else {
                                a(i8, i7, i6);
                                i2 = 0;
                                i3 = k2;
                                i = 0;
                            }
                            i6 = i + 36;
                            i7 = i2 + 1;
                            if (i7 == 5000) {
                                a(i3, i7, i6);
                                i6 = 0;
                                i7 = 0;
                            }
                            i5++;
                            i8 = i3;
                        }
                        if (i7 > 0) {
                            a(i8, i7, i6);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private void a(int i, int i2, int i3) {
        if (i2 != 0) {
            this.p = this.o.c(i2 * 36);
            this.p.put(this.b, 0, i3);
            this.p.flip();
            a(i2);
            a(i, i3, i2, this.p, this.a.getMapConfig());
            this.o.a();
        }
    }

    private void a(int i) {
        if (i > 5000) {
            i = 5000;
        }
        if (this.d == 0) {
            int[] iArr = new int[2];
            GLES20.glGenBuffers(2, iArr, 0);
            this.d = iArr[0];
            this.e = iArr[1];
            ShortBuffer b2 = this.o.b(30000);
            short[] sArr = new short[30000];
            for (int i2 = 0; i2 < 5000; i2++) {
                sArr[(i2 * 6) + 0] = (short) ((i2 * 4) + 0);
                sArr[(i2 * 6) + 1] = (short) ((i2 * 4) + 1);
                sArr[(i2 * 6) + 2] = (short) ((i2 * 4) + 2);
                sArr[(i2 * 6) + 3] = (short) ((i2 * 4) + 0);
                sArr[(i2 * 6) + 4] = (short) ((i2 * 4) + 2);
                sArr[(i2 * 6) + 5] = (short) ((i2 * 4) + 3);
            }
            b2.put(sArr);
            b2.flip();
            GLES20.glBindBuffer(34963, this.e);
            GLES20.glBufferData(34963, 60000, b2, 35044);
        }
        GLES20.glBindBuffer(34962, this.d);
        GLES20.glBufferData(34962, i * 36 * 4, this.p, 35044);
    }

    public static void b() {
        c = new b("texture_normal.glsl");
    }

    /* compiled from: MapOverlayImageView.java */
    /* loaded from: classes.dex */
    public static class b extends cu {
        int a;
        int b;
        int c;
        int d;
        int e;

        b(String str) {
            if (a(str)) {
                this.a = c("aMVP");
                dg.a("getUniform");
                this.e = c("aMapBearing");
                this.b = b("aVertex");
                this.c = b("aTextureCoord");
                this.d = b("aBearingTiltAlpha");
            }
        }
    }

    private void a(int i, int i2, int i3, FloatBuffer floatBuffer, MapConfig mapConfig) {
        if (i != 0 && floatBuffer != null && i3 != 0) {
            c.b();
            GLES20.glUniform1f(c.e, mapConfig.getS_r());
            GLES20.glEnableVertexAttribArray(c.b);
            GLES20.glBindBuffer(34962, this.d);
            GLES20.glVertexAttribPointer(c.b, 4, 5126, false, 36, 0);
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(1, 771);
            GLES20.glBindTexture(3553, i);
            GLES20.glEnableVertexAttribArray(c.c);
            GLES20.glBindBuffer(34962, this.d);
            GLES20.glVertexAttribPointer(c.c, 2, 5126, false, 36, 16);
            GLES20.glEnableVertexAttribArray(c.d);
            GLES20.glBindBuffer(34962, this.d);
            GLES20.glVertexAttribPointer(c.d, 3, 5126, false, 36, 24);
            GLES20.glUniformMatrix4fv(c.a, 1, false, c(), 0);
            GLES20.glBindBuffer(34963, this.e);
            GLES20.glDrawElements(4, i3 * 6, 5123, 0);
            GLES20.glBindBuffer(34962, 0);
            GLES20.glBindBuffer(34963, 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glDisableVertexAttribArray(c.b);
            GLES20.glDisableVertexAttribArray(c.c);
            GLES20.glDisable(3042);
            GLES20.glUseProgram(0);
        }
    }

    public synchronized boolean a(Bitmap bitmap, r rVar) {
        boolean z;
        dz.c a2 = this.n.a(bitmap.getWidth(), bitmap.getHeight(), rVar.j());
        if (a2 != null) {
            rVar.b(a2.a / this.n.a());
            rVar.a(a2.b / this.n.b());
            rVar.c((a2.a + a2.c) / this.n.a());
            rVar.d((a2.d + a2.b) / this.n.b());
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public float[] c() {
        return this.a != null ? this.a.u() : new float[16];
    }

    public k d() {
        return this.a;
    }

    public ce e() {
        return this.l;
    }

    public ce a(MotionEvent motionEvent) {
        ce ceVar;
        synchronized (this.f) {
            int size = this.f.size() - 1;
            while (true) {
                if (size < 0) {
                    ceVar = null;
                    break;
                }
                ch chVar = this.f.get(size);
                if ((chVar instanceof cm) && dt.a(chVar.h(), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.l = (ce) chVar;
                    ceVar = this.l;
                    break;
                }
                size--;
            }
        }
        return ceVar;
    }

    public boolean b(MotionEvent motionEvent) throws RemoteException {
        boolean z;
        Rect h;
        boolean a2;
        synchronized (this.f) {
            int size = this.f.size() - 1;
            while (true) {
                if (size < 0) {
                    z = false;
                    break;
                }
                ch chVar = this.f.get(size);
                if ((chVar instanceof cm) && chVar.isVisible() && ((cm) chVar).isClickable() && (a2 = dt.a((h = chVar.h()), (int) motionEvent.getX(), (int) motionEvent.getY()))) {
                    this.k = IPoint.obtain(h.left + (h.width() / 2), h.top);
                    this.l = (ce) chVar;
                    z = a2;
                    break;
                }
                size--;
            }
        }
        return z;
    }

    public List<Marker> f() {
        ArrayList arrayList;
        synchronized (this.f) {
            arrayList = new ArrayList();
            for (ch chVar : this.f) {
                if ((chVar instanceof cm) && chVar.j()) {
                    arrayList.add(new Marker((IMarker) chVar));
                }
            }
        }
        return arrayList;
    }

    public void g() {
        this.q.removeCallbacks(this.s);
        this.q.postDelayed(this.s, 10L);
    }

    public boolean c(ch chVar) {
        boolean contains;
        synchronized (this.f) {
            contains = this.f.contains(chVar);
        }
        return contains;
    }

    public int h() {
        int size;
        synchronized (this.f) {
            size = this.f.size();
        }
        return size;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001b A[Catch: all -> 0x0053, TRY_ENTER, TryCatch #1 {Throwable -> 0x0056, blocks: (B:4:0x0004, B:7:0x000f, B:8:0x001a, B:9:0x001b, B:11:0x0022, B:12:0x0027, B:15:0x002b, B:17:0x0034, B:19:0x0046, B:21:0x004d), top: B:26:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            if (r7 == 0) goto L_0x000e
            java.lang.String r2 = r7.trim()     // Catch: Throwable -> 0x0056
            int r2 = r2.length()     // Catch: Throwable -> 0x0056
            if (r2 != 0) goto L_0x0029
        L_0x000e:
            r2 = 1
        L_0x000f:
            r3 = 0
            r6.l = r3     // Catch: Throwable -> 0x0056
            r3 = 0
            r6.k = r3     // Catch: Throwable -> 0x0056
            r3 = 0
            r6.m = r3     // Catch: Throwable -> 0x0056
            java.util.List<com.amap.api.col.ch> r3 = r6.f     // Catch: Throwable -> 0x0056
            monitor-enter(r3)     // Catch: Throwable -> 0x0056
            java.util.List<com.amap.api.col.ch> r4 = r6.h     // Catch: all -> 0x0053
            r4.clear()     // Catch: all -> 0x0053
            if (r2 == 0) goto L_0x002b
            java.util.List<com.amap.api.col.ch> r0 = r6.f     // Catch: all -> 0x0053
            r0.clear()     // Catch: all -> 0x0053
        L_0x0027:
            monitor-exit(r3)     // Catch: all -> 0x0053
        L_0x0028:
            return
        L_0x0029:
            r2 = r0
            goto L_0x000f
        L_0x002b:
            java.util.List<com.amap.api.col.ch> r2 = r6.f     // Catch: all -> 0x0053
            int r4 = r2.size()     // Catch: all -> 0x0053
            r2 = r0
        L_0x0032:
            if (r2 >= r4) goto L_0x0066
            java.util.List<com.amap.api.col.ch> r0 = r6.f     // Catch: all -> 0x0053
            java.lang.Object r0 = r0.get(r2)     // Catch: all -> 0x0053
            com.amap.api.col.ch r0 = (com.amap.api.col.ch) r0     // Catch: all -> 0x0053
            java.lang.String r5 = r0.getId()     // Catch: all -> 0x0053
            boolean r5 = r7.equals(r5)     // Catch: all -> 0x0053
            if (r5 == 0) goto L_0x0062
        L_0x0046:
            java.util.List<com.amap.api.col.ch> r1 = r6.f     // Catch: all -> 0x0053
            r1.clear()     // Catch: all -> 0x0053
            if (r0 == 0) goto L_0x0027
            java.util.List<com.amap.api.col.ch> r1 = r6.f     // Catch: all -> 0x0053
            r1.add(r0)     // Catch: all -> 0x0053
            goto L_0x0027
        L_0x0053:
            r0 = move-exception
            monitor-exit(r3)     // Catch: all -> 0x0053
            throw r0     // Catch: Throwable -> 0x0056
        L_0x0056:
            r0 = move-exception
            java.lang.String r1 = "MapOverlayImageView"
            java.lang.String r2 = "clear"
            com.amap.api.col.gr.b(r0, r1, r2)
            r0.printStackTrace()
            goto L_0x0028
        L_0x0062:
            int r0 = r2 + 1
            r2 = r0
            goto L_0x0032
        L_0x0066:
            r0 = r1
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p.a(java.lang.String):void");
    }

    public void a(r rVar) {
        synchronized (this.g) {
            if (rVar != null) {
                this.g.add(rVar);
            }
        }
    }

    public void i() {
        synchronized (this.g) {
            int e = this.a.e();
            for (int i = 0; i < this.g.size(); i++) {
                r rVar = this.g.get(i);
                if (rVar != null) {
                    rVar.h();
                    if (rVar.i() == 0) {
                        if (rVar.f() == e) {
                            this.n.a(rVar.j());
                        } else {
                            this.r[0] = rVar.f();
                            GLES20.glDeleteTextures(1, this.r, 0);
                        }
                    }
                }
            }
        }
    }

    public void j() {
        try {
            for (ch chVar : this.f) {
                if (chVar != null) {
                    chVar.destroy(false);
                }
            }
            a((String) null);
        } catch (Throwable th) {
            gr.b(th, "MapOverlayImageView", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
            Log.d("amapApi", "MapOverlayImageView clear erro" + th.getMessage());
        }
    }

    /* compiled from: MapOverlayImageView.java */
    /* loaded from: classes.dex */
    public static class a implements Serializable, Comparator<Object> {
        a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            ch chVar = (ch) obj;
            ch chVar2 = (ch) obj2;
            if (!(chVar == null || chVar2 == null)) {
                try {
                    if (chVar.getZIndex() > chVar2.getZIndex()) {
                        return 1;
                    }
                    if (chVar.getZIndex() < chVar2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    gr.b(th, "MapOverlayImageView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }
}

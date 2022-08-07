package com.amap.api.col;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import com.amap.api.col.db;
import com.amap.api.col.du;
import com.amap.api.col.dx;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.ITileOverlay;
import java.lang.ref.WeakReference;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: TileOverlayDelegateImp.java */
/* loaded from: classes.dex */
public class ct implements cl {
    static a a;
    private static int h = 0;
    private u b;
    private TileProvider c;
    private Float d;
    private boolean e;
    private boolean f;
    private k g;
    private int i;
    private int j;
    private int k;
    private dv l;
    private List<b> m;
    private boolean n;
    private c o;
    private String p;
    private FloatBuffer q;

    private static String a(String str) {
        h++;
        return str + h;
    }

    public ct(TileOverlayOptions tileOverlayOptions, u uVar) {
        this.f = false;
        this.i = 256;
        this.j = 256;
        this.k = -1;
        this.m = new ArrayList();
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        this.b = uVar;
        this.c = tileOverlayOptions.getTileProvider();
        this.i = this.c.getTileWidth();
        this.j = this.c.getTileHeight();
        this.q = dt.a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
        this.d = Float.valueOf(tileOverlayOptions.getZIndex());
        this.e = tileOverlayOptions.isVisible();
        this.p = getId();
        this.g = this.b.a();
        this.k = Integer.parseInt(this.p.substring("TileOverlay".length()));
        try {
            du.a aVar = new du.a(this.b.e(), this.p);
            aVar.a(tileOverlayOptions.getMemoryCacheEnabled());
            aVar.b(tileOverlayOptions.getDiskCacheEnabled());
            aVar.a(tileOverlayOptions.getMemCacheSize());
            aVar.a(tileOverlayOptions.getDiskCacheSize());
            String diskCacheDir = tileOverlayOptions.getDiskCacheDir();
            if (diskCacheDir != null && !diskCacheDir.equals("")) {
                aVar.a(diskCacheDir);
            }
            this.l = new dv(this.b.e(), this.i, this.j);
            this.l.a(this.c);
            this.l.a(aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public ct(TileOverlayOptions tileOverlayOptions, u uVar, boolean z) {
        this(tileOverlayOptions, uVar);
        this.f = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void remove() {
        this.b.b(this);
        this.g.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void destroy(boolean z) {
        if (this.o != null && this.o.a() == db.d.RUNNING) {
            this.o.a(true);
        }
        synchronized (this.m) {
            int size = this.m.size();
            for (int i = 0; i < size; i++) {
                this.m.get(i).b();
            }
            this.m.clear();
        }
        if (this.l != null) {
            this.l.g();
            this.l.a(true);
            this.l.a((TileProvider) null);
        }
        if (z) {
            remove();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void clearTileCache() {
        if (this.l != null) {
            this.l.f();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public String getId() {
        if (this.p == null) {
            this.p = a("TileOverlay");
        }
        return this.p;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void setZIndex(float f) {
        this.d = Float.valueOf(f);
        this.b.d();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public float getZIndex() {
        return this.d.floatValue();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void setVisible(boolean z) {
        this.e = z;
        this.g.setRunLowFrame(false);
        if (z) {
            a(true);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public boolean isVisible() {
        return this.e;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public boolean equalsRemote(ITileOverlay iTileOverlay) {
        return equals(iTileOverlay) || iTileOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public int hashCodeRemote() {
        return super.hashCode();
    }

    private boolean a(b bVar) {
        GLMapState b2 = this.g.b();
        float f = bVar.c;
        int i = this.i;
        int i2 = this.j;
        int i3 = bVar.e.x;
        int i4 = bVar.e.y + ((1 << (20 - ((int) f))) * i2);
        FPoint obtain = FPoint.obtain();
        b2.geo2Map(i3, i4, obtain);
        FPoint obtain2 = FPoint.obtain();
        b2.geo2Map(((1 << (20 - ((int) f))) * i) + i3, i4, obtain2);
        FPoint obtain3 = FPoint.obtain();
        b2.geo2Map((i * (1 << (20 - ((int) f)))) + i3, i4 - ((1 << (20 - ((int) f))) * i2), obtain3);
        FPoint obtain4 = FPoint.obtain();
        b2.geo2Map(i3, i4 - ((1 << (20 - ((int) f))) * i2), obtain4);
        float[] fArr = {obtain.x, obtain.y, 0.0f, obtain2.x, obtain2.y, 0.0f, obtain3.x, obtain3.y, 0.0f, obtain4.x, obtain4.y, 0.0f};
        if (bVar.h == null) {
            bVar.h = dt.a(fArr);
        } else {
            bVar.h = dt.a(fArr, bVar.h);
        }
        obtain.recycle();
        obtain4.recycle();
        obtain2.recycle();
        obtain3.recycle();
        return true;
    }

    @Override // com.amap.api.col.cl
    public void a() {
        if (this.m != null) {
            synchronized (this.m) {
                if (this.m.size() != 0) {
                    int size = this.m.size();
                    for (int i = 0; i < size; i++) {
                        b bVar = this.m.get(i);
                        if (!bVar.g) {
                            IPoint iPoint = bVar.e;
                            if (!(bVar.i == null || bVar.i.isRecycled() || iPoint == null)) {
                                bVar.f = dt.a(bVar.i);
                                if (bVar.f != 0) {
                                    bVar.g = true;
                                }
                                bVar.i = null;
                            }
                        }
                        if (bVar.g) {
                            a(bVar);
                            a(bVar.f, bVar.h, this.q);
                        }
                    }
                }
            }
        }
    }

    public static ArrayList<b> b(k kVar, int i, int i2, int i3, int i4, u uVar, dv dvVar) {
        GLMapState b2 = kVar.b();
        Rect rect = kVar.getMapConfig().getGeoRectangle().getRect();
        IPoint obtain = IPoint.obtain();
        IPoint obtain2 = IPoint.obtain();
        obtain.x = rect.left;
        obtain.y = rect.top;
        int min = Math.min(Integer.MAX_VALUE, obtain.x);
        int max = Math.max(0, obtain.x);
        int min2 = Math.min(Integer.MAX_VALUE, obtain.y);
        int max2 = Math.max(0, obtain.y);
        obtain.x = rect.right;
        obtain.y = rect.top;
        int min3 = Math.min(min, obtain.x);
        int max3 = Math.max(max, obtain.x);
        int min4 = Math.min(min2, obtain.y);
        int max4 = Math.max(max2, obtain.y);
        obtain.x = rect.left;
        obtain.y = rect.bottom;
        int min5 = Math.min(min3, obtain.x);
        int max5 = Math.max(max3, obtain.x);
        int min6 = Math.min(min4, obtain.y);
        int max6 = Math.max(max4, obtain.y);
        obtain.x = rect.right;
        obtain.y = rect.bottom;
        int min7 = Math.min(min5, obtain.x);
        int max7 = Math.max(max5, obtain.x);
        int min8 = Math.min(min6, obtain.y);
        int max8 = Math.max(max6, obtain.y);
        int i5 = min7 - ((1 << (20 - i)) * i2);
        int i6 = min8 - ((1 << (20 - i)) * i3);
        b2.getGeoCenter(obtain2);
        int i7 = (obtain2.x >> (20 - i)) / i2;
        int i8 = (obtain2.y >> (20 - i)) / i3;
        b bVar = new b(i7, i8, i, i4, kVar, uVar, dvVar);
        bVar.e = IPoint.obtain((i7 << (20 - i)) * i2, (i8 << (20 - i)) * i3);
        obtain.recycle();
        obtain2.recycle();
        ArrayList<b> arrayList = new ArrayList<>();
        arrayList.add(bVar);
        int i9 = 1;
        while (true) {
            boolean z = false;
            for (int i10 = i7 - i9; i10 <= i7 + i9; i10++) {
                int i11 = i8 + i9;
                IPoint iPoint = new IPoint((i10 << (20 - i)) * i2, (i11 << (20 - i)) * i3);
                if (iPoint.x < max7 && iPoint.x > i5 && iPoint.y < max8 && iPoint.y > i6) {
                    if (!z) {
                        z = true;
                    }
                    b bVar2 = new b(i10, i11, i, i4, kVar, uVar, dvVar);
                    bVar2.e = iPoint;
                    arrayList.add(bVar2);
                }
                int i12 = i8 - i9;
                IPoint iPoint2 = new IPoint((i10 << (20 - i)) * i2, (i12 << (20 - i)) * i3);
                if (iPoint2.x < max7 && iPoint2.x > i5 && iPoint2.y < max8 && iPoint2.y > i6) {
                    if (!z) {
                        z = true;
                    }
                    b bVar3 = new b(i10, i12, i, i4, kVar, uVar, dvVar);
                    bVar3.e = iPoint2;
                    arrayList.add(bVar3);
                }
            }
            for (int i13 = (i8 + i9) - 1; i13 > i8 - i9; i13--) {
                int i14 = i7 + i9;
                IPoint iPoint3 = new IPoint((i14 << (20 - i)) * i2, (i13 << (20 - i)) * i3);
                if (iPoint3.x < max7 && iPoint3.x > i5 && iPoint3.y < max8 && iPoint3.y > i6) {
                    if (!z) {
                        z = true;
                    }
                    b bVar4 = new b(i14, i13, i, i4, kVar, uVar, dvVar);
                    bVar4.e = iPoint3;
                    arrayList.add(bVar4);
                }
                int i15 = i7 - i9;
                IPoint iPoint4 = new IPoint((i15 << (20 - i)) * i2, (i13 << (20 - i)) * i3);
                if (iPoint4.x < max7 && iPoint4.x > i5 && iPoint4.y < max8 && iPoint4.y > i6) {
                    if (!z) {
                        z = true;
                    }
                    b bVar5 = new b(i15, i13, i, i4, kVar, uVar, dvVar);
                    bVar5.e = iPoint4;
                    arrayList.add(bVar5);
                }
            }
            if (!z) {
                return arrayList;
            }
            i9++;
        }
    }

    public void b() {
        if (this.m != null) {
            synchronized (this.m) {
                this.m.clear();
            }
        }
    }

    public static boolean b(k kVar, List<b> list, int i, boolean z, List<b> list2, boolean z2, u uVar, dv dvVar) {
        boolean z3;
        if (!(list == null || list2 == null)) {
            synchronized (list2) {
                for (b bVar : list2) {
                    Iterator<b> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z3 = false;
                            break;
                        }
                        b next = it.next();
                        if (bVar.equals(next) && bVar.g) {
                            next.g = bVar.g;
                            next.f = bVar.f;
                            z3 = true;
                            break;
                        }
                    }
                    if (!z3) {
                        bVar.b();
                    }
                }
                list2.clear();
            }
            if (i > ((int) kVar.getMaxZoomLevel()) || i < ((int) kVar.getMinZoomLevel())) {
                return false;
            }
            int size = list.size();
            if (size <= 0) {
                return false;
            }
            for (int i2 = 0; i2 < size; i2++) {
                b bVar2 = list.get(i2);
                if (bVar2 != null && (!z2 || (bVar2.c >= 10 && !dp.a(bVar2.a, bVar2.b, bVar2.c)))) {
                    list2.add(bVar2);
                    if (!bVar2.g && dvVar != null) {
                        dvVar.a(z, bVar2);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.amap.api.col.cl
    public void a(boolean z) {
        if (!this.n) {
            if (this.o != null && this.o.a() == db.d.RUNNING) {
                this.o.a(true);
            }
            this.o = new c(z, this.g, this.i, this.j, this.k, this.m, this.f, this.b, this.l);
            this.o.c((Object[]) new Void[0]);
        }
    }

    @Override // com.amap.api.col.cl
    public void b(boolean z) {
        if (this.n != z) {
            this.n = z;
            if (this.l != null) {
                this.l.a(z);
            }
        }
    }

    /* compiled from: TileOverlayDelegateImp.java */
    /* loaded from: classes.dex */
    public static class c extends db<Void, Void, List<b>> {
        private int d;
        private boolean e;
        private int f;
        private int g;
        private int h;
        private WeakReference<k> i;
        private List<b> j;
        private boolean k;
        private WeakReference<u> l;
        private WeakReference<dv> m;

        public c(boolean z, k kVar, int i, int i2, int i3, List<b> list, boolean z2, u uVar, dv dvVar) {
            this.f = 256;
            this.g = 256;
            this.h = 0;
            this.e = z;
            this.i = new WeakReference<>(kVar);
            this.f = i;
            this.g = i2;
            this.h = i3;
            this.j = list;
            this.k = z2;
            this.l = new WeakReference<>(uVar);
            this.m = new WeakReference<>(dvVar);
        }

        public List<b> a(Void... voidArr) {
            try {
                k kVar = this.i.get();
                if (kVar == null) {
                    return null;
                }
                int mapWidth = kVar.getMapWidth();
                int mapHeight = kVar.getMapHeight();
                this.d = (int) kVar.g();
                if (mapWidth <= 0 || mapHeight <= 0) {
                    return null;
                }
                return ct.b(kVar, this.d, this.f, this.g, this.h, this.l.get(), this.m.get());
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        public void a(List<b> list) {
            if (list != null) {
                try {
                    if (list.size() > 0) {
                        ct.b(this.i.get(), list, this.d, this.e, this.j, this.k, this.l.get(), this.m.get());
                        list.clear();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* compiled from: TileOverlayDelegateImp.java */
    /* loaded from: classes.dex */
    public static class b implements Cloneable {
        public int a;
        public int b;
        public int c;
        public int d;
        public IPoint e;
        public int f;
        public boolean g;
        public FloatBuffer h;
        public Bitmap i;
        public dx.a j;
        public int k;
        private final int l;
        private k m;
        private u n;
        private dv o;

        public b(int i, int i2, int i3, int i4, k kVar, u uVar, dv dvVar) {
            this.f = 0;
            this.g = false;
            this.h = null;
            this.i = null;
            this.j = null;
            this.k = 0;
            this.l = 3;
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.m = kVar;
            this.n = uVar;
            this.o = dvVar;
        }

        public b(b bVar) {
            this.f = 0;
            this.g = false;
            this.h = null;
            this.i = null;
            this.j = null;
            this.k = 0;
            this.l = 3;
            this.a = bVar.a;
            this.b = bVar.b;
            this.c = bVar.c;
            this.d = bVar.d;
            this.e = bVar.e;
            this.h = bVar.h;
            this.k = 0;
            this.n = bVar.n;
            this.m = bVar.m;
            this.o = bVar.o;
        }

        /* renamed from: a */
        public b clone() {
            try {
                b bVar = (b) super.clone();
                bVar.a = this.a;
                bVar.b = this.b;
                bVar.c = this.c;
                bVar.d = this.d;
                bVar.e = (IPoint) this.e.clone();
                bVar.h = this.h.asReadOnlyBuffer();
                this.k = 0;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return new b(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a == bVar.a && this.b == bVar.b && this.c == bVar.c && this.d == bVar.d;
        }

        public int hashCode() {
            return (this.a * 7) + (this.b * 11) + (this.c * 13) + this.d;
        }

        public String toString() {
            return this.a + "-" + this.b + "-" + this.c + "-" + this.d;
        }

        public synchronized void a(Bitmap bitmap) {
            if (bitmap != null) {
                if (!bitmap.isRecycled()) {
                    this.j = null;
                    this.i = bitmap;
                    this.m.setRunLowFrame(false);
                }
            }
            if (this.k < 3) {
                this.k++;
                if (this.o != null) {
                    this.o.a(true, this);
                }
            }
        }

        public void b() {
            try {
                dx.a(this);
                if (this.g) {
                    this.n.a(this.f);
                }
                this.g = false;
                this.f = 0;
                if (this.i != null && !this.i.isRecycled()) {
                    this.i.recycle();
                }
                this.i = null;
                if (this.h != null) {
                    this.h.clear();
                }
                this.h = null;
                this.j = null;
                this.k = 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void c() {
        a = new a("texture.glsl");
    }

    /* compiled from: TileOverlayDelegateImp.java */
    /* loaded from: classes.dex */
    public static class a extends cu {
        int a;
        int b;
        int c;

        a(String str) {
            if (a(str)) {
                this.a = c("aMVP");
                this.b = b("aVertex");
                this.c = b("aTextureCoord");
            }
        }
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null && i != 0) {
            a.b();
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(1, 771);
            GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
            GLES20.glBindTexture(3553, i);
            GLES20.glEnableVertexAttribArray(a.b);
            GLES20.glVertexAttribPointer(a.b, 3, 5126, false, 12, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(a.c);
            GLES20.glVertexAttribPointer(a.c, 2, 5126, false, 8, (Buffer) floatBuffer2);
            GLES20.glUniformMatrix4fv(a.a, 1, false, this.b.g(), 0);
            GLES20.glDrawArrays(6, 0, 4);
            GLES20.glDisableVertexAttribArray(a.b);
            GLES20.glDisableVertexAttribArray(a.c);
            GLES20.glBindTexture(3553, 0);
            GLES20.glUseProgram(0);
            GLES20.glDisable(3042);
        }
    }
}

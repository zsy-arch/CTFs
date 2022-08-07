package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.animation.GLAnimation;
import com.autonavi.amap.mapcore.interfaces.IInfoWindowManager;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import java.nio.FloatBuffer;

/* compiled from: PopupOverlay.java */
/* loaded from: classes.dex */
public class cr implements cd, cg, IInfoWindowManager {
    private GLAnimation D;
    private GLAnimation E;
    k a;
    private Context b;
    private AMap.MultiPositionInfoWindowAdapter c;
    private ce d;
    private FPoint j;
    private FloatBuffer n;
    private boolean q;
    private Bitmap r;
    private Bitmap s;
    private int w;
    private boolean e = false;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private FloatBuffer k = null;
    private boolean m = true;
    private float o = 0.5f;
    private float p = 1.0f;
    private Rect t = new Rect();

    /* renamed from: u  reason: collision with root package name */
    private float f13u = 0.0f;
    private float v = 0.0f;
    private boolean x = true;
    private Bitmap y = null;
    private Bitmap z = null;
    private Bitmap A = null;
    private Bitmap B = null;
    private boolean C = false;
    private boolean F = false;
    private boolean G = true;
    private String l = getId();

    public boolean f() {
        return this.x;
    }

    public void a(boolean z) {
        this.x = z;
    }

    public void a(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            c(this.y);
            this.y = bitmap;
        }
    }

    private synchronized void c(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }

    private void d(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            c(this.z);
            this.z = bitmap;
        }
    }

    private void e(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            c(this.A);
            this.A = bitmap;
        }
    }

    private void f(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            c(this.B);
            this.B = bitmap;
        }
    }

    private Bitmap h() {
        return this.y;
    }

    private Bitmap i() {
        return this.A;
    }

    public cr(k kVar, Context context) {
        this.a = null;
        this.b = context;
        this.a = kVar;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() {
        if (this.l == null) {
            this.l = "PopupOverlay";
        }
        return this.l;
    }

    public synchronized void b(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled() && (this.r == null || this.r.hashCode() != bitmap.hashCode())) {
                    if (this.r != null) {
                        if (this.y == null && this.z == null && this.A == null && this.B == null) {
                            c(this.s);
                            this.s = this.r;
                        } else if (!g(this.r)) {
                            c(this.s);
                            this.s = this.r;
                        }
                    }
                    this.C = false;
                    this.r = bitmap;
                }
            } catch (Throwable th) {
            }
        }
    }

    private boolean g(Bitmap bitmap) {
        if (this.y != null && bitmap.hashCode() == this.y.hashCode()) {
            return true;
        }
        if (this.A != null && bitmap.hashCode() == this.A.hashCode()) {
            return true;
        }
        if (this.z != null && bitmap.hashCode() == this.z.hashCode()) {
            return true;
        }
        if (this.B == null || bitmap.hashCode() != this.B.hashCode()) {
            return false;
        }
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) {
        if (!this.m && z) {
            this.q = true;
        }
        this.m = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() {
        return this.m;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() {
        return super.hashCode();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowAnimation(Animation animation, Animation.AnimationListener animationListener) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowAppearAnimation(Animation animation) {
        if (this.E == null || !this.E.equals(animation.glAnimation)) {
            this.D = animation.glAnimation;
            return;
        }
        try {
            this.D = animation.glAnimation.clone();
        } catch (Throwable th) {
            gr.b(th, "PopupOverlay", "setInfoWindowDisappearAnimation");
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowBackColor(int i) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowBackEnable(boolean z) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowBackScale(float f, float f2) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowDisappearAnimation(Animation animation) {
        if (this.D == null || !this.D.equals(animation.glAnimation)) {
            this.E = animation.glAnimation;
            return;
        }
        try {
            this.E = animation.glAnimation.clone();
        } catch (Throwable th) {
            gr.b(th, "PopupOverlay", "setInfoWindowDisappearAnimation");
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowMovingAnimation(Animation animation) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void startAnimation() {
    }

    public void a(int i, int i2) throws RemoteException {
        if (this.F) {
            this.h = i;
            this.i = i2;
            return;
        }
        this.f = i;
        this.g = i2;
        this.h = i;
        this.i = i2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() {
        return 0.0f;
    }

    @Override // com.amap.api.col.cg
    public boolean a() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (z) {
            b(h());
        } else {
            b(i());
        }
    }

    private void c(final boolean z) {
        if (this.E != null) {
            this.G = false;
            this.F = true;
            this.E.startNow();
            this.E.setAnimationListener(new Animation.AnimationListener() { // from class: com.amap.api.col.cr.1
                @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
                public void onAnimationStart() {
                }

                @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
                public void onAnimationEnd() {
                    if (cr.this.D != null) {
                        cr.this.F = true;
                        cr.this.D.startNow();
                        cr.this.b(z);
                    }
                }
            });
        } else if (this.D != null) {
            this.F = true;
            this.D.startNow();
            b(z);
        } else {
            b(z);
        }
    }

    private void j() {
        if (!this.x || this.r == null) {
            b(i());
        } else {
            c(false);
        }
        a(false);
    }

    private void k() {
        if (this.x || this.r == null) {
            b(h());
        } else {
            c(true);
        }
        a(true);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        if (this.e) {
            try {
                remove();
                l();
                if (this.n != null) {
                    this.n.clear();
                    this.n = null;
                }
                if (this.k != null) {
                    this.k.clear();
                    this.k = null;
                }
                this.j = null;
                this.w = 0;
            } catch (Throwable th) {
                gr.b(th, "PopupOverlay", "realDestroy");
                th.printStackTrace();
            }
        }
    }

    private void l() {
        Bitmap bitmap;
        if (!(this.r == null || (bitmap = this.r) == null)) {
            bitmap.recycle();
            this.r = null;
        }
        if (this.s != null && !this.s.isRecycled()) {
            this.s.recycle();
            this.s = null;
        }
        if (this.y != null && !this.y.isRecycled()) {
            this.y.recycle();
        }
        if (this.z != null && !this.z.isRecycled()) {
            this.z.recycle();
        }
        if (this.A != null && !this.A.isRecycled()) {
            this.A.recycle();
        }
        if (this.B != null && !this.B.isRecycled()) {
            this.B.recycle();
        }
    }

    @Override // com.amap.api.col.cg
    public boolean d() {
        return false;
    }

    @Override // com.amap.api.col.cg
    public void c() throws RemoteException {
    }

    public void a(FPoint fPoint) {
        this.j = fPoint;
    }

    public boolean g() {
        return this.F;
    }

    @Override // com.amap.api.col.cd
    public synchronized void a(ce ceVar) throws RemoteException {
        if (ceVar != null) {
            if (ceVar.isInfoWindowEnable()) {
                if (this.d != null && !this.d.getId().equals(ceVar.getId())) {
                    b();
                }
                if (this.c != null) {
                    this.d = ceVar;
                    ceVar.a(true);
                    setVisible(true);
                    a(a(this.c.getInfoWindow(new Marker(this.d))));
                    d(a(this.c.getInfoWindowClick(new Marker(this.d))));
                    e(a(this.c.getOverturnInfoWindow(new Marker(this.d))));
                    f(a(this.c.getOverturnInfoWindowClick(new Marker(this.d))));
                }
            }
        }
    }

    private Bitmap a(View view) {
        if (view == null) {
            return null;
        }
        if ((view instanceof RelativeLayout) && this.b != null) {
            LinearLayout linearLayout = new LinearLayout(this.b);
            linearLayout.setOrientation(1);
            linearLayout.addView(view);
            view = linearLayout;
        }
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(0);
        return dt.a(view);
    }

    private Rect m() {
        return new Rect(this.t.left, this.t.top, this.t.right, this.t.top + o());
    }

    private Rect n() {
        return new Rect(this.t.left, this.t.top, this.t.right, this.t.top + p());
    }

    private int o() {
        if (this.y == null || this.y.isRecycled()) {
            return 0;
        }
        return this.y.getHeight();
    }

    private int p() {
        if (this.A == null || this.A.isRecycled()) {
            return 0;
        }
        return this.A.getHeight();
    }

    @Override // com.amap.api.col.cd
    public void e() {
        try {
            if (this.d == null || !this.d.j()) {
                setVisible(false);
                return;
            }
            setVisible(true);
            Rect h = this.d.h();
            int c = this.d.c() + this.d.e();
            int f = this.d.f() + this.d.d() + 2;
            if (g()) {
                if (this.r != null) {
                    return;
                }
                if (this.y == null && this.A == null) {
                    return;
                }
            }
            IMarkerAction iMarkerAction = this.d.getIMarkerAction();
            if (iMarkerAction == null || iMarkerAction.isInfoWindowEnable()) {
                setVisible(true);
                if (iMarkerAction == null || !iMarkerAction.isInfoWindowAutoOverturn()) {
                    a(this.d.a());
                    a(c, f);
                    k();
                    return;
                }
                Rect m = m();
                Rect n = n();
                if (f()) {
                    n.offset(0, h.height() + m.height() + 2);
                } else {
                    m.offset(0, -(h.height() + m.height() + 2));
                }
                int a = this.a.a(iMarkerAction, m);
                int a2 = this.a.a(iMarkerAction, n);
                if (a <= 0 || (a2 != 0 && (a2 <= 0 || a >= a2))) {
                    k();
                } else {
                    f = this.d.f() + this.d.d() + 2 + h.height() + n.height();
                    j();
                }
                a(this.d.a());
                a(c, f);
                return;
            }
            setVisible(false);
        } catch (Throwable th) {
        }
    }

    @Override // com.amap.api.col.cd
    public boolean a(MotionEvent motionEvent) {
        if (!this.m || this.d == null || !dt.a(this.t, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        return true;
    }

    @Override // com.amap.api.col.cd
    public synchronized void b() {
        setVisible(false);
        l();
    }

    public void a(AMap.MultiPositionInfoWindowAdapter multiPositionInfoWindowAdapter) throws RemoteException {
        this.c = multiPositionInfoWindowAdapter;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }
}

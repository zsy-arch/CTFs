package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import c.a.a;
import c.a.a.C;
import c.a.f.C0051t;
import c.a.f.C0052u;
import c.a.f.E;
import c.a.f.ka;
import c.a.j;
import c.e.h.n;

/* loaded from: classes.dex */
public class AppCompatSeekBar extends SeekBar {

    /* renamed from: a  reason: collision with root package name */
    public final C0052u f118a;

    public AppCompatSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.seekBarStyle);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0052u uVar = this.f118a;
        Drawable drawable = uVar.f648e;
        if (drawable != null && drawable.isStateful() && drawable.setState(uVar.f647d.getDrawableState())) {
            uVar.f647d.invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f118a.f648e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f118a.a(canvas);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f118a = new C0052u(this);
        C0052u uVar = this.f118a;
        ka a2 = ka.a(uVar.f644b.getContext(), attributeSet, C0051t.f643a, i, 0);
        Drawable c2 = a2.c(0);
        if (c2 != null) {
            ProgressBar progressBar = uVar.f644b;
            if (c2 instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) c2;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i2 = 0; i2 < numberOfFrames; i2++) {
                    Drawable a3 = uVar.a(animationDrawable.getFrame(i2), true);
                    a3.setLevel(10000);
                    animationDrawable2.addFrame(a3, animationDrawable.getDuration(i2));
                }
                animationDrawable2.setLevel(10000);
                c2 = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(c2);
        }
        Drawable c3 = a2.c(1);
        if (c3 != null) {
            uVar.f644b.setProgressDrawable(uVar.a(c3, false));
        }
        a2.f605b.recycle();
        ka a4 = ka.a(uVar.f647d.getContext(), attributeSet, j.AppCompatSeekBar, i, 0);
        Drawable c4 = a4.c(j.AppCompatSeekBar_android_thumb);
        if (c4 != null) {
            uVar.f647d.setThumb(c4);
        }
        Drawable b2 = a4.b(j.AppCompatSeekBar_tickMark);
        Drawable drawable = uVar.f648e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        uVar.f648e = b2;
        if (b2 != null) {
            b2.setCallback(uVar.f647d);
            C.a(b2, n.g(uVar.f647d));
            if (b2.isStateful()) {
                b2.setState(uVar.f647d.getDrawableState());
            }
            uVar.a();
        }
        uVar.f647d.invalidate();
        if (a4.f(j.AppCompatSeekBar_tickMarkTintMode)) {
            uVar.g = E.a(a4.d(j.AppCompatSeekBar_tickMarkTintMode, -1), uVar.g);
            uVar.i = true;
        }
        if (a4.f(j.AppCompatSeekBar_tickMarkTint)) {
            uVar.f = a4.a(j.AppCompatSeekBar_tickMarkTint);
            uVar.h = true;
        }
        a4.f605b.recycle();
        uVar.a();
    }
}

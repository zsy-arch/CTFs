package c.a.f;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import c.a.a.C;
import c.a.j;
import c.e.h.n;

/* renamed from: c.a.f.u  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0052u extends C0051t {

    /* renamed from: d  reason: collision with root package name */
    public final SeekBar f647d;

    /* renamed from: e  reason: collision with root package name */
    public Drawable f648e;
    public ColorStateList f = null;
    public PorterDuff.Mode g = null;
    public boolean h = false;
    public boolean i = false;

    public C0052u(SeekBar seekBar) {
        super(seekBar);
        this.f647d = seekBar;
    }

    @Override // c.a.f.C0051t
    public void a(AttributeSet attributeSet, int i) {
        ka a2 = ka.a(this.f644b.getContext(), attributeSet, C0051t.f643a, i, 0);
        Drawable c2 = a2.c(0);
        if (c2 != null) {
            ProgressBar progressBar = this.f644b;
            if (c2 instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) c2;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i2 = 0; i2 < numberOfFrames; i2++) {
                    Drawable a3 = a(animationDrawable.getFrame(i2), true);
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
            this.f644b.setProgressDrawable(a(c3, false));
        }
        a2.f605b.recycle();
        ka a4 = ka.a(this.f647d.getContext(), attributeSet, j.AppCompatSeekBar, i, 0);
        Drawable c4 = a4.c(j.AppCompatSeekBar_android_thumb);
        if (c4 != null) {
            this.f647d.setThumb(c4);
        }
        Drawable b2 = a4.b(j.AppCompatSeekBar_tickMark);
        Drawable drawable = this.f648e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.f648e = b2;
        if (b2 != null) {
            b2.setCallback(this.f647d);
            C.a(b2, n.g(this.f647d));
            if (b2.isStateful()) {
                b2.setState(this.f647d.getDrawableState());
            }
            a();
        }
        this.f647d.invalidate();
        if (a4.f(j.AppCompatSeekBar_tickMarkTintMode)) {
            this.g = E.a(a4.d(j.AppCompatSeekBar_tickMarkTintMode, -1), this.g);
            this.i = true;
        }
        if (a4.f(j.AppCompatSeekBar_tickMarkTint)) {
            this.f = a4.a(j.AppCompatSeekBar_tickMarkTint);
            this.h = true;
        }
        a4.f605b.recycle();
        a();
    }

    public final void a() {
        if (this.f648e == null) {
            return;
        }
        if (this.h || this.i) {
            this.f648e = C.b(this.f648e.mutate());
            if (this.h) {
                Drawable drawable = this.f648e;
                ColorStateList colorStateList = this.f;
                int i = Build.VERSION.SDK_INT;
                drawable.setTintList(colorStateList);
            }
            if (this.i) {
                Drawable drawable2 = this.f648e;
                PorterDuff.Mode mode = this.g;
                int i2 = Build.VERSION.SDK_INT;
                drawable2.setTintMode(mode);
            }
            if (this.f648e.isStateful()) {
                this.f648e.setState(this.f647d.getDrawableState());
            }
        }
    }

    public void a(Canvas canvas) {
        if (this.f648e != null) {
            int max = this.f647d.getMax();
            int i = 1;
            if (max > 1) {
                int intrinsicWidth = this.f648e.getIntrinsicWidth();
                int intrinsicHeight = this.f648e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                if (intrinsicHeight >= 0) {
                    i = intrinsicHeight / 2;
                }
                this.f648e.setBounds(-i2, -i, i2, i);
                float width = ((this.f647d.getWidth() - this.f647d.getPaddingLeft()) - this.f647d.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(this.f647d.getPaddingLeft(), this.f647d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f648e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}

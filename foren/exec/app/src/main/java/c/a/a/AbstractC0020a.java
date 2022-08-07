package c.a.a;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import c.a.e.a;
import c.a.j;

/* renamed from: c.a.a.a  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0020a {

    /* renamed from: c.a.a.a$b */
    /* loaded from: classes.dex */
    public interface b {
        void onMenuVisibilityChanged(boolean z);
    }

    @Deprecated
    /* renamed from: c.a.a.a$c */
    /* loaded from: classes.dex */
    public static abstract class c {
        public abstract CharSequence a();

        public abstract View b();

        public abstract Drawable c();

        public abstract CharSequence d();

        public abstract void e();
    }

    public abstract a a(a.AbstractC0005a aVar);

    public abstract void a(Configuration configuration);

    public abstract void a(CharSequence charSequence);

    public abstract void a(boolean z);

    public abstract boolean a();

    public abstract boolean a(int i, KeyEvent keyEvent);

    public abstract int b();

    public abstract void b(boolean z);

    public abstract Context c();

    public abstract void c(boolean z);

    public void d() {
    }

    /* renamed from: c.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0002a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public int f341a;

        public C0002a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f341a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ActionBarLayout);
            this.f341a = obtainStyledAttributes.getInt(j.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public C0002a(int i, int i2) {
            super(i, i2);
            this.f341a = 0;
            this.f341a = 8388627;
        }

        public C0002a(C0002a aVar) {
            super((ViewGroup.MarginLayoutParams) aVar);
            this.f341a = 0;
            this.f341a = aVar.f341a;
        }

        public C0002a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f341a = 0;
        }
    }
}

package c.m.a.a;

import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
class c implements Drawable.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f1044a;

    public c(d dVar) {
        this.f1044a = dVar;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        this.f1044a.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.f1044a.scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.f1044a.unscheduleSelf(runnable);
    }
}

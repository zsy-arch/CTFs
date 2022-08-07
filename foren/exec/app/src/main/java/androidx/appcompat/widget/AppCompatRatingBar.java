package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import c.a.f.C0051t;

/* loaded from: classes.dex */
public class AppCompatRatingBar extends RatingBar {

    /* renamed from: a  reason: collision with root package name */
    public final C0051t f117a = new C0051t(this);

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AppCompatRatingBar(android.content.Context r2, android.util.AttributeSet r3) {
        /*
            r1 = this;
            int r0 = c.a.a.ratingBarStyle
            r1.<init>(r2, r3, r0)
            c.a.f.t r2 = new c.a.f.t
            r2.<init>(r1)
            r1.f117a = r2
            c.a.f.t r2 = r1.f117a
            r2.a(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatRatingBar.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap bitmap = this.f117a.f645c;
        if (bitmap != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f117a.a(attributeSet, i);
    }
}

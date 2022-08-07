package c.d.a;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<Matrix> f738a = new ThreadLocal<>();

    /* renamed from: b  reason: collision with root package name */
    public static final ThreadLocal<RectF> f739b = new ThreadLocal<>();

    public static void a(ViewGroup viewGroup, View view, Rect rect) {
        rect.set(0, 0, view.getWidth(), view.getHeight());
        Matrix matrix = f738a.get();
        if (matrix == null) {
            matrix = new Matrix();
            f738a.set(matrix);
        } else {
            matrix.reset();
        }
        a(viewGroup, view, matrix);
        RectF rectF = f739b.get();
        if (rectF == null) {
            rectF = new RectF();
            f739b.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    public static void a(ViewParent viewParent, View view, Matrix matrix) {
        ViewParent parent = view.getParent();
        if ((parent instanceof View) && parent != viewParent) {
            View view2 = (View) parent;
            a(viewParent, view2, matrix);
            matrix.preTranslate(-view2.getScrollX(), -view2.getScrollY());
        }
        matrix.preTranslate(view.getLeft(), view.getTop());
        if (!view.getMatrix().isIdentity()) {
            matrix.preConcat(view.getMatrix());
        }
    }
}

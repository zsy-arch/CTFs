package c.e.b.a;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public abstract class j {
    public abstract void a(int i);

    public abstract void a(Typeface typeface);

    public final void a(Typeface typeface, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new h(this, typeface));
    }

    public final void a(int i, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new i(this, i));
    }
}

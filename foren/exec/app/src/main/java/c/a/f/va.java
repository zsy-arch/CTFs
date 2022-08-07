package c.a.f;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import c.a.f;
import c.a.g;
import c.a.i;

/* loaded from: classes.dex */
public class va {

    /* renamed from: a  reason: collision with root package name */
    public final Context f654a;

    /* renamed from: b  reason: collision with root package name */
    public final View f655b;

    /* renamed from: c  reason: collision with root package name */
    public final TextView f656c;

    /* renamed from: d  reason: collision with root package name */
    public final WindowManager.LayoutParams f657d = new WindowManager.LayoutParams();

    /* renamed from: e  reason: collision with root package name */
    public final Rect f658e = new Rect();
    public final int[] f = new int[2];
    public final int[] g = new int[2];

    public va(Context context) {
        this.f654a = context;
        this.f655b = LayoutInflater.from(this.f654a).inflate(g.abc_tooltip, (ViewGroup) null);
        this.f656c = (TextView) this.f655b.findViewById(f.message);
        this.f657d.setTitle(va.class.getSimpleName());
        this.f657d.packageName = this.f654a.getPackageName();
        WindowManager.LayoutParams layoutParams = this.f657d;
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = i.Animation_AppCompat_Tooltip;
        layoutParams.flags = 24;
    }

    public void a() {
        if (this.f655b.getParent() != null) {
            ((WindowManager) this.f654a.getSystemService("window")).removeView(this.f655b);
        }
    }

    public boolean b() {
        return this.f655b.getParent() != null;
    }
}

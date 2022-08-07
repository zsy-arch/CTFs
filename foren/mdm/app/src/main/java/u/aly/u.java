package u.aly;

import android.content.Context;

/* compiled from: IDFATracker.java */
/* loaded from: classes2.dex */
public class u extends r {
    private static final String a = "idfa";
    private Context b;

    public u(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.r
    public String f() {
        String a2 = bi.a(this.b);
        return a2 == null ? "" : a2;
    }
}

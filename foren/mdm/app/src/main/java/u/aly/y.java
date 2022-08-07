package u.aly;

import android.content.Context;

/* compiled from: MacTracker.java */
/* loaded from: classes2.dex */
public class y extends r {
    private static final String a = "mac";
    private Context b;

    public y(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.r
    public String f() {
        try {
            return bl.q(this.b);
        } catch (Exception e) {
            return null;
        }
    }
}

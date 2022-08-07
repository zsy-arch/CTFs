package u.aly;

import android.os.Build;

/* compiled from: SerialTracker.java */
/* loaded from: classes2.dex */
public class z extends r {
    private static final String a = "serial";

    public z() {
        super(a);
    }

    @Override // u.aly.r
    public String f() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return null;
    }
}

package u.aly;

import android.content.Context;

/* compiled from: UMIdTracker.java */
/* loaded from: classes2.dex */
public class aa extends r {
    private static final String a = "idmd5";
    private Context b;

    public aa(Context context) {
        super("idmd5");
        this.b = context;
    }

    @Override // u.aly.r
    public String f() {
        return bl.d(this.b);
    }
}

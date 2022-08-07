package u.aly;

import android.content.Context;
import android.provider.Settings;

/* compiled from: AndroidIdTracker.java */
/* loaded from: classes2.dex */
public class s extends r {
    private static final String a = "android_id";
    private Context b;

    public s(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.r
    public String f() {
        try {
            return Settings.Secure.getString(this.b.getContentResolver(), a);
        } catch (Exception e) {
            return null;
        }
    }
}

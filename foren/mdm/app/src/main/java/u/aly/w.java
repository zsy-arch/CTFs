package u.aly;

import android.content.Context;
import android.telephony.TelephonyManager;

/* compiled from: ImeiTracker.java */
/* loaded from: classes2.dex */
public class w extends r {
    private static final String a = "imei";
    private Context b;

    public w(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.r
    public String f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        if (telephonyManager == null) {
        }
        try {
            if (bl.a(this.b, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getDeviceId();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}

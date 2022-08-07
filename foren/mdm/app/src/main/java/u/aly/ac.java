package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* compiled from: UUIDTracker.java */
/* loaded from: classes2.dex */
public class ac extends r {
    private static final String a = "uuid";
    private static final String e = "yosuid";
    private static final String f = "23346339";
    private Context b;
    private String c;
    private String d;

    public ac(Context context) {
        super(a);
        this.b = null;
        this.c = null;
        this.d = null;
        this.b = context;
        this.c = null;
        this.d = null;
    }

    @Override // u.aly.r
    public String f() {
        SharedPreferences a2;
        SharedPreferences.Editor edit;
        try {
            if (!(TextUtils.isEmpty(a("ro.yunos.version", "")) || this.b == null || (a2 = aq.a(this.b)) == null)) {
                String string = a2.getString(e, "");
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
                this.d = b(f);
                if (!(TextUtils.isEmpty(this.d) || this.b == null || a2 == null || (edit = a2.edit()) == null)) {
                    edit.putString(e, this.d).commit();
                }
                return this.d;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0139 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0143 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x013e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x00de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x00d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.ac.b(java.lang.String):java.lang.String");
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }
}

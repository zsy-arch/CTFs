package u.aly;

import android.text.TextUtils;
import java.util.List;

/* compiled from: UMCCAggregatedRestrictionManager.java */
/* loaded from: classes2.dex */
public class n {

    /* compiled from: UMCCAggregatedRestrictionManager.java */
    /* loaded from: classes2.dex */
    private static class a {
        private static final n a = new n();

        private a() {
        }
    }

    private n() {
    }

    public static n a() {
        return a.a;
    }

    public boolean a(String str) {
        if ("".equals(str)) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.getBytes().length < 160 && a(str, 48);
    }

    public boolean b(String str) {
        if (!TextUtils.isEmpty(str) && str.length() < 16 && a(str, 48)) {
            return true;
        }
        return false;
    }

    public boolean a(List<String> list) {
        if (list == null) {
            return false;
        }
        if (list.size() <= 1) {
            return true;
        }
        for (int i = 1; i < list.size(); i++) {
            if (!TextUtils.isEmpty(list.get(i)) && a(list.get(i), 48)) {
            }
            return false;
        }
        return true;
    }

    private boolean a(String str, int i) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) < i) {
                return false;
            }
        }
        return true;
    }

    public int b() {
        return 8;
    }

    public int c() {
        return 128;
    }

    public int d() {
        return 512;
    }

    public boolean b(List<String> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        int i = 0;
        for (String str : list) {
            i = str.getBytes().length + i;
        }
        if (i < 256) {
            return true;
        }
        return false;
    }
}

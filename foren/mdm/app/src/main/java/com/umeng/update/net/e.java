package com.umeng.update.net;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
import u.upd.b;

/* compiled from: NotificationRuntimeCache.java */
/* loaded from: classes2.dex */
public class e {
    private static final String a = "UMENG_RUNTIME_CACHE";
    private static final String b = e.class.getName();
    private final Context c;

    public e(Context context) {
        this.c = context;
    }

    public void a(int i) {
        try {
            SharedPreferences sharedPreferences = this.c.getSharedPreferences(a, 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            synchronized (sharedPreferences) {
                edit.putString("" + i, "");
                edit.commit();
            }
            b.a(b, "add nid [" + i + "] to runtime cache.");
        } catch (Exception e) {
        }
    }

    public List<Integer> a() {
        ArrayList arrayList = new ArrayList();
        try {
            SharedPreferences sharedPreferences = this.c.getSharedPreferences(a, 0);
            for (String str : sharedPreferences.getAll().keySet()) {
                try {
                    int parseInt = Integer.parseInt(str);
                    arrayList.add(Integer.valueOf(parseInt));
                    b.a(b, "get nid [" + parseInt + "]");
                } catch (NumberFormatException e) {
                }
            }
            sharedPreferences.edit().clear().commit();
        } catch (Exception e2) {
        }
        return arrayList;
    }

    public boolean b() {
        return this.c.getSharedPreferences(a, 0).getAll().size() > 0;
    }

    public void b(int i) {
        try {
            SharedPreferences sharedPreferences = this.c.getSharedPreferences(a, 0);
            if (sharedPreferences.contains("" + i)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove("" + i);
                edit.commit();
            }
            b.a(b, "remove nid [" + i + "] to runtime cache.");
        } catch (Exception e) {
        }
    }
}

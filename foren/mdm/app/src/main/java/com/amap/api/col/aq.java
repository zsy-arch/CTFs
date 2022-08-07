package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

/* compiled from: OfflineMapRemoveTask.java */
/* loaded from: classes.dex */
public class aq {
    private Context a;

    public aq(Context context) {
        this.a = context;
    }

    public void a(aj ajVar) {
        b(ajVar);
    }

    private boolean b(aj ajVar) {
        if (ajVar == null) {
            return false;
        }
        String pinyin = ajVar.getPinyin();
        boolean a = a(pinyin, this.a, "vmap/");
        if (pinyin.equals("quanguogaiyaotu")) {
            pinyin = "quanguo";
        }
        boolean z = a(pinyin, this.a, "map/") || a;
        if (z) {
            ajVar.i();
            return z;
        }
        ajVar.h();
        return false;
    }

    private boolean a(String str, Context context, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String a = dt.a(context);
        try {
            File file = new File(a + str2 + str + ".dat");
            if (!file.exists() || bh.b(file)) {
                try {
                    bh.b(a + str2);
                    bh.b(str, context);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            } else {
                bh.a("deleteDownload delete some thing wrong!");
                return false;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e4) {
            e4.printStackTrace();
            return false;
        }
    }
}

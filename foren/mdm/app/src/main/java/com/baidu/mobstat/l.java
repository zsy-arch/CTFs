package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l {
    static l a = new l();

    l() {
    }

    public synchronized void a(Context context) {
        String k = cu.k(context);
        if (!TextUtils.isEmpty(k)) {
            x.a.a(System.currentTimeMillis(), k);
        }
    }
}

package com.baidu.mobstat;

import android.content.Context;

/* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
/* loaded from: classes.dex */
final class ap extends an {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ap(String str, int i, int i2) {
        super(str, i, i2, null);
    }

    @Override // com.baidu.mobstat.an
    public void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        k a = as.a(context);
        bc bcVar = new bc();
        bcVar.a = false;
        bcVar.b = "M";
        bcVar.c = false;
        a.a(applicationContext, bcVar.a());
    }
}

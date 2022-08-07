package com.baidu.mobstat;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ao extends an {
    public ao(String str, int i, int i2) {
        super(str, i, i2, null);
    }

    @Override // com.baidu.mobstat.an
    public void a(Context context) {
        if (as.a(context).b(context)) {
            try {
                Intent intent = new Intent(context, Class.forName("com.baidu.bottom.service.BottomService"));
                intent.putExtra("SDK_PRODUCT_LY", "MS");
                context.startService(intent);
            } catch (Throwable th) {
                cr.b(th);
            }
        }
    }
}

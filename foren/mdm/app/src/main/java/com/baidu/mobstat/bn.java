package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bn {
    private static bn a = new bn();
    private boolean b = false;

    private bn() {
    }

    public static bn a() {
        return a;
    }

    public void a(Context context) {
        cr.a("openExceptonAnalysis");
        if (!this.b) {
            this.b = true;
            bg.a().a(context);
            NativeCrashHandler.init(context);
        }
    }
}

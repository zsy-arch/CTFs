package com.alipay.sdk.app;

import android.app.Activity;
import android.content.DialogInterface;

/* loaded from: classes.dex */
final class e implements DialogInterface.OnClickListener {
    final /* synthetic */ c a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(c cVar) {
        this.a = cVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Activity activity;
        this.a.a.cancel();
        this.a.b.d = false;
        h.a = h.a();
        activity = this.a.b.a;
        activity.finish();
    }
}

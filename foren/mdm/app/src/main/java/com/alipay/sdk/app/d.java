package com.alipay.sdk.app;

import android.content.DialogInterface;

/* loaded from: classes.dex */
final class d implements DialogInterface.OnClickListener {
    final /* synthetic */ c a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar) {
        this.a = cVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.d = true;
        this.a.a.proceed();
        dialogInterface.dismiss();
    }
}

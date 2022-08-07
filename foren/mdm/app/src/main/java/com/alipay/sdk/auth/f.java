package com.alipay.sdk.auth;

import android.content.DialogInterface;

/* loaded from: classes.dex */
final class f implements DialogInterface.OnClickListener {
    final /* synthetic */ e a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(e eVar) {
        this.a = eVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AuthActivity.this.g = true;
        this.a.a.proceed();
        dialogInterface.dismiss();
    }
}

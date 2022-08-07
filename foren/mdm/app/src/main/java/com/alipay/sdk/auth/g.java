package com.alipay.sdk.auth;

import android.content.DialogInterface;

/* loaded from: classes.dex */
final class g implements DialogInterface.OnClickListener {
    final /* synthetic */ e a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(e eVar) {
        this.a = eVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        String str;
        this.a.a.cancel();
        AuthActivity.this.g = false;
        StringBuilder sb = new StringBuilder();
        str = AuthActivity.this.d;
        h.a(AuthActivity.this, sb.append(str).append("?resultCode=150").toString());
        AuthActivity.this.finish();
    }
}

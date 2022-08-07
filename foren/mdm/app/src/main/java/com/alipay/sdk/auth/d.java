package com.alipay.sdk.auth;

/* loaded from: classes.dex */
final class d implements Runnable {
    final /* synthetic */ AuthActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(AuthActivity authActivity) {
        this.a = authActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AuthActivity.g(this.a);
    }
}

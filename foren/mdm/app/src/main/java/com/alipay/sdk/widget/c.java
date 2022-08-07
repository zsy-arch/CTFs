package com.alipay.sdk.widget;

import com.alipay.sdk.widget.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c implements Runnable {
    final /* synthetic */ a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a.AlertDialogC0010a aVar;
        a.AlertDialogC0010a aVar2;
        aVar = this.a.f;
        if (aVar != null) {
            try {
                aVar2 = this.a.f;
                aVar2.dismiss();
            } catch (Exception e) {
            }
        }
    }
}

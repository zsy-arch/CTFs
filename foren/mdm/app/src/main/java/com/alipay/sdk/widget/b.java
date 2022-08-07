package com.alipay.sdk.widget;

import com.alipay.sdk.widget.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b implements Runnable {
    final /* synthetic */ a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a.AlertDialogC0010a aVar;
        a.AlertDialogC0010a aVar2;
        a.AlertDialogC0010a aVar3;
        a.AlertDialogC0010a aVar4;
        boolean z;
        aVar = this.a.f;
        if (aVar == null) {
            this.a.f = new a.AlertDialogC0010a(this.a.g);
            aVar4 = this.a.f;
            z = this.a.e;
            aVar4.setCancelable(z);
        }
        try {
            aVar2 = this.a.f;
            if (!aVar2.isShowing()) {
                aVar3 = this.a.f;
                aVar3.show();
            }
        } catch (Exception e) {
        }
    }
}

package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class g extends IRemoteServiceCallback.Stub {
    final /* synthetic */ e a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(e eVar) {
        this.a = eVar;
    }

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public final boolean isHideLoadingScreen() throws RemoteException {
        return false;
    }

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public final void payEnd(boolean z, String str) throws RemoteException {
    }

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public final void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
        Activity activity;
        Activity activity2;
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            bundle.putInt("CallingPid", i);
            intent.putExtras(bundle);
        } catch (Exception e) {
        }
        intent.setClassName(str, str2);
        activity = this.a.a;
        if (activity != null) {
            activity2 = this.a.a;
            activity2.startActivity(intent);
        }
    }
}

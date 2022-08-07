package com.tencent.smtt.sdk;

import com.tencent.tbs.video.interfaces.IUserStateChangedListener;

/* loaded from: classes2.dex */
class aw implements IUserStateChangedListener {
    final /* synthetic */ av a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aw(av avVar) {
        this.a = avVar;
    }

    @Override // com.tencent.tbs.video.interfaces.IUserStateChangedListener
    public void onUserStateChanged() {
        this.a.a.c();
    }
}

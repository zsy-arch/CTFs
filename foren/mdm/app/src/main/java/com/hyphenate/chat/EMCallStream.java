package com.hyphenate.chat;

import com.hyphenate.chat.adapter.EMACallStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class EMCallStream extends EMBase<EMACallStream> {
    EMACallStream emaObject;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMCallStream(EMACallStream eMACallStream) {
        this.emaObject = eMACallStream;
    }

    public String getFullName() {
        return this.emaObject.getFullName();
    }

    public String getMemId() {
        return this.emaObject.getMemId();
    }

    public String getStreamId() {
        return this.emaObject.getStreamId();
    }

    public String getUserName() {
        return this.emaObject.getUserName();
    }
}

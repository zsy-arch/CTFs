package com.superrtc.call;

/* loaded from: classes2.dex */
public interface SdpObserver {
    void onCreateFailure(String str);

    void onCreateSuccess(SessionDescription sessionDescription);

    void onSetFailure(String str);

    void onSetSuccess();
}

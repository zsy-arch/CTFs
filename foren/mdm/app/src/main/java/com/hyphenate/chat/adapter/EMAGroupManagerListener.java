package com.hyphenate.chat.adapter;

import java.util.List;

/* loaded from: classes2.dex */
public abstract class EMAGroupManagerListener extends EMABase implements EMAGroupManagerListenerInterface {
    public EMAGroupManagerListener() {
        nativeInit();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native void nativeFinalize();

    native void nativeInit();

    public void onAutoAcceptInvitationFromGroup(EMAGroup eMAGroup, String str, String str2) {
    }

    public void onLeaveGroup(EMAGroup eMAGroup, int i) {
    }

    public void onReceiveAcceptionFromGroup(EMAGroup eMAGroup) {
    }

    public void onReceiveInviteAcceptionFromGroup(EMAGroup eMAGroup, String str) {
    }

    public void onReceiveInviteDeclineFromGroup(EMAGroup eMAGroup, String str, String str2) {
    }

    public void onReceiveInviteFromGroup(String str, String str2, String str3) {
    }

    public void onReceiveJoinGroupApplication(EMAGroup eMAGroup, String str, String str2) {
    }

    public void onReceiveRejectionFromGroup(String str, String str2) {
    }

    public void onUpdateMyGroupList(List<EMAGroup> list) {
    }
}

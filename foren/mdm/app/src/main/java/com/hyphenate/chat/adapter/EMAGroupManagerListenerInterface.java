package com.hyphenate.chat.adapter;

import java.util.List;

/* loaded from: classes2.dex */
public interface EMAGroupManagerListenerInterface {
    void onAutoAcceptInvitationFromGroup(EMAGroup eMAGroup, String str, String str2);

    void onLeaveGroup(EMAGroup eMAGroup, int i);

    void onReceiveAcceptionFromGroup(EMAGroup eMAGroup);

    void onReceiveInviteAcceptionFromGroup(EMAGroup eMAGroup, String str);

    void onReceiveInviteDeclineFromGroup(EMAGroup eMAGroup, String str, String str2);

    void onReceiveInviteFromGroup(String str, String str2, String str3);

    void onReceiveJoinGroupApplication(EMAGroup eMAGroup, String str, String str2);

    void onReceiveRejectionFromGroup(String str, String str2);

    void onUpdateMyGroupList(List<EMAGroup> list);
}

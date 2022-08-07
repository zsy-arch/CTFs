package com.hyphenate;

/* loaded from: classes2.dex */
public interface EMGroupChangeListener {
    void onAutoAcceptInvitationFromGroup(String str, String str2, String str3);

    void onGroupDestroyed(String str, String str2);

    void onInvitationAccepted(String str, String str2, String str3);

    void onInvitationDeclined(String str, String str2, String str3);

    void onInvitationReceived(String str, String str2, String str3, String str4);

    void onRequestToJoinAccepted(String str, String str2, String str3);

    void onRequestToJoinDeclined(String str, String str2, String str3, String str4);

    void onRequestToJoinReceived(String str, String str2, String str3, String str4);

    void onUserRemoved(String str, String str2);
}

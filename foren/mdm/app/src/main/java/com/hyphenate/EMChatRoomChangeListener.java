package com.hyphenate;

/* loaded from: classes2.dex */
public interface EMChatRoomChangeListener {
    void onChatRoomDestroyed(String str, String str2);

    void onMemberExited(String str, String str2, String str3);

    void onMemberJoined(String str, String str2);

    void onRemovedFromChatRoom(String str, String str2, String str3);
}

package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public interface EMAChatRoomManagerListenerInterface {
    void onLeaveChatRoom(EMAChatRoom eMAChatRoom, int i);

    void onMemberJoinedChatRoom(EMAChatRoom eMAChatRoom, String str);

    void onMemberLeftChatRoom(EMAChatRoom eMAChatRoom, String str);
}

package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public abstract class EMAChatRoomManagerListener extends EMABase implements EMAChatRoomManagerListenerInterface {
    public static final int BE_KICKED = 0;
    public static final int DESTROYED = 1;

    public EMAChatRoomManagerListener() {
        nativeInit();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native void nativeFinalize();

    native void nativeInit();

    public void onLeaveChatRoom(EMAChatRoom eMAChatRoom, int i) {
    }

    public void onMemberJoinedChatRoom(EMAChatRoom eMAChatRoom, String str) {
    }

    public void onMemberLeftChatRoom(EMAChatRoom eMAChatRoom, String str) {
    }
}

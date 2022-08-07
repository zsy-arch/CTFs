package com.hyphenate.chat.adapter;

import com.hyphenate.chat.EMPageResult;
import java.util.List;

/* loaded from: classes2.dex */
public class EMAChatRoomManager extends EMABase {
    public void addListener(EMAChatRoomManagerListener eMAChatRoomManagerListener) {
        nativeAddListener(eMAChatRoomManagerListener);
    }

    public void clearListeners() {
        nativeClearListeners();
    }

    public List<EMAChatRoom> fetchAllChatrooms(EMAError eMAError) {
        return nativeFetchAllChatrooms(eMAError);
    }

    public EMAChatRoom fetchChatroomSpecification(String str, EMAError eMAError, boolean z) {
        return nativeFetchChatroomSpecification(str, eMAError, z);
    }

    public EMPageResult<EMAChatRoom> fetchChatroomsWithPage(int i, int i2, EMAError eMAError) {
        return nativefetchChatroomsWithPage(i, i2, eMAError);
    }

    public EMAChatRoom getChatroom(String str) {
        return nativeGetChatroom(str);
    }

    public EMAChatRoom joinChatRoom(String str, EMAError eMAError) {
        return nativeJoinChatRoom(str, eMAError);
    }

    public EMAChatRoom leaveChatRoom(String str, EMAError eMAError) {
        return nativeLeaveChatRoom(str, eMAError);
    }

    native void nativeAddListener(EMAChatRoomManagerListener eMAChatRoomManagerListener);

    native void nativeClearListeners();

    native List<EMAChatRoom> nativeFetchAllChatrooms(EMAError eMAError);

    native EMAChatRoom nativeFetchChatroomSpecification(String str, EMAError eMAError, boolean z);

    native EMAChatRoom nativeGetChatroom(String str);

    native EMAChatRoom nativeJoinChatRoom(String str, EMAError eMAError);

    native EMAChatRoom nativeLeaveChatRoom(String str, EMAError eMAError);

    native void nativeRemoveListener(EMAChatRoomManagerListener eMAChatRoomManagerListener);

    native EMPageResult<EMAChatRoom> nativefetchChatroomsWithPage(int i, int i2, EMAError eMAError);

    public void removeListener(EMAChatRoomManagerListener eMAChatRoomManagerListener) {
        nativeRemoveListener(eMAChatRoomManagerListener);
    }
}

package com.hyphenate.chat.adapter;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class EMAChatRoom extends EMABase {
    public static final int EMChatroomLeaveReason_BE_KICKED = 0;
    public static final int EMChatroomLeaveReason_DESTROYED = 1;

    /* loaded from: classes2.dex */
    public enum EMLeaveReason {
        BE_KICKED,
        DESTROYED
    }

    public EMAChatRoom() {
        nativeInit();
    }

    public EMAChatRoom(EMAChatRoom eMAChatRoom) {
        nativeInit(eMAChatRoom);
    }

    public EMAChatRoom(String str) {
        nativeInit(str);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public int getAffiliationsCount() {
        return nativegetAffiliationsCount();
    }

    public String getDescription() {
        return nativeChatroomDescription();
    }

    public String getId() {
        return nativeChatroomId();
    }

    public int getMaxUsers() {
        return nativegetMaxUsers();
    }

    public List<String> getMemberList() {
        return nativegetMemberList();
    }

    public List<String> getMembers() {
        return new ArrayList();
    }

    public String getName() {
        return nativeChatroomSubject();
    }

    public String getOwner() {
        return nativegetOwner();
    }

    native String nativeChatroomDescription();

    native String nativeChatroomId();

    native String nativeChatroomSubject();

    native void nativeFinalize();

    native void nativeInit();

    native void nativeInit(EMAChatRoom eMAChatRoom);

    native void nativeInit(String str);

    native int nativegetAffiliationsCount();

    native int nativegetMaxUsers();

    native List<String> nativegetMemberList();

    native String nativegetOwner();

    native void nativesetAffiliationsCount(int i);

    native void nativesetDescription(String str);

    native void nativesetMaxUsers(int i);

    native void nativesetName(String str);

    native void nativesetOwner(String str);

    public void setAffiliationsCount(int i) {
        nativesetAffiliationsCount(i);
    }

    public void setDescription(String str) {
        nativesetDescription(str);
    }

    public void setMaxUsers(int i) {
        nativesetMaxUsers(i);
    }

    public void setName(String str) {
        nativesetName(str);
    }

    public void setOwner(String str) {
        nativesetOwner(str);
    }
}

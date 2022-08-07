package com.hyphenate.chat.adapter;

import com.hyphenate.chat.adapter.message.EMAMessage;
import java.util.List;

/* loaded from: classes2.dex */
public class EMAConversation extends EMABase {
    public static final int EMAConversationType_CHAT = 0;
    public static final int EMAConversationType_CHATROOM = 2;
    public static final int EMAConversationType_DISCUSSIONGROUP = 3;
    public static final int EMAConversationType_GROUPCHAT = 1;
    public static final int EMAConversationType_HELPDESK = 4;

    /* loaded from: classes2.dex */
    public enum EMAConversationType {
        CHAT,
        GROUPCHAT,
        CHATROOM,
        DISCUSSIONGROUP,
        HELPDESK
    }

    /* loaded from: classes2.dex */
    public enum EMASearchDirection {
        UP,
        DOWN
    }

    public EMAConversation() {
        nativeInit();
    }

    public EMAConversation(EMAConversation eMAConversation) {
        nativeInit(eMAConversation);
    }

    public EMAConversationType _getType() {
        int nativeConversationType = nativeConversationType();
        return nativeConversationType == 0 ? EMAConversationType.CHAT : nativeConversationType == 1 ? EMAConversationType.GROUPCHAT : nativeConversationType == 2 ? EMAConversationType.CHATROOM : nativeConversationType == 3 ? EMAConversationType.DISCUSSIONGROUP : nativeConversationType == 4 ? EMAConversationType.HELPDESK : EMAConversationType.CHAT;
    }

    public boolean _removeMessage(String str) {
        return nativeRemoveMessage(str);
    }

    public boolean _setExtField(String str) {
        return nativeSetExtField(str);
    }

    public boolean appendMessage(EMAMessage eMAMessage) {
        return nativeInsertMessage(eMAMessage);
    }

    public boolean clearAllMessages() {
        return nativeClearAllMessages();
    }

    public String conversationId() {
        return nativeConversationId();
    }

    public String extField() {
        return nativeExtField();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public boolean insertMessage(EMAMessage eMAMessage) {
        return nativeInsertMessage(eMAMessage);
    }

    public EMAMessage latestMessage() {
        return nativeLatestMessage();
    }

    public EMAMessage loadMessage(String str) {
        return nativeLoadMessage(str);
    }

    public List<EMAMessage> loadMoreMessages(String str, int i, EMASearchDirection eMASearchDirection) {
        return nativeLoadMoreMessages(str, i, eMASearchDirection.ordinal());
    }

    public boolean markAllMessagesAsRead(boolean z) {
        return nativeMarkAllMessagesAsRead(z);
    }

    public boolean markMessageAsRead(String str, boolean z) {
        return nativeMarkMessageAsRead(str, z);
    }

    public int messagesCount() {
        return nativeMessagesCount();
    }

    native boolean nativeAppendMessage(EMAMessage eMAMessage);

    native boolean nativeClearAllMessages();

    native void nativeClearCachedMessages();

    native String nativeConversationId();

    native int nativeConversationType();

    native String nativeExtField();

    native void nativeFinalize();

    native void nativeInit();

    native void nativeInit(EMAConversation eMAConversation);

    native boolean nativeInsertMessage(EMAMessage eMAMessage);

    native EMAMessage nativeLatestMessage();

    native EMAMessage nativeLoadMessage(String str);

    native List<EMAMessage> nativeLoadMoreMessages(String str, int i, int i2);

    native boolean nativeMarkAllMessagesAsRead(boolean z);

    native boolean nativeMarkMessageAsRead(String str, boolean z);

    native int nativeMessagesCount();

    native boolean nativeRemoveMessage(EMAMessage eMAMessage);

    native boolean nativeRemoveMessage(String str);

    native List<EMAMessage> nativeSearchMessages(int i, long j, int i2, String str, int i3);

    native List<EMAMessage> nativeSearchMessages(long j, int i, int i2);

    native List<EMAMessage> nativeSearchMessages(long j, long j2, int i);

    native List<EMAMessage> nativeSearchMessages(String str, long j, int i, String str2, int i2);

    native boolean nativeSetExtField(String str);

    native int nativeUnreadMessagesCount();

    native boolean nativeUpdateMessage(EMAMessage eMAMessage);

    public boolean removeMessage(EMAMessage eMAMessage) {
        return nativeRemoveMessage(eMAMessage);
    }

    public List<EMAMessage> searchMessages(int i, long j, int i2, String str, EMASearchDirection eMASearchDirection) {
        return nativeSearchMessages(i, j, i2, str, eMASearchDirection.ordinal());
    }

    public List<EMAMessage> searchMessages(long j, int i, EMASearchDirection eMASearchDirection) {
        return nativeSearchMessages(j, i, eMASearchDirection.ordinal());
    }

    public List<EMAMessage> searchMessages(long j, long j2, int i) {
        return nativeSearchMessages(j, j2, i);
    }

    public List<EMAMessage> searchMessages(String str, long j, int i, String str2, EMASearchDirection eMASearchDirection) {
        return nativeSearchMessages(str, j, i, str2, eMASearchDirection.ordinal());
    }

    public int unreadMessagesCount() {
        return nativeUnreadMessagesCount();
    }

    public boolean updateMessage(EMAMessage eMAMessage) {
        return nativeUpdateMessage(eMAMessage);
    }
}

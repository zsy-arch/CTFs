package com.hyphenate.chat.adapter;

import com.hyphenate.chat.adapter.EMAConversation;
import com.hyphenate.chat.adapter.message.EMAMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class EMAChatManager extends EMABase {
    private Set<EMAChatManagerListener> listeners = new HashSet();

    public EMAChatManager() {
    }

    protected EMAChatManager(EMAChatManager eMAChatManager) {
        nativeInit(eMAChatManager);
    }

    public void addListener(EMAChatManagerListener eMAChatManagerListener) {
        this.listeners.add(eMAChatManagerListener);
        nativeAddListener(eMAChatManagerListener);
    }

    public void clearListeners() {
        this.listeners.clear();
        nativeClearListeners();
    }

    public EMAConversation conversationWithType(String str, EMAConversation.EMAConversationType eMAConversationType, boolean z) {
        return nativeConversationWithType(str, eMAConversationType.ordinal(), z);
    }

    public void downloadMessageAttachments(EMAMessage eMAMessage) {
        nativeDownloadMessageAttachments(eMAMessage);
    }

    public void downloadMessageThumbnail(EMAMessage eMAMessage) {
        nativeDownloadMessageThumbnail(eMAMessage);
    }

    public List<EMAConversation> getConversations() {
        return nativeGetConversations();
    }

    public EMAEncryptProviderInterface getEncryptProvider(boolean z) {
        return nativeGetEncryptProvider(z);
    }

    public EMAMessage getMessage(String str) {
        return nativeGetMessage(str);
    }

    public List<EMAConversation> loadAllConversationsFromDB() {
        return nativeLoadAllConversationsFromDB();
    }

    native void nativeAddListener(EMAChatManagerListener eMAChatManagerListener);

    native void nativeClearListeners();

    native EMAConversation nativeConversationWithType(String str, int i, boolean z);

    native void nativeDownloadMessageAttachments(EMAMessage eMAMessage);

    native void nativeDownloadMessageThumbnail(EMAMessage eMAMessage);

    native List<EMAConversation> nativeGetConversations();

    native EMAEncryptProviderInterface nativeGetEncryptProvider(boolean z);

    native EMAMessage nativeGetMessage(String str);

    native void nativeInit(EMAChatManager eMAChatManager);

    native List<EMAConversation> nativeLoadAllConversationsFromDB();

    native void nativeRemoveConversation(String str, boolean z);

    native void nativeRemoveListener(EMAChatManagerListener eMAChatManagerListener);

    native void nativeResendMessage(EMAMessage eMAMessage);

    native void nativeSendMessage(EMAMessage eMAMessage);

    native void nativeSendReadAckForMessage(EMAMessage eMAMessage);

    native void nativeSetEncryptProvider(EMAEncryptProviderInterface eMAEncryptProviderInterface);

    native boolean nativeUpdateParticipant(String str, String str2);

    native void nativeUploadLog();

    public void removeConversation(String str, boolean z) {
        nativeRemoveConversation(str, z);
    }

    public void removeListener(EMAChatManagerListener eMAChatManagerListener) {
        this.listeners.remove(eMAChatManagerListener);
        nativeRemoveListener(eMAChatManagerListener);
    }

    public void resendMessage(EMAMessage eMAMessage) {
        nativeResendMessage(eMAMessage);
    }

    public void sendMessage(EMAMessage eMAMessage) {
        nativeSendMessage(eMAMessage);
    }

    public void sendReadAckForMessage(EMAMessage eMAMessage) {
        nativeSendReadAckForMessage(eMAMessage);
    }

    public void setEncryptProvider(EMAEncryptProviderInterface eMAEncryptProviderInterface) {
        nativeSetEncryptProvider(eMAEncryptProviderInterface);
    }

    public boolean updateParticipant(String str, String str2) {
        return nativeUpdateParticipant(str, str2);
    }

    public void uploadLog() {
        nativeUploadLog();
    }
}

package com.hyphenate.chat;

import android.graphics.BitmapFactory;
import android.util.Log;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConversationListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.a.a;
import com.hyphenate.chat.adapter.EMAChatManager;
import com.hyphenate.chat.adapter.EMAChatManagerListener;
import com.hyphenate.chat.adapter.EMAConversation;
import com.hyphenate.chat.adapter.EMAError;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.cloud.EMCloudOperationCallback;
import com.hyphenate.cloud.EMHttpClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.ImageUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class EMChatManager {
    private static final String INTERNAL_ACTION_PREFIX = "em_";
    private static final String TAG = "EMChatManager";
    EMAChatManager emaObject;
    EMClient mClient;
    Map<String, EMConversation.MessageCache> caches = new Hashtable();
    private List<EMMessageListener> messageListeners = Collections.synchronizedList(new ArrayList());
    private List<EMConversationListener> conversationListeners = Collections.synchronizedList(new ArrayList());
    EMAChatManagerListener chatManagerListenerImpl = new EMAChatManagerListener() { // from class: com.hyphenate.chat.EMChatManager.1
        @Override // com.hyphenate.chat.adapter.EMAChatManagerListener, com.hyphenate.chat.adapter.EMAChatManagerListenerInterface
        public void onMessageAttachmentsStatusChanged(EMAMessage eMAMessage, EMAError eMAError) {
            synchronized (EMChatManager.this.messageListeners) {
                EMMessage eMMessage = new EMMessage(eMAMessage);
                for (EMMessageListener eMMessageListener : EMChatManager.this.messageListeners) {
                    eMMessageListener.onMessageChanged(eMMessage, null);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAChatManagerListener, com.hyphenate.chat.adapter.EMAChatManagerListenerInterface
        public void onMessageStatusChanged(final EMAMessage eMAMessage, EMAError eMAError) {
            EMChatManager.this.mClient.executeOnMainQueue(new Runnable() { // from class: com.hyphenate.chat.EMChatManager.1.3
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (EMChatManager.this.messageListeners) {
                        EMMessage eMMessage = new EMMessage(eMAMessage);
                        for (EMMessageListener eMMessageListener : EMChatManager.this.messageListeners) {
                            eMMessageListener.onMessageChanged(eMMessage, null);
                        }
                    }
                }
            });
        }

        @Override // com.hyphenate.chat.adapter.EMAChatManagerListener, com.hyphenate.chat.adapter.EMAChatManagerListenerInterface
        public void onReceiveCmdMessages(final List<EMAMessage> list) {
            EMChatManager.this.mClient.executeOnMainQueue(new Runnable() { // from class: com.hyphenate.chat.EMChatManager.1.2
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    for (EMAMessage eMAMessage : list) {
                        EMMessage eMMessage = new EMMessage(eMAMessage);
                        String action = ((EMCmdMessageBody) eMMessage.getBody()).action();
                        if (EMChatManager.this.isAdvanceDebugMessage(action)) {
                            a.a().a(eMMessage, a.EnumC0042a.valueOf(action));
                        } else {
                            arrayList.add(eMMessage);
                        }
                    }
                    synchronized (EMChatManager.this.messageListeners) {
                        for (EMMessageListener eMMessageListener : EMChatManager.this.messageListeners) {
                            eMMessageListener.onCmdMessageReceived(arrayList);
                        }
                    }
                }
            });
        }

        @Override // com.hyphenate.chat.adapter.EMAChatManagerListener, com.hyphenate.chat.adapter.EMAChatManagerListenerInterface
        public void onReceiveHasDeliveredAcks(final List<EMAMessage> list) {
            EMChatManager.this.mClient.executeOnMainQueue(new Runnable() { // from class: com.hyphenate.chat.EMChatManager.1.5
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    for (EMAMessage eMAMessage : list) {
                        arrayList.add(new EMMessage(eMAMessage));
                    }
                    synchronized (EMChatManager.this.messageListeners) {
                        for (EMMessageListener eMMessageListener : EMChatManager.this.messageListeners) {
                            eMMessageListener.onMessageDelivered(arrayList);
                        }
                    }
                }
            });
        }

        @Override // com.hyphenate.chat.adapter.EMAChatManagerListener, com.hyphenate.chat.adapter.EMAChatManagerListenerInterface
        public void onReceiveHasReadAcks(final List<EMAMessage> list) {
            EMChatManager.this.mClient.executeOnMainQueue(new Runnable() { // from class: com.hyphenate.chat.EMChatManager.1.4
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    for (EMAMessage eMAMessage : list) {
                        arrayList.add(new EMMessage(eMAMessage));
                    }
                    synchronized (EMChatManager.this.messageListeners) {
                        for (EMMessageListener eMMessageListener : EMChatManager.this.messageListeners) {
                            eMMessageListener.onMessageRead(arrayList);
                        }
                    }
                }
            });
        }

        @Override // com.hyphenate.chat.adapter.EMAChatManagerListener, com.hyphenate.chat.adapter.EMAChatManagerListenerInterface
        public void onReceiveMessages(final List<EMAMessage> list) {
            EMChatManager.this.mClient.executeOnMainQueue(new Runnable() { // from class: com.hyphenate.chat.EMChatManager.1.1
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList<EMMessage> arrayList = new ArrayList();
                    for (EMAMessage eMAMessage : list) {
                        arrayList.add(new EMMessage(eMAMessage));
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (EMMessage eMMessage : arrayList) {
                        EMConversation conversation = EMChatManager.this.getConversation(eMMessage.conversationId(), EMConversation.msgType2ConversationType(eMMessage.getFrom(), eMMessage.getChatType()), false);
                        if (conversation != null) {
                            if (eMMessage.getType() != EMMessage.Type.CMD) {
                                conversation.getCache().addMessage(eMMessage);
                            }
                            arrayList2.add(eMMessage);
                        }
                    }
                    if (arrayList2.size() > 0) {
                        EMLog.d(EMChatManager.TAG, "onMessageReceived");
                        synchronized (EMChatManager.this.messageListeners) {
                            for (EMMessageListener eMMessageListener : EMChatManager.this.messageListeners.subList(0, EMChatManager.this.messageListeners.size())) {
                                eMMessageListener.onMessageReceived(arrayList2);
                            }
                        }
                    }
                }
            });
        }

        @Override // com.hyphenate.chat.adapter.EMAChatManagerListener, com.hyphenate.chat.adapter.EMAChatManagerListenerInterface
        public void onUpdateConversationList(List<EMAConversation> list) {
            EMChatManager.this.mClient.executeOnMainQueue(new Runnable() { // from class: com.hyphenate.chat.EMChatManager.1.6
                @Override // java.lang.Runnable
                public void run() {
                    EMLog.d(EMChatManager.TAG, "onUpdateConversationList");
                    synchronized (EMChatManager.this.conversationListeners) {
                        for (EMConversationListener eMConversationListener : EMChatManager.this.conversationListeners) {
                            eMConversationListener.onCoversationUpdate();
                        }
                    }
                }
            });
        }
    };

    protected EMChatManager() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EMChatManager(EMClient eMClient, EMAChatManager eMAChatManager) {
        this.mClient = eMClient;
        this.emaObject = eMAChatManager;
        this.emaObject.addListener(this.chatManagerListenerImpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAdvanceDebugMessage(String str) {
        if (str.startsWith(INTERNAL_ACTION_PREFIX)) {
            try {
                a.EnumC0042a.valueOf(str);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMessageSendCallback(final EMMessage eMMessage, final EMConversation eMConversation, final String str, final String str2) {
        if (eMMessage != null) {
            eMMessage.setInnerCallback(new EMCallBack() { // from class: com.hyphenate.chat.EMChatManager.3
                @Override // com.hyphenate.EMCallBack
                public void onError(int i, String str3) {
                }

                @Override // com.hyphenate.EMCallBack
                public void onProgress(int i, String str3) {
                }

                @Override // com.hyphenate.EMCallBack
                public void onSuccess() {
                    if (eMConversation != null) {
                        eMConversation.getCache().removeMessage(str);
                        eMConversation.getCache().addMessage(eMMessage);
                    }
                    if (str2 != null && (eMMessage.getBody() instanceof EMImageMessageBody)) {
                        String localUrl = ((EMImageMessageBody) eMMessage.getBody()).getLocalUrl();
                        EMLog.d(EMChatManager.TAG, "origin: + " + str2 + ", scale:" + localUrl);
                        if (localUrl != null && !localUrl.equals(str2)) {
                            File file = new File(localUrl);
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                        ((EMImageMessageBody) eMMessage.getBody()).setLocalUrl(str2);
                        EMChatManager.this.updateMessage(eMMessage);
                    }
                }
            });
        }
    }

    public void ackMessageRead(String str, String str2) throws HyphenateException {
        if (!EMClient.getInstance().getChatConfigPrivate().b().getRequireAck()) {
            EMLog.d(TAG, "chat option reqire ack set to false. skip send out ask msg read");
            return;
        }
        EMAMessage message = this.emaObject.getMessage(str2);
        if (message != null) {
            this.emaObject.sendReadAckForMessage(message);
        }
    }

    public void addConversationListener(EMConversationListener eMConversationListener) {
        if (!this.conversationListeners.contains(eMConversationListener)) {
            this.conversationListeners.add(eMConversationListener);
        }
    }

    public void addMessageListener(EMMessageListener eMMessageListener) {
        if (eMMessageListener != null && !this.messageListeners.contains(eMMessageListener)) {
            this.messageListeners.add(eMMessageListener);
        }
    }

    public boolean deleteConversation(String str, boolean z) {
        EMConversation conversation = getConversation(str);
        if (conversation == null) {
            return false;
        }
        if (!z) {
            conversation.clear();
        } else {
            conversation.clearAllMessages();
        }
        this.emaObject.removeConversation(str, z);
        return true;
    }

    public void downloadAttachment(EMMessage eMMessage) {
        if (eMMessage != null) {
            this.emaObject.downloadMessageAttachments(eMMessage.emaObject);
        }
    }

    @Deprecated
    public void downloadFile(String str, String str2, Map<String, String> map, final EMCallBack eMCallBack) {
        EMHttpClient.getInstance().downloadFile(str, str2, map, new EMCloudOperationCallback() { // from class: com.hyphenate.chat.EMChatManager.4
            @Override // com.hyphenate.cloud.CloudOperationCallback
            public void onError(String str3) {
                if (eMCallBack != null) {
                    eMCallBack.onError(1, str3);
                }
            }

            @Override // com.hyphenate.cloud.CloudOperationCallback
            public void onProgress(int i) {
                if (eMCallBack != null) {
                    eMCallBack.onProgress(i, null);
                }
            }

            @Override // com.hyphenate.cloud.CloudOperationCallback
            public void onSuccess(String str3) {
                if (eMCallBack != null) {
                    eMCallBack.onSuccess();
                }
            }
        });
    }

    public void downloadThumbnail(EMMessage eMMessage) {
        this.emaObject.downloadMessageThumbnail(eMMessage.emaObject);
    }

    public Map<String, EMConversation> getAllConversations() {
        List<EMAConversation> conversations = this.emaObject.getConversations();
        Hashtable hashtable = new Hashtable();
        for (EMAConversation eMAConversation : conversations) {
            hashtable.put(eMAConversation.conversationId(), new EMConversation(eMAConversation));
        }
        return hashtable;
    }

    public EMConversation getConversation(String str) {
        EMAConversation conversationWithType = this.emaObject.conversationWithType(str, EMAConversation.EMAConversationType.CHAT, false);
        if (conversationWithType == null) {
            conversationWithType = this.emaObject.conversationWithType(str, EMAConversation.EMAConversationType.GROUPCHAT, false);
        }
        if (conversationWithType == null) {
            conversationWithType = this.emaObject.conversationWithType(str, EMAConversation.EMAConversationType.CHATROOM, false);
        }
        if (conversationWithType == null) {
            conversationWithType = this.emaObject.conversationWithType(str, EMAConversation.EMAConversationType.DISCUSSIONGROUP, false);
        }
        EMAConversation conversationWithType2 = conversationWithType == null ? this.emaObject.conversationWithType(str, EMAConversation.EMAConversationType.HELPDESK, false) : conversationWithType;
        if (conversationWithType2 == null) {
            return null;
        }
        return new EMConversation(conversationWithType2);
    }

    public EMConversation getConversation(String str, EMConversation.EMConversationType eMConversationType) {
        return getConversation(str, eMConversationType, false);
    }

    public EMConversation getConversation(String str, EMConversation.EMConversationType eMConversationType, boolean z) {
        EMAConversation.EMAConversationType eMAConversationType = EMAConversation.EMAConversationType.CHAT;
        if (eMConversationType == EMConversation.EMConversationType.Chat) {
            eMAConversationType = EMAConversation.EMAConversationType.CHAT;
        } else if (eMConversationType == EMConversation.EMConversationType.GroupChat) {
            eMAConversationType = EMAConversation.EMAConversationType.GROUPCHAT;
        } else if (eMConversationType == EMConversation.EMConversationType.ChatRoom) {
            eMAConversationType = EMAConversation.EMAConversationType.CHATROOM;
        } else if (eMConversationType == EMConversation.EMConversationType.DiscussionGroup) {
            eMAConversationType = EMAConversation.EMAConversationType.DISCUSSIONGROUP;
        } else if (eMConversationType == EMConversation.EMConversationType.HelpDesk) {
            eMAConversationType = EMAConversation.EMAConversationType.HELPDESK;
        }
        EMAConversation conversationWithType = this.emaObject.conversationWithType(str, eMAConversationType, z);
        if (conversationWithType == null) {
            return null;
        }
        Log.d(TAG, "convID:" + conversationWithType.conversationId());
        return new EMConversation(conversationWithType);
    }

    public List<EMConversation> getConversationsByType(EMConversation.EMConversationType eMConversationType) {
        List<EMAConversation> conversations = this.emaObject.getConversations();
        ArrayList arrayList = new ArrayList();
        for (EMAConversation eMAConversation : conversations) {
            if (eMConversationType.ordinal() == eMAConversation._getType().ordinal()) {
                arrayList.add(new EMConversation(eMAConversation));
            }
        }
        return arrayList;
    }

    public EMMessage getMessage(String str) {
        synchronized (this.caches) {
            for (EMConversation.MessageCache messageCache : this.caches.values()) {
                EMMessage message = messageCache.getMessage(str);
                if (message != null) {
                    return message;
                }
            }
            EMAMessage message2 = this.emaObject.getMessage(str);
            if (message2 == null) {
                return null;
            }
            EMMessage eMMessage = new EMMessage(message2);
            EMConversation conversation = getConversation(eMMessage.conversationId(), EMConversation.msgType2ConversationType(eMMessage.getFrom(), eMMessage.getChatType()), false);
            if (conversation == null) {
                return eMMessage;
            }
            conversation.getCache().addMessage(eMMessage);
            return eMMessage;
        }
    }

    public int getUnreadMessageCount() {
        int i = 0;
        for (EMAConversation eMAConversation : this.emaObject.getConversations()) {
            i = eMAConversation._getType() != EMAConversation.EMAConversationType.CHATROOM ? eMAConversation.unreadMessagesCount() + i : i;
        }
        return i;
    }

    @Deprecated
    public int getUnreadMsgsCount() {
        return getUnreadMessageCount();
    }

    public synchronized void importMessages(List<EMMessage> list) {
        ArrayList arrayList = new ArrayList();
        for (EMMessage eMMessage : list) {
            arrayList.add(eMMessage.emaObject);
        }
        EMClient.getInstance().getChatConfigPrivate().c(arrayList);
    }

    public void loadAllConversations() {
        this.emaObject.loadAllConversationsFromDB();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void loadAllConversationsFromDB() {
        this.emaObject.loadAllConversationsFromDB();
    }

    public void markAllConversationsAsRead() {
        for (EMAConversation eMAConversation : this.emaObject.loadAllConversationsFromDB()) {
            eMAConversation.markAllMessagesAsRead(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onLogout() {
        this.caches.clear();
    }

    public void removeConversationListener(EMConversationListener eMConversationListener) {
        if (eMConversationListener != null) {
            this.conversationListeners.remove(eMConversationListener);
        }
    }

    public void removeMessageListener(EMMessageListener eMMessageListener) {
        if (eMMessageListener != null) {
            this.messageListeners.remove(eMMessageListener);
        }
    }

    public void saveMessage(EMMessage eMMessage) {
        EMMessage.ChatType chatType = eMMessage.getChatType();
        EMConversation.EMConversationType eMConversationType = EMConversation.EMConversationType.Chat;
        switch (chatType) {
            case Chat:
                eMConversationType = EMConversation.EMConversationType.Chat;
                break;
            case GroupChat:
                eMConversationType = EMConversation.EMConversationType.GroupChat;
                break;
            case ChatRoom:
                eMConversationType = EMConversation.EMConversationType.ChatRoom;
                break;
        }
        String to = eMMessage.getTo();
        if (eMConversationType == EMConversation.EMConversationType.Chat && eMMessage.direct() == EMMessage.Direct.RECEIVE) {
            to = eMMessage.getFrom();
        }
        if (eMMessage.getType() != EMMessage.Type.CMD) {
            getConversation(to, eMConversationType, true).insertMessage(eMMessage);
        }
    }

    public void sendMessage(final EMMessage eMMessage) {
        boolean z = true;
        eMMessage.makeCallbackStrong();
        final EMConversation conversation = getConversation(eMMessage.conversationId(), EMConversation.msgType2ConversationType(eMMessage.getTo(), eMMessage.getChatType()), eMMessage.getType() != EMMessage.Type.CMD);
        if (conversation != null) {
            if (conversation.getCache().getMessage(eMMessage.getMsgId()) == null) {
                z = false;
            }
            if (!z) {
                long currentTimeMillis = System.currentTimeMillis();
                EMMessage lastMessage = conversation.getLastMessage();
                if (lastMessage != null && currentTimeMillis < lastMessage.getMsgTime()) {
                    currentTimeMillis = lastMessage.getMsgTime();
                }
                eMMessage.setMsgTime(currentTimeMillis + 1);
                conversation.getCache().addMessage(eMMessage);
            }
        }
        this.mClient.execute(new Runnable() { // from class: com.hyphenate.chat.EMChatManager.2
            @Override // java.lang.Runnable
            public void run() {
                String str;
                String str2 = null;
                try {
                    if (eMMessage.getType() == EMMessage.Type.IMAGE) {
                        eMMessage.setStatus(EMMessage.Status.INPROGRESS);
                        EMImageMessageBody eMImageMessageBody = (EMImageMessageBody) eMMessage.getBody();
                        if (eMImageMessageBody == null) {
                            new Object(1, "Message body can not be null", eMMessage) { // from class: com.hyphenate.chat.EMChatManager.1HandleError
                                final /* synthetic */ EMMessage val$msg;

                                {
                                    this.val$msg = r5;
                                    EMMessage.EMCallbackHolder eMCallbackHolder = this.val$msg.messageStatusCallBack;
                                    if (eMCallbackHolder != null) {
                                        eMCallbackHolder.onError(r3, r4);
                                    }
                                }
                            };
                            return;
                        }
                        String localUrl = eMImageMessageBody.getLocalUrl();
                        File file = new File(localUrl);
                        if (!file.exists() || !file.canRead()) {
                            new Object(401, "File not exists or can not be read", eMMessage) { // from class: com.hyphenate.chat.EMChatManager.1HandleError
                                final /* synthetic */ EMMessage val$msg;

                                {
                                    this.val$msg = r5;
                                    EMMessage.EMCallbackHolder eMCallbackHolder = this.val$msg.messageStatusCallBack;
                                    if (eMCallbackHolder != null) {
                                        eMCallbackHolder.onError(r3, r4);
                                    }
                                }
                            };
                            return;
                        }
                        if (!eMImageMessageBody.isSendOriginalImage()) {
                            str = ImageUtils.getScaledImage(EMChatManager.this.mClient.getContext(), localUrl);
                            if (!str.equals(localUrl)) {
                                File file2 = new File(str);
                                long length = new File(localUrl).length();
                                long length2 = file2.length();
                                if (length == 0) {
                                    EMLog.d(EMChatManager.TAG, "original image size:" + length);
                                    new Object(401, "original image size is 0", eMMessage) { // from class: com.hyphenate.chat.EMChatManager.1HandleError
                                        final /* synthetic */ EMMessage val$msg;

                                        {
                                            this.val$msg = r5;
                                            EMMessage.EMCallbackHolder eMCallbackHolder = this.val$msg.messageStatusCallBack;
                                            if (eMCallbackHolder != null) {
                                                eMCallbackHolder.onError(r3, r4);
                                            }
                                        }
                                    };
                                    return;
                                }
                                EMLog.d(EMChatManager.TAG, "original image size:" + length + " scaled image size:" + length2 + " ratio:" + ((int) (length2 / length)) + "%");
                                eMImageMessageBody.setLocalUrl(str);
                                str2 = localUrl;
                                BitmapFactory.Options bitmapOptions = ImageUtils.getBitmapOptions(str);
                                eMImageMessageBody.setSize(bitmapOptions.outWidth, bitmapOptions.outHeight);
                                eMImageMessageBody.setFileName(new File(str).getName());
                            }
                        }
                        str = localUrl;
                        BitmapFactory.Options bitmapOptions2 = ImageUtils.getBitmapOptions(str);
                        eMImageMessageBody.setSize(bitmapOptions2.outWidth, bitmapOptions2.outHeight);
                        eMImageMessageBody.setFileName(new File(str).getName());
                    } else {
                        str2 = null;
                    }
                    EMChatManager.this.setMessageSendCallback(eMMessage, conversation, eMMessage.getMsgId(), str2);
                    EMChatManager.this.emaObject.sendMessage(eMMessage.emaObject);
                } catch (Exception e) {
                    e.printStackTrace();
                    new Object(1, "send message failed", eMMessage) { // from class: com.hyphenate.chat.EMChatManager.1HandleError
                        final /* synthetic */ EMMessage val$msg;

                        {
                            this.val$msg = r5;
                            EMMessage.EMCallbackHolder eMCallbackHolder = this.val$msg.messageStatusCallBack;
                            if (eMCallbackHolder != null) {
                                eMCallbackHolder.onError(r3, r4);
                            }
                        }
                    };
                }
            }
        });
    }

    @Deprecated
    public void setMessageListened(EMMessage eMMessage) {
        setVoiceMessageListened(eMMessage);
    }

    public void setVoiceMessageListened(EMMessage eMMessage) {
        eMMessage.setListened(true);
        updateMessage(eMMessage);
    }

    public boolean updateMessage(EMMessage eMMessage) {
        String from = eMMessage.direct() == EMMessage.Direct.RECEIVE ? eMMessage.getFrom() : eMMessage.getTo();
        if (eMMessage.getType() == EMMessage.Type.CMD) {
            return false;
        }
        return getConversation(eMMessage.conversationId(), EMConversation.msgType2ConversationType(from, eMMessage.getChatType()), true).updateMessage(eMMessage);
    }

    public boolean updateParticipant(String str, String str2) {
        return this.emaObject.updateParticipant(str, str2);
    }
}

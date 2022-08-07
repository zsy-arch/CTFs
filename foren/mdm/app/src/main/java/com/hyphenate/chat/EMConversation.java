package com.hyphenate.chat;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.adapter.EMAConversation;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.util.EMLog;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class EMConversation extends EMBase<EMAConversation> {
    private static final int LIST_SIZE = 512;
    private static final String TAG = "conversation";
    EMAConversation emaObject;

    /* loaded from: classes2.dex */
    public enum EMConversationType {
        Chat,
        GroupChat,
        ChatRoom,
        DiscussionGroup,
        HelpDesk
    }

    /* loaded from: classes2.dex */
    public enum EMSearchDirection {
        UP,
        DOWN
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class MessageCache {
        TreeMap<Long, EMMessage> sortedMessages = new TreeMap<>(new MessageComparator());
        Map<String, EMMessage> messages = new HashMap();
        Map<String, Long> idTimeMap = new HashMap();
        final boolean sortByServerTime = EMClient.getInstance().getChatConfigPrivate().b().isSortMessageByServerTime();

        /* loaded from: classes2.dex */
        class MessageComparator implements Comparator<Long> {
            MessageComparator() {
            }

            public int compare(Long l, Long l2) {
                long longValue = l.longValue() - l2.longValue();
                if (longValue > 0) {
                    return 1;
                }
                return longValue == 0 ? 0 : -1;
            }
        }

        MessageCache() {
        }

        public synchronized void addMessage(EMMessage eMMessage) {
            if (eMMessage != null) {
                if (!(eMMessage.getMsgTime() == 0 || eMMessage.getMsgTime() == -1 || eMMessage.getMsgId() == null || eMMessage.getMsgId().isEmpty() || eMMessage.getType() == EMMessage.Type.CMD)) {
                    String msgId = eMMessage.getMsgId();
                    long msgTime = this.sortByServerTime ? eMMessage.getMsgTime() : eMMessage.localTime();
                    this.sortedMessages.put(Long.valueOf(msgTime), eMMessage);
                    this.messages.put(msgId, eMMessage);
                    this.idTimeMap.put(msgId, Long.valueOf(msgTime));
                }
            }
        }

        public synchronized void addMessages(List<EMMessage> list) {
            for (EMMessage eMMessage : list) {
                addMessage(eMMessage);
            }
        }

        public synchronized void clear() {
            this.sortedMessages.clear();
            this.messages.clear();
            this.idTimeMap.clear();
        }

        public synchronized List<EMMessage> getAllMessages() {
            ArrayList arrayList;
            arrayList = new ArrayList();
            arrayList.addAll(this.sortedMessages.values());
            return arrayList;
        }

        public synchronized EMMessage getLastMessage() {
            return this.sortedMessages.isEmpty() ? null : this.sortedMessages.lastEntry().getValue();
        }

        public synchronized EMMessage getMessage(String str) {
            EMMessage eMMessage;
            if (str != null) {
                if (!str.isEmpty()) {
                    eMMessage = this.messages.get(str);
                }
            }
            eMMessage = null;
            return eMMessage;
        }

        public synchronized boolean isEmpty() {
            return this.sortedMessages.isEmpty();
        }

        public synchronized void removeMessage(String str) {
            if (str != null) {
                if (!str.isEmpty() && this.messages.get(str) != null) {
                    Long l = this.idTimeMap.get(str);
                    if (l != null) {
                        this.sortedMessages.remove(l);
                        this.idTimeMap.remove(str);
                    }
                    this.messages.remove(str);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMConversation(EMAConversation eMAConversation) {
        this.emaObject = eMAConversation;
    }

    public static EMConversationType msgType2ConversationType(String str, EMMessage.ChatType chatType) {
        return chatType == EMMessage.ChatType.Chat ? EMConversationType.Chat : chatType == EMMessage.ChatType.GroupChat ? EMConversationType.GroupChat : chatType == EMMessage.ChatType.ChatRoom ? EMConversationType.ChatRoom : EMConversationType.Chat;
    }

    public void appendMessage(EMMessage eMMessage) {
        this.emaObject.appendMessage(eMMessage.emaObject);
        getCache().addMessage(eMMessage);
    }

    public void clear() {
        getCache().clear();
    }

    public void clearAllMessages() {
        this.emaObject.clearAllMessages();
        getCache().clear();
    }

    public String conversationId() {
        return this.emaObject.conversationId();
    }

    public List<EMMessage> getAllMessages() {
        if (getCache().isEmpty()) {
            EMAMessage latestMessage = this.emaObject.latestMessage();
            ArrayList arrayList = new ArrayList();
            if (latestMessage != null) {
                arrayList.add(new EMMessage(latestMessage));
            }
            getCache().addMessages(arrayList);
        }
        return getCache().getAllMessages();
    }

    public int getAllMsgCount() {
        return this.emaObject.messagesCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageCache getCache() {
        MessageCache messageCache;
        synchronized (EMClient.getInstance().chatManager().caches) {
            messageCache = EMClient.getInstance().chatManager().caches.get(this.emaObject.conversationId());
            if (messageCache == null) {
                messageCache = new MessageCache();
            }
            EMClient.getInstance().chatManager().caches.put(this.emaObject.conversationId(), messageCache);
        }
        return messageCache;
    }

    public String getExtField() {
        return this.emaObject.extField();
    }

    public EMMessage getLastMessage() {
        if (!getCache().isEmpty()) {
            return getCache().getLastMessage();
        }
        EMAMessage latestMessage = this.emaObject.latestMessage();
        EMMessage eMMessage = latestMessage == null ? null : new EMMessage(latestMessage);
        getCache().addMessage(eMMessage);
        return eMMessage;
    }

    public EMMessage getMessage(String str, boolean z) {
        EMMessage message = getCache().getMessage(str);
        if (message == null) {
            message = new EMMessage(this.emaObject.loadMessage(str));
            getCache().addMessage(message);
        }
        this.emaObject.markMessageAsRead(str, z);
        return message;
    }

    public String getMessageAttachmentPath() {
        return EMClient.getInstance().getChatConfigPrivate().D() + "/" + EMClient.getInstance().getCurrentUser() + "/" + conversationId();
    }

    public EMConversationType getType() {
        EMAConversation.EMAConversationType _getType = this.emaObject._getType();
        return _getType == EMAConversation.EMAConversationType.CHAT ? EMConversationType.Chat : _getType == EMAConversation.EMAConversationType.GROUPCHAT ? EMConversationType.GroupChat : _getType == EMAConversation.EMAConversationType.CHATROOM ? EMConversationType.ChatRoom : _getType == EMAConversation.EMAConversationType.DISCUSSIONGROUP ? EMConversationType.DiscussionGroup : _getType == EMAConversation.EMAConversationType.HELPDESK ? EMConversationType.HelpDesk : EMConversationType.Chat;
    }

    public int getUnreadMsgCount() {
        return this.emaObject.unreadMessagesCount();
    }

    public void insertMessage(EMMessage eMMessage) {
        this.emaObject.insertMessage(eMMessage.emaObject);
        getCache().addMessage(eMMessage);
    }

    public boolean isGroup() {
        EMConversationType type = getType();
        return type == EMConversationType.GroupChat || type == EMConversationType.ChatRoom;
    }

    @Deprecated
    public List<EMMessage> loadMessages(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            EMAMessage loadMessage = this.emaObject.loadMessage(str);
            if (loadMessage != null) {
                arrayList.add(new EMMessage(loadMessage));
            }
        }
        getCache().addMessages(arrayList);
        return arrayList;
    }

    public List<EMMessage> loadMoreMsgFromDB(String str, int i) {
        List<EMAMessage> loadMoreMessages = this.emaObject.loadMoreMessages(str, i, EMAConversation.EMASearchDirection.UP);
        ArrayList arrayList = new ArrayList();
        for (EMAMessage eMAMessage : loadMoreMessages) {
            if (eMAMessage != null) {
                arrayList.add(new EMMessage(eMAMessage));
            }
        }
        getCache().addMessages(arrayList);
        return arrayList;
    }

    public void markAllMessagesAsRead() {
        this.emaObject.markAllMessagesAsRead(true);
    }

    public void markMessageAsRead(String str) {
        this.emaObject.markMessageAsRead(str, true);
    }

    public void removeMessage(String str) {
        EMLog.d(TAG, "remove msg from conversation:" + str);
        this.emaObject._removeMessage(str);
        getCache().removeMessage(str);
    }

    public List<EMMessage> searchMsgFromDB(long j, int i, EMSearchDirection eMSearchDirection) {
        List<EMAMessage> searchMessages = this.emaObject.searchMessages(j, i, eMSearchDirection == EMSearchDirection.UP ? EMAConversation.EMASearchDirection.UP : EMAConversation.EMASearchDirection.DOWN);
        List<EMMessage> linkedList = searchMessages.size() > 512 ? new LinkedList<>() : new ArrayList<>();
        for (EMAMessage eMAMessage : searchMessages) {
            if (eMAMessage != null) {
                linkedList.add(new EMMessage(eMAMessage));
            }
        }
        return linkedList;
    }

    public List<EMMessage> searchMsgFromDB(long j, long j2, int i) {
        List<EMAMessage> searchMessages = this.emaObject.searchMessages(j, j2, i);
        List<EMMessage> linkedList = searchMessages.size() > 512 ? new LinkedList<>() : new ArrayList<>();
        for (EMAMessage eMAMessage : searchMessages) {
            if (eMAMessage != null) {
                linkedList.add(new EMMessage(eMAMessage));
            }
        }
        return linkedList;
    }

    public List<EMMessage> searchMsgFromDB(EMMessage.Type type, long j, int i, String str, EMSearchDirection eMSearchDirection) {
        List<EMAMessage> searchMessages = this.emaObject.searchMessages(type.ordinal(), j, i, str, eMSearchDirection == EMSearchDirection.UP ? EMAConversation.EMASearchDirection.UP : EMAConversation.EMASearchDirection.DOWN);
        List<EMMessage> linkedList = searchMessages.size() > 512 ? new LinkedList<>() : new ArrayList<>();
        for (EMAMessage eMAMessage : searchMessages) {
            if (eMAMessage != null) {
                linkedList.add(new EMMessage(eMAMessage));
            }
        }
        return linkedList;
    }

    public List<EMMessage> searchMsgFromDB(String str, long j, int i, String str2, EMSearchDirection eMSearchDirection) {
        List<EMAMessage> searchMessages = this.emaObject.searchMessages(str, j, i, str2, eMSearchDirection == EMSearchDirection.UP ? EMAConversation.EMASearchDirection.UP : EMAConversation.EMASearchDirection.DOWN);
        List<EMMessage> linkedList = searchMessages.size() > 512 ? new LinkedList<>() : new ArrayList<>();
        for (EMAMessage eMAMessage : searchMessages) {
            if (eMAMessage != null) {
                linkedList.add(new EMMessage(eMAMessage));
            }
        }
        return linkedList;
    }

    public void setExtField(String str) {
        this.emaObject._setExtField(str);
    }

    public boolean updateMessage(EMMessage eMMessage) {
        return this.emaObject.updateMessage(eMMessage.emaObject);
    }
}

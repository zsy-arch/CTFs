package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.adapter.EMACallback;
import com.hyphenate.chat.adapter.message.EMACmdMessageBody;
import com.hyphenate.chat.adapter.message.EMAFileMessageBody;
import com.hyphenate.chat.adapter.message.EMAImageMessageBody;
import com.hyphenate.chat.adapter.message.EMALocationMessageBody;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.chat.adapter.message.EMAMessageBody;
import com.hyphenate.chat.adapter.message.EMATextMessageBody;
import com.hyphenate.chat.adapter.message.EMAVideoMessageBody;
import com.hyphenate.chat.adapter.message.EMAVoiceMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EMMessage extends EMBase<EMAMessage> implements Parcelable, Cloneable {
    static final String ATTR_ENCRYPTED = "isencrypted";
    public static final Parcelable.Creator<EMMessage> CREATOR = new Parcelable.Creator<EMMessage>() { // from class: com.hyphenate.chat.EMMessage.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMMessage createFromParcel(Parcel parcel) {
            return new EMMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMMessage[] newArray(int i) {
            return new EMMessage[i];
        }
    };
    private static final String TAG = "msg";
    EMMessageBody body;
    EMAMessage emaObject;
    EMCallbackHolder messageStatusCallBack;

    /* loaded from: classes2.dex */
    public enum ChatType {
        Chat,
        GroupChat,
        ChatRoom
    }

    /* loaded from: classes2.dex */
    public enum Direct {
        SEND,
        RECEIVE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class EMCallbackHolder implements EMCallBack {
        EMCallBack innerCallback = null;
        private EMCallBack strong;
        private WeakReference<EMCallBack> weak;

        EMCallbackHolder(EMCallBack eMCallBack) {
            this.weak = new WeakReference<>(eMCallBack);
        }

        synchronized EMCallBack getRef() {
            return this.strong != null ? this.strong : this.weak != null ? this.weak.get() : null;
        }

        synchronized void makeItStrong() {
            if (!(this.strong != null || this.weak == null || this.weak.get() == null)) {
                this.strong = this.weak.get();
            }
        }

        @Override // com.hyphenate.EMCallBack
        public void onError(int i, String str) {
            if (this.innerCallback != null) {
                this.innerCallback.onError(i, str);
            }
            EMCallBack ref = getRef();
            if (ref != null) {
                ref.onError(i, str);
                release();
            }
        }

        @Override // com.hyphenate.EMCallBack
        public void onProgress(int i, String str) {
            if (this.innerCallback != null) {
                this.innerCallback.onProgress(i, str);
            }
            EMCallBack ref = getRef();
            if (ref != null) {
                ref.onProgress(i, str);
            }
        }

        @Override // com.hyphenate.EMCallBack
        public void onSuccess() {
            if (this.innerCallback != null) {
                this.innerCallback.onSuccess();
            }
            EMCallBack ref = getRef();
            if (ref != null) {
                ref.onSuccess();
                release();
            }
        }

        synchronized void release() {
            if (this.strong != null) {
                this.weak = new WeakReference<>(this.strong);
                this.strong = null;
            }
        }

        synchronized void update(EMCallBack eMCallBack) {
            if (this.strong != null) {
                this.strong = eMCallBack;
            } else {
                this.weak = new WeakReference<>(eMCallBack);
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum Status {
        SUCCESS,
        FAIL,
        INPROGRESS,
        CREATE
    }

    /* loaded from: classes2.dex */
    public enum Type {
        TXT,
        IMAGE,
        VIDEO,
        LOCATION,
        VOICE,
        FILE,
        CMD
    }

    private EMMessage(Parcel parcel) {
        EMMessage message = EMClient.getInstance().chatManager().getMessage(parcel.readString());
        this.emaObject = message == null ? null : message.emaObject;
    }

    public EMMessage(EMAMessage eMAMessage) {
        this.emaObject = eMAMessage;
    }

    public static EMMessage createFileSendMessage(String str, String str2) {
        if (!new File(str).exists()) {
            EMLog.e("msg", "file does not exist");
            return null;
        }
        EMMessage createSendMessage = createSendMessage(Type.FILE);
        createSendMessage.setTo(str2);
        createSendMessage.addBody(new EMNormalFileMessageBody(new File(str)));
        return createSendMessage;
    }

    public static EMMessage createImageSendMessage(String str, boolean z, String str2) {
        if (!new File(str).exists()) {
            EMLog.e("msg", "image file does not exsit");
            return null;
        }
        EMMessage createSendMessage = createSendMessage(Type.IMAGE);
        createSendMessage.setTo(str2);
        EMImageMessageBody eMImageMessageBody = new EMImageMessageBody(new File(str));
        eMImageMessageBody.setSendOriginalImage(z);
        createSendMessage.addBody(eMImageMessageBody);
        return createSendMessage;
    }

    public static EMMessage createLocationSendMessage(double d, double d2, String str, String str2) {
        EMMessage createSendMessage = createSendMessage(Type.LOCATION);
        createSendMessage.addBody(new EMLocationMessageBody(str, d, d2));
        createSendMessage.setTo(str2);
        return createSendMessage;
    }

    public static EMMessage createReceiveMessage(Type type) {
        EMMessage eMMessage = new EMMessage(EMAMessage.createReceiveMessage("", self(), null, ChatType.Chat.ordinal()));
        eMMessage.setTo(EMSessionManager.getInstance().currentUser.getUsername());
        return eMMessage;
    }

    public static EMMessage createSendMessage(Type type) {
        return new EMMessage(EMAMessage.createSendMessage(self(), "", null, ChatType.Chat.ordinal()));
    }

    public static EMMessage createTxtSendMessage(String str, String str2) {
        if (EMClient.getInstance().getCurrentUser() == null) {
            EMSessionManager.getInstance().getLastLoginUser();
        }
        if (str.length() > 0) {
            EMMessage createSendMessage = createSendMessage(Type.TXT);
            createSendMessage.addBody(new EMTextMessageBody(str));
            createSendMessage.setTo(str2);
            return createSendMessage;
        }
        EMLog.e("msg", "text content size must be greater than 10");
        return null;
    }

    public static EMMessage createVideoSendMessage(String str, String str2, int i, String str3) {
        File file = new File(str);
        if (!file.exists()) {
            EMLog.e("msg", "video file does not exist");
            return null;
        }
        EMMessage createSendMessage = createSendMessage(Type.VIDEO);
        createSendMessage.setTo(str3);
        createSendMessage.addBody(new EMVideoMessageBody(str, str2, i, file.length()));
        return createSendMessage;
    }

    public static EMMessage createVoiceSendMessage(String str, int i, String str2) {
        if (!new File(str).exists()) {
            EMLog.e("msg", "voice file does not exsit");
            return null;
        }
        EMMessage createSendMessage = createSendMessage(Type.VOICE);
        createSendMessage.addBody(new EMVoiceMessageBody(new File(str), i));
        createSendMessage.setTo(str2);
        return createSendMessage;
    }

    static String self() {
        String currentUser = EMClient.getInstance().getCurrentUser();
        return currentUser == null ? EMSessionManager.getInstance().getLastLoginUser() : currentUser;
    }

    public void addBody(EMMessageBody eMMessageBody) {
        this.body = eMMessageBody;
        this.emaObject.addBody(eMMessageBody.emaObject);
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String conversationId() {
        return this.emaObject.conversationId();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Direct direct() {
        return this.emaObject.direction() == EMAMessage.EMADirection.SEND ? Direct.SEND : Direct.RECEIVE;
    }

    public Map<String, Object> ext() {
        return this.emaObject.ext();
    }

    public EMMessageBody getBody() {
        if (this.body != null) {
            return this.body;
        }
        List<EMAMessageBody> bodies = this.emaObject.bodies();
        if (bodies.size() <= 0) {
            return null;
        }
        EMAMessageBody eMAMessageBody = bodies.get(0);
        if (eMAMessageBody instanceof EMATextMessageBody) {
            this.body = new EMTextMessageBody((EMATextMessageBody) eMAMessageBody);
        } else if (eMAMessageBody instanceof EMACmdMessageBody) {
            this.body = new EMCmdMessageBody((EMACmdMessageBody) eMAMessageBody);
        } else if (eMAMessageBody instanceof EMAVideoMessageBody) {
            this.body = new EMVideoMessageBody((EMAVideoMessageBody) eMAMessageBody);
        } else if (eMAMessageBody instanceof EMAVoiceMessageBody) {
            this.body = new EMVoiceMessageBody((EMAVoiceMessageBody) eMAMessageBody);
        } else if (eMAMessageBody instanceof EMAImageMessageBody) {
            this.body = new EMImageMessageBody((EMAImageMessageBody) eMAMessageBody);
        } else if (eMAMessageBody instanceof EMALocationMessageBody) {
            this.body = new EMLocationMessageBody((EMALocationMessageBody) eMAMessageBody);
        } else if (eMAMessageBody instanceof EMAFileMessageBody) {
            this.body = new EMNormalFileMessageBody((EMAFileMessageBody) eMAMessageBody);
        }
        return this.body;
    }

    public boolean getBooleanAttribute(String str) throws HyphenateException {
        if (str == null || str.equals("")) {
            throw new HyphenateException("attribute " + str + " can not be null or empty");
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        if (this.emaObject.getBooleanAttribute(str, false, atomicBoolean)) {
            return atomicBoolean.get();
        }
        throw new HyphenateException("attribute " + str + " not found");
    }

    public boolean getBooleanAttribute(String str, boolean z) {
        if (str == null || str.equals("")) {
            return z;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        return this.emaObject.getBooleanAttribute(str, false, atomicBoolean) ? atomicBoolean.get() : z;
    }

    public ChatType getChatType() {
        EMAMessage.EMAChatType chatType = this.emaObject.chatType();
        ChatType chatType2 = ChatType.Chat;
        return chatType == EMAMessage.EMAChatType.SINGLE ? ChatType.Chat : chatType == EMAMessage.EMAChatType.GROUP ? ChatType.GroupChat : ChatType.ChatRoom;
    }

    public String getFrom() {
        return this.emaObject.from();
    }

    public int getIntAttribute(String str) throws HyphenateException {
        if (str == null || str.equals("")) {
            throw new HyphenateException("attribute " + str + " can not be null or empty");
        }
        AtomicInteger atomicInteger = new AtomicInteger();
        if (this.emaObject.getIntAttribute(str, -1, atomicInteger)) {
            return atomicInteger.intValue();
        }
        throw new HyphenateException("attribute " + str + " not found");
    }

    public int getIntAttribute(String str, int i) {
        if (str == null || str.equals("")) {
            return i;
        }
        AtomicInteger atomicInteger = new AtomicInteger();
        return this.emaObject.getIntAttribute(str, -1, atomicInteger) ? atomicInteger.intValue() : i;
    }

    public JSONArray getJSONArrayAttribute(String str) throws HyphenateException {
        if (str == null || str.equals("")) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (this.emaObject.getJsonAttribute(str, "[]", sb)) {
            try {
                return new JSONArray(sb.toString());
            } catch (JSONException e) {
            }
        }
        throw new HyphenateException("attribute " + str + " not found");
    }

    public JSONObject getJSONObjectAttribute(String str) throws HyphenateException {
        if (str == null || str.equals("")) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (this.emaObject.getJsonAttribute(str, "{}", sb)) {
            try {
                return new JSONObject(sb.toString());
            } catch (JSONException e) {
            }
        }
        throw new HyphenateException("attribute " + str + " not found");
    }

    public long getLongAttribute(String str) throws HyphenateException {
        if (str == null || str.equals("")) {
            throw new HyphenateException("attribute " + str + " can not be null or empty");
        }
        AtomicLong atomicLong = new AtomicLong();
        if (this.emaObject.getLongAttribute(str, -1L, atomicLong)) {
            return atomicLong.longValue();
        }
        throw new HyphenateException("attribute " + str + " not found");
    }

    public long getLongAttribute(String str, long j) {
        if (str == null || str.equals("")) {
            return j;
        }
        AtomicLong atomicLong = new AtomicLong();
        return this.emaObject.getLongAttribute(str, -1L, atomicLong) ? atomicLong.longValue() : j;
    }

    public String getMsgId() {
        return this.emaObject.msgId();
    }

    public long getMsgTime() {
        return this.emaObject.timeStamp();
    }

    public String getStringAttribute(String str) throws HyphenateException {
        if (str == null || str.equals("")) {
            throw new HyphenateException("attribute " + str + " can not be null or empty");
        }
        StringBuilder sb = new StringBuilder();
        if (this.emaObject.getStringAttribute(str, "", sb)) {
            return sb.toString();
        }
        throw new HyphenateException("attribute " + str + " not found");
    }

    public String getStringAttribute(String str, String str2) {
        if (str == null || str.equals("")) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        return this.emaObject.getStringAttribute(str, "", sb) ? sb.toString() : str2;
    }

    public String getTo() {
        return this.emaObject.to();
    }

    public Type getType() {
        List<EMAMessageBody> bodies = this.emaObject.bodies();
        if (bodies.size() > 0) {
            int type = bodies.get(0).type();
            if (type == Type.TXT.ordinal()) {
                return Type.TXT;
            }
            if (type == Type.IMAGE.ordinal()) {
                return Type.IMAGE;
            }
            if (type == Type.CMD.ordinal()) {
                return Type.CMD;
            }
            if (type == Type.FILE.ordinal()) {
                return Type.FILE;
            }
            if (type == Type.VIDEO.ordinal()) {
                return Type.VIDEO;
            }
            if (type == Type.VOICE.ordinal()) {
                return Type.VOICE;
            }
            if (type == Type.LOCATION.ordinal()) {
                return Type.LOCATION;
            }
        }
        return Type.TXT;
    }

    public String getUserName() {
        return (getFrom() == null || !getFrom().equals(EMClient.getInstance().getCurrentUser())) ? getFrom() : getTo();
    }

    public boolean isAcked() {
        return this.emaObject.isAcked();
    }

    public boolean isDelivered() {
        return this.emaObject.isDeliverAcked();
    }

    public boolean isListened() {
        return this.emaObject.isListened();
    }

    public boolean isUnread() {
        return !this.emaObject.isRead();
    }

    public long localTime() {
        return this.emaObject.getLocalTime();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void makeCallbackStrong() {
        EMCallbackHolder eMCallbackHolder = this.messageStatusCallBack;
        if (eMCallbackHolder != null) {
            eMCallbackHolder.makeItStrong();
        }
    }

    public int progress() {
        return this.emaObject.progress();
    }

    public void setAcked(boolean z) {
        this.emaObject.setIsAcked(z);
    }

    public void setAttribute(String str, int i) {
        if (str != null && !str.equals("")) {
            this.emaObject.setAttribute(str, i);
        }
    }

    public void setAttribute(String str, long j) {
        if (str != null && !str.equals("")) {
            this.emaObject.setAttribute(str, j);
        }
    }

    public void setAttribute(String str, String str2) {
        if (str != null && !str.equals("")) {
            this.emaObject.setAttribute(str, str2);
        }
    }

    public void setAttribute(String str, JSONArray jSONArray) {
        if (str != null && !str.equals("")) {
            this.emaObject.setJsonAttribute(str, jSONArray.toString());
        }
    }

    public void setAttribute(String str, JSONObject jSONObject) {
        if (str != null && !str.equals("")) {
            this.emaObject.setJsonAttribute(str, jSONObject.toString());
        }
    }

    public void setAttribute(String str, boolean z) {
        if (str != null && !str.equals("")) {
            this.emaObject.setAttribute(str, z);
        }
    }

    void setCallback(final EMCallbackHolder eMCallbackHolder) {
        this.emaObject.setCallback(new EMACallback(new EMCallBack() { // from class: com.hyphenate.chat.EMMessage.1
            @Override // com.hyphenate.EMCallBack
            public void onError(int i, String str) {
                if (eMCallbackHolder != null) {
                    eMCallbackHolder.onError(i, str);
                }
            }

            @Override // com.hyphenate.EMCallBack
            public void onProgress(int i, String str) {
                if (eMCallbackHolder != null) {
                    eMCallbackHolder.onProgress(i, str);
                }
            }

            @Override // com.hyphenate.EMCallBack
            public void onSuccess() {
                if (eMCallbackHolder != null) {
                    eMCallbackHolder.onSuccess();
                }
            }
        }));
    }

    public void setChatType(ChatType chatType) {
        EMAMessage.EMAChatType eMAChatType = EMAMessage.EMAChatType.SINGLE;
        this.emaObject.setChatType(chatType == ChatType.Chat ? EMAMessage.EMAChatType.SINGLE : chatType == ChatType.GroupChat ? EMAMessage.EMAChatType.GROUP : EMAMessage.EMAChatType.CHATROOM);
    }

    public void setDeliverAcked(boolean z) {
        this.emaObject.setIsDeliverAcked(z);
    }

    public void setDelivered(boolean z) {
        this.emaObject.setIsDeliverAcked(z);
    }

    public void setDirection(Direct direct) {
        this.emaObject.setDirection(direct.ordinal());
    }

    public void setFrom(String str) {
        this.emaObject.setFrom(str);
        if (!self().equals(str)) {
            this.emaObject.setConversationId(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setInnerCallback(EMCallBack eMCallBack) {
        EMCallbackHolder eMCallbackHolder = this.messageStatusCallBack;
        if (eMCallbackHolder != null) {
            eMCallbackHolder.innerCallback = eMCallBack;
        } else {
            this.messageStatusCallBack = new EMCallbackHolder(null);
            this.messageStatusCallBack.innerCallback = eMCallBack;
        }
        setCallback(this.messageStatusCallBack);
    }

    public void setListened(boolean z) {
        this.emaObject.setListened(z);
    }

    public void setLocalTime(long j) {
        this.emaObject.setLocalTime(j);
    }

    public synchronized void setMessageStatusCallback(EMCallBack eMCallBack) {
        EMCallbackHolder eMCallbackHolder = this.messageStatusCallBack;
        if (eMCallbackHolder != null) {
            eMCallbackHolder.update(eMCallBack);
        } else {
            this.messageStatusCallBack = new EMCallbackHolder(eMCallBack);
        }
        setCallback(this.messageStatusCallBack);
    }

    public void setMsgId(String str) {
        this.emaObject.setMsgId(str);
    }

    public void setMsgTime(long j) {
        this.emaObject.setTimeStamp(j);
    }

    public void setProgress(int i) {
        this.emaObject.setProgress(i);
    }

    public void setStatus(Status status) {
        EMAMessage.EMAMessageStatus eMAMessageStatus = EMAMessage.EMAMessageStatus.SUCCESS;
        if (status == Status.CREATE) {
            eMAMessageStatus = EMAMessage.EMAMessageStatus.NEW;
        } else if (status == Status.SUCCESS) {
            eMAMessageStatus = EMAMessage.EMAMessageStatus.SUCCESS;
        } else if (status == Status.FAIL) {
            eMAMessageStatus = EMAMessage.EMAMessageStatus.FAIL;
        } else if (status == Status.INPROGRESS) {
            eMAMessageStatus = EMAMessage.EMAMessageStatus.DELIVERING;
        }
        this.emaObject.setStatus(eMAMessageStatus.ordinal());
    }

    public void setTo(String str) {
        this.emaObject.setTo(str);
        if (!self().equals(str)) {
            this.emaObject.setConversationId(str);
        }
    }

    public void setUnread(boolean z) {
        this.emaObject.setIsRead(!z);
    }

    public Status status() {
        EMAMessage.EMAMessageStatus _status = this.emaObject._status();
        return _status == EMAMessage.EMAMessageStatus.NEW ? Status.CREATE : _status == EMAMessage.EMAMessageStatus.SUCCESS ? Status.SUCCESS : _status == EMAMessage.EMAMessageStatus.FAIL ? Status.FAIL : _status == EMAMessage.EMAMessageStatus.DELIVERING ? Status.INPROGRESS : Status.CREATE;
    }

    public String toString() {
        return "msg{from:" + getFrom() + ", to:" + getTo() + " body:" + getBody();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getMsgId());
    }
}

package com.hyphenate.chat.adapter.message;

import com.hyphenate.chat.adapter.EMABase;
import com.hyphenate.chat.adapter.EMACallback;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
public class EMAMessage extends EMABase {
    public static final int EMAChatType_CHATROOM = 2;
    public static final int EMAChatType_GROUP = 1;
    public static final int EMAChatType_SINGLE = 0;
    public static final int EMAMessageStatus_DELIVERING = 1;
    public static final int EMAMessageStatus_FAIL = 3;
    public static final int EMAMessageStatus_NEW = 0;
    public static final int EMAMessageStatus_SUCCESS = 2;

    /* loaded from: classes2.dex */
    public enum EMAChatType {
        SINGLE,
        GROUP,
        CHATROOM
    }

    /* loaded from: classes2.dex */
    public enum EMADirection {
        SEND,
        RECEIVE
    }

    /* loaded from: classes2.dex */
    public enum EMAMessageStatus {
        NEW,
        DELIVERING,
        SUCCESS,
        FAIL
    }

    public EMAMessage() {
        nativeInit();
    }

    public EMAMessage(EMAMessage eMAMessage) {
        nativeInit(eMAMessage);
    }

    public static EMAMessage createReceiveMessage(String str, String str2, EMAMessageBody eMAMessageBody, int i) {
        return nativeCreateReceiveMessage(str, str2, eMAMessageBody, i);
    }

    public static EMAMessage createSendMessage(String str, String str2, EMAMessageBody eMAMessageBody, int i) {
        return nativeCreateSendMessage(str, str2, eMAMessageBody, i);
    }

    public static native EMAMessage nativeCreateReceiveMessage(String str, String str2, EMAMessageBody eMAMessageBody, int i);

    public static native EMAMessage nativeCreateSendMessage(String str, String str2, EMAMessageBody eMAMessageBody, int i);

    public EMAMessageStatus _status() {
        switch (nativeStatus()) {
            case 0:
                return EMAMessageStatus.NEW;
            case 1:
                return EMAMessageStatus.DELIVERING;
            case 2:
                return EMAMessageStatus.SUCCESS;
            case 3:
                return EMAMessageStatus.FAIL;
            default:
                return EMAMessageStatus.FAIL;
        }
    }

    public void addBody(EMAMessageBody eMAMessageBody) {
        nativeAddBody(eMAMessageBody);
    }

    public List<EMAMessageBody> bodies() {
        return nativeBodies();
    }

    public EMAChatType chatType() {
        int nativeChatType = nativeChatType();
        EMAChatType eMAChatType = EMAChatType.SINGLE;
        return nativeChatType == EMAChatType.SINGLE.ordinal() ? EMAChatType.SINGLE : nativeChatType == EMAChatType.GROUP.ordinal() ? EMAChatType.GROUP : EMAChatType.CHATROOM;
    }

    public void clearBodies() {
        nativeClearBodies();
    }

    public String conversationId() {
        return nativeConversationId();
    }

    public EMADirection direction() {
        return nativeDirection() == EMADirection.SEND.ordinal() ? EMADirection.SEND : EMADirection.RECEIVE;
    }

    public Map ext() {
        return nativeExt();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public String from() {
        return nativeFrom();
    }

    public boolean getBooleanAttribute(String str, boolean z, AtomicBoolean atomicBoolean) {
        return nativeGetBooleanAttribute(str, z, atomicBoolean);
    }

    public boolean getIntAttribute(String str, int i, AtomicInteger atomicInteger) {
        return nativeGetIntAttribute(str, i, atomicInteger);
    }

    public boolean getJsonAttribute(String str, String str2, StringBuilder sb) {
        return nativeGetJsonAttribute(str, str2, sb);
    }

    public long getLocalTime() {
        return nativeGetLocalTime();
    }

    public boolean getLongAttribute(String str, long j, AtomicLong atomicLong) {
        return nativeGetLongAttribute(str, j, atomicLong);
    }

    public boolean getStringAttribute(String str, String str2, StringBuilder sb) {
        return nativeGetStringAttribute(str, str2, sb);
    }

    public boolean isAcked() {
        return nativeIsAcked();
    }

    public boolean isDeliverAcked() {
        return nativeIsDeliverAcked();
    }

    public boolean isListened() {
        return nativeIsListened();
    }

    public boolean isRead() {
        return nativeIsRead();
    }

    public String msgId() {
        return nativeMsgId();
    }

    public native void nativeAddBody(EMAMessageBody eMAMessageBody);

    public native List<EMAMessageBody> nativeBodies();

    native int nativeChatType();

    public native void nativeClearBodies();

    public native String nativeConversationId();

    native int nativeDirection();

    native Map<String, Object> nativeExt();

    native void nativeFinalize();

    public native String nativeFrom();

    public native boolean nativeGetBooleanAttribute(String str, boolean z, AtomicBoolean atomicBoolean);

    public native boolean nativeGetIntAttribute(String str, int i, AtomicInteger atomicInteger);

    native boolean nativeGetJsonAttribute(String str, String str2, StringBuilder sb);

    native long nativeGetLocalTime();

    public native boolean nativeGetLongAttribute(String str, long j, AtomicLong atomicLong);

    public native boolean nativeGetStringAttribute(String str, String str2, StringBuilder sb);

    native void nativeInit();

    native void nativeInit(EMAMessage eMAMessage);

    public native boolean nativeIsAcked();

    public native boolean nativeIsDeliverAcked();

    native boolean nativeIsListened();

    public native boolean nativeIsRead();

    public native String nativeMsgId();

    native int nativeProgress();

    public native void nativeSetAttribute(String str, int i);

    public native void nativeSetAttribute(String str, long j);

    public native void nativeSetAttribute(String str, String str2);

    public native void nativeSetAttribute(String str, boolean z);

    native void nativeSetCallback(EMACallback eMACallback);

    native void nativeSetChatType(int i);

    public native void nativeSetConversationId(String str);

    native void nativeSetDirection(int i);

    public native void nativeSetFrom(String str);

    public native void nativeSetIsAcked(boolean z);

    public native void nativeSetIsDeliverAcked(boolean z);

    public native void nativeSetIsRead(boolean z);

    native void nativeSetJsonAttribute(String str, String str2);

    native void nativeSetListened(boolean z);

    native void nativeSetLocalTime(long j);

    public native void nativeSetMsgId(String str);

    native void nativeSetProgress(int i);

    public native void nativeSetStatus(int i);

    native void nativeSetTimeStamp(long j);

    public native void nativeSetTo(String str);

    public native int nativeStatus();

    public native long nativeTimeStamp();

    public native String nativeTo();

    public int progress() {
        return nativeProgress();
    }

    public void setAttribute(String str, int i) {
        nativeSetAttribute(str, i);
    }

    public void setAttribute(String str, long j) {
        nativeSetAttribute(str, j);
    }

    public void setAttribute(String str, String str2) {
        nativeSetAttribute(str, str2);
    }

    public void setAttribute(String str, boolean z) {
        nativeSetAttribute(str, z);
    }

    public void setCallback(EMACallback eMACallback) {
        nativeSetCallback(eMACallback);
    }

    public void setChatType(EMAChatType eMAChatType) {
        nativeSetChatType(eMAChatType.ordinal());
    }

    public void setConversationId(String str) {
        nativeSetConversationId(str);
    }

    public void setDirection(int i) {
        nativeSetDirection(i);
    }

    public void setFrom(String str) {
        nativeSetFrom(str);
    }

    public void setIsAcked(boolean z) {
        nativeSetIsAcked(z);
    }

    public void setIsDeliverAcked(boolean z) {
        nativeSetIsDeliverAcked(z);
    }

    public void setIsRead(boolean z) {
        nativeSetIsRead(z);
    }

    public void setJsonAttribute(String str, String str2) {
        nativeSetJsonAttribute(str, str2);
    }

    public void setListened(boolean z) {
        nativeSetListened(z);
    }

    public void setLocalTime(long j) {
        nativeSetLocalTime(j);
    }

    public void setMsgId(String str) {
        nativeSetMsgId(str);
    }

    public void setProgress(int i) {
        nativeSetProgress(i);
    }

    public void setStatus(int i) {
        nativeSetStatus(i);
    }

    public void setTimeStamp(long j) {
        nativeSetTimeStamp(j);
    }

    public void setTo(String str) {
        nativeSetTo(str);
    }

    public long timeStamp() {
        return nativeTimeStamp();
    }

    public String to() {
        return nativeTo();
    }
}

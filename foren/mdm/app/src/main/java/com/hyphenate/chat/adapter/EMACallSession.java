package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMACallSession extends EMABase {

    /* loaded from: classes2.dex */
    public enum ConnectType {
        NONE,
        DIRECT,
        RELAY
    }

    /* loaded from: classes2.dex */
    public enum EndReason {
        HANGUP,
        NORESPONSE,
        REJECT,
        BUSY,
        FAIL
    }

    /* loaded from: classes2.dex */
    public enum Mode {
        NORMAL,
        CONFERENCE_1v1,
        CONFERENCE
    }

    /* loaded from: classes2.dex */
    public enum NetworkStatus {
        CONNECTED,
        UNSTABLE,
        DISCONNECTED
    }

    /* loaded from: classes2.dex */
    public enum Status {
        DISCONNECTED,
        RINGING,
        CONNECTING,
        CONNECTED,
        ACCEPTED
    }

    /* loaded from: classes2.dex */
    public enum StreamControlType {
        PAUSE_VOICE,
        RESUME_VOICE,
        PAUSE_VIDEO,
        RESUME_VIDEO
    }

    /* loaded from: classes2.dex */
    public enum Type {
        VOICE,
        VIDEO
    }

    public EMACallSession() {
        nativeInit();
    }

    public EMACallSession(EMACallSession eMACallSession) {
        nativeInit(eMACallSession);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public String getCallId() {
        return nativeGetCallId();
    }

    public ConnectType getConnectType() {
        int nativeGetConnectType = nativeGetConnectType();
        return nativeGetConnectType == ConnectType.DIRECT.ordinal() ? ConnectType.DIRECT : nativeGetConnectType == ConnectType.RELAY.ordinal() ? ConnectType.RELAY : ConnectType.NONE;
    }

    public String getExt() {
        return nativeGetExt();
    }

    public String getLocalName() {
        return nativeGetLocalName();
    }

    public String getRemoteName() {
        return nativeGetRemoteName();
    }

    public Status getStatus() {
        int nativeGetStatus = nativeGetStatus();
        Status status = Status.DISCONNECTED;
        switch (nativeGetStatus) {
            case 0:
                return Status.DISCONNECTED;
            case 1:
                return Status.RINGING;
            case 2:
                return Status.CONNECTING;
            case 3:
                return Status.CONNECTED;
            case 4:
                return Status.ACCEPTED;
            default:
                return status;
        }
    }

    public Type getType() {
        return nativeGetType() == Type.VOICE.ordinal() ? Type.VOICE : Type.VIDEO;
    }

    native void nativeFinalize();

    native String nativeGetCallId();

    native int nativeGetConnectType();

    native String nativeGetExt();

    native String nativeGetLocalName();

    native String nativeGetRemoteName();

    native int nativeGetStatus();

    native int nativeGetType();

    native void nativeInit();

    native void nativeInit(EMACallSession eMACallSession);
}

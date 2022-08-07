package com.hyphenate.chat;

import com.hyphenate.chat.adapter.EMACallSession;

/* loaded from: classes2.dex */
public class EMCallSession extends EMBase<EMACallSession> {
    EMACallSession emaObject;

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
        FAIL,
        OFFLINE
    }

    /* loaded from: classes2.dex */
    enum SessionInfoType {
        PAUSE_VOICE,
        RESUME_VOICE,
        PAUSE_VIDEO,
        RESUME_VIDEO
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
    public enum Type {
        VOICE,
        VIDEO
    }

    EMCallSession() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMCallSession(EMACallSession eMACallSession) {
        this.emaObject = eMACallSession;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EndReason getEndReason(int i) {
        return i == EndReason.HANGUP.ordinal() ? EndReason.HANGUP : i == EndReason.NORESPONSE.ordinal() ? EndReason.NORESPONSE : i == EndReason.REJECT.ordinal() ? EndReason.REJECT : i == EndReason.BUSY.ordinal() ? EndReason.BUSY : i == EndReason.FAIL.ordinal() ? EndReason.FAIL : EndReason.OFFLINE;
    }

    static SessionInfoType getSessionInfoType(int i) {
        SessionInfoType sessionInfoType = SessionInfoType.RESUME_VOICE;
        switch (i) {
            case 0:
                return SessionInfoType.PAUSE_VOICE;
            case 1:
                return SessionInfoType.RESUME_VOICE;
            case 2:
                return SessionInfoType.PAUSE_VIDEO;
            case 3:
                return SessionInfoType.RESUME_VIDEO;
            default:
                return sessionInfoType;
        }
    }

    public String getCallId() {
        return this.emaObject.getCallId();
    }

    public ConnectType getConnectType() {
        switch (this.emaObject.getConnectType()) {
            case DIRECT:
                return ConnectType.DIRECT;
            case RELAY:
                return ConnectType.RELAY;
            default:
                return ConnectType.NONE;
        }
    }

    public String getExt() {
        return this.emaObject.getExt();
    }

    public String getLocalName() {
        return this.emaObject.getLocalName();
    }

    public String getRemoteName() {
        return this.emaObject.getRemoteName();
    }

    public Type getType() {
        switch (this.emaObject.getType()) {
            case VOICE:
                return Type.VOICE;
            default:
                return Type.VIDEO;
        }
    }

    String password() {
        return "12345678";
    }
}

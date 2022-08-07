package com.hyphenate.chat;

/* loaded from: classes2.dex */
public interface EMCallStateChangeListener {

    /* loaded from: classes2.dex */
    public enum CallError {
        ERROR_NONE("error_none"),
        ERROR_TRANSPORT("error_transport"),
        ERROR_UNAVAILABLE("error_unavailable"),
        REJECTED("rejected"),
        ERROR_NORESPONSE("error_noresponse"),
        ERROR_BUSY("busy"),
        ERROR_NO_DATA("error_no_data"),
        ERROR_LOCAL_SDK_VERSION_OUTDATED("error_local_sdk_version_outdated"),
        ERROR_REMOTE_SDK_VERSION_OUTDATED("error_remote_sdk_version_outdated");
        
        private final String error;

        CallError(String str) {
            this.error = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.error;
        }
    }

    /* loaded from: classes2.dex */
    public enum CallState {
        IDLE("idle"),
        RINGING("ringing"),
        ANSWERING("answering"),
        CONNECTING("connecting"),
        CONNECTED("conntected"),
        ACCEPTED("accepted"),
        DISCONNECTED("disconnected"),
        VOICE_PAUSE("voice_pause"),
        VOICE_RESUME("voice_resume"),
        VIDEO_PAUSE("video_pause"),
        VIDEO_RESUME("video_resume"),
        NETWORK_UNSTABLE("network_unstable"),
        NETWORK_NORMAL("network_normal"),
        NETWORK_DISCONNECTED("network_disconnected");
        
        private final String state;

        CallState(String str) {
            this.state = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.state;
        }
    }

    void onCallStateChanged(CallState callState, CallError callError);
}

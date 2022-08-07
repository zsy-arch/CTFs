package com.superrtc.call;

/* loaded from: classes2.dex */
public class IceCandidate {
    public final String sdp;
    public final int sdpMLineIndex;
    public final String sdpMid;

    public IceCandidate(String sdpMid, int sdpMLineIndex, String sdp) {
        this.sdpMid = sdpMid;
        this.sdpMLineIndex = sdpMLineIndex;
        this.sdp = sdp;
    }

    public String toString() {
        return "sdpMid:::" + this.sdpMid + ", sdpMLineIndex:::" + this.sdpMLineIndex + ", sdp:::" + this.sdp;
    }
}

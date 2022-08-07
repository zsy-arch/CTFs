package com.superrtc.call;

import com.superrtc.call.DataChannel;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class PeerConnection {
    private final long nativeObserver;
    private final long nativePeerConnection;
    private final List<MediaStream> localStreams = new LinkedList();
    private List<RtpSender> senders = new LinkedList();
    private List<RtpReceiver> receivers = new LinkedList();

    /* loaded from: classes2.dex */
    public enum BundlePolicy {
        BALANCED,
        MAXBUNDLE,
        MAXCOMPAT
    }

    /* loaded from: classes2.dex */
    public enum ContinualGatheringPolicy {
        GATHER_ONCE,
        GATHER_CONTINUALLY
    }

    /* loaded from: classes2.dex */
    public enum IceConnectionState {
        NEW,
        CHECKING,
        CONNECTED,
        COMPLETED,
        FAILED,
        DISCONNECTED,
        CLOSED
    }

    /* loaded from: classes2.dex */
    public enum IceGatheringState {
        NEW,
        GATHERING,
        COMPLETE
    }

    /* loaded from: classes2.dex */
    public enum IceTransportsType {
        NONE,
        RELAY,
        NOHOST,
        ALL
    }

    /* loaded from: classes2.dex */
    public enum KeyType {
        RSA,
        ECDSA
    }

    /* loaded from: classes2.dex */
    public interface Observer {
        void onAddStream(MediaStream mediaStream);

        void onDataChannel(DataChannel dataChannel);

        void onIceCandidate(IceCandidate iceCandidate);

        void onIceConnectionChange(IceConnectionState iceConnectionState);

        void onIceConnectionReceivingChange(boolean z);

        void onIceGatheringChange(IceGatheringState iceGatheringState);

        void onRemoveStream(MediaStream mediaStream);

        void onRenegotiationNeeded();

        void onSignalingChange(SignalingState signalingState);
    }

    /* loaded from: classes2.dex */
    public enum RtcpMuxPolicy {
        NEGOTIATE,
        REQUIRE
    }

    /* loaded from: classes2.dex */
    public enum SignalingState {
        STABLE,
        HAVE_LOCAL_OFFER,
        HAVE_LOCAL_PRANSWER,
        HAVE_REMOTE_OFFER,
        HAVE_REMOTE_PRANSWER,
        CLOSED
    }

    /* loaded from: classes2.dex */
    public enum TcpCandidatePolicy {
        ENABLED,
        DISABLED
    }

    private static native void freeObserver(long j);

    private static native void freePeerConnection(long j);

    private native boolean nativeAddIceCandidate(String str, int i, String str2);

    private native boolean nativeAddLocalStream(long j);

    private native RtpSender nativeCreateSender(String str, String str2);

    private native List<RtpReceiver> nativeGetReceivers();

    private native List<RtpSender> nativeGetSenders();

    private native boolean nativeGetStats(StatsObserver statsObserver, long j);

    private native void nativeRemoveLocalStream(long j);

    private native boolean nativeSetMaxSendBandwidth(int i);

    private native boolean nativeTakePicture(String str);

    public native void close();

    public native void createAnswer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public native DataChannel createDataChannel(String str, DataChannel.Init init);

    public native void createOffer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public native SessionDescription getLocalDescription();

    public native SessionDescription getRemoteDescription();

    public native IceConnectionState iceConnectionState();

    public native IceGatheringState iceGatheringState();

    public native boolean setConfiguration(RTCConfiguration rTCConfiguration);

    public native void setLocalDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native void setRemoteDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native void setconfigminbitrate(int i);

    public native void setdisableResize(boolean z);

    public native SignalingState signalingState();

    static {
        System.loadLibrary("hyphenate_av");
    }

    /* loaded from: classes2.dex */
    public static class IceServer {
        public final String password;
        public final String uri;
        public final String username;

        public IceServer(String uri) {
            this(uri, "", "");
        }

        public IceServer(String uri, String username, String password) {
            this.uri = uri;
            this.username = username;
            this.password = password;
        }

        public String toString() {
            return String.valueOf(this.uri) + "[" + this.username + ":" + this.password + "]";
        }
    }

    /* loaded from: classes2.dex */
    public static class RTCConfiguration {
        public List<IceServer> iceServers;
        public IceTransportsType iceTransportsType = IceTransportsType.ALL;
        public BundlePolicy bundlePolicy = BundlePolicy.BALANCED;
        public RtcpMuxPolicy rtcpMuxPolicy = RtcpMuxPolicy.NEGOTIATE;
        public TcpCandidatePolicy tcpCandidatePolicy = TcpCandidatePolicy.ENABLED;
        public int audioJitterBufferMaxPackets = 50;
        public boolean audioJitterBufferFastAccelerate = false;
        public int iceConnectionReceivingTimeout = -1;
        public int iceBackupCandidatePairPingInterval = -1;
        public KeyType keyType = KeyType.ECDSA;
        public ContinualGatheringPolicy continualGatheringPolicy = ContinualGatheringPolicy.GATHER_ONCE;

        public RTCConfiguration(List<IceServer> iceServers) {
            this.iceServers = iceServers;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeerConnection(long nativePeerConnection, long nativeObserver) {
        this.nativePeerConnection = nativePeerConnection;
        this.nativeObserver = nativeObserver;
    }

    public void SetMaxSendBandwidth(int maxbps) {
        nativeSetMaxSendBandwidth(maxbps);
    }

    public boolean TakePicture(String filepath) {
        return nativeTakePicture(filepath);
    }

    public boolean addIceCandidate(IceCandidate candidate) {
        return nativeAddIceCandidate(candidate.sdpMid, candidate.sdpMLineIndex, candidate.sdp);
    }

    public boolean addStream(MediaStream stream) {
        if (!nativeAddLocalStream(stream.nativeStream)) {
            return false;
        }
        this.localStreams.add(stream);
        return true;
    }

    public void removeStream(MediaStream stream) {
        nativeRemoveLocalStream(stream.nativeStream);
        this.localStreams.remove(stream);
    }

    public RtpSender createSender(String kind, String stream_id) {
        RtpSender new_sender = nativeCreateSender(kind, stream_id);
        if (new_sender != null) {
            this.senders.add(new_sender);
        }
        return new_sender;
    }

    public List<RtpSender> getSenders() {
        for (RtpSender sender : this.senders) {
            sender.dispose();
        }
        this.senders = nativeGetSenders();
        return Collections.unmodifiableList(this.senders);
    }

    public List<RtpReceiver> getReceivers() {
        for (RtpReceiver receiver : this.receivers) {
            receiver.dispose();
        }
        this.receivers = nativeGetReceivers();
        return Collections.unmodifiableList(this.receivers);
    }

    public boolean getStats(StatsObserver observer, MediaStreamTrack track) {
        return nativeGetStats(observer, track == null ? 0L : track.nativeTrack);
    }

    public void dispose() {
        close();
        for (MediaStream stream : this.localStreams) {
            nativeRemoveLocalStream(stream.nativeStream);
            stream.dispose();
        }
        this.localStreams.clear();
        for (RtpSender sender : this.senders) {
            sender.dispose();
        }
        this.senders.clear();
        for (RtpReceiver receiver : this.receivers) {
            receiver.dispose();
        }
        this.receivers.clear();
        freePeerConnection(this.nativePeerConnection);
        freeObserver(this.nativeObserver);
    }
}

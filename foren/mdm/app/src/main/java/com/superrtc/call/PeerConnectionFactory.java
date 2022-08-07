package com.superrtc.call;

import com.superrtc.call.EglBase;
import com.superrtc.call.PeerConnection;
import java.util.List;

/* loaded from: classes.dex */
public class PeerConnectionFactory {
    private static final String TAG = "PeerConnectionFactory";
    private static Thread signalingThread;
    private static Thread workerThread;
    private EglBase localEglbase;
    private final long nativeFactory;
    private EglBase remoteEglbase;

    /* loaded from: classes2.dex */
    public static class Options {
        static final int ADAPTER_TYPE_CELLULAR = 4;
        static final int ADAPTER_TYPE_ETHERNET = 1;
        static final int ADAPTER_TYPE_LOOPBACK = 16;
        static final int ADAPTER_TYPE_UNKNOWN = 0;
        static final int ADAPTER_TYPE_VPN = 8;
        static final int ADAPTER_TYPE_WIFI = 2;
        public boolean disableEncryption;
        public boolean disableNetworkMonitor;
        public int networkIgnoreMask;
    }

    public static native boolean initializeAndroidGlobals(Object obj, boolean z, boolean z2, boolean z3);

    public static native void initializeFieldTrials(String str);

    public static native void initializeInternalTracer();

    private static native long nativeCreateAudioSource(long j, MediaConstraints mediaConstraints);

    private static native long nativeCreateAudioTrack(long j, String str, long j2);

    private static native long nativeCreateLocalMediaStream(long j, String str);

    private static native long nativeCreateObserver(PeerConnection.Observer observer);

    private static native long nativeCreatePeerConnection(long j, PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, long j2);

    private static native long nativeCreatePeerConnectionFactory(Options options);

    private static native long nativeCreateVideoSource(long j, VideoCapturer videoCapturer, MediaConstraints mediaConstraints);

    private static native long nativeCreateVideoTrack(long j, String str, long j2);

    private static native void nativeFreeFactory(long j);

    private static native void nativeSetVideoHwAccelerationOptions(long j, Object obj, Object obj2);

    private static native void nativeSetVideoenableHwOptions(long j, boolean z, boolean z2);

    private static native boolean nativeStartAecDump(long j, int i, int i2);

    private static native boolean nativeStartRtcEventLog(long j, int i);

    private static native void nativeStopAecDump(long j);

    private static native void nativeStopRtcEventLog(long j);

    private static native void nativeThreadsCallbacks(long j);

    public static native void setconfigframerate(int i);

    public static native void shutdownInternalTracer();

    public static native boolean startInternalTracingCapture(String str);

    public static native void stopInternalTracingCapture();

    @Deprecated
    public native void nativeSetOptions(long j, Options options);

    static {
        System.loadLibrary("hyphenate_av");
    }

    @Deprecated
    public PeerConnectionFactory() {
        this(null);
    }

    public PeerConnectionFactory(Options options) {
        this.nativeFactory = nativeCreatePeerConnectionFactory(options);
        if (this.nativeFactory == 0) {
            throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
        }
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rtcConfig, MediaConstraints constraints, PeerConnection.Observer observer) {
        long nativeObserver = nativeCreateObserver(observer);
        if (nativeObserver == 0) {
            return null;
        }
        long nativePeerConnection = nativeCreatePeerConnection(this.nativeFactory, rtcConfig, constraints, nativeObserver);
        if (nativePeerConnection == 0) {
            return null;
        }
        return new PeerConnection(nativePeerConnection, nativeObserver);
    }

    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> iceServers, MediaConstraints constraints, PeerConnection.Observer observer) {
        return createPeerConnection(new PeerConnection.RTCConfiguration(iceServers), constraints, observer);
    }

    public MediaStream createLocalMediaStream(String label) {
        return new MediaStream(nativeCreateLocalMediaStream(this.nativeFactory, label));
    }

    public VideoSource createVideoSource(VideoCapturer capturer, MediaConstraints constraints) {
        return new VideoSource(nativeCreateVideoSource(this.nativeFactory, capturer, constraints));
    }

    public VideoTrack createVideoTrack(String id, VideoSource source) {
        return new VideoTrack(nativeCreateVideoTrack(this.nativeFactory, id, source.nativeSource));
    }

    public AudioSource createAudioSource(MediaConstraints constraints) {
        return new AudioSource(nativeCreateAudioSource(this.nativeFactory, constraints));
    }

    public AudioTrack createAudioTrack(String id, AudioSource source) {
        return new AudioTrack(nativeCreateAudioTrack(this.nativeFactory, id, source.nativeSource));
    }

    public boolean startAecDump(int file_descriptor, int filesize_limit_bytes) {
        return nativeStartAecDump(this.nativeFactory, file_descriptor, filesize_limit_bytes);
    }

    public void stopAecDump() {
        nativeStopAecDump(this.nativeFactory);
    }

    public boolean startRtcEventLog(int file_descriptor) {
        return nativeStartRtcEventLog(this.nativeFactory, file_descriptor);
    }

    public void StopRtcEventLog() {
        nativeStopRtcEventLog(this.nativeFactory);
    }

    @Deprecated
    public void setOptions(Options options) {
        nativeSetOptions(this.nativeFactory, options);
    }

    public void setVideoHwAccelerationOptions(EglBase.Context localEglContext, EglBase.Context remoteEglContext) {
        if (this.localEglbase != null) {
            Logging.w(TAG, "Egl context already set.");
            this.localEglbase.release();
        }
        if (this.remoteEglbase != null) {
            Logging.w(TAG, "Egl context already set.");
            this.remoteEglbase.release();
        }
        this.localEglbase = EglBase.create(localEglContext);
        this.remoteEglbase = EglBase.create(remoteEglContext);
        nativeSetVideoHwAccelerationOptions(this.nativeFactory, this.localEglbase.getEglBaseContext(), this.remoteEglbase.getEglBaseContext());
    }

    public void SetVideoenableHwOptions(boolean disaleHWEncoder, boolean disableHWDecoder) {
        nativeSetVideoenableHwOptions(this.nativeFactory, disaleHWEncoder, disableHWDecoder);
    }

    public void dispose() {
        nativeFreeFactory(this.nativeFactory);
        signalingThread = null;
        workerThread = null;
        if (this.localEglbase != null) {
            this.localEglbase.release();
        }
        if (this.remoteEglbase != null) {
            this.remoteEglbase.release();
        }
    }

    public void threadsCallbacks() {
        nativeThreadsCallbacks(this.nativeFactory);
    }

    private static void printStackTrace(Thread thread, String threadName) {
        if (thread != null) {
            StackTraceElement[] stackTraces = thread.getStackTrace();
            if (stackTraces.length > 0) {
                Logging.d(TAG, String.valueOf(threadName) + " stacks trace:");
                for (StackTraceElement stackTrace : stackTraces) {
                    Logging.d(TAG, stackTrace.toString());
                }
            }
        }
    }

    public static void printStackTraces() {
        printStackTrace(workerThread, "Worker thread");
        printStackTrace(signalingThread, "Signaling thread");
    }

    private static void onWorkerThreadReady() {
        workerThread = Thread.currentThread();
        Logging.d(TAG, "onWorkerThreadReady");
    }

    private static void onSignalingThreadReady() {
        signalingThread = Thread.currentThread();
        Logging.d(TAG, "onSignalingThreadReady");
    }
}

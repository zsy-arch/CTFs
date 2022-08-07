package com.superrtc.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Camera;
import android.opengl.EGLContext;
import android.util.Log;
import com.hyphenate.util.EMPrivateConstant;
import com.hyphenate.util.HanziToPinyin;
import com.superrtc.call.AudioTrack;
import com.superrtc.call.CameraEnumerationAndroid;
import com.superrtc.call.DataChannel;
import com.superrtc.call.IceCandidate;
import com.superrtc.call.Logging;
import com.superrtc.call.MediaCodecVideoEncoder;
import com.superrtc.call.MediaConstraints;
import com.superrtc.call.MediaStream;
import com.superrtc.call.MediaStreamTrack;
import com.superrtc.call.PeerConnection;
import com.superrtc.call.PeerConnectionFactory;
import com.superrtc.call.RendererCommon;
import com.superrtc.call.SdpObserver;
import com.superrtc.call.SessionDescription;
import com.superrtc.call.StatsObserver;
import com.superrtc.call.StatsReport;
import com.superrtc.call.VideoCapturerAndroid;
import com.superrtc.call.VideoRenderer;
import com.superrtc.call.VideoRendererGui2;
import com.superrtc.call.VideoSource;
import com.superrtc.call.VideoTrack;
import com.superrtc.util.AppRTCAudioManager;
import com.superrtc.util.CpuMonitor;
import com.superrtc.util.LooperExecutor;
import com.superrtc.voice.WebRtcAudioManager;
import com.tencent.stat.DeviceInfo;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

@SuppressLint({"DefaultLocale"})
/* loaded from: classes2.dex */
public class RtcConnection {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$superrtc$sdk$RtcConnection$Medialogmod = null;
    private static final String AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT = "googAutoGainControl";
    private static final String AUDIO_CODEC_ISAC = "ISAC";
    private static final String AUDIO_CODEC_OPUS = "opus";
    private static final String AUDIO_CODEC_PARAM_BITRATE = "maxaveragebitrate";
    private static final String AUDIO_ECHO_CANCELLATION_CONSTRAINT = "googEchoCancellation";
    private static final String AUDIO_HIGH_PASS_FILTER_CONSTRAINT = "googHighpassFilter";
    private static final String AUDIO_NOISE_SUPPRESSION_CONSTRAINT = "googNoiseSuppression";
    public static final String AUDIO_TRACK_ID = "ARDAMSa0";
    private static final int DEFAULT_CONFIG_FPS = 20;
    private static final String DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT = "DtlsSrtpKeyAgreement";
    private static final String FIELD_TRIAL_VP9 = "WebRTC-SupportVP9/Enabled/";
    private static final int HD_VIDEO_HEIGHT = 1080;
    private static final int HD_VIDEO_WIDTH = 1920;
    private static final int MAX_VIDEO_FPS = 30;
    private static final String MAX_VIDEO_FPS_CONSTRAINT = "maxFrameRate";
    private static final int MAX_VIDEO_HEIGHT = 1920;
    private static final String MAX_VIDEO_HEIGHT_CONSTRAINT = "maxHeight";
    private static final int MAX_VIDEO_WIDTH = 1920;
    private static final String MAX_VIDEO_WIDTH_CONSTRAINT = "maxWidth";
    private static final String MIN_VIDEO_FPS_CONSTRAINT = "minFrameRate";
    private static final String MIN_VIDEO_HEIGHT_CONSTRAINT = "minHeight";
    private static final String MIN_VIDEO_WIDTH_CONSTRAINT = "minWidth";
    private static final String RTC_DEFAULT_AUDIO_CODEC = "OPUS";
    private static final String RTC_DEFAULT_VIDEO_CODEC = "VP8";
    private static final int RTC_DEFAULT_VIDEO_HEIGH = 480;
    private static final int RTC_DEFAULT_VIDEO_WIDTH = 640;
    public static final String RtcConstStringCredential = "credential";
    public static final String RtcConstStringG722 = "G722";
    public static final String RtcConstStringH264 = "H264";
    public static final String RtcConstStringOPUS = "OPUS";
    public static final String RtcConstStringURL = "url";
    public static final String RtcConstStringUserName = "username";
    public static final String RtcConstStringVP8 = "VP8";
    public static final String RtcConstStringVP9 = "VP9";
    public static final String RtcHDVideoDecoderBoolean = "hddecoder";
    public static final String RtcHDVideoEncoderBoolean = "hdencoder";
    public static final String RtcKVCaptureAudioBoolean = "capAudio";
    public static final String RtcKVCaptureVideoBoolean = "capVideo";
    public static final String RtcKVDisablePranswerBoolean = "disablePranswer";
    public static final String RtcKVIceServersArray = "iceServers";
    public static final String RtcKVMaxAudioKbpsLong = "maxAKbps";
    public static final String RtcKVMaxVideoKbpsLong = "maxVKbps";
    public static final String RtcKVPreferACodecString = "prefAC";
    public static final String RtcKVPreferVCodecString = "prefVC";
    public static final String RtcKVRecvAudioBoolean = "recvAudio";
    public static final String RtcKVRecvVideoBoolean = "recvVideo";
    public static final String RtcKVRelayAudioKbpsLong = "relayAKbps";
    public static final String RtcKVRelayOnlyBoolean = "relayOnly";
    public static final String RtcKVRelayVideoKbpsLong = "relayVKbps";
    public static final String RtcKVVideoResolutionLevelLong = "vresL";
    public static final String RtcvideofpsLong = "videofps";
    public static final String RtcvideoheigthLong = "heigth";
    public static final String RtcvideowidthLong = "width";
    private static final String STAT_KEY_CONNECTION = "connection";
    private static final String TAG = "RtcConn";
    private static final String VIDEO_CODEC_H264 = "H264";
    private static final String VIDEO_CODEC_PARAM_START_BITRATE = "x-google-start-bitrate";
    private static final String VIDEO_CODEC_VP8 = "VP8";
    private static final String VIDEO_CODEC_VP9 = "VP9";
    public static final String VIDEO_TRACK_ID = "ARDAMSv0";
    private static LooperExecutor executor;
    private static PeerConnectionFactory factory;
    private static String globalVideoCodec;
    private static LooperExecutor local_executor;
    private static Context sContext;
    private String audioCodec;
    private MediaConstraints audioConstraints;
    private PeerConnection.IceConnectionState iceConnectionState;
    private boolean isInitiator;
    private boolean ishangup;
    private Listener listener;
    private AudioTrack localAudioTrack;
    VideoRendererGui2 localGui;
    RenderRect localRect;
    private VideoRenderer.Callbacks localRender;
    private SessionDescription localSdp;
    private VideoTrack localVideoTrack;
    VideoViewRenderer localViewRender;
    private VideoRenderer localvideorender;
    private MediaStream mediaStream;
    private String name;
    private int numberOfCameras;
    private MediaConstraints pcConstraints;
    private PeerConnection peerConnection;
    private Parameters peerConnectionParameters;
    private boolean preferIsac;
    private RtcCameraDataProcessor processor;
    VideoRendererGui2 remoteGui;
    RenderRect remoteRect;
    private VideoRenderer.Callbacks remoteRender;
    MediaStream remoteStream;
    private VideoTrack remoteVideoTrack;
    VideoViewRenderer remoteViewRender;
    private VideoRenderer remotevideorender;
    private boolean renderVideo;
    private MediaConstraints sdpMediaConstraints;
    private VideoCapturerAndroid videoCapturer;
    private MediaConstraints videoConstraints;
    private VideoSource videoSource;
    private boolean videoSourceStopped;
    private static Logging.Severity medialoglevel = Logging.Severity.LS_WARNING;
    private static boolean disableResize = false;
    private static int configfps = 0;
    private static int configminkbps = 80;
    private static int DEFAULT_CONFIG_MINKBPS = 80;
    private static boolean useVideoCodecHw = false;
    private static boolean iscreateConnectionFactory = false;
    private static int cameraid = -1;
    private static boolean enableLocalViewMirror = true;
    public static int loglevel = 6;
    private static LogListener sLogListenerInternal = new LogListener() { // from class: com.superrtc.sdk.RtcConnection.1
        @Override // com.superrtc.sdk.RtcConnection.LogListener
        public void onLog(int level, String log) {
            Log.i(RtcConnection.TAG, log);
        }
    };
    public static LogListener sLogListener = sLogListenerInternal;
    private boolean enableOpenCamera = false;
    private boolean autoAddVideo = true;
    private boolean localRendererAdded = false;
    private boolean remoteRendererAdded = false;
    private String connectType = "disconn";
    private boolean isconnected = false;
    private Packetslostrate packetslostrate = new Packetslostrate();
    private Packetslostrate recvpacketslostrate = new Packetslostrate();
    private String lusevcodectype = RtcConstStringVP8;
    private String luseacodectype = "OPUS";
    private String rusevcodectype = RtcConstStringVP8;
    private String ruseacodectype = "OPUS";
    private int framesReceived = 0;
    private boolean enableHWEncoder = false;
    private boolean enableHWDecoder = false;
    private long localSeq = 0;
    SDPSsrcChanger ssrcChanger = new SDPSsrcChanger("ARDAMS");
    private LinkedList<SessionDescription> pendingSdp = new LinkedList<>();
    private List<IceCandidate> pendingCandidates = new ArrayList();
    LocalSDPObserver localSDPObserver = new LocalSDPObserver(this, null);
    RemoteSDPObserver remoteSDPObserver = new RemoteSDPObserver(this, null);
    boolean isPranswerState = false;
    private final CpuMonitor cpuMonitor = new CpuMonitor();
    private Bandwidth remoteVideoBW = new Bandwidth();
    private Bandwidth remoteAudioBW = new Bandwidth();
    private Bandwidth localVideoBW = new Bandwidth();
    private Bandwidth localAudioBW = new Bandwidth();
    private RendererCommon.ScalingType LocalscalingType = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
    private RendererCommon.ScalingType RemotescalingType = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
    private int videolastsendpackets = 0;
    private int videocursendpackets = 0;
    private int audiolastsendpackets = 0;
    private int audiocursendpackets = 0;
    private int videosendbytes = 0;
    private int videorecvbytes = 0;
    private int audiosendbytes = 0;
    private int audiorecvbytes = 0;
    private int videolastsendpacketsLost = 0;
    private int videocursendpacketsLost = 0;
    private int audiolastsendpacketsLost = 0;
    private int audiocursendpacketsLost = 0;
    private int videolastrecvpackets = 0;
    private int videocurrecvpackets = 0;
    private int audiolastrecvpackets = 0;
    private int audiocurrecvpackets = 0;
    private int videolastrecvpacketsLost = 0;
    private int videocurrecvpacketsLost = 0;
    private int audiolastrecvpacketsLost = 0;
    private int audiocurrecvpacketsLost = 0;
    private final PCObserver pcObserver = new PCObserver(this, null);
    PeerConnectionFactory.Options options = null;
    private boolean videoCallEnabled = true;
    private boolean videoreceiveEnabled = true;
    private boolean disablePranswer = false;
    private String videoCodec = globalVideoCodec;
    private int videoWidth = 0;
    private int videoHeigth = 0;
    private int videoFramerate = 15;
    private int videoMaxrate = 0;
    private int relayvideoMaxrate = 0;
    private boolean audioCallEnabled = true;
    private boolean audioreceiveEnabled = true;
    private int audioMaxrate = 24;
    private int relayaudioMaxrate = 24;
    private boolean enableRelay = false;
    private boolean preferH264 = true;
    private List<PeerConnection.IceServer> iceServers = new ArrayList();
    private boolean enableaudio = true;
    private boolean enableaec = true;
    private boolean enableagc = true;
    private boolean enablens = true;
    private AppRTCAudioManager audioManager = null;
    private Timer statsTimer = new Timer();

    /* loaded from: classes2.dex */
    public enum AspectMode {
        AspectFit,
        AspectFill
    }

    /* loaded from: classes2.dex */
    public interface Listener {
        void onCandidateCompleted();

        void onClosed();

        void onConnected();

        void onConnectionsetup();

        void onDisconnected();

        void onError(String str);

        void onLocalCandidate(String str);

        void onLocalSdp(String str);

        void onStats(RtcStatistics rtcStatistics);
    }

    /* loaded from: classes2.dex */
    public interface LogListener {
        void onLog(int i, String str);
    }

    /* loaded from: classes2.dex */
    public enum LoggingSeverity {
        LS_SENSITIVE,
        LS_VERBOSE,
        LS_INFO,
        LS_WARNING,
        LS_ERROR,
        LS_NONE
    }

    /* loaded from: classes2.dex */
    public enum Medialogmod {
        M_SENSITIVE,
        M_VERBOSE,
        M_INFO,
        M_WARNING,
        M_ERROR
    }

    /* loaded from: classes2.dex */
    public interface RtcCameraDataProcessor {
        void onProcessData(byte[] bArr, Camera camera, int i, int i2, int i3);

        void setResolution(int i, int i2);
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$superrtc$sdk$RtcConnection$Medialogmod() {
        int[] iArr = $SWITCH_TABLE$com$superrtc$sdk$RtcConnection$Medialogmod;
        if (iArr == null) {
            iArr = new int[Medialogmod.values().length];
            try {
                iArr[Medialogmod.M_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Medialogmod.M_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Medialogmod.M_SENSITIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Medialogmod.M_VERBOSE.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Medialogmod.M_WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            $SWITCH_TABLE$com$superrtc$sdk$RtcConnection$Medialogmod = iArr;
        }
        return iArr;
    }

    public static void registerLogListener(LogListener listener) {
        Log.i(TAG, "[rapi]++ registerLogListener");
        synchronized (RtcConnection.class) {
            Log.i(TAG, "[rapi]-- registerLogListener");
            sLogListener = listener;
            if (listener != null) {
                sLogListener = listener;
            } else {
                sLogListener = sLogListenerInternal;
            }
        }
    }

    protected static void callbackLog(int level, String log) {
        synchronized (RtcConnection.class) {
            sLogListener.onLog(level, log);
        }
    }

    static int getKbpsByVideoSize(int w, int h) {
        if (w == 320 && h == 240) {
            return 300;
        }
        if (w == 240 && h == 320) {
            return 300;
        }
        if ((w == 640 && h == RTC_DEFAULT_VIDEO_HEIGH) || (w == RTC_DEFAULT_VIDEO_HEIGH && h == 640)) {
            return 800;
        }
        if ((w == 1280 && h == 720) || (w == 720 && h == 1280)) {
            return 2500;
        }
        return ((w == 1920 && h == HD_VIDEO_HEIGHT) || (w == HD_VIDEO_HEIGHT && h == 1920)) ? 10000 : 300;
    }

    public static void setAudioSampleRate(int samplerate) {
        if (samplerate > 0) {
            WebRtcAudioManager.setAudioSampleRate(samplerate);
        }
    }

    public void setRtcCameraDataProcessor(RtcCameraDataProcessor processor) {
        this.processor = processor;
        checkVideoCapturerDataProcessor();
    }

    public void enableFixedVideoResolution(boolean enable) {
        disableResize = enable;
        checkdisableResize(enable);
    }

    public static void setMinVideoKbps(int kbps) {
        if (kbps > 0) {
            configminkbps = kbps;
        } else {
            configminkbps = DEFAULT_CONFIG_MINKBPS;
        }
    }

    public static void setCameraFacing(int id) {
        if (cameraid != id) {
            cameraid = id;
            if (cameraid == 0) {
                enableLocalViewMirror = false;
            } else if (cameraid == 1) {
                enableLocalViewMirror = true;
            }
        }
    }

    public static int getCameraFacing() {
        return cameraid;
    }

    public static void setGlobalVideoCodec(String codec) {
        globalVideoCodec = codec;
    }

    public static void initGlobal(Context context) throws Exception {
        initGlobal(context, false);
    }

    public static void setMediaLogLevel(Medialogmod level) {
        switch ($SWITCH_TABLE$com$superrtc$sdk$RtcConnection$Medialogmod()[level.ordinal()]) {
            case 1:
                medialoglevel = Logging.Severity.LS_SENSITIVE;
                return;
            case 2:
                medialoglevel = Logging.Severity.LS_VERBOSE;
                return;
            case 3:
                medialoglevel = Logging.Severity.LS_INFO;
                return;
            case 4:
                medialoglevel = Logging.Severity.LS_WARNING;
                return;
            case 5:
                medialoglevel = Logging.Severity.LS_ERROR;
                return;
            default:
                return;
        }
    }

    public static void initGlobal(Context context, final boolean useVideoCodecHw2) throws Exception {
        sLogListener.onLog(loglevel, "[rapi]++ initGlobal, useVideoCodecHw=" + useVideoCodecHw2);
        sContext = context;
        executor = new LooperExecutor();
        executor.requestStart();
        useVideoCodecHw = useVideoCodecHw2;
        local_executor = new LooperExecutor();
        local_executor.requestStart();
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.2
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, "[rapi]-- initGlobal, useVideoCodecHw=" + useVideoCodecHw2);
                PeerConnectionFactory.setconfigframerate(20);
                Logging.enableTracing("logcat:", EnumSet.of(Logging.TraceLevel.TRACE_DEFAULT), RtcConnection.medialoglevel);
            }
        });
    }

    public static void createConnectionFactoryInternal(Context context, EGLContext renderEGLContext, boolean videoCodecHwAcceleration) throws Exception {
        PeerConnectionFactory.initializeFieldTrials(null);
        if (!PeerConnectionFactory.initializeAndroidGlobals(context, true, true, videoCodecHwAcceleration)) {
            throw newException("Failed to initializeAndroid");
        }
        factory = new PeerConnectionFactory();
    }

    public String getReportString() throws JSONException {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ getReportString");
        JSONObject obj = new JSONObject();
        synchronized (RtcConnection.class) {
            obj.put("conn", this.connectType);
            obj.put("lvcodec", this.lusevcodectype);
            obj.put("lacodec", this.luseacodectype);
            obj.put("rvcodec", this.rusevcodectype);
            obj.put("racodec", this.ruseacodectype);
            obj.put("sentVB", this.videosendbytes);
            obj.put("sentAB", this.audiosendbytes);
            obj.put("recvVB", this.videorecvbytes);
            obj.put("recvAB", this.audiorecvbytes);
            obj.put("sentVP", this.videolastsendpackets);
            obj.put("sentAP", this.audiolastsendpackets);
            obj.put("recvVP", this.videolastrecvpackets);
            obj.put("recvAP", this.audiolastrecvpackets);
            obj.put("os", "a");
            if (this.remoteGui != null) {
                this.framesReceived = this.remoteGui.getframesReceived();
            }
            obj.put("rvfrm", this.framesReceived);
        }
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]-- getReportString");
        return obj.toString();
    }

    private Parameters getDefaultParameters() {
        return new Parameters(true, false, this.videoWidth, this.videoHeigth, this.videoFramerate, this.videoMaxrate, this.videoCodec, false, 0, this.audioCodec, false, true);
    }

    public RtcConnection(final String name) {
        this.name = "RTC0";
        this.ishangup = false;
        this.name = name;
        setStatsEnable(true);
        this.ishangup = false;
        sLogListener.onLog(loglevel, String.valueOf(name) + "::: [rapi]++ create RtcConnection");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.3
            @Override // java.lang.Runnable
            public void run() {
                if (!RtcConnection.iscreateConnectionFactory) {
                    RtcConnection.iscreateConnectionFactory = true;
                    try {
                        RtcConnection.createConnectionFactoryInternal(RtcConnection.sContext, null, RtcConnection.useVideoCodecHw);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(name) + "::: [rapi]-- create RtcConnection");
            }
        });
    }

    public String getName() {
        return this.name;
    }

    public void setListener(final Listener listerner) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setListener");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.4
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setListener");
                RtcConnection.this.listener = listerner;
            }
        });
    }

    public void clearIceServer() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ clearIceServer");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.5
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- clearIceServer");
                RtcConnection.this.iceServers.clear();
            }
        });
    }

    public void addIceServer(final String uri, final String username, final String password) {
        sLogListener.onLog(loglevel, "[rapi]++ addIceServer");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.6
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- addIceServer");
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: add ice server: " + uri);
                RtcConnection.this.iceServers.add(new PeerConnection.IceServer(uri, username, password));
            }
        });
    }

    public void setViews(VideoViewRenderer localView_, VideoViewRenderer remoteView_) {
        String setviewtype = "";
        if (localView_ != null) {
            setviewtype = String.valueOf(setviewtype) + " localView_ ";
        }
        if (remoteView_ != null) {
            setviewtype = String.valueOf(setviewtype) + " remoteView_ ";
        }
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setViews" + setviewtype);
        this.localViewRender = localView_;
        this.remoteViewRender = remoteView_;
        if (remoteView_ != null) {
            this.remoteGui = remoteView_.getGuiImpl();
        }
        if (localView_ != null) {
            this.localGui = localView_.getGuiImpl();
        }
        if (this.remoteRect == null) {
            this.remoteRect = new RenderRect(0, 0, 100, 100);
        }
        if (this.localRect == null) {
            if (localView_ != remoteView_) {
                this.localRect = new RenderRect(0, 0, 100, 100);
            } else {
                this.localRect = new RenderRect(72, 72, 25, 25);
            }
        }
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.7
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.this.checkAddRenddererInternal();
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setViews");
            }
        });
    }

    private boolean isLocalViewReady() {
        return this.localViewRender != null && this.localViewRender.isViewReady();
    }

    private boolean isRemoteViewReady() {
        return this.remoteViewRender != null && this.remoteViewRender.isViewReady();
    }

    public void switchVideo(final boolean enabled) {
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.8
            @Override // java.lang.Runnable
            public void run() {
                if (RtcConnection.this.peerConnectionParameters != null) {
                    RtcConnection.this.peerConnectionParameters.captureVideo = enabled;
                    RtcConnection.this.checkMediaTracksInternal();
                }
            }
        });
    }

    public void switchAudio(final boolean enabled) {
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.9
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.this.peerConnectionParameters.captureAudio = enabled;
                RtcConnection.this.checkMediaTracksInternal();
            }
        });
    }

    private static String appendStatString(String orgin, String add) {
        return (orgin == null || orgin.equals("")) ? add : String.valueOf(orgin) + "," + add;
    }

    public static void addState(Map<String, String> map, String key, String add) {
        map.put(key, appendStatString(map.get(key), add));
    }

    public static String makeStatLine(Map<String, String> map, String key, String div) {
        return map.get(key) != null ? String.valueOf(div) + key + ": " + map.get(key) + "\r\n" : "";
    }

    public static Map<String, String> convertStatMap(StatsReport.Value[] values) {
        Map<String, String> m = new HashMap<>();
        for (StatsReport.Value value : values) {
            m.put(value.name, value.value);
        }
        return m;
    }

    public void getStats() {
        if (this.peerConnection != null && !this.peerConnection.getStats(new StatsObserver() { // from class: com.superrtc.sdk.RtcConnection.10
            @Override // com.superrtc.call.StatsObserver
            public void onComplete(StatsReport[] reports) {
                Map<String, String> mapCommon = new HashMap<>();
                Map<String, String> mapLocal = new HashMap<>();
                Map<String, String> mapRemote = new HashMap<>();
                Map<String, String> mapLocalstat = new HashMap<>();
                Map<String, String> mapRemotestat = new HashMap<>();
                RtcStatistics rtcStatistics = new RtcStatistics();
                for (StatsReport report : reports) {
                    Map<String, String> m = RtcConnection.convertStatMap(report.values);
                    if (report.type.equals("googCandidatePair")) {
                        if (m.get("googActiveConnection").equals("true")) {
                            String localType = m.get("googLocalCandidateType");
                            String remoteType = m.get("googRemoteCandidateType");
                            if ((localType == null || !localType.equals("relay")) && (remoteType == null || !remoteType.equals("relay"))) {
                                RtcConnection.addState(mapCommon, "connection", "direct");
                                RtcConnection.this.connectType = "direct";
                                rtcStatistics.connectionType = RtcConnection.this.connectType;
                            } else {
                                RtcConnection.addState(mapCommon, "connection", "relay");
                                RtcConnection.this.connectType = "relay";
                                rtcStatistics.connectionType = RtcConnection.this.connectType;
                            }
                        }
                    } else if (report.type.equals("ssrc")) {
                        String codec = m.get("googCodecName");
                        if (m.get("googFrameWidthReceived") != null) {
                            RtcConnection.this.rusevcodectype = codec;
                            String w = m.get("googFrameWidthReceived");
                            String h = m.get("googFrameHeightReceived");
                            mapRemote.put("vcodec", codec);
                            mapRemote.put("vsize", String.valueOf(w) + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + h);
                            mapRemote.put("vfps", m.get("googFrameRateDecoded"));
                            mapRemote.put("vlost", m.get("packetsLost"));
                            mapRemote.put("vbytes", m.get("bytesReceived"));
                            RtcConnection.this.remoteVideoBW.update(m.get("bytesReceived"));
                            RtcConnection.this.videorecvbytes = Integer.parseInt(m.get("bytesReceived"));
                            RtcConnection.this.videocurrecvpackets = Integer.parseInt(m.get("packetsReceived"));
                            int recvpacket = RtcConnection.this.videocurrecvpackets - RtcConnection.this.videolastrecvpackets;
                            RtcConnection.this.videolastrecvpackets = RtcConnection.this.videocurrecvpackets;
                            RtcConnection.this.videocurrecvpacketsLost = Integer.parseInt(m.get("packetsLost"));
                            int recvpacketlost = RtcConnection.this.videocurrecvpacketsLost - RtcConnection.this.videolastrecvpacketsLost;
                            RtcConnection.this.videolastrecvpacketsLost = RtcConnection.this.videocurrecvpacketsLost;
                            RtcConnection.this.recvpacketslostrate.addvideopackslost(recvpacket, recvpacketlost);
                            rtcStatistics.remoteVideoPacketsLostrate = RtcConnection.this.recvpacketslostrate.getvideopackslostrate();
                            mapRemotestat.put("vsize", String.valueOf(w) + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + h);
                            mapRemotestat.put("vfps", m.get("googFrameRateReceived"));
                            mapRemotestat.put("vlost", m.get("packetsLost"));
                            rtcStatistics.remoteWidth = Integer.parseInt(m.get("googFrameWidthReceived"));
                            rtcStatistics.remoteHeight = Integer.parseInt(m.get("googFrameHeightReceived"));
                            rtcStatistics.remoteFps = Integer.parseInt(m.get("googFrameRateReceived"));
                            rtcStatistics.remoteVideoPacketsLost = Integer.parseInt(m.get("packetsLost"));
                        } else if (m.get("googFrameWidthSent") != null) {
                            String w2 = m.get("googFrameWidthSent");
                            String h2 = m.get("googFrameHeightSent");
                            RtcConnection.this.lusevcodectype = codec;
                            mapLocal.put("vcodec", codec);
                            mapLocal.put("vinsize", String.valueOf(m.get("googFrameWidthInput")) + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + m.get("googFrameHeightInput"));
                            mapLocal.put("vsize", String.valueOf(w2) + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + h2);
                            mapLocal.put("vfps", String.valueOf(m.get("googFrameRateInput")) + "/" + m.get("googFrameRateSent"));
                            mapLocal.put("vlost", m.get("packetsLost"));
                            mapLocal.put("vrtt", m.get("googRtt"));
                            mapLocal.put("vbytes", m.get("bytesSent"));
                            RtcConnection.this.localVideoBW.update(m.get("bytesSent"));
                            RtcConnection.this.videosendbytes = Integer.parseInt(m.get("bytesSent"));
                            RtcConnection.this.videocursendpackets = Integer.parseInt(m.get("packetsSent"));
                            int sendpacket = RtcConnection.this.videocursendpackets - RtcConnection.this.videolastsendpackets;
                            RtcConnection.this.videolastsendpackets = RtcConnection.this.videocursendpackets;
                            RtcConnection.this.videocursendpacketsLost = Integer.parseInt(m.get("packetsLost"));
                            int sendpacketlost = RtcConnection.this.videocursendpacketsLost - RtcConnection.this.videolastsendpacketsLost;
                            RtcConnection.this.videolastsendpacketsLost = RtcConnection.this.videocursendpacketsLost;
                            RtcConnection.this.packetslostrate.addvideopackslost(sendpacket, sendpacketlost);
                            rtcStatistics.localVideoPacketsLostrate = RtcConnection.this.packetslostrate.getvideopackslostrate();
                            mapLocalstat.put("vsize", String.valueOf(w2) + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + h2);
                            mapLocalstat.put("vfps", String.valueOf(m.get("googFrameRateInput")) + "/" + m.get("googFrameRateSent"));
                            mapLocalstat.put("vlost", m.get("packetsLost"));
                            mapLocalstat.put("vrtt", m.get("googRtt"));
                            rtcStatistics.localCaptureWidth = Integer.parseInt(m.get("googFrameWidthInput"));
                            rtcStatistics.localCaptureHeight = Integer.parseInt(m.get("googFrameHeightInput"));
                            rtcStatistics.localEncodedWidth = Integer.parseInt(m.get("googFrameWidthSent"));
                            rtcStatistics.localEncodedHeight = Integer.parseInt(m.get("googFrameHeightSent"));
                            rtcStatistics.localCaptureFps = Integer.parseInt(m.get("googFrameRateInput"));
                            rtcStatistics.localEncodedFps = Integer.parseInt(m.get("googFrameRateSent"));
                            rtcStatistics.localVideoPacketsLost = Integer.parseInt(m.get("packetsLost"));
                            rtcStatistics.localVideoRtt = Integer.parseInt(m.get("googRtt"));
                        } else if (m.get("bytesReceived") != null) {
                            RtcConnection.this.ruseacodectype = codec;
                            mapRemote.put("acodec", codec);
                            mapRemote.put("alost", m.get("packetsLost"));
                            mapRemote.put("abytes", m.get("bytesReceived"));
                            RtcConnection.this.remoteAudioBW.update(m.get("bytesReceived"));
                            RtcConnection.this.audiorecvbytes = Integer.parseInt(m.get("bytesReceived"));
                            RtcConnection.this.audiocurrecvpackets = Integer.parseInt(m.get("packetsReceived"));
                            int recvpacket2 = RtcConnection.this.audiocurrecvpackets - RtcConnection.this.audiolastrecvpackets;
                            RtcConnection.this.audiolastrecvpackets = RtcConnection.this.audiocurrecvpackets;
                            RtcConnection.this.audiocurrecvpacketsLost = Integer.parseInt(m.get("packetsLost"));
                            int recvpacketlost2 = RtcConnection.this.audiocurrecvpacketsLost - RtcConnection.this.audiolastrecvpacketsLost;
                            RtcConnection.this.audiolastrecvpacketsLost = RtcConnection.this.audiocurrecvpacketsLost;
                            RtcConnection.this.recvpacketslostrate.addaudiopackslost(recvpacket2, recvpacketlost2);
                            rtcStatistics.remoteAudioPacketsLostrate = RtcConnection.this.recvpacketslostrate.getaudiopackslostrate();
                            mapRemotestat.put("alost", m.get("packetsLost"));
                            mapRemotestat.put("artt", m.get("googRtt"));
                            rtcStatistics.remoteAudioPacketsLost = Integer.parseInt(m.get("packetsLost"));
                        } else if (m.get("bytesSent") != null) {
                            RtcConnection.this.luseacodectype = codec;
                            mapLocal.put("acodec", codec);
                            mapLocal.put("alost", m.get("packetsLost"));
                            mapLocal.put("abytes", m.get("bytesSent"));
                            RtcConnection.this.localAudioBW.update(m.get("bytesSent"));
                            mapLocalstat.put("alost", m.get("packetsLost"));
                            mapLocalstat.put("abytes", m.get("bytesSent"));
                            mapLocalstat.put("artt", m.get("googRtt"));
                            RtcConnection.this.audiosendbytes = Integer.parseInt(m.get("bytesSent"));
                            RtcConnection.this.audiocursendpackets = Integer.parseInt(m.get("packetsSent"));
                            int sendpacket2 = RtcConnection.this.audiocursendpackets - RtcConnection.this.audiolastsendpackets;
                            RtcConnection.this.audiolastsendpackets = RtcConnection.this.audiocursendpackets;
                            RtcConnection.this.audiocursendpacketsLost = Integer.parseInt(m.get("packetsLost"));
                            int sendpacketlost2 = RtcConnection.this.audiocursendpacketsLost - RtcConnection.this.audiolastsendpacketsLost;
                            RtcConnection.this.audiolastsendpacketsLost = RtcConnection.this.audiocursendpacketsLost;
                            RtcConnection.this.packetslostrate.addaudiopackslost(sendpacket2, sendpacketlost2);
                            rtcStatistics.localAudioPacketsLostrate = RtcConnection.this.packetslostrate.getaudiopackslostrate();
                            rtcStatistics.localAudioRtt = Integer.parseInt(m.get("googRtt"));
                            rtcStatistics.localAudioPacketsLost = Integer.parseInt(m.get("packetsLost"));
                        }
                    } else if (report.type.equals("VideoBwe")) {
                        mapLocal.put("vbr", String.valueOf(m.get("googActualEncBitrate")) + "/" + m.get("googTargetEncBitrate"));
                        mapLocalstat.put("vbr", String.valueOf(Integer.parseInt(m.get("googActualEncBitrate")) / 1000) + "/" + (Integer.parseInt(m.get("googTargetEncBitrate")) / 1000));
                        rtcStatistics.localVideoActualBps = Integer.parseInt(m.get("googActualEncBitrate")) / 1000;
                        rtcStatistics.localVideoTargetBps = Integer.parseInt(m.get("googTargetEncBitrate")) / 1000;
                    }
                }
                if (mapCommon.get("connection") == null) {
                    mapCommon.put("connection", "disconnect");
                }
                String stat = String.valueOf("") + RtcConnection.makeStatLine(mapCommon, "connection", "");
                String avstat = String.valueOf("") + RtcConnection.makeStatLine(mapCommon, "connection", "");
                if (RtcConnection.this.cpuMonitor.sampleCpuUtilization()) {
                    stat = String.valueOf(stat) + "CPU%: " + RtcConnection.this.cpuMonitor.getCpuCurrent() + "/" + RtcConnection.this.cpuMonitor.getCpuAvg3() + "/" + RtcConnection.this.cpuMonitor.getCpuAvgAll();
                }
                if (mapLocal.size() > 0) {
                    mapLocal.put("vbps", String.valueOf(RtcConnection.this.localVideoBW.getBitrateString()));
                    mapLocal.put("abps", String.valueOf(RtcConnection.this.localAudioBW.getBitrateString()));
                    mapLocalstat.put("abps", String.valueOf(RtcConnection.this.localAudioBW.getBitrateString()));
                    rtcStatistics.localAudioBps = Integer.parseInt(RtcConnection.this.localAudioBW.getBitrateString());
                    stat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(stat) + "\r\n") + RtcConnection.makeStatLine(mapLocal, "vcodec", "")) + RtcConnection.makeStatLine(mapLocal, "vinsize", "")) + RtcConnection.makeStatLine(mapLocal, "vsize", "")) + RtcConnection.makeStatLine(mapLocal, "vfps", "")) + RtcConnection.makeStatLine(mapLocal, "vrtt", "")) + RtcConnection.makeStatLine(mapLocal, "vlost", "")) + RtcConnection.makeStatLine(mapLocal, "vbytes", "")) + RtcConnection.makeStatLine(mapLocal, "vbps", "")) + RtcConnection.makeStatLine(mapLocal, "vbr", "")) + RtcConnection.makeStatLine(mapLocal, "acodec", "")) + RtcConnection.makeStatLine(mapLocal, "alost", "")) + RtcConnection.makeStatLine(mapLocal, "abytes", "")) + RtcConnection.makeStatLine(mapLocal, "abps", "");
                    avstat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(avstat) + RtcConnection.makeStatLine(mapLocal, "vcodec", "")) + RtcConnection.makeStatLine(mapLocalstat, "vsize", "")) + RtcConnection.makeStatLine(mapLocalstat, "vfps", "")) + RtcConnection.makeStatLine(mapLocalstat, "vrtt", "")) + RtcConnection.makeStatLine(mapLocalstat, "vlost", "")) + "vlostrate: " + rtcStatistics.localVideoPacketsLostrate + "\r\n") + RtcConnection.makeStatLine(mapLocalstat, "vbr", "")) + RtcConnection.makeStatLine(mapLocalstat, "alost", "")) + "alostrate: " + rtcStatistics.localAudioPacketsLostrate + "\r\n") + RtcConnection.makeStatLine(mapLocalstat, "artt", "")) + RtcConnection.makeStatLine(mapLocalstat, "abps", "");
                }
                if (mapRemote.size() > 0) {
                    mapRemote.put("vbps", String.valueOf(RtcConnection.this.remoteVideoBW.getBitrateString()));
                    mapRemote.put("abps", String.valueOf(RtcConnection.this.remoteAudioBW.getBitrateString()));
                    mapRemotestat.put("vbps", String.valueOf(RtcConnection.this.remoteVideoBW.getBitrateString()));
                    mapRemotestat.put("abps", String.valueOf(RtcConnection.this.remoteAudioBW.getBitrateString()));
                    rtcStatistics.remoteVideoBps = Integer.parseInt(RtcConnection.this.remoteVideoBW.getBitrateString());
                    rtcStatistics.remoteAudioBps = Integer.parseInt(RtcConnection.this.remoteAudioBW.getBitrateString());
                    String str = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(stat) + "\r\n") + "remote:\r\n") + RtcConnection.makeStatLine(mapRemote, "vcodec", "")) + RtcConnection.makeStatLine(mapRemote, "vsize", "")) + RtcConnection.makeStatLine(mapRemote, "vfps", "")) + RtcConnection.makeStatLine(mapRemote, "vlost", "")) + RtcConnection.makeStatLine(mapRemote, "vbytes", "")) + RtcConnection.makeStatLine(mapRemote, "vbps", "")) + RtcConnection.makeStatLine(mapRemote, "acodec", "")) + RtcConnection.makeStatLine(mapRemote, "alost", "")) + RtcConnection.makeStatLine(mapRemote, "abytes", "")) + RtcConnection.makeStatLine(mapRemote, "abps", "");
                    avstat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(avstat) + RtcConnection.makeStatLine(mapRemotestat, "vcodec", "")) + RtcConnection.makeStatLine(mapRemotestat, "vsize", "")) + RtcConnection.makeStatLine(mapRemotestat, "vfps", "")) + RtcConnection.makeStatLine(mapRemotestat, "vlost", "")) + "vlostrate: " + rtcStatistics.remoteVideoPacketsLostrate + "\r\n") + RtcConnection.makeStatLine(mapRemotestat, "vbps", "")) + RtcConnection.makeStatLine(mapRemotestat, "alost", "")) + "alostrate: " + rtcStatistics.remoteAudioPacketsLostrate + "\r\n") + RtcConnection.makeStatLine(mapRemotestat, "abps", "");
                }
                rtcStatistics.fullStatsString = avstat;
                if (!RtcConnection.this.ishangup) {
                    RtcConnection.this.listener.onStats(rtcStatistics);
                }
            }
        }, null)) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: getStats() returns false!");
        }
    }

    public void setStatsEnable(boolean enable) {
        if (enable) {
            try {
                this.statsTimer.schedule(new TimerTask() { // from class: com.superrtc.sdk.RtcConnection.11
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        RtcConnection.local_executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.11.1
                            @Override // java.lang.Runnable
                            public void run() {
                                RtcConnection.this.getStats();
                            }
                        });
                    }
                }, 0L, 1000L);
            } catch (Exception e) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Can not schedule statistics timer " + e);
            }
        } else {
            this.statsTimer.cancel();
        }
    }

    public void stopRenderer(final VideoViewRenderer localView_, final VideoViewRenderer remoteView_) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ stopRenderer");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.12
            @Override // java.lang.Runnable
            public void run() {
                if (!(remoteView_ == null || RtcConnection.this.remoteViewRender != remoteView_ || RtcConnection.this.remoteVideoTrack == null || !RtcConnection.this.remoteRendererAdded || RtcConnection.this.remotevideorender == null)) {
                    RtcConnection.this.remoteRendererAdded = false;
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: stop remote Renderer");
                    RtcConnection.this.remoteVideoTrack.removeRenderer(RtcConnection.this.remotevideorender);
                    RtcConnection.this.remoteRect = null;
                    RtcConnection.this.remotevideorender = null;
                    RtcConnection.this.remoteRender = null;
                    RtcConnection.this.remoteViewRender = null;
                    RtcConnection.this.remoteGui = null;
                }
                if (!(localView_ == null || RtcConnection.this.localViewRender != localView_ || RtcConnection.this.localVideoTrack == null || !RtcConnection.this.localRendererAdded || RtcConnection.this.localvideorender == null)) {
                    RtcConnection.this.localRendererAdded = false;
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: stop local renderer");
                    RtcConnection.this.localVideoTrack.removeRenderer(RtcConnection.this.localvideorender);
                    RtcConnection.this.localRect = null;
                    RtcConnection.this.localvideorender = null;
                    RtcConnection.this.localRender = null;
                    RtcConnection.this.localViewRender = null;
                    RtcConnection.this.localGui = null;
                }
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- stopRenderer");
            }
        });
    }

    public void addRenderer(final VideoViewRenderer localView_, final VideoViewRenderer remoteView_) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ addRenderer");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.13
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.this.remoteViewRender = remoteView_;
                RtcConnection.this.localViewRender = localView_;
                if (remoteView_ != null) {
                    RtcConnection.this.remoteGui = remoteView_.getGuiImpl();
                }
                if (localView_ != null) {
                    RtcConnection.this.localGui = localView_.getGuiImpl();
                }
                if (RtcConnection.this.remoteRect == null) {
                    RtcConnection.this.remoteRect = new RenderRect(0, 0, 100, 100);
                }
                if (RtcConnection.this.localRect == null) {
                    if (localView_ != remoteView_) {
                        RtcConnection.this.localRect = new RenderRect(0, 0, 100, 100);
                    } else {
                        RtcConnection.this.localRect = new RenderRect(72, 72, 25, 25);
                    }
                }
                RtcConnection.this.checkAddRenddererInternal();
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- addRenderer");
            }
        });
    }

    public void changeRenderer(VideoViewRenderer localView_, VideoViewRenderer remoteView_) {
        stopRenderer(this.localViewRender, this.remoteViewRender);
        addRenderer(localView_, remoteView_);
    }

    public void setAspectMode(final AspectMode Localaspectmode, final AspectMode Remoteaspectmode) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setAspectMode Localaspectmode::" + Localaspectmode + ",Remoteaspectmode::" + Remoteaspectmode);
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.14
            @Override // java.lang.Runnable
            public void run() {
                if (Localaspectmode == AspectMode.AspectFill) {
                    RtcConnection.this.LocalscalingType = RendererCommon.ScalingType.SCALE_ASPECT_FILL;
                } else if (Localaspectmode == AspectMode.AspectFit) {
                    RtcConnection.this.LocalscalingType = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
                }
                if (Remoteaspectmode == AspectMode.AspectFill) {
                    RtcConnection.this.RemotescalingType = RendererCommon.ScalingType.SCALE_ASPECT_FILL;
                } else if (Remoteaspectmode == AspectMode.AspectFit) {
                    RtcConnection.this.RemotescalingType = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
                }
                RtcConnection.this.updateVideoViewInternal();
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setAspectMode");
            }
        });
    }

    public void checkAddRenddererInternal() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: checkAddRenddererInternal: => local=[" + this.localRender + ", " + this.localVideoTrack + "], remote=[" + this.remoteRender + ", " + this.remoteVideoTrack + "]");
        if (isRemoteViewReady()) {
            if (this.remoteRender == null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: create remote renderer");
                this.remoteRender = this.remoteGui.create(this.remoteRect.x, this.remoteRect.y, this.remoteRect.width, this.remoteRect.height, this.RemotescalingType, false);
            }
            if (!(this.remoteVideoTrack == null || this.remoteRendererAdded || this.remoteRender == null)) {
                this.remoteRendererAdded = true;
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: add remote renderer");
                this.remotevideorender = new VideoRenderer(this.remoteRender);
                this.remoteVideoTrack.addRenderer(this.remotevideorender);
            }
        }
        if (isLocalViewReady()) {
            if (this.localRender == null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: create local renderer. Mirror::" + enableLocalViewMirror);
                this.localRender = this.localGui.create(this.localRect.x, this.localRect.y, this.localRect.width, this.localRect.height, this.LocalscalingType, enableLocalViewMirror);
            }
            if (this.localVideoTrack != null && !this.localRendererAdded && this.localRender != null) {
                this.localRendererAdded = true;
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: add local renderer");
                this.localvideorender = new VideoRenderer(this.localRender);
                this.localVideoTrack.addRenderer(this.localvideorender);
            }
        }
    }

    public void updateVideoViewInternal() {
        if (this.remoteRender != null) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: update remote renderer,RemotescalingType::" + this.RemotescalingType);
            if (this.remoteGui != null) {
                this.remoteGui.update(this.remoteRender, this.remoteRect.x, this.remoteRect.y, this.remoteRect.width, this.remoteRect.height, this.RemotescalingType, false);
            }
        }
        if (this.localRender != null && this.localGui != null) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: update local renderer,LocalscalingType::" + this.LocalscalingType);
            this.localGui.update(this.localRender, this.localRect.x, this.localRect.y, this.localRect.width, this.localRect.height, this.LocalscalingType, enableLocalViewMirror);
        }
    }

    public void updateVideoViewmirror(boolean mirror) {
        if (this.localRender != null && this.localGui != null) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: update local renderer,LocalscalingType::" + this.LocalscalingType + " mirror:" + mirror);
            this.localGui.update(this.localRender, this.localRect.x, this.localRect.y, this.localRect.width, this.localRect.height, this.LocalscalingType, mirror);
        }
    }

    public void setIceServer(final String url, final String username, final String password) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setIceServer");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.15
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("url", url);
                    obj.put("username", username);
                    obj.put(RtcConnection.RtcConstStringCredential, password);
                    JSONArray array = new JSONArray();
                    array.put(obj);
                    JSONObject root = new JSONObject();
                    root.put(RtcConnection.RtcKVIceServersArray, array);
                    RtcConnection.this.setConfigureInternal(root.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: excpetion:" + e.getMessage());
                }
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setIceServer");
            }
        });
    }

    public void setConfigure(final String configJson) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setConfigure");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.16
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.this.setConfigureInternal(configJson);
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setConfigure");
            }
        });
    }

    public void setConfigureInternal(String configJson) {
        try {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: start setConfigure: config=" + configJson);
            JSONObject dataJson = new JSONObject(configJson);
            if (dataJson.has(RtcKVCaptureVideoBoolean)) {
                this.videoCallEnabled = dataJson.getBoolean(RtcKVCaptureVideoBoolean);
            }
            if (dataJson.has(RtcKVRecvVideoBoolean)) {
                this.videoreceiveEnabled = dataJson.getBoolean(RtcKVRecvVideoBoolean);
            }
            if (dataJson.has(RtcKVPreferVCodecString)) {
                this.videoCodec = dataJson.getString(RtcKVPreferVCodecString);
            }
            if (dataJson.has("width")) {
                this.videoWidth = dataJson.getInt("width");
                if (this.isconnected && dataJson.has(RtcvideoheigthLong)) {
                    this.videoHeigth = dataJson.getInt(RtcvideoheigthLong);
                    if (!(this.videoWidth == 0 || this.videoHeigth == 0)) {
                        changeVideoResolution(this.videoWidth, this.videoHeigth);
                    }
                }
            }
            if (dataJson.has(RtcvideoheigthLong)) {
                this.videoHeigth = dataJson.getInt(RtcvideoheigthLong);
            }
            if (dataJson.has(RtcvideofpsLong)) {
                this.videoFramerate = dataJson.getInt(RtcvideofpsLong);
            }
            if (dataJson.has(RtcKVMaxVideoKbpsLong)) {
                this.videoMaxrate = dataJson.getInt(RtcKVMaxVideoKbpsLong);
                if (this.videoMaxrate < 0) {
                    this.videoMaxrate = 0;
                }
            }
            if (dataJson.has(RtcKVRelayVideoKbpsLong)) {
                this.relayvideoMaxrate = dataJson.getInt(RtcKVRelayVideoKbpsLong);
            }
            if (dataJson.has(RtcKVCaptureAudioBoolean)) {
                this.audioCallEnabled = dataJson.getBoolean(RtcKVCaptureAudioBoolean);
            }
            if (dataJson.has(RtcKVRecvAudioBoolean)) {
                this.audioreceiveEnabled = dataJson.getBoolean(RtcKVRecvAudioBoolean);
            }
            if (dataJson.has(RtcKVPreferACodecString)) {
                this.audioCodec = dataJson.getString(RtcKVPreferACodecString);
            }
            if (dataJson.has(RtcKVMaxAudioKbpsLong)) {
                this.audioMaxrate = dataJson.getInt(RtcKVMaxAudioKbpsLong);
            }
            if (dataJson.has(RtcKVRelayAudioKbpsLong)) {
                this.relayaudioMaxrate = dataJson.getInt(RtcKVRelayAudioKbpsLong);
            }
            if (dataJson.has(RtcKVDisablePranswerBoolean)) {
                this.disablePranswer = dataJson.getBoolean(RtcKVDisablePranswerBoolean);
            }
            if (dataJson.has(RtcKVRelayOnlyBoolean)) {
                this.enableRelay = dataJson.getBoolean(RtcKVRelayOnlyBoolean);
            }
            if (dataJson.has(RtcHDVideoEncoderBoolean)) {
                this.enableHWEncoder = dataJson.getBoolean(RtcHDVideoEncoderBoolean);
            }
            if (dataJson.has(RtcHDVideoDecoderBoolean)) {
                this.enableHWDecoder = dataJson.getBoolean(RtcHDVideoDecoderBoolean);
            }
            JSONArray serverArray = dataJson.optJSONArray(RtcKVIceServersArray);
            if (serverArray != null) {
                this.iceServers.clear();
                for (int i = 0; i < serverArray.length(); i++) {
                    JSONObject obj = serverArray.getJSONObject(i);
                    this.iceServers.add(new PeerConnection.IceServer(obj.optString("url"), obj.optString("username"), obj.optString(RtcConstStringCredential)));
                }
            }
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: finish setConfigure");
        } catch (JSONException e) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: setConfigure failed");
            e.printStackTrace();
        }
    }

    public void createOffer() {
        createOffer(null);
    }

    public void capturePicture(String filepath) {
    }

    public void startRecord(String dir) {
    }

    public String stopRecord() {
        return null;
    }

    public void createOffer(Parameters parameters) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ createOffer");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.17
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.this.createConnectionInternal(true);
                RtcConnection.this.peerConnection.createOffer(RtcConnection.this.localSDPObserver, RtcConnection.this.sdpMediaConstraints);
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- createOffer");
            }
        });
    }

    private void checkdisableResize(final boolean enable) {
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.18
            @Override // java.lang.Runnable
            public void run() {
                if (RtcConnection.this.peerConnection != null) {
                    RtcConnection.this.peerConnection.setdisableResize(enable);
                }
            }
        });
    }

    public void changeVideoResolution(int width, int height) {
        if (!this.videoCallEnabled || this.videoCapturer == null) {
            Log.e(TAG, "Failed to change capture format. Video: " + this.videoCallEnabled + ". Error : ");
            return;
        }
        this.videoCapturer.onOutputFormatRequest(width, height, 15);
        this.videoCapturer.changeCaptureFormat(width, height, 15);
    }

    public void SetMaxSendBandwidth(final int maxkbps) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ SetMaxSendBandwidth::" + maxkbps);
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.19
            @Override // java.lang.Runnable
            public void run() {
                if (RtcConnection.this.peerConnection != null) {
                    RtcConnection.this.peerConnection.SetMaxSendBandwidth(maxkbps * 1000);
                }
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- SetMaxSendBandwidth");
            }
        });
    }

    public void setMaxVideoFrameRate(final int fps) {
        configfps = fps;
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ SetMaxSendBandwidth fps:" + fps);
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.20
            @Override // java.lang.Runnable
            public void run() {
                if (fps > 0) {
                    PeerConnectionFactory.setconfigframerate(fps);
                } else {
                    PeerConnectionFactory.setconfigframerate(20);
                }
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- SetMaxSendBandwidth");
            }
        });
    }

    public void answer() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ answer");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.21
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]++ answer run");
                if (RtcConnection.this.isPranswerState || RtcConnection.this.disablePranswer) {
                    RtcConnection.this.isPranswerState = false;
                    RtcConnection.this.checkMediaTracksInternal();
                    RtcConnection.this.peerConnection.createAnswer(RtcConnection.this.localSDPObserver, RtcConnection.this.sdpMediaConstraints);
                    if (RtcConnection.this.iceConnectionState == PeerConnection.IceConnectionState.CONNECTED) {
                    }
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- answer");
                    return;
                }
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: accpet: NOT pranswer state");
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- answer");
            }
        });
    }

    public void createConnectionInternal(boolean initiator) {
        boolean z;
        this.isInitiator = initiator;
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: isInitiator => " + this.isInitiator);
        if (this.peerConnectionParameters == null) {
            this.peerConnectionParameters = getDefaultParameters();
            if (this.autoAddVideo) {
                this.peerConnectionParameters.captureVideo = this.autoAddVideo;
                this.peerConnectionParameters.receiveVideo = this.autoAddVideo;
            } else {
                this.peerConnectionParameters.captureVideo = this.videoCallEnabled;
                this.peerConnectionParameters.receiveVideo = this.videoreceiveEnabled;
            }
            this.peerConnectionParameters.captureAudio = this.audioCallEnabled;
            this.peerConnectionParameters.receiveAudio = this.audioreceiveEnabled;
            this.peerConnectionParameters.videoCallEnabled = this.videoCallEnabled;
            if (this.videoCodec == null) {
                this.videoCodec = RtcConstStringVP8;
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: use default video codec " + this.videoCodec);
            }
            this.peerConnectionParameters.videoCodec = this.videoCodec;
            if (this.videoWidth == 0 || this.videoHeigth == 0) {
                this.videoWidth = 640;
                this.videoHeigth = RTC_DEFAULT_VIDEO_HEIGH;
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: use default video size " + this.videoWidth + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + this.videoHeigth);
            }
            this.peerConnectionParameters.videoWidth = this.videoWidth;
            this.peerConnectionParameters.videoHeight = this.videoHeigth;
            if (this.videoMaxrate == 0) {
                this.videoMaxrate = getKbpsByVideoSize(this.videoWidth, this.videoHeigth);
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: use default maxVideoKbps " + this.videoMaxrate);
            }
            if (this.relayvideoMaxrate == 0) {
                this.relayvideoMaxrate = getKbpsByVideoSize(this.videoWidth, this.videoHeigth);
            }
            this.peerConnectionParameters.videoFps = this.videoFramerate;
            if (this.audioCodec == null) {
                this.audioCodec = "OPUS";
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: use default audio codec " + this.audioCodec);
            }
            this.peerConnectionParameters.audioCodec = this.audioCodec;
        }
        if (!this.videoCallEnabled || !this.peerConnectionParameters.renderLocal) {
            z = false;
        } else {
            z = true;
        }
        this.renderVideo = z;
        this.renderVideo = true;
        createMediaConstraintsInternal();
        createPeerConnectionInternal();
    }

    private static SessionDescription content2Sdp(RtcContent sc) {
        if (sc.type.equalsIgnoreCase(RtcContent.TYPE_OFFER) || sc.type.equalsIgnoreCase(RtcContent.TYPE_ANSWER) || sc.type.equalsIgnoreCase(RtcContent.TYPE_PRANSWER)) {
            return new SessionDescription(SessionDescription.Type.valueOf(sc.type.toUpperCase()), sc.sdp);
        }
        return null;
    }

    private IceCandidate content2Candidatee(RtcContent sc) {
        if (sc.type.equalsIgnoreCase("candidate")) {
            return new IceCandidate("audio", sc.mlineindex, sc.candidate);
        }
        return null;
    }

    private void addPendingSdp(SessionDescription sdp) {
        synchronized (this.pendingSdp) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: add pending sdp");
            this.pendingSdp.addLast(sdp);
        }
    }

    private SessionDescription removePendingSdp() {
        synchronized (this.pendingSdp) {
            if (this.pendingSdp.isEmpty()) {
                return null;
            }
            return this.pendingSdp.removeFirst();
        }
    }

    public void processPendingSdpInternal() {
        while (true) {
            SessionDescription sdp = removePendingSdp();
            if (sdp != null) {
                if (sdp.type == SessionDescription.Type.OFFER) {
                    if (this.isInitiator) {
                        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: got offer but isInitiator, drop it");
                    } else {
                        if (this.peerConnection == null) {
                            createConnectionInternal(false);
                        }
                        if (this.peerConnection.signalingState() != PeerConnection.SignalingState.STABLE) {
                            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: got offer at wrong state " + this.peerConnection.signalingState());
                        } else if (this.peerConnection.getRemoteDescription() == null) {
                            this.isPranswerState = true;
                            setRemoteSdpInternal(sdp);
                            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: create PRANSWER : sdpMediaConstraints=" + this.sdpMediaConstraints);
                            this.peerConnection.createAnswer(this.localSDPObserver, this.sdpMediaConstraints);
                        } else {
                            this.isPranswerState = false;
                            setRemoteSdpInternal(sdp);
                            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: create ANSWER : sdpMediaConstraints=" + this.sdpMediaConstraints);
                            this.peerConnection.createAnswer(this.localSDPObserver, this.sdpMediaConstraints);
                        }
                    }
                } else if (sdp.type != SessionDescription.Type.PRANSWER && sdp.type != SessionDescription.Type.ANSWER) {
                    sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: unknown sdp type " + sdp.type);
                } else if (!this.isInitiator) {
                    sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: got " + sdp.type + " but NOT isInitiator, drop it " + this.isInitiator);
                } else if (this.peerConnection == null) {
                    sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: got " + sdp.type + " but NOT pc null, drop it");
                } else {
                    if (this.iceConnectionState == PeerConnection.IceConnectionState.CONNECTED) {
                    }
                    if (this.peerConnection.signalingState() == PeerConnection.SignalingState.HAVE_LOCAL_OFFER || this.peerConnection.signalingState() == PeerConnection.SignalingState.HAVE_REMOTE_PRANSWER) {
                        setRemoteSdpInternal(sdp);
                    } else {
                        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: got " + sdp.type + " at wrong state " + this.peerConnection.signalingState());
                    }
                }
            } else {
                return;
            }
        }
    }

    public String setRemoteJson(String json) throws Exception {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: remote json: " + json);
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setRemoteJson");
        RtcContent sc = RtcContent.fromJson(json);
        SessionDescription sdp = content2Sdp(sc);
        if (sdp != null) {
            addPendingSdp(sdp);
            executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.22
                @Override // java.lang.Runnable
                public void run() {
                    RtcConnection.this.processPendingSdpInternal();
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setRemoteJson remote sdp");
                }
            });
            return sc.type;
        }
        final IceCandidate remoteCandidate = content2Candidatee(sc);
        if (remoteCandidate != null) {
            executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.23
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection != null) {
                        RtcConnection.this.peerConnection.addIceCandidate(remoteCandidate);
                        RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setRemoteJson remote candidate");
                        return;
                    }
                    synchronized (RtcConnection.this.pendingCandidates) {
                        RtcConnection.this.pendingCandidates.add(remoteCandidate);
                    }
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setRemoteJson remote candidate");
                }
            });
            return sc.type;
        }
        throw new Exception("unknown type " + sc.type);
    }

    public String setRemoteCandidate(String json) throws Exception {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setRemoteCandidate");
        RtcContent sc = RtcContent.fromJson(json);
        final IceCandidate remoteCandidate = content2Candidatee(sc);
        if (remoteCandidate != null) {
            executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.24
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection != null) {
                        RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: add remoteCandidate:: " + remoteCandidate.toString());
                        RtcConnection.this.peerConnection.addIceCandidate(remoteCandidate);
                        RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setRemoteCandidate");
                        return;
                    }
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: add pending candidate");
                    synchronized (RtcConnection.this.pendingCandidates) {
                        RtcConnection.this.pendingCandidates.add(remoteCandidate);
                    }
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setRemoteCandidate");
                }
            });
            return sc.type;
        }
        throw new Exception("unknown type " + sc.type);
    }

    public String setRemoteSdp(String json) throws Exception {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setRemoteSdp");
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: setRemoteSdp: " + json);
        RtcContent sc = RtcContent.fromJson(json);
        SessionDescription sdp = content2Sdp(sc);
        if (sdp != null) {
            addPendingSdp(sdp);
            executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.25
                @Override // java.lang.Runnable
                public void run() {
                    RtcConnection.this.processPendingSdpInternal();
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setRemoteSdp");
                }
            });
            return sc.type;
        }
        throw new Exception("unknown type " + sc.type);
    }

    public String inverseSdpJson(String json) throws Exception {
        RtcContent sc = RtcContent.fromJson(json);
        if (sc.type.equalsIgnoreCase(RtcContent.TYPE_OFFER)) {
            return RtcContent.SDP2Json(new SessionDescription(SessionDescription.Type.valueOf(RtcContent.TYPE_ANSWER.toUpperCase()), sc.sdp), getLocalSeq(), "connectionId");
        }
        if (sc.type.equalsIgnoreCase(RtcContent.TYPE_ANSWER)) {
            return RtcContent.SDP2Json(new SessionDescription(SessionDescription.Type.valueOf(RtcContent.TYPE_OFFER.toUpperCase()), sc.sdp), getLocalSeq(), "connectionId");
        }
        return json;
    }

    public void printRemoteStream(String prefix) {
        if (this.remoteStream != null) {
            if (this.remoteStream.videoTracks != null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: " + prefix + " remote stream video tracks = " + this.remoteStream.videoTracks.size());
                if (this.remoteStream.videoTracks.size() > 0) {
                    sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: " + prefix + " remote stream video track[0] state = " + this.remoteStream.videoTracks.get(0).state());
                    return;
                }
                return;
            }
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: " + prefix + " remote stream video tracks is null");
        }
    }

    private void setRemoteSdpInternal(SessionDescription sdp) {
        String sdpDescription;
        if (this.peerConnection != null) {
            String sdpDescription2 = sdp.description;
            if (this.enableRelay) {
                sdpDescription = sdpDescription2.replaceAll("a=mid:audio\r", "a=mid:audio\r\nb=AS:" + this.relayaudioMaxrate + "\r").replaceAll("a=mid:video\r", "a=mid:video\r\nb=AS:" + this.relayvideoMaxrate + "\r");
            } else {
                sdpDescription = sdpDescription2.replaceAll("a=mid:audio\r", "a=mid:audio\r\nb=AS:" + this.audioMaxrate + "\r").replaceAll("a=mid:video\r", "a=mid:video\r\nb=AS:" + this.videoMaxrate + "\r");
            }
            SessionDescription sdpRemote = new SessionDescription(sdp.type, sdpDescription);
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Set remote SDP => " + sdpRemote.type);
            try {
                this.peerConnection.setRemoteDescription(this.remoteSDPObserver, sdpRemote);
                if (this.remoteStream != null) {
                    printRemoteStream("p1");
                    if (this.remoteVideoTrack != null && this.remoteVideoTrack.state() == MediaStreamTrack.State.ENDED) {
                        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: remote remote video track");
                        printRemoteStream("p2");
                    }
                }
            } catch (Throwable x) {
                x.printStackTrace();
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Set remote SDP exception " + x.getMessage());
            }
        }
    }

    public void drainCandidatesInteranl() {
        if (this.peerConnection != null) {
            synchronized (this.pendingCandidates) {
                for (IceCandidate candidate : this.pendingCandidates) {
                    this.peerConnection.addIceCandidate(candidate);
                }
                this.pendingCandidates.clear();
            }
        }
    }

    public long getLocalSeq() {
        long j;
        synchronized (this) {
            this.localSeq++;
            j = this.localSeq;
        }
        return j;
    }

    /* loaded from: classes2.dex */
    public static class RtcContent {
        private static final String TYPE_CANDIDATE = "candidate";
        String candidate;
        int mlineindex;
        String sdp;
        long seq;
        String type;
        private static final String TYPE_OFFER = SessionDescription.Type.OFFER.name();
        private static final String TYPE_ANSWER = SessionDescription.Type.ANSWER.name();
        private static final String TYPE_PRANSWER = SessionDescription.Type.PRANSWER.name();

        private RtcContent() {
        }

        public boolean isSdp() {
            if (this.type == null) {
                return false;
            }
            if (this.type.equalsIgnoreCase(TYPE_OFFER) || this.type.equalsIgnoreCase(TYPE_ANSWER) || this.type.equalsIgnoreCase(TYPE_PRANSWER)) {
                return true;
            }
            return false;
        }

        public static RtcContent fromJson(String json) throws JSONException {
            RtcContent c = new RtcContent();
            JSONObject rootobj = (JSONObject) new JSONTokener(json).nextValue();
            c.type = rootobj.optString("type");
            c.mlineindex = rootobj.optInt("mlineindex", -1);
            c.candidate = rootobj.optString(TYPE_CANDIDATE, null);
            c.sdp = rootobj.optString("sdp", null);
            c.seq = rootobj.optLong("seq", -1L);
            return c;
        }

        protected static String candidate2Json(IceCandidate cand, long seq, String connectionId) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("type", TYPE_CANDIDATE);
                obj.put("mlineindex", cand.sdpMLineIndex);
                obj.put(DeviceInfo.TAG_MID, cand.sdpMid);
                obj.put(TYPE_CANDIDATE, cand.sdp);
                obj.put("connId", connectionId);
                obj.put("seq", seq);
                return obj.toString();
            } catch (JSONException e) {
                ALog.i(RtcConnection.TAG, "Exception: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        protected static String SDP2Json(SessionDescription sdp, long seq, String connectionId) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("type", sdp.type.toString());
                obj.put("sdp", sdp.description);
                obj.put("seq", seq);
                obj.put("connId", connectionId);
                return obj.toString();
            } catch (JSONException e) {
                ALog.i(RtcConnection.TAG, "Exception: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

    public void reportError(final String errorMessage) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: rtc reportError: " + errorMessage);
        local_executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.26
            @Override // java.lang.Runnable
            public void run() {
                if (!RtcConnection.this.ishangup) {
                    RtcConnection.this.listener.onError(errorMessage);
                }
            }
        });
    }

    /* loaded from: classes2.dex */
    public static class RenderRect {
        int height;
        int width;
        int x;
        int y;

        public RenderRect(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private static Exception newException(String errMsg) {
        ALog.i(TAG, errMsg);
        return new Exception(errMsg);
    }

    private static void slog(String msg) {
        ALog.i(TAG, msg);
    }

    private static void slogw(String msg) {
        ALog.i(TAG, msg);
    }

    private static void sloge(String msg) {
        ALog.i(TAG, msg);
    }

    public void log(String msg) {
        ALog.i(TAG, "<D><" + this.name + "> " + msg);
    }

    private void logw(String msg) {
        ALog.i(TAG, "<W><" + this.name + "> " + msg);
    }

    private void loge(String msg) {
        ALog.e(TAG, "<E><" + this.name + "> " + msg);
    }

    /* loaded from: classes2.dex */
    public class RtcStatistics {
        public String connectionType = "disconnect";
        public int localCaptureWidth = 0;
        public int localCaptureHeight = 0;
        public int localCaptureFps = 0;
        public int localEncodedWidth = 0;
        public int localEncodedHeight = 0;
        public int localEncodedFps = 0;
        public int localVideoActualBps = 0;
        public int localVideoTargetBps = 0;
        public int localVideoPacketsLost = 0;
        public int localVideoPacketsLostrate = 0;
        public int localVideoRtt = 0;
        public int localAudioPacketsLost = 0;
        public int localAudioPacketsLostrate = 0;
        public int localAudioBps = 0;
        public int localAudioRtt = 0;
        public int remoteWidth = 0;
        public int remoteHeight = 0;
        public int remoteFps = 0;
        public int remoteVideoPacketsLost = 0;
        public int remoteVideoPacketsLostrate = 0;
        public int remoteVideoBps = 0;
        public int remoteAudioPacketsLost = 0;
        public int remoteAudioPacketsLostrate = 0;
        public int remoteAudioBps = 0;
        public String fullStatsString = "fullStats";

        public RtcStatistics() {
            RtcConnection.this = r3;
        }
    }

    /* loaded from: classes2.dex */
    public static class Parameters extends PeerConnectionParameters {
        public boolean renderLocal = true;
        public int cameraIndex = -1;
        public boolean receiveVideo = true;
        public boolean receiveAudio = true;
        public boolean captureVideo = true;
        public boolean captureAudio = true;

        public Parameters(boolean videoCallEnabled, boolean loopback, int videoWidth, int videoHeight, int videoFps, int videoStartBitrate, String videoCodec, boolean videoCodecHwAcceleration, int audioStartBitrate, String audioCodec, boolean noAudioProcessing, boolean cpuOveruseDetection) {
            super(videoCallEnabled, loopback, videoWidth, videoHeight, videoFps, videoStartBitrate, videoCodec, videoCodecHwAcceleration, audioStartBitrate, audioCodec, noAudioProcessing, cpuOveruseDetection);
        }

        public void enableVideo(boolean capture, boolean receive) {
            this.captureVideo = capture;
            this.receiveVideo = receive;
        }

        public void enableAudio(boolean capture, boolean receive) {
            this.captureAudio = capture;
            this.receiveAudio = receive;
        }
    }

    public void setenableaec(final boolean enable) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setenableaec:" + enable);
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.27
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setenableaec");
                RtcConnection.this.enableaec = enable;
            }
        });
    }

    public void setenableagc(final boolean enable) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setenableagc:" + enable);
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.28
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setenableagc");
                RtcConnection.this.enableagc = enable;
            }
        });
    }

    public void setenablens(final boolean enable) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setenablens:" + enable);
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.29
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setenablens");
                RtcConnection.this.enablens = enable;
            }
        });
    }

    private void createMediaConstraintsInternal() {
        this.pcConstraints = new MediaConstraints();
        if (this.peerConnectionParameters.loopback) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: loopback: set DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT false ");
            this.pcConstraints.optional.add(new MediaConstraints.KeyValuePair(DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT, "false"));
        } else {
            this.pcConstraints.optional.add(new MediaConstraints.KeyValuePair(DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT, "true"));
        }
        this.numberOfCameras = CameraEnumerationAndroid.getDeviceCount();
        if (this.numberOfCameras == 0) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: No camera on device. Switch to audio only call.");
            this.videoCallEnabled = false;
        }
        if (this.peerConnectionParameters.captureVideo) {
            this.videoConstraints = new MediaConstraints();
            int videoWidth = this.peerConnectionParameters.videoWidth;
            int videoHeight = this.peerConnectionParameters.videoHeight;
            if ((videoWidth == 0 || videoHeight == 0) && this.peerConnectionParameters.videoCodecHwAcceleration && MediaCodecVideoEncoder.isVp8HwSupported()) {
                videoWidth = 1920;
                videoHeight = HD_VIDEO_HEIGHT;
            }
            if (videoWidth > 0 && videoHeight > 0) {
                int videoWidth2 = Math.min(videoWidth, 1920);
                int videoHeight2 = Math.min(videoHeight, 1920);
                this.videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair(MIN_VIDEO_WIDTH_CONSTRAINT, Integer.toString(videoWidth2)));
                this.videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair(MAX_VIDEO_WIDTH_CONSTRAINT, Integer.toString(1920)));
                this.videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair(MIN_VIDEO_HEIGHT_CONSTRAINT, Integer.toString(videoHeight2)));
                this.videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair(MAX_VIDEO_HEIGHT_CONSTRAINT, Integer.toString(1920)));
            }
            int videoFps = this.peerConnectionParameters.videoFps;
            if (videoFps > 0) {
                int videoFps2 = Math.min(videoFps, 30);
                this.videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair(MIN_VIDEO_FPS_CONSTRAINT, Integer.toString(videoFps2)));
                this.videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair(MAX_VIDEO_FPS_CONSTRAINT, Integer.toString(videoFps2)));
            }
        }
        this.audioConstraints = new MediaConstraints();
        if (this.peerConnectionParameters.noAudioProcessing) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Disabling audio processing");
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_ECHO_CANCELLATION_CONSTRAINT, "false"));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT, "false"));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_HIGH_PASS_FILTER_CONSTRAINT, "false"));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_NOISE_SUPPRESSION_CONSTRAINT, "false"));
        }
        if (!this.enableaec) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Disabling audio AEC");
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_ECHO_CANCELLATION_CONSTRAINT, "false"));
        }
        if (!this.enableagc) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Disabling audio AGC");
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT, "false"));
        }
        if (!this.enablens) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Disabling audio NS");
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_NOISE_SUPPRESSION_CONSTRAINT, "false"));
        }
        this.sdpMediaConstraints = new MediaConstraints();
        if (this.peerConnectionParameters.loopback || this.peerConnectionParameters.receiveAudio) {
            this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        } else {
            this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "false"));
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: set OfferToReceiveAudio false");
        }
        if (this.peerConnectionParameters.loopback || this.peerConnectionParameters.receiveVideo || this.peerConnectionParameters.captureVideo) {
            this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"));
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: loopback set OfferToReceiveVideo true");
            return;
        }
        this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "false"));
    }

    private void initAudio() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: initAudio");
        if (this.audioManager == null) {
            this.audioManager = AppRTCAudioManager.create(sContext, new Runnable() { // from class: com.superrtc.sdk.RtcConnection.30
                @Override // java.lang.Runnable
                public void run() {
                }
            });
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Initializing the audio manager...");
            this.audioManager.init();
        }
    }

    private void createPeerConnectionInternal() {
        if (factory == null) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Peerconnection factory is not created");
            return;
        }
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Create peer connection");
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: PCConstraints: " + this.pcConstraints.toString());
        if (this.videoConstraints != null) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: VideoConstraints: " + this.videoConstraints.toString());
        }
        PeerConnection.RTCConfiguration rtcConfig = new PeerConnection.RTCConfiguration(this.iceServers);
        rtcConfig.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.DISABLED;
        rtcConfig.bundlePolicy = PeerConnection.BundlePolicy.MAXBUNDLE;
        rtcConfig.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.REQUIRE;
        if (this.enableRelay) {
            rtcConfig.iceTransportsType = PeerConnection.IceTransportsType.RELAY;
        }
        this.peerConnection = factory.createPeerConnection(rtcConfig, this.pcConstraints, this.pcObserver);
        if (this.peerConnection != null) {
            if (disableResize) {
                this.peerConnection.setdisableResize(disableResize);
            }
            if (configfps != 0) {
                PeerConnectionFactory.setconfigframerate(configfps);
            }
            if (configminkbps <= 0) {
                configminkbps = DEFAULT_CONFIG_MINKBPS;
                this.peerConnection.setconfigminbitrate(configminkbps);
            } else {
                this.peerConnection.setconfigminbitrate(configminkbps);
            }
        }
        this.mediaStream = factory.createLocalMediaStream("ARDAMS");
        if (this.isInitiator) {
            checkMediaTracksInternal();
        }
        this.peerConnection.addStream(this.mediaStream);
    }

    private void checkRenegoInternal() {
    }

    private void checkRenegoInternal0() {
        if (this.peerConnection != null) {
            if (this.peerConnection.signalingState() != PeerConnection.SignalingState.STABLE && this.peerConnection.signalingState() != PeerConnection.SignalingState.HAVE_LOCAL_PRANSWER && this.peerConnection.signalingState() != PeerConnection.SignalingState.HAVE_REMOTE_PRANSWER) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: skip re-nego because of state " + this.peerConnection.signalingState());
            } else if (this.peerConnection.getLocalDescription() == null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: skip re-nego because of no local sdp ");
            } else if (this.isInitiator) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: re-create OFFER : sdpMediaConstraints=" + this.sdpMediaConstraints);
                this.peerConnection.createOffer(this.localSDPObserver, this.sdpMediaConstraints);
            } else {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: re-create ANSWER : sdpMediaConstraints=" + this.sdpMediaConstraints);
                this.peerConnection.createAnswer(this.localSDPObserver, this.sdpMediaConstraints);
            }
        }
    }

    private void checkVideoCapturerDataProcessor() {
        if (this.processor != null) {
            if (this.videoCapturer != null) {
                this.videoCapturer.setCameraDataProcessor(new VideoCapturerAndroid.VideoCapturerDataProcessor() { // from class: com.superrtc.sdk.RtcConnection.31
                    @Override // com.superrtc.call.VideoCapturerAndroid.VideoCapturerDataProcessor
                    public void onProcessData(byte[] data, Camera camera, int width, int height, int rotation) {
                        RtcConnection.this.processor.onProcessData(data, camera, width, height, rotation);
                    }

                    @Override // com.superrtc.call.VideoCapturerAndroid.VideoCapturerDataProcessor
                    public void setResolution(int width, int height) {
                        RtcConnection.this.processor.setResolution(width, height);
                    }
                });
            }
        } else if (this.videoCapturer != null) {
            this.videoCapturer.setCameraDataProcessor(null);
        }
    }

    public void checkMediaTracksInternal() {
        if (this.peerConnection != null && this.mediaStream != null) {
            if (this.peerConnectionParameters.captureVideo && this.localVideoTrack == null) {
                String cameraDeviceName = CameraEnumerationAndroid.getDeviceName(0);
                boolean isbackfacing = true;
                String frontCameraDeviceName = CameraEnumerationAndroid.getNameOfFrontFacingDevice();
                if (this.numberOfCameras > 1 && frontCameraDeviceName != null) {
                    cameraDeviceName = frontCameraDeviceName;
                    isbackfacing = false;
                }
                if (cameraid >= 0 && cameraid < this.numberOfCameras) {
                    cameraDeviceName = CameraEnumerationAndroid.getDeviceName(cameraid);
                    if (cameraid == 0) {
                        isbackfacing = true;
                    }
                }
                if (isbackfacing) {
                    enableLocalViewMirror = false;
                } else {
                    enableLocalViewMirror = true;
                }
                updateVideoViewmirror(enableLocalViewMirror);
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Opening camera: " + cameraDeviceName + " Mirror::" + enableLocalViewMirror);
                this.enableOpenCamera = this.peerConnectionParameters.videoCallEnabled;
                this.videoCapturer = VideoCapturerAndroid.create(cameraDeviceName, null);
                if (this.videoCapturer == null) {
                    reportError("Failed to open camera");
                } else {
                    checkVideoCapturerDataProcessor();
                    if (this.peerConnectionParameters.videoCallEnabled) {
                        this.videoCapturer.setEnableCamera(true);
                        this.videoCapturer.setEnableCameragetsuppoted(false);
                    } else if (this.autoAddVideo) {
                        this.videoCapturer.setEnableCamera(false);
                        this.videoCapturer.setEnableCameragetsuppoted(false);
                    }
                    this.videoSource = factory.createVideoSource(this.videoCapturer, this.videoConstraints);
                    this.localVideoTrack = factory.createVideoTrack(VIDEO_TRACK_ID, this.videoSource);
                    this.localVideoTrack.setEnabled(this.renderVideo);
                    this.mediaStream.addTrack(this.localVideoTrack);
                }
                checkAddRenddererInternal();
                checkRenegoInternal();
            } else if (!this.peerConnectionParameters.captureVideo && this.localVideoTrack != null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: remove video track");
                if (this.localVideoTrack != null) {
                    this.mediaStream.removeTrack(this.localVideoTrack);
                    this.localVideoTrack.setEnabled(false);
                    this.localVideoTrack.dispose();
                    this.localVideoTrack = null;
                    if (this.localRendererAdded) {
                        this.localRendererAdded = false;
                    }
                }
                if (this.videoSource != null) {
                    this.videoSource.stop();
                    this.videoSource.dispose();
                    this.videoSource = null;
                }
                checkRenegoInternal();
            }
            if (isRemoteViewReady() && this.remoteRender == null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: checking remote renderer");
                checkAddRenddererInternal();
            }
            if (this.peerConnectionParameters.captureAudio && this.localAudioTrack == null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: create capture audio");
                this.localAudioTrack = factory.createAudioTrack(AUDIO_TRACK_ID, factory.createAudioSource(this.audioConstraints));
                if (this.localAudioTrack != null) {
                    if (!this.enableaudio) {
                        this.localAudioTrack.setEnabled(this.enableaudio);
                    }
                    this.mediaStream.addTrack(this.localAudioTrack);
                }
                checkRenegoInternal();
            } else if (this.peerConnectionParameters.captureAudio || this.localAudioTrack == null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: do nothing for audio");
            } else {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: remove audio track");
                if (this.localAudioTrack != null) {
                    this.mediaStream.removeTrack(this.localAudioTrack);
                    this.localAudioTrack = null;
                }
                checkRenegoInternal();
            }
        }
    }

    public void setMute(final boolean enable) {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ setMute ::" + enable);
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.32
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.this.enableaudio = !enable;
                if (RtcConnection.this.localAudioTrack != null) {
                    RtcConnection.this.localAudioTrack.setEnabled(RtcConnection.this.enableaudio);
                }
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- setMute");
            }
        });
    }

    public void addlocalstream() {
        String cameraDeviceName = CameraEnumerationAndroid.getDeviceName(0);
        String frontCameraDeviceName = CameraEnumerationAndroid.getNameOfFrontFacingDevice();
        if (this.numberOfCameras > 1 && frontCameraDeviceName != null) {
            cameraDeviceName = frontCameraDeviceName;
        }
        if (this.peerConnectionParameters.cameraIndex >= 0 && this.peerConnectionParameters.cameraIndex < this.numberOfCameras) {
            cameraDeviceName = CameraEnumerationAndroid.getDeviceName(this.peerConnectionParameters.cameraIndex);
        }
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Opening camera: " + cameraDeviceName);
    }

    public void TakePicture(String filepath) {
        this.peerConnection.TakePicture(filepath);
    }

    public void close() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ close");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.33
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.this.closeInternal();
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- close");
            }
        });
    }

    public void hangup() {
        if (this.ishangup) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: peer ishangup : " + this.ishangup);
            return;
        }
        this.ishangup = true;
        JSONObject obj = new JSONObject();
        synchronized (RtcConnection.class) {
            try {
                obj.put("conn", this.connectType);
                obj.put("lvcodec", this.lusevcodectype);
                obj.put("lacodec", this.luseacodectype);
                obj.put("rvcodec", this.rusevcodectype);
                obj.put("racodec", this.ruseacodectype);
                obj.put("sentVPkts", this.videolastsendpackets);
                obj.put("sentAPkts", this.audiolastsendpackets);
                obj.put("recvVPkts", this.videolastrecvpackets);
                obj.put("recvAPkts", this.audiolastrecvpackets);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: ReportString ::" + obj.toString());
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ hangup");
        this.statsTimer.cancel();
        this.isconnected = false;
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.34
            @Override // java.lang.Runnable
            public void run() {
                if (RtcConnection.this.peerConnection != null) {
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: RTC hangup close peerConnection");
                    RtcConnection.this.peerConnection.dispose();
                    RtcConnection.this.peerConnection = null;
                }
                if (RtcConnection.this.videoSource != null) {
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: RTC hangup close videosource");
                    RtcConnection.this.videoSource.dispose();
                    RtcConnection.this.videoSource = null;
                }
                RtcConnection.this.options = null;
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]++ onClosed");
                if (!RtcConnection.this.ishangup) {
                    RtcConnection.this.listener.onClosed();
                }
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- hangup");
            }
        });
    }

    public void closeInternal() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Closing peer connection.");
        this.statsTimer.cancel();
        if (this.peerConnection != null) {
            this.peerConnection.dispose();
            this.peerConnection = null;
        }
        log("Closing video source.");
        if (this.videoSource != null) {
            this.videoSource.dispose();
            this.videoSource = null;
        }
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Closing peer connection factory.");
        this.options = null;
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Closing peer connection done.");
        sLogListener.onLog(loglevel, String.valueOf(this.name) + ":::  onClosed");
        if (!this.ishangup) {
            this.listener.onClosed();
        }
        PeerConnectionFactory.stopInternalTracingCapture();
        PeerConnectionFactory.shutdownInternalTracer();
    }

    private static String setStartBitrate(String codec, boolean isVideoCodec, String sdpDescription, int bitrateKbps) {
        String bitrateSet;
        String[] lines = sdpDescription.split("\r\n");
        int rtpmapLineIndex = -1;
        boolean sdpFormatUpdated = false;
        String codecRtpMap = null;
        Pattern codecPattern = Pattern.compile("^a=rtpmap:(\\d+) " + codec + "(/\\d+)+[\r]?$");
        int i = 0;
        while (true) {
            if (i >= lines.length) {
                break;
            }
            Matcher codecMatcher = codecPattern.matcher(lines[i]);
            if (codecMatcher.matches()) {
                codecRtpMap = codecMatcher.group(1);
                rtpmapLineIndex = i;
                break;
            }
            i++;
        }
        if (codecRtpMap == null) {
            sLogListener.onLog(loglevel, "::: No rtpmap for " + codec + " codec");
            return sdpDescription;
        }
        sLogListener.onLog(loglevel, "::: Found " + codec + " rtpmap " + codecRtpMap + " at " + lines[rtpmapLineIndex]);
        Pattern codecPattern2 = Pattern.compile("^a=fmtp:" + codecRtpMap + " \\w+=\\d+.*[\r]?$");
        int i2 = 0;
        while (true) {
            if (i2 >= lines.length) {
                break;
            } else if (codecPattern2.matcher(lines[i2]).matches()) {
                sLogListener.onLog(loglevel, "::: Found " + codec + HanziToPinyin.Token.SEPARATOR + lines[i2]);
                if (isVideoCodec) {
                    lines[i2] = String.valueOf(lines[i2]) + "; x-google-start-bitrate=" + bitrateKbps;
                } else {
                    lines[i2] = String.valueOf(lines[i2]) + "; maxaveragebitrate=" + (bitrateKbps * 1000);
                }
                sLogListener.onLog(loglevel, "::: Update remote SDP line: " + lines[i2]);
                sdpFormatUpdated = true;
            } else {
                i2++;
            }
        }
        StringBuilder newSdpDescription = new StringBuilder();
        for (int i3 = 0; i3 < lines.length; i3++) {
            newSdpDescription.append(lines[i3]).append("\r\n");
            if (!sdpFormatUpdated && i3 == rtpmapLineIndex) {
                if (isVideoCodec) {
                    bitrateSet = "a=fmtp:" + codecRtpMap + HanziToPinyin.Token.SEPARATOR + VIDEO_CODEC_PARAM_START_BITRATE + "=" + bitrateKbps;
                } else {
                    bitrateSet = "a=fmtp:" + codecRtpMap + HanziToPinyin.Token.SEPARATOR + AUDIO_CODEC_PARAM_BITRATE + "=" + (bitrateKbps * 1000);
                }
                sLogListener.onLog(loglevel, "::: Add remote SDP line: " + bitrateSet);
                newSdpDescription.append(bitrateSet).append("\r\n");
            }
        }
        return newSdpDescription.toString();
    }

    public static String preferCodec(String sdpDescription, String codec, boolean isAudio) {
        String[] lines = sdpDescription.split("\r\n");
        int mLineIndex = -1;
        String codecRtpMap = null;
        Pattern codecPattern = Pattern.compile("^a=rtpmap:(\\d+) " + codec + "(/\\d+)+[\r]?$");
        String mediaDescription = "m=video ";
        if (isAudio) {
            mediaDescription = "m=audio ";
        }
        for (int i = 0; i < lines.length && (mLineIndex == -1 || codecRtpMap == null); i++) {
            if (lines[i].startsWith(mediaDescription)) {
                mLineIndex = i;
            } else {
                Matcher codecMatcher = codecPattern.matcher(lines[i]);
                if (codecMatcher.matches()) {
                    codecRtpMap = codecMatcher.group(1);
                }
            }
        }
        if (mLineIndex == -1) {
            sLogListener.onLog(loglevel, "::: No " + mediaDescription + " line, so can't prefer " + codec);
            return sdpDescription;
        } else if (codecRtpMap == null) {
            sLogListener.onLog(loglevel, "::: No rtpmap for " + codec);
            return sdpDescription;
        } else {
            sLogListener.onLog(loglevel, "::: Found " + codec + " rtpmap " + codecRtpMap + ", prefer at " + lines[mLineIndex]);
            String[] origMLineParts = lines[mLineIndex].split(HanziToPinyin.Token.SEPARATOR);
            if (origMLineParts.length > 3) {
                StringBuilder newMLine = new StringBuilder();
                int origPartIndex = 0 + 1;
                newMLine.append(origMLineParts[0]).append(HanziToPinyin.Token.SEPARATOR);
                int origPartIndex2 = origPartIndex + 1;
                newMLine.append(origMLineParts[origPartIndex]).append(HanziToPinyin.Token.SEPARATOR);
                newMLine.append(origMLineParts[origPartIndex2]).append(HanziToPinyin.Token.SEPARATOR);
                newMLine.append(codecRtpMap);
                for (int origPartIndex3 = origPartIndex2 + 1; origPartIndex3 < origMLineParts.length; origPartIndex3++) {
                    if (!origMLineParts[origPartIndex3].equals(codecRtpMap)) {
                        newMLine.append(HanziToPinyin.Token.SEPARATOR).append(origMLineParts[origPartIndex3]);
                    }
                }
                lines[mLineIndex] = newMLine.toString();
                sLogListener.onLog(loglevel, "::: Change media description: " + lines[mLineIndex]);
            } else {
                sLogListener.onLog(loglevel, "::: Wrong SDP media description format: " + lines[mLineIndex]);
            }
            StringBuilder newSdpDescription = new StringBuilder();
            for (String line : lines) {
                newSdpDescription.append(line).append("\r\n");
            }
            return newSdpDescription.toString();
        }
    }

    public void switchCameraInternal() {
        if (this.autoAddVideo) {
            if (!this.enableOpenCamera || this.numberOfCameras < 2 || this.videoCapturer == null) {
                sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Failed to switch camera. Video: " + this.enableOpenCamera + ". Number of cameras: " + this.numberOfCameras);
                return;
            }
        } else if (!this.videoCallEnabled || this.numberOfCameras < 2 || this.videoCapturer == null) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Failed to switch camera. Video: " + this.videoCallEnabled + ". Number of cameras: " + this.numberOfCameras);
            return;
        }
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Switch camera");
        this.videoCapturer.switchCamera(new VideoCapturerAndroid.CameraSwitchHandler() { // from class: com.superrtc.sdk.RtcConnection.35
            @Override // com.superrtc.call.VideoCapturerAndroid.CameraSwitchHandler
            public void onCameraSwitchDone(boolean isFrontCamera) {
                if (isFrontCamera) {
                    RtcConnection.enableLocalViewMirror = true;
                } else {
                    RtcConnection.enableLocalViewMirror = false;
                }
                RtcConnection.this.updateVideoViewmirror(RtcConnection.enableLocalViewMirror);
            }

            @Override // com.superrtc.call.VideoCapturerAndroid.CameraSwitchHandler
            public void onCameraSwitchError(String errorDescription) {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: switchCamera Error::" + errorDescription);
            }
        });
    }

    public void switchCamera() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ switchCamera");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.36
            @Override // java.lang.Runnable
            public void run() {
                RtcConnection.this.switchCameraInternal();
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- switchCamera");
            }
        });
    }

    public void checkoutenableCamera() {
        if (this.videoCapturer == null || this.enableOpenCamera || !this.autoAddVideo) {
            sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: Failed to enableCamera. Video:" + this.enableOpenCamera + " autoAddVideo:" + this.autoAddVideo);
            return;
        }
        this.videoCapturer.enableCameraThread();
        this.enableOpenCamera = true;
    }

    public void startCapture() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ startCapture");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.37
            @Override // java.lang.Runnable
            public void run() {
                if (RtcConnection.this.videoCapturer == null || RtcConnection.this.enableOpenCamera) {
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, "Failed to startCapture. OpenCamera:" + RtcConnection.this.enableOpenCamera);
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- startCapture");
                    return;
                }
                RtcConnection.this.checkoutenableCamera();
                RtcConnection.this.videoSource.restart();
                RtcConnection.this.enableOpenCamera = true;
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- startCapture");
            }
        });
    }

    public void stopCapture() {
        sLogListener.onLog(loglevel, String.valueOf(this.name) + "::: [rapi]++ stopCapture");
        executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.38
            @Override // java.lang.Runnable
            public void run() {
                if (!RtcConnection.this.enableOpenCamera || RtcConnection.this.videoCapturer == null) {
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, "Failed to stopCapture. OpenCamera:" + RtcConnection.this.enableOpenCamera);
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- stopCapture");
                    return;
                }
                RtcConnection.this.videoSource.stop();
                RtcConnection.this.enableOpenCamera = false;
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- stopCapture");
            }
        });
    }

    /* loaded from: classes2.dex */
    public static class PeerConnectionParameters {
        public String audioCodec;
        public int audioStartBitrate;
        public boolean cpuOveruseDetection;
        public boolean loopback;
        public boolean noAudioProcessing;
        public boolean videoCallEnabled;
        public String videoCodec;
        public boolean videoCodecHwAcceleration;
        public int videoFps;
        public int videoHeight;
        public int videoStartBitrate;
        public int videoWidth;

        public PeerConnectionParameters(boolean videoCallEnabled, boolean loopback, int videoWidth, int videoHeight, int videoFps, int videoStartBitrate, String videoCodec, boolean videoCodecHwAcceleration, int audioStartBitrate, String audioCodec, boolean noAudioProcessing, boolean cpuOveruseDetection) {
            this.videoCallEnabled = videoCallEnabled;
            this.loopback = loopback;
            this.videoWidth = videoWidth;
            this.videoHeight = videoHeight;
            this.videoFps = videoFps;
            this.videoStartBitrate = videoStartBitrate;
            this.videoCodec = videoCodec;
            this.videoCodecHwAcceleration = videoCodecHwAcceleration;
            this.audioStartBitrate = audioStartBitrate;
            this.audioCodec = audioCodec;
            this.noAudioProcessing = noAudioProcessing;
            this.cpuOveruseDetection = cpuOveruseDetection;
        }

        public String toString() {
            return "[videoCallEnabled=" + this.videoCallEnabled + ", loopback=" + this.loopback + ", videoWidth=" + this.videoWidth + ", videoHeight=" + this.videoHeight + ", videoFps=" + this.videoFps + ", videoStartBitrate=" + this.videoStartBitrate + ", videoCodec=" + this.videoCodec + ", videoCodecHwAcceleration=" + this.videoCodecHwAcceleration + ", audioStartBitrate=" + this.audioStartBitrate + ", audioCodec=" + this.audioCodec + ", noAudioProcessing=" + this.noAudioProcessing + ", cpuOveruseDetection=" + this.cpuOveruseDetection + "]";
        }
    }

    public String preferCandidates(String sdpDescription) {
        StringBuilder newSdpDescription = new StringBuilder();
        for (String line : sdpDescription.split("\r\n")) {
            newSdpDescription.append(line).append("\r\n");
        }
        return newSdpDescription.toString();
    }

    /* loaded from: classes2.dex */
    public class PCObserver implements PeerConnection.Observer {
        private PCObserver() {
            RtcConnection.this = r1;
        }

        /* synthetic */ PCObserver(RtcConnection rtcConnection, PCObserver pCObserver) {
            this();
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onIceCandidate(final IceCandidate candidate) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]++ onlocalIceCandidate::" + candidate);
            RtcConnection.local_executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.PCObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    RtcConnection.this.listener.onLocalCandidate(RtcContent.candidate2Json(candidate, RtcConnection.this.getLocalSeq(), "connectionId"));
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]-- onlocalIceCandidate");
                }
            });
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onSignalingChange(PeerConnection.SignalingState newState) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: onSignalingChange: " + newState);
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onIceConnectionChange(final PeerConnection.IceConnectionState newState) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + ":::  ++ onIceConnectionChange: " + newState + " (signalingState=" + RtcConnection.this.peerConnection.signalingState() + ")");
            RtcConnection.local_executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.PCObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (newState == PeerConnection.IceConnectionState.CONNECTED) {
                        if (!RtcConnection.this.isconnected) {
                            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]++ onConnectionsetup");
                            if (!RtcConnection.this.ishangup) {
                                RtcConnection.this.listener.onConnectionsetup();
                            }
                            RtcConnection.this.isconnected = true;
                            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]++ connectType ::" + RtcConnection.this.connectType);
                        } else {
                            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]++ onConnected");
                            if (!RtcConnection.this.ishangup) {
                                RtcConnection.this.listener.onConnected();
                            }
                        }
                        RtcConnection.this.updateVideoViewInternal();
                        RtcConnection.this.iceConnectionState = PeerConnection.IceConnectionState.CONNECTED;
                    } else if (newState == PeerConnection.IceConnectionState.DISCONNECTED) {
                        RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]++ onDisconnected");
                        if (!RtcConnection.this.ishangup) {
                            RtcConnection.this.listener.onDisconnected();
                        }
                    } else if (newState == PeerConnection.IceConnectionState.FAILED) {
                        RtcConnection.this.reportError("ICE connection failed.");
                    }
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: -- onIceConnectionChange: ");
                }
            });
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onIceGatheringChange(PeerConnection.IceGatheringState newState) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: IceGatheringState: " + newState);
            if (newState == PeerConnection.IceGatheringState.COMPLETE) {
                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi]++ onCandidateCompleted");
                if (!RtcConnection.this.ishangup) {
                    RtcConnection.this.listener.onCandidateCompleted();
                }
            }
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onIceConnectionReceivingChange(boolean receiving) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: IceConnectionReceiving changed to " + receiving);
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onAddStream(final MediaStream stream) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: onAddStream");
            RtcConnection.executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.PCObserver.3
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection != null) {
                        if (stream.audioTracks.size() > 1 || stream.videoTracks.size() > 1) {
                            RtcConnection.this.reportError("Weird-looking stream: " + stream);
                            return;
                        }
                        if (stream.videoTracks.size() == 1) {
                            RtcConnection.this.remoteVideoTrack = stream.videoTracks.get(0);
                            RtcConnection.this.remoteVideoTrack.setEnabled(true);
                            RtcConnection.this.checkAddRenddererInternal();
                            if (RtcConnection.this.remoteVideoTrack != null) {
                                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: onAddStream: remoteVideoTrack state " + RtcConnection.this.remoteVideoTrack.state());
                            }
                        }
                        RtcConnection.this.remoteStream = stream;
                    }
                }
            });
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onRemoveStream(final MediaStream stream) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: onRemoveStream");
            RtcConnection.executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.PCObserver.4
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection != null) {
                        RtcConnection.this.remoteVideoTrack = null;
                        stream.videoTracks.get(0).dispose();
                        if (stream == RtcConnection.this.remoteStream) {
                            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: remove remote stream");
                            RtcConnection.this.remoteStream = null;
                            return;
                        }
                        RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: warning: remove known stream");
                    }
                }
            });
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onDataChannel(DataChannel dc) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: onDataChannel");
            RtcConnection.this.reportError("AppRTC doesn't use data channels, but got: " + dc.label() + " anyway!");
        }

        @Override // com.superrtc.call.PeerConnection.Observer
        public void onRenegotiationNeeded() {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: onRenegotiationNeeded");
        }
    }

    /* loaded from: classes2.dex */
    public class LocalSDPObserver implements SdpObserver {
        private LocalSDPObserver() {
            RtcConnection.this = r1;
        }

        /* synthetic */ LocalSDPObserver(RtcConnection rtcConnection, LocalSDPObserver localSDPObserver) {
            this();
        }

        @Override // com.superrtc.call.SdpObserver
        public void onCreateSuccess(final SessionDescription origSdp) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, "create local sdp success");
            RtcConnection.executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.LocalSDPObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection == null) {
                        RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: skip set local because of null conn");
                        return;
                    }
                    String sdpDescription = RtcConnection.this.ssrcChanger.changeSSRC(RtcConnection.this.preferCandidates(RtcConnection.preferCodec(RtcConnection.preferCodec(origSdp.description, RtcConnection.this.audioCodec, true), RtcConnection.this.videoCodec, false)));
                    SessionDescription.Type sdpType = origSdp.type;
                    if (RtcConnection.this.isPranswerState && !RtcConnection.this.disablePranswer) {
                        sdpDescription = RtcConnection.this.ssrcChanger.processPranswer(sdpDescription.replaceAll("a=recvonly", "a=inactive"));
                        sdpType = SessionDescription.Type.PRANSWER;
                    }
                    SessionDescription sdp = new SessionDescription(sdpType, sdpDescription);
                    RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: Set local SDP " + sdp.type);
                    RtcConnection.this.localSdp = sdp;
                    RtcConnection.this.peerConnection.setLocalDescription(RtcConnection.this.localSDPObserver, sdp);
                }
            });
        }

        @Override // com.superrtc.call.SdpObserver
        public void onSetSuccess() {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: set local sdp success");
            RtcConnection.local_executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.LocalSDPObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection != null) {
                        RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi] onLocalSdp");
                        if (!RtcConnection.this.ishangup) {
                            RtcConnection.this.listener.onLocalSdp(RtcContent.SDP2Json(RtcConnection.this.localSdp, RtcConnection.this.getLocalSeq(), "connectionId"));
                        }
                        RtcConnection.this.drainCandidatesInteranl();
                        RtcConnection.this.printRemoteStream("after local sdp: ");
                    }
                }
            });
        }

        @Override // com.superrtc.call.SdpObserver
        public void onCreateFailure(String error) {
            RtcConnection.this.reportError("create local sdp failure: " + error);
        }

        @Override // com.superrtc.call.SdpObserver
        public void onSetFailure(String error) {
            RtcConnection.this.reportError("set local sdp failure: " + error);
        }
    }

    /* loaded from: classes2.dex */
    public class RemoteSDPObserver implements SdpObserver {
        private RemoteSDPObserver() {
            RtcConnection.this = r1;
        }

        /* synthetic */ RemoteSDPObserver(RtcConnection rtcConnection, RemoteSDPObserver remoteSDPObserver) {
            this();
        }

        @Override // com.superrtc.call.SdpObserver
        public void onCreateSuccess(SessionDescription origSdp) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: create remote sdp success??");
        }

        @Override // com.superrtc.call.SdpObserver
        public void onSetSuccess() {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: set remote sdp success");
            RtcConnection.executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.RemoteSDPObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection != null) {
                        RtcConnection.this.drainCandidatesInteranl();
                        RtcConnection.this.printRemoteStream("after remote sdp: ");
                    }
                }
            });
        }

        @Override // com.superrtc.call.SdpObserver
        public void onCreateFailure(String error) {
            RtcConnection.this.reportError("create local sdp failure: " + error);
        }

        @Override // com.superrtc.call.SdpObserver
        public void onSetFailure(String error) {
            RtcConnection.this.reportError("set local sdp failure: " + error);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SDPObserver implements SdpObserver {
        private SDPObserver() {
            RtcConnection.this = r1;
        }

        @Override // com.superrtc.call.SdpObserver
        public void onCreateSuccess(SessionDescription origSdp) {
            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: sdp: onCreateSuccess");
            final SessionDescription sdp = new SessionDescription(origSdp.type, RtcConnection.preferCodec(RtcConnection.preferCodec(origSdp.description, RtcConnection.this.audioCodec, true), RtcConnection.this.videoCodec, false));
            RtcConnection.this.localSdp = sdp;
            RtcConnection.executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.SDPObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection != null) {
                        RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: Set local SDP from " + sdp.type);
                        RtcConnection.this.peerConnection.setLocalDescription(RtcConnection.this.localSDPObserver, sdp);
                    }
                }
            });
        }

        @Override // com.superrtc.call.SdpObserver
        public void onSetSuccess() {
            RtcConnection.this.log("sdp: onSetSuccess");
            RtcConnection.local_executor.execute(new Runnable() { // from class: com.superrtc.sdk.RtcConnection.SDPObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (RtcConnection.this.peerConnection != null) {
                        if (RtcConnection.this.isInitiator) {
                            if (RtcConnection.this.peerConnection.getRemoteDescription() == null) {
                                RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi] onLocalSdp");
                                if (!RtcConnection.this.ishangup) {
                                    RtcConnection.this.listener.onLocalSdp(RtcContent.SDP2Json(RtcConnection.this.localSdp, RtcConnection.this.getLocalSeq(), "connectionId"));
                                    return;
                                }
                                return;
                            }
                            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: Remote SDP set succesfully");
                            RtcConnection.this.drainCandidatesInteranl();
                        } else if (RtcConnection.this.peerConnection.getLocalDescription() != null) {
                            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: Local SDP set succesfully");
                            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: [rapi] onLocalSdp");
                            if (!RtcConnection.this.ishangup) {
                                RtcConnection.this.listener.onLocalSdp(RtcContent.SDP2Json(RtcConnection.this.localSdp, RtcConnection.this.getLocalSeq(), "connectionId"));
                            }
                            RtcConnection.this.drainCandidatesInteranl();
                        } else {
                            RtcConnection.sLogListener.onLog(RtcConnection.loglevel, String.valueOf(RtcConnection.this.name) + "::: Remote SDP set succesfully");
                        }
                    }
                }
            });
        }

        @Override // com.superrtc.call.SdpObserver
        public void onCreateFailure(String error) {
            RtcConnection.this.reportError("sdp: onCreateFailure: " + error);
        }

        @Override // com.superrtc.call.SdpObserver
        public void onSetFailure(String error) {
            RtcConnection.this.reportError("sdp: onSetFailure: " + error);
        }
    }
}

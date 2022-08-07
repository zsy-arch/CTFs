package com.hyphenate.chat;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.util.Pair;
import com.easeui.EaseConstant;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMCallSession;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.adapter.EMACallConference;
import com.hyphenate.chat.adapter.EMACallManager;
import com.hyphenate.chat.adapter.EMACallManagerListener;
import com.hyphenate.chat.adapter.EMACallRtcImpl;
import com.hyphenate.chat.adapter.EMACallRtcListenerDelegate;
import com.hyphenate.chat.adapter.EMACallSession;
import com.hyphenate.chat.adapter.EMACallStream;
import com.hyphenate.chat.adapter.EMAError;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.hyphenate.exceptions.EMServiceNotReadyException;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.media.EMLocalSurfaceView;
import com.hyphenate.media.EMOppositeSurfaceView;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.NetUtils;
import com.superrtc.sdk.RtcConnection;
import com.superrtc.sdk.VideoView;
import com.superrtc.sdk.VideoViewRenderer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EMCallManager {
    public static final String IncomingCallAction = "com.hyphenate.action.incomingcall";
    static final String TAG = "EMCallManager";
    EMCallOptions callOptions;
    EMCallConference currentConference;
    EMCallSession currentSession;
    EMACallManager emaObject;
    EMClient mClient;
    EMCallPushProvider mPushProvider;
    RtcConnection mRtcConnection;
    RtcConnection.Listener mRtcListener;
    EMCameraDataProcessor processor;
    List<EMCallStateChangeListener> callListeners = Collections.synchronizedList(new ArrayList());
    EMACallListenerDelegate delegate = new EMACallListenerDelegate();
    EMVideoCallHelper callHelper = new EMVideoCallHelper();
    EMCameraDataProcessorDelegate mProcessorDelegate = new EMCameraDataProcessorDelegate();
    CallStateUnion callState = new CallStateUnion();
    boolean isConnectedFromRinging = false;
    boolean isVideoCall = true;
    List<EMConferenceListener> mConferenceListeners = Collections.synchronizedList(new ArrayList());
    final EMCallPushProvider defaultProvider = new AnonymousClass2();
    List<ViewRendererBinder> mViewBinders = Collections.synchronizedList(new ArrayList());
    boolean mFirstOppositeRenderer = true;
    HandlerThread stateChangeHandlerThread = new HandlerThread("CallStateHandlerThread");
    Handler stateChangeHandler = new Handler(this.stateChangeHandlerThread.getLooper()) { // from class: com.hyphenate.chat.EMCallManager.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Pair pair = (Pair) message.obj;
            EMCallStateChangeListener.CallState callState = (EMCallStateChangeListener.CallState) pair.first;
            EMLog.d(EMCallManager.TAG, "stateChangeHandler handleMessage BEGIN ---- state:" + callState);
            EMCallManager.this.notifyCallStateChanged(callState, (EMCallStateChangeListener.CallError) pair.second);
            EMLog.d(EMCallManager.TAG, "stateChangeHandler handleMessage  END  ----");
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.hyphenate.chat.EMCallManager$2 */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 implements EMCallPushProvider {
        AnonymousClass2() {
            EMCallManager.this = r1;
        }

        @Override // com.hyphenate.chat.EMCallManager.EMCallPushProvider
        public void onRemoteOffline(final String str) {
            EMLog.d(EMCallManager.TAG, "onRemoteOffline, to:" + str);
            final EMMessage createTxtSendMessage = EMMessage.createTxtSendMessage("You have an incoming call", str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("em_push_title", "You have an incoming call");
            } catch (Exception e) {
                e.printStackTrace();
            }
            createTxtSendMessage.setAttribute("em_apns_ext", jSONObject);
            createTxtSendMessage.setAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, !EMCallManager.this.isVideoCall);
            createTxtSendMessage.setMessageStatusCallback(new EMCallBack() { // from class: com.hyphenate.chat.EMCallManager.2.1
                @Override // com.hyphenate.EMCallBack
                public void onError(int i, String str2) {
                    EMLog.d(EMCallManager.TAG, "onRemoteOffline Error");
                    AnonymousClass2.this.updateMessageText(createTxtSendMessage, str);
                }

                @Override // com.hyphenate.EMCallBack
                public void onProgress(int i, String str2) {
                }

                @Override // com.hyphenate.EMCallBack
                public void onSuccess() {
                    EMLog.d(EMCallManager.TAG, "onRemoteOffline success");
                    AnonymousClass2.this.updateMessageText(createTxtSendMessage, str);
                }
            });
            EMClient.getInstance().chatManager().sendMessage(createTxtSendMessage);
        }

        void updateMessageText(EMMessage eMMessage, String str) {
            EMConversation conversation = EMClient.getInstance().chatManager().getConversation(eMMessage.getTo());
            if (conversation != null) {
                conversation.removeMessage(eMMessage.getMsgId());
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class CallStateUnion {
        short BIT0 = 1;
        short BIT1 = 2;
        short BIT2 = 4;
        short BIT3 = 8;
        short BIT4 = 16;
        short BIT5 = 32;
        short BIT6 = 64;
        short BIT7 = 128;
        BitSet callState = new BitSet();
        boolean ringingToConnected = false;

        CallStateUnion() {
        }

        void changeState(EMCallStateChangeListener.CallState callState) {
            switch (callState) {
                case IDLE:
                case RINGING:
                case ANSWERING:
                case CONNECTING:
                case CONNECTED:
                case ACCEPTED:
                case DISCONNECTED:
                    if (isRinging() && callState == EMCallStateChangeListener.CallState.CONNECTED) {
                        this.ringingToConnected = true;
                    }
                    this.callState.clear(0, 3);
                    int ordinal = callState.ordinal();
                    if ((this.BIT0 & ordinal) > 0) {
                        this.callState.set(0);
                    } else {
                        this.callState.clear(0);
                    }
                    if ((this.BIT1 & ordinal) > 0) {
                        this.callState.set(1);
                    } else {
                        this.callState.clear(1);
                    }
                    if ((this.BIT2 & ordinal) > 0) {
                        this.callState.set(2);
                    } else {
                        this.callState.clear(2);
                    }
                    if ((ordinal & this.BIT3) > 0) {
                        this.callState.set(3);
                    } else {
                        this.callState.clear(3);
                    }
                    if (callState == EMCallStateChangeListener.CallState.DISCONNECTED) {
                        reset();
                        return;
                    }
                    return;
                case VOICE_PAUSE:
                    this.callState.set(4);
                    return;
                case VOICE_RESUME:
                    this.callState.clear(4);
                    return;
                case VIDEO_PAUSE:
                    this.callState.set(5);
                    return;
                case VIDEO_RESUME:
                    this.callState.clear(5);
                    return;
                case NETWORK_NORMAL:
                    this.callState.clear(6, 7);
                    return;
                case NETWORK_UNSTABLE:
                    this.callState.set(6);
                    this.callState.clear(7);
                    return;
                case NETWORK_DISCONNECTED:
                    this.callState.clear(6);
                    this.callState.set(7);
                    return;
                default:
                    return;
            }
        }

        EMCallStateChangeListener.CallState getState() {
            int i = 0;
            if (this.callState.get(0)) {
                i = 0 | this.BIT0;
            }
            if (this.callState.get(1)) {
                i |= this.BIT1;
            }
            if (this.callState.get(2)) {
                i |= this.BIT2;
            }
            if (this.callState.get(3)) {
                i |= this.BIT3;
            }
            return EMCallStateChangeListener.CallState.values()[i];
        }

        boolean isAccepted() {
            return this.callState.get(0) && !this.callState.get(1) && this.callState.get(2) && !this.callState.get(3);
        }

        boolean isAnswering() {
            return !this.callState.get(0) && this.callState.get(1) && !this.callState.get(2) && !this.callState.get(3);
        }

        boolean isConnected() {
            return !this.callState.get(0) && !this.callState.get(1) && this.callState.get(2) && !this.callState.get(3);
        }

        boolean isConnecting() {
            return this.callState.get(0) && this.callState.get(1) && !this.callState.get(2) && !this.callState.get(3);
        }

        boolean isDisconnected() {
            return !this.callState.get(0) && this.callState.get(1) && this.callState.get(2) && !this.callState.get(3);
        }

        boolean isIdle() {
            return !this.callState.get(0) && !this.callState.get(1) && !this.callState.get(2) && !this.callState.get(3);
        }

        boolean isRinging() {
            return isRinging_() || (isConnected() && this.ringingToConnected);
        }

        boolean isRinging_() {
            return this.callState.get(0) && !this.callState.get(1) && !this.callState.get(2) && !this.callState.get(3);
        }

        boolean isVideoPause() {
            return this.callState.get(5);
        }

        boolean isVoicePause() {
            return this.callState.get(4);
        }

        void reset() {
            this.callState.clear();
            this.ringingToConnected = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class EMACallListenerDelegate extends EMACallManagerListener {
        EMACallListenerDelegate() {
            EMCallManager.this = r1;
        }

        EMCallStateChangeListener.CallError endReasonToCallError(EMCallSession.EndReason endReason, EMAError eMAError) {
            EMCallStateChangeListener.CallError callError = EMCallStateChangeListener.CallError.ERROR_NONE;
            switch (endReason) {
                case HANGUP:
                    return EMCallStateChangeListener.CallError.ERROR_NONE;
                case NORESPONSE:
                    return EMCallStateChangeListener.CallError.ERROR_NORESPONSE;
                case REJECT:
                    return EMCallStateChangeListener.CallError.REJECTED;
                case BUSY:
                    return EMCallStateChangeListener.CallError.ERROR_BUSY;
                case FAIL:
                    EMCallStateChangeListener.CallError callError2 = EMCallStateChangeListener.CallError.ERROR_TRANSPORT;
                    if (eMAError.errCode() == 0) {
                        return callError2;
                    }
                    if (!(eMAError.errCode() == 802 || eMAError.errCode() == 801)) {
                        return eMAError.errCode() == 803 ? EMCallStateChangeListener.CallError.ERROR_TRANSPORT : callError2;
                    }
                    return EMCallStateChangeListener.CallError.ERROR_UNAVAILABLE;
                case OFFLINE:
                    return EMCallStateChangeListener.CallError.ERROR_UNAVAILABLE;
                default:
                    return callError;
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onConferenceClosed(EMACallConference eMACallConference) {
            EMLog.d(EMCallManager.TAG, "onConferenceClosed sessionId:" + eMACallConference.getCallId());
            synchronized (EMCallManager.this.mConferenceListeners) {
                for (EMConferenceListener eMConferenceListener : EMCallManager.this.mConferenceListeners) {
                    eMConferenceListener.onConferenceClosed(eMACallConference.getCallId());
                }
            }
            for (ViewRendererBinder viewRendererBinder : EMCallManager.this.mViewBinders) {
                if (viewRendererBinder.localView instanceof EMOppositeSurfaceView) {
                    ((EMOppositeSurfaceView) viewRendererBinder.localView).release();
                }
            }
            EMCallManager.this.mViewBinders.clear();
            EMCallManager.this.mFirstOppositeRenderer = false;
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onConferenceMemberJoined(EMACallConference eMACallConference, String str) {
            EMLog.d(EMCallManager.TAG, "onConferenceMemberEntered sessionId:" + eMACallConference.getCallId() + " enteredName:" + str);
            synchronized (EMCallManager.this.mConferenceListeners) {
                for (EMConferenceListener eMConferenceListener : EMCallManager.this.mConferenceListeners) {
                    eMConferenceListener.onConferenceMemberEntered(eMACallConference.getCallId(), str);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onConferenceMemberLeaved(EMACallConference eMACallConference, String str) {
            EMLog.d(EMCallManager.TAG, "onConferenceMemberExited sessionId:" + eMACallConference.getCallId() + " leavedName:" + str);
            synchronized (EMCallManager.this.mConferenceListeners) {
                for (EMConferenceListener eMConferenceListener : EMCallManager.this.mConferenceListeners) {
                    eMConferenceListener.onConferenceMemberExited(eMACallConference.getCallId(), str);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onConferenceMemberPublished(EMACallConference eMACallConference, String str) {
            EMLog.d(EMCallManager.TAG, "onConferenceMemberPublished sessionId:" + eMACallConference.getCallId() + " publishedName:" + str);
            synchronized (EMCallManager.this.mConferenceListeners) {
                for (EMConferenceListener eMConferenceListener : EMCallManager.this.mConferenceListeners) {
                    eMConferenceListener.onConferenceMemberPublished(eMACallConference.getCallId(), str);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onConferenceMembersUpdated(EMACallConference eMACallConference) {
            EMLog.d(EMCallManager.TAG, "onConferenceMembersUpdated sessionId:" + eMACallConference.getCallId());
            synchronized (EMCallManager.this.mConferenceListeners) {
                for (EMConferenceListener eMConferenceListener : EMCallManager.this.mConferenceListeners) {
                    eMConferenceListener.onConferenceMembersUpdated(eMACallConference.getCallId());
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onNewRtcConnection(String str, int i, EMACallStream eMACallStream, String str2, RtcConnection.Listener listener, EMACallRtcImpl eMACallRtcImpl) {
            EMLog.d(EMCallManager.TAG, "onNewRtcConnection, mode:" + i + " remoteName: " + str2);
            if (eMACallRtcImpl != null && listener != null) {
                if (i == EMACallSession.Mode.CONFERENCE.ordinal()) {
                    ViewRendererBinder renderer = EMCallManager.this.getRenderer(eMACallStream);
                    if (renderer != null) {
                        EMLog.d(EMCallManager.TAG, "get new View rtc binder");
                        renderer.bindRtc = true;
                        renderer.rtc.setListener(listener);
                        eMACallRtcImpl.setRtcConnection(EMCallManager.this, renderer.rtc);
                        return;
                    }
                    EMLog.e(EMCallManager.TAG, "can not find ViewRendererBinder");
                    return;
                }
                synchronized (EMCallManager.this) {
                    if (EMCallManager.this.mRtcConnection == null) {
                        EMCallManager.this.mRtcConnection = EMCallManager.createRtcConnection("rtc:" + str2);
                        EMCallManager.this.mRtcConnection.setRtcCameraDataProcessor(EMCallManager.this.mProcessorDelegate);
                    }
                    EMCallManager.this.mRtcListener = listener;
                    EMCallManager.this.mRtcConnection.setListener(listener);
                    eMACallRtcImpl.setRtcConnection(EMCallManager.this, EMCallManager.this.mRtcConnection);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onRecvCallAccepted(EMACallSession eMACallSession) {
            EMLog.d(EMCallManager.TAG, "onReceiveCallAccepted");
            synchronized (EMCallManager.this) {
                if (EMCallManager.this.currentSession == null) {
                    EMCallManager.this.currentSession = new EMCallSession(eMACallSession);
                }
                EMLog.d(EMCallManager.TAG, "onReceiveCallAccepted");
                EMCallManager.this.changeState(EMCallStateChangeListener.CallState.ACCEPTED);
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onRecvCallConnected(EMACallSession eMACallSession) {
            EMLog.d(EMCallManager.TAG, "onRecvSessionConnected");
            synchronized (EMCallManager.this) {
                if (EMCallManager.this.currentSession == null) {
                    EMCallManager.this.currentSession = new EMCallSession(eMACallSession);
                }
                EMCallManager.this.changeState(EMCallStateChangeListener.CallState.CONNECTED);
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onRecvCallEnded(EMACallSession eMACallSession, int i, EMAError eMAError) {
            EMLog.d(EMCallManager.TAG, "onReceiveCallTerminated, reasonOrdinal: " + i);
            synchronized (EMCallManager.this) {
                if (EMCallManager.this.currentSession != null) {
                    EMCallManager.this.currentSession = null;
                }
                EMCallManager.this.changeState(EMCallStateChangeListener.CallState.DISCONNECTED, endReasonToCallError(EMCallSession.getEndReason(i), eMAError));
                EMCallManager.this.mRtcConnection = null;
                EMCallManager.this.mRtcListener = null;
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onRecvCallFeatureUnsupported(EMACallSession eMACallSession, EMAError eMAError) {
            EMLog.d(EMCallManager.TAG, "onRecvCallFeatureUnsupported, callId:" + eMACallSession.getCallId());
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onRecvCallIncoming(EMACallSession eMACallSession) {
            EMLog.d(EMCallManager.TAG, "onRecvSessionRemoteInitiate");
            synchronized (EMCallManager.this) {
                EMCallManager.this.currentSession = new EMCallSession(eMACallSession);
                EMCallManager.this.changeState(EMCallStateChangeListener.CallState.RINGING);
            }
            Intent intent = new Intent(EMCallManager.this.getIncomingCallBroadcastAction());
            intent.putExtra("type", EMCallManager.this.currentSession.getType() == EMCallSession.Type.VIDEO ? "video" : "voice");
            intent.putExtra(MessageEncoder.ATTR_FROM, EMCallManager.this.currentSession.getRemoteName());
            intent.putExtra(MessageEncoder.ATTR_TO, EMClient.getInstance().getCurrentUser());
            EMClient.getInstance().getContext().sendBroadcast(intent);
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onRecvCallNetworkStatusChanged(EMACallSession eMACallSession, int i) {
            EMLog.d(EMCallManager.TAG, "onRecvCallNetworkStatusChanged, callId: " + eMACallSession.getCallId() + " toStatus:" + i);
            EMCallStateChangeListener.CallState callState = EMCallStateChangeListener.CallState.DISCONNECTED;
            if (i == EMACallSession.NetworkStatus.CONNECTED.ordinal()) {
                callState = EMCallStateChangeListener.CallState.NETWORK_NORMAL;
            } else if (i == EMACallSession.NetworkStatus.UNSTABLE.ordinal()) {
                callState = EMCallStateChangeListener.CallState.NETWORK_UNSTABLE;
            } else if (i == EMACallSession.NetworkStatus.DISCONNECTED.ordinal()) {
                callState = EMCallStateChangeListener.CallState.NETWORK_DISCONNECTED;
            } else {
                try {
                    throw new HyphenateException("onRecvCallNetworkStatusChanged invalid toStatus:" + i);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
            if (EMCallManager.this.callState.equals(callState)) {
                EMLog.d(EMCallManager.TAG, "onRecvCallNetworkStatusChanged toStatus equals to current callState");
            } else {
                EMCallManager.this.changeState(callState);
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onRecvCallStateChanged(EMACallSession eMACallSession, int i) {
            EMLog.d(EMCallManager.TAG, "onRecvCallStateChanged, callId: " + eMACallSession.getCallId() + " StreamControlType:" + i);
            EMCallStateChangeListener.CallState callState = EMCallStateChangeListener.CallState.DISCONNECTED;
            if (i == EMACallSession.StreamControlType.PAUSE_VIDEO.ordinal()) {
                callState = EMCallStateChangeListener.CallState.VIDEO_PAUSE;
            } else if (i == EMACallSession.StreamControlType.PAUSE_VOICE.ordinal()) {
                callState = EMCallStateChangeListener.CallState.VOICE_PAUSE;
            } else if (i == EMACallSession.StreamControlType.RESUME_VIDEO.ordinal()) {
                callState = EMCallStateChangeListener.CallState.VIDEO_RESUME;
            } else if (i == EMACallSession.StreamControlType.RESUME_VOICE.ordinal()) {
                callState = EMCallStateChangeListener.CallState.VOICE_RESUME;
            } else {
                try {
                    throw new HyphenateException("onRecvCallStateChanged invalid streamControlType:" + i);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
            if (EMCallManager.this.callState.equals(callState)) {
                EMLog.d(EMCallManager.TAG, "onRecvCallStateChanged toStatus equals to current callState");
            } else {
                EMCallManager.this.changeState(callState);
            }
        }

        @Override // com.hyphenate.chat.adapter.EMACallManagerListener, com.hyphenate.chat.adapter.EMACallManagerListenerInterface
        public void onSendPushMessage(String str, String str2) {
            EMCallPushProvider eMCallPushProvider = EMCallManager.this.mPushProvider;
            if (eMCallPushProvider == null) {
                EMCallManager.this.defaultProvider.onRemoteOffline(str2);
            } else {
                eMCallPushProvider.onRemoteOffline(str2);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface EMCallPushProvider {
        void onRemoteOffline(String str);
    }

    /* loaded from: classes2.dex */
    public enum EMCallType {
        VOICE,
        VIDEO
    }

    /* loaded from: classes2.dex */
    public interface EMCameraDataProcessor {
        void onProcessData(byte[] bArr, Camera camera, int i, int i2, int i3);
    }

    /* loaded from: classes2.dex */
    public class EMCameraDataProcessorDelegate implements RtcConnection.RtcCameraDataProcessor {
        EMCameraDataProcessorDelegate() {
            EMCallManager.this = r1;
        }

        @Override // com.superrtc.sdk.RtcConnection.RtcCameraDataProcessor
        public void onProcessData(byte[] bArr, Camera camera, int i, int i2, int i3) {
            EMCameraDataProcessor eMCameraDataProcessor = EMCallManager.this.processor;
            if (eMCameraDataProcessor != null) {
                eMCameraDataProcessor.onProcessData(bArr, camera, i, i2, i3);
            }
        }

        @Override // com.superrtc.sdk.RtcConnection.RtcCameraDataProcessor
        public void setResolution(int i, int i2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface EMConferenceListener {
        void onConferenceClosed(String str);

        void onConferenceMemberEntered(String str, String str2);

        void onConferenceMemberExited(String str, String str2);

        void onConferenceMemberPublished(String str, String str2);

        void onConferenceMembersUpdated(String str);
    }

    /* loaded from: classes.dex */
    public static class EMVideoCallHelper {

        /* loaded from: classes2.dex */
        public enum CallType {
            audio,
            video
        }

        private EMVideoCallHelper() {
        }

        @Deprecated
        public int getLocalBitrate() {
            RtcConnection.RtcStatistics statistics;
            RtcConnection.Listener listener = EMClient.getInstance().callManager().mRtcListener;
            if (listener == null || !(listener instanceof EMACallRtcListenerDelegate) || (statistics = ((EMACallRtcListenerDelegate) listener).getStatistics()) == null) {
                return 0;
            }
            return statistics.localVideoActualBps;
        }

        @Deprecated
        public int getRemoteBitrate() {
            RtcConnection.RtcStatistics statistics;
            RtcConnection.Listener listener = EMClient.getInstance().callManager().mRtcListener;
            if (listener == null || !(listener instanceof EMACallRtcListenerDelegate) || (statistics = ((EMACallRtcListenerDelegate) listener).getStatistics()) == null) {
                return 0;
            }
            return statistics.remoteVideoBps;
        }

        @Deprecated
        public int getVideoFrameRate() {
            RtcConnection.RtcStatistics statistics;
            RtcConnection.Listener listener = EMClient.getInstance().callManager().mRtcListener;
            if (listener == null || !(listener instanceof EMACallRtcListenerDelegate) || (statistics = ((EMACallRtcListenerDelegate) listener).getStatistics()) == null) {
                return 0;
            }
            return statistics.remoteFps;
        }

        public int getVideoHeight() {
            RtcConnection.RtcStatistics statistics;
            RtcConnection.Listener listener = EMClient.getInstance().callManager().mRtcListener;
            if (listener == null || !(listener instanceof EMACallRtcListenerDelegate) || (statistics = ((EMACallRtcListenerDelegate) listener).getStatistics()) == null) {
                return 0;
            }
            return statistics.remoteHeight;
        }

        @Deprecated
        public int getVideoLatency() {
            RtcConnection.RtcStatistics statistics;
            RtcConnection.Listener listener = EMClient.getInstance().callManager().mRtcListener;
            if (listener == null || !(listener instanceof EMACallRtcListenerDelegate) || (statistics = ((EMACallRtcListenerDelegate) listener).getStatistics()) == null) {
                return 0;
            }
            return statistics.localVideoRtt;
        }

        @Deprecated
        public int getVideoLostRate() {
            RtcConnection.RtcStatistics statistics;
            RtcConnection.Listener listener = EMClient.getInstance().callManager().mRtcListener;
            if (listener == null || !(listener instanceof EMACallRtcListenerDelegate) || (statistics = ((EMACallRtcListenerDelegate) listener).getStatistics()) == null) {
                return 0;
            }
            return statistics.remoteVideoPacketsLostrate;
        }

        public int getVideoWidth() {
            RtcConnection.RtcStatistics statistics;
            RtcConnection.Listener listener = EMClient.getInstance().callManager().mRtcListener;
            if (listener == null || !(listener instanceof EMACallRtcListenerDelegate) || (statistics = ((EMACallRtcListenerDelegate) listener).getStatistics()) == null) {
                return 0;
            }
            return statistics.remoteWidth;
        }

        public void setPreferMovFormatEnable(boolean z) {
            if (z) {
                RtcConnection.setGlobalVideoCodec(RtcConnection.RtcConstStringH264);
            } else {
                RtcConnection.setGlobalVideoCodec(null);
            }
        }

        public void startVideoRecord(String str) {
            EMClient.getInstance().callManager().emaObject.startRecordVideo(str);
        }

        public String stopVideoRecord() {
            return EMClient.getInstance().callManager().emaObject.stopRecordVideo();
        }

        public boolean takePicture(String str) {
            return EMClient.getInstance().callManager().emaObject.capturePicture(str);
        }
    }

    /* loaded from: classes2.dex */
    public static class ViewRendererBinder {
        EMCallStream callStream;
        RtcConnection rtc;
        boolean bindRtc = false;
        VideoViewRenderer localRenderer = null;
        VideoViewRenderer oppositeRenderer = null;
        VideoView localView = null;
        VideoView oppositeView = null;

        public ViewRendererBinder(String str) {
            this.rtc = null;
            this.rtc = EMCallManager.createRtcConnection(str);
        }
    }

    static {
        RtcConnection.registerLogListener(new RtcConnection.LogListener() { // from class: com.hyphenate.chat.EMCallManager.1
            @Override // com.superrtc.sdk.RtcConnection.LogListener
            public void onLog(int i, String str) {
                EMLog.d("EMCallManager$RTC", str);
            }
        });
    }

    public EMCallManager(EMClient eMClient, EMACallManager eMACallManager) {
        this.stateChangeHandlerThread.start();
        this.mClient = eMClient;
        this.emaObject = eMACallManager;
        this.emaObject.addListener(this.delegate);
        try {
            RtcConnection.initGlobal(EMClient.getInstance().getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static RtcConnection createRtcConnection(String str) {
        RtcConnection rtcConnection = new RtcConnection(str);
        EMCallOptions callOptions = EMClient.getInstance().callManager().getCallOptions();
        if (callOptions.isUserSetAutoResizing) {
            rtcConnection.enableFixedVideoResolution(callOptions.userSetAutoResizing);
        }
        if (callOptions.isUserSetMaxFrameRate) {
            rtcConnection.setMaxVideoFrameRate(callOptions.userSetMaxFrameRate);
        }
        if (!(!callOptions.isChangeVideoResolution || callOptions.changeVideoResolutionWidth == -1 || callOptions.changeVideoResolutionHeight == -1)) {
            rtcConnection.changeVideoResolution(callOptions.changeVideoResolutionWidth, callOptions.changeVideoResolutionHeight);
        }
        return rtcConnection;
    }

    private EMCallStateChangeListener.CallError getCallError(EMAError eMAError) {
        EMCallStateChangeListener.CallError callError = EMCallStateChangeListener.CallError.ERROR_TRANSPORT;
        switch (eMAError.errCode()) {
            case 800:
                return EMCallStateChangeListener.CallError.ERROR_NONE;
            case 801:
                return EMCallStateChangeListener.CallError.ERROR_BUSY;
            case 802:
                return EMCallStateChangeListener.CallError.ERROR_UNAVAILABLE;
            case 803:
                return EMCallStateChangeListener.CallError.ERROR_TRANSPORT;
            default:
                return callError;
        }
    }

    private void handleError(EMAError eMAError) throws HyphenateException {
        if (eMAError.errCode() != 0) {
            EMLog.e(TAG, "error code:" + eMAError.errCode() + " errorMsg:" + eMAError.errMsg());
            throw new HyphenateException(eMAError);
        }
    }

    public void notifyCallStateChanged(EMCallStateChangeListener.CallState callState, EMCallStateChangeListener.CallError callError) {
        synchronized (this.callListeners) {
            for (EMCallStateChangeListener eMCallStateChangeListener : this.callListeners) {
                eMCallStateChangeListener.onCallStateChanged(callState, callError);
            }
        }
    }

    public void addCallStateChangeListener(EMCallStateChangeListener eMCallStateChangeListener) {
        synchronized (this.callListeners) {
            if (!this.callListeners.contains(eMCallStateChangeListener)) {
                this.callListeners.add(eMCallStateChangeListener);
            }
        }
    }

    void addConferenceListener(EMConferenceListener eMConferenceListener) {
        synchronized (this.mConferenceListeners) {
            if (!this.mConferenceListeners.contains(eMConferenceListener)) {
                this.mConferenceListeners.add(eMConferenceListener);
            }
        }
    }

    public void answerCall() throws EMNoActiveCallException {
        synchronized (this) {
            if (this.currentSession == null) {
                throw new EMNoActiveCallException("no incoming active call");
            }
        }
        if (!this.callState.isRinging()) {
            throw new EMNoActiveCallException("Current callstate is not ringing callState:" + this.callState.getState());
        }
        EMAError eMAError = new EMAError();
        this.emaObject.answerCall(this.currentSession.getCallId(), eMAError);
        synchronized (this) {
            changeState(EMCallStateChangeListener.CallState.ANSWERING);
            if (eMAError.errCode() != 0) {
                EMLog.d(TAG, "errorCode:" + eMAError.errCode());
                endCall();
            }
        }
    }

    void asyncPublishConferenceStream(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.asyncPublishConferenceStream(str, eMAError);
        handleError(eMAError);
    }

    void asyncSubscribeConferenceStream(String str, EMCallStream eMCallStream, EMOppositeSurfaceView eMOppositeSurfaceView) throws HyphenateException {
        if (str == null || eMCallStream == null || eMOppositeSurfaceView == null) {
            throw new HyphenateException(1, "null pointer exception");
        }
        EMLog.d(TAG, "asyncSubscribeConferenceStream:" + eMOppositeSurfaceView);
        for (ViewRendererBinder viewRendererBinder : this.mViewBinders) {
            if (viewRendererBinder.oppositeView == eMOppositeSurfaceView) {
                EMLog.d(TAG, "asyncSubscribeConferenceStream oppositeView already in used");
                throw new HyphenateException(1, "asyncSubscribeConferenceStream oppositeView already in used");
            }
        }
        if (!this.mFirstOppositeRenderer || this.mViewBinders.size() <= 0) {
            ViewRendererBinder viewRendererBinder2 = new ViewRendererBinder("remote:" + eMCallStream.getUserName());
            viewRendererBinder2.oppositeView = eMOppositeSurfaceView;
            viewRendererBinder2.oppositeRenderer = eMOppositeSurfaceView.getRenderer();
            viewRendererBinder2.callStream = eMCallStream;
            EMLog.d(TAG, "asyncSubscribeConferenceStream setViews 2");
            viewRendererBinder2.rtc.setViews(null, eMOppositeSurfaceView.getRenderer());
            this.mViewBinders.add(viewRendererBinder2);
        } else {
            ViewRendererBinder viewRendererBinder3 = this.mViewBinders.get(0);
            viewRendererBinder3.oppositeView = eMOppositeSurfaceView;
            viewRendererBinder3.oppositeRenderer = eMOppositeSurfaceView.getRenderer();
            viewRendererBinder3.callStream = eMCallStream;
            EMLog.d(TAG, "asyncSubscribeConferenceStream setViews 1");
            viewRendererBinder3.rtc.setViews(viewRendererBinder3.localRenderer, eMOppositeSurfaceView.getRenderer());
            this.mFirstOppositeRenderer = false;
        }
        EMAError eMAError = new EMAError();
        this.emaObject.asyncSubscribeConferenceStream(str, eMCallStream.emaObject, eMAError);
        handleError(eMAError);
    }

    void changeState(EMCallStateChangeListener.CallState callState) {
        changeState(callState, EMCallStateChangeListener.CallError.ERROR_NONE);
    }

    protected void changeState(EMCallStateChangeListener.CallState callState, EMCallStateChangeListener.CallError callError) {
        EMLog.d(TAG, "changeState:" + callState);
        this.callState.changeState(callState);
        this.stateChangeHandler.sendMessage(this.stateChangeHandler.obtainMessage(0, new Pair(callState, callError)));
    }

    public void clearRenderView() {
        this.mViewBinders.clear();
    }

    @Deprecated
    public void clearRtcConnection() {
        this.mRtcConnection = null;
    }

    void clearStateMessages() {
        this.stateChangeHandler.removeMessages(0);
    }

    @Deprecated
    public void clearView() {
    }

    EMCallConference createAndJoinConference(EMCallType eMCallType, String str) throws HyphenateException {
        this.mFirstOppositeRenderer = true;
        EMAError eMAError = new EMAError();
        EMCallConference eMCallConference = new EMCallConference(this.emaObject.createAndJoinConference(eMCallType.ordinal(), str, eMAError));
        synchronized (this) {
            this.currentConference = eMCallConference;
        }
        handleError(eMAError);
        return eMCallConference;
    }

    EMCallConference createConference(EMCallType eMCallType, String str) throws HyphenateException {
        this.mFirstOppositeRenderer = true;
        EMAError eMAError = new EMAError();
        EMCallConference eMCallConference = new EMCallConference(this.emaObject.createConference(eMCallType.ordinal(), str, eMAError));
        synchronized (this) {
            this.currentConference = eMCallConference;
        }
        handleError(eMAError);
        return eMCallConference;
    }

    void deleteConference(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.deleteConference(str, eMAError);
        handleError(eMAError);
        for (ViewRendererBinder viewRendererBinder : this.mViewBinders) {
            if (viewRendererBinder.localView instanceof EMOppositeSurfaceView) {
                ((EMOppositeSurfaceView) viewRendererBinder.localView).release();
            }
        }
        this.mFirstOppositeRenderer = true;
        this.mViewBinders.clear();
    }

    void deleteConferenceStream(String str, String str2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.deleteConference(str, str2, eMAError);
        handleError(eMAError);
    }

    public void endCall() throws EMNoActiveCallException {
        final String callId;
        synchronized (this) {
            if (this.currentSession == null) {
                EMLog.e(TAG, "no incoming active call");
                throw new EMNoActiveCallException("no incoming active call");
            }
            callId = this.currentSession.getCallId();
        }
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMCallManager.4
            @Override // java.lang.Runnable
            public void run() {
                EMCallManager.this.emaObject.endCall(callId, EMACallSession.EndReason.HANGUP);
            }
        });
        synchronized (this) {
            this.mRtcConnection = null;
            this.mRtcListener = null;
        }
    }

    public EMCallOptions getCallOptions() {
        if (this.callOptions == null) {
            this.callOptions = new EMCallOptions(this.emaObject);
        }
        return this.callOptions;
    }

    public EMCallStateChangeListener.CallState getCallState() {
        return this.callState.getState();
    }

    public int getCameraFacing() {
        return RtcConnection.getCameraFacing();
    }

    List<String> getConferenceMembersFromServer(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        List<String> conferenceMembersFromServer = this.emaObject.getConferenceMembersFromServer(str, eMAError);
        handleError(eMAError);
        return conferenceMembersFromServer;
    }

    public EMCallSession getCurrentCallSession() {
        return this.currentSession;
    }

    public String getIncomingCallBroadcastAction() {
        return IncomingCallAction;
    }

    ViewRendererBinder getNextAvailableRenderer(String str) {
        synchronized (this.mViewBinders) {
            for (int i = 0; i < this.mViewBinders.size(); i++) {
                ViewRendererBinder viewRendererBinder = this.mViewBinders.get(i);
                if (!viewRendererBinder.bindRtc) {
                    return viewRendererBinder;
                }
            }
            return null;
        }
    }

    ViewRendererBinder getRenderer(EMACallStream eMACallStream) {
        ViewRendererBinder viewRendererBinder;
        if (eMACallStream == null) {
            return null;
        }
        EMLog.d(TAG, "callStream.username:" + eMACallStream.getUserName());
        synchronized (this.mViewBinders) {
            if (!eMACallStream.getUserName().equals(EMClient.getInstance().getCurrentUser()) || this.mViewBinders.size() <= 0) {
                Iterator<ViewRendererBinder> it = this.mViewBinders.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        viewRendererBinder = null;
                        break;
                    }
                    viewRendererBinder = it.next();
                    if (viewRendererBinder != null && viewRendererBinder.callStream != null && viewRendererBinder.callStream.emaObject.equals(eMACallStream)) {
                        break;
                    }
                }
            } else {
                viewRendererBinder = this.mViewBinders.get(0);
            }
        }
        return viewRendererBinder;
    }

    public EMVideoCallHelper getVideoCallHelper() {
        return this.callHelper;
    }

    public boolean isDirectCall() {
        RtcConnection.RtcStatistics statistics;
        RtcConnection.Listener listener = EMClient.getInstance().callManager().mRtcListener;
        if (listener == null || !(listener instanceof EMACallRtcListenerDelegate) || (statistics = ((EMACallRtcListenerDelegate) listener).getStatistics()) == null || statistics.connectionType == null) {
            return true;
        }
        return statistics.connectionType.equals("direct");
    }

    EMCallConference joinConference(String str, String str2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        EMCallConference eMCallConference = new EMCallConference(this.emaObject.joinConference(str, str2, eMAError));
        synchronized (this) {
            this.currentConference = eMCallConference;
        }
        handleError(eMAError);
        return this.currentConference;
    }

    void leaveConference(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.leaveConference(str, eMAError);
        handleError(eMAError);
    }

    public void makeVideoCall(String str) throws EMServiceNotReadyException {
        makeVideoCall(str, "");
    }

    public void makeVideoCall(String str, String str2) throws EMServiceNotReadyException {
        EMLog.d(TAG, "makeVideoCall");
        this.isVideoCall = true;
        if (!EMClient.getInstance().isConnected()) {
            EMLog.d(TAG, "exception isConnected:false");
            throw new EMServiceNotReadyException("exception isConnected:false");
        } else if (!NetUtils.hasDataConnection(EMClient.getInstance().getContext())) {
            EMLog.d(TAG, "Has no network connection");
            throw new EMServiceNotReadyException(2, "Has no network connection");
        } else if (this.callState.isIdle() || this.callState.isDisconnected()) {
            EMAError eMAError = new EMAError();
            EMACallSession makeCall = this.emaObject.makeCall(str, EMACallSession.Type.VIDEO, str2, eMAError);
            synchronized (this) {
                if (eMAError.errCode() != 0) {
                    EMLog.d(TAG, "errorCode:" + eMAError.errCode());
                    this.currentSession = null;
                    changeState(EMCallStateChangeListener.CallState.DISCONNECTED, getCallError(eMAError));
                    throw new EMServiceNotReadyException(eMAError.errCode(), eMAError.errMsg());
                }
                this.currentSession = new EMCallSession(makeCall);
                changeState(EMCallStateChangeListener.CallState.CONNECTING);
            }
        } else {
            EMLog.d(TAG, "exception callState:" + this.callState);
            throw new EMServiceNotReadyException("exception callState:" + this.callState.getState().toString());
        }
    }

    public void makeVoiceCall(String str) throws EMServiceNotReadyException {
        makeVoiceCall(str, "");
    }

    public void makeVoiceCall(String str, String str2) throws EMServiceNotReadyException {
        EMLog.d(TAG, "makeVoiceCall");
        this.isVideoCall = false;
        if (!EMClient.getInstance().isConnected()) {
            EMLog.d(TAG, "exception isConnected:false");
            throw new EMServiceNotReadyException("exception isConnected:false");
        } else if (!NetUtils.hasDataConnection(EMClient.getInstance().getContext())) {
            EMLog.d(TAG, "Has no network connection");
            throw new EMServiceNotReadyException(2, "Has no network connection");
        } else if (this.callState.isIdle() || this.callState.isDisconnected()) {
            EMAError eMAError = new EMAError();
            EMACallSession makeCall = this.emaObject.makeCall(str, EMACallSession.Type.VOICE, str2, eMAError);
            synchronized (this) {
                if (eMAError.errCode() != 0) {
                    EMLog.d(TAG, "errorCode:" + eMAError.errCode());
                    this.currentSession = null;
                    changeState(EMCallStateChangeListener.CallState.DISCONNECTED, getCallError(eMAError));
                    throw new EMServiceNotReadyException(eMAError.errCode(), eMAError.errMsg());
                }
                this.currentSession = new EMCallSession(makeCall);
                changeState(EMCallStateChangeListener.CallState.CONNECTING);
            }
        } else {
            EMLog.d(TAG, "exception callState:" + this.callState);
            throw new EMServiceNotReadyException("exception callState:" + this.callState.getState().toString());
        }
    }

    void onLogout() {
        this.stateChangeHandler.removeMessages(0);
    }

    public void pauseVideoTransfer() throws HyphenateException {
        RtcConnection rtcConnection = this.mRtcConnection;
        if (rtcConnection != null) {
            rtcConnection.stopCapture();
        }
        EMCallSession eMCallSession = this.currentSession;
        if (eMCallSession != null) {
            EMAError eMAError = new EMAError();
            this.emaObject.updateCall(eMCallSession.getCallId(), EMACallSession.StreamControlType.PAUSE_VIDEO, eMAError);
            handleError(eMAError);
        }
    }

    public void pauseVoiceTransfer() throws HyphenateException {
        RtcConnection rtcConnection = this.mRtcConnection;
        if (rtcConnection != null) {
            rtcConnection.setMute(true);
        }
        EMCallSession eMCallSession = this.currentSession;
        if (eMCallSession != null) {
            EMAError eMAError = new EMAError();
            this.emaObject.updateCall(eMCallSession.getCallId(), EMACallSession.StreamControlType.PAUSE_VOICE, eMAError);
            handleError(eMAError);
        }
    }

    void printStackTrace() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rejectCall() throws EMNoActiveCallException {
        final EMCallSession eMCallSession = this.currentSession;
        if (eMCallSession == null) {
            EMLog.e(TAG, "no incoming active call");
            throw new EMNoActiveCallException("no incoming active call");
        }
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMCallManager.3
            @Override // java.lang.Runnable
            public void run() {
                EMCallManager.this.emaObject.endCall(eMCallSession.getCallId(), EMCallManager.this.callState.isRinging() ? EMACallSession.EndReason.REJECT : EMACallSession.EndReason.HANGUP);
            }
        });
        synchronized (this) {
            this.mRtcConnection = null;
            this.mRtcListener = null;
        }
    }

    public void removeCallStateChangeListener(EMCallStateChangeListener eMCallStateChangeListener) {
        synchronized (this.callListeners) {
            if (this.callListeners.contains(eMCallStateChangeListener)) {
                this.callListeners.remove(eMCallStateChangeListener);
            }
        }
    }

    void removeConferenceListener(EMConferenceListener eMConferenceListener) {
        synchronized (this.mConferenceListeners) {
            if (this.mConferenceListeners.contains(eMConferenceListener)) {
                this.mConferenceListeners.remove(eMConferenceListener);
            }
        }
    }

    public void resumeVideoTransfer() throws HyphenateException {
        RtcConnection rtcConnection = this.mRtcConnection;
        if (rtcConnection != null) {
            rtcConnection.startCapture();
        }
        EMCallSession eMCallSession = this.currentSession;
        if (eMCallSession != null) {
            EMAError eMAError = new EMAError();
            this.emaObject.updateCall(eMCallSession.getCallId(), EMACallSession.StreamControlType.RESUME_VOICE, eMAError);
            handleError(eMAError);
        }
    }

    public void resumeVoiceTransfer() throws HyphenateException {
        RtcConnection rtcConnection = this.mRtcConnection;
        if (rtcConnection != null) {
            rtcConnection.setMute(false);
        }
        EMCallSession eMCallSession = this.currentSession;
        if (eMCallSession != null) {
            EMAError eMAError = new EMAError();
            this.emaObject.updateCall(eMCallSession.getCallId(), EMACallSession.StreamControlType.RESUME_VOICE, eMAError);
            handleError(eMAError);
        }
    }

    @Deprecated
    public void setCameraDataProcessor(EMCameraDataProcessor eMCameraDataProcessor) {
        this.processor = eMCameraDataProcessor;
    }

    public void setCameraFacing(int i) throws HyphenateException {
        if (i == 0 || i == 1) {
            RtcConnection.setCameraFacing(i);
            return;
        }
        throw new HyphenateException((int) EMError.CALL_INVALID_CAMERA_INDEX, "Invalid camera index");
    }

    void setLocalView(EMLocalSurfaceView eMLocalSurfaceView) {
        ViewRendererBinder viewRendererBinder = new ViewRendererBinder("local");
        viewRendererBinder.localRenderer = eMLocalSurfaceView.getRenderer();
        viewRendererBinder.localView = eMLocalSurfaceView;
        viewRendererBinder.rtc.setViews(eMLocalSurfaceView.getRenderer(), null);
        this.mViewBinders.add(viewRendererBinder);
    }

    void setOppositeViews(List<EMOppositeSurfaceView> list) {
    }

    public void setPushProvider(EMCallPushProvider eMCallPushProvider) {
        this.mPushProvider = eMCallPushProvider;
    }

    public synchronized void setSurfaceView(EMLocalSurfaceView eMLocalSurfaceView, EMOppositeSurfaceView eMOppositeSurfaceView) {
        if (this.mRtcConnection == null) {
            this.mRtcConnection = createRtcConnection("rtc");
        }
        this.mRtcConnection.setViews(eMLocalSurfaceView.getRenderer(), eMOppositeSurfaceView.getRenderer());
        this.mRtcConnection.setRtcCameraDataProcessor(this.mProcessorDelegate);
    }

    public synchronized void switchCamera() {
        RtcConnection rtcConnection = this.mRtcConnection;
        if (rtcConnection != null) {
            rtcConnection.switchCamera();
        }
    }
}

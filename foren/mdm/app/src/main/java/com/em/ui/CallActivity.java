package com.em.ui;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.Toast;
import com.easeui.EaseConstant;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.EMServiceNotReadyException;
import com.hyphenate.media.EMLocalSurfaceView;
import com.hyphenate.media.EMOppositeSurfaceView;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.NetUtils;
import com.vsf2f.f2f.R;

@SuppressLint({"Registered"})
/* loaded from: classes.dex */
public class CallActivity extends BaseActivity {
    protected AudioManager audioManager;
    protected String callDruationText;
    protected EMCallStateChangeListener callStateListener;
    protected boolean isInComingCall;
    protected EMLocalSurfaceView localSurface;
    protected String msgid;
    protected EMOppositeSurfaceView oppositeSurface;
    protected int outgoing;
    protected Ringtone ringtone;
    protected SoundPool soundPool;
    protected String username;
    protected final int MSG_CALL_MAKE_VIDEO = 0;
    protected final int MSG_CALL_MAKE_VOICE = 1;
    protected final int MSG_CALL_ANSWER = 2;
    protected final int MSG_CALL_REJECT = 3;
    protected final int MSG_CALL_END = 4;
    protected final int MSG_CALL_RLEASE_HANDLER = 5;
    protected final int MSG_CALL_SWITCH_CAMERA = 6;
    protected CallingState callingState = CallingState.CANCED;
    protected boolean isAnswered = false;
    protected int streamID = -1;
    protected int callType = 0;
    Runnable timeoutHangup = new Runnable() { // from class: com.em.ui.CallActivity.1
        @Override // java.lang.Runnable
        public void run() {
            CallActivity.this.handler.sendEmptyMessage(4);
        }
    };
    HandlerThread callHandlerThread = new HandlerThread("callHandlerThread");
    protected Handler handler = new Handler(this.callHandlerThread.getLooper()) { // from class: com.em.ui.CallActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            EMLog.d("EMCallManager CallActivity", "handleMessage ---enter--- msg.what:" + msg.what);
            switch (msg.what) {
                case 0:
                case 1:
                    try {
                        CallActivity.this.streamID = CallActivity.this.playMakeCallSounds();
                        if (msg.what == 0) {
                            EMClient.getInstance().callManager().makeVideoCall(CallActivity.this.username);
                        } else {
                            EMClient.getInstance().callManager().makeVoiceCall(CallActivity.this.username);
                        }
                        CallActivity.this.handler.removeCallbacks(CallActivity.this.timeoutHangup);
                        CallActivity.this.handler.postDelayed(CallActivity.this.timeoutHangup, 50000L);
                        break;
                    } catch (EMServiceNotReadyException e) {
                        e.printStackTrace();
                        CallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.CallActivity.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(CallActivity.this, CallActivity.this.getResources().getString(R.string.not_yet_connected_to_the_server), 0).show();
                            }
                        });
                        break;
                    }
                case 2:
                    if (CallActivity.this.ringtone != null) {
                        CallActivity.this.ringtone.stop();
                    }
                    if (CallActivity.this.isInComingCall) {
                        try {
                            if (NetUtils.hasDataConnection(CallActivity.this)) {
                                EMClient.getInstance().callManager().answerCall();
                                CallActivity.this.isAnswered = true;
                                break;
                            } else {
                                CallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.CallActivity.2.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Toast.makeText(CallActivity.this, CallActivity.this.getResources().getString(R.string.not_yet_connected_to_the_server), 0).show();
                                    }
                                });
                                throw new Exception();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            CallActivity.this.saveCallRecord();
                            CallActivity.this.finish();
                            return;
                        }
                    }
                    break;
                case 3:
                    if (CallActivity.this.ringtone != null) {
                        CallActivity.this.ringtone.stop();
                    }
                    try {
                        EMClient.getInstance().callManager().rejectCall();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        CallActivity.this.saveCallRecord();
                        CallActivity.this.finish();
                    }
                    CallActivity.this.callingState = CallingState.REFUESD;
                    break;
                case 4:
                    if (CallActivity.this.soundPool != null) {
                        CallActivity.this.soundPool.stop(CallActivity.this.streamID);
                    }
                    try {
                        EMClient.getInstance().callManager().endCall();
                        break;
                    } catch (Exception e3) {
                        CallActivity.this.saveCallRecord();
                        CallActivity.this.finish();
                        break;
                    }
                case 5:
                    try {
                        EMClient.getInstance().callManager().endCall();
                    } catch (Exception e4) {
                    }
                    CallActivity.this.handler.removeCallbacks(CallActivity.this.timeoutHangup);
                    CallActivity.this.handler.removeMessages(0);
                    CallActivity.this.handler.removeMessages(1);
                    CallActivity.this.handler.removeMessages(2);
                    CallActivity.this.handler.removeMessages(3);
                    CallActivity.this.handler.removeMessages(4);
                    CallActivity.this.callHandlerThread.quit();
                    break;
                case 6:
                    EMClient.getInstance().callManager().switchCamera();
                    break;
            }
            EMLog.d("EMCallManager CallActivity", "handleMessage ---exit--- msg.what:" + msg.what);
        }
    };

    /* loaded from: classes.dex */
    public enum CallingState {
        CANCED,
        NORMAL,
        REFUESD,
        BEREFUESD,
        UNANSWERED,
        OFFLINE,
        NORESPONSE,
        BUSY,
        VERSION_NOT_SAME
    }

    public CallActivity() {
        this.callHandlerThread.start();
    }

    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.audioManager = (AudioManager) getSystemService("audio");
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.soundPool != null) {
            this.soundPool.release();
        }
        if (this.ringtone != null && this.ringtone.isPlaying()) {
            this.ringtone.stop();
        }
        this.audioManager.setMode(0);
        this.audioManager.setMicrophoneMute(false);
        if (this.callStateListener != null) {
            EMClient.getInstance().callManager().removeCallStateChangeListener(this.callStateListener);
        }
        releaseHandler();
        super.onDestroy();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        this.handler.sendEmptyMessage(4);
        saveCallRecord();
        finish();
        super.onBackPressed();
    }

    void releaseHandler() {
        this.handler.sendEmptyMessage(5);
    }

    protected int playMakeCallSounds() {
        try {
            this.audioManager.setMode(1);
            this.audioManager.setSpeakerphoneOn(false);
            return this.soundPool.play(this.outgoing, 0.3f, 0.3f, 1, -1, 1.0f);
        } catch (Exception e) {
            return -1;
        }
    }

    protected void openSpeakerOn() {
        try {
            if (!this.audioManager.isSpeakerphoneOn()) {
                this.audioManager.setSpeakerphoneOn(true);
            }
            this.audioManager.setMode(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void closeSpeakerOn() {
        try {
            if (this.audioManager != null) {
                if (this.audioManager.isSpeakerphoneOn()) {
                    this.audioManager.setSpeakerphoneOn(false);
                }
                this.audioManager.setMode(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void saveCallRecord() {
        EMMessage message;
        EMTextMessageBody txtBody;
        if (!this.isInComingCall) {
            message = EMMessage.createSendMessage(EMMessage.Type.TXT);
            message.setTo(this.username);
        } else {
            message = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
            message.setFrom(this.username);
        }
        String st1 = getResources().getString(R.string.call_duration);
        String st2 = getResources().getString(R.string.refused);
        String st3 = getResources().getString(R.string.other_party_has_refused_to);
        String st4 = getResources().getString(R.string.other_is_not_online);
        String st5 = getResources().getString(R.string.other_is_on_the_phone);
        String st6 = getResources().getString(R.string.other_party_did_not_answer);
        String st7 = getResources().getString(R.string.not_answer);
        String st8 = getResources().getString(R.string.be_canceled);
        switch (this.callingState) {
            case NORMAL:
                txtBody = new EMTextMessageBody(st1 + this.callDruationText);
                break;
            case REFUESD:
                txtBody = new EMTextMessageBody(st2);
                break;
            case BEREFUESD:
                txtBody = new EMTextMessageBody(st3);
                break;
            case OFFLINE:
                txtBody = new EMTextMessageBody(st4);
                break;
            case BUSY:
                txtBody = new EMTextMessageBody(st5);
                break;
            case NORESPONSE:
                txtBody = new EMTextMessageBody(st6);
                break;
            case UNANSWERED:
                txtBody = new EMTextMessageBody(st7);
                break;
            case VERSION_NOT_SAME:
                txtBody = new EMTextMessageBody(getString(R.string.call_version_inconsistent));
                break;
            default:
                txtBody = new EMTextMessageBody(st8);
                break;
        }
        if (this.callType == 0) {
            message.setAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, true);
        } else {
            message.setAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, true);
        }
        message.addBody(txtBody);
        message.setMsgId(this.msgid);
        message.setStatus(EMMessage.Status.SUCCESS);
        EMClient.getInstance().chatManager().saveMessage(message);
    }
}

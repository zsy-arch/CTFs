package com.em.ui;

import android.hardware.Camera;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.em.DemoHelper;
import com.em.ui.CallActivity;
import com.hyphenate.chat.EMCallManager;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.media.EMLocalSurfaceView;
import com.hyphenate.media.EMOppositeSurfaceView;
import com.hyphenate.util.EMPrivateConstant;
import com.hyphenate.util.PathUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.UUID;

/* loaded from: classes.dex */
public class VideoCallActivity extends CallActivity implements View.OnClickListener {
    private Button answerBtn;
    private LinearLayout bottomContainer;
    private EMCallManager.EMVideoCallHelper callHelper;
    private TextView callStateTextView;
    private Chronometer chronometer;
    private LinearLayout comingBtnContainer;
    private ImageView handsFreeImage;
    private Button hangupBtn;
    private boolean isAnswered;
    private boolean isHandsfreeState;
    private boolean isInCalling;
    private boolean isMuteState;
    private TextView monitorTextView;
    private ImageView muteImage;
    private TextView netwrokStatusVeiw;
    private TextView nickTextView;
    private Button recordBtn;
    private Button refuseBtn;
    private RelativeLayout rootContainer;
    private Button toggleVideoBtn;
    private LinearLayout topContainer;
    private Handler uiHandler;
    private LinearLayout voiceContronlLayout;
    private boolean endCallTriggerByMe = false;
    private boolean monitor = true;
    boolean isRecording = false;
    private BrightnessDataProcess dataProcessor = new BrightnessDataProcess();

    /* loaded from: classes.dex */
    class BrightnessDataProcess implements EMCallManager.EMCameraDataProcessor {
        byte yDelta = 0;

        BrightnessDataProcess() {
            VideoCallActivity.this = this$0;
        }

        synchronized void setYDelta(byte yDelta) {
            Log.d("VideoCallActivity", "brigntness uDelta:" + ((int) yDelta));
            this.yDelta = yDelta;
        }

        @Override // com.hyphenate.chat.EMCallManager.EMCameraDataProcessor
        public synchronized void onProcessData(byte[] data, Camera camera, int width, int height, int rotateAngel) {
            int wh = width * height;
            for (int i = 0; i < wh; i++) {
                int d = (data[i] & 255) + this.yDelta;
                if (d < 16) {
                    d = 16;
                }
                if (d > 235) {
                    d = 235;
                }
                data[i] = (byte) d;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.CallActivity, com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            finish();
            return;
        }
        setContentView(R.layout.em_activity_video_call);
        DemoHelper.getInstance().isVideoCalling = true;
        this.callType = 1;
        getWindow().addFlags(6815872);
        this.uiHandler = new Handler();
        this.callStateTextView = (TextView) findViewById(R.id.tv_call_state);
        this.comingBtnContainer = (LinearLayout) findViewById(R.id.ll_coming_call);
        this.rootContainer = (RelativeLayout) findViewById(R.id.root_layout);
        this.refuseBtn = (Button) findViewById(R.id.btn_refuse_call);
        this.answerBtn = (Button) findViewById(R.id.btn_answer_call);
        this.hangupBtn = (Button) findViewById(R.id.btn_hangup_call);
        this.muteImage = (ImageView) findViewById(R.id.iv_mute);
        this.handsFreeImage = (ImageView) findViewById(R.id.iv_handsfree);
        this.callStateTextView = (TextView) findViewById(R.id.tv_call_state);
        this.nickTextView = (TextView) findViewById(R.id.tv_nick);
        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
        this.voiceContronlLayout = (LinearLayout) findViewById(R.id.ll_voice_control);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.ll_btns);
        this.topContainer = (LinearLayout) findViewById(R.id.ll_top_container);
        this.bottomContainer = (LinearLayout) findViewById(R.id.ll_bottom_container);
        this.monitorTextView = (TextView) findViewById(R.id.tv_call_monitor);
        this.netwrokStatusVeiw = (TextView) findViewById(R.id.tv_network_status);
        this.recordBtn = (Button) findViewById(R.id.btn_record_video);
        this.refuseBtn.setOnClickListener(this);
        this.answerBtn.setOnClickListener(this);
        this.hangupBtn.setOnClickListener(this);
        this.muteImage.setOnClickListener(this);
        this.handsFreeImage.setOnClickListener(this);
        this.rootContainer.setOnClickListener(this);
        this.recordBtn.setOnClickListener(this);
        ((Button) findViewById(R.id.btn_switch_camera)).setOnClickListener(this);
        ((SeekBar) findViewById(R.id.seekbar_y_detal)).setOnSeekBarChangeListener(new YDeltaSeekBarListener());
        this.msgid = UUID.randomUUID().toString();
        this.isInComingCall = getIntent().getBooleanExtra("isComingCall", false);
        this.username = getIntent().getStringExtra("username");
        this.nickTextView.setText(this.username);
        this.localSurface = (EMLocalSurfaceView) findViewById(R.id.local_surface);
        this.localSurface.setZOrderMediaOverlay(true);
        this.localSurface.setZOrderOnTop(true);
        this.oppositeSurface = (EMOppositeSurfaceView) findViewById(R.id.opposite_surface);
        addCallStateListener();
        setVolumeControlStream(2);
        if (!this.isInComingCall) {
            this.soundPool = new SoundPool(1, 2, 0);
            this.outgoing = this.soundPool.load(this, R.raw.em_outgoing, 1);
            this.comingBtnContainer.setVisibility(4);
            this.hangupBtn.setVisibility(0);
            this.callStateTextView.setText(getResources().getString(R.string.connected_to_each_other));
            EMClient.getInstance().callManager().setSurfaceView(this.localSurface, this.oppositeSurface);
            this.handler.sendEmptyMessage(0);
        } else {
            this.voiceContronlLayout.setVisibility(4);
            this.localSurface.setVisibility(4);
            Uri ringUri = RingtoneManager.getDefaultUri(1);
            this.audioManager.setMode(1);
            this.audioManager.setSpeakerphoneOn(true);
            this.ringtone = RingtoneManager.getRingtone(this, ringUri);
            this.ringtone.play();
            EMClient.getInstance().callManager().setSurfaceView(this.localSurface, this.oppositeSurface);
        }
        this.callHelper = EMClient.getInstance().callManager().getVideoCallHelper();
        EMClient.getInstance().callManager().setCameraDataProcessor(this.dataProcessor);
    }

    /* loaded from: classes.dex */
    class YDeltaSeekBarListener implements SeekBar.OnSeekBarChangeListener {
        YDeltaSeekBarListener() {
            VideoCallActivity.this = this$0;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            VideoCallActivity.this.dataProcessor.setYDelta((byte) ((20.0f * (progress - 50)) / 50.0f));
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /* renamed from: com.em.ui.VideoCallActivity$1 */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements EMCallStateChangeListener {
        AnonymousClass1() {
            VideoCallActivity.this = this$0;
        }

        @Override // com.hyphenate.chat.EMCallStateChangeListener
        public void onCallStateChanged(EMCallStateChangeListener.CallState callState, final EMCallStateChangeListener.CallError error) {
            switch (AnonymousClass3.$SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[callState.ordinal()]) {
                case 1:
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.callStateTextView.setText(R.string.connected_to_each_other);
                        }
                    });
                    return;
                case 2:
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.callStateTextView.setText(R.string.have_connected_with);
                        }
                    });
                    return;
                case 3:
                    VideoCallActivity.this.setVolumeControlStream(0);
                    VideoCallActivity.this.handler.removeCallbacks(VideoCallActivity.this.timeoutHangup);
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.3
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (VideoCallActivity.this.soundPool != null) {
                                    VideoCallActivity.this.soundPool.stop(VideoCallActivity.this.streamID);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            VideoCallActivity.this.openSpeakerOn();
                            ((TextView) VideoCallActivity.this.findViewById(R.id.tv_is_p2p)).setText(EMClient.getInstance().callManager().isDirectCall() ? R.string.direct_call : R.string.relay_call);
                            VideoCallActivity.this.handsFreeImage.setImageResource(R.drawable.em_icon_speaker_on);
                            VideoCallActivity.this.isHandsfreeState = true;
                            VideoCallActivity.this.isInCalling = true;
                            VideoCallActivity.this.chronometer.setVisibility(0);
                            VideoCallActivity.this.chronometer.setBase(SystemClock.elapsedRealtime());
                            VideoCallActivity.this.chronometer.start();
                            VideoCallActivity.this.nickTextView.setVisibility(4);
                            VideoCallActivity.this.callStateTextView.setText(R.string.in_the_call);
                            VideoCallActivity.this.recordBtn.setVisibility(0);
                            VideoCallActivity.this.callingState = CallActivity.CallingState.NORMAL;
                        }
                    });
                    return;
                case 4:
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.netwrokStatusVeiw.setVisibility(0);
                            if (error == EMCallStateChangeListener.CallError.ERROR_NO_DATA) {
                                VideoCallActivity.this.netwrokStatusVeiw.setText(R.string.no_call_data);
                            } else {
                                VideoCallActivity.this.netwrokStatusVeiw.setText(R.string.network_unstable);
                            }
                        }
                    });
                    return;
                case 5:
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.5
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.netwrokStatusVeiw.setVisibility(4);
                        }
                    });
                    return;
                case 6:
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.6
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.netwrokStatusVeiw.setText(R.string.mute_prompt);
                        }
                    });
                    return;
                case 7:
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.7
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.netwrokStatusVeiw.setText("");
                        }
                    });
                    return;
                case 8:
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.8
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.netwrokStatusVeiw.setText(R.string.mute_prompt);
                        }
                    });
                    return;
                case 9:
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.9
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.netwrokStatusVeiw.setText("");
                        }
                    });
                    return;
                case 10:
                    VideoCallActivity.this.handler.removeCallbacks(VideoCallActivity.this.timeoutHangup);
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.10
                        private void postDelayedCloseMsg() {
                            VideoCallActivity.this.uiHandler.postDelayed(new Runnable() { // from class: com.em.ui.VideoCallActivity.1.10.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    VideoCallActivity.this.saveCallRecord();
                                    Animation animation = new AlphaAnimation(1.0f, 0.0f);
                                    animation.setDuration(800L);
                                    VideoCallActivity.this.rootContainer.startAnimation(animation);
                                    VideoCallActivity.this.finish();
                                }
                            }, 3000L);
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.chronometer.stop();
                            VideoCallActivity.this.callDruationText = VideoCallActivity.this.chronometer.getText().toString();
                            String s1 = VideoCallActivity.this.getResources().getString(R.string.other_party_refused_to_accept);
                            String s2 = VideoCallActivity.this.getResources().getString(R.string.connection_failure);
                            String s3 = VideoCallActivity.this.getResources().getString(R.string.other_party_is_not_online);
                            String s4 = VideoCallActivity.this.getResources().getString(R.string.other_is_on_the_phone_please);
                            String s5 = VideoCallActivity.this.getResources().getString(R.string.other_party_did_not_answer);
                            String s6 = VideoCallActivity.this.getResources().getString(R.string.hang_up);
                            String s7 = VideoCallActivity.this.getResources().getString(R.string.other_is_hang_up);
                            String s8 = VideoCallActivity.this.getResources().getString(R.string.not_answer);
                            String s9 = VideoCallActivity.this.getResources().getString(R.string.be_canceled);
                            if (error == EMCallStateChangeListener.CallError.REJECTED) {
                                VideoCallActivity.this.callingState = CallActivity.CallingState.BEREFUESD;
                                VideoCallActivity.this.callStateTextView.setText(s1);
                            } else if (error == EMCallStateChangeListener.CallError.ERROR_TRANSPORT) {
                                VideoCallActivity.this.callStateTextView.setText(s2);
                            } else if (error == EMCallStateChangeListener.CallError.ERROR_UNAVAILABLE) {
                                VideoCallActivity.this.callingState = CallActivity.CallingState.OFFLINE;
                                VideoCallActivity.this.callStateTextView.setText(s3);
                            } else if (error == EMCallStateChangeListener.CallError.ERROR_BUSY) {
                                VideoCallActivity.this.callingState = CallActivity.CallingState.BUSY;
                                VideoCallActivity.this.callStateTextView.setText(s4);
                            } else if (error == EMCallStateChangeListener.CallError.ERROR_NORESPONSE) {
                                VideoCallActivity.this.callingState = CallActivity.CallingState.NORESPONSE;
                                VideoCallActivity.this.callStateTextView.setText(s5);
                            } else if (error == EMCallStateChangeListener.CallError.ERROR_LOCAL_SDK_VERSION_OUTDATED || error == EMCallStateChangeListener.CallError.ERROR_REMOTE_SDK_VERSION_OUTDATED) {
                                VideoCallActivity.this.callingState = CallActivity.CallingState.VERSION_NOT_SAME;
                                VideoCallActivity.this.callStateTextView.setText(R.string.call_version_inconsistent);
                            } else if (VideoCallActivity.this.isAnswered) {
                                VideoCallActivity.this.callingState = CallActivity.CallingState.NORMAL;
                                if (!VideoCallActivity.this.endCallTriggerByMe) {
                                    VideoCallActivity.this.callStateTextView.setText(s7);
                                }
                            } else if (VideoCallActivity.this.isInComingCall) {
                                VideoCallActivity.this.callingState = CallActivity.CallingState.UNANSWERED;
                                VideoCallActivity.this.callStateTextView.setText(s8);
                            } else if (VideoCallActivity.this.callingState != CallActivity.CallingState.NORMAL) {
                                VideoCallActivity.this.callingState = CallActivity.CallingState.CANCED;
                                VideoCallActivity.this.callStateTextView.setText(s9);
                            } else {
                                VideoCallActivity.this.callStateTextView.setText(s6);
                            }
                            postDelayedCloseMsg();
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    }

    void addCallStateListener() {
        this.callStateListener = new AnonymousClass1();
        EMClient.getInstance().callManager().addCallStateChangeListener(this.callStateListener);
    }

    /* renamed from: com.em.ui.VideoCallActivity$3 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState = new int[EMCallStateChangeListener.CallState.values().length];

        static {
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.ACCEPTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.NETWORK_UNSTABLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.NETWORK_NORMAL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.VIDEO_PAUSE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.VIDEO_RESUME.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.VOICE_PAUSE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.VOICE_RESUME.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.DISCONNECTED.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.root_layout /* 2131756606 */:
                if (this.callingState != CallActivity.CallingState.NORMAL) {
                    return;
                }
                if (this.bottomContainer.getVisibility() == 0) {
                    this.bottomContainer.setVisibility(8);
                    this.topContainer.setVisibility(8);
                    return;
                }
                this.bottomContainer.setVisibility(0);
                this.topContainer.setVisibility(0);
                return;
            case R.id.btn_record_video /* 2131756614 */:
                if (!this.isRecording) {
                    this.callHelper.startVideoRecord(PathUtil.getInstance().getVideoPath().getAbsolutePath());
                    this.isRecording = true;
                    this.recordBtn.setText(R.string.stop_record);
                    return;
                }
                String filepath = this.callHelper.stopVideoRecord();
                this.isRecording = false;
                this.recordBtn.setText(R.string.recording_video);
                Toast.makeText(getApplicationContext(), String.format(getString(R.string.record_finish_toast), filepath), 0).show();
                return;
            case R.id.btn_switch_camera /* 2131756615 */:
                this.handler.sendEmptyMessage(6);
                return;
            case R.id.iv_mute /* 2131756622 */:
                if (this.isMuteState) {
                    this.muteImage.setImageResource(R.drawable.em_icon_mute_normal);
                    try {
                        EMClient.getInstance().callManager().resumeVoiceTransfer();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                    this.isMuteState = false;
                    return;
                }
                this.muteImage.setImageResource(R.drawable.em_icon_mute_on);
                try {
                    EMClient.getInstance().callManager().pauseVoiceTransfer();
                } catch (HyphenateException e2) {
                    e2.printStackTrace();
                }
                this.isMuteState = true;
                return;
            case R.id.iv_handsfree /* 2131756623 */:
                if (this.isHandsfreeState) {
                    this.handsFreeImage.setImageResource(R.drawable.em_icon_speaker_normal);
                    closeSpeakerOn();
                    this.isHandsfreeState = false;
                    return;
                }
                this.handsFreeImage.setImageResource(R.drawable.em_icon_speaker_on);
                openSpeakerOn();
                this.isHandsfreeState = true;
                return;
            case R.id.btn_hangup_call /* 2131756624 */:
                this.hangupBtn.setEnabled(false);
                this.chronometer.stop();
                this.endCallTriggerByMe = true;
                this.callStateTextView.setText(getResources().getString(R.string.hanging_up));
                if (this.isRecording) {
                    this.callHelper.stopVideoRecord();
                }
                this.handler.sendEmptyMessage(4);
                return;
            case R.id.btn_refuse_call /* 2131756626 */:
                this.refuseBtn.setEnabled(false);
                this.handler.sendEmptyMessage(3);
                return;
            case R.id.btn_answer_call /* 2131756627 */:
                this.answerBtn.setEnabled(false);
                openSpeakerOn();
                if (this.ringtone != null) {
                    this.ringtone.stop();
                }
                this.callStateTextView.setText("answering...");
                this.handler.sendEmptyMessage(2);
                this.handsFreeImage.setImageResource(R.drawable.em_icon_speaker_on);
                this.isAnswered = true;
                this.isHandsfreeState = true;
                this.comingBtnContainer.setVisibility(4);
                this.hangupBtn.setVisibility(0);
                this.voiceContronlLayout.setVisibility(0);
                this.localSurface.setVisibility(0);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.CallActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        DemoHelper.getInstance().isVideoCalling = false;
        stopMonitor();
        if (this.isRecording) {
            this.callHelper.stopVideoRecord();
            this.isRecording = false;
        }
        this.localSurface = null;
        this.oppositeSurface = null;
        super.onDestroy();
    }

    @Override // com.em.ui.CallActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        this.callDruationText = this.chronometer.getText().toString();
        super.onBackPressed();
    }

    void startMonitor() {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.VideoCallActivity.2
            @Override // java.lang.Runnable
            public void run() {
                while (VideoCallActivity.this.monitor) {
                    VideoCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VideoCallActivity.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoCallActivity.this.monitorTextView.setText("WidthxHeight：" + VideoCallActivity.this.callHelper.getVideoWidth() + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + VideoCallActivity.this.callHelper.getVideoHeight() + "\nDelay：" + VideoCallActivity.this.callHelper.getVideoLatency() + "\nFramerate：" + VideoCallActivity.this.callHelper.getVideoFrameRate() + "\nLost：" + VideoCallActivity.this.callHelper.getVideoLostRate() + "\nLocalBitrate：" + VideoCallActivity.this.callHelper.getLocalBitrate() + "\nRemoteBitrate：" + VideoCallActivity.this.callHelper.getRemoteBitrate());
                        }
                    });
                    try {
                        Thread.sleep(1500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    void stopMonitor() {
        this.monitor = false;
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (this.isInCalling) {
            try {
                EMClient.getInstance().callManager().pauseVideoTransfer();
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isInCalling) {
            try {
                EMClient.getInstance().callManager().resumeVideoTransfer();
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }
}

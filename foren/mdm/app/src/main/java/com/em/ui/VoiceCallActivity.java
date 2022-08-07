package com.em.ui;

import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.easeui.utils.EaseUserUtils;
import com.em.DemoHelper;
import com.em.ui.CallActivity;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.vsf2f.f2f.R;
import java.util.UUID;

/* loaded from: classes.dex */
public class VoiceCallActivity extends CallActivity implements View.OnClickListener {
    private Button answerBtn;
    private TextView callStateTextView;
    private Chronometer chronometer;
    private LinearLayout comingBtnContainer;
    private boolean endCallTriggerByMe = false;
    private ImageView handsFreeImage;
    private Button hangupBtn;
    private boolean isHandsfreeState;
    private boolean isMuteState;
    private ImageView muteImage;
    private TextView netwrokStatusVeiw;
    private Button refuseBtn;
    String st1;
    private LinearLayout voiceContronlLayout;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.CallActivity, com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            finish();
            return;
        }
        setContentView(R.layout.em_activity_voice_call);
        DemoHelper.getInstance().isVoiceCalling = true;
        this.callType = 0;
        this.comingBtnContainer = (LinearLayout) findViewById(R.id.ll_coming_call);
        this.refuseBtn = (Button) findViewById(R.id.btn_refuse_call);
        this.answerBtn = (Button) findViewById(R.id.btn_answer_call);
        this.hangupBtn = (Button) findViewById(R.id.btn_hangup_call);
        this.muteImage = (ImageView) findViewById(R.id.iv_mute);
        this.handsFreeImage = (ImageView) findViewById(R.id.iv_handsfree);
        this.callStateTextView = (TextView) findViewById(R.id.tv_call_state);
        TextView textView = (TextView) findViewById(R.id.tv_calling_duration);
        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
        this.voiceContronlLayout = (LinearLayout) findViewById(R.id.ll_voice_control);
        this.netwrokStatusVeiw = (TextView) findViewById(R.id.tv_network_status);
        this.refuseBtn.setOnClickListener(this);
        this.answerBtn.setOnClickListener(this);
        this.hangupBtn.setOnClickListener(this);
        this.muteImage.setOnClickListener(this);
        this.handsFreeImage.setOnClickListener(this);
        getWindow().addFlags(6815872);
        addCallStateListener();
        this.msgid = UUID.randomUUID().toString();
        this.username = getIntent().getStringExtra("username");
        this.isInComingCall = getIntent().getBooleanExtra("isComingCall", false);
        setVolumeControlStream(2);
        if (!this.isInComingCall) {
            this.soundPool = new SoundPool(1, 2, 0);
            this.outgoing = this.soundPool.load(this, R.raw.em_outgoing, 1);
            this.comingBtnContainer.setVisibility(4);
            this.hangupBtn.setVisibility(0);
            this.st1 = getResources().getString(R.string.connected_to_each_other);
            this.callStateTextView.setText(this.st1);
            this.handler.sendEmptyMessage(1);
        } else {
            this.voiceContronlLayout.setVisibility(4);
            Uri ringUri = RingtoneManager.getDefaultUri(1);
            this.audioManager.setMode(1);
            this.audioManager.setSpeakerphoneOn(true);
            this.ringtone = RingtoneManager.getRingtone(this, ringUri);
            this.ringtone.play();
        }
        EaseUserUtils.setUserAvatar(this, this.username, (ImageView) findViewById(R.id.iv_avatar));
        EaseUserUtils.setUserNick(this.username, (TextView) findViewById(R.id.tv_nick));
    }

    /* renamed from: com.em.ui.VoiceCallActivity$1 */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements EMCallStateChangeListener {
        AnonymousClass1() {
            VoiceCallActivity.this = this$0;
        }

        @Override // com.hyphenate.chat.EMCallStateChangeListener
        public void onCallStateChanged(EMCallStateChangeListener.CallState callState, final EMCallStateChangeListener.CallError error) {
            EMLog.d("EMCallManager", "onCallStateChanged:" + callState);
            switch (AnonymousClass2.$SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[callState.ordinal()]) {
                case 1:
                    VoiceCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            VoiceCallActivity.this.callStateTextView.setText(VoiceCallActivity.this.st1);
                        }
                    });
                    return;
                case 2:
                    VoiceCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            VoiceCallActivity.this.callStateTextView.setText(VoiceCallActivity.this.getResources().getString(R.string.have_connected_with));
                        }
                    });
                    return;
                case 3:
                    VoiceCallActivity.this.setVolumeControlStream(0);
                    VoiceCallActivity.this.handler.removeCallbacks(VoiceCallActivity.this.timeoutHangup);
                    VoiceCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.3
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (VoiceCallActivity.this.soundPool != null) {
                                    VoiceCallActivity.this.soundPool.stop(VoiceCallActivity.this.streamID);
                                }
                            } catch (Exception e) {
                                HyUtil.printException(e);
                            }
                            if (!VoiceCallActivity.this.isHandsfreeState) {
                                VoiceCallActivity.this.closeSpeakerOn();
                            }
                            ((TextView) VoiceCallActivity.this.findViewById(R.id.tv_is_p2p)).setText(EMClient.getInstance().callManager().isDirectCall() ? R.string.direct_call : R.string.relay_call);
                            VoiceCallActivity.this.chronometer.setVisibility(0);
                            VoiceCallActivity.this.chronometer.setBase(SystemClock.elapsedRealtime());
                            VoiceCallActivity.this.chronometer.start();
                            VoiceCallActivity.this.callStateTextView.setText(VoiceCallActivity.this.getResources().getString(R.string.in_the_call));
                            VoiceCallActivity.this.callingState = CallActivity.CallingState.NORMAL;
                        }
                    });
                    return;
                case 4:
                    VoiceCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            VoiceCallActivity.this.netwrokStatusVeiw.setVisibility(0);
                            if (error == EMCallStateChangeListener.CallError.ERROR_NO_DATA) {
                                VoiceCallActivity.this.netwrokStatusVeiw.setText(R.string.no_call_data);
                            } else {
                                VoiceCallActivity.this.netwrokStatusVeiw.setText(R.string.network_unstable);
                            }
                        }
                    });
                    return;
                case 5:
                    VoiceCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.5
                        @Override // java.lang.Runnable
                        public void run() {
                            VoiceCallActivity.this.netwrokStatusVeiw.setVisibility(4);
                        }
                    });
                    return;
                case 6:
                    VoiceCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.6
                        @Override // java.lang.Runnable
                        public void run() {
                            MyToast.show(VoiceCallActivity.this.getApplicationContext(), "对方已静音");
                        }
                    });
                    return;
                case 7:
                    VoiceCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.7
                        @Override // java.lang.Runnable
                        public void run() {
                            MyToast.show(VoiceCallActivity.this.getApplicationContext(), "对方打开麦克风");
                        }
                    });
                    return;
                case 8:
                    VoiceCallActivity.this.handler.removeCallbacks(VoiceCallActivity.this.timeoutHangup);
                    VoiceCallActivity.this.runOnUiThread(new AnonymousClass8(error));
                    return;
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.em.ui.VoiceCallActivity$1$8 */
        /* loaded from: classes.dex */
        public class AnonymousClass8 implements Runnable {
            final /* synthetic */ EMCallStateChangeListener.CallError val$fError;

            AnonymousClass8(EMCallStateChangeListener.CallError callError) {
                AnonymousClass1.this = this$1;
                this.val$fError = callError;
            }

            private void postDelayedCloseMsg() {
                VoiceCallActivity.this.handler.postDelayed(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VoiceCallActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.VoiceCallActivity.1.8.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Log.d("AAA", "CALL DISCONNETED");
                                VoiceCallActivity.this.saveCallRecord();
                                Animation animation = new AlphaAnimation(1.0f, 0.0f);
                                animation.setDuration(800L);
                                VoiceCallActivity.this.findViewById(R.id.root_layout).startAnimation(animation);
                                VoiceCallActivity.this.finish();
                            }
                        });
                    }
                }, 200L);
            }

            @Override // java.lang.Runnable
            public void run() {
                VoiceCallActivity.this.chronometer.stop();
                VoiceCallActivity.this.callDruationText = VoiceCallActivity.this.chronometer.getText().toString();
                String st2 = VoiceCallActivity.this.getResources().getString(R.string.other_party_refused_to_accept);
                String st3 = VoiceCallActivity.this.getResources().getString(R.string.connection_failure);
                String st4 = VoiceCallActivity.this.getResources().getString(R.string.other_party_is_not_online);
                String st5 = VoiceCallActivity.this.getResources().getString(R.string.other_is_on_the_phone_please);
                String st6 = VoiceCallActivity.this.getResources().getString(R.string.other_party_did_not_answer_new);
                VoiceCallActivity.this.getResources().getString(R.string.hang_up);
                String st8 = VoiceCallActivity.this.getResources().getString(R.string.other_is_hang_up);
                String st9 = VoiceCallActivity.this.getResources().getString(R.string.not_answer);
                String st10 = VoiceCallActivity.this.getResources().getString(R.string.be_canceled);
                String st11 = VoiceCallActivity.this.getResources().getString(R.string.hang_up);
                if (this.val$fError == EMCallStateChangeListener.CallError.REJECTED) {
                    VoiceCallActivity.this.callingState = CallActivity.CallingState.BEREFUESD;
                    VoiceCallActivity.this.callStateTextView.setText(st2);
                } else if (this.val$fError == EMCallStateChangeListener.CallError.ERROR_TRANSPORT) {
                    VoiceCallActivity.this.callStateTextView.setText(st3);
                } else if (this.val$fError == EMCallStateChangeListener.CallError.ERROR_UNAVAILABLE) {
                    VoiceCallActivity.this.callingState = CallActivity.CallingState.OFFLINE;
                    VoiceCallActivity.this.callStateTextView.setText(st4);
                } else if (this.val$fError == EMCallStateChangeListener.CallError.ERROR_BUSY) {
                    VoiceCallActivity.this.callingState = CallActivity.CallingState.BUSY;
                    VoiceCallActivity.this.callStateTextView.setText(st5);
                } else if (this.val$fError == EMCallStateChangeListener.CallError.ERROR_NORESPONSE) {
                    VoiceCallActivity.this.callingState = CallActivity.CallingState.NORESPONSE;
                    VoiceCallActivity.this.callStateTextView.setText(st6);
                } else if (this.val$fError == EMCallStateChangeListener.CallError.ERROR_LOCAL_SDK_VERSION_OUTDATED || this.val$fError == EMCallStateChangeListener.CallError.ERROR_REMOTE_SDK_VERSION_OUTDATED) {
                    VoiceCallActivity.this.callingState = CallActivity.CallingState.VERSION_NOT_SAME;
                    VoiceCallActivity.this.callStateTextView.setText(R.string.call_version_inconsistent);
                } else if (VoiceCallActivity.this.isAnswered) {
                    VoiceCallActivity.this.callingState = CallActivity.CallingState.NORMAL;
                    if (!VoiceCallActivity.this.endCallTriggerByMe) {
                        VoiceCallActivity.this.callStateTextView.setText(st8);
                    }
                } else if (VoiceCallActivity.this.isInComingCall) {
                    VoiceCallActivity.this.callingState = CallActivity.CallingState.UNANSWERED;
                    VoiceCallActivity.this.callStateTextView.setText(st9);
                } else if (VoiceCallActivity.this.callingState != CallActivity.CallingState.NORMAL) {
                    VoiceCallActivity.this.callingState = CallActivity.CallingState.CANCED;
                    VoiceCallActivity.this.callStateTextView.setText(st10);
                } else {
                    VoiceCallActivity.this.callStateTextView.setText(st11);
                }
                postDelayedCloseMsg();
            }
        }
    }

    void addCallStateListener() {
        this.callStateListener = new AnonymousClass1();
        EMClient.getInstance().callManager().addCallStateChangeListener(this.callStateListener);
    }

    /* renamed from: com.em.ui.VoiceCallActivity$2 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
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
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.VOICE_PAUSE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.VOICE_RESUME.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$hyphenate$chat$EMCallStateChangeListener$CallState[EMCallStateChangeListener.CallState.DISCONNECTED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
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
                this.handler.sendEmptyMessage(4);
                return;
            case R.id.ll_coming_call /* 2131756625 */:
            default:
                return;
            case R.id.btn_refuse_call /* 2131756626 */:
                this.refuseBtn.setEnabled(false);
                this.handler.sendEmptyMessage(3);
                return;
            case R.id.btn_answer_call /* 2131756627 */:
                this.answerBtn.setEnabled(false);
                closeSpeakerOn();
                this.callStateTextView.setText("正在接听...");
                this.comingBtnContainer.setVisibility(4);
                this.hangupBtn.setVisibility(0);
                this.voiceContronlLayout.setVisibility(0);
                this.handler.sendEmptyMessage(2);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.CallActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        DemoHelper.getInstance().isVoiceCalling = false;
    }

    @Override // com.em.ui.CallActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        this.callDruationText = this.chronometer.getText().toString();
    }
}
